package com.jnj.adf.xd.mutifiles.listener;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jnj.adf.client.api.ADFService;
import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.adf.xd.mutifiles.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

import javax.annotation.Resource;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdfParquetListener  implements StepExecutionListener{
	private static final Logger logger = LoggerFactory.getLogger(AdfParquetListener.class);
	
	private String hdfsLocation = "";
	private String kPrincipal = "";
	private String keyPath = "";
	private String hdfsFilePath = "";
	private String hdfsConfigPath = "";
	
	private String awsAccessKeyId = "";
	private String awsSecretAccessKey = "";
	private String s3Region = "";
	private boolean deltaLoad = false;
	private String envName;
	
	private String ftpHost;
	private int ftpPort;
	private String ftpUser;
	private String ftpPassword;
	private String ftpPath;
	
	private String target;
	private String endPoint;
	
	private String reportPath;
	
	private boolean statusFile = false;
	
	private boolean columnsValidation = true;
	
	private String format;
	
	private String fileNamePrefix = "";
	private String triggerFilePrefix = "";
	
	@Resource
	ADFService adfService;
	
	private JobStepFileUtil pfUtil = JobStepFileUtil.getInstance();
	
	private ReportUtil ru = ReportUtil.getInstance();

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if(StringUtils.isNotBlank(reportPath)){
			String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
			String path = getReportPath(reportPath,jobName);
			ru.setFilePath(path);
		}
		ExitStatus es = writeTarget(stepExecution);
		return es;
	}

	private String getReportPath(String reportPath2, String jobName) {
		String path = "";
		if(reportPath2.endsWith("/")){
			path = reportPath2+jobName;
		}else{
			path = reportPath2+"/"+jobName;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		path+="-"+sdf.format(new Date())+".xlsx";
		return path;
	}

	private ExitStatus writeTarget(StepExecution stepExecution) {
		int readCount = stepExecution.getReadCount();
		int writeCount = stepExecution.getWriteCount();
		String parquetFileListKey = (String)stepExecution.getExecutionContext().get(JobStepFileUtil.FILE_LIST_KEY);
		
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		long count  = Long.valueOf(String.valueOf(executionContext.get("count")));
		Integer serverSendCount = pfUtil.getServerSendSize(parquetFileListKey); 
		
		logger.info("Read count :{} ,write count:{} ,region count:{},serverSendCount:{}",readCount,writeCount,count,serverSendCount);
		
		if(readCount != writeCount){
			return ExitStatus.FAILED.addExitDescription("ReadCount not equals writeCount!");
		}
		
		if(serverSendCount==null){
			return ExitStatus.FAILED.addExitDescription("The remoteService does not run successfully.");
		}
		
		if(serverSendCount != readCount){
			return ExitStatus.FAILED.addExitDescription("Server send count is "+serverSendCount+", but readCount is "+readCount+"!");
		}
		
		if(count>readCount){
			return ExitStatus.FAILED.addExitDescription("Server send count is "+serverSendCount+",but region count is "+count);
		}
		
		String parquetTrue = pfUtil.getParquetTrue(parquetFileListKey);
		if(parquetTrue!=null){
			if(columnsValidation){
				return ExitStatus.FAILED.addExitDescription("Parquet format wrong,"+parquetTrue);
			}else{
				stepExecution.getExitStatus().addExitDescription("Parquet format wrong,"+parquetTrue);
			}
		}
		
		return writeToTarget(stepExecution);
	}

	private ExitStatus writeToTarget(StepExecution stepExecution) {
		if(target!=null){
			System.out.println("stating upload file to "+target.trim().toLowerCase());
			ExecutionContext executionContext = stepExecution.getExecutionContext();
			String parquetFileListKey = (String)executionContext.get(JobStepFileUtil.FILE_LIST_KEY);
			String regionName = (String)executionContext.get("regionName");
			try {
				List<String> parquetFileList = pfUtil.removePath(JobStepFileUtil.SystemType.HDFS,parquetFileListKey);
				Closeable writer = pfUtil.removeWriter(parquetFileListKey);
				if(writer!=null){
					writer.close();
				}
				if (ExitStatus.COMPLETED.equals(stepExecution.getExitStatus())){
					if(stepExecution.getReadCount() > 0 ){
						if(target.trim().toLowerCase().equals("hdfs")){
							return writeHdfs(regionName,parquetFileList,stepExecution);
						}
					
						if(target.trim().toLowerCase().equals("s3")){
							return writeS3(regionName,parquetFileList,false,stepExecution);
						}
						if(target.trim().toLowerCase().equals("locals3")){
							return writeS3(regionName,parquetFileList,true,stepExecution);
						}
						if(target.trim().toLowerCase().equals("ftp")){
							return writeFtp(regionName,parquetFileList,stepExecution);
						}
					}
				}
			}catch (Exception e) {
				logger.error("Upload to ftp error:",e);
				return ExitStatus.FAILED.addExitDescription("File is done but can't upload to "+target.trim().toLowerCase()+"!").addExitDescription(e);
			}
		}
		return stepExecution.getExitStatus();
	}

	private ExitStatus writeFtp(String regionName , List<String> parquetFileList,StepExecution stepExecution) {
		FtpUtil ftpUtil = null;
		try {
			if(parquetFileList!=null && !parquetFileList.isEmpty()){
				ftpUtil = new FtpUtil(ftpHost,ftpPort,ftpUser,ftpPassword);
				if(!ftpUtil.isConnect())
					throw new Exception("connect error");
				
				String realPath = ftpPath.endsWith("/") ? ftpPath.substring(0, ftpPath.length()-1) : ftpPath;
				
				long totalSize = 0L;
				
				for(String filePath : parquetFileList){
					File parquetFile = new File(filePath);
					
					long start = System.currentTimeMillis();
					ftpUtil.uploadFile(parquetFile, realPath);
					long end = System.currentTimeMillis();
					
					totalSize += new File(filePath).length();
					
					if(ru.getWriteReport()){
						ru.addDetail(regionName,filePath,realPath,end-start);
					}
				}
				
				if(StringUtils.isNotBlank(format) && JobStepFileUtil.FileFormat.Optymyze.equal(format) && statusFile){
					uploadOptymyzeTriggerFile(ftpUtil,regionName,parquetFileList.get(0),stepExecution.getReadCount(),realPath);
				}
				
				if(ru.getWriteReport()){
					ru.addSummarization(regionName,realPath,stepExecution.getReadCount(),parquetFileList.size(),totalSize,stepExecution);
				}
			}
		} catch (Exception e) {
			logger.error("Upload to ftp error:",e);
			return ExitStatus.FAILED.addExitDescription("File is done but can't upload to ftp!").addExitDescription(e);
		}finally {
			if(ftpUtil!=null)
				ftpUtil.close();
		}
		return stepExecution.getExitStatus();
	}
	
	private void uploadOptymyzeTriggerFile(FtpUtil ftpUtil, String regionName, String filePath, int readCount, String realPath) throws Exception {
		String firstColumn = triggerFilePrefix;
		if(StringUtils.isBlank(firstColumn)){
			regionName = CommonUtils.getRegionPath(regionName, false);
			firstColumn = regionName.replaceAll("/", "_").toUpperCase();
		}

		int lastIndex = filePath.lastIndexOf("_DETL_");
		int last_Index = filePath.lastIndexOf("_");
		int lastDotIndex = filePath.lastIndexOf(".");
		String timeStmap = filePath.substring(last_Index+1, lastDotIndex);
		String triggerFilePath = filePath.substring(0, lastIndex)+"_TRIGGER_"+filePath.substring(lastIndex+"_DETL_".length());
		
		String text = firstColumn+"|"+timeStmap+"|"+readCount;
		
		List<String> lines = new ArrayList<String>();
		lines.add(text);
		FileUtils.writeLines(new File(triggerFilePath), lines);
		
		ftpUtil.uploadFile(triggerFilePath, realPath);
	}

	public ExitStatus writeS3(String regionName , List<String> parquetFileList,boolean local,StepExecution stepExecution){
		try {
			if(parquetFileList!=null && !parquetFileList.isEmpty()){
				AmazonS3 s3 = null;
				if(local){
					AWSCredentials credentials1 = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
					ClientConfiguration localClientConfig = new ClientConfiguration();
					localClientConfig.setProtocol(Protocol.HTTP);
					s3 = new AmazonS3Client(credentials1, localClientConfig);
					s3.setEndpoint(endPoint);
					S3ClientOptions s3opt = new S3ClientOptions();
					s3opt.withPathStyleAccess(true);
					s3.setS3ClientOptions(s3opt);
				}else{
					AWSCredentials credentials = null;
					credentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
					ClientConfiguration clientConfig = new ClientConfiguration();
					clientConfig.setProtocol(Protocol.HTTPS);
					s3 = new AmazonS3Client(credentials, clientConfig);
					Region r = Region.getRegion(Regions.fromName(s3Region));
				    s3.setRegion(r);
				}
				
			    String bucketName = "eads-data-ingestion";
			    String sePath = getS3PathForRegion(regionName);
			    
			    if(isDeltaLoad()){
			        bucketName= new StringBuilder(bucketName).append("/unprocess/").append(envName).append("/delta-data/")
			        		.append(DateUtil.format(new Date())).append("/").append(sePath).toString();
			    } else {
			        bucketName= new StringBuilder(bucketName).append("/unprocess/").append(envName).append("/full-data/")
			        		.append(sePath).toString();
			    }
				
				long totalSize = 0L;
			    for(String filePath : parquetFileList){
			    	File file = new File(filePath);
			    	
			    	long start = System.currentTimeMillis();
			    	PutObjectRequest por = new PutObjectRequest(bucketName, file.getName(), file);
			    	ObjectMetadata objectMetadata = new ObjectMetadata();
				    objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);     
				    por.setMetadata(objectMetadata);
				    logger.info("Uploading "+filePath+" to S3 from a file\n");
				    s3.putObject(por);
				    long end = System.currentTimeMillis();
				    
				    totalSize+=new File(filePath).length();
					if(ru.getWriteReport()){
						ru.addDetail(regionName,filePath,bucketName,end-start);
					}
			    }
			    if(ru.getWriteReport()){
			    	ru.addSummarization(regionName,bucketName,stepExecution.getReadCount(),parquetFileList.size(),totalSize,stepExecution);
				}
			}
		} catch (Exception e) {
			logger.error("Upload to S3 error:",e);
			return ExitStatus.FAILED.addExitDescription("File is done but can't upload to S3!").addExitDescription(e);
		}
		return stepExecution.getExitStatus();
	}

	private String getS3PathForRegion(String regionName) {
		if(regionName.startsWith("/"))
			regionName = regionName.substring(1);
		if(regionName.endsWith("/"))
			regionName = regionName.substring(0,regionName.length()-1);
		return regionName;
	}

	public ExitStatus writeHdfs(String regionName , List<String> parquetFileList,StepExecution stepExecution){
		try {
			String realPath = CommonUtils.getPathWithRegionName(hdfsFilePath, regionName);
			Configuration conf = HdfsOperation.KerberosLogin(hdfsLocation, kPrincipal, keyPath,hdfsConfigPath);
			int readCount = stepExecution.getReadCount();
			
			if(parquetFileList!=null && !parquetFileList.isEmpty() && readCount>0){
				long totalSize = 0L;
				for(String filePath : parquetFileList){
					long start = System.currentTimeMillis();
					HdfsOperation.addFile(filePath, realPath, conf);
					long end = System.currentTimeMillis();
					totalSize+=new File(filePath).length();
					if(ru.getWriteReport()){
						ru.addDetail(regionName,filePath,realPath,end-start);
					}
				}
				if(ru.getWriteReport()){
					ru.addSummarization(regionName,realPath,stepExecution.getReadCount(),parquetFileList.size(),totalSize,stepExecution);
				}
			}
			if(statusFile){
				pushStatusFile(regionName,realPath,parquetFileList,readCount,conf);
			}
		} catch (Exception e) {
			logger.error("Upload to HDFS error",e);
			return ExitStatus.FAILED.addExitDescription("File is done but can't upload to HDFS!").addExitDescription(e);
		}
		return stepExecution.getExitStatus();
	}
	
	private void pushStatusFile(String regionName,String realPath, List<String> parquetFileList,int readCount ,Configuration conf) throws IOException {
		String patht = CommonUtils.getFileNameFromRegionName(regionName);
		
		String endFilePath = patht+"_completed.parquet";
		if(parquetFileList!=null && parquetFileList.size()>0){
			String filePath = parquetFileList.get(0);
			String parquetDirec = filePath.substring(0,filePath.lastIndexOf("/"));
			endFilePath = parquetDirec+"/"+patht+"_completed.parquet";
		}
		File endFile = new File(endFilePath);
		if(endFile.exists() && endFile.isFile()){
			endFile.delete();
		}
		endFile.createNewFile();
		HdfsOperation.addFile(endFile.getAbsolutePath(), realPath, conf);
		if(parquetFileList==null || parquetFileList.size()<=0){
			endFile.delete();
		}
	}

	public String getHdfsLocation() {
		return hdfsLocation;
	}

	public void setHdfsLocation(String hdfsLocation) {
		this.hdfsLocation = hdfsLocation;
	}

	public String getkPrincipal() {
		return kPrincipal;
	}

	public void setkPrincipal(String kPrincipal) {
		this.kPrincipal = kPrincipal;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getHdfsFilePath() {
		return hdfsFilePath;
	}

	public void setHdfsFilePath(String hdfsFilePath) {
		this.hdfsFilePath = hdfsFilePath;
	}

	public String getHdfsConfigPath() {
		return hdfsConfigPath;
	}

	public void setHdfsConfigPath(String hdfsConfigPath) {
		this.hdfsConfigPath = hdfsConfigPath;
	}

	public String getTarget() {
		return target;
	}
	
	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}

	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}

	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}

	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}

	public String getS3Region() {
		return s3Region;
	}

	public void setS3Region(String s3Region) {
		this.s3Region = s3Region;
	}
	
	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public boolean isDeltaLoad() {
		return deltaLoad;
	}

	public void setDeltaLoad(boolean deltaLoad) {
		this.deltaLoad = deltaLoad;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public int getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(int ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public boolean isStatusFile() {
		return statusFile;
	}

	public void setStatusFile(boolean statusFile) {
		this.statusFile = statusFile;
	}

	public boolean isColumnsValidation() {
		return columnsValidation;
	}

	public void setColumnsValidation(boolean columnsValidation) {
		this.columnsValidation = columnsValidation;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFileNamePrefix() {
		return fileNamePrefix;
	}

	public void setFileNamePrefix(String fileNamePrefix) {
		this.fileNamePrefix = fileNamePrefix;
	}

	public String getTriggerFilePrefix() {
		return triggerFilePrefix;
	}

	public void setTriggerFilePrefix(String triggerFilePrefix) {
		this.triggerFilePrefix = triggerFilePrefix;
	}
}

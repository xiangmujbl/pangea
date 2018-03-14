package com.jnj.adf.xd.mutifiles.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;


public class FtpUtil {
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
	private String ip;
	private int port;
	private String userName;
	private String password;
	private boolean isConnect = false;
	
	private Session session;
//	
//	public static void main(String[] args) throws Exception {
//		FtpUtil ftpUtil = new FtpUtil("ITSUSABLSP00550",22,"adaas","dev@201701");
//		ftpUtil.uploadFile("C:/out/parquet/test/p01_marc_1.parquet", "/apps/adf/EDLTEST/parquet/test/p01/marc");
//		ftpUtil.close();
//	}
	
	public FtpUtil(String ip,int port,String userName,String password){
		System.out.println("connectting to "+ip+" success");
		
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.password = password;
		
		try {
			connect();
			isConnect = true;
			logger.info("connect to {} success",ip);
			System.out.println("connect to "+ip+" success");
		} catch (Exception e) {
			logger.error("connect failed",e);
		}
	}

	private void connect() throws Exception {
		JSch jsch = new JSch();
		session = jsch.getSession(userName, ip, port);
		session.setPassword(password);
		Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
        session.setConfig(config); 
        session.connect(); 
	}
	
	public void uploadFile(String file,String path) throws Exception{
		uploadFile(new File(file),path);
	}
	
	public void uploadFile(File file,String path) throws Exception{
		logger.info("uploading file {} to {} ",file.getAbsoluteFile(),path);
		
		if(file.exists() && file.isFile()){
			ChannelSftp channel = newChannel();
			String destination = getRealdestinationPath(file.getName(),path);
			mkdirs(channel,path);
			channel.put(new FileInputStream(file), destination);
			channel.disconnect();
			
			logger.info("upload file {} to {} success",file.getAbsoluteFile(),destination);
			System.out.println("upload file {"+file.getAbsoluteFile()+"} to {"+destination+"} success");
		}else{
			throw new RuntimeException(file.getAbsolutePath()+" not exists or is not a file.");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void mkdirs(ChannelSftp channel,String path) throws Exception {
		System.out.println("mkdirs "+path);
		String directory = "";
		if(path.endsWith("/")){
			directory = path.substring(0, path.length()-1);
		}else{
			directory = path;
		}
		if(directory.startsWith("/"))  directory = directory.substring(1);
		String[] directorys = directory.split("/");
		String tmp = "/";String pre = "/";
		for(int i=0;i< directorys.length;i++){
			String dy = directorys[i];
			pre = tmp;
			tmp =  pre+dy+"/";
			
			Vector<LsEntry> list = channel.ls(pre);
			boolean exist = false;
			for(LsEntry lsEntry : list){
				if(lsEntry.getFilename().startsWith("."))
					continue;
				if(lsEntry.getFilename().equals(dy)){
					exist = true;
					break;
				}
			}
			
			if(!exist){
				channel.cd(pre);
				System.out.println("mkdir pre:"+pre+" directory:"+dy);
				channel.mkdir(dy);
			}
		}
	}

	private String getRealdestinationPath(String name, String path) {
		if(path.endsWith("/")){
			return path+name;
		}
		return path+"/"+name;
	}

	private ChannelSftp newChannel() throws JSchException{
		ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
	    channel.connect(); 
        return channel;
	}
	
	public void close(){
		if(session!=null)
			session.disconnect();
	}

	public boolean isConnect() {
		return isConnect;
	}
}

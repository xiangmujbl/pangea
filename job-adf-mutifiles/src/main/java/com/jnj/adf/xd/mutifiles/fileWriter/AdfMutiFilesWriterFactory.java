package com.jnj.adf.xd.mutifiles.fileWriter;

import com.jnj.adf.xd.mutifiles.util.JobStepFileUtil;
import org.springframework.batch.item.ExecutionContext;

import java.util.Map;


public class AdfMutiFilesWriterFactory {
	
	
	public static BasicWriter getWriter(String format, ExecutionContext executionContext, Map<String,Object> params) throws Exception{
		if(JobStepFileUtil.FileFormat.AVRO.equal(format)){
			return new AdfAvroWriter(executionContext,params);
		}
		if(JobStepFileUtil.FileFormat.CSV.equal(format)){
			return new AdfCsvWriter(executionContext,params);
		}
		if(JobStepFileUtil.FileFormat.PARQUET.equal(format)){
			return new AdfParquetWriter(executionContext,params);
		}
		if(JobStepFileUtil.FileFormat.JSON.equal(format)){
			return new AdfJsonWriter(executionContext,params);
		}
		if(JobStepFileUtil.FileFormat.Optymyze.equal(format)){
			return new AdfOptymyzeWriter(executionContext,params);
		}
		throw new Exception("Cant't support this format:"+format);
	}
}

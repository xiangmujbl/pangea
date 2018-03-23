package com.jnj.adf.xd.mutifiles.writer;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.xd.mutifiles.fileWriter.AdfMutiFilesWriterFactory;
import com.jnj.adf.xd.mutifiles.fileWriter.BasicWriter;
import com.jnj.adf.xd.mutifiles.util.JobStepFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.core.io.Resource;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdfMutiFilesJobWriter  extends AbstractItemStreamItemWriter<JsonObject>
implements ResourceAwareItemWriterItemStream<JsonObject>{
	private static final Logger logger = LoggerFactory.getLogger(ResourceAwareItemWriterItemStream.class);

	private String columnNames;
	private String regionName;
	private String parquetFileListKey;
	private String target ;
	private String format;
	private String columnsMapping;
	private String keys;
	
	private Resource resouce;
	private JobStepFileUtil pfUtil = JobStepFileUtil.getInstance();
	private BasicWriter writer;
	private boolean writeTarget = false;
  
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException
	{
		super.open(executionContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("columnNames", columnNames);
		params.put("regionName", regionName);
		params.put("resouce", resouce);
		params.put(JobStepFileUtil.FILE_LIST_KEY, parquetFileListKey);
		params.put("keys", keys);
		
		try {
			writer = AdfMutiFilesWriterFactory.getWriter(format, executionContext, params );
			writer.setColumnsMapping(columnsMapping);
			writer.open();
			if(writeTarget){
				pfUtil.putPath(JobStepFileUtil.SystemType.HDFS,parquetFileListKey, resouce.getFile().getAbsolutePath());
		    }
		    pfUtil.setWriter(parquetFileListKey, writer);
		} catch (Exception e) {
			logger.error("get writer error ",e);
			throw new ItemStreamException(e);
		}
	}
	
	@Override
	public void write(List<? extends JsonObject> items) throws Exception {
		long startTime = System.currentTimeMillis();
		
		writer.write(items);
		
		logger.info("Write {} items complete cost {}ms ",items.size(),System.currentTimeMillis()-startTime);
	}
	
	@Override
	public void close()
	{
		try
	    {
	    	Closeable writer = pfUtil.removeWriter(parquetFileListKey);
			if(writer!=null){
				writer.close();
			}
	    }
	    catch (Exception e)
	    {
	    	logger.error("writerr close error ",e);
	    	throw new ItemStreamException(e);
	    }
	}

	@Override
	public void setResource(Resource resource) {
		this.resouce = resource;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getParquetFileListKey() {
		return parquetFileListKey;
	}

	public void setParquetFileListKey(String parquetFileListKey) {
		this.parquetFileListKey = parquetFileListKey;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
		if(target!=null && JobStepFileUtil.SystemType.contains(target)){
			this.writeTarget=true;
		}
	}

	public Resource getResouce() {
		return resouce;
	}

	public void setResouce(Resource resouce) {
		this.resouce = resouce;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getColumnsMapping() {
		return columnsMapping;
	}

	public void setColumnsMapping(String columnsMapping) {
		this.columnsMapping = columnsMapping;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
}

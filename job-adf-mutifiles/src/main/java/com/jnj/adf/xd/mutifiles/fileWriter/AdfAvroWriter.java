package com.jnj.adf.xd.mutifiles.fileWriter;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.xd.mutifiles.util.CommonUtils;
import com.jnj.adf.xd.mutifiles.util.JobStepFileUtil;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

public class AdfAvroWriter extends BasicWriter {

	private Schema schema;
	DataFileWriter<GenericRecord> dataFileWriter;
	private String columnNames;
	private String regionName;
	private Resource resouce;
	private String parquetFileListKey;
	
	AdfAvroWriter(ExecutionContext executionContext,Map<String,Object> params){
		resouce = (Resource)params.get("resouce");
		columnNames = (String)params.get("columnNames");
		regionName = (String)params.get("regionName");
		parquetFileListKey = (String)params.get(JobStepFileUtil.FILE_LIST_KEY);
	}
	
	@Override
	public void close() throws IOException {
		if(dataFileWriter!=null)
			dataFileWriter.close();
	}
	
	@Override
	public void open() throws Exception {
		schema = createSchema(columnNames,regionName);
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
    	dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
    	dataFileWriter.create(schema, resouce.getFile());
    	
    	LogUtil.getCoreLog().info("avro file "+resouce.getURI().toString()+"is created!");
	}
	
	private Schema createSchema(String columnNames,String regionName) throws IOException {
		JsonObject jo = JsonObject.create();
		List<Map<String,Object>> fields = new ArrayList<Map<String,Object>>();
		String[] types = {"string", "null"};
		
		regionName = CommonUtils.getRegionPath(regionName, false);

		int index = regionName.lastIndexOf("/");
		String namespace = regionName.substring(0,index);
		String name = regionName.substring(index+1);
		
		for(String column : columnNames.split(",")){
			Map<String,Object> field = new HashMap<String,Object>();
			String columnName = getRealColumnName(column);
			field.put("name", columnName);
			field.put("type",Arrays.asList(types));
			fields.add(field);
		}
		
		jo.append("namespace", namespace.replace("/", "."));
		jo.append("name", name.replace("/", "."));
		jo.append("type", "record");
		jo.append("fields",fields);
		
		String schemaStr = jo.toJson();
		
		return new Schema.Parser().parse(schemaStr);
	}

	@Override
	public void write(List<? extends JsonObject> items) throws Exception {
//		LogUtil.getCoreLog().info(Thread.currentThread().getName()+" enter write , size is "+items.size());
		
		for(JsonObject item : items){
			Map<String, Object> map = item.toMap();
			GenericRecord record = new GenericData.Record(schema);
			for(String column : columnNames.split(",")){
				String val = String.valueOf(map.get(column));
		        if(map.get(column) != null) {
		        	String columnName = getRealColumnName(column);
		        	record.put(columnName, val);
		        }else{
		        	if(item.toJson().length()>1000){
						JobStepFileUtil.getInstance().isParquetTrue(parquetFileListKey,"does not contain col:"+column+" value:"+item.toJson().substring(0, 1000));
					}else{
						JobStepFileUtil.getInstance().isParquetTrue(parquetFileListKey,"does not contain col:"+column+" value:"+item.toJson());
					}
		        }
			}
			dataFileWriter.append(record);
		}
//		LogUtil.getCoreLog().info(Thread.currentThread().getName()+" write is done!");
	}
}

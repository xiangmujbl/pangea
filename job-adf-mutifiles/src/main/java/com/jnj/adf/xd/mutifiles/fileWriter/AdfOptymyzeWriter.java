package com.jnj.adf.xd.mutifiles.fileWriter;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.utils.LogUtil;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdfOptymyzeWriter extends BasicWriter {

	private Resource resouce;
	private BufferedWriter writer;
	private String columnNames;
	
	public AdfOptymyzeWriter(ExecutionContext executionContext,Map<String,Object> params){
		resouce = (Resource)params.get("resouce");
		columnNames = (String)params.get("columnNames");
	}
	
	@Override
	public void close() throws IOException {
		if(writer!=null)
			writer.close();
	}

	@Override
	public void open() throws Exception {
		String path = resouce.getFile().getAbsolutePath();
		writer = new BufferedWriter(new FileWriter(path));
		
		LogUtil.getCoreLog().info("JSON file "+resouce.getURI().toString()+"is created!");
	}

	@Override
	public void write(List<? extends JsonObject> items) throws Exception {
		for(JsonObject item : items){
			StringBuffer sb = new StringBuffer();
			Map<String,Object> map = transformColumn(item).toMap();
			
			for(String col : columnNames.split(",")){
				sb.append(map.get(col)==null ? "":String.valueOf(map.get(col))).append("|");
			}
			if(sb.length()>0) {
				writer.write(sb.substring(0, sb.length()-1));
			}else{
				writer.write(sb.toString());
			}
			writer.newLine();
		}
	}
}

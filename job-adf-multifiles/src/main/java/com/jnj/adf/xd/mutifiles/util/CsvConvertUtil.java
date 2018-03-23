package com.jnj.adf.xd.mutifiles.util;

import au.com.bytecode.opencsv.CSVWriter;
import com.jnj.adf.client.api.JsonObject;

import java.io.*;

public class CsvConvertUtil implements Closeable
{
	private String[] head = null ;
	private CSVWriter csvWriter = null;
	
	public CsvConvertUtil(String filePath ) throws IOException{
		File writeFile = new File(filePath);
		Writer writer = new FileWriter(writeFile);
		csvWriter = new CSVWriter(writer, ',', CSVWriter.DEFAULT_QUOTE_CHARACTER);
	}
	
	public CsvConvertUtil(String filePath , String separator, String quotechar ) throws IOException{
		File writeFile = new File(filePath);
		Writer writer = new FileWriter(writeFile);
		csvWriter = new CSVWriter(writer, separator.charAt(0), quotechar.charAt(0));
	}
	
	public void writeHead(String[] line){
		head = line;
		csvWriter.writeNext(line);
	}
	
	public void writeNext(String[] line) throws Exception{
		if(line!=null && head!=null && line.length==head.length){
			for(int i=0;i < line.length;i++){
				if(line[i]==null){
					line[i]="";
				}
			}
			csvWriter.writeNext(line);
		}else{
			throw new Exception("wirte error:columns number not right");
		}
	}
	
	public void writeNext(JsonObject js) throws Exception{
		String[] line = new String[head.length];
		for(int i=0;i< head.length;i++){
			line[i] = js.getValue(head[i])==null? "" :js.getValue(head[i]) ;
		}
		csvWriter.writeNext(line);
	}
	
	@Override
	public void close() throws IOException{
		if(csvWriter!=null){
			csvWriter.close();
		}
	}
}

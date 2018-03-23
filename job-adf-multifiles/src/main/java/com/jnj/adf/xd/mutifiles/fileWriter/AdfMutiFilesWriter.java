package com.jnj.adf.xd.mutifiles.fileWriter;

import com.jnj.adf.client.api.JsonObject;

import java.io.Closeable;
import java.util.List;

public interface AdfMutiFilesWriter extends Closeable{
	
	void open() throws Exception;
	
	void write(List<? extends JsonObject> items) throws Exception;
}

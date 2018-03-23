package com.jnj.adf.xd.mutifiles.remoteService;

import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.jnj.adf.config.annotations.Collector;
import com.jnj.adf.config.annotations.Path;
import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import com.jnj.adf.config.annotations.RemoteServiceApi;

import java.util.List;

@RemoteServiceApi("adf.data.ingestion.region.out.rs")
public interface DataServiceApi {
	
	@SuppressWarnings("rawtypes")
	@RemoteMethod(type = InvokeTypes.ON_REGION)
	public void listData(@Path String regionName, String queryString, String[] columns, int batchSize, @Collector ResultCollector collector);
	
	@RemoteMethod(type = InvokeTypes.ON_SERVER)
	public Long getRegionSize(@Path String regionName);
	
	@RemoteMethod(type = InvokeTypes.ON_SERVER)
	public List<String> getRegionKeys(@Path String regionName);
	
	@RemoteMethod(type = InvokeTypes.ON_SERVER)
	public List<String> getRegionColumns(@Path String path);
}

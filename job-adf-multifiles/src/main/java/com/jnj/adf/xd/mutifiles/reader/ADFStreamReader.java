package com.jnj.adf.xd.mutifiles.reader;

import com.jnj.adf.client.api.ADFService;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.config.annotations.EnableADF;
import com.jnj.adf.grid.auth.AuthService;
import com.jnj.adf.grid.auth.session.AuthInternalSession;
import com.jnj.adf.grid.common.ADFException;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.support.system.ADFConfigHelper.ITEMS;
import com.jnj.adf.xd.mutifiles.domain.DataBlockQueueCollector;
import com.jnj.adf.xd.mutifiles.remoteService.DataServiceApi;
import com.jnj.adf.xd.mutifiles.remoteService.IBizExt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import com.jnj.adf.grid.connect.ADFClient;

@EnableADF
public class ADFStreamReader implements ItemStreamReader<Object>
{
	private static final Logger logger = LoggerFactory.getLogger(ADFStreamReader.class);

	private int queueSize ;
	private DataBlockQueueCollector collector;
	//gridName
	private String grid;
	private long maxReadWaitTime = 5000L;
	private BlockingQueue<Object> queue;
	private ExecutorService readThread = Executors.newSingleThreadExecutor(); 
	private String regionName;
	private int batchSize;
	private String namingServer;
	private String queryString;
	private String locators;
	private String username;
	private String password;
	private String columnNames;
	private String parquetFileListKey;
	
	@Autowired
	private AuthService authService;
	@Autowired
	ADFService adfService;
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException
	{
		logger.info(" ADFStreamReader open");
		
		collector = new DataBlockQueueCollector(queueSize,parquetFileListKey);
		queue = collector.getResult();
		
//		connectGrid();
		
	  	readThread.execute(new Runnable() {
	  		@Override
	  		public void run() {
	  			try {
	  				logger.info(" ADFStreamReader run dataService ");
	  				connectGrid();
	  				
		  			if(!StringUtils.isNotBlank(queryString)) queryString  = "*:*";
		  			DataServiceApi srv= IBizExt.getRemoteService(DataServiceApi.class);
		  			srv.listData(regionName ,queryString,columnNames.split(","),batchSize,collector);
				} catch (Exception e) {
					logger.error("run remoteService error ",e);
					collector.endResults();
					collector.setJobRuning(false);
				}
	  		}
	  	});
	}
	
	private void connectGrid() {
		logger.info("Connect grid locators:"+locators+",namingServer:"+namingServer);
		// set ADF configuration
		ADFConfigHelper.setProperty(ITEMS.NAMING_SERVER, namingServer);
		login();
		logger.info("Connect grid success!"+locators+",namingServer:"+namingServer);
	}
	

	private AuthInternalSession login() {
		AuthInternalSession session = authService.login(username, password);
		return session;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException
	{
	}

	@Override
	public void close() throws ItemStreamException
	{
		logger.warn("Reader close.");
		collector.setJobRuning(false);
		readThread.shutdownNow();
	}

	@Override
	public Object read()
	{
		JsonObject js = null;
		try {
			Object obj = queue.poll(maxReadWaitTime, TimeUnit.MILLISECONDS);
			while(obj==null && (!collector.isEnd() || !queue.isEmpty())){
				obj = queue.poll(maxReadWaitTime, TimeUnit.MILLISECONDS);
			}
			if(obj!=null){
				js = JsonObject.append(String.valueOf(obj));
			}else{
				logger.info(Thread.currentThread().getName()+" is done!");
			}
		} catch (Exception e) {
			logger.error("read error",e);
			throw new ADFException(e);
		}
		return js;
	}

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public long getMaxReadWaitTime() {
		return maxReadWaitTime;
	}

	public void setMaxReadWaitTime(long maxReadWaitTime) {
		this.maxReadWaitTime = maxReadWaitTime;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public String getNamingServer() {
		return namingServer;
	}

	public void setNamingServer(String namingServer) {
		this.namingServer = namingServer;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getLocators() {
		return locators;
	}

	public void setLocators(String locators) {
		this.locators = locators;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getParquetFileListKey() {
		return parquetFileListKey;
	}

	public void setParquetFileListKey(String parquetFileListKey) {
		this.parquetFileListKey = parquetFileListKey;
	}
}

package com.jnj.adf.xd.mutifiles.domain;

import com.gemstone.gemfire.DataSerializer;
import com.gemstone.gemfire.cache.execute.FunctionException;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.gemstone.gemfire.distributed.DistributedMember;
import com.gemstone.gemfire.internal.ByteArrayDataInput;
import com.gemstone.gemfire.internal.Version;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.common.ADFException;
import com.jnj.adf.xd.mutifiles.remoteService.SimpleKryoSerializer;
import com.jnj.adf.xd.mutifiles.util.JobStepFileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class DataBlockQueueCollector implements ResultCollector<Object, BlockingQueue<Object>>, StreamConstant {
	private static final Logger logger = LoggerFactory.getLogger(DataBlockQueueCollector.class);

	private BlockingQueue<Object> results;

	private AtomicBoolean isEnd = new AtomicBoolean(false);

	private String[] columns;
	private String primaryKey;

	private AtomicLong size = new AtomicLong(0L);

	private AtomicInteger recieveDataCount = new AtomicInteger(0);

	private boolean jobRuning = true;

	public DataBlockQueueCollector(int queueSize) {
		results = new ArrayBlockingQueue<Object>(queueSize);
	}

	public DataBlockQueueCollector(int queueSize, String primaryKey) {
		results = new ArrayBlockingQueue<Object>(queueSize);
		this.primaryKey = primaryKey;
	}

	@Override
	public BlockingQueue<Object> getResult() throws FunctionException {
		return results;
	}

	@Override
	public BlockingQueue<Object> getResult(long paramLong, TimeUnit paramTimeUnit)
			throws FunctionException, InterruptedException {
		return results;
	}

	@SuppressWarnings("resource")
	@Override
	public void addResult(DistributedMember paramDistributedMember, Object result) {
		if (result instanceof Throwable || !jobRuning) {
			endResults();
			if (!jobRuning) {
				throw new ADFException("Collector run error since job is colsed!");
			} else {
				throw new ADFException((Throwable) result);
			}
		} else {
			try {
				if (result instanceof byte[]) {
					byte[] chunk = (byte[]) result;
					if (chunk != null && chunk.length > 1) {
						ByteArrayDataInput input = new ByteArrayDataInput();
						input.initialize(chunk, Version.CURRENT);
						byte chunkType = input.readByte();
						switch (chunkType) {
						case COLUMN_CHUNK:
							putColumns(input,paramDistributedMember);
							return;
						case DATA_CHUNK:
							putData(input,paramDistributedMember);
							return;
						case SIZE_CHUNK:
							int pz = input.position();
							try {
								long sendSize = DataSerializer.readLong(input);
								size.addAndGet(sendSize);
								logger.warn(paramDistributedMember.getName() + " send " + sendSize + " data!");
							} catch (Exception e) {
								logger.error("Get server send size error. Try to use readInteger ." + e);
								input.setPosition(pz);
								int sendSize = DataSerializer.readInteger(input);
								size.addAndGet(sendSize);
								logger.warn(paramDistributedMember.getName() + " send " + sendSize + " data!");
							}
							return;
						case ERROR_CHUNK:
							Exception e =(Exception) DataSerializer.readObject(input);
							throw new ADFException(e);
						default:
							throw new ADFException("unknown chunk type: chunkType");
						}
					}
				}
			} catch (Exception e) {
				endResults();
				logger.error("receive data error", e);
				throw new ADFException(e);
			}
		}
	}

	private synchronized void putColumns(ByteArrayDataInput input,DistributedMember paramDistributedMember) throws IOException{
		if(columns==null){
			columns = DataSerializer.readStringArray(input);
			if(columns==null){
				throw new ADFException("get null columns from "+paramDistributedMember.getName());
			}else{
				logger.warn(" columns is set for " + paramDistributedMember.getName());
			}
		}
	}

	private void putData(ByteArrayDataInput input,DistributedMember paramDistributedMember) throws Exception {
		while (input.available() > 0) {
			byte b = input.readByte();
			switch (b) {
			case BYTEARR_ARR_DATA:
				byte[] valueBytes = DataSerializer.readByteArray(input);
				Object[] data = SimpleKryoSerializer.toObject(valueBytes, Object[].class);
				if(data==null){
					logger.error("get null data from "+paramDistributedMember.getName());
					return;
				}
				if (columns != null && data != null && columns.length == data.length) {
					JsonObject js = JsonObject.create();
					for (int i = 0; i < columns.length; i++) {
						js.append(columns[i], data[i]);
					}

					int i = 0;
					boolean bool = results.offer(js.toJson());
					while (!bool) {
						i++;
						if (i >= 6 || !jobRuning) {
							throw new ADFException("Collector run error since job is colsed!,i:"+i);
						}
						bool = results.offer(js.toJson(), 10 * 1000, TimeUnit.MILLISECONDS);
					}

					recieveDataCount.incrementAndGet();
				} else {
					throw new Exception("columns leng not equal data length");
				}
				break;
			default:
			}
		}
	}

	@Override
	public void endResults() {
		System.out.println(" server send: " + size.get());
		if (StringUtils.isNotBlank(this.primaryKey)) {
			JobStepFileUtil.getInstance().putServerSendSize(primaryKey,
					size == null ? null : Integer.valueOf(String.valueOf(size.get())));
		}
		isEnd.set(true);
	}

	@Override
	public void clearResults() {
		results.clear();
	}

	public boolean isJobRuning() {
		return jobRuning;
	}

	public void setJobRuning(boolean jobRuning) {
		this.jobRuning = jobRuning;
	}

	public boolean isEnd() {
		return isEnd.get();
	}
}

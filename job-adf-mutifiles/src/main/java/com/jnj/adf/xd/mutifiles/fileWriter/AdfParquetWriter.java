package com.jnj.adf.xd.mutifiles.fileWriter;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.xd.mutifiles.util.JobStepFileUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter.Mode;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.Type.Repetition;
import org.apache.parquet.schema.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdfParquetWriter extends BasicWriter {
	private static final Logger logger = LoggerFactory.getLogger(AdfParquetWriter.class);

	private String columnNames;
	private String regionName;
	private String keys;
	private Resource resouce;
	MessageType schema;
	public static final Configuration configuration = new Configuration(false);
	Path outFile;
	public static final int ONE_K = 1000;
	public static final int FIVE_K = 5 * ONE_K;
	public static final int TEN_K = 2 * FIVE_K;
	public static final int HUNDRED_K = 10 * TEN_K;
	public static final int ONE_MILLION = 10 * HUNDRED_K;

	public static final int FIXED_LEN_BYTEARRAY_SIZE = 1024;

	public static final int BLOCK_SIZE_DEFAULT = ParquetWriter.DEFAULT_BLOCK_SIZE;
	public static final int BLOCK_SIZE_256M = 256 * 1024 * 1024;
	public static final int BLOCK_SIZE_512M = 512 * 1024 * 1024;

	public static final int PAGE_SIZE_DEFAULT = ParquetWriter.DEFAULT_PAGE_SIZE;
	public static final int PAGE_SIZE_4M = 4 * 1024 * 1024;
	public static final int PAGE_SIZE_8M = 8 * 1024 * 1024;

	public static final int DICT_PAGE_SIZE = 512;

	private ParquetWriter<Group> writer;
	private SimpleGroupFactory factory;
	private String parquetFileListKey;

	AdfParquetWriter(ExecutionContext executionContext, Map<String, Object> params) {
		resouce = (Resource) params.get("resouce");
		columnNames = (String) params.get("columnNames");
		regionName = (String) params.get("regionName");
		parquetFileListKey = (String)params.get(JobStepFileUtil.FILE_LIST_KEY);
		keys = (String)params.get("keys");
	}

	@Override
	public void close() throws IOException {
		if (writer != null)
			writer.close();
	}

	@Override
	public void open() throws Exception {
		Types.MessageTypeBuilder typeBuilder = Types.buildMessage();
		String[] columns = columnNames.split(",");
		for (String col : columns) {
			String columnName = getRealColumnName(col);
			Type t0 = new PrimitiveType(Repetition.OPTIONAL, PrimitiveTypeName.BINARY, columnName);
			typeBuilder.addField(t0);
		}
		schema = typeBuilder.named(regionName);

		try {
			outFile = new Path(resouce.getURI().toString());
			logger.info("parquet file " + resouce.getURI().toString() + "is created!");
		} catch (Exception e1) {
			logger.error("create parquet error ",e1);
			throw new ItemStreamException(e1);
		}
		ExampleParquetWriter.Builder builder = ExampleParquetWriter.builder(outFile);
		builder.withCompressionCodec(CompressionCodecName.GZIP);
		builder.withDictionaryPageSize(DICT_PAGE_SIZE);
		builder.withConf(configuration);
		builder.withPageSize(PAGE_SIZE_DEFAULT);
		builder.withRowGroupSize(BLOCK_SIZE_DEFAULT);
		builder.withWriterVersion(WriterVersion.PARQUET_2_0);
		builder.withType(schema);
		builder.withValidation(false);
		builder.withDictionaryEncoding(true);
		builder.withWriteMode(Mode.OVERWRITE);
		factory = new SimpleGroupFactory(schema);
		writer = builder.build();
	}

	@Override
	public void write(List<? extends JsonObject> items) throws Exception {
		for (JsonObject item : items) {
			Map<String, Object> map = item.toMap();
			Group row = factory.newGroup();
			String[] columns = columnNames.split(",");
			for (String col : columns) {
				String val = String.valueOf(map.get(col));
				if (map.get(col) == null) {
//					logger.error("received data columns do not contain "+col);
//					if(item.toJson().length()>1000){
						JobStepFileUtil.getInstance().isParquetTrue(parquetFileListKey,"does not contain col:"+col+" value:"+getErrorKeyString(map,keys,col));
//					}else{
//						JobStepFileUtil.getInstance().isParquetTrue(parquetFileListKey,"does not contain col:"+col+" value:"+item.toJson());
//					}
				}
				String columnName = getRealColumnName(col);
				row.add(columnName, val);
			}
			writer.write(row);
		}
	}
	
	private String getErrorKeyString(Map<String, Object> map,String keys,String errorKey){
		StringBuffer sb =new StringBuffer();
		for(String key : keys.split(",",-1)){
			sb.append("\"").append(key).append("\":\"").append(String.valueOf(map.get(key))).append("\",\"");
		}
		sb.append(errorKey).append("\":\"").append(String.valueOf(map.get(errorKey))).append("\"");
		return sb.toString();
	}

}

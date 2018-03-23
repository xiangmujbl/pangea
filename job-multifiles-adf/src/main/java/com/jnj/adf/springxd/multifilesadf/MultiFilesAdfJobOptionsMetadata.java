package com.jnj.adf.springxd.multifilesadf;

import org.springframework.xd.module.options.spi.Mixin;
import org.springframework.xd.module.options.spi.ModuleOption;

@Mixin({ FileMixin.class, AdfConnectionMixin.class })
public class MultiFilesAdfJobOptionsMetadata {
	private int throttleLimit = 8;
	private int commitInterval = 1000;
	private int checkDataSize = 1;
	private String reportLocation = "";
	private boolean deltaData = false;
	private boolean reportFlg = false;
	private boolean trimKey = true;
	private boolean keyAddUUID = false;
	private boolean cleanData = false;
	private int randomSize = 20;
	private int corePoolSize = 16;
	private int maxPoolSize = 16;
	private String titlePageContent = "Comparison Report";
	private boolean writeGrid = true;
	private int itemCountLimitPerResource = 10000000;
	private String resourcePath = "";
	private boolean writeParquet = true;
	private boolean writeS3 = true;
    private String awsAccessKeyId;
    private String awsSecretAccessKey;
    private String s3Region;
    private String envName;
    private boolean deltaLoad = false;
	private boolean fullLoad = true;
	private boolean clientPut = false;
	private boolean notParseColumn = false;

	public int getThrottleLimit() {
		return throttleLimit;
	}

	@ModuleOption("The throttle limit")
	public void setThrottleLimit(int throttleLimit) {
		this.throttleLimit = throttleLimit;
	}

	public int getCommitInterval() {
		return commitInterval;
	}

	@ModuleOption("The commit interval")
	public void setCommitInterval(int commitInterval) {
		this.commitInterval = commitInterval;
	}

	public int getCheckDataSize() {
		return checkDataSize;
	}

	@ModuleOption("Set the check data's size every commit")
	public void setCheckDataSize(int checkDataSize) {
		this.checkDataSize = checkDataSize;
	}

	public String getReportLocation() {
		return reportLocation;
	}

	@ModuleOption("Set the location for report file")
	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}

	public boolean isDeltaData() {
		return deltaData;
	}

	@ModuleOption("Set the read data delta flag, default false")
	public void setDeltaData(boolean deltaData) {
		this.deltaData = deltaData;
	}

	public boolean isReportFlg() {
		return reportFlg;
	}

	@ModuleOption("Set the report export flag, default false")
	public void setReportFlg(boolean reportFlg) {
		this.reportFlg = reportFlg;
	}

	public boolean isTrimKey() {
		return trimKey;
	}

	@ModuleOption("Trim key value? default true")
	public void setTrimKey(boolean trimKey) {
		this.trimKey = trimKey;
	}

	public boolean isKeyAddUUID() {
		return keyAddUUID;
	}

	@ModuleOption("Add UUID to key? default false")
	public void setKeyAddUUID(boolean keyAddUUID) {
		this.keyAddUUID = keyAddUUID;
	}

	public boolean isCleanData() {
		return cleanData;
	}

	@ModuleOption("Clean data before import? default false")
	public void setCleanData(boolean cleanData) {
		this.cleanData = cleanData;
	}

	public int getRandomSize() {
		return randomSize;
	}

	@ModuleOption("Set the max random size")
	public void setRandomSize(int randomSize) {
		this.randomSize = randomSize;
	}

	public String getTitlePageContent() {
		return titlePageContent;
	}

	@ModuleOption("Set the report title page content")
	public void setTitlePageContent(String titlePageContent) {
		this.titlePageContent = titlePageContent;
	}
	
	public int getCorePoolSize() {
		return corePoolSize;
	}

	@ModuleOption("Set the ThreadPoolExecutor's core pool size")
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	@ModuleOption("Set the ThreadPoolExecutor's maximum pool size")
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public boolean isWriteGrid() {
		return writeGrid;
	}
	
	@ModuleOption("Set Write Grid")
	public void setWriteGrid(boolean writeGrid) {
		this.writeGrid = writeGrid;
	}

	public int getItemCountLimitPerResource() {
		return itemCountLimitPerResource;
	}
	
	@ModuleOption("Set the Item Count Limit")
	public void setItemCountLimitPerResource(int itemCountLimitPerResource) {
		this.itemCountLimitPerResource = itemCountLimitPerResource;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	@ModuleOption("Set the Resource Path")
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	public boolean isWriteParquet() {
		return writeParquet;
	}
	
	@ModuleOption("Set write Parquet")
	public void setWriteParquet(boolean writeParquet) {
		this.writeParquet = writeParquet;
	}

	public boolean isWriteS3() {
		return writeS3;
	}

	@ModuleOption("write to S3? true or false")
	public void setWriteS3(boolean writeS3) {
		this.writeS3 = writeS3;
	}

	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}

	@ModuleOption("aws Access Key Id")
	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}

	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}

	@ModuleOption("aws Secret Access Key")
	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}

	public String getS3Region() {
		return s3Region;
	}

	@ModuleOption("s3 Region name")
	public void setS3Region(String s3Region) {
		this.s3Region = s3Region;
	}
	public String getEnvName() {
		return envName;
	}

	@ModuleOption("env Name")
	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public boolean isDeltaLoad() {
		return deltaLoad;
	}

	@ModuleOption("create delta folder for S3?")
	public void setDeltaLoad(boolean deltaLoad) {
		this.deltaLoad = deltaLoad;
	}
	
	public boolean isFullLoad() {
		return fullLoad;
	}
	
	@ModuleOption("full Load?")
	public void setFullLoad(boolean fullLoad) {
		this.fullLoad = fullLoad;
	}
	
	public boolean isClientPut() {
		return clientPut;
	}

	@ModuleOption("client side put?")
	public void setClientPut(boolean clientPut) {
		this.clientPut = clientPut;
	}

	public boolean isNotParseColumn() {
		return notParseColumn;
	}

	@ModuleOption("parse Columns name to camel?")
	public void setNotParseColumn(boolean notParseColumn) {
		this.notParseColumn = notParseColumn;
	}
	
}

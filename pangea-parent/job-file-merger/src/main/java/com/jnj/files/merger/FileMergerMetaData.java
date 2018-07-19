package com.jnj.files.merger;

import org.springframework.xd.module.options.spi.ModuleOption;

public class FileMergerMetaData {

    private String sLocation;
    private String sHost;
    private String sUsername;
    private String sPassword;
    private String sPattern;
    private String sSaveLocation;

    private String mLocation;
    private String outputFileName;
    private boolean toCompress;
    private String zipFileName;
    private String outputDirectory;

    private String tSourceLocation = null;
    private String tLocation = null;
    private String tHost;
    private String tUsername;
    private String tPassword;
    private String tFileName;
    private String keyString;
    private boolean duplicationFlag = false;
    private boolean onlyDownLoadFlag = false;
    private boolean sendToMBox = false;

    private String namingServer;

    @ModuleOption("Naming Server")
    public void setNamingServer(String namingServer) {
        this.namingServer = namingServer;
    }

    @ModuleOption("Source file location")
    public void setsLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    @ModuleOption("Source host")
    public void setsHost(String sHost) {
        this.sHost = sHost;
    }

    @ModuleOption("Source username")
    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    @ModuleOption("Source password")
    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @ModuleOption("Source file search pattern")
    public void setsPattern(String sPattern) {
        this.sPattern = sPattern;
    }

    @ModuleOption("Source file save location")
    public void setsSaveLocation(String sSaveLocation) {
        this.sSaveLocation = sSaveLocation;
    }

    @ModuleOption("Mearge file location")
    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    @ModuleOption("Output file name for merge")
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    @ModuleOption("Zipping flag")
    public void setToCompress(boolean toCompress) {
        this.toCompress = toCompress;
    }

    @ModuleOption("Zip file name")
    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @ModuleOption("Output directory")
    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @ModuleOption("Target file pickup location")
    public void settSourceLocation(String tSourceLocation) {
        this.tSourceLocation = tSourceLocation;
    }

    @ModuleOption("Target file drop location")
    public void settLocation(String tLocation) {
        this.tLocation = tLocation;
    }

    @ModuleOption("Target host")
    public void settHost(String tHost) {
        this.tHost = tHost;
    }

    @ModuleOption("Target username")
    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    @ModuleOption("Target password")
    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    @ModuleOption("Target file naming")
    public void settFileName(String tFileName) {
        this.tFileName = tFileName;
    }

    @ModuleOption("merge key")
    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }


    public boolean isDuplicationFlag() {
        return duplicationFlag;
    }

    @ModuleOption("delete duplication data for merge file")
    public void setDuplicationFlag(boolean duplicationFlag) {
        this.duplicationFlag = duplicationFlag;
    }

    @ModuleOption("Only downLoad file Not merge file flag")
    public void setOnlyDownLoadFlag(boolean onlyDownLoadFlag) {
        this.onlyDownLoadFlag = onlyDownLoadFlag;
    }

    @ModuleOption("is send the target file to mbox")
    public void setSendToMBox(boolean sendToMBox) {
        this.sendToMBox = sendToMBox;
    }

    public boolean isSendToMBox() {
        return sendToMBox;
    }

    public String getKeyString() {
        return keyString;
    }

    public String getsLocation() {
        return sLocation;
    }

    public String getsHost() {
        return sHost;
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsPassword() {
        return sPassword;
    }

    public String getsPattern() {
        return sPattern;
    }

    public String getsSaveLocation() {
        return sSaveLocation;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean isToCompress() {
        return toCompress;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String gettSourceLocation() {
        return tSourceLocation;
    }

    public String gettLocation() {
        return tLocation;
    }

    public String gettHost() {
        return tHost;
    }

    public String gettUsername() {
        return tUsername;
    }

    public String gettPassword() {
        return tPassword;
    }

    public String gettFileName() {
        return tFileName;
    }

    public String getNamingServer() {
        return namingServer;
    }

    public boolean isOnlyDownLoadFlag() {
        return onlyDownLoadFlag;
    }

}

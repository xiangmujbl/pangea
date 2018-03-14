package com.jnj.adf.springxd.multifilesadf;

import org.springframework.xd.module.options.spi.ModuleOption;

public class AdfConnectionMixin {
	private String namingServer;
	private String locators;
	private String grid;
	private String path;
	private String keys;
	private String skipColumns;
	private boolean temporal = false;
	private boolean cdcMode = false;
	private String grid_username;
	private String grid_password;

	public String getNamingServer() {
		return namingServer;
	}

	@ModuleOption("ADF naming server")
	public void setNamingServer(String namingServer) {
		this.namingServer = namingServer;
	}

	public String getGrid() {
		return grid;
	}

	@ModuleOption("The grid name")
	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getPath() {
		return path;
	}

	@ModuleOption("The path name")
	public void setPath(String path) {
		this.path = path;
	}

	public String getKeys() {
		return keys;
	}

	@ModuleOption("The key field names. Like 'key1,key2,key3,...' split by comma")
	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getSkipColumns() {
		return skipColumns;
	}

	@ModuleOption("Skip columns names, which columns does not need to import to the grid. Like 'c1,c2,c3,...' split by comma")
	public void setSkipColumns(String filterColumns) {
		this.skipColumns = filterColumns;
	}

	public String getLocators() {
		return locators;
	}

	@ModuleOption("ADF locators")
	public void setLocators(String locators) {
		this.locators = locators;
	}

	public boolean isTemporal() {
		return temporal;
	}

	@ModuleOption("Is temporal enable")
	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	public boolean isCdcMode() {
		return cdcMode;
	}

	@ModuleOption("Is cdc mode? if true will process items by calling cdc remote service, default: false")
	public void setCdcMode(boolean cdcMode) {
		this.cdcMode = cdcMode;
	}

	public String getGrid_username() {
		return grid_username;
	}

	@ModuleOption("The grid username, using application service account")
	public void setGrid_username(String grid_username) {
		this.grid_username = grid_username;
	}

	public String getGrid_password() {
		return grid_password;
	}

	@ModuleOption("The grid password, encrypted with prefix {cipher}, for example: {cipher}FKSAJDFGYOS8F7GLHAKERGFHLSAJ")
	public void setGrid_password(String grid_password) {
		this.grid_password = grid_password;
	}
}

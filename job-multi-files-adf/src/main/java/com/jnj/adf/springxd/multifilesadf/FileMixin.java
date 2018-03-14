package com.jnj.adf.springxd.multifilesadf;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.xd.module.options.spi.ModuleOption;

public class FileMixin {
	private String columns;
	private String[] names;
	private String delimiter = "\t";
	private char delimiterChar = ',';
	private String quote;
	private int linesToSkip = 1;
	private int maxItemCount = Integer.MAX_VALUE;
	private String encoding = "ISO-8859-1";
	private String filePathPattern;
	private String archiveFolder;

	public String getColumns() {
		return columns;
	}

	@ModuleOption("The field names in the CSV file. Like 'column1,column2,column3,...' split by comma")
	public void setColumns(String columns) {
		this.columns = columns;
		if (StringUtils.isNotEmpty(columns))
			this.names = columns.split(",");
	}


	public String[] getNames() {
		return names;
	}
	
	public String getDelimiter() {
		return delimiter;
	}

	@ModuleOption("The delimiter for the delimited file, default: TAB")
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
		if (StringUtils.isNotEmpty(delimiter))
			this.delimiterChar = CharUtils.toChar(delimiter);
	}

	public char getDelimiterChar() {
		return delimiterChar;
	}
	
	@ModuleOption("The quote character")
	public void setQuote(String quote) {
		this.quote = quote;
	}

	public int getLinesToSkip() {
		return linesToSkip;
	}

	@ModuleOption("The number of skip lines")
	public void setLinesToSkip(int linesToSkip) {
		this.linesToSkip = linesToSkip;
	}

	public int getMaxItemCount() {
		return maxItemCount;
	}

	@ModuleOption("Maximum number of lines")
	public void setMaxItemCount(int maxItemCount) {
		this.maxItemCount = maxItemCount;
	}

	public String getQuote() {
		return quote;
	}

	public String getEncoding() {
		return encoding;
	}

	@ModuleOption("Set the Encoding, default: ISO-8859-1")
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getFilePathPattern() {
		return filePathPattern;
	}

	@ModuleOption("The file pattern describes which files will write to ADF")
	public void setFilePathPattern(String filePathPattern) {
		this.filePathPattern = filePathPattern;
	}

	/**
	 * @return the archiveFolder
	 */
	public String getArchiveFolder() {
		return archiveFolder;
	}

	/**
	 * @param archiveFolder
	 *            the archiveFolder to set
	 */
	@ModuleOption("The archive folder path, if not set will not archive the files")
	public void setArchiveFolder(String archiveFolder) {
		this.archiveFolder = archiveFolder;
	}
}

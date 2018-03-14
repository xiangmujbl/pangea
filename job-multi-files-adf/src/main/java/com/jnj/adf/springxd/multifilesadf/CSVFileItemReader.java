/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jnj.adf.springxd.multifilesadf;


import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.utils.ImportUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ReaderNotOpenException;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Restartable {@link ItemReader} that reads lines from input {@link #setResource(Resource)}. Line is defined by the
 * {@link #setRecordSeparatorPolicy(RecordSeparatorPolicy)} and mapped to item using {@link #setLineMapper(LineMapper)}.
 * If an exception is thrown during line mapping it is rethrown as {@link FlatFileParseException} adding information
 * about the problematic line and its line number.
 * 
 * @author Robert Kasanicky
 */
public class CSVFileItemReader extends AbstractItemCountingItemStreamItemReader<JsonObject> implements
		ResourceAwareItemReaderItemStream<JsonObject>, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(CSVFileItemReader.class);

	// default encoding for input files
	public static final String DEFAULT_CHARSET = Charset.defaultCharset().name();

	private RecordSeparatorPolicy recordSeparatorPolicy = new SimpleRecordSeparatorPolicy();

	private Resource resource;

	private BufferedReader reader;

	private int lineCount = 0;

	private boolean noInput = false;

	private String encoding = DEFAULT_CHARSET;

	private int linesToSkip = 0;
	
	private Iterator<CSVRecord> records;

	private boolean strict = true;

	private BufferedReaderFactory bufferedReaderFactory = new DefaultBufferedReaderFactory();
	
	private String skipColumns;
	
	private String columns;
	
	private Set<String> skipSet = new HashSet<String>();
	
	private List<String> columnSet = new ArrayList<String>();

    private String quote = "\"";
	
	private char delimiterChar = ',';

	private boolean notParseColumn;

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public char getDelimiterChar() {
		return delimiterChar;
	}

	public void setDelimiterChar(char delimiterChar) {
		this.delimiterChar = delimiterChar;
	}

	public boolean isNotParseColumn() {
		return notParseColumn;
	}

	public void setNotParseColumn(boolean notParseColumn) {
		this.notParseColumn = notParseColumn;
	}
	
	public CSVFileItemReader() {
		setName(ClassUtils.getShortName(CSVFileItemReader.class));
	}

	/**
	 * In strict mode the reader will throw an exception on
	 * {@link #open(org.springframework.batch.item.ExecutionContext)} if the input resource does not exist.
	 * @param strict <code>true</code> by default
	 */
	public void setStrict(boolean strict) {
		this.strict = strict;
	}

	/**
	 * Public setter for the number of lines to skip at the start of a file. Can be used if the file contains a header
	 * without useful (column name) information, and without a comment delimiter at the beginning of the lines.
	 * 
	 * @param linesToSkip the number of lines to skip
	 */
	public void setLinesToSkip(int linesToSkip) {
		this.linesToSkip = linesToSkip;
	}

	/**
	 * Setter for the encoding for this input source. Default value is {@link #DEFAULT_CHARSET}.
	 * 
	 * @param encoding a properties object which possibly contains the encoding for this input file;
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Factory for the {@link BufferedReader} that will be used to extract lines from the file. The default is fine for
	 * plain text files, but this is a useful strategy for binary files where the standard BufferedReaader from java.io
	 * is limiting.
	 * 
	 * @param bufferedReaderFactory the bufferedReaderFactory to set
	 */
	public void setBufferedReaderFactory(BufferedReaderFactory bufferedReaderFactory) {
		this.bufferedReaderFactory = bufferedReaderFactory;
	}

	/**
	 * Public setter for the input resource.
	 */
    @Override
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * Public setter for the recordSeparatorPolicy. Used to determine where the line endings are and do things like
	 * continue over a line ending if inside a quoted string.
	 * 
	 * @param recordSeparatorPolicy the recordSeparatorPolicy to set
	 */
	public void setRecordSeparatorPolicy(RecordSeparatorPolicy recordSeparatorPolicy) {
		this.recordSeparatorPolicy = recordSeparatorPolicy;
	}

	/**
	 * @return string corresponding to logical record according to
	 * {@link #setRecordSeparatorPolicy(RecordSeparatorPolicy)} (might span multiple lines in file).
	 */
	@Override
	protected JsonObject doRead() throws Exception {
		if (noInput) {
			return null;
		}

		CSVRecord line = readLine();

		if (line == null) {
			return null;
		}
		else {
			try {
				return mapFieldSet(line);
			}
			catch (Exception ex) {
				throw new FlatFileParseException("Parsing error at line: " + lineCount + " in resource=["
						+ resource.getDescription() + "], input=[" + line + "]", ex, line.toString(), lineCount);
			}
		}
	}

	public JsonObject mapFieldSet(CSVRecord line) throws BindException {
		JsonObject jsonObj = JsonObject.create();
		if(columnSet.size() != line.size()){
			throw new FlatFileParseException("columns number is not match!: " + lineCount + " in resource=["
					+ resource.getDescription() + "], input=[" + line + "]", null, line.toString(), lineCount);
		}
		Iterator<String> fieldSets = line.iterator();
		for (String name : columnSet) {
			if (!skipSet.contains(name))
				jsonObj.append(name, fieldSets.next());
			else
				fieldSets.next();
		}
		return jsonObj;
	}
	
	/**
	 * @return next line (skip comments).getCurrentResource
	 */
	private CSVRecord readLine() {

		if (reader == null) {
			throw new ReaderNotOpenException("Reader must be open before it can be read.");
		}

		CSVRecord line = null;

		if(records.hasNext()){
			line = records.next();
		}
		if (line == null) {
			return null;
		}
		lineCount++;
		return line;
	}


	@Override
	protected void doClose() throws Exception {
		lineCount = 0;
		if (reader != null) {
			reader.close();
		}
	}

	@Override
	protected void doOpen() throws Exception {
		Assert.notNull(resource, "Input resource must be set");
		Assert.notNull(recordSeparatorPolicy, "RecordSeparatorPolicy must be set");

		noInput = true;
		if (!resource.exists()) {
			if (strict) {
				throw new IllegalStateException("Input resource must exist (reader is in 'strict' mode): " + resource);
			}
			logger.warn("Input resource does not exist " + resource.getDescription());
			return;
		}

		if (!resource.isReadable()) {
			if (strict) {
				throw new IllegalStateException("Input resource must be readable (reader is in 'strict' mode): "
						+ resource);
			}
			logger.warn("Input resource is not readable " + resource.getDescription());
			return;
		}

		reader = bufferedReaderFactory.create(resource, encoding);
		records = CSVFormat.DEFAULT.withDelimiter(delimiterChar).withQuote(CharUtils.toChar(quote)).parse(reader).iterator();

//		records = CSVFormat.DEFAULT.parse(reader).iterator();
		for (int i = 0; i < linesToSkip; i++) {
			if(records.hasNext()){
				records.next();
			}
		}
		noInput = false;
	}


	public String getSkipColumns() {
		return skipColumns;
	}

	public void setSkipColumns(String skipColumns) {
		this.skipColumns = skipColumns;
	}
	
	
    public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
    	if (StringUtils.isNotBlank(skipColumns)) {
			String[] filterArray = null;
			try {
				filterArray = skipColumns.split(",");
			} catch (Exception e) {
				logger.error("skipColumns split error, skipColumns string is: {}", skipColumns);
				filterArray = null;
			}
			if (filterArray != null) {
				for (String fc : filterArray) {
					if (isNotParseColumn()) {
						skipSet.add(fc);
					}else{
						skipSet.add(ImportUtils.parseColumn(fc));
					}
				}
			}
		}
    	if (StringUtils.isNotBlank(columns)) {

			String[] filterArray = null;
			try {
				filterArray = columns.split(",");
			} catch (Exception e) {
				logger.error("columns split error, columns string is: {}", columns);
				filterArray = null;
			}
			if (filterArray != null) {
				for (String fc : filterArray) {
					if (isNotParseColumn()) {
						columnSet.add(fc);
					}else{
						columnSet.add(ImportUtils.parseColumn(fc));
					}
				}
			}
		
    	}
    	
	}

	@Override
	protected void jumpToItem(int itemIndex) throws Exception {
		for (int i = 0; i < itemIndex; i++) {
			readLine();
		}
	}

}

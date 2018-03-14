/*
 * Copyright 2006-2013 the original author or authors.
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

import com.jnj.adf.grid.utils.ImportUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link Partitioner} that locates multiple resources and
 * associates their file names with execution context keys. Creates an
 * {@link ExecutionContext} per resource, and labels them as
 * <code>{partition0, partition1, ..., partitionN}</code>. The grid size is
 * ignored.
 *
 * @author Dave Syer
 * @author sli172
 * @since 2.0
 */
public class CustomMultiResourcePartitioner implements Partitioner {

	private static final String DEFAULT_KEY_NAME = "fileName";

	private static final String PARTITION_KEY = "partition";

	private Resource[] resources = new Resource[0];

	private String keyName = DEFAULT_KEY_NAME;
	
	private String path;
	private String columns;
	private String skipColumns;
	private boolean notParseColumn;

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	
	public String getSkipColumns() {
		return skipColumns;
	}

	public void setSkipColumns(String skipColumns) {
		this.skipColumns = skipColumns;
	}

	public String getKeyName() {
		return keyName;
	}

	public boolean isNotParseColumn() {
		return notParseColumn;
	}

	public void setNotParseColumn(boolean notParseColumn) {
		this.notParseColumn = notParseColumn;
	}

	/**
	 * The resources to assign to each partition. In Spring configuration you
	 * can use a pattern to select multiple resources.
	 * @param resources the resources to use
	 */
	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	/**
	 * The name of the key for the file name in each {@link ExecutionContext}.
	 * Defaults to "fileName".
	 * @param keyName the value of the key
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * Assign the filename of each of the injected resources to an
	 * {@link ExecutionContext}.
	 *
	 * @see Partitioner#partition(int)
	 */
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> map = new HashMap<String, ExecutionContext>(gridSize);
		String i = "";
		String[] columnss = columns.split(",");
		String[] skipColumnss = null;
		if(StringUtils.isNotBlank(skipColumns)) {
			skipColumnss = skipColumns.split(",");

		}
		StringBuilder sb = new StringBuilder("");
		for(String column : columnss){
			if(!ArrayUtils.contains(skipColumnss, column)){
				if (isNotParseColumn()) {
					sb.append(column).append(",");
				} else{
					sb.append(ImportUtils.parseColumn(column)).append(",");
				}
			}
		}
		
		for (Resource resource : resources) {
			ExecutionContext context = new ExecutionContext();
			Assert.state(resource.exists(), "Resource does not exist: "+resource);
			try {
				context.putString("path", path);
				context.putString("filename", resource.getFilename());
				context.putString("columnNames", sb.substring(0, sb.length()-1));
				context.putString(keyName, resource.getURL().toExternalForm());
			}
			catch (IOException e) {
				throw new IllegalArgumentException("File could not be located for: "+resource, e);
			}
			i = resource.getFilename();
			map.put(PARTITION_KEY + i, context);
		}
		return map;
	}

}

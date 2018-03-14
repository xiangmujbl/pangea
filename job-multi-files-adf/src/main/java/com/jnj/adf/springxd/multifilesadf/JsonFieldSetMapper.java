/*                                                                         
 * Copyright 2010-2013 the original author or authors.                     
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;

import java.util.HashSet;
import java.util.Set;

public class JsonFieldSetMapper implements FieldSetMapper<JsonObject>, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(JsonFieldSetMapper.class);
	private String skipColumns;
	private Set<String> skipSet = new HashSet<String>();
	private boolean notParseColumn;

	@Override
	public JsonObject mapFieldSet(FieldSet fs) throws BindException {
		JsonObject jsonObj = JsonObject.create();
		for (String name : fs.getNames()) {
			if (!skipSet.contains(name)){
				if (isNotParseColumn()) {
					jsonObj.append(name, fs.readRawString(name));
				}else {
					jsonObj.append(ImportUtils.parseColumn(name), fs.readRawString(name));
				}
			}
		}
		return jsonObj;
	}

	public String getSkipColumns() {
		return skipColumns;
	}

	public void setSkipColumns(String skipColumns) {
		this.skipColumns = skipColumns;
	}

	public boolean isNotParseColumn() {
		return notParseColumn;
	}

	public void setNotParseColumn(boolean notParseColumn) {
		this.notParseColumn = notParseColumn;
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
					skipSet.add(fc);
				}
			}
		}
	}
}

package com.jnj.pangea.common.utils;

import java.util.List;

public class RegionData {

    private String path;
    private List<List<String>> values;
    private List<String> keyField;
    private List<Integer> keyIndex;
    private List<String> fields;

    public RegionData() {
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<List<String>> getValues() {
        return this.values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    public List<String> getKeyField() {
        return this.keyField;
    }

    public void setKeyField(List<String> keyField) {
        this.keyField = keyField;
    }

    public List<String> getFields() {
        return this.fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<Integer> getKeyIndex() {
        return this.keyIndex;
    }

    public void setKeyIndex(List<Integer> keyIndex) {
        this.keyIndex = keyIndex;
    }
}

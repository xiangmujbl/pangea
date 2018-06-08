package com.jnj.pangea.common.utils;

import java.util.List;

public class ExcelData {

    private String sheetName;
    private List<RegionData> regionList;

    public ExcelData() {
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<RegionData> getRegionList() {
        return this.regionList;
    }

    public void setRegionList(List<RegionData> regionList) {
        this.regionList = regionList;
    }
}

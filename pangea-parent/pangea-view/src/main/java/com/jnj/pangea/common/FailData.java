package com.jnj.pangea.common;

import com.jnj.adf.client.api.remote.RawDataHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public class FailData extends BaseBo {

    public FailData() {
    }

    public FailData(String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1) {
        this.functionalArea = functionalArea;
        this.interfaceID = interfaceID;
        this.errorCode = errorCode;
        this.errorValue = errorValue;
        this.sourceSystem = sourceSystem;
        this.key1 = key1;
        this.key2 = "";
        this.key3 = "";
        this.key4 = "";
        this.key5 = "";
    }

    public FailData(String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2) {
        this.functionalArea = functionalArea;
        this.interfaceID = interfaceID;
        this.errorCode = errorCode;
        this.errorValue = errorValue;
        this.sourceSystem = sourceSystem;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = "";
        this.key4 = "";
        this.key5 = "";
    }

    public FailData(String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2, String key3) {
        this.functionalArea = functionalArea;
        this.interfaceID = interfaceID;
        this.errorCode = errorCode;
        this.errorValue = errorValue;
        this.sourceSystem = sourceSystem;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = "";
        this.key5 = "";
    }

    public FailData(String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2, String key3, String key4) {
        this.functionalArea = functionalArea;
        this.interfaceID = interfaceID;
        this.errorCode = errorCode;
        this.errorValue = errorValue;
        this.sourceSystem = sourceSystem;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.key5 = "";
    }

    public FailData(String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2, String key3, String key4, String key5) {
        this.functionalArea = functionalArea;
        this.interfaceID = interfaceID;
        this.errorCode = errorCode;
        this.errorValue = errorValue;
        this.sourceSystem = sourceSystem;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.key5 = key5;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("functionalArea", this.functionalArea)
                .add("interfaceID", this.interfaceID)
                .add("errorCode", this.errorCode)
                .add("sourceSystem", this.sourceSystem)
                .add("key1", this.key1)
                .add("key2", this.key2)
                .add("key3", this.key3)
                .add("key4", this.key4)
                .add("key5", this.key5)
                .toJsonString();
    }

    private String functionalArea;
    private String interfaceID;
    private String errorCode;
    private String sourceSystem;
    private String businessArea;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    private String errorValue;

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public String getInterfaceID() {
        return interfaceID;
    }

    public void setInterfaceID(String interfaceID) {
        this.interfaceID = interfaceID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getKey5() {
        return key5;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }
}

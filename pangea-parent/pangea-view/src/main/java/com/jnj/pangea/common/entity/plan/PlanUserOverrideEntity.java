package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanUserOverrideEntity extends CommonEntity {

    private String target;
    private String sourceSystem;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;

    public PlanUserOverrideEntity(Map<String, Object> map) {
        super(map);

        setTarget((String) map.get("target"));
        setSourceSystem((String) map.get("sourceSystem"));
        setKey1((String) map.get("key1"));
        setKey2((String) map.get("key2"));
        setKey3((String) map.get("key3"));
        setKey4((String) map.get("key4"));
        setKey5((String) map.get("key5"));
        setValue1((String) map.get("value1"));
        setValue2((String) map.get("value2"));
        setValue3((String) map.get("value3"));
        setValue4((String) map.get("value4"));
        setValue5((String) map.get("value5"));
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getKey1() {
        return this.key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return this.key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return this.key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return this.key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getKey5() {
        return this.key5;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return this.value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return this.value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return this.value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

}

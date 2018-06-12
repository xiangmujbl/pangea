package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMJNJCalendarV1Entity extends CommonEntity {
    private String fiscalPeriod;
    private String weekFromDate;
    private String weekToDate;
    private String calWeek;
    private String noOfWeek;

    public String getCalWeek() {
        return calWeek;
    }

    public void setCalWeek(String calWeek) {
        this.calWeek = calWeek;
    }

    public String getNoOfWeek() {
        return noOfWeek;
    }

    public void setNoOfWeek(String noOfWeek) {
        this.noOfWeek = noOfWeek;
    }

    @Override
    public String toString() {
        return "EDMJNJCalendarV1Entity{" +
                "fiscalPeriod='" + fiscalPeriod + '\'' +
                ", weekFromDate='" + weekFromDate + '\'' +
                ", weekToDate='" + weekToDate + '\'' +
                ", calWeek='" + calWeek + '\'' +
                ", noOfWeek='" + noOfWeek + '\'' +
                '}';
    }

    public EDMJNJCalendarV1Entity(Map<String, Object> map) {
        super(map);
        setFiscalPeriod((String) map.get("fiscalPeriod"));
        setWeekFromDate((String) map.get("weekFromDate"));
        setWeekToDate((String) map.get("weekToDate"));
        setCalWeek((String) map.get("calWeek"));
        setNoOfWeek((String) map.get("noOfWeek"));
    }
    public String getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(String fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public String getWeekFromDate() {
        return weekFromDate;
    }

    public void setWeekFromDate(String weekFromDate) {
        this.weekFromDate = weekFromDate;
    }

    public String getWeekToDate() {
        return weekToDate;
    }

    public void setWeekToDate(String weekToDate) {
        this.weekToDate = weekToDate;
    }


}

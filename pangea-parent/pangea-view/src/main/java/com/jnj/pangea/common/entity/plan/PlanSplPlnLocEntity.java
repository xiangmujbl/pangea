package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * @author: qzhan112
 * @date: 2018/5/3
 */
public class PlanSplPlnLocEntity extends CommonEntity {
    private String planLocTypeDes;
    private String sourceSystem;
    private String planLocTypeId;
    private String _3specLocAttDesc;
    private String _1specLocAttDesc;
    private String localCurrency;
    private String vendorOrCustomer;
    private String localNumber;
    private String _2specLocAtt;
    private String _2specLocAttDesc;
    private String localName;
    private String _3specLocAtt;
    private String _1specLocAtt;
    private String localCountry;
    private String localRegion;
    private String localPlant;


    public PlanSplPlnLocEntity(Map<String, Object> map) {
        super(map);
        setPlanLocTypeDes((String) map.get("planLocTypeDes"));
        setSourceSystem((String) map.get("sourceSystem"));
        setPlanLocTypeId((String) map.get("planLocTypeId"));
        set_3specLocAttDesc((String) map.get("_3specLocAttDesc"));
        set_1specLocAttDesc((String) map.get("_1specLocAttDesc"));
        setLocalCurrency((String) map.get("localCurrency"));
        setVendorOrCustomer((String) map.get("vendorOrCustomer"));
        setLocalNumber((String) map.get("localNumber"));
        set_2specLocAtt((String) map.get("_2specLocAtt"));
        set_2specLocAttDesc((String) map.get("_2specLocAttDesc"));
        setLocalName((String) map.get("localName"));
        set_3specLocAtt((String) map.get("_3specLocAtt"));
        set_1specLocAtt((String) map.get("_1specLocAtt"));
        setLocalCountry((String) map.get("localCountry"));
        setLocalRegion((String) map.get("localRegion"));
        setLocalPlant((String) map.get("localPlant"));
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getPlanLocTypeDes() {
        return planLocTypeDes;
    }

    public void setPlanLocTypeDes(String planLocTypeDes) {
        this.planLocTypeDes = planLocTypeDes;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPlanLocTypeId() {
        return planLocTypeId;
    }

    public void setPlanLocTypeId(String planLocTypeId) {
        this.planLocTypeId = planLocTypeId;
    }

    public String get_3specLocAttDesc() {
        return _3specLocAttDesc;
    }

    public void set_3specLocAttDesc(String _3specLocAttDesc) {
        this._3specLocAttDesc = _3specLocAttDesc;
    }

    public String get_1specLocAttDesc() {
        return _1specLocAttDesc;
    }

    public void set_1specLocAttDesc(String _1specLocAttDesc) {
        this._1specLocAttDesc = _1specLocAttDesc;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getVendorOrCustomer() {
        return vendorOrCustomer;
    }

    public void setVendorOrCustomer(String vendorOrCustomer) {
        this.vendorOrCustomer = vendorOrCustomer;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String get_2specLocAtt() {
        return _2specLocAtt;
    }

    public void set_2specLocAtt(String _2specLocAtt) {
        this._2specLocAtt = _2specLocAtt;
    }

    public String get_2specLocAttDesc() {
        return _2specLocAttDesc;
    }

    public void set_2specLocAttDesc(String _2specLocAttDesc) {
        this._2specLocAttDesc = _2specLocAttDesc;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String get_3specLocAtt() {
        return _3specLocAtt;
    }

    public void set_3specLocAtt(String _3specLocAtt) {
        this._3specLocAtt = _3specLocAtt;
    }

    public String get_1specLocAtt() {
        return _1specLocAtt;
    }

    public void set_1specLocAtt(String _1specLocAtt) {
        this._1specLocAtt = _1specLocAtt;
    }

    public String getLocalCountry() {
        return localCountry;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }

    public String getLocalRegion() {
        return localRegion;
    }

    public void setLocalRegion(String localRegion) {
        this.localRegion = localRegion;
    }
}

package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMOutboundDeliveryHeaderV1Entity extends CommonEntity {

    private String srcSysCd;
    private String delvDocId;
    private String shippingPtNum;
    private String delvTypeCd;
    private String slsOrdrCarCd;
    private String delvDt;
    private String crtDttm;
    private String soldToCustNum;
    private String shipToCustNum;
    private String billOfLdngNum;
    private String delvNum;
    private String planGiDt;
    private String actlGiDt;
    private String shippingCondCd;
    private String supNum;
    private String plntCd;

    public EDMOutboundDeliveryHeaderV1Entity(Map<String, Object> map) {
        super(map);
        setSrcSysCd((String)map.get("srcSysCd"));
        setDelvDocId((String)map.get("delvDocId"));
        setShippingPtNum((String)map.get("shippingPtNum"));
        setDelvTypeCd((String)map.get("delvTypeCd"));
        setSlsOrdrCarCd((String)map.get("slsOrdrCarCd"));
        setDelvDt((String)map.get("delvDt"));
        setCrtDttm((String)map.get("crtDttm"));
        setSoldToCustNum((String)map.get("soldToCustNum"));
        setShipToCustNum((String)map.get("shipToCustNum"));
        setBillOfLdngNum((String)map.get("billOfLdngNum"));
        setDelvNum((String)map.get("delvNum"));
        setPlanGiDt((String)map.get("planGiDt"));
        setActlGiDt((String)map.get("actlGiDt"));
        setShippingCondCd((String)map.get("shippingCondCd"));
        setSupNum((String)map.get("supNum"));
        setPlntCd((String)map.get("plntCd"));
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getDelvDocId() {
        return delvDocId;
    }

    public void setDelvDocId(String delvDocId) {
        this.delvDocId = delvDocId;
    }

    public String getShippingPtNum() {
        return shippingPtNum;
    }

    public void setShippingPtNum(String shippingPtNum) {
        this.shippingPtNum = shippingPtNum;
    }

    public String getSupNum() {
        return supNum;
    }

    public void setSupNum(String supNum) {
        this.supNum = supNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getDelvTypeCd() {
        return delvTypeCd;
    }

    public void setDelvTypeCd(String delvTypeCd) {
        this.delvTypeCd = delvTypeCd;
    }

    public String getSlsOrdrCarCd() {
        return slsOrdrCarCd;
    }

    public void setSlsOrdrCarCd(String slsOrdrCarCd) {
        this.slsOrdrCarCd = slsOrdrCarCd;
    }

    public String getDelvDt() {
        return delvDt;
    }

    public void setDelvDt(String delvDt) {
        this.delvDt = delvDt;
    }

    public String getCrtDttm() {
        return crtDttm;
    }

    public void setCrtDttm(String crtDttm) {
        this.crtDttm = crtDttm;
    }

    public String getSoldToCustNum() {
        return soldToCustNum;
    }

    public void setSoldToCustNum(String soldToCustNum) {
        this.soldToCustNum = soldToCustNum;
    }

    public String getShipToCustNum() {
        return shipToCustNum;
    }

    public void setShipToCustNum(String shipToCustNum) {
        this.shipToCustNum = shipToCustNum;
    }

    public String getBillOfLdngNum() {
        return billOfLdngNum;
    }

    public void setBillOfLdngNum(String billOfLdngNum) {
        this.billOfLdngNum = billOfLdngNum;
    }

    public String getDelvNum() {
        return delvNum;
    }

    public void setDelvNum(String delvNum) {
        this.delvNum = delvNum;
    }

    public String getPlanGiDt() {
        return planGiDt;
    }

    public void setPlanGiDt(String planGiDt) {
        this.planGiDt = planGiDt;
    }

    public String getActlGiDt() {
        return actlGiDt;
    }

    public void setActlGiDt(String actlGiDt) {
        this.actlGiDt = actlGiDt;
    }

    public String getShippingCondCd() {
        return shippingCondCd;
    }

    public void setShippingCondCd(String shippingCondCd) {
        this.shippingCondCd = shippingCondCd;
    }
}

package com.jnj.pangea.omp.gdm_req_from_erp.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmReqFromErpBo extends BaseBo {

    private String reqFromErpId;
    private String blckd;
    private String blckt;
    private String deleted;
    private String deliveryDate;
    private String delkz;
    private String delnr;
    private String delps;
    private String erpId;
    private String flief;
    private String locationId;
    private String manualOffset;
    private String plifz;
    private String prio_urg;
    private String productId;
    private String reqType;
    private String totalQuantity;
    private String unitId;
    private String verid;
    private String wrk02;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("", "")
                .toJsonString();
    }

    public String getReqFromErpId() {
        return reqFromErpId;
    }

    public void setReqFromErpId(String reqFromErpId) {
        this.reqFromErpId = reqFromErpId;
    }

    public String getBlckd() {
        return blckd;
    }

    public void setBlckd(String blckd) {
        this.blckd = blckd;
    }

    public String getBlckt() {
        return blckt;
    }

    public void setBlckt(String blckt) {
        this.blckt = blckt;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDelkz() {
        return delkz;
    }

    public void setDelkz(String delkz) {
        this.delkz = delkz;
    }

    public String getDelnr() {
        return delnr;
    }

    public void setDelnr(String delnr) {
        this.delnr = delnr;
    }

    public String getDelps() {
        return delps;
    }

    public void setDelps(String delps) {
        this.delps = delps;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getFlief() {
        return flief;
    }

    public void setFlief(String flief) {
        this.flief = flief;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getManualOffset() {
        return manualOffset;
    }

    public void setManualOffset(String manualOffset) {
        this.manualOffset = manualOffset;
    }

    public String getPlifz() {
        return plifz;
    }

    public void setPlifz(String plifz) {
        this.plifz = plifz;
    }

    public String getPrio_urg() {
        return prio_urg;
    }

    public void setPrio_urg(String prio_urg) {
        this.prio_urg = prio_urg;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getVerid() {
        return verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getWrk02() {
        return wrk02;
    }

    public void setWrk02(String wrk02) {
        this.wrk02 = wrk02;
    }
}

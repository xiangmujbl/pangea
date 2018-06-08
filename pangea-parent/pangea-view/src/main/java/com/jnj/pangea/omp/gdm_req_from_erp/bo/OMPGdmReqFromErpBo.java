package com.jnj.pangea.omp.gdm_req_from_erp.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmReqFromErpBo extends BaseBo {

    private String REQFromERPId;
    private String BLCKD;
    private String BLCKT;
    private String DELETED;
    private String DeliveryDate;
    private String DELKZ;
    private String DELNR;
    private String DELPS;
    private String ERPId;
    private String FLIEF;
    private String LocationId;
    private String ManualOffset;
    private String PLIFZ;
    private String PRIO_URG;
    private String ProductId;
    private String REQType;
    private String TotalQuantity;
    private String UnitId;
    private String VERID;
    private String WRK02;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("REQFromERPId", this.REQFromERPId)
                .toJsonString();
    }

    public String getREQFromERPId() {
        return REQFromERPId;
    }

    public void setREQFromERPId(String REQFromERPId) {
        this.REQFromERPId = REQFromERPId;
    }

    public String getBLCKD() {
        return BLCKD;
    }

    public void setBLCKD(String BLCKD) {
        this.BLCKD = BLCKD;
    }

    public String getBLCKT() {
        return BLCKT;
    }

    public void setBLCKT(String BLCKT) {
        this.BLCKT = BLCKT;
    }

    public String getDELETED() {
        return DELETED;
    }

    public void setDELETED(String DELETED) {
        this.DELETED = DELETED;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getDELKZ() {
        return DELKZ;
    }

    public void setDELKZ(String DELKZ) {
        this.DELKZ = DELKZ;
    }

    public String getDELNR() {
        return DELNR;
    }

    public void setDELNR(String DELNR) {
        this.DELNR = DELNR;
    }

    public String getDELPS() {
        return DELPS;
    }

    public void setDELPS(String DELPS) {
        this.DELPS = DELPS;
    }

    public String getERPId() {
        return ERPId;
    }

    public void setERPId(String ERPId) {
        this.ERPId = ERPId;
    }

    public String getFLIEF() {
        return FLIEF;
    }

    public void setFLIEF(String FLIEF) {
        this.FLIEF = FLIEF;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    public String getManualOffset() {
        return ManualOffset;
    }

    public void setManualOffset(String manualOffset) {
        ManualOffset = manualOffset;
    }

    public String getPLIFZ() {
        return PLIFZ;
    }

    public void setPLIFZ(String PLIFZ) {
        this.PLIFZ = PLIFZ;
    }

    public String getPRIO_URG() {
        return PRIO_URG;
    }

    public void setPRIO_URG(String PRIO_URG) {
        this.PRIO_URG = PRIO_URG;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getREQType() {
        return REQType;
    }

    public void setREQType(String REQType) {
        this.REQType = REQType;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getVERID() {
        return VERID;
    }

    public void setVERID(String VERID) {
        this.VERID = VERID;
    }

    public String getWRK02() {
        return WRK02;
    }

    public void setWRK02(String WRK02) {
        this.WRK02 = WRK02;
    }
}

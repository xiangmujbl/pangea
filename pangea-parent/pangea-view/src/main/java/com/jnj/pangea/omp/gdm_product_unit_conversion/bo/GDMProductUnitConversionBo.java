package com.jnj.pangea.omp.gdm_product_unit_conversion.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class GDMProductUnitConversionBo extends BaseBo {

    private String gdmProductUnitConversionId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String factor;
    private String productId;
    private String unitId;
    private String comments;

    @Override
    public String toString() {
        return "GDMProductUnitConversionBo{" +
                "gdmProductUnitConversionId='" + gdmProductUnitConversionId + '\'' +
                ", active='" + active + '\'' +
                ", activeFCTERP='" + activeFCTERP + '\'' +
                ", activeOPRERP='" + activeOPRERP + '\'' +
                ", activeSOPERP='" + activeSOPERP + '\'' +
                ", factor='" + factor + '\'' +
                ", productId='" + productId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("gdmProductUnitConversionId", this.gdmProductUnitConversionId)
                .toJsonString();
    }

    public String getGdmProductUnitConversionId() {
        return gdmProductUnitConversionId;
    }

    public void setGdmProductUnitConversionId(String gdmProductUnitConversionId) {
        this.gdmProductUnitConversionId = gdmProductUnitConversionId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
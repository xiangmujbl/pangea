package com.jnj.pangea.omp.gdm_bom_element_Process.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

import java.lang.reflect.Field;

public class OMPGdmbomelementBo extends BaseBo implements Cloneable {

    private String BOMElementId;
    private String Active;
    private String ActiveFCTERP;
    private String ActiveOPRERP;
    private String ActiveSOPERP;
    private String BatchId;
    private String BOMId;
    private String BOMType;
    private String BOMUsage;
    private String Comments;
    private String EndEff;
    private String ERPFeedbackQuantity;
    private String LocationId;
    private String Offset;
    private String OffsetCalendarId;
    private String OffsetPercentage;
    private String OffsetPercType;
    private String PlanLevelId;
    private String ProductId;
    private String Quantity;
    private String StartEff;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("BOMElementId",BOMElementId)
                .toJsonString();
    }

    public String getBOMElementId() {
        return BOMElementId;
    }

    public void setBOMElementId(String BOMElementId) {
        this.BOMElementId = BOMElementId;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getActiveFCTERP() {
        return ActiveFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        ActiveFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return ActiveOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        ActiveOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return ActiveSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        ActiveSOPERP = activeSOPERP;
    }

    public String getBatchId() {
        return BatchId;
    }

    public void setBatchId(String batchId) {
        BatchId = batchId;
    }

    public String getBOMId() {
        return BOMId;
    }

    public void setBOMId(String BOMId) {
        this.BOMId = BOMId;
    }

    public String getBOMType() {
        return BOMType;
    }

    public void setBOMType(String BOMType) {
        this.BOMType = BOMType;
    }

    public String getBOMUsage() {
        return BOMUsage;
    }

    public void setBOMUsage(String BOMUsage) {
        this.BOMUsage = BOMUsage;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getEndEff() {
        return EndEff;
    }

    public void setEndEff(String endEff) {
        EndEff = endEff;
    }

    public String getERPFeedbackQuantity() {
        return ERPFeedbackQuantity;
    }

    public void setERPFeedbackQuantity(String ERPFeedbackQuantity) {
        this.ERPFeedbackQuantity = ERPFeedbackQuantity;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    public String getOffset() {
        return Offset;
    }

    public void setOffset(String offset) {
        Offset = offset;
    }

    public String getOffsetCalendarId() {
        return OffsetCalendarId;
    }

    public void setOffsetCalendarId(String offsetCalendarId) {
        OffsetCalendarId = offsetCalendarId;
    }

    public String getOffsetPercentage() {
        return OffsetPercentage;
    }

    public void setOffsetPercentage(String offsetPercentage) {
        OffsetPercentage = offsetPercentage;
    }

    public String getOffsetPercType() {
        return OffsetPercType;
    }

    public void setOffsetPercType(String offsetPercType) {
        OffsetPercType = offsetPercType;
    }

    public String getPlanLevelId() {
        return PlanLevelId;
    }

    public void setPlanLevelId(String planLevelId) {
        PlanLevelId = planLevelId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getStartEff() {
        return StartEff;
    }

    public void setStartEff(String startEff) {
        StartEff = startEff;
    }

    @Override
    public String toString() {
        return "OMPGdmbomelementBo{" +
                "BOMElementId='" + BOMElementId + '\'' +
                ", Active='" + Active + '\'' +
                ", ActiveFCTERP='" + ActiveFCTERP + '\'' +
                ", ActiveOPRERP='" + ActiveOPRERP + '\'' +
                ", ActiveSOPERP='" + ActiveSOPERP + '\'' +
                ", BatchId='" + BatchId + '\'' +
                ", BOMId='" + BOMId + '\'' +
                ", BOMType='" + BOMType + '\'' +
                ", BOMUsage='" + BOMUsage + '\'' +
                ", Comments='" + Comments + '\'' +
                ", EndEff='" + EndEff + '\'' +
                ", ERPFeedbackQuantity='" + ERPFeedbackQuantity + '\'' +
                ", LocationId='" + LocationId + '\'' +
                ", Offset='" + Offset + '\'' +
                ", OffsetCalendarId='" + OffsetCalendarId + '\'' +
                ", OffsetPercentage='" + OffsetPercentage + '\'' +
                ", OffsetPercType='" + OffsetPercType + '\'' +
                ", PlanLevelId='" + PlanLevelId + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", StartEff='" + StartEff + '\'' +
                '}';
    }
    @Override
    public OMPGdmbomelementBo clone() {
        try {
            return (OMPGdmbomelementBo)super.clone();
        }catch (Exception e){
          e.printStackTrace();
            OMPGdmbomelementBo ompGdmbomelementBo=new OMPGdmbomelementBo();
            Class classOORI=this.getClass();
            Class classOClone=ompGdmbomelementBo.getClass();
            Field[] fieldORI=classOORI.getDeclaredFields();
            Field[] fieldClone=classOClone.getDeclaredFields();
            for(int i=0;i<fieldORI.length;i++){
               Field FieldORI=fieldORI[i];
                for(int j=0;j<fieldClone.length;j++){
                    Field FieldClone=fieldClone[j];
                    if(FieldClone.getName().equals(FieldORI.getName())){
                        FieldClone.setAccessible(true);
                        try {
                            FieldClone.set(ompGdmbomelementBo, FieldORI.get(this));
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                        FieldClone.setAccessible(false);
                    }

                }
            }
            return ompGdmbomelementBo;
        }
        // TODO Auto-generated method stub
    }
}

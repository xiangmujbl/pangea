package com.jnj.pangea.edm.sales_history_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EDMSalesHistoryV1Bo
 * @Package com.jnj.pangea.edm.sales_history_v1.bo
 * @Description: EDMSalesHistoryV1Bo Domain Business Object
 * @date 2018/6/12 15:23
 */
public class EDMSalesHistoryV1Bo extends BaseBo {

    private String sourceSystem;
    private String localPrecDocNo;
    private String localSPrecDocLnNo;
    private String localSubsDocNo;
    private String localSubsDocLnNo;
    private String localSubDocCatg;
    private String localBaseQuantity;
    private String localBaseUom;
    private String localSalesQuantity;
    private String localSalesUom;
    private String localPrecItemCatg;
    private String localCrtDt;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPrecDocNo() {
        return localPrecDocNo;
    }

    public void setLocalPrecDocNo(String localPrecDocNo) {
        this.localPrecDocNo = localPrecDocNo;
    }

    public String getLocalSPrecDocLnNo() {
        return localSPrecDocLnNo;
    }

    public void setLocalSPrecDocLnNo(String localSPrecDocLnNo) {
        this.localSPrecDocLnNo = localSPrecDocLnNo;
    }

    public String getLocalSubsDocNo() {
        return localSubsDocNo;
    }

    public void setLocalSubsDocNo(String localSubsDocNo) {
        this.localSubsDocNo = localSubsDocNo;
    }

    public String getLocalSubsDocLnNo() {
        return localSubsDocLnNo;
    }

    public void setLocalSubsDocLnNo(String localSubsDocLnNo) {
        this.localSubsDocLnNo = localSubsDocLnNo;
    }

    public String getLocalSubDocCatg() {
        return localSubDocCatg;
    }

    public void setLocalSubDocCatg(String localSubDocCatg) {
        this.localSubDocCatg = localSubDocCatg;
    }

    public String getLocalBaseQuantity() {
        return localBaseQuantity;
    }

    public void setLocalBaseQuantity(String localBaseQuantity) {
        this.localBaseQuantity = localBaseQuantity;
    }

    public String getLocalBaseUom() {
        return localBaseUom;
    }

    public void setLocalBaseUom(String localBaseUom) {
        this.localBaseUom = localBaseUom;
    }

    public String getLocalSalesQuantity() {
        return localSalesQuantity;
    }

    public void setLocalSalesQuantity(String localSalesQuantity) {
        this.localSalesQuantity = localSalesQuantity;
    }

    public String getLocalSalesUom() {
        return localSalesUom;
    }

    public void setLocalSalesUom(String localSalesUom) {
        this.localSalesUom = localSalesUom;
    }

    public String getLocalPrecItemCatg() {
        return localPrecItemCatg;
    }

    public void setLocalPrecItemCatg(String localPrecItemCatg) {
        this.localPrecItemCatg = localPrecItemCatg;
    }

    public String getLocalCrtDt() {
        return localCrtDt;
    }

    public void setLocalCrtDt(String localCrtDt) {
        this.localCrtDt = localCrtDt;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPrecDocNo", this.localPrecDocNo)
                .add("localSPrecDocLnNo", this.localSPrecDocLnNo)
                .add("localSubsDocNo", this.localSubsDocNo)
                .add("localSubsDocLnNo", this.localSubsDocLnNo)
                .add("localSubDocCatg", this.localSubDocCatg)
                .toJsonString();
    }
}




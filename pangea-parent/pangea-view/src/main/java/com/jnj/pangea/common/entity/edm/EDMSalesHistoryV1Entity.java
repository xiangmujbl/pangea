package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**   
 * @Name: EDMSalesHistoryV1Entity
 * @Description: sales_history_v1 entity for edm system
 * @author KG(Kelvin Gu)   
 * @date 06-12-2018 02:55:05 
*/
public class EDMSalesHistoryV1Entity extends CommonEntity {

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

    public EDMSalesHistoryV1Entity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalPrecDocNo((String) map.get("localPrecDocNo"));
        setLocalSPrecDocLnNo((String) map.get("localSPrecDocLnNo"));
        setLocalSubsDocNo((String) map.get("localSubsDocNo"));
        setLocalSubsDocLnNo((String) map.get("localSubsDocLnNo"));
        setLocalSubDocCatg((String) map.get("localSubDocCatg"));
        setLocalBaseQuantity((String) map.get("localBaseQuantity"));
        setLocalBaseUom((String) map.get("localBaseUom"));
        setLocalSalesQuantity((String) map.get("localSalesQuantity"));
        setLocalSalesUom((String) map.get("localSalesUom"));
        setLocalPrecItemCatg((String) map.get("localPrecItemCatg"));
        setLocalCrtDt((String) map.get("localCrtDt"));
    }

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
}

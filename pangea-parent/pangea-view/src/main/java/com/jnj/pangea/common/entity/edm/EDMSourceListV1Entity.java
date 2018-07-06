package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMSourceListV1Entity extends CommonEntity {
    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String localNumberofSourceListRecord;
    private String materialNumber;
//    private String plant;
//    private String localCreatedOn;
//    private String localCreatedBy;
    private String localSourceListRecordValidFrom;
    private String localSourceListRecordValidTo;
    private String localVendorAccountNumber;
    private String localFixedvendor;
    private String localAgreementNumber;
    private String localAgreementItem;
    private String localFixedOutlinePurchaseAgreementItem;
    private String localPlantfromWhichMaterialisProcured;
    private String localMatForManufPartNumber;
    private String localBlockedSourceofSupply;
    private String localPurchasingOrganization;
    private String localPurchasingDocumentCategory;
    private String localCategoryofSourceListRecord;
    private String localSourceListUsageinMaterialsPlanning;

    public EDMSourceListV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setLocalNumberofSourceListRecord((String) map.get("localNumberofSourceListRecord"));
        setMaterialNumber((String) map.get("materialNumber"));
//        setPlant((String) map.get("plant"));
//        setLocalCreatedOn((String) map.get("localCreatedOn"));
//        setLocalCreatedBy((String) map.get("localCreatedBy"));
        setLocalSourceListRecordValidFrom((String) map.get("localSourceListRecordValidFrom"));
        setLocalSourceListRecordValidTo((String) map.get("localSourceListRecordValidTo"));
        setLocalVendorAccountNumber((String) map.get("localVendorAccountNumber"));
        setLocalFixedvendor((String) map.get("localFixedvendor"));
        setLocalAgreementNumber((String) map.get("localAgreementNumber"));
        setLocalAgreementItem((String) map.get("localAgreementItem"));
        setLocalFixedOutlinePurchaseAgreementItem((String) map.get("localFixedOutlinePurchaseAgreementItem"));
        setLocalPlantfromWhichMaterialisProcured((String) map.get("localPlantfromWhichMaterialisProcured"));
        setLocalMatForManufPartNumber((String) map.get("localMatForManufPartNumber"));
        setLocalBlockedSourceofSupply((String) map.get("localBlockedSourceofSupply"));
        setLocalPurchasingOrganization((String) map.get("localPurchasingOrganization"));
        setLocalPurchasingDocumentCategory((String) map.get("localPurchasingDocumentCategory"));
        setLocalCategoryofSourceListRecord((String) map.get("localCategoryofSourceListRecord"));
        setLocalSourceListUsageinMaterialsPlanning((String) map.get("localSourceListUsageinMaterialsPlanning"));


    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalNumberofSourceListRecord() {
        return localNumberofSourceListRecord;
    }

    public void setLocalNumberofSourceListRecord(String localNumberofSourceListRecord) {
        this.localNumberofSourceListRecord = localNumberofSourceListRecord;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

//    public String getPlant() {
//        return plant;
//    }
//
//    public void setPlant(String plant) {
//        this.plant = plant;
//    }
//
//    public String getLocalCreatedOn() {
//        return localCreatedOn;
//    }
//
//    public void setLocalCreatedOn(String localCreatedOn) {
//        this.localCreatedOn = localCreatedOn;
//    }
//
//    public String getLocalCreatedBy() {
//        return localCreatedBy;
//    }
//
//    public void setLocalCreatedBy(String localCreatedBy) {
//        this.localCreatedBy = localCreatedBy;
//    }

    public String getLocalSourceListRecordValidFrom() {
        return localSourceListRecordValidFrom;
    }

    public void setLocalSourceListRecordValidFrom(String localSourceListRecordValidFrom) {
        this.localSourceListRecordValidFrom = localSourceListRecordValidFrom;
    }

    public String getLocalSourceListRecordValidTo() {
        return localSourceListRecordValidTo;
    }

    public void setLocalSourceListRecordValidTo(String localSourceListRecordValidTo) {
        this.localSourceListRecordValidTo = localSourceListRecordValidTo;
    }

    public String getLocalVendorAccountNumber() {
        return localVendorAccountNumber;
    }

    public void setLocalVendorAccountNumber(String localVendorAccountNumber) {
        this.localVendorAccountNumber = localVendorAccountNumber;
    }

    public String getLocalFixedvendor() {
        return localFixedvendor;
    }

    public void setLocalFixedvendor(String localFixedvendor) {
        this.localFixedvendor = localFixedvendor;
    }

    public String getLocalAgreementNumber() {
        return localAgreementNumber;
    }

    public void setLocalAgreementNumber(String localAgreementNumber) {
        this.localAgreementNumber = localAgreementNumber;
    }

    public String getLocalAgreementItem() {
        return localAgreementItem;
    }

    public void setLocalAgreementItem(String localAgreementItem) {
        this.localAgreementItem = localAgreementItem;
    }

    public String getLocalFixedOutlinePurchaseAgreementItem() {
        return localFixedOutlinePurchaseAgreementItem;
    }

    public void setLocalFixedOutlinePurchaseAgreementItem(String localFixedOutlinePurchaseAgreementItem) {
        this.localFixedOutlinePurchaseAgreementItem = localFixedOutlinePurchaseAgreementItem;
    }

    public String getLocalPlantfromWhichMaterialisProcured() {
        return localPlantfromWhichMaterialisProcured;
    }

    public void setLocalPlantfromWhichMaterialisProcured(String localPlantfromWhichMaterialisProcured) {
        this.localPlantfromWhichMaterialisProcured = localPlantfromWhichMaterialisProcured;
    }

    public String getLocalMatForManufPartNumber() {
        return localMatForManufPartNumber;
    }

    public void setLocalMatForManufPartNumber(String localMatForManufPartNumber) {
        this.localMatForManufPartNumber = localMatForManufPartNumber;
    }

    public String getLocalBlockedSourceofSupply() {
        return localBlockedSourceofSupply;
    }

    public void setLocalBlockedSourceofSupply(String localBlockedSourceofSupply) {
        this.localBlockedSourceofSupply = localBlockedSourceofSupply;
    }

    public String getLocalPurchasingOrganization() {
        return localPurchasingOrganization;
    }

    public void setLocalPurchasingOrganization(String localPurchasingOrganization) {
        this.localPurchasingOrganization = localPurchasingOrganization;
    }

    public String getLocalPurchasingDocumentCategory() {
        return localPurchasingDocumentCategory;
    }

    public void setLocalPurchasingDocumentCategory(String localPurchasingDocumentCategory) {
        this.localPurchasingDocumentCategory = localPurchasingDocumentCategory;
    }

    public String getLocalCategoryofSourceListRecord() {
        return localCategoryofSourceListRecord;
    }

    public void setLocalCategoryofSourceListRecord(String localCategoryofSourceListRecord) {
        this.localCategoryofSourceListRecord = localCategoryofSourceListRecord;
    }

    public String getLocalSourceListUsageinMaterialsPlanning() {
        return localSourceListUsageinMaterialsPlanning;
    }

    public void setLocalSourceListUsageinMaterialsPlanning(String localSourceListUsageinMaterialsPlanning) {
        this.localSourceListUsageinMaterialsPlanning = localSourceListUsageinMaterialsPlanning;
    }


}

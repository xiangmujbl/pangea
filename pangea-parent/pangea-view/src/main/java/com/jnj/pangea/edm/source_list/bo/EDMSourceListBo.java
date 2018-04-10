package com.jnj.pangea.edm.source_list.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMSourceListBo extends BaseBo {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String localNumberofSourceListRecord;
    private String localDateonWhichRecordWasCreated;
    private String localNameofPersonwhoCreatedtheObject;
    private String localSourceListRecordValidFrom;
    private String localSourceListRecordValidTo;
    private String localVendorAccountNumber;
    private String localFixedvendor;
    private String localAgreementNumber;
    private String localAgreementItem;
    private String localFixedOutlinePurchaseAgreementItem;
    private String localPlantfromWhichMaterialisProcured;
    private String localMaterialNumberCorrespondingtoManufacturerPartNumber;
    private String localBlockedSourceofSupply;
    private String localPurchasingOrganization;
    private String localPurchasingDocumentCategory;
    private String localCategoryofSourceListRecord;
    private String localSourceListUsageinMaterialsPlanning;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localPlant", this.localPlant)
                .add("localNumberofSourceListRecord", this.localNumberofSourceListRecord)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalNumberofSourceListRecord() {
        return this.localNumberofSourceListRecord;
    }

    public void setLocalNumberofSourceListRecord(String localNumberofSourceListRecord) {
        this.localNumberofSourceListRecord = localNumberofSourceListRecord;
    }

    public String getLocalDateonWhichRecordWasCreated() {
        return this.localDateonWhichRecordWasCreated;
    }

    public void setLocalDateonWhichRecordWasCreated(String localDateonWhichRecordWasCreated) {
        this.localDateonWhichRecordWasCreated = localDateonWhichRecordWasCreated;
    }

    public String getLocalNameofPersonwhoCreatedtheObject() {
        return this.localNameofPersonwhoCreatedtheObject;
    }

    public void setLocalNameofPersonwhoCreatedtheObject(String localNameofPersonwhoCreatedtheObject) {
        this.localNameofPersonwhoCreatedtheObject = localNameofPersonwhoCreatedtheObject;
    }

    public String getLocalSourceListRecordValidFrom() {
        return this.localSourceListRecordValidFrom;
    }

    public void setLocalSourceListRecordValidFrom(String localSourceListRecordValidFrom) {
        this.localSourceListRecordValidFrom = localSourceListRecordValidFrom;
    }

    public String getLocalSourceListRecordValidTo() {
        return this.localSourceListRecordValidTo;
    }

    public void setLocalSourceListRecordValidTo(String localSourceListRecordValidTo) {
        this.localSourceListRecordValidTo = localSourceListRecordValidTo;
    }

    public String getLocalVendorAccountNumber() {
        return this.localVendorAccountNumber;
    }

    public void setLocalVendorAccountNumber(String localVendorAccountNumber) {
        this.localVendorAccountNumber = localVendorAccountNumber;
    }

    public String getLocalFixedvendor() {
        return this.localFixedvendor;
    }

    public void setLocalFixedvendor(String localFixedvendor) {
        this.localFixedvendor = localFixedvendor;
    }

    public String getLocalAgreementNumber() {
        return this.localAgreementNumber;
    }

    public void setLocalAgreementNumber(String localAgreementNumber) {
        this.localAgreementNumber = localAgreementNumber;
    }

    public String getLocalAgreementItem() {
        return this.localAgreementItem;
    }

    public void setLocalAgreementItem(String localAgreementItem) {
        this.localAgreementItem = localAgreementItem;
    }

    public String getLocalFixedOutlinePurchaseAgreementItem() {
        return this.localFixedOutlinePurchaseAgreementItem;
    }

    public void setLocalFixedOutlinePurchaseAgreementItem(String localFixedOutlinePurchaseAgreementItem) {
        this.localFixedOutlinePurchaseAgreementItem = localFixedOutlinePurchaseAgreementItem;
    }

    public String getLocalPlantfromWhichMaterialisProcured() {
        return this.localPlantfromWhichMaterialisProcured;
    }

    public void setLocalPlantfromWhichMaterialisProcured(String localPlantfromWhichMaterialisProcured) {
        this.localPlantfromWhichMaterialisProcured = localPlantfromWhichMaterialisProcured;
    }

    public String getLocalMaterialNumberCorrespondingtoManufacturerPartNumber() {
        return this.localMaterialNumberCorrespondingtoManufacturerPartNumber;
    }

    public void setLocalMaterialNumberCorrespondingtoManufacturerPartNumber(String localMaterialNumberCorrespondingtoManufacturerPartNumber) {
        this.localMaterialNumberCorrespondingtoManufacturerPartNumber = localMaterialNumberCorrespondingtoManufacturerPartNumber;
    }

    public String getLocalBlockedSourceofSupply() {
        return this.localBlockedSourceofSupply;
    }

    public void setLocalBlockedSourceofSupply(String localBlockedSourceofSupply) {
        this.localBlockedSourceofSupply = localBlockedSourceofSupply;
    }

    public String getLocalPurchasingOrganization() {
        return this.localPurchasingOrganization;
    }

    public void setLocalPurchasingOrganization(String localPurchasingOrganization) {
        this.localPurchasingOrganization = localPurchasingOrganization;
    }

    public String getLocalPurchasingDocumentCategory() {
        return this.localPurchasingDocumentCategory;
    }

    public void setLocalPurchasingDocumentCategory(String localPurchasingDocumentCategory) {
        this.localPurchasingDocumentCategory = localPurchasingDocumentCategory;
    }

    public String getLocalCategoryofSourceListRecord() {
        return this.localCategoryofSourceListRecord;
    }

    public void setLocalCategoryofSourceListRecord(String localCategoryofSourceListRecord) {
        this.localCategoryofSourceListRecord = localCategoryofSourceListRecord;
    }

    public String getLocalSourceListUsageinMaterialsPlanning() {
        return this.localSourceListUsageinMaterialsPlanning;
    }

    public void setLocalSourceListUsageinMaterialsPlanning(String localSourceListUsageinMaterialsPlanning) {
        this.localSourceListUsageinMaterialsPlanning = localSourceListUsageinMaterialsPlanning;
    }

}

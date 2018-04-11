package com.jnj.pangea.edm.purchasing_info_record_v1_latam.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMPurchasingInfoRecordV1LatamBo extends BaseBo {

    private String sourceSystem;
    private String localPurchasingInfoRec;
    private String localvendor;
    private String localPurchasingOrg;
    private String infotype;
    private String localPlanPlant;
    private String localMaterialNumber;
    private String localBaseUnit;
    private String localMaterialGroup;
    private String localPurchOrgDataFlagDeletion;
    private String localGeneralDataFlagDeletion;
    private String localCreatedOnEine;
    private String localCreatedOnEina;
    private String localCreatedByEine;
    private String localCreatedByEina;
    private String localPurchasingGroup;
    private String localInfoShortText;
    private String localCurrencyKey;
    private String localMinimumQty;
    private String localStandardQty;
    private String localPlDelivTime;
    private String localVendorMatNo;
    private String localNumerator;
    private String localDenominator;
    private String localOrderUnit;
    private String localManufacturer;
    private String localTaxCode;
    private String localConfirmationControlKey;
    private String localPrDateCat;
    private String localIncoterms;
    private String localIncoterms2;
    private String localProductionVersion;
    private String localMaxQuantity;
    private String localRndingProfile;
    private String localNCMCode;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPurchasingInfoRec",this.localPurchasingInfoRec)
                .add("localvendor",this.localvendor)
                .add("localPurchasingOrg",this.localPurchasingOrg)
                .add("infotype",this.infotype)
                .add("localPlanPlant",this.localPlanPlant)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPurchasingInfoRec() {
        return this.localPurchasingInfoRec;
    }

    public void setLocalPurchasingInfoRec(String localPurchasingInfoRec) {
        this.localPurchasingInfoRec = localPurchasingInfoRec;
    }

    public String getLocalvendor() {
        return this.localvendor;
    }

    public void setLocalvendor(String localvendor) {
        this.localvendor = localvendor;
    }

    public String getLocalPurchasingOrg() {
        return this.localPurchasingOrg;
    }

    public void setLocalPurchasingOrg(String localPurchasingOrg) {
        this.localPurchasingOrg = localPurchasingOrg;
    }

    public String getInfotype() {
        return this.infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getLocalPlanPlant() {
        return this.localPlanPlant;
    }

    public void setLocalPlanPlant(String localPlanPlant) {
        this.localPlanPlant = localPlanPlant;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalBaseUnit() {
        return this.localBaseUnit;
    }

    public void setLocalBaseUnit(String localBaseUnit) {
        this.localBaseUnit = localBaseUnit;
    }

    public String getLocalMaterialGroup() {
        return this.localMaterialGroup;
    }

    public void setLocalMaterialGroup(String localMaterialGroup) {
        this.localMaterialGroup = localMaterialGroup;
    }

    public String getLocalPurchOrgDataFlagDeletion() {
        return this.localPurchOrgDataFlagDeletion;
    }

    public void setLocalPurchOrgDataFlagDeletion(String localPurchOrgDataFlagDeletion) {
        this.localPurchOrgDataFlagDeletion = localPurchOrgDataFlagDeletion;
    }

    public String getLocalGeneralDataFlagDeletion() {
        return this.localGeneralDataFlagDeletion;
    }

    public void setLocalGeneralDataFlagDeletion(String localGeneralDataFlagDeletion) {
        this.localGeneralDataFlagDeletion = localGeneralDataFlagDeletion;
    }

    public String getLocalCreatedOnEine() {
        return this.localCreatedOnEine;
    }

    public void setLocalCreatedOnEine(String localCreatedOnEine) {
        this.localCreatedOnEine = localCreatedOnEine;
    }

    public String getLocalCreatedOnEina() {
        return this.localCreatedOnEina;
    }

    public void setLocalCreatedOnEina(String localCreatedOnEina) {
        this.localCreatedOnEina = localCreatedOnEina;
    }

    public String getLocalCreatedByEine() {
        return this.localCreatedByEine;
    }

    public void setLocalCreatedByEine(String localCreatedByEine) {
        this.localCreatedByEine = localCreatedByEine;
    }

    public String getLocalCreatedByEina() {
        return this.localCreatedByEina;
    }

    public void setLocalCreatedByEina(String localCreatedByEina) {
        this.localCreatedByEina = localCreatedByEina;
    }

    public String getLocalPurchasingGroup() {
        return this.localPurchasingGroup;
    }

    public void setLocalPurchasingGroup(String localPurchasingGroup) {
        this.localPurchasingGroup = localPurchasingGroup;
    }

    public String getLocalInfoShortText() {
        return this.localInfoShortText;
    }

    public void setLocalInfoShortText(String localInfoShortText) {
        this.localInfoShortText = localInfoShortText;
    }

    public String getLocalCurrencyKey() {
        return this.localCurrencyKey;
    }

    public void setLocalCurrencyKey(String localCurrencyKey) {
        this.localCurrencyKey = localCurrencyKey;
    }

    public String getLocalMinimumQty() {
        return this.localMinimumQty;
    }

    public void setLocalMinimumQty(String localMinimumQty) {
        this.localMinimumQty = localMinimumQty;
    }

    public String getLocalStandardQty() {
        return this.localStandardQty;
    }

    public void setLocalStandardQty(String localStandardQty) {
        this.localStandardQty = localStandardQty;
    }

    public String getLocalPlDelivTime() {
        return this.localPlDelivTime;
    }

    public void setLocalPlDelivTime(String localPlDelivTime) {
        this.localPlDelivTime = localPlDelivTime;
    }

    public String getLocalVendorMatNo() {
        return this.localVendorMatNo;
    }

    public void setLocalVendorMatNo(String localVendorMatNo) {
        this.localVendorMatNo = localVendorMatNo;
    }

    public String getLocalNumerator() {
        return this.localNumerator;
    }

    public void setLocalNumerator(String localNumerator) {
        this.localNumerator = localNumerator;
    }

    public String getLocalDenominator() {
        return this.localDenominator;
    }

    public void setLocalDenominator(String localDenominator) {
        this.localDenominator = localDenominator;
    }

    public String getLocalOrderUnit() {
        return this.localOrderUnit;
    }

    public void setLocalOrderUnit(String localOrderUnit) {
        this.localOrderUnit = localOrderUnit;
    }

    public String getLocalManufacturer() {
        return this.localManufacturer;
    }

    public void setLocalManufacturer(String localManufacturer) {
        this.localManufacturer = localManufacturer;
    }

    public String getLocalTaxCode() {
        return this.localTaxCode;
    }

    public void setLocalTaxCode(String localTaxCode) {
        this.localTaxCode = localTaxCode;
    }

    public String getLocalConfirmationControlKey() {
        return this.localConfirmationControlKey;
    }

    public void setLocalConfirmationControlKey(String localConfirmationControlKey) {
        this.localConfirmationControlKey = localConfirmationControlKey;
    }

    public String getLocalPrDateCat() {
        return this.localPrDateCat;
    }

    public void setLocalPrDateCat(String localPrDateCat) {
        this.localPrDateCat = localPrDateCat;
    }

    public String getLocalIncoterms() {
        return this.localIncoterms;
    }

    public void setLocalIncoterms(String localIncoterms) {
        this.localIncoterms = localIncoterms;
    }

    public String getLocalIncoterms2() {
        return this.localIncoterms2;
    }

    public void setLocalIncoterms2(String localIncoterms2) {
        this.localIncoterms2 = localIncoterms2;
    }

    public String getLocalProductionVersion() {
        return this.localProductionVersion;
    }

    public void setLocalProductionVersion(String localProductionVersion) {
        this.localProductionVersion = localProductionVersion;
    }

    public String getLocalMaxQuantity() {
        return this.localMaxQuantity;
    }

    public void setLocalMaxQuantity(String localMaxQuantity) {
        this.localMaxQuantity = localMaxQuantity;
    }

    public String getLocalRndingProfile() {
        return this.localRndingProfile;
    }

    public void setLocalRndingProfile(String localRndingProfile) {
        this.localRndingProfile = localRndingProfile;
    }

    public String getLocalNCMCode() {
        return this.localNCMCode;
    }

    public void setLocalNCMCode(String localNCMCode) {
        this.localNCMCode = localNCMCode;
    }

}

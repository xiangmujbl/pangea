package com.jnj.pangea.edm.sales_order.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMSalesOrderBo extends BaseBo {

    private String sourceSystem;
    private String salesOrderNo;
    private String salesOrderItem;
    private String scheduleLineItem;
    private String localOrderCreateDt;
    private String localOrderDate;
    private String localValidFromDt;
    private String localValidToDt;
    private String localDocumentCateg;
    private String localOrderType;
    private String localOrderReason;
    private String localDeliveryBlock;
    private String localBillingBlock;
    private String localSalesOrg;
    private String localDistrChannel;
    private String localDivision;
    private String localSalesGroup;
    private String localSalesOffice;
    private String localRequestedDate;
    private String localCustomerPO;
    private String localSoldToParty;
    private String localShipToParty;
    private String localChangeDt;
    private String localMaterialNumber;
    private String localPlant;
    private String localItemCategory;
    private String localItemDlvRlvnt;
    private String localItemBillRlvnt;
    private String localRejReason;
    private String salesOrderQty;
    private String localSalesUnit;
    private String localNumtoBase;
    private String localDentoBase;
    private String localBillingBlockItem;
    private String localSDItemValue;
    private String localSDItemCurrency;
    private String localStorageLocation;
    private String localShippingPoint;
    private String localRoute;
    private String localScheduleLineDate;
    private String localSchLineQty;
    private String localSchLineConfimQty;
    private String localCustomerGroup;
    private String localPricingProcedure;
    private String localIncoTerms1;
    private String localIncoTerms2;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("salesOrderNo",this.salesOrderNo)
                .add("salesOrderItem",this.salesOrderItem)
                .add("scheduleLineItem",this.scheduleLineItem)
                .toJsonString();
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getSalesOrderItem() {
        return salesOrderItem;
    }

    public void setSalesOrderItem(String salesOrderItem) {
        this.salesOrderItem = salesOrderItem;
    }

    public String getScheduleLineItem() {
        return scheduleLineItem;
    }

    public void setScheduleLineItem(String scheduleLineItem) {
        this.scheduleLineItem = scheduleLineItem;
    }

    public String getLocalOrderCreateDt() {
        return localOrderCreateDt;
    }

    public void setLocalOrderCreateDt(String localOrderCreateDt) {
        this.localOrderCreateDt = localOrderCreateDt;
    }

    public String getLocalOrderDate() {
        return localOrderDate;
    }

    public void setLocalOrderDate(String localOrderDate) {
        this.localOrderDate = localOrderDate;
    }

    public String getLocalValidFromDt() {
        return localValidFromDt;
    }

    public void setLocalValidFromDt(String localValidFromDt) {
        this.localValidFromDt = localValidFromDt;
    }

    public String getLocalValidToDt() {
        return localValidToDt;
    }

    public void setLocalValidToDt(String localValidToDt) {
        this.localValidToDt = localValidToDt;
    }

    public String getLocalDocumentCateg() {
        return localDocumentCateg;
    }

    public void setLocalDocumentCateg(String localDocumentCateg) {
        this.localDocumentCateg = localDocumentCateg;
    }

    public String getLocalOrderType() {
        return localOrderType;
    }

    public void setLocalOrderType(String localOrderType) {
        this.localOrderType = localOrderType;
    }

    public String getLocalOrderReason() {
        return localOrderReason;
    }

    public void setLocalOrderReason(String localOrderReason) {
        this.localOrderReason = localOrderReason;
    }

    public String getLocalDeliveryBlock() {
        return localDeliveryBlock;
    }

    public void setLocalDeliveryBlock(String localDeliveryBlock) {
        this.localDeliveryBlock = localDeliveryBlock;
    }

    public String getLocalBillingBlock() {
        return localBillingBlock;
    }

    public void setLocalBillingBlock(String localBillingBlock) {
        this.localBillingBlock = localBillingBlock;
    }

    public String getLocalSalesOrg() {
        return localSalesOrg;
    }

    public void setLocalSalesOrg(String localSalesOrg) {
        this.localSalesOrg = localSalesOrg;
    }

    public String getLocalDistrChannel() {
        return localDistrChannel;
    }

    public void setLocalDistrChannel(String localDistrChannel) {
        this.localDistrChannel = localDistrChannel;
    }

    public String getLocalDivision() {
        return localDivision;
    }

    public void setLocalDivision(String localDivision) {
        this.localDivision = localDivision;
    }

    public String getLocalSalesGroup() {
        return localSalesGroup;
    }

    public void setLocalSalesGroup(String localSalesGroup) {
        this.localSalesGroup = localSalesGroup;
    }

    public String getLocalSalesOffice() {
        return localSalesOffice;
    }

    public void setLocalSalesOffice(String localSalesOffice) {
        this.localSalesOffice = localSalesOffice;
    }

    public String getLocalRequestedDate() {
        return localRequestedDate;
    }

    public void setLocalRequestedDate(String localRequestedDate) {
        this.localRequestedDate = localRequestedDate;
    }

    public String getLocalCustomerPO() {
        return localCustomerPO;
    }

    public void setLocalCustomerPO(String localCustomerPO) {
        this.localCustomerPO = localCustomerPO;
    }

    public String getLocalSoldToParty() {
        return localSoldToParty;
    }

    public void setLocalSoldToParty(String localSoldToParty) {
        this.localSoldToParty = localSoldToParty;
    }

    public String getLocalShipToParty() {
        return localShipToParty;
    }

    public void setLocalShipToParty(String localShipToParty) {
        this.localShipToParty = localShipToParty;
    }

    public String getLocalChangeDt() {
        return localChangeDt;
    }

    public void setLocalChangeDt(String localChangeDt) {
        this.localChangeDt = localChangeDt;
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

    public String getLocalItemCategory() {
        return localItemCategory;
    }

    public void setLocalItemCategory(String localItemCategory) {
        this.localItemCategory = localItemCategory;
    }

    public String getLocalItemDlvRlvnt() {
        return localItemDlvRlvnt;
    }

    public void setLocalItemDlvRlvnt(String localItemDlvRlvnt) {
        this.localItemDlvRlvnt = localItemDlvRlvnt;
    }

    public String getLocalItemBillRlvnt() {
        return localItemBillRlvnt;
    }

    public void setLocalItemBillRlvnt(String localItemBillRlvnt) {
        this.localItemBillRlvnt = localItemBillRlvnt;
    }

    public String getLocalRejReason() {
        return localRejReason;
    }

    public void setLocalRejReason(String localRejReason) {
        this.localRejReason = localRejReason;
    }

    public String getSalesOrderQty() {
        return salesOrderQty;
    }

    public void setSalesOrderQty(String salesOrderQty) {
        this.salesOrderQty = salesOrderQty;
    }

    public String getLocalSalesUnit() {
        return localSalesUnit;
    }

    public void setLocalSalesUnit(String localSalesUnit) {
        this.localSalesUnit = localSalesUnit;
    }

    public String getLocalNumtoBase() {
        return localNumtoBase;
    }

    public void setLocalNumtoBase(String localNumtoBase) {
        this.localNumtoBase = localNumtoBase;
    }

    public String getLocalDentoBase() {
        return localDentoBase;
    }

    public void setLocalDentoBase(String localDentoBase) {
        this.localDentoBase = localDentoBase;
    }

    public String getLocalBillingBlockItem() {
        return localBillingBlockItem;
    }

    public void setLocalBillingBlockItem(String localBillingBlockItem) {
        this.localBillingBlockItem = localBillingBlockItem;
    }

    public String getLocalSDItemValue() {
        return localSDItemValue;
    }

    public void setLocalSDItemValue(String localSDItemValue) {
        this.localSDItemValue = localSDItemValue;
    }

    public String getLocalSDItemCurrency() {
        return localSDItemCurrency;
    }

    public void setLocalSDItemCurrency(String localSDItemCurrency) {
        this.localSDItemCurrency = localSDItemCurrency;
    }

    public String getLocalStorageLocation() {
        return localStorageLocation;
    }

    public void setLocalStorageLocation(String localStorageLocation) {
        this.localStorageLocation = localStorageLocation;
    }

    public String getLocalShippingPoint() {
        return localShippingPoint;
    }

    public void setLocalShippingPoint(String localShippingPoint) {
        this.localShippingPoint = localShippingPoint;
    }

    public String getLocalRoute() {
        return localRoute;
    }

    public void setLocalRoute(String localRoute) {
        this.localRoute = localRoute;
    }

    public String getLocalScheduleLineDate() {
        return localScheduleLineDate;
    }

    public void setLocalScheduleLineDate(String localScheduleLineDate) {
        this.localScheduleLineDate = localScheduleLineDate;
    }

    public String getLocalSchLineQty() {
        return localSchLineQty;
    }

    public void setLocalSchLineQty(String localSchLineQty) {
        this.localSchLineQty = localSchLineQty;
    }

    public String getLocalSchLineConfimQty() {
        return localSchLineConfimQty;
    }

    public void setLocalSchLineConfimQty(String localSchLineConfimQty) {
        this.localSchLineConfimQty = localSchLineConfimQty;
    }

    public String getLocalCustomerGroup() {
        return localCustomerGroup;
    }

    public void setLocalCustomerGroup(String localCustomerGroup) {
        this.localCustomerGroup = localCustomerGroup;
    }

    public String getLocalPricingProcedure() {
        return localPricingProcedure;
    }

    public void setLocalPricingProcedure(String localPricingProcedure) {
        this.localPricingProcedure = localPricingProcedure;
    }

    public String getLocalIncoTerms1() {
        return localIncoTerms1;
    }

    public void setLocalIncoTerms1(String localIncoTerms1) {
        this.localIncoTerms1 = localIncoTerms1;
    }

    public String getLocalIncoTerms2() {
        return localIncoTerms2;
    }

    public void setLocalIncoTerms2(String localIncoTerms2) {
        this.localIncoTerms2 = localIncoTerms2;
    }
}

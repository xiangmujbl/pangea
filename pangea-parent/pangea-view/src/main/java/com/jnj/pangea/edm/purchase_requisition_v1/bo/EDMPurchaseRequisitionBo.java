package com.jnj.pangea.edm.purchase_requisition_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMPurchaseRequisitionBo extends BaseBo {

    private String sourceSystem;
    private String prNum;
    private String prLineNbr;
    private String plntCd;
    private String matlNum;
    private String prLineUomCd;
    private String prTypeCd;
    private String prCatCd;
    private String localControlInd;
    private String delInd;
    private String prStsCd;
    private String recCrtInd;
    private String prchsngGrpNum;
    private String crtByNm;
    private String chngOnDt;
    private String prchInfoDesc;
    private String mfrPartNum;
    private String slocCd;
    private String intrnlRefNum;
    private String locaalMaterialGroup;
    private String suplPlntCd;
    private String prLineQty;
    private String prRqstDt;
    private String needByDt;
    private String apprByDt;
    private String localPrGRLeadTimeDays;
    private String prLineCatCd;
    private String acctAsgnmtCatCd;
    private String localGRInd;
    private String localSupNum;
    private String localFixedVendor;
    private String prchsngOrgNum;
    private String poTypeCd;
    private String localAgreement;
    private String localAgreementItem;
    private String localInfoRecord;
    private String asgnSuplSrcInd;
    private String localQuotaArr;
    private String localQuotaArrItem;
    private String prMrpHrzn;
    private String bomNum;
    private String localPurchaseOrder;
    private String localItem;
    private String localPODate;
    private String localPOQuantity;
    private String prClseInd;
    private String localReservation;
    private String splStkInd;
    private String fxInd;
    private String localOrderUnit;
    private String localSubjToRelease;
    private String localBatch;
    private String localSpIndStckTfr;
    private String localProdVersion;
    private String localdelvAddrADRNR;
    private String localdelvAddrADRN2;
    private String localCustomer;
    private String supNum;
    private String localSCVendor;
    private String localCurrency;
    private String localOverallReqRel;
    private String localManufacturer;
    private String localExternalManuf;
    private String localPDT;
    private String localIncomplete;
    private String lineStsCd;
    private String blokInd;
    private String localBlockingText;
    private String localProcuringPlant;
    private String localIssStorLoc;
    private String localXSysPReqNo;
    private String localXSysPReqItem;
    private String localXSysItemCat;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem",this.sourceSystem)
                .add("prNum",this.prNum)
                .add("prLineNbr",this.prLineNbr)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPrNum() {
        return this.prNum;
    }

    public void setPrNum(String prNum) {
        this.prNum = prNum;
    }

    public String getPrLineNbr() {
        return this.prLineNbr;
    }

    public void setPrLineNbr(String prLineNbr) {
        this.prLineNbr = prLineNbr;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPrLineUomCd() {
        return this.prLineUomCd;
    }

    public void setPrLineUomCd(String prLineUomCd) {
        this.prLineUomCd = prLineUomCd;
    }

    public String getPrTypeCd() {
        return this.prTypeCd;
    }

    public void setPrTypeCd(String prTypeCd) {
        this.prTypeCd = prTypeCd;
    }

    public String getPrCatCd() {
        return this.prCatCd;
    }

    public void setPrCatCd(String prCatCd) {
        this.prCatCd = prCatCd;
    }

    public String getLocalControlInd() {
        return this.localControlInd;
    }

    public void setLocalControlInd(String localControlInd) {
        this.localControlInd = localControlInd;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getPrStsCd() {
        return this.prStsCd;
    }

    public void setPrStsCd(String prStsCd) {
        this.prStsCd = prStsCd;
    }

    public String getRecCrtInd() {
        return this.recCrtInd;
    }

    public void setRecCrtInd(String recCrtInd) {
        this.recCrtInd = recCrtInd;
    }

    public String getPrchsngGrpNum() {
        return this.prchsngGrpNum;
    }

    public void setPrchsngGrpNum(String prchsngGrpNum) {
        this.prchsngGrpNum = prchsngGrpNum;
    }

    public String getCrtByNm() {
        return this.crtByNm;
    }

    public void setCrtByNm(String crtByNm) {
        this.crtByNm = crtByNm;
    }

    public String getChngOnDt() {
        return this.chngOnDt;
    }

    public void setChngOnDt(String chngOnDt) {
        this.chngOnDt = chngOnDt;
    }

    public String getPrchInfoDesc() {
        return this.prchInfoDesc;
    }

    public void setPrchInfoDesc(String prchInfoDesc) {
        this.prchInfoDesc = prchInfoDesc;
    }



    public String getSlocCd() {
        return this.slocCd;
    }

    public void setSlocCd(String slocCd) {
        this.slocCd = slocCd;
    }

    public String getIntrnlRefNum() {
        return this.intrnlRefNum;
    }

    public void setIntrnlRefNum(String intrnlRefNum) {
        this.intrnlRefNum = intrnlRefNum;
    }

    public String getLocaalMaterialGroup() {
        return this.locaalMaterialGroup;
    }

    public void setLocaalMaterialGroup(String locaalMaterialGroup) {
        this.locaalMaterialGroup = locaalMaterialGroup;
    }

    public String getSuplPlntCd() {
        return this.suplPlntCd;
    }

    public void setSuplPlntCd(String suplPlntCd) {
        this.suplPlntCd = suplPlntCd;
    }

    public String getPrLineQty() {
        return this.prLineQty;
    }

    public void setPrLineQty(String prLineQty) {
        this.prLineQty = prLineQty;
    }

    public String getPrRqstDt() {
        return this.prRqstDt;
    }

    public void setPrRqstDt(String prRqstDt) {
        this.prRqstDt = prRqstDt;
    }

    public String getNeedByDt() {
        return this.needByDt;
    }

    public void setNeedByDt(String needByDt) {
        this.needByDt = needByDt;
    }

    public String getApprByDt() {
        return this.apprByDt;
    }

    public void setApprByDt(String apprByDt) {
        this.apprByDt = apprByDt;
    }

    public String getLocalPrGRLeadTimeDays() {
        return this.localPrGRLeadTimeDays;
    }

    public void setLocalPrGRLeadTimeDays(String localPrGRLeadTimeDays) {
        this.localPrGRLeadTimeDays = localPrGRLeadTimeDays;
    }

    public String getPrLineCatCd() {
        return this.prLineCatCd;
    }

    public void setPrLineCatCd(String prLineCatCd) {
        this.prLineCatCd = prLineCatCd;
    }

    public String getAcctAsgnmtCatCd() {
        return this.acctAsgnmtCatCd;
    }

    public void setAcctAsgnmtCatCd(String acctAsgnmtCatCd) {
        this.acctAsgnmtCatCd = acctAsgnmtCatCd;
    }

    public String getLocalGRInd() {
        return this.localGRInd;
    }

    public void setLocalGRInd(String localGRInd) {
        this.localGRInd = localGRInd;
    }

    public String getLocalSupNum() {
        return this.localSupNum;
    }

    public void setLocalSupNum(String localSupNum) {
        this.localSupNum = localSupNum;
    }

    public String getLocalFixedVendor() {
        return this.localFixedVendor;
    }

    public void setLocalFixedVendor(String localFixedVendor) {
        this.localFixedVendor = localFixedVendor;
    }

    public String getPrchsngOrgNum() {
        return this.prchsngOrgNum;
    }

    public void setPrchsngOrgNum(String prchsngOrgNum) {
        this.prchsngOrgNum = prchsngOrgNum;
    }

    public String getPoTypeCd() {
        return this.poTypeCd;
    }

    public void setPoTypeCd(String poTypeCd) {
        this.poTypeCd = poTypeCd;
    }

    public String getLocalAgreement() {
        return this.localAgreement;
    }

    public void setLocalAgreement(String localAgreement) {
        this.localAgreement = localAgreement;
    }

    public String getLocalAgreementItem() {
        return this.localAgreementItem;
    }

    public void setLocalAgreementItem(String localAgreementItem) {
        this.localAgreementItem = localAgreementItem;
    }

    public String getLocalInfoRecord() {
        return this.localInfoRecord;
    }

    public void setLocalInfoRecord(String localInfoRecord) {
        this.localInfoRecord = localInfoRecord;
    }

    public String getAsgnSuplSrcInd() {
        return this.asgnSuplSrcInd;
    }

    public void setAsgnSuplSrcInd(String asgnSuplSrcInd) {
        this.asgnSuplSrcInd = asgnSuplSrcInd;
    }

    public String getLocalQuotaArr() {
        return this.localQuotaArr;
    }

    public void setLocalQuotaArr(String localQuotaArr) {
        this.localQuotaArr = localQuotaArr;
    }

    public String getLocalQuotaArrItem() {
        return this.localQuotaArrItem;
    }

    public void setLocalQuotaArrItem(String localQuotaArrItem) {
        this.localQuotaArrItem = localQuotaArrItem;
    }

    public String getBomNum() {
        return this.bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getLocalPurchaseOrder() {
        return this.localPurchaseOrder;
    }

    public void setLocalPurchaseOrder(String localPurchaseOrder) {
        this.localPurchaseOrder = localPurchaseOrder;
    }

    public String getLocalItem() {
        return this.localItem;
    }

    public void setLocalItem(String localItem) {
        this.localItem = localItem;
    }

    public String getLocalPODate() {
        return this.localPODate;
    }

    public void setLocalPODate(String localPODate) {
        this.localPODate = localPODate;
    }

    public String getLocalPOQuantity() {
        return this.localPOQuantity;
    }

    public void setLocalPOQuantity(String localPOQuantity) {
        this.localPOQuantity = localPOQuantity;
    }

    public String getPrClseInd() {
        return this.prClseInd;
    }

    public void setPrClseInd(String prClseInd) {
        this.prClseInd = prClseInd;
    }

    public String getLocalReservation() {
        return this.localReservation;
    }

    public void setLocalReservation(String localReservation) {
        this.localReservation = localReservation;
    }

    public String getSplStkInd() {
        return this.splStkInd;
    }

    public void setSplStkInd(String splStkInd) {
        this.splStkInd = splStkInd;
    }

    public String getFxInd() {
        return this.fxInd;
    }

    public void setFxInd(String fxInd) {
        this.fxInd = fxInd;
    }

    public String getLocalOrderUnit() {
        return this.localOrderUnit;
    }

    public void setLocalOrderUnit(String localOrderUnit) {
        this.localOrderUnit = localOrderUnit;
    }

    public String getLocalSubjToRelease() {
        return this.localSubjToRelease;
    }

    public void setLocalSubjToRelease(String localSubjToRelease) {
        this.localSubjToRelease = localSubjToRelease;
    }

    public String getLocalBatch() {
        return this.localBatch;
    }

    public void setLocalBatch(String localBatch) {
        this.localBatch = localBatch;
    }

    public String getLocalSpIndStckTfr() {
        return this.localSpIndStckTfr;
    }

    public void setLocalSpIndStckTfr(String localSpIndStckTfr) {
        this.localSpIndStckTfr = localSpIndStckTfr;
    }

    public String getLocalProdVersion() {
        return this.localProdVersion;
    }

    public void setLocalProdVersion(String localProdVersion) {
        this.localProdVersion = localProdVersion;
    }

    public String getLocaldelvAddrADRNR() {
        return this.localdelvAddrADRNR;
    }

    public void setLocaldelvAddrADRNR(String localdelvAddrADRNR) {
        this.localdelvAddrADRNR = localdelvAddrADRNR;
    }

    public String getLocaldelvAddrADRN2() {
        return this.localdelvAddrADRN2;
    }

    public void setLocaldelvAddrADRN2(String localdelvAddrADRN2) {
        this.localdelvAddrADRN2 = localdelvAddrADRN2;
    }

    public String getLocalCustomer() {
        return this.localCustomer;
    }

    public void setLocalCustomer(String localCustomer) {
        this.localCustomer = localCustomer;
    }

    public String getSupNum() {
        return this.supNum;
    }

    public void setSupNum(String supNum) {
        this.supNum = supNum;
    }

    public String getLocalSCVendor() {
        return this.localSCVendor;
    }

    public void setLocalSCVendor(String localSCVendor) {
        this.localSCVendor = localSCVendor;
    }

    public String getLocalCurrency() {
        return this.localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getLocalOverallReqRel() {
        return this.localOverallReqRel;
    }

    public void setLocalOverallReqRel(String localOverallReqRel) {
        this.localOverallReqRel = localOverallReqRel;
    }

    public String getMfrPartNum() {
        return this.mfrPartNum;
    }

    public void setMfrPartNum(String mfrPartNum) {
        this.mfrPartNum = mfrPartNum;
    }

    public String getLocalManufacturer() {
        return this.localManufacturer;
    }

    public void setLocalManufacturer(String localManufacturer) {
        this.localManufacturer = localManufacturer;
    }

    public String getLocalExternalManuf() {
        return this.localExternalManuf;
    }

    public void setLocalExternalManuf(String localExternalManuf) {
        this.localExternalManuf = localExternalManuf;
    }

    public String getLocalPDT() {
        return this.localPDT;
    }

    public void setLocalPDT(String localPDT) {
        this.localPDT = localPDT;
    }

    public String getPrMrpHrzn() {
        return this.prMrpHrzn;
    }

    public void setPrMrpHrzn(String prMrpHrzn) {
        this.prMrpHrzn = prMrpHrzn;
    }

    public String getLocalIncomplete() {
        return this.localIncomplete;
    }

    public void setLocalIncomplete(String localIncomplete) {
        this.localIncomplete = localIncomplete;
    }

    public String getLineStsCd() {
        return this.lineStsCd;
    }

    public void setLineStsCd(String lineStsCd) {
        this.lineStsCd = lineStsCd;
    }

    public String getBlokInd() {
        return this.blokInd;
    }

    public void setBlokInd(String blokInd) {
        this.blokInd = blokInd;
    }

    public String getLocalBlockingText() {
        return this.localBlockingText;
    }

    public void setLocalBlockingText(String localBlockingText) {
        this.localBlockingText = localBlockingText;
    }

    public String getLocalProcuringPlant() {
        return this.localProcuringPlant;
    }

    public void setLocalProcuringPlant(String localProcuringPlant) {
        this.localProcuringPlant = localProcuringPlant;
    }

    public String getLocalIssStorLoc() {
        return this.localIssStorLoc;
    }

    public void setLocalIssStorLoc(String localIssStorLoc) {
        this.localIssStorLoc = localIssStorLoc;
    }

    public String getLocalXSysPReqNo() {
        return this.localXSysPReqNo;
    }

    public void setLocalXSysPReqNo(String localXSysPReqNo) {
        this.localXSysPReqNo = localXSysPReqNo;
    }

    public String getLocalXSysPReqItem() {
        return this.localXSysPReqItem;
    }

    public void setLocalXSysPReqItem(String localXSysPReqItem) {
        this.localXSysPReqItem = localXSysPReqItem;
    }

    public String getLocalXSysItemCat() {
        return this.localXSysItemCat;
    }

    public void setLocalXSysItemCat(String localXSysItemCat) {
        this.localXSysItemCat = localXSysItemCat;
    }

}

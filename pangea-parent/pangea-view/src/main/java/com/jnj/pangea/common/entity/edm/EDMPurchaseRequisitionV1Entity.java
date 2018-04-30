package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMPurchaseRequisitionV1Entity extends CommonEntity{

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


    public EDMPurchaseRequisitionV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setPrNum((String) map.get("prNum"));
        setPrLineNbr((String) map.get("prLineNbr"));
        setPlntCd((String) map.get("plntCd"));
        setMatlNum((String) map.get("matlNum"));
        setPrLineUomCd((String) map.get("prLineUomCd"));
        setPrTypeCd((String) map.get("prTypeCd"));
        setPrCatCd((String) map.get("prCatCd"));
        setLocalControlInd((String) map.get("localControlInd"));
        setDelInd((String) map.get("delInd"));
        setPrStsCd((String) map.get("prStsCd"));
        setRecCrtInd((String) map.get("recCrtInd"));
        setPrchsngGrpNum((String) map.get("prchsngGrpNum"));
        setCrtByNm((String) map.get("crtByNm"));
        setChngOnDt((String) map.get("chngOnDt"));
        setPrchInfoDesc((String) map.get("prchInfoDesc"));
        setMfrPartNum((String) map.get("mfrPartNum"));
        setSlocCd((String) map.get("slocCd"));
        setIntrnlRefNum((String) map.get("intrnlRefNum"));
        setLocaalMaterialGroup((String) map.get("locaalMaterialGroup"));
        setSuplPlntCd((String) map.get("suplPlntCd"));
        setPrLineQty((String) map.get("prLineQty"));
        setPrRqstDt((String) map.get("prRqstDt"));
        setNeedByDt((String) map.get("needByDt"));
        setApprByDt((String) map.get("apprByDt"));
        setLocalPrGRLeadTimeDays((String) map.get("localPrGRLeadTimeDays"));
        setPrLineCatCd((String) map.get("prLineCatCd"));
        setAcctAsgnmtCatCd((String) map.get("acctAsgnmtCatCd"));
        setLocalGRInd((String) map.get("localGRInd"));
        setLocalSupNum((String) map.get("localSupNum"));
        setLocalFixedVendor((String) map.get("localFixedVendor"));
        setPrchsngOrgNum((String) map.get("prchsngOrgNum"));
        setPoTypeCd((String) map.get("poTypeCd"));
        setLocalAgreement((String) map.get("localAgreement"));
        setLocalAgreementItem((String) map.get("localAgreementItem"));
        setLocalInfoRecord((String) map.get("localInfoRecord"));
        setAsgnSuplSrcInd((String) map.get("asgnSuplSrcInd"));
        setLocalQuotaArr((String) map.get("localQuotaArr"));
        setLocalQuotaArrItem((String) map.get("localQuotaArrItem"));
        setPrMrpHrzn((String) map.get("prMrpHrzn"));
        setBomNum((String) map.get("bomNum"));
        setLocalPurchaseOrder((String) map.get("localPurchaseOrder"));
        setLocalItem((String) map.get("localItem"));
        setLocalPODate((String) map.get("localPODate"));
        setLocalPOQuantity((String) map.get("localPOQuantity"));
        setPrClseInd((String) map.get("prClseInd"));
        setLocalReservation((String) map.get("localReservation"));
        setSplStkInd((String) map.get("splStkInd"));
        setFxInd((String) map.get("fxInd"));
        setLocalOrderUnit((String) map.get("localOrderUnit"));
        setLocalSubjToRelease((String) map.get("localSubjToRelease"));
        setLocalBatch((String) map.get("localBatch"));
        setLocalSpIndStckTfr((String) map.get("localSpIndStckTfr"));
        setLocalProdVersion((String) map.get("localProdVersion"));
        setLocaldelvAddrADRNR((String) map.get("localdelvAddrADRNR"));
        setLocaldelvAddrADRN2((String) map.get("localdelvAddrADRN2"));
        setLocalCustomer((String) map.get("localCustomer"));
        setSupNum((String) map.get("supNum"));
        setLocalSCVendor((String) map.get("localSCVendor"));
        setLocalCurrency((String) map.get("localCurrency"));
        setLocalOverallReqRel((String) map.get("localOverallReqRel"));
        setLocalManufacturer((String) map.get("localManufacturer"));
        setLocalExternalManuf((String) map.get("localExternalManuf"));
        setLocalPDT((String) map.get("localPDT"));
        setLocalIncomplete((String) map.get("localIncomplete"));
        setLineStsCd((String) map.get("lineStsCd"));
        setBlokInd((String) map.get("blokInd"));
        setLocalBlockingText((String) map.get("localBlockingText"));
        setLocalProcuringPlant((String) map.get("localProcuringPlant"));
        setLocalIssStorLoc((String) map.get("localIssStorLoc"));
        setLocalXSysPReqNo((String) map.get("localXSysPReqNo"));
        setLocalXSysPReqItem((String) map.get("localXSysPReqItem"));
        setLocalXSysItemCat((String) map.get("localXSysItemCat"));

    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPrNum() {
        return prNum;
    }

    public void setPrNum(String prNum) {
        this.prNum = prNum;
    }

    public String getPrLineNbr() {
        return prLineNbr;
    }

    public void setPrLineNbr(String prLineNbr) {
        this.prLineNbr = prLineNbr;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPrLineUomCd() {
        return prLineUomCd;
    }

    public void setPrLineUomCd(String prLineUomCd) {
        this.prLineUomCd = prLineUomCd;
    }

    public String getPrTypeCd() {
        return prTypeCd;
    }

    public void setPrTypeCd(String prTypeCd) {
        this.prTypeCd = prTypeCd;
    }

    public String getPrCatCd() {
        return prCatCd;
    }

    public void setPrCatCd(String prCatCd) {
        this.prCatCd = prCatCd;
    }

    public String getLocalControlInd() {
        return localControlInd;
    }

    public void setLocalControlInd(String localControlInd) {
        this.localControlInd = localControlInd;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getPrStsCd() {
        return prStsCd;
    }

    public void setPrStsCd(String prStsCd) {
        this.prStsCd = prStsCd;
    }

    public String getRecCrtInd() {
        return recCrtInd;
    }

    public void setRecCrtInd(String recCrtInd) {
        this.recCrtInd = recCrtInd;
    }

    public String getPrchsngGrpNum() {
        return prchsngGrpNum;
    }

    public void setPrchsngGrpNum(String prchsngGrpNum) {
        this.prchsngGrpNum = prchsngGrpNum;
    }

    public String getCrtByNm() {
        return crtByNm;
    }

    public void setCrtByNm(String crtByNm) {
        this.crtByNm = crtByNm;
    }

    public String getChngOnDt() {
        return chngOnDt;
    }

    public void setChngOnDt(String chngOnDt) {
        this.chngOnDt = chngOnDt;
    }

    public String getPrchInfoDesc() {
        return prchInfoDesc;
    }

    public void setPrchInfoDesc(String prchInfoDesc) {
        this.prchInfoDesc = prchInfoDesc;
    }

    public String getMfrPartNum() {
        return mfrPartNum;
    }

    public void setMfrPartNum(String mfrPartNum) {
        this.mfrPartNum = mfrPartNum;
    }

    public String getSlocCd() {
        return slocCd;
    }

    public void setSlocCd(String slocCd) {
        this.slocCd = slocCd;
    }

    public String getIntrnlRefNum() {
        return intrnlRefNum;
    }

    public void setIntrnlRefNum(String intrnlRefNum) {
        this.intrnlRefNum = intrnlRefNum;
    }

    public String getLocaalMaterialGroup() {
        return locaalMaterialGroup;
    }

    public void setLocaalMaterialGroup(String locaalMaterialGroup) {
        this.locaalMaterialGroup = locaalMaterialGroup;
    }

    public String getSuplPlntCd() {
        return suplPlntCd;
    }

    public void setSuplPlntCd(String suplPlntCd) {
        this.suplPlntCd = suplPlntCd;
    }

    public String getPrLineQty() {
        return prLineQty;
    }

    public void setPrLineQty(String prLineQty) {
        this.prLineQty = prLineQty;
    }

    public String getPrRqstDt() {
        return prRqstDt;
    }

    public void setPrRqstDt(String prRqstDt) {
        this.prRqstDt = prRqstDt;
    }

    public String getNeedByDt() {
        return needByDt;
    }

    public void setNeedByDt(String needByDt) {
        this.needByDt = needByDt;
    }

    public String getApprByDt() {
        return apprByDt;
    }

    public void setApprByDt(String apprByDt) {
        this.apprByDt = apprByDt;
    }

    public String getLocalPrGRLeadTimeDays() {
        return localPrGRLeadTimeDays;
    }

    public void setLocalPrGRLeadTimeDays(String localPrGRLeadTimeDays) {
        this.localPrGRLeadTimeDays = localPrGRLeadTimeDays;
    }

    public String getPrLineCatCd() {
        return prLineCatCd;
    }

    public void setPrLineCatCd(String prLineCatCd) {
        this.prLineCatCd = prLineCatCd;
    }

    public String getAcctAsgnmtCatCd() {
        return acctAsgnmtCatCd;
    }

    public void setAcctAsgnmtCatCd(String acctAsgnmtCatCd) {
        this.acctAsgnmtCatCd = acctAsgnmtCatCd;
    }

    public String getLocalGRInd() {
        return localGRInd;
    }

    public void setLocalGRInd(String localGRInd) {
        this.localGRInd = localGRInd;
    }

    public String getLocalSupNum() {
        return localSupNum;
    }

    public void setLocalSupNum(String localSupNum) {
        this.localSupNum = localSupNum;
    }

    public String getLocalFixedVendor() {
        return localFixedVendor;
    }

    public void setLocalFixedVendor(String localFixedVendor) {
        this.localFixedVendor = localFixedVendor;
    }

    public String getPrchsngOrgNum() {
        return prchsngOrgNum;
    }

    public void setPrchsngOrgNum(String prchsngOrgNum) {
        this.prchsngOrgNum = prchsngOrgNum;
    }

    public String getPoTypeCd() {
        return poTypeCd;
    }

    public void setPoTypeCd(String poTypeCd) {
        this.poTypeCd = poTypeCd;
    }

    public String getLocalAgreement() {
        return localAgreement;
    }

    public void setLocalAgreement(String localAgreement) {
        this.localAgreement = localAgreement;
    }

    public String getLocalAgreementItem() {
        return localAgreementItem;
    }

    public void setLocalAgreementItem(String localAgreementItem) {
        this.localAgreementItem = localAgreementItem;
    }

    public String getLocalInfoRecord() {
        return localInfoRecord;
    }

    public void setLocalInfoRecord(String localInfoRecord) {
        this.localInfoRecord = localInfoRecord;
    }

    public String getAsgnSuplSrcInd() {
        return asgnSuplSrcInd;
    }

    public void setAsgnSuplSrcInd(String asgnSuplSrcInd) {
        this.asgnSuplSrcInd = asgnSuplSrcInd;
    }

    public String getLocalQuotaArr() {
        return localQuotaArr;
    }

    public void setLocalQuotaArr(String localQuotaArr) {
        this.localQuotaArr = localQuotaArr;
    }

    public String getLocalQuotaArrItem() {
        return localQuotaArrItem;
    }

    public void setLocalQuotaArrItem(String localQuotaArrItem) {
        this.localQuotaArrItem = localQuotaArrItem;
    }

    public String getPrMrpHrzn() {
        return prMrpHrzn;
    }

    public void setPrMrpHrzn(String prMrpHrzn) {
        this.prMrpHrzn = prMrpHrzn;
    }

    public String getBomNum() {
        return bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getLocalPurchaseOrder() {
        return localPurchaseOrder;
    }

    public void setLocalPurchaseOrder(String localPurchaseOrder) {
        this.localPurchaseOrder = localPurchaseOrder;
    }

    public String getLocalItem() {
        return localItem;
    }

    public void setLocalItem(String localItem) {
        this.localItem = localItem;
    }

    public String getLocalPODate() {
        return localPODate;
    }

    public void setLocalPODate(String localPODate) {
        this.localPODate = localPODate;
    }

    public String getLocalPOQuantity() {
        return localPOQuantity;
    }

    public void setLocalPOQuantity(String localPOQuantity) {
        this.localPOQuantity = localPOQuantity;
    }

    public String getPrClseInd() {
        return prClseInd;
    }

    public void setPrClseInd(String prClseInd) {
        this.prClseInd = prClseInd;
    }

    public String getLocalReservation() {
        return localReservation;
    }

    public void setLocalReservation(String localReservation) {
        this.localReservation = localReservation;
    }

    public String getSplStkInd() {
        return splStkInd;
    }

    public void setSplStkInd(String splStkInd) {
        this.splStkInd = splStkInd;
    }

    public String getFxInd() {
        return fxInd;
    }

    public void setFxInd(String fxInd) {
        this.fxInd = fxInd;
    }

    public String getLocalOrderUnit() {
        return localOrderUnit;
    }

    public void setLocalOrderUnit(String localOrderUnit) {
        this.localOrderUnit = localOrderUnit;
    }

    public String getLocalSubjToRelease() {
        return localSubjToRelease;
    }

    public void setLocalSubjToRelease(String localSubjToRelease) {
        this.localSubjToRelease = localSubjToRelease;
    }

    public String getLocalBatch() {
        return localBatch;
    }

    public void setLocalBatch(String localBatch) {
        this.localBatch = localBatch;
    }

    public String getLocalSpIndStckTfr() {
        return localSpIndStckTfr;
    }

    public void setLocalSpIndStckTfr(String localSpIndStckTfr) {
        this.localSpIndStckTfr = localSpIndStckTfr;
    }

    public String getLocalProdVersion() {
        return localProdVersion;
    }

    public void setLocalProdVersion(String localProdVersion) {
        this.localProdVersion = localProdVersion;
    }

    public String getLocaldelvAddrADRNR() {
        return localdelvAddrADRNR;
    }

    public void setLocaldelvAddrADRNR(String localdelvAddrADRNR) {
        this.localdelvAddrADRNR = localdelvAddrADRNR;
    }

    public String getLocaldelvAddrADRN2() {
        return localdelvAddrADRN2;
    }

    public void setLocaldelvAddrADRN2(String localdelvAddrADRN2) {
        this.localdelvAddrADRN2 = localdelvAddrADRN2;
    }

    public String getLocalCustomer() {
        return localCustomer;
    }

    public void setLocalCustomer(String localCustomer) {
        this.localCustomer = localCustomer;
    }

    public String getSupNum() {
        return supNum;
    }

    public void setSupNum(String supNum) {
        this.supNum = supNum;
    }

    public String getLocalSCVendor() {
        return localSCVendor;
    }

    public void setLocalSCVendor(String localSCVendor) {
        this.localSCVendor = localSCVendor;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getLocalOverallReqRel() {
        return localOverallReqRel;
    }

    public void setLocalOverallReqRel(String localOverallReqRel) {
        this.localOverallReqRel = localOverallReqRel;
    }

    public String getLocalManufacturer() {
        return localManufacturer;
    }

    public void setLocalManufacturer(String localManufacturer) {
        this.localManufacturer = localManufacturer;
    }

    public String getLocalExternalManuf() {
        return localExternalManuf;
    }

    public void setLocalExternalManuf(String localExternalManuf) {
        this.localExternalManuf = localExternalManuf;
    }

    public String getLocalPDT() {
        return localPDT;
    }

    public void setLocalPDT(String localPDT) {
        this.localPDT = localPDT;
    }

    public String getLocalIncomplete() {
        return localIncomplete;
    }

    public void setLocalIncomplete(String localIncomplete) {
        this.localIncomplete = localIncomplete;
    }

    public String getLineStsCd() {
        return lineStsCd;
    }

    public void setLineStsCd(String lineStsCd) {
        this.lineStsCd = lineStsCd;
    }

    public String getBlokInd() {
        return blokInd;
    }

    public void setBlokInd(String blokInd) {
        this.blokInd = blokInd;
    }

    public String getLocalBlockingText() {
        return localBlockingText;
    }

    public void setLocalBlockingText(String localBlockingText) {
        this.localBlockingText = localBlockingText;
    }

    public String getLocalProcuringPlant() {
        return localProcuringPlant;
    }

    public void setLocalProcuringPlant(String localProcuringPlant) {
        this.localProcuringPlant = localProcuringPlant;
    }

    public String getLocalIssStorLoc() {
        return localIssStorLoc;
    }

    public void setLocalIssStorLoc(String localIssStorLoc) {
        this.localIssStorLoc = localIssStorLoc;
    }

    public String getLocalXSysPReqNo() {
        return localXSysPReqNo;
    }

    public void setLocalXSysPReqNo(String localXSysPReqNo) {
        this.localXSysPReqNo = localXSysPReqNo;
    }

    public String getLocalXSysPReqItem() {
        return localXSysPReqItem;
    }

    public void setLocalXSysPReqItem(String localXSysPReqItem) {
        this.localXSysPReqItem = localXSysPReqItem;
    }

    public String getLocalXSysItemCat() {
        return localXSysItemCat;
    }

    public void setLocalXSysItemCat(String localXSysItemCat) {
        this.localXSysItemCat = localXSysItemCat;
    }
}

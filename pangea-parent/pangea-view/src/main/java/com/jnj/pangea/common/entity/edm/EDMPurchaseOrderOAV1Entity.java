package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMPurchaseOrderOAV1Entity extends CommonEntity {

    private String sourceSystem;
    private String poNum;
    private String poLineNbr;
    private String evTypeCd;
    private String matlMvmttYr;
    private String matlMvmtNum;
    private String matlMvmtSeqNbr;
    private String delvSchedCntNbr;
    private String poCatTypeCd;
    private String poTypeCd;
    private String crtOnDt;
    private String supNum;
    private String prchsngOrgNum;
    private String prchsngGrpNum;
    private String prchsngCoCd;
    private String crncyCd;
    private String poDt;
    private String vldFromDt;
    private String vldToDt;
    private String suplVendNum;
    private String suplPlntCd;
    private String rlseCmpltInd;
    private String rlseDocInd;
    private String delInd;
    private String matlNum;
    private String plntCd;
    private String slocCd;
    private String intrnlRefNum;
    private String poLineQty;
    private String poUomCd;
    private String localNumerator;
    private String localDenominator;
    private String stkTypeCd;
    private String delvCmpltInd;
    private String lineItemTypeCd;
    private String acctAsgnmtCatCd;
    private String prinPrchAgmtNum;
    private String prinPrchAgmtLineNbr;
    private String localBaseUOM;
    private String delvAddrNum;
    private String localPDT;
    private String localSpecialStock;
    private String cnfrmCd;
    private String subcntrcInd;
    private String localdelvAddrNum;
    private String prDocId;
    private String prLineNbr;
    private String localBrazilianNCMCode;
    private String isuSlocCd;
    private String poHistCatCd;
    private String localMovementType;
    private String localPostingDate;
    private String recvEaQty;
    private String cnfrmSeqNbr;
    private String delvDt;
    private String cnfrmQty;
    private String mrpAdjQty;
    private String localCreationIndVendorConf;
    private String slsOrdrNum;
    private String slsOrdrLineNbr;
    private String vendBtchNum;
    private String localdelvDt;
    private String schdQty;
    private String recvQty;
    private String localPurchaseReq;
    private String localPurchaseReqItem;
    private String stkTfrRecvQty;
    private String localmrpAdjQty;
    private String grLeadTimeDays;
    private String custNum;

    public EDMPurchaseOrderOAV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String)map.get("sourceSystem"));
        setPoNum((String)map.get("poNum"));
        setPoLineNbr((String)map.get("poLineNbr"));
        setEvTypeCd((String)map.get("evTypeCd"));
        setMatlMvmttYr((String)map.get("matlMvmttYr"));
        setMatlMvmtNum((String)map.get("matlMvmtNum"));
        setMatlMvmtSeqNbr((String)map.get("matlMvmtSeqNbr"));
        setDelvSchedCntNbr((String)map.get("delvSchedCntNbr"));
        setPoCatTypeCd((String)map.get("poCatTypeCd"));
        setPoTypeCd((String)map.get("poTypeCd"));
        setCrtOnDt((String)map.get("crtOnDt"));
        setSupNum((String)map.get("supNum"));
        setPrchsngOrgNum((String)map.get("prchsngOrgNum"));
        setPrchsngGrpNum((String)map.get("prchsngGrpNum"));
        setPrchsngCoCd((String)map.get("prchsngCoCd"));
        setCrncyCd((String)map.get("crncyCd"));
        setPoDt((String)map.get("poDt"));
        setVldFromDt((String)map.get("vldFromDt"));
        setVldToDt((String)map.get("vldToDt"));
        setSuplVendNum((String)map.get("suplVendNum"));
        setSuplPlntCd((String)map.get("suplPlntCd"));
        setRlseCmpltInd((String)map.get("rlseCmpltInd"));
        setRlseDocInd((String)map.get("rlseDocInd"));
        setDelInd((String)map.get("delInd"));
        setMatlNum((String)map.get("matlNum"));
        setPlntCd((String)map.get("plntCd"));
        setSlocCd((String)map.get("slocCd"));
        setIntrnlRefNum((String)map.get("intrnlRefNum"));
        setPoLineQty((String)map.get("poLineQty"));
        setPoUomCd((String)map.get("poUomCd"));
        setLocalNumerator((String)map.get("localNumerator"));
        setLocalDenominator((String)map.get("localDenominator"));
        setStkTypeCd((String)map.get("stkTypeCd"));
        setDelvCmpltInd((String)map.get("delvCmpltInd"));
        setLineItemTypeCd((String)map.get("lineItemTypeCd"));
        setAcctAsgnmtCatCd((String)map.get("acctAsgnmtCatCd"));
        setPrinPrchAgmtNum((String)map.get("prinPrchAgmtNum"));
        setPrinPrchAgmtLineNbr((String)map.get("prinPrchAgmtLineNbr"));
        setLocalBaseUOM((String)map.get("localBaseUOM"));
        setDelvAddrNum((String)map.get("delvAddrNum"));
        setLocalPDT((String)map.get("localPDT"));
        setLocalSpecialStock((String)map.get("localSpecialStock"));
        setCnfrmCd((String)map.get("cnfrmCd"));
        setSubcntrcInd((String)map.get("subcntrcInd"));
        setLocaldelvAddrNum((String)map.get("localdelvAddrNum"));
        setPrDocId((String)map.get("prDocId"));
        setPrLineNbr((String)map.get("prLineNbr"));
        setLocalBrazilianNCMCode((String)map.get("localBrazilianNCMCode"));
        setIsuSlocCd((String)map.get("isuSlocCd"));
        setPoHistCatCd((String)map.get("poHistCatCd"));
        setLocalMovementType((String)map.get("localMovementType"));
        setLocalPostingDate((String)map.get("localPostingDate"));
        setRecvEaQty((String)map.get("recvEaQty"));
        setCnfrmSeqNbr((String)map.get("cnfrmSeqNbr"));
        setDelvDt((String)map.get("delvDt"));
        setCnfrmQty((String)map.get("cnfrmQty"));
        setMrpAdjQty((String)map.get("mrpAdjQty"));
        setLocalCreationIndVendorConf((String)map.get("localCreationIndVendorConf"));
        setSlsOrdrNum((String)map.get("slsOrdrNum"));
        setSlsOrdrLineNbr((String)map.get("slsOrdrLineNbr"));
        setVendBtchNum((String)map.get("vendBtchNum"));
        setLocaldelvDt((String)map.get("localdelvDt"));
        setSchdQty((String)map.get("schdQty"));
        setRecvQty((String)map.get("recvQty"));
        setLocalPurchaseReq((String)map.get("localPurchaseReq"));
        setLocalPurchaseReqItem((String)map.get("localPurchaseReqItem"));
        setStkTfrRecvQty((String)map.get("stkTfrRecvQty"));
        setLocalmrpAdjQty((String)map.get("localmrpAdjQty"));
        setGrLeadTimeDays((String)map.get("grLeadTimeDays"));
        setCustNum((String)map.get("custNum"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getPoLineNbr() {
        return poLineNbr;
    }

    public void setPoLineNbr(String poLineNbr) {
        this.poLineNbr = poLineNbr;
    }

    public String getEvTypeCd() {
        return evTypeCd;
    }

    public void setEvTypeCd(String evTypeCd) {
        this.evTypeCd = evTypeCd;
    }

    public String getMatlMvmttYr() {
        return matlMvmttYr;
    }

    public void setMatlMvmttYr(String matlMvmttYr) {
        this.matlMvmttYr = matlMvmttYr;
    }

    public String getMatlMvmtNum() {
        return matlMvmtNum;
    }

    public void setMatlMvmtNum(String matlMvmtNum) {
        this.matlMvmtNum = matlMvmtNum;
    }

    public String getMatlMvmtSeqNbr() {
        return matlMvmtSeqNbr;
    }

    public void setMatlMvmtSeqNbr(String matlMvmtSeqNbr) {
        this.matlMvmtSeqNbr = matlMvmtSeqNbr;
    }

    public String getDelvSchedCntNbr() {
        return delvSchedCntNbr;
    }

    public void setDelvSchedCntNbr(String delvSchedCntNbr) {
        this.delvSchedCntNbr = delvSchedCntNbr;
    }

    public String getPoCatTypeCd() {
        return poCatTypeCd;
    }

    public void setPoCatTypeCd(String poCatTypeCd) {
        this.poCatTypeCd = poCatTypeCd;
    }

    public String getPoTypeCd() {
        return poTypeCd;
    }

    public void setPoTypeCd(String poTypeCd) {
        this.poTypeCd = poTypeCd;
    }

    public String getCrtOnDt() {
        return crtOnDt;
    }

    public void setCrtOnDt(String crtOnDt) {
        this.crtOnDt = crtOnDt;
    }

    public String getSupNum() {
        return supNum;
    }

    public void setSupNum(String supNum) {
        this.supNum = supNum;
    }

    public String getPrchsngOrgNum() {
        return prchsngOrgNum;
    }

    public void setPrchsngOrgNum(String prchsngOrgNum) {
        this.prchsngOrgNum = prchsngOrgNum;
    }

    public String getPrchsngGrpNum() {
        return prchsngGrpNum;
    }

    public void setPrchsngGrpNum(String prchsngGrpNum) {
        this.prchsngGrpNum = prchsngGrpNum;
    }

    public String getPrchsngCoCd() {
        return prchsngCoCd;
    }

    public void setPrchsngCoCd(String prchsngCoCd) {
        this.prchsngCoCd = prchsngCoCd;
    }

    public String getCrncyCd() {
        return crncyCd;
    }

    public void setCrncyCd(String crncyCd) {
        this.crncyCd = crncyCd;
    }

    public String getPoDt() {
        return poDt;
    }

    public void setPoDt(String poDt) {
        this.poDt = poDt;
    }

    public String getVldFromDt() {
        return vldFromDt;
    }

    public void setVldFromDt(String vldFromDt) {
        this.vldFromDt = vldFromDt;
    }

    public String getVldToDt() {
        return vldToDt;
    }

    public void setVldToDt(String vldToDt) {
        this.vldToDt = vldToDt;
    }

    public String getSuplVendNum() {
        return suplVendNum;
    }

    public void setSuplVendNum(String suplVendNum) {
        this.suplVendNum = suplVendNum;
    }

    public String getSuplPlntCd() {
        return suplPlntCd;
    }

    public void setSuplPlntCd(String suplPlntCd) {
        this.suplPlntCd = suplPlntCd;
    }

    public String getRlseCmpltInd() {
        return rlseCmpltInd;
    }

    public void setRlseCmpltInd(String rlseCmpltInd) {
        this.rlseCmpltInd = rlseCmpltInd;
    }

    public String getRlseDocInd() {
        return rlseDocInd;
    }

    public void setRlseDocInd(String rlseDocInd) {
        this.rlseDocInd = rlseDocInd;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
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

    public String getPoLineQty() {
        return poLineQty;
    }

    public void setPoLineQty(String poLineQty) {
        this.poLineQty = poLineQty;
    }

    public String getPoUomCd() {
        return poUomCd;
    }

    public void setPoUomCd(String poUomCd) {
        this.poUomCd = poUomCd;
    }

    public String getLocalNumerator() {
        return localNumerator;
    }

    public void setLocalNumerator(String localNumerator) {
        this.localNumerator = localNumerator;
    }

    public String getLocalDenominator() {
        return localDenominator;
    }

    public void setLocalDenominator(String localDenominator) {
        this.localDenominator = localDenominator;
    }

    public String getStkTypeCd() {
        return stkTypeCd;
    }

    public void setStkTypeCd(String stkTypeCd) {
        this.stkTypeCd = stkTypeCd;
    }

    public String getDelvCmpltInd() {
        return delvCmpltInd;
    }

    public void setDelvCmpltInd(String delvCmpltInd) {
        this.delvCmpltInd = delvCmpltInd;
    }

    public String getLineItemTypeCd() {
        return lineItemTypeCd;
    }

    public void setLineItemTypeCd(String lineItemTypeCd) {
        this.lineItemTypeCd = lineItemTypeCd;
    }

    public String getAcctAsgnmtCatCd() {
        return acctAsgnmtCatCd;
    }

    public void setAcctAsgnmtCatCd(String acctAsgnmtCatCd) {
        this.acctAsgnmtCatCd = acctAsgnmtCatCd;
    }

    public String getPrinPrchAgmtNum() {
        return prinPrchAgmtNum;
    }

    public void setPrinPrchAgmtNum(String prinPrchAgmtNum) {
        this.prinPrchAgmtNum = prinPrchAgmtNum;
    }

    public String getPrinPrchAgmtLineNbr() {
        return prinPrchAgmtLineNbr;
    }

    public void setPrinPrchAgmtLineNbr(String prinPrchAgmtLineNbr) {
        this.prinPrchAgmtLineNbr = prinPrchAgmtLineNbr;
    }

    public String getLocalBaseUOM() {
        return localBaseUOM;
    }

    public void setLocalBaseUOM(String localBaseUOM) {
        this.localBaseUOM = localBaseUOM;
    }

    public String getDelvAddrNum() {
        return delvAddrNum;
    }

    public void setDelvAddrNum(String delvAddrNum) {
        this.delvAddrNum = delvAddrNum;
    }

    public String getLocalPDT() {
        return localPDT;
    }

    public void setLocalPDT(String localPDT) {
        this.localPDT = localPDT;
    }

    public String getLocalSpecialStock() {
        return localSpecialStock;
    }

    public void setLocalSpecialStock(String localSpecialStock) {
        this.localSpecialStock = localSpecialStock;
    }

    public String getCnfrmCd() {
        return cnfrmCd;
    }

    public void setCnfrmCd(String cnfrmCd) {
        this.cnfrmCd = cnfrmCd;
    }

    public String getSubcntrcInd() {
        return subcntrcInd;
    }

    public void setSubcntrcInd(String subcntrcInd) {
        this.subcntrcInd = subcntrcInd;
    }

    public String getLocaldelvAddrNum() {
        return localdelvAddrNum;
    }

    public void setLocaldelvAddrNum(String localdelvAddrNum) {
        this.localdelvAddrNum = localdelvAddrNum;
    }

    public String getPrDocId() {
        return prDocId;
    }

    public void setPrDocId(String prDocId) {
        this.prDocId = prDocId;
    }

    public String getPrLineNbr() {
        return prLineNbr;
    }

    public void setPrLineNbr(String prLineNbr) {
        this.prLineNbr = prLineNbr;
    }

    public String getLocalBrazilianNCMCode() {
        return localBrazilianNCMCode;
    }

    public void setLocalBrazilianNCMCode(String localBrazilianNCMCode) {
        this.localBrazilianNCMCode = localBrazilianNCMCode;
    }

    public String getIsuSlocCd() {
        return isuSlocCd;
    }

    public void setIsuSlocCd(String isuSlocCd) {
        this.isuSlocCd = isuSlocCd;
    }

    public String getPoHistCatCd() {
        return poHistCatCd;
    }

    public void setPoHistCatCd(String poHistCatCd) {
        this.poHistCatCd = poHistCatCd;
    }

    public String getLocalMovementType() {
        return localMovementType;
    }

    public void setLocalMovementType(String localMovementType) {
        this.localMovementType = localMovementType;
    }

    public String getLocalPostingDate() {
        return localPostingDate;
    }

    public void setLocalPostingDate(String localPostingDate) {
        this.localPostingDate = localPostingDate;
    }

    public String getRecvEaQty() {
        return recvEaQty;
    }

    public void setRecvEaQty(String recvEaQty) {
        this.recvEaQty = recvEaQty;
    }

    public String getCnfrmSeqNbr() {
        return cnfrmSeqNbr;
    }

    public void setCnfrmSeqNbr(String cnfrmSeqNbr) {
        this.cnfrmSeqNbr = cnfrmSeqNbr;
    }

    public String getDelvDt() {
        return delvDt;
    }

    public void setDelvDt(String delvDt) {
        this.delvDt = delvDt;
    }

    public String getCnfrmQty() {
        return cnfrmQty;
    }

    public void setCnfrmQty(String cnfrmQty) {
        this.cnfrmQty = cnfrmQty;
    }

    public String getMrpAdjQty() {
        return mrpAdjQty;
    }

    public void setMrpAdjQty(String mrpAdjQty) {
        this.mrpAdjQty = mrpAdjQty;
    }

    public String getLocalCreationIndVendorConf() {
        return localCreationIndVendorConf;
    }

    public void setLocalCreationIndVendorConf(String localCreationIndVendorConf) {
        this.localCreationIndVendorConf = localCreationIndVendorConf;
    }

    public String getSlsOrdrNum() {
        return slsOrdrNum;
    }

    public void setSlsOrdrNum(String slsOrdrNum) {
        this.slsOrdrNum = slsOrdrNum;
    }

    public String getSlsOrdrLineNbr() {
        return slsOrdrLineNbr;
    }

    public void setSlsOrdrLineNbr(String slsOrdrLineNbr) {
        this.slsOrdrLineNbr = slsOrdrLineNbr;
    }

    public String getVendBtchNum() {
        return vendBtchNum;
    }

    public void setVendBtchNum(String vendBtchNum) {
        this.vendBtchNum = vendBtchNum;
    }

    public String getLocaldelvDt() {
        return localdelvDt;
    }

    public void setLocaldelvDt(String localdelvDt) {
        this.localdelvDt = localdelvDt;
    }

    public String getSchdQty() {
        return schdQty;
    }

    public void setSchdQty(String schdQty) {
        this.schdQty = schdQty;
    }

    public String getRecvQty() {
        return recvQty;
    }

    public void setRecvQty(String recvQty) {
        this.recvQty = recvQty;
    }

    public String getLocalPurchaseReq() {
        return localPurchaseReq;
    }

    public void setLocalPurchaseReq(String localPurchaseReq) {
        this.localPurchaseReq = localPurchaseReq;
    }

    public String getLocalPurchaseReqItem() {
        return localPurchaseReqItem;
    }

    public void setLocalPurchaseReqItem(String localPurchaseReqItem) {
        this.localPurchaseReqItem = localPurchaseReqItem;
    }

    public String getStkTfrRecvQty() {
        return stkTfrRecvQty;
    }

    public void setStkTfrRecvQty(String stkTfrRecvQty) {
        this.stkTfrRecvQty = stkTfrRecvQty;
    }

    public String getLocalmrpAdjQty() {
        return localmrpAdjQty;
    }

    public void setLocalmrpAdjQty(String localmrpAdjQty) {
        this.localmrpAdjQty = localmrpAdjQty;
    }

    public String getGrLeadTimeDays() {
        return grLeadTimeDays;
    }

    public void setGrLeadTimeDays(String grLeadTimeDays) {
        this.grLeadTimeDays = grLeadTimeDays;
    }

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }
}

package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchase_order_oa_v1.bo.EDMPurchaseOrderOABo;


public class OMPGdmStockPurchaseOrderPreAggregServiceImpl implements ICommonService{

    private static OMPGdmStockPurchaseOrderPreAggregServiceImpl instance;

    public static OMPGdmStockPurchaseOrderPreAggregServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmStockPurchaseOrderPreAggregServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        EDMPurchaseOrderOABo purchaseOrderOABo = new EDMPurchaseOrderOABo();
        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        Float cnfrmQtySum = Float.valueOf(0);

        EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity = (EDMPurchaseOrderOAV1Entity) o;
        if(purchaseOrderOAV1Entity.getCnfrmQty() != null && !purchaseOrderOAV1Entity.getCnfrmQty().isEmpty()) {
            cnfrmQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getCnfrmQty().trim());
        }
        if(cnfrmQtySum <= 0 && isRecordInvalid(purchaseOrderOAV1Entity)) {
            return resultObjectSkip;
        }

        purchaseOrderOABo.setSourceSystem(purchaseOrderOAV1Entity.getSourceSystem());
        purchaseOrderOABo.setPoNum(purchaseOrderOAV1Entity.getPoNum());
        purchaseOrderOABo.setPoLineNbr(purchaseOrderOAV1Entity.getPoLineNbr());
        purchaseOrderOABo.setPoCatTypeCd(purchaseOrderOAV1Entity.getPoCatTypeCd());
        purchaseOrderOABo.setPoTypeCd(purchaseOrderOAV1Entity.getPoTypeCd());
        purchaseOrderOABo.setCrtOnDt(purchaseOrderOAV1Entity.getCrtOnDt());
        purchaseOrderOABo.setSupNum(purchaseOrderOAV1Entity.getSupNum());
        purchaseOrderOABo.setPrchsngOrgNum(purchaseOrderOAV1Entity.getPrchsngOrgNum());
        purchaseOrderOABo.setPrchsngGrpNum(purchaseOrderOAV1Entity.getPrchsngGrpNum());
        purchaseOrderOABo.setPrchsngCoCd(purchaseOrderOAV1Entity.getPrchsngCoCd());
        purchaseOrderOABo.setCrncyCd(purchaseOrderOAV1Entity.getCrncyCd());
        purchaseOrderOABo.setPoDt(purchaseOrderOAV1Entity.getPoDt());
        purchaseOrderOABo.setVldFromDt(purchaseOrderOAV1Entity.getVldFromDt());
        purchaseOrderOABo.setVldToDt(purchaseOrderOAV1Entity.getVldToDt());
        purchaseOrderOABo.setSuplVendNum(purchaseOrderOAV1Entity.getSuplVendNum());
        purchaseOrderOABo.setSuplPlntCd(purchaseOrderOAV1Entity.getSuplPlntCd());
        purchaseOrderOABo.setRlseCmpltInd(purchaseOrderOAV1Entity.getRlseCmpltInd());
        purchaseOrderOABo.setRlseDocInd(purchaseOrderOAV1Entity.getRlseDocInd());
        purchaseOrderOABo.setDelInd(purchaseOrderOAV1Entity.getDelInd());
        purchaseOrderOABo.setMatlNum(purchaseOrderOAV1Entity.getMatlNum());
        purchaseOrderOABo.setPlntCd(purchaseOrderOAV1Entity.getPlntCd());
        purchaseOrderOABo.setSlocCd(purchaseOrderOAV1Entity.getSlocCd());
        purchaseOrderOABo.setIntrnlRefNum(purchaseOrderOAV1Entity.getIntrnlRefNum());
        purchaseOrderOABo.setPoLineQty(purchaseOrderOAV1Entity.getPoLineQty());
        purchaseOrderOABo.setPoUomCd(purchaseOrderOAV1Entity.getPoUomCd());
        purchaseOrderOABo.setLocalNumerator(purchaseOrderOAV1Entity.getLocalNumerator());
        purchaseOrderOABo.setLocalDenominator(purchaseOrderOAV1Entity.getLocalDenominator());
        purchaseOrderOABo.setStkTypeCd(purchaseOrderOAV1Entity.getStkTypeCd());
        purchaseOrderOABo.setDelvCmpltInd(purchaseOrderOAV1Entity.getDelvCmpltInd());
        purchaseOrderOABo.setLineItemTypeCd(purchaseOrderOAV1Entity.getLineItemTypeCd());
        purchaseOrderOABo.setAcctAsgnmtCatCd(purchaseOrderOAV1Entity.getAcctAsgnmtCatCd());
        purchaseOrderOABo.setPrinPrchAgmtNum(purchaseOrderOAV1Entity.getPrinPrchAgmtNum());
        purchaseOrderOABo.setPrinPrchAgmtLineNbr(purchaseOrderOAV1Entity.getPrinPrchAgmtLineNbr());
        purchaseOrderOABo.setLocalBaseUOM(purchaseOrderOAV1Entity.getLocalBaseUOM());
        purchaseOrderOABo.setDelvAddrNum(purchaseOrderOAV1Entity.getDelvAddrNum());
        purchaseOrderOABo.setLocalPDT(purchaseOrderOAV1Entity.getLocalPDT());
        purchaseOrderOABo.setLocalSpecialStock(purchaseOrderOAV1Entity.getLocalSpecialStock());
        purchaseOrderOABo.setCnfrmCd(purchaseOrderOAV1Entity.getCnfrmCd());
        purchaseOrderOABo.setSubcntrcInd(purchaseOrderOAV1Entity.getSubcntrcInd());
        purchaseOrderOABo.setLocaldelvAddrNum(purchaseOrderOAV1Entity.getLocaldelvAddrNum());
        purchaseOrderOABo.setPrDocId(purchaseOrderOAV1Entity.getPrDocId());
        purchaseOrderOABo.setPrLineNbr(purchaseOrderOAV1Entity.getPrLineNbr());
        purchaseOrderOABo.setLocalBrazilianNCMCode(purchaseOrderOAV1Entity.getLocalBrazilianNCMCode());
        purchaseOrderOABo.setIsuSlocCd(purchaseOrderOAV1Entity.getIsuSlocCd());
        purchaseOrderOABo.setGrLeadTimeDays(purchaseOrderOAV1Entity.getGrLeadTimeDays());
        purchaseOrderOABo.setOutbDelvCmpltInd(purchaseOrderOAV1Entity.getOutbDelvCmpltInd());
        purchaseOrderOABo.setEvTypeCd(purchaseOrderOAV1Entity.getEvTypeCd());
        purchaseOrderOABo.setMatlMvmttYr(purchaseOrderOAV1Entity.getMatlMvmttYr());
        purchaseOrderOABo.setMatlMvmtNum(purchaseOrderOAV1Entity.getMatlMvmtNum());
        purchaseOrderOABo.setMatlMvmtSeqNbr(purchaseOrderOAV1Entity.getMatlMvmtSeqNbr());
        purchaseOrderOABo.setPoHistCatCd(purchaseOrderOAV1Entity.getPoHistCatCd());
        purchaseOrderOABo.setLocalMovementType(purchaseOrderOAV1Entity.getLocalMovementType());
        purchaseOrderOABo.setLocalPostingDate(purchaseOrderOAV1Entity.getLocalPostingDate());
        purchaseOrderOABo.setRecvEaQty(purchaseOrderOAV1Entity.getRecvEaQty());
        purchaseOrderOABo.setLocalSeqNumActAsgn(purchaseOrderOAV1Entity.getLocalSeqNumActAsgn());
        purchaseOrderOABo.setCnfrmSeqNbr(purchaseOrderOAV1Entity.getCnfrmSeqNbr());
        purchaseOrderOABo.setDelvDt(purchaseOrderOAV1Entity.getDelvDt());
        purchaseOrderOABo.setCnfrmQty(purchaseOrderOAV1Entity.getCnfrmQty());
        purchaseOrderOABo.setMrpAdjQty(purchaseOrderOAV1Entity.getMrpAdjQty());
        purchaseOrderOABo.setLocalCreationIndVendorConf(purchaseOrderOAV1Entity.getLocalCreationIndVendorConf());
        purchaseOrderOABo.setSlsOrdrNum(purchaseOrderOAV1Entity.getSlsOrdrNum());
        purchaseOrderOABo.setSlsOrdrLineNbr(purchaseOrderOAV1Entity.getSlsOrdrLineNbr());
        purchaseOrderOABo.setVendBtchNum(purchaseOrderOAV1Entity.getVendBtchNum());
        purchaseOrderOABo.setLocalConfCrtOnDt(purchaseOrderOAV1Entity.getLocalConfCrtOnDt());
        purchaseOrderOABo.setLocalSeqNumVendConf(purchaseOrderOAV1Entity.getLocalSeqNumVendConf());
        purchaseOrderOABo.setDelvSchedCntNbr(purchaseOrderOAV1Entity.getDelvSchedCntNbr());
        purchaseOrderOABo.setLocaldelvDt(purchaseOrderOAV1Entity.getLocaldelvDt());
        purchaseOrderOABo.setSchdQty(purchaseOrderOAV1Entity.getSchdQty());
        purchaseOrderOABo.setRecvQty(purchaseOrderOAV1Entity.getRecvQty());
        purchaseOrderOABo.setLocalPurchaseReq(purchaseOrderOAV1Entity.getLocalPurchaseReq());
        purchaseOrderOABo.setLocalPurchaseReqItem(purchaseOrderOAV1Entity.getLocalPurchaseReqItem());
        purchaseOrderOABo.setStkTfrRecvQty(purchaseOrderOAV1Entity.getStkTfrRecvQty());
        purchaseOrderOABo.setLocalmrpAdjQty(purchaseOrderOAV1Entity.getLocalmrpAdjQty());
        purchaseOrderOABo.setCustNum(purchaseOrderOAV1Entity.getCustNum());

        resultObject.setBaseBo(purchaseOrderOABo);

        return resultObject;
    }

    private boolean isRecordInvalid(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if(purchaseOrderOAV1Entity.getEvTypeCd() != null && !purchaseOrderOAV1Entity.getEvTypeCd().isEmpty()){
            return !purchaseOrderOAV1Entity.getEvTypeCd().equals("1");
        } else{
            return true;
        }
    }

}

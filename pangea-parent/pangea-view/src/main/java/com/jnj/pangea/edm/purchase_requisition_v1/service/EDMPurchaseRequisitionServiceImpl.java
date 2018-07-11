package com.jnj.pangea.edm.purchase_requisition_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.EbanEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchase_requisition_v1.bo.EDMPurchaseRequisitionBo;
import org.apache.commons.lang.StringUtils;

public class EDMPurchaseRequisitionServiceImpl implements ICommonService {

    private static EDMPurchaseRequisitionServiceImpl instance;

    public static EDMPurchaseRequisitionServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPurchaseRequisitionServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EbanEntity ebanEntity = (EbanEntity) o;

        EDMPurchaseRequisitionBo purchaseRequisitionV1Bo = new EDMPurchaseRequisitionBo();
        if (ebanEntity == null) {
            return resultObject;
        }

        //rules T1
        EDMSourceSystemV1Entity eDMSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != eDMSourceSystemV1Entity) {
            purchaseRequisitionV1Bo.setSourceSystem(eDMSourceSystemV1Entity.getSourceSystem());
        }

        //rules N1
        purchaseRequisitionV1Bo.setPrStsCd(ebanEntity.getStatu());

        //rules N2
        String loekz = ebanEntity.getLoekz();
        if (StringUtils.isEmpty(StringUtils.trim(loekz))) {
            purchaseRequisitionV1Bo.setDelInd(ebanEntity.getLoekz());

            purchaseRequisitionV1Bo.setPrNum(ebanEntity.getBanfn());
            purchaseRequisitionV1Bo.setPrLineNbr(ebanEntity.getBnfpo());
            purchaseRequisitionV1Bo.setPlntCd(ebanEntity.getWerks());
            purchaseRequisitionV1Bo.setMatlNum(ebanEntity.getMatnr());
            purchaseRequisitionV1Bo.setPrLineUomCd(ebanEntity.getMeins());
            purchaseRequisitionV1Bo.setPrTypeCd(ebanEntity.getBsart());
            purchaseRequisitionV1Bo.setPrCatCd(ebanEntity.getBstyp());
            purchaseRequisitionV1Bo.setLocalControlInd(ebanEntity.getBsakz());
            purchaseRequisitionV1Bo.setRecCrtInd(ebanEntity.getEstkz());
            purchaseRequisitionV1Bo.setPrchsngGrpNum(ebanEntity.getEkgrp());
            purchaseRequisitionV1Bo.setCrtByNm(ebanEntity.getErnam());
            purchaseRequisitionV1Bo.setChngOnDt(ebanEntity.getErdat());
            purchaseRequisitionV1Bo.setPrchInfoDesc(ebanEntity.getTxz01());
            purchaseRequisitionV1Bo.setSlocCd(ebanEntity.getLgort());
            purchaseRequisitionV1Bo.setIntrnlRefNum(ebanEntity.getBednr());
            purchaseRequisitionV1Bo.setLocaalMaterialGroup(ebanEntity.getMatkl());
            purchaseRequisitionV1Bo.setSuplPlntCd(ebanEntity.getReswk());
            purchaseRequisitionV1Bo.setPrLineQty(ebanEntity.getMenge());
            purchaseRequisitionV1Bo.setPrRqstDt(ebanEntity.getBadat());
            purchaseRequisitionV1Bo.setNeedByDt(ebanEntity.getLfdat());
            purchaseRequisitionV1Bo.setApprByDt(ebanEntity.getFrgdt());
            purchaseRequisitionV1Bo.setLocalPrGRLeadTimeDays(ebanEntity.getWebaz());
            purchaseRequisitionV1Bo.setPrLineCatCd(ebanEntity.getPstyp());
            purchaseRequisitionV1Bo.setAcctAsgnmtCatCd(ebanEntity.getKnttp());
            purchaseRequisitionV1Bo.setLocalGRInd(ebanEntity.getWepos());
            purchaseRequisitionV1Bo.setLocalSupNum(ebanEntity.getLifnr());
            purchaseRequisitionV1Bo.setLocalFixedVendor(ebanEntity.getFlief());
            purchaseRequisitionV1Bo.setPrchsngOrgNum(ebanEntity.getEkorg());
            purchaseRequisitionV1Bo.setPoTypeCd(ebanEntity.getVrtyp());
            purchaseRequisitionV1Bo.setLocalAgreement(ebanEntity.getKonnr());
            purchaseRequisitionV1Bo.setLocalAgreementItem(ebanEntity.getKtpnr());
            purchaseRequisitionV1Bo.setLocalInfoRecord(ebanEntity.getInfnr());
            purchaseRequisitionV1Bo.setAsgnSuplSrcInd(ebanEntity.getZugba());
            purchaseRequisitionV1Bo.setLocalQuotaArr(ebanEntity.getQunum());
            purchaseRequisitionV1Bo.setLocalQuotaArrItem(ebanEntity.getQupos());
            purchaseRequisitionV1Bo.setBomNum(ebanEntity.getSernr());
            purchaseRequisitionV1Bo.setLocalPurchaseOrder(ebanEntity.getEbeln());
            purchaseRequisitionV1Bo.setLocalItem(ebanEntity.getEbelp());
            purchaseRequisitionV1Bo.setLocalPODate(ebanEntity.getBedat());
            purchaseRequisitionV1Bo.setLocalPOQuantity(ebanEntity.getBsmng());
            purchaseRequisitionV1Bo.setPrClseInd(ebanEntity.getEbakz());
            purchaseRequisitionV1Bo.setLocalReservation(ebanEntity.getRsnum());
            purchaseRequisitionV1Bo.setSplStkInd(ebanEntity.getSobkz());
            purchaseRequisitionV1Bo.setFxInd(ebanEntity.getFixkz());
            purchaseRequisitionV1Bo.setLocalOrderUnit(ebanEntity.getBmein());
            purchaseRequisitionV1Bo.setLocalSubjToRelease(ebanEntity.getFrgrl());
            purchaseRequisitionV1Bo.setLocalBatch(ebanEntity.getCharg());
            purchaseRequisitionV1Bo.setLocalSpIndStckTfr(ebanEntity.getUmsok());
            purchaseRequisitionV1Bo.setLocalProdVersion(ebanEntity.getVerid());
            purchaseRequisitionV1Bo.setLocaldelvAddrADRNR(ebanEntity.getAdrnr());
            purchaseRequisitionV1Bo.setLocaldelvAddrADRN2(ebanEntity.getAdrn2());
            purchaseRequisitionV1Bo.setLocalCustomer(ebanEntity.getKunnr());
            purchaseRequisitionV1Bo.setSupNum(ebanEntity.getEmlif());
            purchaseRequisitionV1Bo.setLocalSCVendor(ebanEntity.getLblkz());
            purchaseRequisitionV1Bo.setLocalCurrency(ebanEntity.getWaers());
            purchaseRequisitionV1Bo.setLocalOverallReqRel(ebanEntity.getGsfrg());
            purchaseRequisitionV1Bo.setLocalManufacturer(ebanEntity.getMfrnr());
            purchaseRequisitionV1Bo.setLocalExternalManuf(ebanEntity.getEmnfr());
            purchaseRequisitionV1Bo.setLocalPDT(ebanEntity.getPlifz());
            purchaseRequisitionV1Bo.setLocalIncomplete(ebanEntity.getMemory());
            purchaseRequisitionV1Bo.setLineStsCd(ebanEntity.getBanpr());
            purchaseRequisitionV1Bo.setBlokInd(ebanEntity.getBlckd());
            purchaseRequisitionV1Bo.setLocalBlockingText(ebanEntity.getBlckt());
            purchaseRequisitionV1Bo.setLocalProcuringPlant(ebanEntity.getBeswk());
            purchaseRequisitionV1Bo.setLocalIssStorLoc(ebanEntity.getReslo());
            purchaseRequisitionV1Bo.setLocalXSysPReqNo(ebanEntity.getBanfnCs());
            purchaseRequisitionV1Bo.setLocalXSysPReqItem(ebanEntity.getBnfpoCs());
            purchaseRequisitionV1Bo.setLocalXSysItemCat(ebanEntity.getItemCs());
            purchaseRequisitionV1Bo.setMfrPartNum(ebanEntity.getMfrpn());
            purchaseRequisitionV1Bo.setPrMrpHrzn(ebanEntity.getDispo());

            resultObject.setBaseBo(purchaseRequisitionV1Bo);
        }
        return resultObject;
    }
}

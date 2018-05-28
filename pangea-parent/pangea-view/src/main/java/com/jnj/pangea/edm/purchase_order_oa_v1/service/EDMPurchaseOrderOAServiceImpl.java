package com.jnj.pangea.edm.purchase_order_oa_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.*;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.edm.purchase_order_oa_v1.bo.EDMPurchaseOrderOABo;

import java.util.ArrayList;
import java.util.List;

public class EDMPurchaseOrderOAServiceImpl{

    private static EDMPurchaseOrderOAServiceImpl instance;

    public static EDMPurchaseOrderOAServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPurchaseOrderOAServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    EkpoDaoImpl ekpoDao = EkpoDaoImpl.getInstance();
    EkbeDaoImpl ekbeDao = EkbeDaoImpl.getInstance();
    EkesDaoImpl ekesDao = EkesDaoImpl.getInstance();
    EketDaoImpl eketDao = EketDaoImpl.getInstance();
    EkpvDaoImpl ekpvDao = EkpvDaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjects = new ArrayList<>();
        EkkoEntity ekkoEntity = (EkkoEntity) o;
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();

        //T1
        if(sourceSystemV1Entity != null) {
            String sourceSystem = sourceSystemV1Entity.getSourceSystem();
            //N1
            List<EkpoEntity> ekpoEntities = ekpoDao.getEkpoEntitiesByEbeln(ekkoEntity.getEbeln());

            if(ekpoEntities != null) {
                for (EkpoEntity ekpoEntity : ekpoEntities) {
                    //N2
                    String ebeln = ekpoEntity.getEbeln();
                    String ebelp = ekpoEntity.getEbelp();
                    List<EkbeEntity> ekbeEntities = ekbeDao.getEkbeEntitiesByEbelnAndEbelp(ebeln, ebelp);
                    List<EkesEntity> ekesEntities = ekesDao.getEkesEntitiesByEbelnAndEbelp(ebeln, ebelp);
                    List<EketEntity> eketEntities = eketDao.getEketEntitiesByEbelnAndEbelp(ebeln, ebelp);
                    List<EkpvEntity> ekpvEntities = ekpvDao.getEkpvEntitiesByEbelnAndEbelp(ebeln, ebelp);

                    if(ekbeEntities != null) {
                        for (EkbeEntity ekbeEntity : ekbeEntities) {
                            resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, ekbeEntity, null, null, null));
                        }
                    }

                    if(ekesEntities != null) {
                        for (EkesEntity ekesEntity : ekesEntities) {
                            resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, ekesEntity, null, null));
                        }
                    }

                    if(eketEntities != null) {
                        for (EketEntity eketEntity : eketEntities) {
                            resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, null, eketEntity, null));
                        }
                    }

                    if(ekpvEntities != null) {
                        for (EkpvEntity ekpvEntity : ekpvEntities) {
                            resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, null, null, ekpvEntity));
                        }
                    }
                }
            }
        }
        return resultObjects;
    }

    private ResultObject setObjectByEntity(String sourceSystem, EkkoEntity ekkoEntity, EkpoEntity ekpoEntity, EkbeEntity ekbeEntity, EkesEntity ekesEntity, EketEntity eketEntity, EkpvEntity ekpvEntity){
        ResultObject resultObject = new ResultObject();
        EDMPurchaseOrderOABo purchaseOrderOABo = new EDMPurchaseOrderOABo();


        purchaseOrderOABo.setSourceSystem(sourceSystem);
        purchaseOrderOABo.setPoNum(ekkoEntity.getEbeln());
        purchaseOrderOABo.setPoLineNbr(ekpoEntity.getEbelp());
        purchaseOrderOABo.setPoCatTypeCd(ekkoEntity.getBstyp());
        purchaseOrderOABo.setPoTypeCd(ekkoEntity.getBsart());
        purchaseOrderOABo.setCrtOnDt(ekkoEntity.getAedat());
        purchaseOrderOABo.setSupNum(ekkoEntity.getLifnr());
        purchaseOrderOABo.setPrchsngOrgNum(ekkoEntity.getEkorg());
        purchaseOrderOABo.setPrchsngGrpNum(ekkoEntity.getEkgrp());
        purchaseOrderOABo.setPrchsngCoCd(ekkoEntity.getBukrs());
        purchaseOrderOABo.setCrncyCd(ekkoEntity.getWaers());
        purchaseOrderOABo.setPoDt(ekkoEntity.getBedat());
        purchaseOrderOABo.setVldFromDt(ekkoEntity.getKdatb());
        purchaseOrderOABo.setVldToDt(ekkoEntity.getKdate());
        purchaseOrderOABo.setSuplVendNum(ekkoEntity.getLlief());
        purchaseOrderOABo.setSuplPlntCd(ekkoEntity.getReswk());
        purchaseOrderOABo.setRlseCmpltInd(ekkoEntity.getFrgrl());
        purchaseOrderOABo.setRlseDocInd(ekkoEntity.getLphis());
        purchaseOrderOABo.setDelInd(ekpoEntity.getLoekz());
        purchaseOrderOABo.setMatlNum(ekpoEntity.getMatnr());
        purchaseOrderOABo.setPlntCd(ekpoEntity.getWerks());
        purchaseOrderOABo.setSlocCd(ekpoEntity.getLgort());
        purchaseOrderOABo.setIntrnlRefNum(ekpoEntity.getBednr());
        purchaseOrderOABo.setPoLineQty(ekpoEntity.getMenge());
        purchaseOrderOABo.setPoUomCd(ekpoEntity.getMeins());
        purchaseOrderOABo.setLocalNumerator(ekpoEntity.getUmrez());
        purchaseOrderOABo.setLocalDenominator(ekpoEntity.getUmren());
        purchaseOrderOABo.setStkTypeCd(ekpoEntity.getInsmk());
        purchaseOrderOABo.setDelvCmpltInd(ekpoEntity.getElikz());
        purchaseOrderOABo.setLineItemTypeCd(ekpoEntity.getPstyp());
        purchaseOrderOABo.setAcctAsgnmtCatCd(ekpoEntity.getKnttp());
        purchaseOrderOABo.setPrinPrchAgmtNum(ekpoEntity.getKonnr());
        purchaseOrderOABo.setPrinPrchAgmtLineNbr(ekpoEntity.getKtpnr());
        purchaseOrderOABo.setLocalBaseUOM(ekpoEntity.getLmein());
        purchaseOrderOABo.setDelvAddrNum(ekpoEntity.getAdrnr());
        purchaseOrderOABo.setLocalPDT(ekpoEntity.getPlifz());
        purchaseOrderOABo.setLocalSpecialStock(ekpoEntity.getSobkz());
        purchaseOrderOABo.setCnfrmCd(ekpoEntity.getBstae());
        purchaseOrderOABo.setSubcntrcInd(ekpoEntity.getLblkz());
        purchaseOrderOABo.setLocaldelvAddrNum(ekpoEntity.getAdrn2());
        purchaseOrderOABo.setPrDocId(ekpoEntity.getBanfn());
        purchaseOrderOABo.setPrLineNbr(ekpoEntity.getBnfpo());
        purchaseOrderOABo.setLocalBrazilianNCMCode(ekpoEntity.getJ1bnbm());
        purchaseOrderOABo.setIsuSlocCd(ekpoEntity.getReslo());
        purchaseOrderOABo.setGrLeadTimeDays(ekpoEntity.getWebaz());
        purchaseOrderOABo.setOutbDelvCmpltInd(ekpoEntity.getEglkz());

        if(ekbeEntity != null) {
            purchaseOrderOABo.setEvTypeCd(ekbeEntity.getVgabe());
            purchaseOrderOABo.setMatlMvmttYr(ekbeEntity.getGjahr());
            purchaseOrderOABo.setMatlMvmtNum(ekbeEntity.getBelnr());
            purchaseOrderOABo.setMatlMvmtSeqNbr(ekbeEntity.getBuzei());
            purchaseOrderOABo.setPoHistCatCd(ekbeEntity.getBewtp());
            purchaseOrderOABo.setLocalMovementType(ekbeEntity.getBwart());
            purchaseOrderOABo.setLocalPostingDate(ekbeEntity.getBudat());
            purchaseOrderOABo.setRecvEaQty(ekbeEntity.getMenge());
            purchaseOrderOABo.setLocalSeqNumActAsgn(ekbeEntity.getZekkn());
        }
        else {
            purchaseOrderOABo.setEvTypeCd("");
            purchaseOrderOABo.setMatlMvmttYr("");
            purchaseOrderOABo.setMatlMvmtNum("");
            purchaseOrderOABo.setMatlMvmtSeqNbr("");
            purchaseOrderOABo.setPoHistCatCd("");
            purchaseOrderOABo.setLocalMovementType("");
            purchaseOrderOABo.setLocalPostingDate("");
            purchaseOrderOABo.setRecvEaQty("");
            purchaseOrderOABo.setLocalSeqNumActAsgn("");
        }

        if(ekesEntity != null) {
            purchaseOrderOABo.setCnfrmSeqNbr(ekesEntity.getEbtyp());
            purchaseOrderOABo.setDelvDt(ekesEntity.getEindt());
            purchaseOrderOABo.setCnfrmQty(ekesEntity.getMenge());
            purchaseOrderOABo.setMrpAdjQty(ekesEntity.getDabmg());
            purchaseOrderOABo.setLocalCreationIndVendorConf(ekesEntity.getEstkz());
            purchaseOrderOABo.setSlsOrdrNum(ekesEntity.getVbeln());
            purchaseOrderOABo.setSlsOrdrLineNbr(ekesEntity.getVbelp());
            purchaseOrderOABo.setVendBtchNum(ekesEntity.getCharg());
            purchaseOrderOABo.setLocalConfCrtOnDt(ekesEntity.getErdat());
            purchaseOrderOABo.setLocalSeqNumVendConf(ekesEntity.getEtens());
        }
        else {
            purchaseOrderOABo.setCnfrmSeqNbr("");
            purchaseOrderOABo.setDelvDt("");
            purchaseOrderOABo.setCnfrmQty("");
            purchaseOrderOABo.setMrpAdjQty("");
            purchaseOrderOABo.setLocalCreationIndVendorConf("");
            purchaseOrderOABo.setSlsOrdrNum("");
            purchaseOrderOABo.setSlsOrdrLineNbr("");
            purchaseOrderOABo.setVendBtchNum("");
            purchaseOrderOABo.setLocalConfCrtOnDt("");
            purchaseOrderOABo.setLocalSeqNumVendConf("");
        }

        if(eketEntity != null) {
            purchaseOrderOABo.setDelvSchedCntNbr(eketEntity.getEtenr());
            purchaseOrderOABo.setLocaldelvDt(eketEntity.getEindt());
            purchaseOrderOABo.setSchdQty(eketEntity.getMenge());
            purchaseOrderOABo.setRecvQty(eketEntity.getWemng());
            purchaseOrderOABo.setLocalPurchaseReq(eketEntity.getBanfn());
            purchaseOrderOABo.setLocalPurchaseReqItem(eketEntity.getBnfpo());
            purchaseOrderOABo.setStkTfrRecvQty(eketEntity.getGlmng());
            purchaseOrderOABo.setLocalmrpAdjQty(eketEntity.getDabmg());
        }
        else {
            purchaseOrderOABo.setDelvSchedCntNbr("");
            purchaseOrderOABo.setLocaldelvDt("");
            purchaseOrderOABo.setSchdQty("");
            purchaseOrderOABo.setRecvQty("");
            purchaseOrderOABo.setLocalPurchaseReq("");
            purchaseOrderOABo.setLocalPurchaseReqItem("");
            purchaseOrderOABo.setStkTfrRecvQty("");
            purchaseOrderOABo.setLocalmrpAdjQty("");
        }

        if(ekpvEntity != null) {
            purchaseOrderOABo.setCustNum(ekpvEntity.getKunnr());
        }
        else {
            purchaseOrderOABo.setCustNum("");
        }

        resultObject.setBaseBo(purchaseOrderOABo);
        return resultObject;
    }
}

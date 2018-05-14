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
        String sourceSystem = "";
        if(sourceSystemV1Entity != null) {
            sourceSystem = sourceSystemV1Entity.getSourceSystem();
        }

        //N1
        List<EkpoEntity> ekpoEntities = ekpoDao.getEkpoEntitiesByEbeln(ekkoEntity.getEbeln());
        for(EkpoEntity ekpoEntity:ekpoEntities) {
            //N2
            String ebeln = ekpoEntity.getEbeln();
            String ebelp = ekpoEntity.getEbelp();
            List<EkbeEntity> ekbeEntities = ekbeDao.getEkbeEntitiesByEbelnAndEbelp(ebeln, ebelp);
            List<EkesEntity> ekesEntities = ekesDao.getEkesEntitiesByEbelnAndEbelp(ebeln, ebelp);
            List<EketEntity> eketEntities = eketDao.getEketEntitiesByEbelnAndEbelp(ebeln, ebelp);
            List<EkpvEntity> ekpvEntities = ekpvDao.getEkpvEntitiesByEbelnAndEbelp(ebeln, ebelp);

            for(EkbeEntity ekbeEntity:ekbeEntities){
                resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, ekbeEntity, null, null, null));
            }

            for(EkesEntity ekesEntity:ekesEntities){
                resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, ekesEntity, null, null));
            }

            for(EketEntity eketEntity:eketEntities){
                resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, null, eketEntity, null));
            }

            for(EkpvEntity ekpvEntity:ekpvEntities){
                resultObjects.add(setObjectByEntity(sourceSystem, ekkoEntity, ekpoEntity, null, null, null, ekpvEntity));
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

        if(ekbeEntity != null)
            purchaseOrderOABo.setEvTypeCd(ekbeEntity.getVgabe());
        else
            purchaseOrderOABo.setEvTypeCd("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setMatlMvmttYr(ekbeEntity.getGjahr());
        else
            purchaseOrderOABo.setMatlMvmttYr("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setMatlMvmtNum(ekbeEntity.getBelnr());
        else
            purchaseOrderOABo.setMatlMvmtSeqNbr("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setMatlMvmtSeqNbr(ekbeEntity.getBuzei());
        else
            purchaseOrderOABo.setMatlMvmtSeqNbr("");

        if(eketEntity != null)
            purchaseOrderOABo.setDelvSchedCntNbr(eketEntity.getEtenr());
        else
            purchaseOrderOABo.setDelvSchedCntNbr("");

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

        if(ekbeEntity != null)
            purchaseOrderOABo.setPoHistCatCd(ekbeEntity.getBewtp());
        else
            purchaseOrderOABo.setPoHistCatCd("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setLocalMovementType(ekbeEntity.getBwart());
        else
            purchaseOrderOABo.setLocalMovementType("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setLocalPostingDate(ekbeEntity.getBudat());
        else
            purchaseOrderOABo.setLocalPostingDate("");

        if(ekbeEntity != null)
            purchaseOrderOABo.setRecvQty(ekbeEntity.getMenge());
        else
            purchaseOrderOABo.setRecvQty("");

        if(ekesEntity != null)
            purchaseOrderOABo.setCnfrmSeqNbr(ekesEntity.getEbtyp());
        else
            purchaseOrderOABo.setCnfrmSeqNbr("");

        if(ekesEntity != null)
            purchaseOrderOABo.setDelvDt(ekesEntity.getEindt());
        else
            purchaseOrderOABo.setDelvDt("");

        if(ekesEntity != null)
            purchaseOrderOABo.setCnfrmQty(ekesEntity.getMenge());
        else
            purchaseOrderOABo.setCnfrmQty("");

        if(ekesEntity != null)
            purchaseOrderOABo.setMrpAdjQty(ekesEntity.getDabmg());
        else
            purchaseOrderOABo.setMrpAdjQty("");

        if(ekesEntity != null)
            purchaseOrderOABo.setLocalCreationIndVendorConf(ekesEntity.getEstkz());
        else
            purchaseOrderOABo.setLocalCreationIndVendorConf("");

        if(ekesEntity != null)
            purchaseOrderOABo.setSlsOrdrNum(ekesEntity.getVbeln());
        else
            purchaseOrderOABo.setSlsOrdrNum("");

        if(ekesEntity != null)
            purchaseOrderOABo.setSlsOrdrLineNbr(ekesEntity.getVbelp());
        else
            purchaseOrderOABo.setSlsOrdrLineNbr("");

        if(ekesEntity != null)
            purchaseOrderOABo.setVendBtchNum(ekesEntity.getCharg());
        else
            purchaseOrderOABo.setVendBtchNum("");

        if(eketEntity != null)
            purchaseOrderOABo.setLocaldelvDt(eketEntity.getEindt());
        else
            purchaseOrderOABo.setLocaldelvDt("");

        if(eketEntity != null)
            purchaseOrderOABo.setSchdQty(eketEntity.getMenge());
        else
            purchaseOrderOABo.setSchdQty("");

        if(eketEntity != null)
            purchaseOrderOABo.setRecvQty(eketEntity.getWemng());
        else
            purchaseOrderOABo.setRecvQty("");

        if(eketEntity != null)
            purchaseOrderOABo.setLocalPurchaseReq(eketEntity.getBanfn());
        else
            purchaseOrderOABo.setLocalPurchaseReq("");

        if(eketEntity != null)
            purchaseOrderOABo.setLocalPurchaseReqItem(eketEntity.getBnfpo());
        else
            purchaseOrderOABo.setLocalPurchaseReqItem("");

        if(eketEntity != null)
            purchaseOrderOABo.setStkTfrRecvQty(eketEntity.getGlmng());
        else
            purchaseOrderOABo.setStkTfrRecvQty("");

        if(eketEntity != null)
            purchaseOrderOABo.setLocalmrpAdjQty(eketEntity.getDabmg());
        else
            purchaseOrderOABo.setLocalmrpAdjQty("");

        purchaseOrderOABo.setGrLeadTimeDays(ekpoEntity.getWebaz());

        if(ekpvEntity != null)
            purchaseOrderOABo.setCustNum(ekpvEntity.getKunnr());
        else
            purchaseOrderOABo.setCustNum("");

        resultObject.setBaseBo(purchaseOrderOABo);
        return resultObject;
    }
}

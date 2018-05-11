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
        EDMPurchaseOrderOABo purchaseOrderOABo = new EDMPurchaseOrderOABo();
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

            }

            for(EkesEntity ekesEntity:ekesEntities){

            }

            for(EketEntity eketEntity:eketEntities){

            }

            for(EkpvEntity ekpvEntityy:ekpvEntities){

            }

        }
        return resultObjects;
    }
}

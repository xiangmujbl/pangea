package com.jnj.pangea.edm.source_list.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.EordEntity;
import com.jnj.pangea.common.entity.project_one.Mch1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.batch_master.bo.EDMBatchMasterBo;
import com.jnj.pangea.edm.source_list.bo.EDMSourceListBo;

public class EDMSourceListServiceImpl implements ICommonService {

    private static EDMSourceListServiceImpl instance;

    public static EDMSourceListServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMSourceListServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl materialGlobalV1Dao = EDMMaterialGlobalDaoImpl.getInstance();
    private EDMPlantV1DaoImpl edmPlantV1Dao = EDMPlantV1DaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EordEntity eordEntity = (EordEntity) o;
        EDMSourceListBo edmSourceListBo = new EDMSourceListBo();
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        edmSourceListBo.setSourceSystem(edmSourceSystemV1Entity.getSourceSystem());
        processSystem(eordEntity,edmSourceListBo);
        EDMMaterialGlobalV1Entity materialGlobalV1Entity =  materialGlobalV1Dao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(edmSourceSystemV1Entity.getSourceSystem(),eordEntity.getMatnr());

        if(materialGlobalV1Entity!=null){
            edmSourceListBo.setMaterialNumber(materialGlobalV1Entity.getMaterialNumber());
        }else{
            edmSourceListBo.setMaterialNumber("");
        }

        EDMPlantV1Entity edmPlantV1Entity = edmPlantV1Dao.getPlantWithSourceSystemAndLocalPlant(edmSourceSystemV1Entity.getSourceSystem(),eordEntity.getWerks());
        if(edmPlantV1Entity!=null){
            edmSourceListBo.setPlant(edmPlantV1Entity.getPlant());
        }else{
            edmSourceListBo.setPlant("");
        }
        resultObject.setBaseBo(edmSourceListBo);
        return resultObject;
    }


    private void processSystem(EordEntity eordEntity,  EDMSourceListBo edmSourceListBo) {
        edmSourceListBo.setLocalMaterialNumber(eordEntity.getMatnr());
        edmSourceListBo.setLocalPlant(eordEntity.getWerks());
        edmSourceListBo.setLocalNumberofSourceListRecord(eordEntity.getZeord());
        edmSourceListBo.setLocalCreatedOn(eordEntity.getErdat());
        edmSourceListBo.setLocalCreatedBy(eordEntity.getErnam());
        edmSourceListBo.setLocalSourceListRecordValidFrom(eordEntity.getVdatu());
        edmSourceListBo.setLocalSourceListRecordValidTo(eordEntity.getBdatu());
        edmSourceListBo.setLocalVendorAccountNumber(eordEntity.getLifnr());
        edmSourceListBo.setLocalFixedvendor(eordEntity.getFlifn());
        edmSourceListBo.setLocalAgreementNumber(eordEntity.getEbeln());
        edmSourceListBo.setLocalAgreementItem(eordEntity.getEbelp());
        edmSourceListBo.setLocalFixedOutlinePurchaseAgreementItem(eordEntity.getFebel());
        edmSourceListBo.setLocalPlantfromWhichMaterialisProcured(eordEntity.getReswk());
        edmSourceListBo.setLocalMatForManufPartNumber(eordEntity.getEmatn());
        edmSourceListBo.setLocalBlockedSourceofSupply(eordEntity.getNotkz());
        edmSourceListBo.setLocalPurchasingOrganization(eordEntity.getEkorg());
        edmSourceListBo.setLocalPurchasingDocumentCategory(eordEntity.getVrtyp());
        edmSourceListBo.setLocalCategoryofSourceListRecord(eordEntity.getEortp());
        edmSourceListBo.setLocalSourceListUsageinMaterialsPlanning(eordEntity.getAutet());

    }
}

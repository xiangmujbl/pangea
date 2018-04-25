package com.jnj.pangea.edm.source_list.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.EordEntity;
import com.jnj.pangea.common.service.ICommonService;
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
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EordEntity eordEntity = (EordEntity) o;
        EDMSourceListBo edmSourceListBo = new EDMSourceListBo();

        // T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != edmSourceSystemV1Entity) {
            edmSourceListBo.setSourceSystem(edmSourceSystemV1Entity.getSourceSystem());
        }

        edmSourceListBo.setLocalMaterialNumber(eordEntity.getMatnr());
        edmSourceListBo.setLocalPlant(eordEntity.getWerks());
        edmSourceListBo.setLocalNumberofSourceListRecord(eordEntity.getZeord());
        edmSourceListBo.setLocalDateonWhichRecordWasCreated(eordEntity.getErdat());
        edmSourceListBo.setLocalNameofPersonwhoCreatedtheObject(eordEntity.getErnam());
        edmSourceListBo.setLocalSourceListRecordValidFrom(eordEntity.getVdatu());
        edmSourceListBo.setLocalSourceListRecordValidTo(eordEntity.getBdatu());
        edmSourceListBo.setLocalVendorAccountNumber(eordEntity.getLifnr());
        edmSourceListBo.setLocalFixedvendor(eordEntity.getFlifn());
        edmSourceListBo.setLocalAgreementNumber(eordEntity.getEbeln());
        edmSourceListBo.setLocalAgreementItem(eordEntity.getEbelp());
        edmSourceListBo.setLocalFixedOutlinePurchaseAgreementItem(eordEntity.getFebel());
        edmSourceListBo.setLocalPlantfromWhichMaterialisProcured(eordEntity.getReswk());
        edmSourceListBo.setLocalMaterialNumberCorrespondingtoManufacturerPartNumber(eordEntity.getEmatn());
        edmSourceListBo.setLocalBlockedSourceofSupply(eordEntity.getNotkz());
        edmSourceListBo.setLocalPurchasingOrganization(eordEntity.getEkorg());
        edmSourceListBo.setLocalPurchasingDocumentCategory(eordEntity.getVrtyp());
        edmSourceListBo.setLocalCategoryofSourceListRecord(eordEntity.getEortp());
        edmSourceListBo.setLocalSourceListUsageinMaterialsPlanning(eordEntity.getAutet());

        resultObject.setBaseBo(edmSourceListBo);
        return resultObject;
    }
}

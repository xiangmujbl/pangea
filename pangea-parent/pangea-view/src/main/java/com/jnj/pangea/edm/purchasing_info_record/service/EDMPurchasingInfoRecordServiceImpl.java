package com.jnj.pangea.edm.purchasing_info_record.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneEineDaoImpl;
import com.jnj.pangea.common.entity.project_one.EinaEntity;
import com.jnj.pangea.common.entity.project_one.EineEntity;
import com.jnj.pangea.edm.purchasing_info_record.bo.EDMPurchasingInfoRecordBo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMPurchasingInfoRecordServiceImpl {

    private static EDMPurchasingInfoRecordServiceImpl instance;

    public static EDMPurchasingInfoRecordServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPurchasingInfoRecordServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneEineDaoImpl eineDao = ProjectOneEineDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> list = new ArrayList<>();

        EinaEntity einaEntity = (EinaEntity) o;

        // N1
        String infnr = einaEntity.getInfnr();
        if (StringUtils.isNotEmpty(infnr)) {
            List<EineEntity> eineEntityList = eineDao.getEntityWithInfnr(infnr);
            for (EineEntity eineEntity : eineEntityList) {

                EDMPurchasingInfoRecordBo purchasingInfoRecordV1LatamBo = new EDMPurchasingInfoRecordBo();
                ResultObject resultObject = new ResultObject();

                // T1
                EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
                if (null != sourceSystemV1Entity) {
                    purchasingInfoRecordV1LatamBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());
                }

                if (null != eineEntity) {
                    purchasingInfoRecordV1LatamBo.setLocalPurchasingOrg(eineEntity.getEkorg());
                    purchasingInfoRecordV1LatamBo.setInfotype(eineEntity.getEsokz());
                    purchasingInfoRecordV1LatamBo.setLocalPlanPlant(eineEntity.getWerks());
                    purchasingInfoRecordV1LatamBo.setLocalPurchOrgDataFlagDeletion(eineEntity.getLoekz());
                    purchasingInfoRecordV1LatamBo.setLocalCreatedOnEine(eineEntity.getErdat());
                    purchasingInfoRecordV1LatamBo.setLocalCreatedByEine(eineEntity.getErnam());
                    purchasingInfoRecordV1LatamBo.setLocalPurchasingGroup(eineEntity.getEkgrp());
                    purchasingInfoRecordV1LatamBo.setLocalCurrencyKey(eineEntity.getWaers());
                    purchasingInfoRecordV1LatamBo.setLocalMinimumQty(eineEntity.getMinbm());
                    purchasingInfoRecordV1LatamBo.setLocalStandardQty(eineEntity.getNorbm());
                    purchasingInfoRecordV1LatamBo.setLocalPlDelivTime(eineEntity.getAplfz());
                    purchasingInfoRecordV1LatamBo.setLocalTaxCode(eineEntity.getMwskz());
                    purchasingInfoRecordV1LatamBo.setLocalConfirmationControlKey(eineEntity.getBstae());
                    purchasingInfoRecordV1LatamBo.setLocalPrDateCat(eineEntity.getMeprf());
                    purchasingInfoRecordV1LatamBo.setLocalIncoterms(eineEntity.getInco1());
                    purchasingInfoRecordV1LatamBo.setLocalIncoterms2(eineEntity.getInco2());
                    purchasingInfoRecordV1LatamBo.setLocalProductionVersion(eineEntity.getVerid());
                    purchasingInfoRecordV1LatamBo.setLocalMaxQuantity(eineEntity.getBstma());
                    purchasingInfoRecordV1LatamBo.setLocalRndingProfile(eineEntity.getRdprf());
                    purchasingInfoRecordV1LatamBo.setLocalNCMCode(eineEntity.getJ1bnbm());

                    purchasingInfoRecordV1LatamBo.setLocalPurchasingInfoRec(einaEntity.getInfnr());
                    purchasingInfoRecordV1LatamBo.setLocalvendor(einaEntity.getLifnr());
                    purchasingInfoRecordV1LatamBo.setLocalMaterialNumber(einaEntity.getMatnr());
                    purchasingInfoRecordV1LatamBo.setLocalBaseUnit(einaEntity.getLmein());
                    purchasingInfoRecordV1LatamBo.setLocalMaterialGroup(einaEntity.getMatkl());
                    purchasingInfoRecordV1LatamBo.setLocalGeneralDataFlagDeletion(einaEntity.getLoekz());
                    purchasingInfoRecordV1LatamBo.setLocalCreatedOnEina(einaEntity.getErdat());
                    purchasingInfoRecordV1LatamBo.setLocalCreatedByEina(einaEntity.getErnam());
                    purchasingInfoRecordV1LatamBo.setLocalInfoShortText(einaEntity.getTxz01());
                    purchasingInfoRecordV1LatamBo.setLocalVendorMatNo(einaEntity.getIdnlf());
                    purchasingInfoRecordV1LatamBo.setLocalNumerator(einaEntity.getUmrez());
                    purchasingInfoRecordV1LatamBo.setLocalDenominator(einaEntity.getUmren());
                    purchasingInfoRecordV1LatamBo.setLocalOrderUnit(einaEntity.getMeins());
                    purchasingInfoRecordV1LatamBo.setLocalManufacturer(einaEntity.getMfrnr());

                    resultObject.setBaseBo(purchasingInfoRecordV1LatamBo);
                    list.add(resultObject);
                }
            }
        }
        return list;
    }
}
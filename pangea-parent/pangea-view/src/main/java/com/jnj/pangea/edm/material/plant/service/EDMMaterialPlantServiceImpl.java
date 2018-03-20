package com.jnj.pangea.edm.material.plant.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMatPlantStatV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectone.MarcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.bo.EDMMaterialPlantBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class EDMMaterialPlantServiceImpl implements ICommonService {

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        MarcEntity mainData = (MarcEntity) o;

        EDMMaterialPlantBo materialPlantBo = new EDMMaterialPlantBo();
        resultObject.setBaseBo(materialPlantBo);

        materialPlantBo.setLocalMaterialNumber(StringUtils.trim(mainData.getMatnr()));
        materialPlantBo.setLocalPlant(StringUtils.trim(mainData.getWerks()));
        materialPlantBo.setLocalPlantStatus(StringUtils.trim(mainData.getMmsta()));

        getSourceSystem(materialPlantBo);

        getMaterialNumber(materialPlantBo);

        getPlant(materialPlantBo);

        getPlantStatus(materialPlantBo);

        return resultObject;
    }

    private void getSourceSystem(EDMMaterialPlantBo materialPlantBo) {
        String sourceSystem = null;
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        List<EDMSourceSystemV1Entity> sourceList = commonDao.queryForList(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);

        for (EDMSourceSystemV1Entity entry : sourceList) {
            sourceSystem = entry.getSourceSystem();
        }
        materialPlantBo.setSourceSystem(sourceSystem);
    }

    private void getMaterialNumber(EDMMaterialPlantBo materialPlantBo) {
        String materialNumber = null;
        String matnr = materialPlantBo.getLocalMaterialNumber();
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(matnr)) {
            String queryString = QueryHelper.buildCriteria(IConstant.SOURCESYSTEM).is(materialPlantBo.getSourceSystem())
                    .and(IConstant.LOCALMATERIALNUMBER).is(matnr).toQueryString();

            if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
                queryString = IConstant.SOURCESYSTEM + ":\"" + materialPlantBo.getSourceSystem() + "\" AND " + IConstant.LOCALMATERIALNUMBER + ":\"" + AdfLuceneHelper.keyword(matnr) + "\"";
            }
            List<EDMMaterialGlobalV1Entity> sourceList = commonDao.queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
            for (EDMMaterialGlobalV1Entity entry : sourceList) {
                materialNumber = entry.getMaterialNumber();
            }
        }
        materialPlantBo.setMaterialNumber(materialNumber);
    }

    private void getPlant(EDMMaterialPlantBo materialPlantBo) {
        String plant = null;
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(materialPlantBo.getLocalPlant())) {
            String queryString = QueryHelper.buildCriteria(IConstant.LOCALPLANT).is(materialPlantBo.getLocalPlant())
                    .and(IConstant.SOURCESYSTEM).is(materialPlantBo.getSourceSystem()).toQueryString();

            List<EDMPlantV1Entity> sourceList = commonDao.queryForList(IConstant.REGION.EDM_PLANT_V1, queryString, EDMPlantV1Entity.class);
            for (EDMPlantV1Entity entry : sourceList) {
                plant = entry.getPlant();
            }
        }
        materialPlantBo.setPlant(plant);
    }

    private void getPlantStatus(EDMMaterialPlantBo materialPlantBo) {
        String plantStatus = null;
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(materialPlantBo.getLocalPlantStatus())) {
            String queryString = QueryHelper.buildCriteria(IConstant.LOCALPLANTSTATUS).is(materialPlantBo.getLocalPlantStatus())
                    .and(IConstant.SOURCESYSTEM).is(materialPlantBo.getSourceSystem()).toQueryString();

            List<EDMMatPlantStatV1Entity> sourceList = commonDao.queryForList(IConstant.REGION.EDM_MAT_PLANT_STAT_V1, queryString, EDMMatPlantStatV1Entity.class);
            for (EDMMatPlantStatV1Entity entry : sourceList) {
                plantStatus = entry.getPlantStatus();
            }

        }
        materialPlantBo.setPlantStatus(plantStatus);
    }
}

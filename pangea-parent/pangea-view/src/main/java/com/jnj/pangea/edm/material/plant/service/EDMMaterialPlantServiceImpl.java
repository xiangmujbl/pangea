package com.jnj.pangea.edm.material.plant.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.edm.EDMMatPlantStatV1Entry;
import com.jnj.pangea.common.entry.edm.EDMMaterialGlobalV1Entry;
import com.jnj.pangea.common.entry.edm.EDMSourceSystemV1Entry;
import com.jnj.pangea.common.entry.edm.EDMPlantV1Entry;
import com.jnj.pangea.common.entry.projectone.MarcEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.bo.EDMMaterialPlantBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class EDMMaterialPlantServiceImpl  implements ICommonService {

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
        MarcEntry mainData = (MarcEntry)o;

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
        String sourceSystem=null;
        String queryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALSOURCESYSTEM).is(CommonRegionPath.LOCALSOURCESYSTEM_PROJECT_ONE).toQueryString();
        List<EDMSourceSystemV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entry.class);

        for (EDMSourceSystemV1Entry entry : sourceList) {
            sourceSystem = entry.getSourceSystem();
        }
        materialPlantBo.setSourceSystem(sourceSystem);
    }

    private void getMaterialNumber(EDMMaterialPlantBo materialPlantBo){
        String materialNumber= null;
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(materialPlantBo.getLocalMaterialNumber())) {
            String queryString = QueryHelper.buildCriteria(CommonRegionPath.SOURCESYSTEM).is(materialPlantBo.getSourceSystem())
                    .and(CommonRegionPath.LOCALMATERIALNUMBER).is(materialPlantBo.getLocalMaterialNumber()).toQueryString();
            List<EDMMaterialGlobalV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entry.class);
            for (EDMMaterialGlobalV1Entry entry : sourceList) {
                materialNumber = entry.getMaterialNumber();
            }
        }
        materialPlantBo.setMaterialNumber(materialNumber);
    }

    private void getPlant(EDMMaterialPlantBo materialPlantBo){
        String plant = null;
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(materialPlantBo.getLocalPlant())){
            String queryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALPLANT).is(materialPlantBo.getLocalPlant())
                    .and(CommonRegionPath.SOURCESYSTEM).is(materialPlantBo.getSourceSystem()).toQueryString();

            List<EDMPlantV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.PANGEA_EDM_PLANT_V1, queryString, EDMPlantV1Entry.class);
            for (EDMPlantV1Entry entry : sourceList) {
                plant = entry.getPlant();
            }
        }
        materialPlantBo.setPlant(plant);
    }

    private void getPlantStatus(EDMMaterialPlantBo materialPlantBo){
        String plantStatus=null;
        if (StringUtils.isNotBlank(materialPlantBo.getSourceSystem()) && StringUtils.isNotBlank(materialPlantBo.getLocalPlantStatus())){
            String queryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALPLANTSTATUS).is(materialPlantBo.getLocalPlantStatus())
                    .and(CommonRegionPath.SOURCESYSTEM).is(materialPlantBo.getSourceSystem()).toQueryString();

            List<EDMMatPlantStatV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.PANGEA_EDM_MAT_PLANT_STAT_V1, queryString, EDMMatPlantStatV1Entry.class);
            for (EDMMatPlantStatV1Entry entry : sourceList) {
                plantStatus = entry.getPlantStatus();
            }

        }
        materialPlantBo.setPlantStatus(plantStatus);
    }
}

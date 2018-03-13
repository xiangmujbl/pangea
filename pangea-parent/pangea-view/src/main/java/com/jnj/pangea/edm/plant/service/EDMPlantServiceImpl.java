package com.jnj.pangea.edm.plant.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFZEnterprisePlants;
import com.jnj.pangea.common.entity.projectone.T001Entity;
import com.jnj.pangea.common.entity.projectone.T001KEntity;
import com.jnj.pangea.common.entity.projectone.T001WEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.plant.bo.EDMPlantBo;
import org.apache.commons.lang3.StringUtils;

public class EDMPlantServiceImpl implements ICommonService {

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMPlantServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFZEnterprisePlants enterprisePlants = (EMSFZEnterprisePlants) o;

        EDMPlantBo plantBo = new EDMPlantBo();

        // rule T1
        String zPlantSourceSystem = enterprisePlants.getzPlantSourceSystem();

        FailData failData = checkT1(enterprisePlants, zPlantSourceSystem);
        if (null != failData) {
            resultObject.setFailData(failData);
            return resultObject;
        }

        plantBo.setSourceSystem(getFieldWithT1(zPlantSourceSystem));

        String localPlant = enterprisePlants.getzPlant();
        plantBo.setLocalPlant(localPlant);

        // rule T2
        T001WEntity t001WEntity = getFieldsWithT2(localPlant);
        if (null != t001WEntity) {

            plantBo.setLocalPlantName(t001WEntity.getName1());
            // rule T3
            String LocalCountry = t001WEntity.getLand1();
            plantBo.setLocalCountry(LocalCountry);
            // rule T4
            plantBo.setCountry(getFieldWithT4(LocalCountry));
            plantBo.setLocalPlantType(t001WEntity.getNodetype());
            // rule J1
            plantBo.setLocalCurrency(getFieldWithJ1(t001WEntity.getBwkey()));
        }
        plantBo.setPlant(enterprisePlants.getzEntPlantNumber());
        plantBo.setLocalPlanningRelevant("");
        plantBo.setSite(enterprisePlants.getzSite());
        plantBo.setPlantType(enterprisePlants.getzEntPlantType());
        plantBo.setRegion(enterprisePlants.getzRegion());

        resultObject.setBaseBo(plantBo);

        return resultObject;
    }

    private String getFieldWithT1(String zPlantSourceSystem) {
        if (StringUtils.isEmpty(zPlantSourceSystem)) {
            return "";
        }
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem").is(zPlantSourceSystem).toQueryString();
        EDMSourceSystemV1Entity sourceSystemV1Entry = commonDao.queryForObject(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, systemQueryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystemV1Entry) {
            return sourceSystemV1Entry.getSourceSystem();
        }
        return "";
    }

    private T001WEntity getFieldsWithT2(String zPlant) {
        if (StringUtils.isEmpty(zPlant)) {
            return null;
        }
        String name1QueryString = QueryHelper.buildCriteria("werks").is(zPlant).toQueryString();
        return commonDao.queryForObject(CommonRegionPath.PROJECT_ONE_T001W, name1QueryString, T001WEntity.class);
    }

    private String getFieldWithT4(String land1) {
        if (StringUtils.isEmpty(land1)) {
            return "";
        }
        String localQueryString = QueryHelper.buildCriteria("localCountry").is(land1).toQueryString();
        EDMCountryEntity countryEntity = commonDao.queryForObject(CommonRegionPath.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        if (null != countryEntity) {
            return countryEntity.getCountryCode();
        }
        return "";
    }

    private String getFieldWithJ1(String bwkey) {
        if (StringUtils.isEmpty(bwkey)) {
            return "";
        }
        String QueryString = QueryHelper.buildCriteria("bwkey").is(bwkey).toQueryString();

        T001KEntity t001KEntity = commonDao.queryForObject(CommonRegionPath.PROJECT_ONE_T001K, QueryString, T001KEntity.class);

        if (null != t001KEntity) {

            String bukrs = t001KEntity.getBukrs();
            if (StringUtils.isEmpty(bukrs)) {
                return "";
            }
            QueryString = QueryHelper.buildCriteria("bukrs").is(bukrs).toQueryString();
            T001Entity t001Entity = commonDao.queryForObject(CommonRegionPath.PROJECT_ONE_T001, QueryString, T001Entity.class);

            if (null != t001Entity) {
                return t001Entity.getWaers();
            }
        }
        return "";
    }

    private FailData checkT1(EMSFZEnterprisePlants enterprisePlants, String sourceSystem) {
        FailData failData = null;
        if (StringUtils.isEmpty(sourceSystem) || "[EMS]".equals(sourceSystem)) {
            failData = new FailData();
            failData.setErrorCode("T1");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMPlant");
            failData.setSourceSystem("project_one");
            failData.setKey1(enterprisePlants.getzPlantSourceSystem());
            failData.setKey2(enterprisePlants.getzPlant());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
        }
        return failData;
    }

}

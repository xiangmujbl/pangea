package com.jnj.pangea.edm.plant.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantInputDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001KDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001WDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMPlantInputEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFZEnterprisePlants;
import com.jnj.pangea.common.entity.project_one.T001Entity;
import com.jnj.pangea.common.entity.project_one.T001KEntity;
import com.jnj.pangea.common.entity.project_one.T001WEntity;
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

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneT001WDaoImpl t001WDao = ProjectOneT001WDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private ProjectOneT001KDaoImpl t001KDao = ProjectOneT001KDaoImpl.getInstance();
    private ProjectOneT001DaoImpl t001Dao = ProjectOneT001DaoImpl.getInstance();
    private EDMPlantInputDaoImpl plantInputDao = EDMPlantInputDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFZEnterprisePlants enterprisePlants = (EMSFZEnterprisePlants) o;

        EDMPlantBo plantBo = new EDMPlantBo();

        // rule T1
        String zPlantSourceSystem = enterprisePlants.getzPlantSourceSystem();


        String sourceSystem=getFieldWithT1(zPlantSourceSystem);
        if(StringUtils.isBlank ( sourceSystem )){
            return resultObject;
        }

        plantBo.setSourceSystem(sourceSystem);
        String zPalnt=enterprisePlants.getzPlant();
        if(StringUtils.isBlank ( zPalnt )||zPalnt.trim ().split ( IConstant.EDM_PLANT_V1.SPLIT_ ).length<2){
            return resultObject;
        }
        String localPlant = zPalnt.split(IConstant.EDM_PLANT_V1.SPLIT_)[1].trim();
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
            T001Entity  t001Entity= getFieldWithJ1(t001WEntity.getBwkey());
            if(t001Entity==null){
                return resultObject;
            }
            plantBo.setLocalCurrency(t001Entity.getWaers ());
        }else {
            return resultObject;
        }

        plantBo.setPlant(enterprisePlants.getzEntPlantNumber());
        // rule J2

        EDMPlantInputEntity edmPlantInputEntity = getFieldWithJ2(plantBo.getSourceSystem(), localPlant);
        if (null != edmPlantInputEntity) {
            plantBo.setLocalPlanningRelevant(edmPlantInputEntity.getLocalPlanningRelevant());
        } else {
            plantBo.setLocalPlanningRelevant(IConstant.EDM_PLANT_V1.FIRLD_BLANK);
        }
        plantBo.setSite(enterprisePlants.getzSite());
        plantBo.setPlantType(enterprisePlants.getzEntPlantType());
        plantBo.setRegion(enterprisePlants.getzRegion());

        resultObject.setBaseBo(plantBo);

        return resultObject;
    }

    private String getFieldWithT1(String zPlantSourceSystem) {

        if (StringUtils.isEmpty(zPlantSourceSystem)) {
            return IConstant.EDM_PLANT_V1.FIRLD_BLANK;
        }
        EDMSourceSystemV1Entity sourceSystemV1Entry = sourceSystemV1Dao.getEntityWithLocalSourceSystem(zPlantSourceSystem);
        if (null != sourceSystemV1Entry) {
            return sourceSystemV1Entry.getSourceSystem();
        }
        return IConstant.EDM_PLANT_V1.FIRLD_BLANK;
    }

    private T001WEntity getFieldsWithT2(String zPlant) {

        if (StringUtils.isEmpty(zPlant)) {
            return null;
        }
        return t001WDao.getEntityWithZPlant(zPlant);
    }

    private String getFieldWithT4(String land1) {

        if (StringUtils.isEmpty(land1)) {
            return IConstant.EDM_PLANT_V1.FIRLD_BLANK;
        }
        EDMCountryEntity countryEntity = countryV1Dao.getEntityWithLocalCountry(land1);
        if (null != countryEntity) {
            return countryEntity.getCountryCode();
        }
        return IConstant.EDM_PLANT_V1.FIRLD_BLANK;
    }

    private T001Entity getFieldWithJ1(String bwkey) {
        T001KEntity t001KEntity = t001KDao.getEntityWithBwkey(bwkey);
        if (null != t001KEntity) {
            String bukrs = t001KEntity.getBukrs();
            return t001Dao.getEntityWithBukrs(bukrs);

        }else{
            return null;
        }
    }

    private EDMPlantInputEntity getFieldWithJ2(String SourceSystem, String localPlant) {

        if (StringUtils.isEmpty(localPlant)) {
            return null;
        }
        return plantInputDao.getPlantWithSourceSystemAndLocalPlant(SourceSystem, localPlant);
    }

    private FailData writeFailDataToRegion(EMSFZEnterprisePlants enterprisePlants, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.EDM_PLANT);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(enterprisePlants.getzPlantSourceSystem());
        failData.setKey1(enterprisePlants.getzPlant());
        failData.setKey2(IConstant.EDM_PLANT_V1.FIRLD_BLANK);
        failData.setKey3(IConstant.EDM_PLANT_V1.FIRLD_BLANK);
        failData.setKey4(IConstant.EDM_PLANT_V1.FIRLD_BLANK);
        failData.setKey5(IConstant.EDM_PLANT_V1.FIRLD_BLANK);
        failData.setErrorValue(errorValue);
        return failData;
    }

}

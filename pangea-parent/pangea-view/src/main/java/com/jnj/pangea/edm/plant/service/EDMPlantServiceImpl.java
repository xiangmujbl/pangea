package com.jnj.pangea.edm.plant.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001KDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001WDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
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

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFZEnterprisePlants enterprisePlants = (EMSFZEnterprisePlants) o;

        EDMPlantBo plantBo = new EDMPlantBo();

        // rule T1
        String zPlantSourceSystem = enterprisePlants.getzPlantSourceSystem();

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
        EDMSourceSystemV1Entity sourceSystemV1Entry = sourceSystemV1Dao.getEntityWithLocalSourceSystem(zPlantSourceSystem);
        if (null != sourceSystemV1Entry) {
            return sourceSystemV1Entry.getSourceSystem();
        }
        return "";
    }

    private T001WEntity getFieldsWithT2(String zPlant) {

        if (StringUtils.isEmpty(zPlant)) {
            return null;
        }
        String plant = zPlant.split(",")[1].trim();
        return t001WDao.getEntityWithZPlant(plant);
    }

    private String getFieldWithT4(String land1) {

        if (StringUtils.isEmpty(land1)) {
            return "";
        }
        EDMCountryEntity countryEntity = countryV1Dao.getEntityWithLocalCountry(land1);
        if (null != countryEntity) {
            return countryEntity.getCountryCode();
        }
        return "";
    }

    private String getFieldWithJ1(String bwkey) {

        if (StringUtils.isEmpty(bwkey)) {
            return "";
        }
        T001KEntity t001KEntity = t001KDao.getEntityWithBwkey(bwkey);
        if (null != t001KEntity) {

            String bukrs = t001KEntity.getBukrs();
            if (StringUtils.isEmpty(bukrs)) {
                return "";
            }

            T001Entity t001Entity = t001Dao.getEntityWithBukrs(bukrs);
            if (null != t001Entity) {
                return t001Entity.getWaers();
            }
        }
        return "";
    }

}

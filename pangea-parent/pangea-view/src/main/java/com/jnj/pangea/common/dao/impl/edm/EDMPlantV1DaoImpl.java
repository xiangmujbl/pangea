package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMPlantV1DaoImpl extends CommonDaoImpl {

    public static final String LOCAL_PLANT = "localPlant";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String COUNTRY = "country";
    public static final String FIRLD_BLANK="";
    public static final String SPLIT_=",";
    
    private static EDMPlantV1DaoImpl instance;

    public static EDMPlantV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlantV1DaoImpl();
        }
        return instance;
    }

    public EDMPlantV1Entity getPlantWithSourceSystemAndLocalPlant(String sourceSystem,String bwkey) {
        if(null != sourceSystem && (!(sourceSystem.isEmpty())) && null != bwkey && (!(bwkey.isEmpty()))) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                    .and(LOCAL_PLANT).is(bwkey).toQueryString();
            return queryForObject(RegionsConstant.EDM_PLANT_V1, queryString, EDMPlantV1Entity.class);
        }
        return null;
    }

    public EDMPlantV1Entity getEntityWithLocalPlant(String localPlant) {
        if(StringUtils.isBlank(localPlant)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(RegionsConstant.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
    }
    public EDMPlantV1Entity getPlantWithLocalPlantAndCountry(String localPlant,String country) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(country)){
            String queryString = QueryHelper.buildCriteria(LOCAL_PLANT).is(localPlant).and(COUNTRY).is(country).toQueryString();
            return queryForObject(RegionsConstant.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
        }
        return null;
    }

    public List<EDMPlantV1Entity> getEntitiesWithSourceSystemAndLocalPlant (String sourceSystem, String localPlant) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForList(RegionsConstant.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
    }
}

package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EDMSourceListV1DaoImpl extends CommonDaoImpl {

    public static final String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_PLANT = "localPlant";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String LOCAL_BLOCKED_SOURCE_OF_SUPPLY = "localBlockedSourceofSupply";
    public static final String LOCAL_SOURCE_LIST_RECORD_VALID_FROM = "localSourceListRecordValidFrom";
    public static final String LOCAL_SOURCE_LIST_RECORD_VALID_TO = "localSourceListRecordValidTo";
    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_BLOCKED_SOURCEOF_SUPPLY = "localBlockedSourceofSupply";

    private static EDMSourceListV1DaoImpl instance;

    public static EDMSourceListV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSourceListV1DaoImpl();
        }
        return instance;
    }

    public List<EDMSourceListV1Entity> getEntitiesWithLocalMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(String localMaterialNumber, String localPlantNumber, String sourceSystem) {

        DateFormat dateFormat = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        Date date = new Date();
        String localSystemDate = dateFormat.format(date);

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_PLANT).is(localPlantNumber)
                .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_SOURCE_LIST_RECORD_VALID_FROM).lessThanEqual(localSystemDate)
                .and(LOCAL_SOURCE_LIST_RECORD_VALID_TO).greaterThanEqual(localSystemDate)
                .and(LOCAL_BLOCKED_SOURCE_OF_SUPPLY).isNull()
                .toQueryString();
        return queryForList(RegionsConstant.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
    }

    public List<EDMSourceListV1Entity> getEntityListWithConditions(String sourceSystem, String localMaterialNumber, String localPlant) {

    	DateFormat dateFormat = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        Date date = new Date();
        String localSystemDate = dateFormat.format(date);
        
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_PLANT).is(localPlant)
                .and(LOCAL_BLOCKED_SOURCEOF_SUPPLY).isNull()
                .and(LOCAL_SOURCE_LIST_RECORD_VALID_FROM).lessThanEqual(localSystemDate)
                .and(LOCAL_SOURCE_LIST_RECORD_VALID_TO).greaterThanEqual(localSystemDate)
                .toQueryString();
        return queryForList(RegionsConstant.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
    }

    public List<EDMSourceListV1Entity> getEntityListWithSourceSystemAndLocalMaterialNumber(String sourceSystem,String localMaterialNumber) {
        if(StringUtils.isNotEmpty(localMaterialNumber)&&StringUtils.isNotEmpty(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(RegionsConstant.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
        }
        return null;
    }
}

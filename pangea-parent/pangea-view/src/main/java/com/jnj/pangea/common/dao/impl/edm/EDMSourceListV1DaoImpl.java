package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
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

    private static EDMSourceListV1DaoImpl instance;

    public static EDMSourceListV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSourceListV1DaoImpl();
        }
        return instance;
    }

    public EDMSourceListV1Entity getEntityWithMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(String materialNumber, String localPlantNumber, String sourceSystem) {

        DateFormat dateFormat = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        Date date = new Date();
        String localSystemDate = dateFormat.format(date);

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_LIST_V1.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_PLANT).is(localPlantNumber)
                .and(IConstant.EDM_SOURCE_LIST_V1.MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_SOURCE_LIST_RECORD_VALID_FROM).lessThanEqual(localSystemDate)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_SOURCE_LIST_RECORD_VALID_TO).greaterThanEqual(localSystemDate)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_BLOCKED_SOURCE_OF_SUPPLY).isNull()
                .toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
    }

    public List<EDMSourceListV1Entity> getEntityListWithConditions(String sourceSystem, String localMaterialNumber, String localPlant) {

    	DateFormat dateFormat = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        Date date = new Date();
        String localSystemDate = dateFormat.format(date);
        
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_LIST_V1.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_PLANT).is(localPlant)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_BLOCKED_SOURCEOF_SUPPLY).isNull()
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_SOURCE_LIST_RECORD_VALID_FROM).lessThanEqual(localSystemDate)
                .and(IConstant.EDM_SOURCE_LIST_V1.LOCAL_SOURCE_LIST_RECORD_VALID_TO).greaterThanEqual(localSystemDate)
                .toQueryString();
        return queryForList(IConstant.REGION.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
    }

    public List<EDMSourceListV1Entity> getEntityListWithSourceSystemAndLocalMaterialNumber(String sourceSystem,String localMaterialNumber) {
        if(StringUtils.isNotEmpty(localMaterialNumber)&&StringUtils.isNotEmpty(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_LIST_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.EDM_SOURCE_LIST_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_SOURCE_LIST_V1, queryString, EDMSourceListV1Entity.class);
        }
        return null;
    }
}

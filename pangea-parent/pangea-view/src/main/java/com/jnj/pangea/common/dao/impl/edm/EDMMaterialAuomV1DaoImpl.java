package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMaterialAuomV1DaoImpl extends CommonDaoImpl {

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String LOCAL_AUOM = "localAuom";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static EDMMaterialAuomV1DaoImpl instance;

    public static EDMMaterialAuomV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialAuomV1Entity getEntityWithConditions(String localMaterialNumber,String localAuom) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_AUOM).is(localAuom).toQueryString();
        return queryForObject(RegionsConstant.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }
    public EDMMaterialAuomV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber,String localAuom,String sourceSystem) {
        if (StringUtils.isNotBlank(localMaterialNumber) && StringUtils.isNotBlank(localAuom) && StringUtils.isNotBlank(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(LOCAL_AUOM).is(localAuom).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(RegionsConstant.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }
    public EDMMaterialAuomV1Entity getListWithLocalMaterialNumber(String localMaterialNumber,String localAuom,String sourceSystem) {
        if (StringUtils.isNotBlank(localMaterialNumber) && StringUtils.isNotBlank(localAuom) && StringUtils.isNotBlank(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(LOCAL_AUOM).is(localAuom)
                    .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(RegionsConstant.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }
    public List<EDMMaterialAuomV1Entity> getListWithLocalAuom(String localMaterialNumber,String localAuom,String sourceSystem){
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_AUOM).is(localAuom)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(RegionsConstant.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }

    public EDMMaterialAuomV1Entity getEntityWithConditions(String localMaterialNumber,String localAuom,String sourceSystem) {

        ADFCriteria adfCriteria=QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER);
        if(StringUtils.isNotBlank(localMaterialNumber)){
            adfCriteria.is(localMaterialNumber);
        }else{
            adfCriteria.isNull();
        }
        if(StringUtils.isNotBlank(localAuom)){
            adfCriteria.and(LOCAL_AUOM).is(localAuom);
        }else{
            adfCriteria.and(LOCAL_AUOM).isNull();
        }

        if(StringUtils.isNotBlank(sourceSystem)){
            adfCriteria.and(SOURCE_SYSTEM).is(sourceSystem);
        }else{
            adfCriteria.and(SOURCE_SYSTEM).isNull();
        }
        String queryString=adfCriteria.toQueryString();
        return queryForObject(RegionsConstant.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }
}

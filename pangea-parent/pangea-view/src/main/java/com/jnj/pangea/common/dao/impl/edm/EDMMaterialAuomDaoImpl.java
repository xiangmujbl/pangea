package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EDMMaterialAuomDaoImpl extends CommonDaoImpl {

    public static final String EDM_MATERIAL_AUOM_V1 = "/edm/material_auom_v1";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String LOCAL_AUOM = "localAuom";
    public static final String SOURCE_SYSTEM = "sourceSystem";


    private static EDMMaterialAuomDaoImpl instance;

    public static EDMMaterialAuomDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomDaoImpl();
        }
        return instance;
    }

    public EDMMaterialAuomV1Entity getEntityWithLocalMaterialNumAndLocalAuom(String localMaterialNum, String localAuom) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                .and(LOCAL_AUOM).is(localAuom).toQueryString();
        return queryForObject(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);

    }

    public List<EDMMaterialAuomV1Entity> getEntityWithLocalMaterialNum(String localMaterialNum) {
        if (StringUtils.isNotBlank(localMaterialNum)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNum).toQueryString();
            return queryForList(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);

        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityListWithMaterialNum(String materialNum) {
        if (StringUtils.isNotBlank(materialNum)) {
            String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(materialNum)
                    .toQueryString();
            return queryForList(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public EDMMaterialAuomV1Entity getEntityWithMaterialNum(String materialNum) {
        if (StringUtils.isNotBlank(materialNum)) {
            String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(materialNum)
                    .toQueryString();
            return queryForObject(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityWithSourceSystemAndLocalMaterialNum(String sourceSystem, String localMaterialNum) {
        if (StringUtils.isNotBlank(localMaterialNum) && StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public EDMMaterialAuomV1Entity getEntityWithSourceSystemAndLocalMaterialNumAndLocalAuom(String sourceSystem, String localMaterialNum, String localAuom) {
        if (StringUtils.isNotBlank(localMaterialNum) && StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localAuom)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .and(LOCAL_AUOM).is(localAuom)
                    .toQueryString();
            return queryForObject(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityList() {
        String queryString = "*:*";
        return queryForList(EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }


}

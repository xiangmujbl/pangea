package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MarcEntity;
import com.jnj.pangea.common.entity.project_one.MchbEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMarcDaoImpl extends CommonDaoImpl {

    private static ProjectOneMarcDaoImpl instance;

    public static ProjectOneMarcDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMarcDaoImpl();
        }
        return instance;
    }

    public MarcEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public List<MarcEntity> getMarcListWithMatnr(String matnr, String werks){
        List<MarcEntity> list = new ArrayList<MarcEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MARC.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MARC.WERKS).is(werks)
                .toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_MARC,queryString,MarcEntity.class);
    }
}

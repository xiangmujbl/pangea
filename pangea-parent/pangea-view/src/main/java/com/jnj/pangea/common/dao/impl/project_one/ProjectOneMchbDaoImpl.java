package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.MchbEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMchbDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MCHB = "/project_one/mchb";

    public static final String CHARG = "charg";
    public static final String MATNR = "matnr";
    public static final String WERKS = "werks";
    public static final String LGORT ="lgort";

    private static ProjectOneMchbDaoImpl instance;

    public static ProjectOneMchbDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMchbDaoImpl();
        }
        return instance;
    }

    public MchbEntity getEntityWithCharg(String charg) {

        String queryString = QueryHelper.buildCriteria(CHARG).is(charg).toQueryString();
        return queryForObject(PROJECT_ONE_MCHB, queryString, MchbEntity.class);
    }

    public List<MchbEntity> getMchbListWithMatnrAndWerks(String matnr, String werks){
        List<MchbEntity> list = new ArrayList<MchbEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks).toQueryString();
        return queryForList(PROJECT_ONE_MCHB,queryString,MchbEntity.class);
    }

    public List<MchbEntity> getMchbListWithMatnrAndWerksAndLgort(String matnr, String werks,String lgort){
        List<MchbEntity> list = new ArrayList<MchbEntity>();
        if ("".equals(matnr) || "".equals(werks) || "".equals(lgort)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks)
                .and(LGORT).is(lgort)
                .toQueryString();
        return queryForList(PROJECT_ONE_MCHB,queryString,MchbEntity.class);
    }

}

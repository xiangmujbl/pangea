package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MkolEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMkolDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MKOL = "/project_one/mkol";

    public static final String MATNR = "matnr";
    public static final String WERKS = "werks";
    public static final String LGORT ="lgort";

    private static ProjectOneMkolDaoImpl instance;

    public static ProjectOneMkolDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMkolDaoImpl();
        }
        return instance;
    }

    public List<MkolEntity> getMkolListWithMatnrAndWerks(String matnr, String werks){
        List<MkolEntity> list = new ArrayList<MkolEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks).toQueryString();
        return queryForList(PROJECT_ONE_MKOL,queryString,MkolEntity.class);
    }

    public List<MkolEntity> getMkolListWithMatnrAndWerksAndLgort(String matnr, String werks, String lgort){
        List<MkolEntity> list = new ArrayList<MkolEntity>();
        if ("".equals(matnr) || "".equals(werks) || "".equals(lgort)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks)
                .and(LGORT).is(lgort)
                .toQueryString();
        return queryForList(PROJECT_ONE_MKOL,queryString,MkolEntity.class);
    }




}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MarcEntity;
import com.jnj.pangea.common.entity.project_one.MchbEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMarcDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MARC = "/project_one/marc";

    public static final String MATNR = "matnr";
    public static final String WERKS = "werks";

    private static ProjectOneMarcDaoImpl instance;

    public static ProjectOneMarcDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMarcDaoImpl();
        }
        return instance;
    }


    public List<MarcEntity> getMarcListWithMatnr(String matnr, String werks){
        List<MarcEntity> list = new ArrayList<MarcEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks)
                .toQueryString();
        return queryForList(PROJECT_ONE_MARC,queryString,MarcEntity.class);
    }
}

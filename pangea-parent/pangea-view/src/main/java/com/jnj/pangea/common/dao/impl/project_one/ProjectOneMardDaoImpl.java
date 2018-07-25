package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MardEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMardDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MARD = "/project_one/mard";

    public static final String MATNR = "matnr";
    public static final String WERKS = "werks";

    private static ProjectOneMardDaoImpl instance;

    public static ProjectOneMardDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMardDaoImpl();
        }
        return instance;
    }

    public List<MardEntity> getMardListWithMatnrAndWerks(String matnr, String werks){
        List<MardEntity> list = new ArrayList<MardEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(WERKS).is(werks).toQueryString();
        return queryForList(PROJECT_ONE_MARD,queryString,MardEntity.class);
    }
}

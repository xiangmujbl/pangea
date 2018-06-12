package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MkolEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMkolDaoImpl extends CommonDaoImpl {

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
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MKOL.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MKOL.WERKS).is(werks).toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_MKOL,queryString,MkolEntity.class);
    }
}

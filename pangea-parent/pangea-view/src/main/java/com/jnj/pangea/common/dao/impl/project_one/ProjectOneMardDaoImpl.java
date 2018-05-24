package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MardEntity;

import java.util.List;

public class ProjectOneMardDaoImpl extends CommonDaoImpl {

    private static ProjectOneMardDaoImpl instance;

    public static ProjectOneMardDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMardDaoImpl();
        }
        return instance;
    }

    public List<MardEntity> getMardListWithMatnrAndWerks(String matnr, String werks){
        if ("".equals(matnr) || "".equals(werks)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MARD.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MARD.WERKS).is(werks).toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_MARD,queryString,MardEntity.class);
    }
}

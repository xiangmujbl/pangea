package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.MchbEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMchbDaoImpl extends CommonDaoImpl {

    private static ProjectOneMchbDaoImpl instance;

    public static ProjectOneMchbDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMchbDaoImpl();
        }
        return instance;
    }

    public MchbEntity getEntityWithCharg(String charg) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MCHB.CHARG).is(charg).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_MCHB, queryString, MchbEntity.class);
    }

    public List<MchbEntity> getMchbListWithMatnrAndWerks(String matnr, String werks){
        List<MchbEntity> list = new ArrayList<MchbEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MCHB.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MCHB.WERKS).is(werks).toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_MCHB,queryString,MchbEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MslbEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOneMslbDaoImpl extends CommonDaoImpl {

    private static ProjectOneMslbDaoImpl instance;

    public static ProjectOneMslbDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMslbDaoImpl();
        }
        return instance;
    }

    public List<MslbEntity> getMslbListWithMatnrAndWerks(String matnr, String werks){
        List<MslbEntity> list = new ArrayList<MslbEntity>();
        if ("".equals(matnr) || "".equals(werks)){
            return list;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MSLB.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MSLB.WERKS).is(werks).toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_MSLB,queryString,MslbEntity.class);
    }
}

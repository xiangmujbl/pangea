package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EkesEntity;

import java.util.List;

public class EkesDaoImpl extends CommonDaoImpl {
    private static EkesDaoImpl instance;

    public static EkesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkesDaoImpl();
        }
        return instance;
    }

    public List<EkesEntity> getEkesEntitiesByEbelnAndEbelp(String ebeln, String ebelp) {
        String queryString= QueryHelper.buildCriteria(IConstant.PROJECT_ONE_EKBE.EBELN).is(ebeln).and(IConstant.PROJECT_ONE_EKBE.EBELP).is(ebelp).toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_EKBE, queryString, EkesEntity.class);
    }
}

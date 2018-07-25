package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EkesEntity;

import java.util.List;

public class EkesDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EKES = "/project_one/ekes";

    public static final String EBELN = "ebeln";
    public static final String EBELP = "ebelp";

    private static EkesDaoImpl instance;

    public static EkesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkesDaoImpl();
        }
        return instance;
    }

    public List<EkesEntity> getEkesEntitiesByEbelnAndEbelp(String ebeln, String ebelp) {
        String queryString= QueryHelper.buildCriteria(EBELN).is(ebeln).and(EBELP).is(ebelp).toQueryString();
        return queryForList(PROJECT_ONE_EKES, queryString, EkesEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EkpvEntity;

import java.util.List;

public class EkpvDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EKPV = "/project_one/ekpv";

    public static final String EBELN = "ebeln";
    public static final String EBELP = "ebelp";

    private static EkpvDaoImpl instance;

    public static EkpvDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkpvDaoImpl();
        }
        return instance;
    }

    public List<EkpvEntity> getEkpvEntitiesByEbelnAndEbelp(String ebeln, String ebelp) {
        String queryString= QueryHelper.buildCriteria(EBELN).is(ebeln).and(EBELP).is(ebelp).toQueryString();
        return queryForList(PROJECT_ONE_EKPV, queryString, EkpvEntity.class);
    }
}

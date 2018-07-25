package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EkbeEntity;

import java.util.List;

public class EkbeDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EKBE = "/project_one/ekbe";

    public static final String EBELN = "ebeln";
    public static final String EBELP = "ebelp";

    private static EkbeDaoImpl instance;

    public static EkbeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkbeDaoImpl();
        }
        return instance;
    }

    public List<EkbeEntity> getEkbeEntitiesByEbelnAndEbelp(String ebeln, String ebelp) {
        String queryString= QueryHelper.buildCriteria(EBELN).is(ebeln).and(EBELP).is(ebelp).toQueryString();
        return queryForList(PROJECT_ONE_EKBE, queryString, EkbeEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EketEntity;

import java.util.List;

public class EketDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EKET = "/project_one/eket";

    public static final String EBELN = "ebeln";
    public static final String EBELP = "ebelp";

    private static EketDaoImpl instance;

    public static EketDaoImpl getInstance() {
        if (instance == null) {
            instance = new EketDaoImpl();
        }
        return instance;
    }

    public List<EketEntity> getEketEntitiesByEbelnAndEbelp(String ebeln, String ebelp) {
        String queryString= QueryHelper.buildCriteria(EBELN).is(ebeln).and(EBELP).is(ebelp).toQueryString();
        return queryForList(PROJECT_ONE_EKET, queryString, EketEntity.class);
    }
}

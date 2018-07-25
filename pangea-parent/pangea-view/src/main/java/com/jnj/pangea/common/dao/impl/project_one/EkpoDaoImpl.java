package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.EkpoEntity;

import java.util.List;

public class EkpoDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EKPO = "/project_one/ekpo";

    public static final String EBELN = "ebeln";

    private static EkpoDaoImpl instance;

    public static EkpoDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkpoDaoImpl();
        }
        return instance;
    }

    public List<EkpoEntity> getEkpoEntitiesByEbeln(String ebeln) {
        String queryString = QueryHelper.buildCriteria(EBELN).is(ebeln).toQueryString();
        return queryForList(PROJECT_ONE_EKPO, queryString, EkpoEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MaktEntity;

import java.util.List;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class ProjectOneMaktDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MAKT = "/project_one/makt";

    public static final String MATNR = "matnr";
    public static final String SPRAS = "spras";

    private static ProjectOneMaktDaoImpl instance;

    public static ProjectOneMaktDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMaktDaoImpl();
        }
        return instance;
    }

    public List<MaktEntity> getEntityWithMatnr(String matnr){
        List<MaktEntity> items = null;
        String queryEnString = QueryHelper.buildCriteria(MATNR).is(matnr).toQueryString();
        if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
            queryEnString = MATNR + ":\"" + AdfLuceneHelper.keyword(matnr) + "\"";
        }
        items = commonDao.queryForList(PROJECT_ONE_MAKT, queryEnString, MaktEntity.class);
        return items;
    }

    public MaktEntity getEntityWithMatnrAndSpras(String matnr, String spras){
        List<MaktEntity> items = null;
        String queryEnString = QueryHelper.buildCriteria(MATNR).is(matnr)
                .and(SPRAS).is(spras).toQueryString();

        return queryForObject(PROJECT_ONE_MAKT, queryEnString, MaktEntity.class);
    }
}

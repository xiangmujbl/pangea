package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.MaktEntity;

import java.util.List;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class ProjectOneMaktDaoImpl extends CommonDaoImpl {
    private static ProjectOneMaktDaoImpl instance;
    public static ProjectOneMaktDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMaktDaoImpl();
        }
        return instance;
    }

    public List<MaktEntity> getEntityWithMatnr(String matnr){
        List<MaktEntity> items = null;
        String queryEnString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MAKT.MATNR).is(matnr).toQueryString();
        if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
            queryEnString = IConstant.PROJECT_ONE_MAKT.MATNR + ":\"" + AdfLuceneHelper.keyword(matnr) + "\"";
        }
        items = commonDao.queryForList(IConstant.REGION.PROJECT_ONE_MAKT, queryEnString, MaktEntity.class);
        return items;
    }
}

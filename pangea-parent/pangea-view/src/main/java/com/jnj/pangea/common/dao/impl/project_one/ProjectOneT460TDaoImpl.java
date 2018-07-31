package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T460TEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class ProjectOneT460TDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T460T = "/project_one/t460t";

    public static final String SOBSL = "sobsl";
    public static final String SPRAS = "spras";

    private static ProjectOneT460TDaoImpl instance;

    public static ProjectOneT460TDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT460TDaoImpl();
        }
        return instance;
    }

    public T460TEntity getEntityWithSobslAndSpras(String sobsl, String spras) {
        String queryString = QueryHelper.buildCriteria(SOBSL).is(sobsl).and(SPRAS).is(spras).toQueryString();
        return commonDao.queryForObject(PROJECT_ONE_T460T,queryString,T460TEntity.class);
    }
}

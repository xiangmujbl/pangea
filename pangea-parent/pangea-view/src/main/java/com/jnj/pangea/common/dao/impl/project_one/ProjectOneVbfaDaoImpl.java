package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOneVbfaEntity;

/**
 * @Name: ProjectOneVbfaDaoImpl
 * @Description: dao for vbfa project one system
 * @author KG(Kelvin Gu)
 * @date 06-12-2018 03:49:18
*/
public class ProjectOneVbfaDaoImpl extends CommonDaoImpl {

    private static ProjectOneVbfaDaoImpl instance;

    public static ProjectOneVbfaDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneVbfaDaoImpl();
        }
        return instance;
    }

    public ProjectOneVbfaEntity getVbfaEntityByaAndb(String a, String b) {
        String queryString = QueryHelper.buildCriteria
             (IConstant.PROJECT_ONE_VBFA.A1).is(a)
             .and(IConstant.PROJECT_ONE_VBFA.B1).is(b)
            .toQueryString();
        ProjectOneVbfaEntity entity = queryForObject(IConstant.REGION.PROJECT_ONE_VBFA_CLONE, queryString, ProjectOneVbfaEntity.class);
        return entity;
    }
}

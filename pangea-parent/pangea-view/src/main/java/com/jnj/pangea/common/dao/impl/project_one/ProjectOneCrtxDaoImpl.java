package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKaktEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOneCrtxEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneCrtxDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_CRTX = "/project_one/crtx";

    public static final String OBJID = "objid";
    public static final String OBJTY = "objty";

    private static ProjectOneCrtxDaoImpl instance;

    public static ProjectOneCrtxDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneCrtxDaoImpl();
        }
        return instance;
    }

    public List<ProjectOneCrtxEntity> getEntityWithObjid(String objid,String objty) {
        if (StringUtils.isNotBlank(objid) && StringUtils.isNotBlank(objty)) {
            String queryString = QueryHelper.buildCriteria(OBJID).is(objid)
                    .and(OBJTY).is(objty)
                    .toQueryString();
            return queryForList(PROJECT_ONE_CRTX, queryString, ProjectOneCrtxEntity.class);
        }
        return null;
    }
}

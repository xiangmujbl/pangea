package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKaktEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOneCrtxEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneCrtxDaoImpl extends CommonDaoImpl {

    private static ProjectOneCrtxDaoImpl instance;

    public static ProjectOneCrtxDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneCrtxDaoImpl();
        }
        return instance;
    }

    public List<ProjectOneCrtxEntity> getEntityWithObjid(String objid) {
        if (StringUtils.isNotBlank(objid)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_CRTX.OBJID).is(objid).toQueryString();
            return queryForList(IConstant.REGION.PROJECT_ONE_CRTX, queryString, ProjectOneCrtxEntity.class);
        }
        return null;
    }
}

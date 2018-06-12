package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKaktEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneKaktDaoImpl extends CommonDaoImpl {

    private static ProjectOneKaktDaoImpl instance;

    public static ProjectOneKaktDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneKaktDaoImpl();
        }
        return instance;
    }

    public List<ProjectOneKaktEntity> getEntityWithKapid(String kapid) {
        if (StringUtils.isNotBlank(kapid)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_KAKT.KAPID).is(kapid).toQueryString();
            LogUtil.getCoreLog().info("------------------queryString-------------"+queryString);
            return queryForList(IConstant.REGION.PROJECT_ONE_KAKT, queryString, ProjectOneKaktEntity.class);
        }
        return null;
    }
}

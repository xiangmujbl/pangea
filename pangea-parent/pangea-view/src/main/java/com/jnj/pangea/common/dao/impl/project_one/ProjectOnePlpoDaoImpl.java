package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlpoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlpoDaoImpl extends CommonDaoImpl {

    private static ProjectOnePlpoDaoImpl instance;

    public static ProjectOnePlpoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlpoDaoImpl();
        }
        return instance;
    }

    public List<ProjectOnePlpoEntity> getEntityWithPlntyAndPlnnrAndPlnkn(String plnty, String plnnr, String plnkn) {
        if(StringUtils.isNotBlank(plnty)&&StringUtils.isNotBlank(plnnr)&&StringUtils.isNotBlank(plnkn)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLPO.PLNTY).is(plnty)
                    .and(IConstant.PROJECT_ONE_PLPO.PLNNR).is(plnnr)
                    .and(IConstant.PROJECT_ONE_PLPO.PLNKN).is(plnkn)
                    .toQueryString();
            return queryForList(IConstant.REGION.PROJECT_ONE_PLPO_CLONE, queryString, ProjectOnePlpoEntity.class,IConstant.PROJECT_ONE_PLPO.ZAEHL);
        }
        return null;
    }
}

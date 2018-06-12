package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
import org.apache.commons.lang3.StringUtils;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;

public class ProjectOnePlasDaoImpl extends CommonDaoImpl {

    private static ProjectOnePlasDaoImpl instance;

    public static ProjectOnePlasDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlasDaoImpl();
        }
        return instance;
    }

    public List<ProjectOnePlasEntity> getEntityWithPlntyAndPlnnrAndPlnkn(String plnty, String plnnr, String plnkn) {
        if(StringUtils.isNotBlank(plnty)&&StringUtils.isNotBlank(plnnr)&&StringUtils.isNotBlank(plnkn)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLAS.PLNTY).is(plnty)
                    .and(IConstant.PROJECT_ONE_PLAS.PLNNR).is(plnnr)
                    .and(IConstant.PROJECT_ONE_PLAS.PLNKN).is(plnkn)
                    .toQueryString();
            return queryForList(IConstant.REGION.PROJECT_ONE_PLAS, queryString, ProjectOnePlasEntity.class);
        }
        return null;
    }
}

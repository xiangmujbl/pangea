package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.StkoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneStkoDaoImpl extends CommonDaoImpl {
    private static ProjectOneStkoDaoImpl instance;

    public static ProjectOneStkoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneStkoDaoImpl();
        }
        return instance;
    }

    public List<StkoEntity> getEntityWithStlnrAndStlal(String stlnr,String stlal,String stlty){
        if (StringUtils.isNotBlank(stlnr) && StringUtils.isNotBlank(stlal) && StringUtils.isNotBlank(stlty)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_STKO.STLNR).is(stlnr).
                    and(IConstant.PROJECT_ONE_STKO.STLAL).is(stlal)
                    .and(IConstant.PROJECT_ONE_STKO.STLTY).is(stlty).toQueryString();

            return queryForList(IConstant.REGION.PROJECT_ONE_STKO_CLONE, queryString, StkoEntity.class);
        }
       return null;
    }
}

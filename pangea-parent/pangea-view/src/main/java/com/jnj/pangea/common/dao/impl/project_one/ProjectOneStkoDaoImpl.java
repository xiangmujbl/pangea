package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
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

    public List<StkoEntity> getEntityWithStlnrAndStlal(String stlnr,String stlal){
        if (StringUtils.isNotBlank(stlnr) && StringUtils.isNotBlank(stlal)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_STKO.STLNR).is(stlnr).
                    and(IConstant.PROJECT_ONE_STKO.STLAL).is(stlal).toQueryString();
            LogUtil.getCoreLog().info("----------queryString---------"+queryString);
            //return queryForList(IConstant.REGION.PROJECT_ONE_STKO, queryString, StkoEntity.class,IConstant.PROJECT_ONE_STKO.STKOZ);
            return queryForList(IConstant.REGION.PROJECT_ONE_STKO_CLONE, queryString, StkoEntity.class);
        }
        return null;
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.MchaEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneMchaDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MCHA = "/project_one/mcha";

    public static final String MATNR = "matnr";
    public static final String CHARG = "charg";

    private static ProjectOneMchaDaoImpl instance;

    public static ProjectOneMchaDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMchaDaoImpl();
        }
        return instance;
    }

    public List<MchaEntity> getEntityWithMatnrAndCharg(String matnr, String charg) {
        if(StringUtils.isNotBlank(matnr)&&StringUtils.isNotBlank(charg)){
            String queryString = QueryHelper.buildCriteria(MATNR).is(matnr)
                    .and(CHARG).is(charg).toQueryString();
            return queryForList(PROJECT_ONE_MCHA, queryString, MchaEntity.class);
        }
       return null;
    }
}

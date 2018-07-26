package com.jnj.pangea.common.dao.impl.project_one;


import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import com.jnj.pangea.common.entity.projectOne.ProjectOneMkalAendEntity;
import org.apache.commons.lang3.StringUtils;

public class ProjectOneMkalAendDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_MKAL_AEND = "/project_one/mkal_aend";

    public static final String FIELD_MATNR = "matnr";
    public static final String FIELD_WERKS = "werks";
    public static final String FIELD_VERID = "verid";

    private static ProjectOneMkalAendDaoImpl instance;

    public static ProjectOneMkalAendDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMkalAendDaoImpl();
        }
        return instance;
    }

    public ProjectOneMkalAendEntity getEntityWithConditions(String matnr,String werks,String verid) {

        ADFCriteria aDFCriteria=QueryHelper.buildCriteria(FIELD_MATNR);
        if(StringUtils.isNotBlank(matnr)){
            aDFCriteria.is(matnr);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(werks)){
            aDFCriteria.and(FIELD_WERKS).is(werks);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(verid)){
            aDFCriteria.and(FIELD_VERID).is(verid);
        }else{
            aDFCriteria.isNull();
        }
            return queryForObject(PROJECT_ONE_MKAL_AEND, aDFCriteria.toQueryString(), ProjectOneMkalAendEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.projectOne;


import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import com.jnj.pangea.common.entity.projectOne.ProjectOneMkalAendEntity;
import org.apache.commons.lang3.StringUtils;

public class ProjectOneMkalAendDaoImpl extends CommonDaoImpl {

    private static ProjectOneMkalAendDaoImpl instance;

    public static ProjectOneMkalAendDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMkalAendDaoImpl();
        }
        return instance;
    }

    public ProjectOneMkalAendEntity getEntityWithConditions(String matnr,String werks,String verid) {

        ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.MKAL_AEND.FIELD_MATNR);
        if(StringUtils.isNotBlank(matnr)){
            aDFCriteria.is(matnr);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(werks)){
            aDFCriteria.and(IConstant.MKAL_AEND.FIELD_WERKS).is(werks);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(verid)){
            aDFCriteria.and(IConstant.MKAL_AEND.FIELD_VERID).is(verid);
        }else{
            aDFCriteria.isNull();
        }
            return queryForObject(IConstant.REGION.PROJECT_ONE_MKAL_AEND, aDFCriteria.toQueryString(), ProjectOneMkalAendEntity.class);
    }
}

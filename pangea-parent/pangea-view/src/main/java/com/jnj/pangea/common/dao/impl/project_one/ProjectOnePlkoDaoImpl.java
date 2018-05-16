package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlabEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlkoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlkoDaoImpl  extends CommonDaoImpl {
    private static ProjectOnePlkoDaoImpl instance;

    public static ProjectOnePlkoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlkoDaoImpl();
        }
        return instance;
    }

    public List<ProjectOnePlkoEntity> getProjectOneMaplClone(String PLNTY, String PLNNR, String PLNAL){
        ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNNR);
        if(StringUtils.isNotBlank(PLNNR)){
            aDFCriteria.is(PLNNR);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(PLNTY)){
            aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNTY).is(PLNTY);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(PLNAL)){
            aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNAL).is(PLNAL);
        }else{
            aDFCriteria.isNull();
        }
        return queryForList(IConstant.REGION.PROJECT_ONE_PLKO_CLONE,aDFCriteria.toQueryString(),IConstant.MFG_RTNG_RLTNSHP.SOFT_ZAEHL_VALUE,ProjectOnePlkoEntity.class);
    }

}

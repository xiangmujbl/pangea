package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlkoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlkoDaoImpl  extends CommonDaoImpl {

    public static final String PROJECT_ONE_PLKO_CLONE = "/project_one/plko_clone";

    public static final String FIELD_NAME_PLNNR = "plnnr";
    public static final String FIELD_NAME_PLNTY = "plnty";
    public static final String FIELD_NAME_PLNAL = "plnal";
    public static final String FIELD_NAME_PLNKN = "plnkn";
    public static final String FIELD_NAME_KNNRN = "knnrn";
    public static final String SOFT_ZAEHL_VALUE = "zaehl";

    private static ProjectOnePlkoDaoImpl instance;

    public static ProjectOnePlkoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlkoDaoImpl();
        }
        return instance;
    }

    public List<PlkoEntity> getProjectOneMaplClone(String PLNTY, String PLNNR, String PLNAL){
        ADFCriteria aDFCriteria=QueryHelper.buildCriteria(FIELD_NAME_PLNNR);
        if(StringUtils.isNotBlank(PLNNR)){
            aDFCriteria.is(PLNNR);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(PLNTY)){
            aDFCriteria.and(FIELD_NAME_PLNTY).is(PLNTY);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(PLNAL)){
            aDFCriteria.and(FIELD_NAME_PLNAL).is(PLNAL);
        }else{
            aDFCriteria.isNull();
        }
        return queryForList(PROJECT_ONE_PLKO_CLONE,aDFCriteria.toQueryString(),SOFT_ZAEHL_VALUE,PlkoEntity.class);
    }

}

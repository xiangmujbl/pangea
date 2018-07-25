package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.StkoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOneStkoDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_STKO = "/project_one/stko";
    public static final String PROJECT_ONE_STKO_CLONE = "/project_one/stko_clone";

    public static final String MANDT = "mandt";
    public static final String STLTY = "stlty";
    public static final String STLNR = "stlnr";
    public static final String STLAL = "stlal";
    public static final String STKOZ = "stkoz";
    public static final String DATUV = "datuv";
    public static final String AENNR = "aennr";
    public static final String LOEKZ = "loekz";
    public static final String VGKZL = "vgkzl";
    public static final String ANDAT = "andat";
    public static final String AEDAT = "aedat";
    public static final String BMEIN = "bmein";
    public static final String BMENG = "bmeng";
    public static final String STKTX = "stktx";
    public static final String STLST = "stlst";

    private static ProjectOneStkoDaoImpl instance;

    public static ProjectOneStkoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneStkoDaoImpl();
        }
        return instance;
    }

    public List<StkoEntity> getEntityWithStlnrAndStlal(String stlnr,String stlal,String stlty){
        if (StringUtils.isNotBlank(stlnr) && StringUtils.isNotBlank(stlal) && StringUtils.isNotBlank(stlty)){
            String queryString = QueryHelper.buildCriteria(STLNR).is(stlnr).
                    and(STLAL).is(stlal)
                    .and(STLTY).is(stlty).toQueryString();

            return queryForList(PROJECT_ONE_STKO_CLONE, queryString, StkoEntity.class);
        }
       return null;
    }
}

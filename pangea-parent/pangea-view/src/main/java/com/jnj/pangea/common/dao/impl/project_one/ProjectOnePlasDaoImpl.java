package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
import org.apache.commons.lang3.StringUtils;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;

public class ProjectOnePlasDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_PLAS = "/project_one/plas";

    public static final String PLNTY = "plnty";
    public static final String PLNNR = "plnnr";
    public static final String PLNAL = "plnal";
    public static final String PLNFL = "plnfl";
    public static final String PLNKN = "plnkn";
    public static final String ZAEHL = "zaehl";
    public static final String DATUV = "datuv";
    public static final String LOEKZ = "loekz";
    public static final String ARNNR = "arnnr";

    private static ProjectOnePlasDaoImpl instance;

    public static ProjectOnePlasDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlasDaoImpl();
        }
        return instance;
    }

    public List<ProjectOnePlasEntity> getEntityWithPlntyAndPlnnrAndPlnkn(String plnty, String plnnr, String plnkn) {
        if(StringUtils.isNotBlank(plnty)&&StringUtils.isNotBlank(plnnr)&&StringUtils.isNotBlank(plnkn)){
            String queryString = QueryHelper.buildCriteria(PLNTY).is(plnty)
                    .and(PLNNR).is(plnnr)
                    .and(PLNKN).is(plnkn)
                    .toQueryString();
            return queryForList(PROJECT_ONE_PLAS, queryString, ProjectOnePlasEntity.class);
        }
        return null;
    }
}

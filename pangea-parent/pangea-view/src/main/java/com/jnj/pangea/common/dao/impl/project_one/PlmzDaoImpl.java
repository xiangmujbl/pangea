package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlmzEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlmzDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_PLMZ = "/project_one/plmz";
    public static final String PROJECT_ONE_PLMZ_CLONE = "/project_one/plmz_clone";

    public static final String PLNTY = "plnty";
    public static final String PLNNR = "plnnr";
    public static final String PLNAL = "plnal";
    public static final String ZUONR = "zuonr";
    public static final String ZAEHL = "zaehl";

    private static PlmzDaoImpl instance;

    public static PlmzDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlmzDaoImpl();
        }
        return instance;
    }
    public List<PlmzEntity> getEntityByPlntyPlnnrPlnalZuonr(String plnty,String plnnr,String plnal,String zuonr){
        if(StringUtils.isNotBlank(plnty) && StringUtils.isNotBlank(plnnr)
                && StringUtils.isNotBlank(plnal) && StringUtils.isNotBlank(zuonr)){
            String queryString = QueryHelper.buildCriteria(PLNTY).is(plnty).
                    and(PLNNR).is(plnnr)
                    .and(PLNAL).is(plnal).
                            and(ZUONR).is(zuonr).toQueryString();
            return queryForList(PROJECT_ONE_PLMZ_CLONE,queryString,PlmzEntity.class,ZAEHL);
        }
        return null;
    }
}

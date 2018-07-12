package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlmzEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlmzDaoImpl extends CommonDaoImpl {

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
            LogUtil.getCoreLog().info("--------------tiaojian------------"+plnty+"-"+plnnr+"-"+plnal+"-"+zuonr);
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLMZ.PLNTY).is(plnty).
                    and(IConstant.PROJECT_ONE_PLMZ.PLNNR).is(plnnr)
                    .and(IConstant.PROJECT_ONE_PLMZ.PLNAL).is(plnal).
                            and(IConstant.PROJECT_ONE_PLMZ.ZUONR).is(zuonr).toQueryString();
            return queryForList(IConstant.REGION.PROJECT_ONE_PLMZ_CLONE,queryString,PlmzEntity.class,IConstant.PROJECT_ONE_PLMZ.ZAEHL);
        }
        return null;
    }
}

package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryInputEntity;
import com.jnj.pangea.common.entity.edm.EDMFormV1Entity;
import org.eclipse.jetty.util.StringUtil;

public class EDMCountryInputDaoImpl extends CommonDaoImpl {

    private static EDMCountryInputDaoImpl instance;

    public static EDMCountryInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCountryInputDaoImpl();
        }
        return instance;
    }

    public EDMCountryInputEntity getEntityWithFormName(String sourcesystem,String localcountry) {
        if(StringUtil.isNotBlank(sourcesystem) && StringUtil.isNotBlank(localcountry)){
            String queryString = QueryHelper.buildCriteria(
                    IConstant.EDM_COUNT_INPUT.SOURCESYSTEM).is(sourcesystem)
                    .and(IConstant.EDM_COUNT_INPUT.LOCALCOUNTRY).is(localcountry)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_COUNTRY_INPUT, queryString, EDMCountryInputEntity.class);
        }else{
            return null;
        }
    }
}

package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;

public class EMSFMdmCurrenciesDaoImpl extends CommonDaoImpl {

    private static EMSFMdmCurrenciesDaoImpl instance;

    public static EMSFMdmCurrenciesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCurrenciesDaoImpl();
        }
        return instance;
    }

    public String getZnameWithzSourceSystemAndZcode(String zSourceSystem,String zCode) {
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is(zSourceSystem).and("zCode").is(zCode).toQueryString();
        EMSFMdmCurrenciesEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_Z_CURRENCIES_CLONE, countryQueryString, EMSFMdmCurrenciesEntity.class);
        if (null != emsfMdmCountriesEntity) {
            return emsfMdmCountriesEntity.getzName();
        }
        return "";
    }
}

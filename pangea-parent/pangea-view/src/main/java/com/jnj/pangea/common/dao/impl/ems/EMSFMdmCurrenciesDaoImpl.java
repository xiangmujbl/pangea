package com.jnj.pangea.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;

public class EMSFMdmCurrenciesDaoImpl extends CommonDaoImpl {

    public static final String EMS_F_Z_CURRENCIES_CLONE = "/ems/ems_f_z_currencies_clone";

    public static String Z_SOURCE_SYSTEM = "zSourceSystem";
    public static String Z_CODE = "zCode";

    private static EMSFMdmCurrenciesDaoImpl instance;

    public static EMSFMdmCurrenciesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCurrenciesDaoImpl();
        }
        return instance;
    }

    public EMSFMdmCurrenciesEntity getZnameWithzSourceSystemAndZcode(String zSourceSystem,String zCode) {
        String countryQueryString = QueryHelper.buildCriteria(Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(Z_CODE).is(zCode).toQueryString();
        EMSFMdmCurrenciesEntity emsfMdmCountriesEntity = queryForObject(EMS_F_Z_CURRENCIES_CLONE, countryQueryString, EMSFMdmCurrenciesEntity.class);
        return emsfMdmCountriesEntity;
    }
}

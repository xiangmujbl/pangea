package com.jnj.pangea.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;

public class EMSFMdmCurrenciesDaoImpl extends CommonDaoImpl {

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
        EMSFMdmCurrenciesEntity emsfMdmCountriesEntity = queryForObject(RegionsConstant.EMS_F_Z_CURRENCIES_CLONE, countryQueryString, EMSFMdmCurrenciesEntity.class);
        return emsfMdmCountriesEntity;
    }
}

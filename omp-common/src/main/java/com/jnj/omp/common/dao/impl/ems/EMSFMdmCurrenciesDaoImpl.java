package com.jnj.omp.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.ems.EMSFMdmCurrenciesEntity;

public class EMSFMdmCurrenciesDaoImpl extends CommonDaoImpl {

    private static EMSFMdmCurrenciesDaoImpl instance;

    public static EMSFMdmCurrenciesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCurrenciesDaoImpl();
        }
        return instance;
    }

    public EMSFMdmCurrenciesEntity getZnameWithzSourceSystemAndZcode(String zSourceSystem,String zCode) {
        String countryQueryString = QueryHelper.buildCriteria(IConstant.EMS_F_Z_CURRENCIES.Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(IConstant.EMS_F_Z_CURRENCIES.Z_CODE).is(zCode).toQueryString();
        EMSFMdmCurrenciesEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_Z_CURRENCIES_CLONE, countryQueryString, EMSFMdmCurrenciesEntity.class);
        return emsfMdmCountriesEntity;
    }
}

package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;

public class EMSFMdmUnitOfMeasureDaoImpl extends CommonDaoImpl {

    private static EMSFMdmUnitOfMeasureDaoImpl instance;

    public static EMSFMdmUnitOfMeasureDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmUnitOfMeasureDaoImpl();
        }
        return instance;
    }

    public String getMdmNameWithzSourceSystemAndMdmSapCode(String zSourceSystem,String zCode) {
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is(zSourceSystem).and("mdmSapCode").is(zCode).toQueryString();
        EMSFMdmUnitOfMeasureEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_MDM_UNITS_CLONE, countryQueryString, EMSFMdmUnitOfMeasureEntity.class);
        if (null != emsfMdmCountriesEntity) {
            return emsfMdmCountriesEntity.getMdmName();
        }
        return "";
    }
}

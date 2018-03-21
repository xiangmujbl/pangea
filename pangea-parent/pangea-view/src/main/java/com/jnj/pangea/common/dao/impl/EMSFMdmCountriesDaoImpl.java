package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;

public class EMSFMdmCountriesDaoImpl extends CommonDaoImpl {

    private static EMSFMdmCountriesDaoImpl instance;

    public static EMSFMdmCountriesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCountriesDaoImpl();
        }
        return instance;
    }

    public EMSFMdmCountriesEntity getMdmNameWithzSourceSystemAndMdmCode(String zSourceSystem,String zEntCodeIso3166Alpha2) {
        String countryQueryString = QueryHelper.buildCriteria(IConstant.EMS_F_MDM_COUNTRIES.ZSOURCESYSTEM)
                .is(zSourceSystem).and(IConstant.EMS_F_MDM_COUNTRIES.MDMCODE).is(zEntCodeIso3166Alpha2).toQueryString();
        EMSFMdmCountriesEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
        return emsfMdmCountriesEntity;
    }
}

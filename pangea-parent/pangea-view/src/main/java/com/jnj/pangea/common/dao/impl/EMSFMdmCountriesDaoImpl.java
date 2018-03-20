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

    public String getMdmNameWithzSourceSystemAndMdmCode(String zSourceSystem,String zEntCodeIso3166Alpha2) {
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is(zSourceSystem).and("mdmCode").is(zEntCodeIso3166Alpha2).toQueryString();
        EMSFMdmCountriesEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
        if (null != emsfMdmCountriesEntity) {
            return emsfMdmCountriesEntity.getMdmName();
        }
        return "";
    }
}

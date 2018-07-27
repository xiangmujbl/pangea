package com.jnj.pangea.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;

public class EMSFMdmCountriesDaoImpl extends CommonDaoImpl {

    public static final String EMS_F_MDM_COUNTRIES_CLONE = "/ems/ems_f_mdm_countries_clone";

    public static final String Z_SOURCE_SYSTEM = "zSourceSystem";
    public static final String MDM_CODE = "mdmCode";

    private static EMSFMdmCountriesDaoImpl instance;

    public static EMSFMdmCountriesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCountriesDaoImpl();
        }
        return instance;
    }

    public EMSFMdmCountriesEntity getMdmNameWithzSourceSystemAndMdmCode(String zSourceSystem, String zEntCodeIso3166Alpha2) {
        String countryQueryString = QueryHelper.buildCriteria(Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(MDM_CODE).is(zEntCodeIso3166Alpha2).toQueryString();
        return queryForObject(EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
    }
}

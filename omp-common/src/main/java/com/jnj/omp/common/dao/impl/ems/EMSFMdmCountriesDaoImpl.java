package com.jnj.omp.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.ems.EMSFMdmCountriesEntity;

public class EMSFMdmCountriesDaoImpl extends CommonDaoImpl {

    private static EMSFMdmCountriesDaoImpl instance;

    public static EMSFMdmCountriesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmCountriesDaoImpl();
        }
        return instance;
    }

    public EMSFMdmCountriesEntity getMdmNameWithzSourceSystemAndMdmCode(String zSourceSystem, String zEntCodeIso3166Alpha2) {
        String countryQueryString = QueryHelper.buildCriteria(IConstant.EMS_F_MDM_COUNTRIES.Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(IConstant.EMS_F_MDM_COUNTRIES.MDM_CODE).is(zEntCodeIso3166Alpha2).toQueryString();
        return queryForObject(IConstant.REGION.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
    }
}

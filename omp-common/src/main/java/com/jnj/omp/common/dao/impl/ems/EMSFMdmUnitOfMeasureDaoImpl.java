package com.jnj.omp.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.ems.EMSFMdmUnitOfMeasureEntity;

public class EMSFMdmUnitOfMeasureDaoImpl extends CommonDaoImpl {

    private static EMSFMdmUnitOfMeasureDaoImpl instance;

    public static EMSFMdmUnitOfMeasureDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmUnitOfMeasureDaoImpl();
        }
        return instance;
    }

    public EMSFMdmUnitOfMeasureEntity getMdmNameWithzSourceSystemAndMdmSapCode(String zSourceSystem,String zCode) {
        String countryQueryString = QueryHelper.buildCriteria(IConstant.EMS_F_MDM_UNITS.Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(IConstant.EMS_F_MDM_UNITS.MDM_SAP_CODE).is(zCode).toQueryString();
        EMSFMdmUnitOfMeasureEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_MDM_UNITS_CLONE, countryQueryString, EMSFMdmUnitOfMeasureEntity.class);
        return emsfMdmCountriesEntity;
    }
}

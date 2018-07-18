package com.jnj.pangea.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;

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
    public EMSFMdmUnitOfMeasureEntity getMdmNameWithzSourceSystemAndMdmSapCode(String zSourceSystem) {
        String countryQueryString = QueryHelper.buildCriteria(IConstant.EMS_F_MDM_UNITS.Z_SOURCE_SYSTEM)
                .is(zSourceSystem).and(IConstant.EMS_F_MDM_UNITS.MDM_SAP_CODE).isNull().toQueryString();
        EMSFMdmUnitOfMeasureEntity emsfMdmCountriesEntity = queryForObject(IConstant.REGION.EMS_F_MDM_UNITS_CLONE, countryQueryString, EMSFMdmUnitOfMeasureEntity.class);
        return emsfMdmCountriesEntity;
    }
}

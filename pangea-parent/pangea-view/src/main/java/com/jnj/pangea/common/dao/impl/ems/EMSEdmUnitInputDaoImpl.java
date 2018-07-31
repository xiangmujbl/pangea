package com.jnj.pangea.common.dao.impl.ems;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSEdmUnitInputEntity;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import org.apache.commons.lang3.StringUtils;

public class EMSEdmUnitInputDaoImpl extends CommonDaoImpl {

    public static final String EMS_EDM_UNIT_INPUT = "/ems/edm_unit_input";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_UOM = "localUom";
    
    private static EMSEdmUnitInputDaoImpl instance;

    public static EMSEdmUnitInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSEdmUnitInputDaoImpl();
        }
        return instance;
    }

    public EMSEdmUnitInputEntity queryEdmUnitInput(String sourceSystem,String mdmSapCode) {
        if(StringUtils.isNotEmpty(sourceSystem)&&StringUtils.isNotEmpty(mdmSapCode)){
            String countryQueryString = QueryHelper.buildCriteria(SOURCE_SYSTEM)
                    .is(sourceSystem).and(LOCAL_UOM).is(mdmSapCode).toQueryString();
            EMSEdmUnitInputEntity emsEdmUnitInputEntity = queryForObject(EMS_EDM_UNIT_INPUT, countryQueryString, EMSEdmUnitInputEntity.class);
            return emsEdmUnitInputEntity;
        }
        return null;
    }
}

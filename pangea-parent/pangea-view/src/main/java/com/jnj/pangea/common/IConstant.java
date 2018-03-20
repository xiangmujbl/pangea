package com.jnj.pangea.common;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public interface IConstant {

    interface REGION {
        // ------fail region
        String FAIL_DATA = "/plan/edm_failed_data";
        // -----region
        String EDM_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
        String EDM_MATERIAL_GLOBAL_V1 = "/edm/material_global_v1";
        String EDM_PLANT_V1 = "/edm/plant_v1";
        String EDM_MAT_PLANT_STAT_V1 = "/edm/mat_plant_stat_v1";
        String PROJECT_ONE_MAKT = "/project_one/makt";
        String NGEMS_MATERIAL_LINKAGE = "/ngems/material_linkage";
        String NGEMS_GOLDEN_MATERIAL = "/ngems/golden_material";
        String EMS_F_MDM_COUNTRIES_CLONE = "/ems/ems_f_mdm_countries_clone";
        String EMS_F_MDM_COUNTRIES = "/ems/ems_f_mdm_countries";
        String EMS_F_MDM_COUNTRIES_FAIL_DATA = "/plan/country_v1_failed_data";
        String EMS_F_Z_CURRENCIES_FAIL_DATA = "/plan/currency_v1_failed_data";
        String EMS_F_MDM_UNITS_FAIL_DATA = "/plan/unit_of_measure_v1_failed_data";
        String EMS_F_Z_CURRENCIES_CLONE = "/ems/ems_f_z_currencies_clone";
        String EMS_F_Z_CURRENCIES = "/ems/ems_f_z_currencies";
        String EMS_F_MDM_UNITS_CLONE = "/ems/ems_f_mdm_units_clone";
        String EMS_F_MDM_UNITS = "/ems/ems_f_mdm_units";
        String PROJECT_ONE_T001W = "/project_one/t001w";
        String PROJECT_ONE_T001K = "/project_one/t001k";
        String PROJECT_ONE_T001 = "/project_one/t001";
        String EDM_COUNTRY_V1 = "/edm/country_v1";
    }

    // -----region attribute
    interface EDM_SOURCE_SYSTEM_V1 {
        String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
    }

    interface PROJECT_ONE_T001W {
        String WERKS = "werks";
    }

    interface EDM_COUNTRY_V1 {
        String LOCAL_COUNTRY = "localCountry";
    }

    interface PROJECT_ONE_T001K {
        String BWKEY = "bwkey";
    }

    interface PROJECT_ONE_T001 {
        String BUKRS = "bukrs";
    }

    interface PROJECT_ONE_MAKT {
        String MATNR = "matnr";
    }

    interface NGEMS_MATERIAL_LINKAGE {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface NGEMS_GOLDEN_MATERIAL {
        String MATERIAL_NUMBER = "materialNumber";
    }

    String SOURCESYSTEM = "sourceSystem";
    String LOCALMATERIALNUMBER = "localMaterialNumber";
    String LOCALPLANT = "localPlant";
    String LOCALPLANTSTATUS = "localPlantStatus";

    // -----region attribute value
    interface VALUE {

        String PROJECT_ONE = "project_one";
        String EMS = "[EMS]";
        String EN = "E";
        String PT = "P";
        String SP = "s";
    }

}

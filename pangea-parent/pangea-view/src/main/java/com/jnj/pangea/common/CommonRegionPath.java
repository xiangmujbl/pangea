package com.jnj.pangea.common;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public interface CommonRegionPath {
    // ------fail region
    String FAIL_DATA = "/pangea/edm_failed_data";
    // -----region
    String EDM_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
    String EDM_MATERIAL_GLOBAL_V1 = "/edm/material_global_v1";
    String PANGEA_EDM_PLANT_V1="/edm/plant_v1";
    String PANGEA_EDM_MAT_PLANT_STAT_V1="/edm/mat_plant_stat_v1";

    String PROJECT_ONE_MAKT = "/project_one/makt";
    String NGEMS_MATERIAL_LINKAGE = "/ngems/material_linkage";
    String NGEMS_GOLDEN_MATERIAL = "/ngems/golden_material";
    String EMS_F_MDM_COUNTRIES_CLONE = "/ems/ems_f_mdm_countries_clone";
    String EMS_F_MDM_COUNTRIES = "/ems/ems_f_mdm_countries";

    String EMS_F_Z_CURRENCIES_CLONE = "/ems/ems_f_z_currencies_clone";
    String EMS_F_Z_CURRENCIES = "/ems/ems_f_z_currencies";

    String EMS_F_MDM_UNITS_CLONE = "/ems/ems_f_mdm_units_clone";
    String EMS_F_MDM_UNITS = "/ems/ems_f_mdm_units";

    String PROJECT_ONE_T001W = "/project_one/t001w";
    String PROJECT_ONE_T001K = "/project_one/t001k";
    String PROJECT_ONE_T001 = "/project_one/t001";
    String EDM_COUNTRY_V1 = "/edm/country_v1";


    // -----region attribute
    String LOCALSOURCESYSTEM="localSourceSystem";
    String SOURCESYSTEM="sourceSystem";
    String LOCALMATERIALNUMBER="localMaterialNumber";
    String LOCALPLANT="localPlant";
    String LOCALPLANTSTATUS="localPlantStatus";

    // -----region attribute value
    // localSourceSystem="project_one"
    String LOCALSOURCESYSTEM_PROJECT_ONE="project_one";

    // z_source_system = [EMS]
    String ZSOURCESYSTEM_EMS = "[EMS]";
}

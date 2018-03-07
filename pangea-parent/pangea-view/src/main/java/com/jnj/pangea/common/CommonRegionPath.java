package com.jnj.pangea.common;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public interface CommonRegionPath {
    // -----region
    String EDM_SOURCE_SYSTEM_V1 = "/pangea/edm/source_system_v1";
    String EDM_MATERIAL_GLOBAL_V1 = "/pangea/edm/material_global_v1";
    String PROJECT_ONE_MAKT = "/project_one/makt";
    String NGEMS_MATERIAL_LINKAGE = "/ngems/material_linkage";
    String NGEMS_GOLDEN_MATERIAL = "/ngems/golden_material";
    String EMS_F_MDM_COUNTRIES_CLONE = "/ems/ems_f_mdm_countries_clone";
    String EMS_F_MDM_COUNTRIES = "/ems/ems_f_mdm_countries";

    // -----region attribute
    String LOCALSOURCESYSTEM="localSourceSystem";

    // -----region attribute value
    // localSourceSystem="project_one"
    String LOCALSOURCESYSTEM_PROJECT_ONE="project_one";

    // z_source_system = [EMS]
    String ZSOURCESYSTEM_EMS = "[EMS]";
}

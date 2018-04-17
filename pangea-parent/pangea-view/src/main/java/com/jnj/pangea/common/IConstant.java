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
        String EMS_F_Z_CURRENCIES_CLONE = "/ems/ems_f_z_currencies_clone";
        String EMS_F_MDM_UNITS_CLONE = "/ems/ems_f_mdm_units_clone";
        String PROJECT_ONE_T001W = "/project_one/t001w";
        String PROJECT_ONE_T001K = "/project_one/t001k";
        String PROJECT_ONE_T001 = "/project_one/t001";
        String PROJECT_ONE_T460T = "/project_one/t460t";
        String EDM_COUNTRY_V1 = "/edm/country_v1";
        String EMS_F_MDM_MATERIAL_TYPES = "/ems/ems_f_mdm_material_types";
        String PROJECT_ONE_T439T = "/project_one/t439t";
        String PROJECT_ONE_T461X = "/project_one/t461x";
        String PROJECT_ONE_TMABCT = "/project_one/tmabct";
        String PLAN_CNS_PLAN_PARAMETER = "/plan/cns_plan_parameter";
        String PLAN_CNS_MATERIAL_INCL = "/plan/cns_material_incl";
        String PROJECT_ONE_VBEP = "/project_one/vbep";
        String PROJECT_ONE_VBAP = "/project_one/vbap";
        String PROJECT_ONE_VBPA = "/project_one/vbpa";
        String PROJECT_ONE_VBKD = "/project_one/vbkd";
        String CNS_PLAN_UNIT = "/plan/cns_plan_unit";
        String EDM_MATERIAL_AUOM_V1 = "/edm/material_auom_v1";
        String PLAN_CNS_PROD_LOC_ATTRIB = "/plan/cns_prod_loc_attrib";
        String PLAN_CNS_MATERIAL_PLAN_STATUS = "/plan/cns_material_plan_status";
        String EDM_PRODUCT_FAMILY_V1 = "/edm/product_family_v1";
        String EDM_FORM_V1 = "/edm/form_v1";
        String EDM_CATEGORY_V1 = "/edm/category_v1";
        String EDM_SUB_BRAND_V1 = "/edm/sub_brand_v1";
        String EDM_BRAND_V1 = "/edm/brand_v1";
        String EDM_FRANCHISE_V1 = "/edm/franchise_v1";
        String EDM_GLOBAL_BASE_UNIT_V1 = "/edm/global_base_unit_v1";
        String EDM_MAT_PLANT_FI_V1 = "/edm/mat_plant_fi_v1";
        String PLAN_CONS_TIME_DEP_XCHANGE = "/plan/cons_time_dep_xchange";
        String PLAN_CNS_PROC_TYP = "/plan/cns_proc_type";
        String PLAN_CNS_ABC_IND = "/plan/cns_abc_ind";
        String PLAN_CNS_SPL_PROC_TYP = "/plan/cns_spl_proc_typ";
        String PLAN_CNS_PLNG_STRAT_GRP = "/plan/cns_plng_strat_grp";
        String PLAN_CNS_CON_MODE = "/plan/cns_con_mode";
        String PLAN_CNS_LOT_SIZE_KEY_TRANS = "/plan/cns_lot_size_key_trans";
        String CNS_MATERIAL_PLAN_STATUS = "/plan/cns_material_plan_status";
        String PROJECT_ONE_T439A = "/project_one/t439a";
        String PROJECT_ONE_T439T_CLONE = "/project_one/t439t_clone";
        String PROJECT_ONE_PLAF = "/project_one/plaf";
        String PROJECT_ONE_PLAF_CLONE = "/project_one/plaf_clone";
        String PROJECT_ONE_EINE = "/project_one/eine";
        String PROJECT_ONE_MCHA = "/project_one/mcha";
        String PROJECT_ONE_MCHB = "/project_one/mchb";
        String PROJECT_ONE_AFPO = "/project_one/afpo";
        String PROJECT_ONE_AUFK = "/project_one/aufk";
        String PROJECT_ONE_JEST = "/project_one/jest";
        String PROJECT_ONE_TJ02T = "/project_one/tj02t";
    }

    interface PLAN_CNS_LOT_SIZE_KEY_TRANS {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_LOT_SIZE_KEY = "localLotSizeKey";
    }

    interface PLAN_CNS_CON_MODE {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_CONSUMPTION_MODE = "localConsumptionMode";
    }

    interface PLAN_CNS_PLNG_STRAT_GRP {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PLAN_STRAT_GRP = "localPlanStratGrp";
    }

    interface PROJECT_ONE_T439A {
        String DISLS = "disls";
    }

    interface PLAN_CNS_SPL_PROC_TYP {
        String LOCAL_SPL_PROC_TYPE = "localSplProcType";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface PLAN_CNS_PROD_LOC_ATTRIB {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface PLAN_CNS_ABC_IND {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_ABC_INDICATOR = "localAbcIndicator";
    }

    interface PLAN_CNS_PROC_TYP {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PROCUREMENT_TYPE = "localProcurementType";
    }

    interface EDM_SOURCE_SYSTEM_V1 {
        String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
    }

    interface PROJECT_ONE_TMABCT {
        String MAABC = "maabc";
        String SPARS = "spars";
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

    interface PROJECT_ONE_T460T {
        String SOBSL = "sobsl";
        String SPRAS = "spras";
    }

    interface PROJECT_ONE_MAKT {
        String MATNR = "matnr";
    }

    interface PROJECT_ONE_T439T {
        String DISLS = "disls";
        String SPRAS = "spras";
    }

    interface PROJECT_ONE_T461X {
        String STRGR = "strgr";
        String SPRAS = "spras";
    }

    interface PROJECT_ONE_VBAP {
        String VBELN = "vbeln";
    }

    interface PROJECT_ONE_VBEP {
        String VBELN = "vbeln";
        String POSNR = "posnr";
    }

    interface PROJECT_ONE_VBPA {
        String VBELN = "vbeln";
        String POSNR = "posnr";
        String PARVW = "parvw";
    }

    interface PROJECT_ONE_VBKD {
        String VBELN = "vbeln";
        String POSNR = "posnr";
        String PARVW = "parvw";
    }

    interface NGEMS_MATERIAL_LINKAGE {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface NGEMS_GOLDEN_MATERIAL {
        String MATERIAL_NUMBER = "materialNumber";
    }

    interface EMS_F_MDM_UNITS {
        String Z_SOURCE_SYSTEM = "zSourceSystem";
        String MDM_SAP_CODE = "mdmSapCode";
    }

    interface EMS_F_MDM_COUNTRIES {
        String Z_SOURCE_SYSTEM = "zSourceSystem";
        String MDM_CODE = "mdmCode";
    }

    interface EMS_F_Z_CURRENCIES {
        String Z_SOURCE_SYSTEM = "zSourceSystem";
        String Z_CODE = "zCode";
    }

    interface EDM_MAT_PLANT_STAT {
        String LOCAL_PLANT_STATUS = "localPlantStatus";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface CNS_PLAN_UNIT {
        String LOCAL_UOM = "localUom";
        String PLAN_FLAG = "planFlag";
    }


    interface EDM_MATERIAL_GLOBAL_V1 {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_TYPE = "localMaterialType";
        String LOCAL_BASE_UOM = "localBaseUom";
    }

    interface EDM_MATERIAL_AUOM_V1 {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_AUOM = "localAuom";
    }

    interface EDM_PLANT_V1 {
        String LOCAL_PLANT = "localPlant";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface CNS_PLAN_PARAMETER {
        String SOURCE_SYSTEM = "sourceSystem";
        String DATA_OBJECT = "dataObject";
        String ATTRIBUTE = "attribute";
        String PARAMETER = "parameter";
        String INCL_EXCL = "inclExcl";
    }

    interface CNS_MATERIAL_INCL {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String PLANNING_TYPE = "planningType";
    }

    interface CNS_PROD_LOC_ATTRIB {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface CNS_MATERIAL_PLAN_STATUS {

        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface EDM_MAT_PLANT_FI_V1 {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface EDM_PRODUCT_FAMILY_V1 {
        String PRODUCT_FAMILY = "productFamily";
    }

    interface EDM_FORM_V1 {
        String FORM = "formName";
    }

    interface EDM_CATEGORY_V1 {
        String CATEGORY = "category";
    }

    interface EDM_SUB_BRAND_V1 {
        String SUB_BRAND = "subBrand";
    }

    interface EDM_BRAND_V1 {
        String BRAND = "brand";
    }

    interface EDM_FRANCHISE_V1 {
        String FRANCHISE = "franchise";
    }

    interface EDM_GLOBAL_BASE_UNIT_V1 {
        String GBU = "gbu";
    }


    interface PLAN_CONS_TIME_DEP_XCHANGE {
        String UNIT_ID = "UnitId";
    }

    interface PROJECT_ONE_PLAF {
        String PLWRK = "plwrk";
        String PLSCN = "plscn";
    }

    interface PROJECT_ONE_EINE {
        String INFNR = "infnr";
    }

    interface PROJECT_ONE_MCHA {
        String MATNR = "matnr";
        String CHARG = "charg";
    }

    interface PROJECT_ONE_MCHB {
        String CHARG = "charg";
    }

    interface PROJECT_ONE_AFPO {
        String AUFNR = "aufnr";
    }

    interface PROJECT_ONE_AUFK {
        String AUFNR = "aufnr";
    }

    interface PROJECT_ONE_JEST {
        String OBJNR = "objnr";
        String STAT = "stat";
        String INACT = "inact";
    }

    interface PROJECT_ONE_TJ02T {
        String SPRAS = "spras";
        String ISTAT = "istat";
    }


    // -----region attribute value
    interface VALUE {

        String PROJECT_ONE = "project_one";
        String EMS = "[EMS]";
        String EN = "E";
        String PT = "P";
        String SP = "S";
        String CONS_LATAM = "CONS_LATAM";
        String CNS_MATERIAL_PLAN_STATUS = "cns_material_plan_status";
        String DP_RELEVANT = "DPRelevant";
        String SP_RELEVANT = "SPRelevant";
        String PLANT = "Plant";
        String MRP_TYPE = "MRPType";
        String I = "I";
        String MATERIAL_TYPE = "MaterialType";
        String X = "X";
        String NP = "NP";
        String Y = "Y";
        String YES = "YES";
        String NO = "NO";
        String WE = "WE";
        String LA_ = "LA_";
        String PGA = "PGA";
        String UNDERLINE = "_";
        String BACK_SLANT = "/";
        String USD = "USD";
        String PANGEA = "Pangea";
        String LINE = "-";
        String DPSP = "DPSP";
        String DP = "DP";
        String SP1 = "SP";
        String V = "V";
        String C = "C";
        String S = "S";
        String SPRAS_EN = "EN";
        String PLSCN = "000";
        String YYYYMMDD = "yyyyMMdd";
        String DD_MM_YYYY = "dd/MM/yyyy";
        String N="N";
        String BLANK="Blank";
        String OR = "OR";
        String SPACE = " ";
    }

    interface FAILED {

        interface FUNCTIONAL_AREA {

            String DP = "DP";
            String SP = "SP";
        }

        interface INTERFACE_ID {

            String OMP_GDM_PRODUCT_LOCATION_DETAIL = "OMPGdmProductLocationDetail";
            String OMP_GDM_PRODUCT = "OMPGdmProduct";
            String OMP_GDM_PRODUCT_LOCATION = "OMPGdmProductLocation";
            String GDM_UNIT_MEASURABLE = "GdmUnitMeasurable";
            String GDM_PRODUCT_COUNTRY = "GDMProductCountry";
            String OMP_GDM_BATCH="OMPGdmBatch";
            String EDM_PLANNED_ORDER="EdmPlannedOrder";
            String EDM_BATCH_MASTER="EdmBatchMaster";
        }

        interface ERROR_CODE {

            String T1 = "T1";

            String C1 = "C1";

            String J1 = "J1";
            String J2 = "J2";
            String J3 = "J3";
            String J4 = "J4";

            String E3 = "E3";
            String E4 = "E4";
            String E5 = "E5";
            String E6 = "E6";
            String E7 = "E7";
            String E8 = "E8";
            String E9 = "E9";

            String N2 = "N2";
            String N1 = "N1";
            String N4 = "N4";
        }
    }
}

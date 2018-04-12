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
        String PROJECT_ONE_VBEP="/project_one/vbep";
        String PROJECT_ONE_VBAP="/project_one/vbap";
        String PROJECT_ONE_VBPA="/project_one/vbpa";
        String PROJECT_ONE_VBKD="/project_one/vbkd";
        String CNS_PLAN_UNIT = "/plan/cns_plan_unit";
        String EDM_MATERIAL_AUOM_V1="/edm/material_auom_v1";
        String PLAN_CNS_PROD_LOC_ATTRIB ="/plan/cns_prod_loc_attrib";
        String PLAN_CNS_FIN_PLAN_VAL = "/plan/cns_fin_plan_val";
        String PLAN_CNS_FIN_PLAN_QTY = "/plan/cns_fin_plan_qty";
        String EDM_CURRENCY_V1 = "/edm/currency_v1";
        String PLAN_CNS_MATERIAL_PLAN_STATUS = "/plan/cns_material_plan_status";
        String PLAN_CNS_PROD_CTY_AFFL="/plan/cns_prod_cty_affl";
        String PLAN_CNS_CERT_DETER="/plan/cns_cert_deter";
        String PLAN_CNS_DEM_GRP_ASGN="/plan/cns_dem_grp_asgn";
        String PROJECT_ONE_KNVH="/project_one/knvh";
        String PROJECT_ONE_TVRO="/project_one/tvro";
        String PLAN_CNS_ORD_REJ="/plan/cns_ord_rej";
    }

    interface EDM_SOURCE_SYSTEM_V1 {
        String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
    }

    interface project_one_tmabct {
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
       String VBELN="vbeln";
    }
    interface PROJECT_ONE_VBEP {
        String VBELN="vbeln";
        String POSNR="posnr";
    }

    interface PROJECT_ONE_VBPA {
        String VBELN="vbeln";
        String POSNR="posnr";
        String PARVW="parvw";
    }
    interface PROJECT_ONE_VBKD {
        String VBELN="vbeln";
        String POSNR="posnr";
        String PARVW="parvw";
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

    interface PLAN_CNS_FIN_PLAN_VAL {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String IDENTIFIER = "identifier";
    }
    interface PLAN_CNS_FIN_PLAN_QTY {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String IDENTIFIER = "identifier";
    }
    interface EDM_CURRENCY_V1 {
        String LOCAL_CURRENCY = "localCurrency";
    }
    interface PLAN_CNS_MATERIAL_PLAN_STATUS {
        String DP_RELEVANT = "dpRelevant";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
    }
    interface PLAN_CNS_PROD_CTY_AFFL{
        String SOURCE_SYSTEM = "sourceSystem";
    }
    interface PLAN_CNS_CERT_DETER{
        String SALES_ORG = "salesOrg";
        String ORDER_TYPE = "orderType";
        String ITEM_CATEGORY = "itemCategory";
    }
    interface PLAN_CNS_DEM_GRP_ASGN{
        String CUSTOMER_ID = "customerId";
        String SALES_ORGANIZATION = "salesOrganization";
    }
    interface PROJECT_ONE_KNVH{
        String KUNNR = "kunnr";
        String VKORG = "vkorg";
        String DATBI = "datbi";
    }
    interface PROJECT_ONE_TVRO{
        String ROUTE="route";
    }
    interface PLAN_CNS_ORD_REJ{
        String SALES_ORG="salesOrg";
        String REJ_CD="rejCd";
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
        String WE="WE";
        String LA_="LA_";
        String PGA = "PGA";
        String UNDERLINE = "_";
        String BACK_SLANT = "/";
        String USD="USD";
        String PANGEA = "Pangea";
        String FPB = "FPB";
        String SAPR = "SAPR";
        String SAMPLE = "SAMPLE";
        String REGULAR = "REGULAR";
        String ACTIVE = "ACTIVE";
        String BASE = "BASE";
        String ZERO = "0";
    }
}

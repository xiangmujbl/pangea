package com.jnj.pangea.common;

import org.stringtemplate.v4.ST;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public interface IConstant {

    interface REGION {

        // -----region
        String EDM_CAPY_HDR = "/edm/capy_hdr/edm/capy_hdr";
        String EDM_WRK_CTR = "/edm/wrk_ctr";
        String PROJECT_ONE_Qave = "/project_one/qave";
        String EDM_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";
        String EDM_SOURCE_LIST_V1 = "/edm/source_list_v1";
        String EDM_MATERIAL_GLOBAL_V1 = "/edm/material_global_v1";
        String EDM_PLANT_V1 = "/edm/plant_v1";
        String EDM_CURRENCY_V1 = "/edm/currency_v1";
        String EDM_MATERIAL_PLANT_V1 = "/edm/material_plant_v1";
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
        String PROJECT_ONE_MKAL_AEND = "/project_one/mkal_aend";
        String EDM_COUNTRY_V1 = "/edm/country_v1";
        String EMS_F_MDM_MATERIAL_TYPES = "/ems/ems_f_mdm_material_types";
        String PROJECT_ONE_T439T = "/project_one/t439t";
        String PROJECT_ONE_T461X = "/project_one/t461x";
        String PROJECT_ONE_TMABCT = "/project_one/tmabct";
        String PLAN_CNS_PLAN_PARAMETER = "/plan/cns_plan_parameter";
        String PLAN_CNS_MATERIAL_INCL = "/plan/cns_material_incl";
        String PLAN_PROD_LOC_MIN_SHELF = "/plan/prod_loc_min_shelf";
        String PROJECT_ONE_VBEP = "/project_one/vbep";
        String PROJECT_ONE_VBAP = "/project_one/vbap";
        String PROJECT_ONE_VBPA = "/project_one/vbpa";
        String PROJECT_ONE_VBKD = "/project_one/vbkd";
        String CNS_PLAN_UNIT = "/plan/cns_plan_unit";
        String EDM_MATERIAL_AUOM_V1 = "/edm/material_auom_v1";
        String PLAN_CNS_PROD_LOC_ATTRIB = "/plan/cns_prod_loc_attrib";
        String PLAN_CNS_FIN_PLAN_VAL = "/plan/cns_fin_plan_val";
        String PLAN_CNS_FIN_PLAN_QTY = "/plan/cns_fin_plan_qty";
        String PLAN_CNS_CUST_CHANNEL = "/plan/cns_cust_channel";
        String PLAN_CNS_DP_PRICE_CLONE = "/plan/cns_dp_price_clone";
        String PLAN_CNS_MATERIAL_PLAN_STATUS = "/plan/cns_material_plan_status";
        String PLAN_CNS_PROD_CTY_AFFL = "/plan/cns_prod_cty_affl";
        String PLAN_CNS_CLUSTERS = "/plan/cns_clusters";
        String PLAN_CNS_DEM_GRP_ASGN = "/plan/cns_dem_grp_asgn";
        String PLAN_CNS_CERT_DETER = "/plan/cns_cert_deter";
        String PROJECT_ONE_KNVH = "/project_one/knvh";
        String PROJECT_ONE_TVRO = "/project_one/tvro";
        String PLAN_CNS_ORD_REJ = "/plan/cns_ord_rej";
        String PLAN_CNS_CUST_EXCL = "/plan/cns_cust_excl";
        String PLAN_CNS_SO_TYPE_INCL = "/plan/cns_so_type_incl";
        String PLAN_CNS_PLANT_ATTR = "/plan/cns_plant_attr";
        String PLAN_CNS_COUNTRY_INPUT = "/plan/cns_country_input";
        String PLAN_CNS_PLAN_REGION = "/plan/cns_plan_region";
        String EDM_PRODUCT_FAMILY_V1 = "/edm/product_family_v1";
        String EDM_FORM_V1 = "/edm/form_v1";
        String EDM_CATEGORY_V1 = "/edm/category_v1";
        String EDM_SUB_BRAND_V1 = "/edm/sub_brand_v1";
        String EDM_BRAND_V1 = "/edm/brand_v1";
        String EDM_FRANCHISE_V1 = "/edm/franchise_v1";
        String EDM_BOM_HDR = "/edm/bom_hdr";
        String EDM_GLOBAL_BASE_UNIT_V1 = "/edm/global_business_unit_v1";
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
        String PROJECT_ONE_MSLB = "/project_one/mslb";
        String PROJECT_ONE_MKOL = "/project_one/mkol";
        String PROJECT_ONE_MARD = "/project_one/mard";
        String PROJECT_ONE_AFPO = "/project_one/afpo";
        String PROJECT_ONE_AUFK = "/project_one/aufk";
        String PROJECT_ONE_JEST = "/project_one/jest";
        String PROJECT_ONE_PLAS = "/project_one/plas";
        String PROJECT_ONE_PLPO = "/project_one/plpo";
        String PROJECT_ONE_PLPO_CLONE = "/project_one/plpo_clone";
        String PROJECT_ONE_TJ02T = "/project_one/tj02t";
        String PROJECT_ONE_STKO = "/project_one/stko";
        String PROJECT_ONE_STKO_CLONE = "/project_one/stko_clone";
        String CNS_SPL_PLN_LOC = "/plan/cns_spl_pln_loc";
        String PLAN_CNS_PLN_SPL_LOC = "/plan/cns_spl_pln_loc";
        String PLAN_CNS_PROCESS_TYPE = "/plan/cns_process_type";
        String PLAN_CNS_PLAN_OBJECT_FILTER = "/plan/cns_plan_object_filter";
        String PROJECT_ONE_EKPO = "/project_one/ekpo";
        String PROJECT_ONE_EKKO = "/project_one/ekko";
        String PROJECT_ONE_EKES = "/project_one/ekes";
        String PROJECT_ONE_EKET = "/project_one/eket";
        String PROJECT_ONE_EKPV = "/project_one/ekpv";
        String PROJECT_ONE_EKBE = "/project_one/ekbe";
        String PLAN_CNS_SPL_PLN_LOC = "/plan/cns_spl_pln_loc";
        String CNS_TLANE_ITEM_EXCEPTION = "/plan/cns_tlane_item_exception";
        String CNS_TLANE_ITEM = "/plan/cns_tlane_item";
        String PLAN_CONS_TIME_DEP_XCHANGE_CLONE = "/plan/cons_time_dep_xchange_clone";
        String EDM_JNJ_CALENDAR_V1 = "/edm/jnj_calendar_v1";
        String EDM_UNIT_OF_MEASURE_V1 = "/edm/unit_of_measure_v1";
        String PROJECT_ONE_LIKP = "/project_one/likp";
        String PROJECT_ONE_LIPS = "/project_one/lips";
        String EDM_MATERIAL_GLOBAL_V1_CLONE = "/edm/material_global_v1_clone";
        String PROJECT_ONE_MAPL_CLONE = "/project_one/mapl_clone";
        String PROJECT_ONE_STAS = "/project_one/stas";
        String PROJECT_ONE_PLAB_CLONE = "/project_one/plab_clone";
        String PROJECT_ONE_PLKO_CLONE = "/project_one/plko_clone";
        String PROJECT_ONE_PLFL = "/project_one/plfl";
        String PROJECT_ONE_PLFL_CLONE = "/project_one/plfl_clone";
        String PROJECT_ONE_KAKT = "/project_one/kakt";
        String PROJECT_ONE_KAKO = "/project_one/kako";
        String PROJECT_ONE_CRTX = "/project_one/crtx";
        String PLAN_CNS_ROOT_DESCRIPTION = "/plan/cns_root_description";
        String PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE = "/plan/cns_root_description_user_override";
        String MATL_PROD_VERSN = "/edm/matl_prod_versn";
        String MATL_BOM = "/edm/matl_bom";
        String MATERIAL_PLANT_V1 = "/edm/material_plant_v1";
        String BOM_ITEM = "/edm/bom_item";
        String BOM_HDR = "/edm/bom_hdr";
        String CNS_PLAN_PARAMETER = "/plan/cns_plan_parameter";
        String MATL_MFG_RTNG = "/edm/matl_mfg_rtng";
        String MFG_RTNG_ITM_NDE = "/edm/mfg_rtng_itm_nde";
        String MFG_RTNG_ITM = "/edm/mfg_rtng_itm";
        String PLAN_CNS_TLANE_CONTROL = "/plan/cns_tlane_control";
        String MFG_RTNG_HDR = "/edm/mfg_rtng_hdr";
        String EDM_MFG_ORDER_ITM = "/edm/mfg_order_itm";
        String EDM_MATL_PROD_VERSN = "/edm/matl_prod_versn";
        String EDM_RESERV_ITM = "/edm/reserv_itm";
        String EDM_MFG_ORDER_RTNG = "/edm/mfg_order_rtng";
        String EDM_MFG_ORDER_SEQ = "/edm/mfg_order_seq";
        String EDM_BOM_ITEM = "/edm/bom_item";
        String EDM_COUNTRY_INPUT="/edm/edm_country_input";
        String EDM_INVENTORY_STOCK = "/edm/inventory_stock";
    }

    interface EDM_INVENTORY_STOCK {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_BATCH_ID = "localBatchId";
        String LOCAL_MATERIAL = "localMaterial";
    }

    interface BOM_HDR {
        String SRCSYSCD = "srcSysCd";
        String ALTBOMNUM = "altBomNum";
        String BOMNUM = "bomNum";
        String BOMCATCD = "bomCatCd";
    }

    interface MFG_RTNG_ITM_NDE {
        String SRCSYSCD = "srcSysCd";
        String RTNGTYPCD = "rtngTypCd";
        String RTNGNDENUM = "rtngNdeNum";
        String RTNGGRPCNTRNBR = "rtngGrpCntrNbr";
        String RTNGGRPCD = "rtngGrpCd";
    }

    interface MFG_RTNG_ITM {
        String RTNGTYPCD = "rtngTypCd";
        String RTNGITMNUM = "rtngItmNum";
        String SRCSYSCD = "srcSysCd";
        String RTNGGRPCD = "rtngGrpCd";
        String OPERNUM = "operNum";
    }

    interface PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_DP_PARENT_CODE = "localDpParentCode";
    }

    interface PLAN_CNS_ROOT_DESCRIPTION {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_DP_PARENT_CODE = "localDpParentCode";
    }

    interface EDM_JNJ_CALENDAR_V1 {
        String FISCALPERIOD = "fiscalPeriod";
    }

    interface PLAN_CONS_TIME_DEP_XCHANGE_CLONE {
        String FROM_CURRENCY = "fromCurrency";
    }

    interface PLAN_CNS_MATERIAL_PLAN_STATUS {
        String DP_RELEVANT = "dpRelevant";

        String SP_RELEVANT = "spRelevant";
        String NO_PLAN_RELEVANT = "noPlanRelevant";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PLANT = "localPlant";
        String LOCAL_PARENT_CODE = "localParentCode";
        String MATERIAL_NUMBER = "materialNumber";
        String ACTIVE = "active";
        String FIELD_X = "X";
    }

    interface PLAN_PROD_LOC_MIN_SHELF {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface PLAN_CNS_PLN_SPL_LOC {
        String LOCAL_NUMBER = "localNumber";
        String VENDOR_OR_CUSTOMER = "vendorOrCustomer";
        String SOURCE_SYSTEM = "sourceSystem";
        String VENDOR_PURCHASE = "vendorPurchase";
        String EXTERNAL_PURCHASE = "externalPurchase";
    }

    interface EDM_SOURCE_SYSTEM_V1 {
        String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface PLAN_CNS_FIN_PLAN_VAL {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
        String SP_RELEVANT = "sPRelevant";
        String NO_PLAN_RELEVANT = "noPlanRelevant";
        String IDENTIFIER = "identifier";
    }

    interface MATL_PROD_VERSN {
        String SRCSYSCD = "srcSysCd";
        String PLNTCD = "plntCd";
        String MATLNUM = "matlNum";
        String ALTBOMNUM = "altBomNum";
    }

    interface EDM_MATERIAL_PLANT_V1 {
        String LOCAL_PLANT = "localPlant";
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String PRIMARY_PLANNING_CODE = "primaryPlanningCode";
    }

    interface EDM_CURRENCY_V1 {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_CURRENCY = "localCurrency";
    }

    interface MKAL_AEND {
        String FIELD_MATNR = "matnr";
        String FIELD_WERKS = "werks";
        String FIELD_VERID = "verid";

    }

    interface PLAN_CNS_FIN_PLAN_QTY {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String IDENTIFIER = "identifier";
    }

    interface PLAN_CNS_PLANT_ATTR {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PLANT = "localPlant";
    }

    interface PLAN_CNS_LOT_SIZE_KEY_TRANS {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_LOT_SIZE_KEY = "localLotSizeKey";
    }

    interface PLAN_CNS_CON_MODE {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_CONSUMPTION_MODE = "localConsumptionMode";
    }
    interface PLAN_CNS_COUNTRY_INPUT {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_COUNTRY = "localCountry";
    }
    interface PLAN_CNS_PLAN_REGION {
        String PLANNING_REGION_ID  = "planningRegionID";
    }


    interface EDM_SOURCE_LIST_V1 {
        String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PLANT = "localPlant";
        String MATERIAL_NUMBER = "materialNumber";
        String LOCAL_BLOCKED_SOURCE_OF_SUPPLY = "localBlockedSourceofSupply";
        String LOCAL_SOURCE_LIST_RECORD_VALID_FROM = "localSourceListRecordValidFrom";
        String LOCAL_SOURCE_LIST_RECORD_VALID_TO = "localSourceListRecordValidTo";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_BLOCKED_SOURCEOF_SUPPLY = "localBlockedSourceofSupply";
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

    interface PLAN_CNS_ABC_IND {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_INDICATOR = "localIndicator";
    }

    interface PLAN_CNS_PROC_TYP {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_PROCUREMENT_TYPE = "localProcurementType";
    }

    interface PROJECT_ONE_LIKP {
        String VBELN = "vbeln";
    }

    interface PROJECT_ONE_LIPS {
        String VBELN = "vbeln";
    }

    interface PROJECT_ONE_TMABCT {
        String MAABC = "maabc";
        String SPARS = "spars";
    }

    interface PLAN_CNS_DP_PRICE {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    }

    interface PROJECT_ONE_T001W {
        String WERKS = "werks";
    }

    interface EDM_COUNTRY_V1 {
        String LOCAL_COUNTRY = "localCountry";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface EDM_WRK_CTR {
        String SRC_SYS_CD = "srcSysCd";
        String CAPY_NUM = "capyNum";
    }

    interface EDM_CAPY_HDR {
        String CAP_CAT_CD = "capyCatCd";
    }


    interface PROJECT_ONE_KAKT {
        String KAPID = "kapid";

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
        String SPRAS = "spras";
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

    interface PROJECT_ONE_Qave {
        String PRUEFLOS = "prueflos";
    }

    interface PROJECT_ONE_VBKD {
        String VBELN = "vbeln";
        String POSNR = "posnr";
        String PARVW = "parvw";
    }

    interface NGEMS_MATERIAL_LINKAGE {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String MATERIAL_NUMBER = "materialNumber";
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
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface EDM_MATERIAL_GLOBAL_V1 {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_TYPE = "localMaterialType";
        String LOCAL_BASE_UOM = "localBaseUom";
        String PRIMARY_PLANNING_CODE = "primaryPlanningCode";
        String MATERIAL_NUMBER = "materialNumber";
        String LOCAL_DP_PARENT_CODE = "localDpParentCode";
    }

    interface EDM_MATERIAL_AUOM_V1 {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String MATERIAL_NUMBER = "materialNumber";
        String LOCAL_AUOM = "localAuom";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface EDM_PLANT_V1 {
        String LOCAL_PLANT = "localPlant";
        String SOURCE_SYSTEM = "sourceSystem";
        String COUNTRY = "country";
    }

    interface CNS_PLAN_PARAMETER {
        String SOURCE_SYSTEM = "sourceSystem";
        String DATA_OBJECT = "dataObject";
        String ATTRIBUTE = "attribute";
        String PARAMETER = "parameter";
        String INCL_EXCL = "inclExcl";
        String PARAMETER_VALUE = "parameterValue";
        String LOCAL_MATERIALNUMBER_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface CNS_MATERIAL_INCL {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String PLANNING_TYPE = "planningType";
        String LOCAL_PLANT = "localPlant";
        String INCLUSION_TYPE = "inclusionType";
    }

    interface CNS_PROD_LOC_ATTRIB {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
    }

    interface CNS_MATERIAL_PLAN_STATUS {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
        String SP_RELEVANT = "spRelevant";
        String NO_PLAN_RELEVANT = "noPlanRelevant";
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

    interface PLAN_CNS_PROD_LOC_ATTRIB {
        String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
        String LOCAL_PLANT = "localPlant";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface EDM_FRANCHISE_V1 {
        String FRANCHISE = "franchise";
    }

    interface EDM_GLOBAL_BASE_UNIT_V1 {
        String GBU = "gbu";
    }

    interface PLAN_CONS_TIME_DEP_XCHANGE {
        String UNIT_ID = "unitId";
        String FROM_CURRENCY = "fromCurrency";
        String TO_CURRENCY = "toCurrency";
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
        String MATNR = "matnr";
        String WERKS = "werks";
    }

    interface PROJECT_ONE_MKOL {
        String MATNR = "matnr";
        String WERKS = "werks";
    }

    interface PROJECT_ONE_MSLB {
        String MATNR = "matnr";
        String WERKS = "werks";
    }

    interface PROJECT_ONE_MARD {
        String MATNR = "matnr";
        String WERKS = "werks";
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

    interface PROJECT_ONE_EKPO {
        String EBELN = "ebeln";
    }

    interface PROJECT_ONE_EKBE {
        String EBELN = "ebeln";
        String EBELP = "ebelp";
    }

    interface PLAN_CNS_PROCESS_TYPE {
        String PROCESS_TYPE_ID = "processTypeId";
        String PROCESS_TYPE_DESCRIPTION = "processTypeDesc";
    }

    interface PLAN_CNS_PLAN_OBJECT_FILTER {
        String SOURCE_OBJECT_TECH_NAME = "sourceObjectTechName";
        String SOURCE_SYSTEM = "sourceSystem";
        String SOURCE_OBJECT_TECHNAME = "sourceObjectTechName";
        String SOURCE_OBJECT_ATTRIBUTE1 = "sourceObjectAttribute1";
        String SOURCE_OBJECT_ATTRIBUTE1_VALUE = "sourceObjectAttribute1Value";
        String SOURCE_OBJECT_PLANT_ATTRIBUTE = "sourceObjectPlantAttribute";
        String SOURCE_FILTER_PLANT_VALUE = "sourceFilterPlantValue";
    }

    interface PLAN_CNS_PROD_CTY_AFFL {
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface PLAN_CNS_CERT_DETER {
        String SALES_ORG = "salesOrg";
        String ORDER_TYPE = "orderType";
        String ITEM_CATEGORY = "itemCategory";
    }

    interface PLAN_CNS_CLUSTERS {
        String COUNTRY_ID = "countryId";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface PLAN_CNS_DEM_GRP_ASGN {
        String CUSTOMER_ID = "customerId";
        String SALES_ORG = "salesOrg";
        String CUSTOMER_SHIP_TO = "customerShipTo";
        String SOURCE_SYSTEM = "sourceSystem";
        String DEMAND_GROUP = "demandGroup";
    }

    interface PROJECT_ONE_KNVH {
        String HKUNNR = "hkunnr";
        String KUNNR = "kunnr";
        String VKORG = "vkorg";
        String DATBI = "datbi";
    }

    interface PROJECT_ONE_TVRO {
        String ROUTE = "route";
    }

    interface PLAN_CNS_ORD_REJ {
        String SALES_ORG = "salesOrg";
        String REJ_CD = "rejCd";
    }

    interface PLAN_CNS_CUST_EXCL {
        String SALES_ORG = "salesOrg";
        String CUSTOMER_SHIP_TO = "customerShipTo";
    }

    interface EDM_CURRENCY {
        String LOCAL_CURRENCY = "localCurrency";
    }

    interface PLAN_CNS_CUST_CHANNEL {
        String CHANNEL = "channel";
        String SALES_ORG = "salesOrg";
        String SOURCE_SYSTEM = "sourceSystem";
    }

    interface PLAN_CNS_SO_TYPE_INCL {
        String SALES_ORG = "salesOrg";
        String ORDER_TYPE = "orderType";
    }

    interface CNS_TLANE_ITEM_EXCEPTION {
        String REF_SEQ_NUM_TLANE_ITEM = "refSeqNumTlaneItem";
        String MATERIAL_NUMBER = "materialNumber";
        String TLANE_NAME = "tlaneName";
    }

    interface PLAN_CNS_SPL_PLN_LOC {
        String SOURCE_SYSTEM = "sourceSystem";
        String LOCALNUMBER = "localNumber";
        String VENDORORCUSTOMER = "vendorOrCustomer";
        String LOCAL_PLANT = "localPlant";
    }

    interface CNS_TLANE_ITEM {

    }

    interface EDM_UNIT_OF_MEASURE_V1 {
        String UOM = "uom";
        String SOURCESYSTEM = "sourceSystem";
    }

    interface PROJECT_ONE_PLFL {
        String PLNNR = "plnnr";
        String PLNAL = "plnal";
        String PLNFL = "plnfl";
        String ZAEHL = "zaehl";
    }

    interface PROJECT_ONE_STKO {
        String MANDT = "mandt";
        String STLTY = "stlty";
        String STLNR = "stlnr";
        String STLAL = "stlal";
        String STKOZ = "stkoz";
        String DATUV = "datuv";
        String AENNR = "aennr";
        String LOEKZ = "loekz";
        String VGKZL = "vgkzl";
        String ANDAT = "andat";
        String AEDAT = "aedat";
        String BMEIN = "bmein";
        String BMENG = "bmeng";
        String STKTX = "stktx";
        String STLST = "stlst";
    }

    interface PROJECT_ONE_PLAS {
        String PLNTY = "plnty";
        String PLNNR = "plnnr";
        String PLNAL = "plnal";
        String PLNFL = "plnfl";
        String PLNKN = "plnkn";
        String ZAEHL = "zaehl";
        String DATUV = "datuv";
        String LOEKZ = "loekz";
        String ARNNR = "arnnr";
    }

    interface PROJECT_ONE_PLPO {
        String PLNTY = "plnty";
        String PLNNR = "plnnr";
        String PLNKN = "plnkn";
        String ZAEHL = "zaehl";
    }

    interface EDM_MATERIAL_GLOBAL_V1_CLONE {
        String LOCAL_DP_PARENT_CODE = "localDpParentCode";
    }

    interface MATL_MFG_RTNG {
        String FIELD_LOEKZ_VALUE_X = "x";
        String FIELD_MATLRTNGVALID_TO = "9999/12/31";
        String FIELD_NAME_PLNNR = "plnnr";
        String FIELD_NAME_PLNTY = "plnty";
        String FIELD_NAME_PLNAL = "plnal";
        String FIELD_NAME_ZKRIZ = "zkriz";
        String SRCSYSCD = "srcSysCd";
        String RTNGTYPCD = "rtngTypCd";
        String RTNGGRPCNTRNUM = "rtngGrpcntrNum";
        String PLNTCD = "plntCd";
        String RNTGGRPCNTRNBR = "rntgGrpCntrNbr";
        String MATLNUM = "matlNum";
        String RNTGGRPCD = "rntgGrpCd";
        String RTNGGRPCD = "rtngGrpCd";
        String FIELD_MATLRTNGVALID_TO_NULL = "";
    }

    interface BOM_ITEM {
        String FIELD_STAS_STLTY_VALUE = "stlty";
        String FIELD_MATLRTNGVALID_TO = "9999/12/31";
        String FIELD_STAS_STLNR_VALUE = "stlnr";
        String FIELD_STAS_STLKN_VALUE = "stlkn";
        String FIELD_STAS_STASZ_VALUE = "stasz";
        String FIELD_LEKNZ_VALUE_X = "x";
        String BOMNUM = "bomNum";
        String SRCSYSCD = "srcSysCd";
        String BOMCATCD = "bomCatCd";

    }

    interface PROJECT_ONE_CRTX {
        String OBJID = "objid";
        String OBJTY = "objty";
    }

    interface MFG_RTNG_RLTNSHP {
        String FIELD_LOEKZ_VALUE_X = "x";
        String FIELD_MATLRTNGVALID_TO = "9999/12/31";
        String FIELD_NAME_PLNNR = "plnnr";
        String FIELD_NAME_PLNTY = "plnty";
        String FIELD_NAME_PLNAL = "plnal";
        String FIELD_NAME_PLNKN = "plnkn";
        String FIELD_NAME_KNNRN = "knnrn";
        String FIELD_PLNTY_VALUE_2 = "2";
        String FIELD_PLNTY_VALUE_N = "N";
        String SOFT_ZAEHL_VALUE = "zaehl";
    }


    interface MFG_RTNG_HDR {
        String FIELD_LOEKZ_VALUE_X = "x";
        String FIELD_MATLRTNGVALID_TO = "9999/12/31";
        String FIELD_NAME_PLNNR = "plnnr";
        String FIELD_NAME_PLNTY = "plnty";
        String FIELD_NAME_PLNAL = "plnal";
        String FIELD_NAME_PLNKN = "plnkn";
        String FIELD_NAME_KNNRN = "knnrn";
        String FIELD_PLNTY_VALUE_2 = "2";
        String FIELD_PLNTY_VALUE_N = "N";
        String SOFT_ZAEHL_VALUE = "zaehl";
        String SRCSYSCD = "srcSysCd";
        String RTNGTYPCD = "rtngTypCd";
        String RTNGGRPCD = "rtngGrpCd";
        String RTNGGRPCNTRNBR = "rtngGrpCntrNbr";
    }

    interface PLAN_CNS_TLANE_CONTROL {
        String CRITICAL_PARAMETER = "criticalParameter{0}";
        String CRITICAL_PARAMETER_HIGH = "criticalParameter{0}High";
        String CRITICAL_PARAMETER_TABLE = "criticalParameter{0}Table";
        String CRITICAL_PARAMETER_FIELD = "criticalParameter{0}Field";
        String CRITICAL_PARAMETER_LOW = "criticalParameter{0}Low";
        String CRITICAL_PARAMETER_OPERATOR = "criticalParameter{0}Operator";
        String CRITICAL_PARAMETER_IE = "criticalParameter{0}IE";
    }

    interface LFU {
        String DATA_OBJECT = "dataObject";
        String SPLIT = "-";
        String SPLIT_ = "_";
        String CHCEK_TIME = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-9]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
        String PATTERN_DECIMAL_3="#.000";
        String PATTERN_DECIMAL_5="#.00000";
        String PATTERN_DECIMAL_0="#";
        int VALUE_DECIMAL_3=3;
        String VALUE_BLACK="";
        String FAIL_MSG="localDpParentCode does not exist in edm Material";
    }

    interface  EDM_COUNT_INPUT{
        String SOURCESYSTEM= "sourceSystem";
        String LOCALCOUNTRY ="localCountry" ;
        String LOCALCURRENCY="localCurrency";

    }

    // -----region attribute value
    interface VALUE {
        String LATAM_ROOT = "LATAM_ROOT";
        String LATAM_SKU = "LATAM_SKU";
        String LATAM_TECH = "LATAM_TECH";
        String PROJECT_ONE = "project_one";
        String PROJECT_ONE_DEV = "Project_One";
        String EMS = "[EMS]";
        String EN = "E";
        String PT = "P";
        String SP = "S";
        String CONS_LATAM = "CONS_LATAM";
        String CNS_MATERIAL_PLAN_STATUS = "cns_material_plan_status";
        String DP_RELEVANT = "DPRelevant";
        String SP_RELEVANT = "SPRelevant";
        String NO_PLAN_RELEVANT = "NoPlanRelevant";
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
        String HORIZONTAL_Line = "-";
        String BACK_SLANT = "/";
        String USD = "USD";
        String PANGEA = "Pangea";
        String FPB = "FPB";
        String LINE = "-";
        String DPSP = "DPSP";
        String DP = "DP";
        String SP1 = "SP";
        String V = "V";
        String C = "C";
        String S = "S";
        String M = "M";
        String SPRAS_EN = "EN";
        String PLSCN = "000";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy/MM/dd";
        String YYYYMMDD_ZERO = "00000000";
        String YYYY_MM_DD_ZERO = "0000/00/00";
        String HH_NN_SS_ZERO = " 00:00:00";
        String YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
        String YYYYDDMMHHMMSS = "yyyy/DD/mm HH:mm:ss";
        String YYYYMMDDBS = "yyyy/MM/dd";
        String YYYYMMDD_WITH_DASH = "yyyy-MM-dd";
        String MMDYYYY = "MM/d/yyyy";
        String DDMMYYYY = "dd/MM/yyyy";
        String N = "N";
        String BLANK = "";
        String OR = "OR";
        String SPACE = " ";
        String CURRENCY = "CURRENCY";
        String ZERO = "0";
        String BA = "BA";
        String FALSE = "FALSE";
        String COLON = ":";
        String TRANSPORT = "TRANSPORT";
        String STAR = "*";
        String LFU = "LFU";
        String SAPR = "SAPR";
        String SAMPLE = "SAMPLE";
        String REGULAR = "REGULAR";
        String ACTIVE = "ACTIVE";
        String BASE = "BASE";
        String STR_ONE = "001";
        String STR_TWO = "002";
        String STR_NINE = "009";
        String OMP = "omp";
        String CNS_SALES_HISTORY = "CNS_SalesHistory";
        String RESTRICT_SELECT = "restrictSelect";
        String INITIAL_SELECT = "initialSelect";
        String LESS_MONTH = "lessMonth";
        String SALESPRICE = "SALESPRICE";
        String DIVISION = "Division";
        String PSMS_STATUS = "PSMSStatus";
        String MRP_CONTROLLER = "MrpController";
        String SEND_TO_OMP = "SEND_TO_OMP";
        String CRITICAL_ROH = "Critical-ROH";
        String CURRENCY_V = "1.0";
        String ONE = "1";
        String TWO = "2";
        String AAAADL = "AAAADL";
        String CNS_PRODUCT_INCLUSION = "cns_product_inclusion";
        String LOCAL_MATERIAL_NUMBER = "LocalMaterialNumber";
        String LESS_DAYS = "LessDays";
        String BOM_VlD_ToDt = "9999/12/31";
        String EN_CAPY = "E";
        String ES_CAPY = "S";
        String PT_CAPY = "P";
        String FBP = "FBP";
        String PATTERN_DIGITAL = "^-?[1-9]\\d*$";
        String SEVEN = "7";
        String VENDOR_TRANSPORT = "VendorTransport";
        String SUBCONTRACTING_TRANSPORT = "SubcontractingTransport";
        String INTERNAL_TRANSPORT = "InternalTransport";
        String SUPPLY = "SUPPLY";
        String INFINITE = "INFINITE";
        String ASTERIX = "*";
        String DEFAULT = "DEFAULT";
        String CNS_PROD_LOC_ATTRIB = "cns_prod_loc_attrib";
        String MATERIAL_PLANT = "material_plant";
        String SOURCE_LIST = "source_list";
        String INCLUSION = "I";
        String EXCLUSION = "E";
        String SOURCE_SYSTEM_CRITICAL_PARAMETERS = "sourceSystemCriticalParameters";
        String SOURCE_SYSTEM = "sourceSystem";
        String SELECT = "select";
        String FROM = "from";
        String WHERE = "WHERE";
        String AND = "AND";
        String OPERATOR_EQUAL = "=";
        String OPERATOR_LESS_THAN = "<";
        String OPERATOR_LESS_THAN_EQUAL = "<=";
        String OPERATOR_GREATER_THAN = ">";
        String OPERATOR_GREATER_THAN_EQUAL = ">=";
        String OPERATOR_NOT_EQUAL = "!=";
        String LOCAL_PLANT = "localPlant";
        String LOCAL_MATERIAL_NUMBER_FIREST_LOWER = "localMaterialNumber";
        String BR = "BR";
        String BATCH_START = "batchstart";
        String BATCH_END = "batchend";
        String CONTINU = "continu";
        String PROPORTIONAL = "proportional";
        String FIXED = "fixed";
        String ENDEFF = "2998/12/31 23:59:59";
        String START_EFF = "1980/01/01 00:00:00";
        String BOM_86400 = "86400";
        String VOLUMEPLANNING = "VolumePlanning";
        String DETAILEDSCHEDULING = "DetailedScheduling";
        String PROPORTIONAL_BACK_SLANT = "/proportional";
        String END_EFF_CHECK = "2998/12/31";
        String START_EFF_CHECK = "1980/01/01";


    }

    interface FAILED {

        interface FUNCTIONAL_AREA {

            String DP = "DP";
            String SP = "SP";
            String PP = "PP";
        }

        interface INTERFACE_ID {
            String GDM_FBP = "GDMFbp";
            String OMP_GDMPRODUCT_TYPE = "OMPGdmProductType";
            String GDM_RESOURCE = "GDMResource";
            String OMP_GDM_PRODUCT_LOCATION_DETAIL = "OMPGdmProductLocationDetail";
            String OMP_GDM_PRODUCT = "OMPGdmProduct";
            String OMP_GDM_PRODUCT_UNIT_CONVERSION = "OMPGdmProductUnitConversion";
            String GDM_UNIT_CURRENCY = "GdmUnitCurrency";
            String GDM_UNIT_MEASURABLE = "GdmUnitMeasurable";
            String GDM_PRODUCT_COUNTRY = "GDMProductCountry";
            String OMP_GDM_BATCH = "OMPGdmBatch";
            String EDM_BATCH_MASTER = "EdmBatchMaster";
            String GDM_UNIT_EVOL = "GDMUnitEvol";
            String EDM_SALES_ORDER = "EDMSalesOrder";
            String OMP_GDM_CLUSTER = "OMPGdmCluster";
            String PLAN_CNS_MATERIAL_PLAN_STATUS = "PlanCnsMaterialPlanStatus";
            String OMP_GDM_SALES_HISTORY = "OMPGdmSalesHistory";
            String OMP_GDM_CONVERSION_STORAGE = "OMPGdmConversionStorage";
            String OMP_GDM_LOCATION_EDM = "OMPGdmLocationEdm";
            String PLAN_CNS_TLANE_ITEM = "PlanCnsTlaneItem";
            String OMP_GDM_POS = "OMPGdmPos";
            String OMP_GDM_LFU = "OMPGdmLfu";
            String OMP_GDM_COUNTRY="OMPGdmCountry";
        }

        interface ERROR_CODE {

            String T1 = "T1";
            String T2 = "T2";
            String T4 = "T4";
            String T5 = "T5";
            String T8 = "T8";
            String C1 = "C1";
            String C2 = "C2";
            String C5 = "C5";
            String J1 = "J1";
            String J2 = "J2";
            String E1 = "E1";
            String E2 = "E2";
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
            String F1 = "F1";
            String F2 = "F2";
            String F2F3 = "F2F3";
            String T10 = "T10";
        }

        interface ERROR_VALUE {

            String OMP_GDM_PRODUCT_LOCATION_DETAIL_N2 = "Not Planning Relevant";
            String OMP_GDM_PRODUCT_LOCATION_DETAIL_E2 = "Missing Primary Plannig Code and Enterprise Material Number";
        }

        interface SOURCE_SYSTEM {

            String PROJECT_ONE = "project_one";
        }
    }

    interface OMP_GDMBOMELEMENT {
        interface EDM_MFG_ORDER_ITM {
            public static final String FIELD_SRCSYSCD = "srcSysCd";
            public static final String FIELD_MFGORDRNUM = "mfgOrdrNum";
        }

        interface MATL_PROD_VERSN {
            public static final String FIELD_SRCSYSCD = "srcSysCd";
            public static final String FIELD_MATLNUM = "matlNum";
            public static final String FIELD_PLNTCD = "plntCd";
            public static final String FIELD_PRDNTVRSNNUM = "prdntVrsnNum";
        }

        interface RESERV_ITM {
            public static final String FIELD_SOURCESYSCD = "sourceSysCd";
            public static final String FIELD_RSRVTNNUM = "rsrvtnNum";
        }

        interface MFG_ORDER_RTNG {
            public static final String FIELD_SOURCESYSCD = "srcSysCd";
            public static final String FIELD_ORDRRTNGNUM = "ordrRtngNum";
        }

        interface MFG_ORDER_SEQ {
            public static final String FIELD_SOURCESYSCD = "sourceSysCd";
            public static final String FIELD_ORDRRTNGNUM = "ordrRtngNum";
            public static final String FIELD_SCSYSCD = "srcSysCd";
        }

        interface BOM_ITEM {
            public static final String FIELD_SRCSYSCD = "srcSysCd";
            public static final String FIELD_BOMCATCD = "bomCatCd";
            public static final String FIELD_BOMNUM = "bomNum";
            public static final String FIELD_DSTRBTNKEYCD_NAME = "GLEI";
            public static final String FIELD_DSTRBTNKEYCD_VALUE = "continu";
            public static final String FIELD_DSTRBTNKEYCD_MAT_VALUE = "batchend";
            public static final String FIELD_DSTRBTNKEYCD_BLACK_VALUE = "batchstart";
            public static final String FIELD_DSTRBTNKEYCD_ELSE_VALUE = "";
        }

        public static final String KEY_SPLIT = "/";

        public static final String QTYFXIND_X = "X";
        public static final String QTYFXIND_X_VALUE = "fixed";
        public static final String QTYFXIND_BLACK = "proportional";
        public static final String QTYFXIND_MAT = "proportional";


        public static final String LEAVE_BLACK = "";

        public static final String PATTERN_POINTER = ".";

        public static final String PATTERN_ = "-";

        public static final String PATTERN_0P000 = "0.000";

        public static final String ENDEFF = "31/12/2998 23:59:59";
        public static final String STARTEFF = "01/01/1980 00:00:00";
        public static final String __SPLIT = "_";
        public static final String FRONT_BOMID_PRO_LOW = "pro";
        public static final String FRONT_BOMID_PRO_UP = "PRO";
        public static final String DEFAULT_VALUE_YES = "YES";
        public static final String DEFAULT_VALUE_NO = "NO";
        public static final long DEFAULT_VALUE_LEADTIMEOFFSET = 864000;
        public static final String DEFAULT_VALUE_PLANLEVELID_BLANK = "*";
        public static final String DEFAULT_VALUE_0_BLANK = "0";
        public static final char DEL_0 = '0';
        public static final String DEFAULT_VALUE_PlanLevelId_X = "DetailedScheduling";
        public static final String CLONE_BOMEID = "proportional";
        public static final String CLONE_PLANLEVELID = "VolumePlanning";
        public static final String PATTERN_DECIMAL_5 = "#.00000";
        public static final String PATTERN_DECIMAL_3 = "#.000";
        public static final String INTERFACEID="OMPGdmbomelementProcess";
        public static final String  PRODUCTID_FAIL_MSG="Unable to construct ProductId";


    }

    interface FAILDATA {
        public static final String ERRORCODE = "500";
        public static final String NA = "NA";
        public static final String PRODUCTID_FAIL_MSG = "Unable to construct ProductId";


    }

    interface RKPF {
        String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})/(0?[13578]|1[02])/(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})/(0?[13456789]|1[012])/(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})/0?2/(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))/0?2-29))$";
    }

}
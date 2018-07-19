package com.jnj.pangea.common;

/**
 * Reserved for Fail constants
 * Replace old IConstant
 */
public class FailConstant {

    interface FUNCTIONAL_AREA {

        String DP = "DP";
        String SP = "SP";
        String PP = "PP";
    }

    interface INTERFACE_ID {
        String OMP_GDM_FBP = "OMPGdmFbp";
        String OMP_GDMPRODUCT_TYPE = "OMPGdmProductType";
        String GDM_RESOURCE = "GDMResource";
        String OMP_GDM_PRODUCT_LOCATION_DETAIL = "OMPGdmProductLocationDetail";
        String OMP_GDM_PRODUCT = "OMPGdmProduct";
        String OMP_GDM_PRODUCT_DETAIL = "OMPGdmProductDetail";
        String OMP_GDM_PRODUCT_UNIT_CONVERSION = "OMPGdmProductUnitConversion";
        String GDM_UNIT_CURRENCY = "GdmUnitCurrency";
        String GDM_UNIT_MEASURABLE = "GdmUnitMeasurable";
        String GDM_PRODUCT_COUNTRY = "GDMProductCountry";
        String OMP_GDM_PRODUCTCUSTOMER = "GDMProductCustomer";
        String OMP_GDM_BATCH = "OMPGdmBatch";
        String EDM_BATCH_MASTER = "EdmBatchMaster";
        String GDM_UNIT_EVOL = "GDMUnitEvol";
        String EDM_SALES_ORDER = "EDMSalesOrder";
        String OMP_GDM_CLUSTER = "OMPGdmCluster";
        String PLAN_CNS_MATERIAL_PLAN_STATUS = "PlanCnsMaterialPlanStatus";
        String OMP_GDM_SALES_HISTORY = "OMPGdmSalesHistory";
        String OMP_GDM_CONVERSION_STORAGE = "OMPGdmConversionStorage";
        String OMP_GDM_LOCATION_EDM = "OMPGdmLocationEdm";
        String OMP_GDM_STOCK_ASN = "OMPGdmStockASN";
        String OMP_GDM_DEMAND_OBD = "OMPGdmDemandOBD";
        String PLAN_CNS_TLANE_ITEM = "PlanCnsTlaneItem";
        String OMP_GDM_POS = "OMPGdmPos";
        String OMP_GDM_LFU = "OMPGdmLfu";
        String OMP_GDM_COUNTRY="OMPGdmCountry";
        String EDM_PLANT = "EDMPlant";
    }

    interface ERROR_CODE {

        String T1 = "T1";
        String T2 = "T2";
        String T4 = "T4";
        String T5 = "T5";
        String T6 = "T6";
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
        String ASN9 = "ASN9";
        String OBD13 = "OBD13";
        String OBD16 = "OBD16";
    }

    interface ERROR_VALUE {

        String OMP_GDM_PRODUCT_LOCATION_DETAIL_N2 = "Not Planning Relevant";
        String OMP_GDM_PRODUCT_LOCATION_DETAIL_E2 = "Missing Primary Plannig Code and Enterprise Material Number";
    }

    interface SOURCE_SYSTEM {

        String PROJECT_ONE = "project_one";
    }



}

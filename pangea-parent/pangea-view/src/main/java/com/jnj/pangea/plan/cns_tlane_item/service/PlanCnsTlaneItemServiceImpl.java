package com.jnj.pangea.plan.cns_tlane_item.service;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceListV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import com.jnj.pangea.plan.cns_tlane_item.bo.PlanCnsTlaneItemBo;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.*;

public class PlanCnsTlaneItemServiceImpl {


    private static PlanCnsTlaneItemServiceImpl instance;

    public static PlanCnsTlaneItemServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsTlaneItemServiceImpl();
        }
        return instance;
    }

    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private PlanCnsPlnSplLocDaoImpl planCnsPlnSplLocDao = PlanCnsPlnSplLocDaoImpl.getInstance();
    private EDMMaterialPlantV1DaoImpl edmMaterialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl edmMaterialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private EDMSourceListV1DaoImpl edmSourceListV1Dao = EDMSourceListV1DaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<>();

        PlanCnsTlaneControlEntity planCnsTlaneControlEntity = (PlanCnsTlaneControlEntity) o;
        Map map = (Map) o2;

        String destinationLocationWithOutSystem = "";
        String originLocationWithOutSystem = "";

        String destinationLocation = planCnsTlaneControlEntity.getDestinationLocation();
        String originLocation = planCnsTlaneControlEntity.getOriginLocation();
        String sourceSystem = planCnsTlaneControlEntity.getSourceSystemCriticalParameters();

        if (StringUtils.isNotEmpty(sourceSystem)) {
            if (StringUtils.isNotEmpty(destinationLocation)) {
                destinationLocationWithOutSystem = destinationLocation.replace(sourceSystem + IConstant.VALUE.UNDERLINE, IConstant.VALUE.BLANK);
            }
            if (StringUtils.isNotEmpty(originLocation)) {
                originLocationWithOutSystem = originLocation.replaceAll(sourceSystem + IConstant.VALUE.UNDERLINE, IConstant.VALUE.BLANK);
            }
        } else {
            LogUtil.getCoreLog().info("================= sourceSystem is null ");
        }


        //N1
        String processTypeId = "";
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(originLocationWithOutSystem)) {

            String[] temp = originLocationWithOutSystem.split(IConstant.VALUE.UNDERLINE);
            if (temp.length > 1) {
                String vendorOrCustomer = temp[0];
                originLocationWithOutSystem = originLocationWithOutSystem.replaceAll(vendorOrCustomer + IConstant.VALUE.UNDERLINE, "");
            }
            String localNumber = fixNumber(originLocationWithOutSystem, 10);
            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity = planCnsPlnSplLocDao.getEntityWithSourceSystemAndLocalNumber(sourceSystem, localNumber);
            if (null != planCnsPlnSplLocEntity) {

                if (StringUtils.isEmpty(planCnsPlnSplLocEntity.getLocalPlant()) || StringUtils.isBlank(planCnsPlnSplLocEntity.getLocalPlant())) {
                    processTypeId = IConstant.VALUE.VENDOR_TRANSPORT;
                } else {
                    processTypeId = IConstant.VALUE.SUBCONTRACTING_TRANSPORT;
                }


            } else {
                if (StringUtils.isNotBlank(destinationLocationWithOutSystem)) {
                    EDMPlantV1Entity plantV1Entity = plantV1Dao.getEntityWithLocalPlant(destinationLocationWithOutSystem);
                    EDMPlantV1Entity plantV1Entity1 = plantV1Dao.getEntityWithLocalPlant(originLocationWithOutSystem);

                    if (null != plantV1Entity && null != plantV1Entity1) {
                        processTypeId = IConstant.VALUE.INTERNAL_TRANSPORT;
                    }
                }
            }
        }


        //both come from plant_v1
        if (StringUtils.isNotBlank(processTypeId) && IConstant.VALUE.INTERNAL_TRANSPORT.equals(processTypeId)) {

            List<String> materialList = completingAllCriticalParameters(map);
            for (String localMaterialNumber : materialList) {
                ResultObject resultObject = new ResultObject();

                PlanCnsTlaneItemBo planCnsTlaneItemBo = new PlanCnsTlaneItemBo();
                planCnsTlaneItemBo.setSequenceNumber(planCnsTlaneControlEntity.getSequenceNumber());
                planCnsTlaneItemBo.setTlaneName(planCnsTlaneControlEntity.getTlaneName());
                planCnsTlaneItemBo.setValidFrom(planCnsTlaneControlEntity.getValidFrom());
                planCnsTlaneItemBo.setValidTo(planCnsTlaneControlEntity.getValidTo());
                planCnsTlaneItemBo.setOriginLocation(planCnsTlaneControlEntity.getOriginLocation());
                planCnsTlaneItemBo.setDestinationLocation(planCnsTlaneControlEntity.getDestinationLocation());
                planCnsTlaneItemBo.setMode(planCnsTlaneControlEntity.getMode());
                planCnsTlaneItemBo.setLeadTime(planCnsTlaneControlEntity.getLeadTime());
                planCnsTlaneItemBo.setProcessTypeId(processTypeId);

                List<PlanCnsMaterialPlanStatusEntity> planStatusEntityList =
                        planCnsMaterialPlanStatusDao.getEntityListWithConditions(sourceSystem, localMaterialNumber, destinationLocationWithOutSystem,
                                IConstant.VALUE.X, IConstant.VALUE.X);

                //if value of Field (cns_material_plan_status-spRelevant) = X Or (cns_material_plan_status_v1-noPlanRelevant) = X
                if (null != planStatusEntityList && planStatusEntityList.size() > 0) {
                    //not sure whether 1:1,but if add sourceSystem into condition , there will be 1:1
                    EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithSourceSystemAndLocalMaterialNumber(sourceSystem, localMaterialNumber);

                    if (null != edmMaterialGlobalV1Entity) {

                        if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                            planCnsTlaneItemBo.setMaterialNumber(edmMaterialGlobalV1Entity.getMaterialNumber());
                            resultObject.setBaseBo(planCnsTlaneItemBo);
                            resultObjectList.add(resultObject);
                        } else if (edmMaterialGlobalV1Entity.getPrimaryPlanningCode().equals(edmMaterialGlobalV1Entity.getMaterialNumber())) {
                            planCnsTlaneItemBo.setMaterialNumber(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
                            resultObject.setBaseBo(planCnsTlaneItemBo);
                            resultObjectList.add(resultObject);
                        }
                        // If (material_global_v1-primaryPlanningCode) not equal to (material_global_v1-MaterialNumber) then - Skip - Do not create T Lane
                    }
                }
            }
        } else {
            String localNumber = fixNumber(originLocationWithOutSystem, 10);
            List<PlanCnsPlnSplLocEntity> plnSplLocEntityList = planCnsPlnSplLocDao.getEntityListWithSourceSystemLocalNumberAndVendorOrCustomer(sourceSystem, localNumber, IConstant.VALUE.V);
            if (null != plnSplLocEntityList && plnSplLocEntityList.size() > 0) {

                List<String> materialList = completingAllCriticalParameters(map);
                for (String localMaterialNumber : materialList) {
                    ResultObject resultObject = new ResultObject();

                    PlanCnsTlaneItemBo planCnsTlaneItemBo = new PlanCnsTlaneItemBo();
                    planCnsTlaneItemBo.setSequenceNumber(planCnsTlaneControlEntity.getSequenceNumber());
                    planCnsTlaneItemBo.setTlaneName(planCnsTlaneControlEntity.getTlaneName());
                    planCnsTlaneItemBo.setValidFrom(planCnsTlaneControlEntity.getValidFrom());
                    planCnsTlaneItemBo.setValidTo(planCnsTlaneControlEntity.getValidTo());
                    planCnsTlaneItemBo.setOriginLocation(planCnsTlaneControlEntity.getOriginLocation());
                    planCnsTlaneItemBo.setDestinationLocation(planCnsTlaneControlEntity.getDestinationLocation());
                    planCnsTlaneItemBo.setMode(planCnsTlaneControlEntity.getMode());
                    planCnsTlaneItemBo.setLeadTime(planCnsTlaneControlEntity.getLeadTime());
                    planCnsTlaneItemBo.setProcessTypeId(processTypeId);

                    List<PlanCnsMaterialPlanStatusEntity> planStatusEntityList = planCnsMaterialPlanStatusDao.getEntityListWithConditions(sourceSystem, localMaterialNumber,
                            destinationLocationWithOutSystem, IConstant.VALUE.X, null);

                    if (null != planStatusEntityList && planStatusEntityList.size() > 0) {
                        List<EDMSourceListV1Entity> sourceListV1EntityList = edmSourceListV1Dao.getEntityListWithSourceSystemAndLocalMaterialNumber(sourceSystem, localMaterialNumber);
                        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithSourceSystemAndLocalMaterialNumber(sourceSystem, localMaterialNumber);
                        if (null != sourceListV1EntityList && sourceListV1EntityList.size() > 0 && null != edmMaterialGlobalV1Entity) {
                            if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                                planCnsTlaneItemBo.setMaterialNumber(edmMaterialGlobalV1Entity.getMaterialNumber());
                                resultObject.setBaseBo(planCnsTlaneItemBo);
                                resultObjectList.add(resultObject);
                            } else if (edmMaterialGlobalV1Entity.getPrimaryPlanningCode().equals(edmMaterialGlobalV1Entity.getMaterialNumber())) {
                                planCnsTlaneItemBo.setMaterialNumber(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
                                resultObject.setBaseBo(planCnsTlaneItemBo);
                                resultObjectList.add(resultObject);
                            }
                            //If (material_global_v1-primaryPlanningCode) not equal to  (material_global_v1-MaterialNumber) then - Skip - Do not create T Lane
                        }
                    }
                }
            } else {
                //Reject the records
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.PLAN_CNS_TLANE_ITEM,
                        "", "", "", planCnsTlaneControlEntity.getSequenceNumber(), planCnsTlaneControlEntity.getTlaneName()));
                resultObjectList.add(resultObject);
            }
        }
        return resultObjectList;
    }

    private String fixNumber(String input, int length) {
        if (StringUtils.isNotBlank(input) && input.length() < length) {
            int shortNumber = length - input.length();
            for (int i = 0; i < shortNumber; i++) {
                input = "0" + input;
            }
        }
        return input;
    }

    private List<String> completingAllCriticalParameters(Map map) {


        Map<String, QueryObject> queryStringMap = new HashMap();
        String sourceSystem = (String) map.get(IConstant.VALUE.SOURCE_SYSTEM_CRITICAL_PARAMETERS);
        boolean isContinue = true;
        for (int i = 1; i < (map.size() + 1) && isContinue; i++) {

            String criticalParameterTableKey = MessageFormat.format(IConstant.PLAN_CNS_TLANE_CONTROL.CRITICAL_PARAMETER_TABLE, i);
            String criticalParameterFieldKey = MessageFormat.format(IConstant.PLAN_CNS_TLANE_CONTROL.CRITICAL_PARAMETER_FIELD, i);
            String criticalParameterLowKey = MessageFormat.format(IConstant.PLAN_CNS_TLANE_CONTROL.CRITICAL_PARAMETER_LOW, i);
            String criticalParameterOperatorKey = MessageFormat.format(IConstant.PLAN_CNS_TLANE_CONTROL.CRITICAL_PARAMETER_OPERATOR, i);
            String criticalParameterIEKey = MessageFormat.format(IConstant.PLAN_CNS_TLANE_CONTROL.CRITICAL_PARAMETER_IE, i);

            if (!(map.containsKey(criticalParameterTableKey)
                    || map.containsKey(criticalParameterFieldKey)
                    || map.containsKey(criticalParameterLowKey)
                    || map.containsKey(criticalParameterOperatorKey)
                    || map.containsKey(criticalParameterIEKey)
            )) {
                isContinue = false;
            }

            String criticalParameterTable = (String) map.get(criticalParameterTableKey);
            String criticalParameterField = (String) map.get(criticalParameterFieldKey);
            String criticalParameterLow = (String) map.get(criticalParameterLowKey);
            String criticalParameterOperator = (String) map.get(criticalParameterOperatorKey);
            String criticalParameterIE = (String) map.get(criticalParameterIEKey);

            String regionPath = "";
            if (IConstant.VALUE.CNS_PROD_LOC_ATTRIB.equals(criticalParameterTable)) {
                regionPath = IConstant.REGION.PLAN_CNS_PROD_LOC_ATTRIB;
            } else if (IConstant.VALUE.MATERIAL_PLANT.equals(criticalParameterTable)) {
                regionPath = IConstant.REGION.EDM_MATERIAL_PLANT_V1;
            } else if (IConstant.VALUE.SOURCE_LIST.equals(criticalParameterTable)) {
                regionPath = IConstant.REGION.EDM_SOURCE_LIST_V1;
            }

            if (StringUtils.isNotBlank(regionPath) && StringUtils.isNotBlank(criticalParameterField) && StringUtils.isNotBlank(criticalParameterOperator) && StringUtils.isNotBlank(criticalParameterLow)) {

                ADFCriteria adfCriteria = combineQueryStringAccordingOperator(criticalParameterField, criticalParameterOperator, criticalParameterLow, criticalParameterIE);
                QueryObject queryObject = new QueryObject();
                queryObject.setRegionPath(regionPath);
                if (queryStringMap.containsKey(criticalParameterTable)) {
                    ADFCriteria query = queryStringMap.get(criticalParameterTable).getAdfCriteria();
                    ADFCriteria newQuery = query.and(adfCriteria);
                    queryObject.setAdfCriteria(newQuery);
                } else {
                    queryObject.setAdfCriteria(adfCriteria);
                }

                queryStringMap.put(criticalParameterTable, queryObject);
            }
        }


        Set<String> keySet = queryStringMap.keySet();

        List<List<String>> materialNumberList = new ArrayList<>();
        for (String tableName : keySet) {
            QueryObject object = queryStringMap.get(tableName);
            String queryString = object.getAdfCriteria().and(IConstant.VALUE.SOURCE_SYSTEM).is(sourceSystem).toQueryString();

            if (StringUtils.isNotBlank(queryString)) {
                List<Map.Entry<String, String>> entryList = AdfViewHelper.queryForList(object.getRegionPath(), queryString, -1);
                List<String> list = new ArrayList();
                for (Map.Entry<String, String> entry : entryList) {
                    Map<String, Object> pMap = JsonUtils.jsonToObject(entry.getValue(), Map.class);
                    if (pMap.containsKey(IConstant.VALUE.LOCAL_MATERIAL_NUMBER_FIREST_LOWER)) {
                        String number = (String) pMap.get(IConstant.VALUE.LOCAL_MATERIAL_NUMBER_FIREST_LOWER);
                        if (!list.contains(number)) {
                            list.add(number);
                        }
                    }
                }
                materialNumberList.add(list);
            }
        }

        //union merge list
        List<String> resultList = materialNumberList.get(0);
        for (List<String> list : materialNumberList) {
            resultList.retainAll(list);
        }

        return resultList;
    }

    class QueryObject {
        private String regionPath;
        private ADFCriteria adfCriteria;

        public String getRegionPath() {
            return regionPath;
        }

        public void setRegionPath(String regionPath) {
            this.regionPath = regionPath;
        }

        public ADFCriteria getAdfCriteria() {
            return adfCriteria;
        }

        public void setAdfCriteria(ADFCriteria adfCriteria) {
            this.adfCriteria = adfCriteria;
        }
    }


    private ADFCriteria combineQueryStringAccordingOperator(String field, String operator, String value, String criticalParameterIE) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria(field);
        switch (operator) {
            case IConstant.VALUE.OPERATOR_EQUAL:
                if (value.contains(",")) {
                    String[] items = value.split(",");
                    adfCriteria.in(items);
                } else {
                    adfCriteria.is(value);
                }
                break;
            case IConstant.VALUE.OPERATOR_NOT_EQUAL:
                if (value.contains(",")) {
                    String[] items = value.split(",");
                    adfCriteria.in(items).not();
                } else {
                    adfCriteria.is(value).not();
                }
                break;
            case IConstant.VALUE.OPERATOR_LESS_THAN:
                adfCriteria.lessThan(value);
                break;
            case IConstant.VALUE.OPERATOR_LESS_THAN_EQUAL:
                adfCriteria.lessThanEqual(value);
                break;
            case IConstant.VALUE.OPERATOR_GREATER_THAN:
                adfCriteria.greaterThan(value);
                break;
            case IConstant.VALUE.OPERATOR_GREATER_THAN_EQUAL:
                adfCriteria.greaterThanEqual(value);
                break;
        }
        if (IConstant.VALUE.EXCLUSION.equals(criticalParameterIE)) {
            adfCriteria.not();
        }
        return adfCriteria;
    }

}

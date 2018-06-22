package com.jnj.pangea.omp.gdm_productcustomer.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_productcustomer.bo.CustomerField;
import com.jnj.pangea.omp.gdm_productcustomer.bo.OMPGdmProductCustomerBo;
import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class OMPGdmProductCustomerServiceImpl {

    private static OMPGdmProductCustomerServiceImpl instance;

    public static OMPGdmProductCustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductCustomerServiceImpl();
        }
        return instance;
    }

    private PlanCnsProductCustomerDaoImpl cnsProductCustomerDao = PlanCnsProductCustomerDaoImpl.getInstance();
    private EDMMaterialAuomV1DaoImpl materialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsPlanDemGrpDaoImpl planCnsPlanDemGrpDao = PlanCnsPlanDemGrpDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjects = new LinkedList<>();
        PlanCnsProductCustomerEntity planCnsProductCustomerEntity = (PlanCnsProductCustomerEntity) o;
        List<EDMMaterialGlobalV1Entity> edmMaterialGlobalV1FirstList = materialGlobalV1Dao.getEntitiesWithLocalMaterialNumberAndSourceSystem(planCnsProductCustomerEntity.getProductId(), planCnsProductCustomerEntity.getSourceSystem());
        //rule J1
        if (edmMaterialGlobalV1FirstList == null || edmMaterialGlobalV1FirstList.size() < 1) {
            FailData failData = writeFailDataToRegion(planCnsProductCustomerEntity, IConstant.FAILED.ERROR_CODE.J1, "ProductId do not exist in edm material");
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjects.add(resultObject);
            return resultObjects;
        }

        for (EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity : edmMaterialGlobalV1FirstList) {
            if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                //rule T1
                FailData failData = writeFailDataToRegion(planCnsProductCustomerEntity, IConstant.FAILED.ERROR_CODE.T1, "localDpParentCode do not exist in edm material");
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(failData);
                resultObjects.add(resultObject);
                continue;
            }
            List<EDMMaterialGlobalV1Entity> edmMaterialGlobalV1List = materialGlobalV1Dao.getEntitiesWithLocalDpParentCode(edmMaterialGlobalV1Entity.getLocalDpParentCode());
            List<PlanCnsProductCustomerEntity> planCnsProductCustomerListTemp = new ArrayList<>();
            for (EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity1 : edmMaterialGlobalV1List) {
                List<PlanCnsProductCustomerEntity> planCnsProductCustomer = cnsProductCustomerDao.getListWithProductIdAndSourceSystemClone(edmMaterialGlobalV1Entity1.getLocalMaterialNumber(), edmMaterialGlobalV1Entity1.getSourceSystem());
                if (planCnsProductCustomer != null && planCnsProductCustomer.size() > 0) {
                    //rule T3
                    Boolean productStatusFlag = false;
                    for (PlanCnsProductCustomerEntity planCnsProductCustomerEntity1 : planCnsProductCustomer) {
                        if (!planCnsProductCustomerEntity1.getProductStatus().equals(null) && planCnsProductCustomerEntity1.getProductStatus().equals("ACTIVE")){
                            productStatusFlag = true;
                        }
                    }
                    for (PlanCnsProductCustomerEntity planCnsProductCustomerEntity2 : planCnsProductCustomer) {
                        if (productStatusFlag){
                            planCnsProductCustomerEntity2.setProductStatus("ACTIVE");
                        } else {
                            planCnsProductCustomerEntity2.setProductStatus("INACTIVE");
                        }
                    }
                    planCnsProductCustomerListTemp.addAll(planCnsProductCustomer);
                }
            }
            resultObjects.addAll(setData(planCnsProductCustomerListTemp, edmMaterialGlobalV1Entity));
        }
        return resultObjects;
    }

    private FailData writeFailDataToRegion(PlanCnsProductCustomerEntity planCnsProductCustomerEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCTCUSTOMER,
                ruleCode,
                errorValue,
                planCnsProductCustomerEntity.getSourceSystem(),
                planCnsProductCustomerEntity.getProductId()
        );
        return failData;
    }

    private FailData writeFailDataForJ2ToRegion(PlanCnsProductCustomerEntity planCnsProductCustomerEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCTCUSTOMER,
                ruleCode,
                errorValue,
                planCnsProductCustomerEntity.getSourceSystem(),
                planCnsProductCustomerEntity.getProductId(),
                planCnsProductCustomerEntity.getUom()
        );
        return failData;
    }

    private String getlargestValue(String str1, String str2) {
        if (StringUtils.isBlank(str1)) {
            return str2;
        }
        if (StringUtils.isBlank(str2)) {
            return str1;
        }
        double value1;
        double value2;
        try {
            value1 = Double.parseDouble(str1);
        } catch (Exception e) {
            return str2;
        }
        try {
            value2 = Double.parseDouble(str2);
        } catch (Exception e) {
            return str1;
        }
        if (value1 > value2) {
            return str1;
        } else {
            return str2;
        }
    }

    private boolean calculateField(CustomerField customerField, PlanCnsProductCustomerEntity planCnsProductCustomerEntity) {
        customerField.setLeadTime(getlargestValue(customerField.getLeadTime(), planCnsProductCustomerEntity.getLeadTime()));
        customerField.setNorm(getlargestValue(customerField.getNorm(), planCnsProductCustomerEntity.getNorm()));
        EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = materialAuomV1Dao.getListWithLocalMaterialNumber(planCnsProductCustomerEntity.getProductId(), planCnsProductCustomerEntity.getUom(), planCnsProductCustomerEntity.getSourceSystem());
        customerField.setLocationId(planCnsProductCustomerEntity.getLocationId());
        if (edmMaterialAuomV1Entity == null) {
            return false;
        }
        Double NumerlocalDeno = null;
        try {
            Double localDenominator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalDenominator());
            Double localNumerator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalNumerator());
            NumerlocalDeno = localNumerator / localDenominator;
        } catch (Exception e) {

        }
        try {
            //rule T4
            customerField.setMoq(customerField.getMoq() + Double.parseDouble(planCnsProductCustomerEntity.getMoq()) * NumerlocalDeno);
        } catch (Exception e) {

        }
        try {
            //rule T5
            customerField.setIoq(customerField.getIoq() + Double.parseDouble(planCnsProductCustomerEntity.getIoq()) * NumerlocalDeno);

        } catch (Exception e) {

        }
        try {
            //rule T6
            customerField.setStockLevel(customerField.getStockLevel() + Double.parseDouble(planCnsProductCustomerEntity.getStockLevel()) * NumerlocalDeno);
        } catch (Exception e) {

        }
        try {
            //rule T8
            customerField.setRoundingThreshold(customerField.getRoundingThreshold() + Double.parseDouble(planCnsProductCustomerEntity.getRoundingThreshold()));
        } catch (Exception e) {

        }
        try {
            //rule T8
            customerField.setRoundingThresholdCount(customerField.getRoundingThresholdCount() + 1);
        } catch (Exception e) {

        }
        return true;
    }

    private static NumberFormat getNF() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMinimumFractionDigits(IConstant.LFU.VALUE_DECIMAL_3);
        nf.setMaximumFractionDigits(IConstant.LFU.VALUE_DECIMAL_3);
//        nf.setMinimumFractionDigits(IConstant.LFU.VALUE_DECIMAL_6);
//        nf.setMaximumFractionDigits(IConstant.LFU.VALUE_DECIMAL_6);
        nf.setGroupingUsed(false);
        return nf;
    }

    private List<ResultObject> setData(List<PlanCnsProductCustomerEntity> planCnsProductCustomerList, EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity) {
        NumberFormat nf = getNF();
        List<ResultObject> resultObjects = new ArrayList<>();
        Map<String, List<PlanCnsProductCustomerEntity>> listMap = new HashMap<>();
        Map<String, CustomerField> fieldMap = new HashMap<>();
        for (PlanCnsProductCustomerEntity planCnsProductCustomerEntity : planCnsProductCustomerList) {
            String customerId = planCnsProductCustomerEntity.getCustomerId();
            if (listMap.containsKey(customerId)) {
                CustomerField customerField = fieldMap.get(customerId);
                boolean J2_flag = calculateField(customerField, planCnsProductCustomerEntity);
                if (!J2_flag) {
                    FailData failData = writeFailDataForJ2ToRegion(planCnsProductCustomerEntity, IConstant.FAILED.ERROR_CODE.J2, "uom do not exist in edm auom");
                    ResultObject resultObject = new ResultObject();
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    continue;
                }
                //rule T2
                String locationId = planCnsProductCustomerEntity.getLocationId();
                if (customerField.isLocationIdFlag() && !(locationId.equals(customerField.getLocationId()))) {
                    locationId = planCnsPlanDemGrpDao.getLocationId(planCnsProductCustomerEntity.getSourceSystem(), planCnsProductCustomerEntity.getCustomerId());
                    customerField.setLocationId(locationId);
                    planCnsProductCustomerEntity.setLocationId(locationId);
                    customerField.setLocationIdFlag(false);
                }
                listMap.get(customerId).add(planCnsProductCustomerEntity);
            } else {
                CustomerField customerField = new CustomerField();
                boolean J2_flag = calculateField(customerField, planCnsProductCustomerEntity);
                if (!J2_flag) {
                    FailData failData = writeFailDataForJ2ToRegion(planCnsProductCustomerEntity, IConstant.FAILED.ERROR_CODE.J2, "uom do not exist in edm auom");
                    ResultObject resultObject = new ResultObject();
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    continue;
                }
                customerField.setLocationId(planCnsProductCustomerEntity.getLocationId());
                fieldMap.put(customerId, customerField);
                List<PlanCnsProductCustomerEntity> tempList = new ArrayList<>();
                tempList.add(planCnsProductCustomerEntity);
                listMap.put(customerId, tempList);
            }
        }

        Iterator listMapIterator = listMap.entrySet().iterator();
        while (listMapIterator.hasNext()) {
            Map.Entry<String, List<PlanCnsProductCustomerEntity>> entry = (Map.Entry<String, List<PlanCnsProductCustomerEntity>>) listMapIterator.next();
            String keyString = entry.getKey();
            List<PlanCnsProductCustomerEntity> planCnsProductCustomerMapList = entry.getValue();
            CustomerField customerField = fieldMap.get(keyString);
            for(PlanCnsProductCustomerEntity planCnsProductCustomerEntity: planCnsProductCustomerMapList){
                OMPGdmProductCustomerBo gdmProductCustomerBo = new OMPGdmProductCustomerBo();
                gdmProductCustomerBo.setCustomerId(planCnsProductCustomerEntity.getCustomerId());
                //rule J1, T1
                gdmProductCustomerBo.setProductId("LA_" + edmMaterialGlobalV1Entity.getLocalDpParentCode());
                //rule T2
                gdmProductCustomerBo.setLocationId(planCnsProductCustomerEntity.getSourceSystem() + "_" + customerField.getLocationId());
                //rule T3
                gdmProductCustomerBo.setProductStatus(planCnsProductCustomerEntity.getProductStatus());
                //rule T4, T5, T6
                gdmProductCustomerBo.setMoq(String.valueOf(nf.format(customerField.getMoq())));
                gdmProductCustomerBo.setIoq(String.valueOf(nf.format(customerField.getIoq())));
                gdmProductCustomerBo.setStockLevel(String.valueOf(nf.format(customerField.getStockLevel())));
                //rule T7
                gdmProductCustomerBo.setNorm(customerField.getNorm());
                gdmProductCustomerBo.setLeadTime(customerField.getLeadTime());
                //rule T8
                gdmProductCustomerBo.setRoundingThreshold(String.valueOf(nf.format(customerField.getRoundingThreshold() / customerField.getRoundingThresholdCount())));
                ResultObject resultObject = new ResultObject();
                resultObject.setBaseBo(gdmProductCustomerBo);
                resultObjects.add(resultObject);
                break;
            }
        }
        return resultObjects;
    }

}

package com.jnj.pangea.omp.gdm_pos.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMJNJCalendarV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDpPosDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMJNJCalendarV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import com.jnj.pangea.omp.gdm_pos.bo.OMPGdmPosBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OMPGdmPosServiceImpl {

    private static OMPGdmPosServiceImpl instance;

    public static OMPGdmPosServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmPosServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private PlanCnsDemGrpAsgnDaoImpl cnsDemGrpAsgnDao = PlanCnsDemGrpAsgnDaoImpl.getInstance();
    private ProjectOneKnvhDaoImpl knvhDao = ProjectOneKnvhDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmjnjCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsDpPosDaoImpl planCnsDpPosDao = PlanCnsDpPosDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> list = new ArrayList<>();
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
            // Reject this record
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                    IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                    edmMaterialGlobalV1Entity.getSourceSystem()));

            list.add(resultObject);
            return list;
        }

        List<OMPGdmPosBo> gdmPosBoList = new ArrayList<>();
        Map<String, String> quantityMap = new HashMap<String, String>();
        Map<String, String> noOfWeekMap = new HashMap<String, String>();

        List<String> forecastUploadIdList = new ArrayList<>();

        // not sure whether need to filter by table cns_plan_parameter
        List<PlanCnsPlanParameterEntity> cnsPlanParameterEntityList = cnsPlanParameterDao.getEntitiesWithConditions(edmMaterialGlobalV1Entity.getSourceSystem(), IConstant.VALUE.SEND_TO_OMP, edmMaterialGlobalV1Entity.getSourceSystem());
        if (null != cnsPlanParameterEntityList && cnsPlanParameterEntityList.size() > 0) {

            //query all localMaterialNumber where have same localDpParent
            List<EDMMaterialGlobalV1Entity> materialGlobalV1EntityList = materialGlobalV1Dao.getEntitiesWithLocalDpParentCodeFromCopy(edmMaterialGlobalV1Entity.getLocalDpParentCode());
            List<String> localMaterialNumberList = new ArrayList<String>();
            for (EDMMaterialGlobalV1Entity globalV1Entity : materialGlobalV1EntityList) {
                localMaterialNumberList.add(globalV1Entity.getLocalMaterialNumber());
            }

            if (localMaterialNumberList.size() > 0) {

                List<PlanCnsDpPosEntity> planCnsDpPosEntityList = planCnsDpPosDao.getEntityListWithLocalMaterial(localMaterialNumberList);
                if (null != planCnsDpPosEntityList && planCnsDpPosEntityList.size() > 0) {

                    List<String> customerIdList = new ArrayList<>();
                    for (PlanCnsDpPosEntity cnsDpPosEntity : planCnsDpPosEntityList) {
                        //T1
                        String aggregationId = "";
                        String customer = cnsDpPosEntity.getCustomer();
                        String yearMonth = cnsDpPosEntity.getYearMonth();

                        if (StringUtils.isNotEmpty(customer)) {
                            // not sure the result is object or list ?
                            List<PlanCnsDemGrpAsgnEntity> planCnsDemGrpAsgnEntityList = getPlanCnsDemGrpAsgnEntityList(customer);

                            if (null != planCnsDemGrpAsgnEntityList && planCnsDemGrpAsgnEntityList.size() > 0) {

                                for (PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity : planCnsDemGrpAsgnEntityList) {
                                    String demandGroup = planCnsDemGrpAsgnEntity.getDemandGroup();
                                    if (StringUtils.isNotBlank(demandGroup)) {
                                        aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.HORIZONTAL_Line + demandGroup;

                                        String mapKey = aggregationId + IConstant.VALUE.UNDERLINE + cnsDpPosEntity.getYearMonth();

                                        String keyAfterCustomerFixed = mapKey + IConstant.VALUE.UNDERLINE + fixField(customer);
                                        if (!customerIdList.contains(keyAfterCustomerFixed)) {
                                            customerIdList.add(keyAfterCustomerFixed);

//                                            LogUtil.getCoreLog().info("================ mapKey = " + mapKey + ",cnsDpPosEntity.getQuantity() = "
//                                                    + cnsDpPosEntity.getQuantity() + "================ quantityMap = " + quantityMap.toString()
//                                                    + ",customer = " + customer + ",quantityMap size = " + quantityMap.size());

                                            if (quantityMap.containsKey(mapKey)) {
                                                String quantity = (String) quantityMap.get(mapKey);
                                                quantity = sumQuantity(quantity, cnsDpPosEntity.getQuantity());
                                                if (StringUtils.isNotBlank(quantity)) {
                                                    quantityMap.put(mapKey, quantity);
                                                }
                                            } else {
                                                if (StringUtils.isNotBlank(cnsDpPosEntity.getQuantity())) {
                                                    quantityMap.put(mapKey, cnsDpPosEntity.getQuantity());
                                                }
                                            }

                                        }
                                        //T2
                                        List<EDMJNJCalendarV1Entity> edmjnjCalendarV1EntityList = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
                                        if (null != edmjnjCalendarV1EntityList && edmjnjCalendarV1EntityList.size() > 0) {

                                            for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmjnjCalendarV1EntityList) {

                                                OMPGdmPosBo gdmPosBo = new OMPGdmPosBo();
                                                gdmPosBo.setAggregationId(aggregationId);
                                                gdmPosBo.setDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));
                                                gdmPosBo.setFromDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));

                                                //T3 CONCATENATE GDMPOS-AggregationId( Column1 ),  "-", GDMPOS-DUEDATE ( Column 3 )
                                                String forecastUploadId = aggregationId + IConstant.VALUE.HORIZONTAL_Line + gdmPosBo.getDueDate();
                                                if (!forecastUploadIdList.contains(forecastUploadId)) {
                                                    forecastUploadIdList.add(forecastUploadId);
                                                } else {
                                                    continue;
                                                }

                                                gdmPosBo.setForecastUploadId(forecastUploadId);
                                                gdmPosBo.setYearMonth(cnsDpPosEntity.getYearMonth());
                                                //T4
                                                //according the data , there just one planCnsPlanUnitEntity record was found
                                                PlanCnsPlanUnitEntity planCnsPlanUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialGlobalV1Entity.getLocalBaseUom());
                                                if (null != planCnsPlanUnitEntity) {
                                                    List<EDMSourceSystemV1Entity> sourceSystemV1EntityList = sourceSystemV1Dao.getEntityListWithSourceSystem(planCnsPlanUnitEntity.getSourceSystem());
                                                    if (null != sourceSystemV1EntityList && sourceSystemV1EntityList.size() > 0) {
                                                        gdmPosBo.setUnitId(planCnsPlanUnitEntity.getUnit());
                                                    }
                                                }

                                                //T5
                                                if (StringUtils.isNotBlank(edmjnjCalendarV1Entity.getNoOfWeek())) {
                                                    noOfWeekMap.put(gdmPosBo.getForecastUploadId(), edmjnjCalendarV1Entity.getNoOfWeek());
                                                }

                                                gdmPosBoList.add(gdmPosBo);
                                            }
                                        }

                                    } else {
//                                        LogUtil.getCoreLog().info("============== demandGroup is null  ");
                                        ResultObject resultObject = new ResultObject();
                                        resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                                                IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                                                edmMaterialGlobalV1Entity.getSourceSystem()));
                                        list.add(resultObject);
                                    }
                                }
                            } else {
//                                LogUtil.getCoreLog().info("============== planCnsDemGrpAsgnEntityList is null ,customer = " + customer);
                                ResultObject resultObject = new ResultObject();
                                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                                        IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                                        edmMaterialGlobalV1Entity.getSourceSystem()));
                                list.add(resultObject);
                            }
                        }
                    }
                } else {
                    // Reject this record
                    ResultObject resultObject = new ResultObject();
                    resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                            IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                            edmMaterialGlobalV1Entity.getSourceSystem()));
                    list.add(resultObject);
                }
            }
        } else {
            // Reject this record
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                    IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                    edmMaterialGlobalV1Entity.getSourceSystem()));
            list.add(resultObject);
        }
//        LogUtil.getCoreLog().info("================ gdmPosBoList size = " + gdmPosBoList.size());
        for (OMPGdmPosBo bo : gdmPosBoList) {
            ResultObject resultObject = new ResultObject();

            String quantity = quantityMap.get(bo.getAggregationId() + IConstant.VALUE.UNDERLINE + bo.getYearMonth());
            String noOfWeek = noOfWeekMap.get(bo.getForecastUploadId());

            try {
                Double NoOfWeekValue = Double.parseDouble(noOfWeek);
                Double quantityValue = Double.parseDouble(quantity);
//                LogUtil.getCoreLog().info("============== NoOfWeekValue= " + NoOfWeekValue + ",quantityValue = " + quantityValue);
                if (0 != NoOfWeekValue) {
                    long result = Math.round(quantityValue / NoOfWeekValue);
                    bo.setVolume(result + "");
                } else {
                    bo.setVolume("");
                }

                resultObject.setBaseBo(bo);
                list.add(resultObject);

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getCoreLog().info("=============== NoOfWeek or quantity is not legal ,NoOfWeek = "
                        + noOfWeek + ",quantity = " + quantity);
            }
        }

        return list;
    }

    private List<PlanCnsDemGrpAsgnEntity> getPlanCnsDemGrpAsgnEntityList(String customer) {
        List<KnvhEntity> kunnrList = knvhDao.getEntityListByKunnrAndDatbi(customer);
        List<PlanCnsDemGrpAsgnEntity> planCnsDemGrpAsgnEntityList = new ArrayList<>();
        PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerId(customer);
        if (null == planCnsDemGrpAsgnEntity) {

            if (null != kunnrList && kunnrList.size() > 0) {

                for (KnvhEntity kunnr : kunnrList) {
                    PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity1 = cnsDemGrpAsgnDao.getEntityWithCustomerId(kunnr.getHkunnr());
                    if (null != planCnsDemGrpAsgnEntity1) {
                        planCnsDemGrpAsgnEntityList.add(planCnsDemGrpAsgnEntity1);
                    }
                }

                if (planCnsDemGrpAsgnEntityList.size() <= 0) {

                    for (KnvhEntity kunnr : kunnrList) {
                        List<KnvhEntity> hkunnrList = knvhDao.getEntityListByKunnrAndDatbi(kunnr.getHkunnr());
                        if (null != hkunnrList && hkunnrList.size() > 0) {
                            for (KnvhEntity item : hkunnrList) {
                                PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity2 = cnsDemGrpAsgnDao.getEntityWithCustomerId(item.getHkunnr());
                                if (null != planCnsDemGrpAsgnEntity2) {
                                    planCnsDemGrpAsgnEntityList.add(planCnsDemGrpAsgnEntity2);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            planCnsDemGrpAsgnEntityList.add(planCnsDemGrpAsgnEntity);
        }

        return planCnsDemGrpAsgnEntityList;
    }

    private String fixField(String field) {
        if (field.startsWith("0")) {
            field = field.substring(1, field.length());
            return fixField(field);
        } else {
            return field;
        }
    }

    private String sumQuantity(String value1, String value2) {
        double result = 0;
        try {
            double quantity1 = 0, quantity2 = 0;
            if (StringUtils.isNotBlank(value1)) {
                quantity1 = Double.parseDouble(value1);
            }
            if (StringUtils.isNotBlank(value2)) {
                quantity2 = Double.parseDouble(value2);
            }
            result = quantity1 + quantity2;
//            LogUtil.getCoreLog().info("============== result= " + result + ",quantity1 = " + quantity1 + "quantity2 = " + quantity2);
            return result + "";
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info("=============== quantity1 or quantity2 is not legal, value1 =  " + value1 + ",value2 = " + value2);
        }
        return "";
    }


}

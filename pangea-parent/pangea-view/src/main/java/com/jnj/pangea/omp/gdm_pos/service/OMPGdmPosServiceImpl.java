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

        // not sure whether need to filter by table cns_plan_parameter
        List<PlanCnsPlanParameterEntity> cnsPlanParameterEntityList = cnsPlanParameterDao.getEntitiesWithConditions(edmMaterialGlobalV1Entity.getSourceSystem(), IConstant.VALUE.SEND_TO_OMP, edmMaterialGlobalV1Entity.getSourceSystem());
        if (null != cnsPlanParameterEntityList && cnsPlanParameterEntityList.size() > 0) {

            //query all localMaterialNumber where have same localDpParent
            List<EDMMaterialGlobalV1Entity> materialGlobalV1EntityList = materialGlobalV1Dao.getEntitiesWithLocalDpParentCodeFromCopy(edmMaterialGlobalV1Entity.getLocalDpParentCode());
            List<String> localMaterialNumberList = new ArrayList<String>();
            localMaterialNumberList.add(edmMaterialGlobalV1Entity.getLocalMaterialNumber());
            for (EDMMaterialGlobalV1Entity globalV1Entity : materialGlobalV1EntityList) {
                localMaterialNumberList.add(globalV1Entity.getLocalMaterialNumber());
            }
            List<PlanCnsDpPosEntity> planCnsDpPosEntityList = new ArrayList<>();
            if (localMaterialNumberList.size() > 0) {
                planCnsDpPosEntityList = planCnsDpPosDao.getEntityListWithLocalMaterial(localMaterialNumberList);
                if (planCnsDpPosEntityList.size() > 0) {

                    for (PlanCnsDpPosEntity cnsDpPosEntity : planCnsDpPosEntityList) {

                        //T1
                        String aggregationId = "";
                        String customer = cnsDpPosEntity.getCustomer();

                        if (StringUtils.isNotEmpty(customer)) {
                            // not sure the result is object or list ?
                            PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerId(customer);
                            if (null != planCnsDemGrpAsgnEntity) {
                                String demandGroup = planCnsDemGrpAsgnEntity.getDemandGroup();
                                if (StringUtils.isNotBlank(demandGroup)) {
                                    //whether filter by table KNVH
                                    String customerId = fixField(planCnsDemGrpAsgnEntity.getCustomerId());

                                    List<KnvhEntity> list1 = knvhDao.getEntityListByKunnrAndDatbi(planCnsDemGrpAsgnEntity.getCustomerId());
                                    List<KnvhEntity> knvhEntityListStep1 = new ArrayList<>();

                                    for (KnvhEntity knvhEntity : list1) {
                                        String kunner = fixField(knvhEntity.getKunnr());
                                        if (kunner.equals(customerId)) {
                                            knvhEntityListStep1.add(knvhEntity);
                                        }
                                    }

                                    if (knvhEntityListStep1.size() > 0 && StringUtils.isNotEmpty(demandGroup)) {
                                        // If value found in cns_dem_grp_asgn-demandGroup then CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
                                        aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.HORIZONTAL_Line + demandGroup;
                                    } else {
                                        List<KnvhEntity> list2 = knvhDao.getEntityListByHKunnrAndDatbi(planCnsDemGrpAsgnEntity.getCustomerId());
                                        List<KnvhEntity> knvhEntityListStep2 = new ArrayList<>();

                                        for (KnvhEntity knvhEntity : list2) {
                                            String hkunner = fixField(knvhEntity.getHkunnr());
                                            if (hkunner.equals(customerId)) {
                                                knvhEntityListStep2.add(knvhEntity);
                                            }
                                        }

                                        if (knvhEntityListStep2.size() > 0 && StringUtils.isNotEmpty(demandGroup)) {
                                            //CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
                                            aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.HORIZONTAL_Line + demandGroup;
                                        }
                                    }
                                    String mapKey = aggregationId + IConstant.VALUE.UNDERLINE + cnsDpPosEntity.getYearMonth();

                                    if (quantityMap.containsKey(mapKey)) {
                                        String quantity = (String) quantityMap.get(mapKey);
                                        quantity = sumQuantity(quantity, cnsDpPosEntity.getQuantity());
                                        quantityMap.put(mapKey, quantity);
                                    } else {
                                        quantityMap.put(mapKey, cnsDpPosEntity.getQuantity());
                                    }

                                    //T2
                                    String yearMonth = cnsDpPosEntity.getYearMonth();
                                    //Lookup jnj_calendar_v1 using cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod to get noOfWeek, weekToDate and weekFromDate.
                                    List<EDMJNJCalendarV1Entity> edmjnjCalendarV1EntityList = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
                                    if (null != edmjnjCalendarV1EntityList && edmjnjCalendarV1EntityList.size() > 0) {

                                        for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmjnjCalendarV1EntityList) {

                                            OMPGdmPosBo gdmPosBo = new OMPGdmPosBo();
                                            gdmPosBo.setAggregationId(aggregationId);
                                            gdmPosBo.setDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));
                                            gdmPosBo.setFromDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));

                                            //T3 CONCATENATE GDMPOS-AggregationId( Column1 ),  "-", GDMPOS-DUEDATE ( Column 3 )
                                            String forecastUploadId = aggregationId + IConstant.VALUE.HORIZONTAL_Line + gdmPosBo.getDueDate();
                                            gdmPosBo.setForecastUploadId(forecastUploadId);
                                            gdmPosBo.setYearMonth(cnsDpPosEntity.getYearMonth());
                                            //T4
                                            //according the data , there just one planCnsPlanUnitEntity record was found
                                            PlanCnsPlanUnitEntity planCnsPlanUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialGlobalV1Entity.getLocalBaseUom());
                                            if (null != planCnsPlanUnitEntity) {
                                                EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithSourceSystem(planCnsPlanUnitEntity.getSourceSystem());
                                                if (null != sourceSystemV1Entity) {
                                                    gdmPosBo.setUnitId(planCnsPlanUnitEntity.getUnit());
                                                }
                                            }

                                            //T5
                                            //Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
                                            if (StringUtils.isNotBlank(edmjnjCalendarV1Entity.getNoOfWeek())) {
                                                noOfWeekMap.put(gdmPosBo.getForecastUploadId(), edmjnjCalendarV1Entity.getNoOfWeek());
                                            }

                                            gdmPosBoList.add(gdmPosBo);
                                            
                                        }
                                    }

                                } else {
                                    ResultObject resultObject = new ResultObject();
                                    resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                                            IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", edmMaterialGlobalV1Entity.getLocalMaterialNumber(),
                                            edmMaterialGlobalV1Entity.getSourceSystem()));
                                    list.add(resultObject);
                                }
                            } else {
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

        for (OMPGdmPosBo bo : gdmPosBoList) {
            ResultObject resultObject = new ResultObject();

            String quantity = quantityMap.get(bo.getAggregationId() + IConstant.VALUE.UNDERLINE + bo.getYearMonth());
            String noOfWeek = noOfWeekMap.get(bo.getForecastUploadId());

            try {
                Double NoOfWeekValue = Double.parseDouble(noOfWeek);
                Double quantityValue = Double.parseDouble(quantity);

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


    private String fixField(String field) {
        if (field.startsWith("0")) {
            field = field.substring(1, field.length());
            return fixField(field);
        } else {
            return field;
        }
    }

    private String sumQuantity(String value1, String value2) {
        int result = 0;
        try {
            int quantity1 = 0, quantity2 = 0;
            if (StringUtils.isNotBlank(value1)) {
                quantity1 = Integer.parseInt(value1);
            }
            if (StringUtils.isNotBlank(value2)) {
                quantity2 = Integer.parseInt(value2);
            }
            result = quantity1 + quantity2;
            return result + "";
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info("=============== quantity1 or quantity2 is not legal");
        }
        return "";
    }


}

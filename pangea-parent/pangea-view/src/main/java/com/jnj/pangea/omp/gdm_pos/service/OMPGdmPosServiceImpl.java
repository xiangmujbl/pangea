package com.jnj.pangea.omp.gdm_pos.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMJNJCalendarV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
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
import java.util.List;

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


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> list = new ArrayList<>();
        PlanCnsDpPosEntity cnsDpPosEntity = (PlanCnsDpPosEntity) o;

        //not sure the result is object or list? according to currrent data ,the result is object
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = null;
        String localMaterialNumber = cnsDpPosEntity.getLocalMaterial();
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(cnsDpPosEntity.getLocalMaterial());
        }
        if (null == edmMaterialGlobalV1Entity) {
            // Reject this record
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                    IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", cnsDpPosEntity.getLocalMaterial(),
                    cnsDpPosEntity.getCustomer(), cnsDpPosEntity.getYearMonth()));
            list.add(resultObject);
            return list;
        }

        List<PlanCnsPlanParameterEntity> cnsPlanParameterEntityList = cnsPlanParameterDao.getEntitiesWithConditions(edmMaterialGlobalV1Entity.getSourceSystem(), IConstant.VALUE.SEND_TO_OMP, edmMaterialGlobalV1Entity.getSourceSystem());
        // not sure whether need to filter by table cns_plan_parameter
        if (cnsPlanParameterEntityList.size() > 0) {
            if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                // Reject this record
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                        IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", cnsDpPosEntity.getLocalMaterial(),
                        cnsDpPosEntity.getCustomer(), cnsDpPosEntity.getYearMonth()));
                list.add(resultObject);
                return list;

            } else {
                String customer = cnsDpPosEntity.getCustomer();
                //T1
                String aggregationId = "";
                if (StringUtils.isNotEmpty(customer)) {

                    // not sure the result is object or list ?
                    PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerId(customer);
                    if (null != planCnsDemGrpAsgnEntity) {
                        String demandGroup = planCnsDemGrpAsgnEntity.getDemandGroup();
                        if (StringUtils.isNotBlank(demandGroup)) {
                            //whether filter by table KNVH
                            List<KnvhEntity> knvhEntityListStep1 = knvhDao.getEntityListByKunnrAndDatbi(planCnsDemGrpAsgnEntity.getCustomerId());
                            if (knvhEntityListStep1.size() > 0 && StringUtils.isNotEmpty(demandGroup)) {
                                // If value found in cns_dem_grp_asgn-demandGroup then CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
                                aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.HORIZONTAL_Line + demandGroup;

                            } else {
                                List<KnvhEntity> knvhEntityListStep2 = knvhDao.getEntityListByHKunnrAndDatbi(planCnsDemGrpAsgnEntity.getCustomerId());
                                if (knvhEntityListStep2.size() > 0 && StringUtils.isNotEmpty(demandGroup)) {
                                    //CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
                                    aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.HORIZONTAL_Line + demandGroup;
                                }
                            }
                        } else {
                            ResultObject resultObject = new ResultObject();
                            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                                    IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", cnsDpPosEntity.getLocalMaterial(),
                                    cnsDpPosEntity.getCustomer(), cnsDpPosEntity.getYearMonth()));
                            list.add(resultObject);
                            return list;
                        }
                    } else {
                        ResultObject resultObject = new ResultObject();
                        resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                                IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", cnsDpPosEntity.getLocalMaterial(),
                                cnsDpPosEntity.getCustomer(), cnsDpPosEntity.getYearMonth()));
                        list.add(resultObject);
                        return list;
                    }
                }

                //T2
                String yearMonth = cnsDpPosEntity.getYearMonth();
                //Lookup  jnj_calendar_v1 using cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod to get noOfWeek, weekToDate and weekFromDate.
                List<EDMJNJCalendarV1Entity> edmjnjCalendarV1EntityList = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
                if (null != edmjnjCalendarV1EntityList && edmjnjCalendarV1EntityList.size() > 0) {
                    for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmjnjCalendarV1EntityList) {
                        ResultObject resultObject = new ResultObject();
                        OMPGdmPosBo gdmPosBo = new OMPGdmPosBo();
                        gdmPosBo.setAggregationId(aggregationId);
                        gdmPosBo.setDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));
                        gdmPosBo.setFromDueDate(DateUtils.dateToString(DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1), DateUtils.J_yyyyMMdd_HHmmss));

                        //T3 CONCATENATE GDMPOS-AggregationId( Column1 ),  "-", GDMPOS-DUEDATE ( Column 3 )
                        String forecastUploadId = aggregationId + IConstant.VALUE.HORIZONTAL_Line + gdmPosBo.getDueDate();
                        gdmPosBo.setForecastUploadId(forecastUploadId);

                        //T4
                        //"get cns_plan_unit-unit WHERE cns_plan_unit-sourceSystem = source_sysstem_v1-sourceSystem AND
                        //        cns_plan_unit -localUom = material_global_v1-localBaseUom"

                        //according the data , there just one planCnsPlanUnitEntity record was found
                        PlanCnsPlanUnitEntity planCnsPlanUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialGlobalV1Entity.getLocalBaseUom());
                        if (null != planCnsPlanUnitEntity) {
                            EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithSourceSystem(planCnsPlanUnitEntity.getSourceSystem());
                            if (null != sourceSystemV1Entity) {
                                gdmPosBo.setUnitId(planCnsPlanUnitEntity.getUnit());
                            }
                        } else {
                            LogUtil.getCoreLog().info("=============== planCnsPlanUnitEntity is null");
                        }

                        //T5
                        //Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
                        if (StringUtils.isNotBlank(cnsDpPosEntity.getQuantity()) && StringUtils.isNotBlank(edmjnjCalendarV1Entity.getNoOfWeek())) {
                            try {
                                int NoOfWeek = Integer.parseInt(edmjnjCalendarV1Entity.getNoOfWeek());
                                int quantity = Integer.parseInt(cnsDpPosEntity.getQuantity());
                                if (0 != NoOfWeek) {
                                    int result = Math.round(quantity / NoOfWeek);
                                    gdmPosBo.setQuantity(result + "");
                                } else {
                                    gdmPosBo.setQuantity("");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                LogUtil.getCoreLog().info("=============== NoOfWeek or quantity is not legal ,NoOfWeek = "
                                        + edmjnjCalendarV1Entity.getNoOfWeek() + ",quantity = " + cnsDpPosEntity.getQuantity());
                            }
                        }
                        resultObject.setBaseBo(gdmPosBo);
                        list.add(resultObject);
                    }
                }
            }
        } else {
            // Reject this record
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                    IConstant.FAILED.INTERFACE_ID.OMP_GDM_POS, "", "Unable to find Root", "", cnsDpPosEntity.getLocalMaterial(),
                    cnsDpPosEntity.getCustomer(), cnsDpPosEntity.getYearMonth()));
            list.add(resultObject);
            return list;
        }
        return list;
    }
}

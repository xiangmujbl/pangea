package com.jnj.pangea.plan.cns_material_plan_status.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustExclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsSoTypeInclDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsSoTypeInclEntity;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class PlanCnsMaterialPlanStatusServiceImpl3 {

    private static PlanCnsMaterialPlanStatusServiceImpl3 instance;

    public static PlanCnsMaterialPlanStatusServiceImpl3 getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusServiceImpl3();
        }
        return instance;
    }

    private PlanCnsCustExclDaoImpl planCnsCustExclDao = PlanCnsCustExclDaoImpl.getInstance();

    private EDMSourceSystemV1DaoImpl edmSourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    private EDMMaterialPlantV1DaoImpl edmMaterialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();

    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();

    private PlanCnsSoTypeInclDaoImpl planCnsSoTypeInclDao = PlanCnsSoTypeInclDaoImpl.getInstance();

    private EDMPlantV1DaoImpl edmPlantV1Dao = EDMPlantV1DaoImpl.getInstance();

    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Set<String> f1ASet, Set<String> f1BSet, Set<String> f1CSet, String time) {
        ResultObject resultObject = new ResultObject();
        EDMSalesOrderV1Entity edmSalesOrderV1Entity = (EDMSalesOrderV1Entity) o;

        boolean checkF1 = checkF1(edmSalesOrderV1Entity, f1ASet, f1BSet, f1CSet);
        boolean checkF2 = checkF2(edmSalesOrderV1Entity);
        if (!checkF1) {

            return null;
        }

        if (!checkF2) {
            return null;
        }
        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();
        //J1
        if (checkJ1(time, edmSalesOrderV1Entity)) {
            materialPlanStatusBo.setLocalPlant(edmSalesOrderV1Entity.getLocalPlant());
            materialPlanStatusBo.setLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
        } else {
            return null;
        }

        //D1
        materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);

        //T1
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = edmSourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (entityWithLocalSourceSystem != null) {
            materialPlanStatusBo.setSourceSystem(entityWithLocalSourceSystem.getSourceSystem());
        }

        //J2
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity) {
            materialPlanStatusBo.setMaterialNumber(materialGlobalV1Entity.getMaterialNumber());
            materialPlanStatusBo.setLocalParentCode(materialGlobalV1Entity.getLocalDpParentCode());
            materialPlanStatusBo.setPpc(materialGlobalV1Entity.getPrimaryPlanningCode());
        } else {
            return null;
        }

        //T5
        if (checkT5(materialPlanStatusBo)) {
            materialPlanStatusBo.setActive(IConstant.VALUE.X);
        }

        if (StringUtils.isNotEmpty(materialPlanStatusBo.getLocalParentCode())) {
            materialPlanStatusBo.setParentActive(IConstant.VALUE.X);
        }
        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private boolean checkF1(EDMSalesOrderV1Entity salesOrderV1Entity, Set<String> f1ASet, Set<String> f1BSet, Set<String> f1CSet) {
        String localPlant = salesOrderV1Entity.getLocalPlant();
        boolean f1A = (f1ASet.isEmpty() || f1ASet.contains(StringUtils.trim(localPlant)));
        boolean f1B = (f1BSet.isEmpty() || !f1BSet.contains(StringUtils.trim(localPlant)));

        if (f1A || f1B) {
            String localMaterialNumber = salesOrderV1Entity.getLocalMaterialNumber();
            List<EDMMaterialPlantV1Entity> materialPlantV1EntityList = edmMaterialPlantV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);

            for (EDMMaterialPlantV1Entity materialPlantV1Entity : materialPlantV1EntityList) {
                String localMrpType = materialPlantV1Entity.getLocalMrpType();
                boolean f1C = (f1CSet.isEmpty() || f1CSet.contains(StringUtils.trim(localMrpType)));

                if (f1C) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkF2(EDMSalesOrderV1Entity salesOrderV1Entity) {
        PlanCnsCustExclEntity cnsCustExclEntityF2A = planCnsCustExclDao.getEntityWithSalesOrgNotCustomerShipTo(salesOrderV1Entity.getLocalSalesOrg(), salesOrderV1Entity.getLocalShipToParty());
        PlanCnsCustExclEntity cnsCustExclEntityF2B = planCnsCustExclDao.getEntityWithSalesOrgIsCustomerShipTo(salesOrderV1Entity.getLocalSalesOrg(), salesOrderV1Entity.getLocalShipToParty());
        if (null != cnsCustExclEntityF2A||null !=cnsCustExclEntityF2B) {
            return true;
        }
        return false;
    }

    private boolean checkJ1(String time, EDMSalesOrderV1Entity edmSalesOrderV1Entity) {
        boolean flag = false;
        if (StringUtils.isNotEmpty(time)) {
                flag = Determine(time, edmSalesOrderV1Entity);
        }
        boolean flagTwo = false;
        if (StringUtils.isNotEmpty(edmSalesOrderV1Entity.getLocalSalesOrg()) && StringUtils.isNotEmpty(edmSalesOrderV1Entity.getLocalOrderType())) {
            PlanCnsSoTypeInclEntity entityWithSalesOrgAndOrderTypeA = planCnsSoTypeInclDao.getEntityWithSalesOrgAndOrderType(edmSalesOrderV1Entity.getLocalSalesOrg(), edmSalesOrderV1Entity.getLocalOrderType());
            PlanCnsSoTypeInclEntity entityWithSalesOrgAndOrderTypeB = planCnsSoTypeInclDao.getEntityWithSalesOrgAndNotOrderType(edmSalesOrderV1Entity.getLocalSalesOrg(), edmSalesOrderV1Entity.getLocalOrderType());
            List<PlanCnsSoTypeInclEntity> planCnsSoTypeInclEntityList = Arrays.asList(entityWithSalesOrgAndOrderTypeA,entityWithSalesOrgAndOrderTypeB);
            for(PlanCnsSoTypeInclEntity planCnsSoTypeInclEntity:planCnsSoTypeInclEntityList){
                if(planCnsSoTypeInclEntity!=null){
                    EDMPlantV1Entity plantWithLocalPlantAndCountry = edmPlantV1Dao.getPlantWithLocalPlantAndCountry(edmSalesOrderV1Entity.getLocalPlant(), planCnsSoTypeInclEntity.getCountry());
                    if (plantWithLocalPlantAndCountry != null) {
                        flagTwo = true;
                    }
                    break;
                }
            }
        }
        return flag && flagTwo;
    }

    private boolean Determine(String time, EDMSalesOrderV1Entity edmSalesOrderV1Entity) {
        Date localOrderCreateDate = DateUtils.stringToDate(edmSalesOrderV1Entity.getLocalOrderCreateDt(), DateUtils.F_yyyyMMdd);
        int timeNum = Integer.parseInt(time);
        Date date = new Date();
        Date offDate = DateUtils.offsetDate(date, -timeNum);
        if (localOrderCreateDate.getTime() >= offDate.getTime()) {
            return true;
        }
        return false;
    }

    private boolean checkT5(PlanCnsMaterialPlanStatusBo materialPlanStatusBo) {
        if (IConstant.VALUE.X.equals(materialPlanStatusBo.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusBo.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusBo.getNoPlanRelevant())) {
            return true;
        }
        return false;
    }

    public String getMaterialNumber(EDMSalesOrderV1Entity edmSalesOrderV1Entity) {
        if (StringUtils.isNotEmpty(edmSalesOrderV1Entity.getLocalMaterialNumber())) {
            EDMMaterialGlobalV1Entity entityWithLocalMaterialNumber = edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
            if (entityWithLocalMaterialNumber != null) {
                return entityWithLocalMaterialNumber.getMaterialNumber();
            }
        }
        return null;
    }

    private FailData writeFailData(EDMSalesOrderV1Entity salesOrderV1Entity, String errorCode, String errorValue) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.PLAN_CNS_MATERIAL_PLAN_STATUS);
        failData.setSourceSystem(salesOrderV1Entity.getSourceSystem());
        failData.setKey1(salesOrderV1Entity.getSalesOrderNo());
        failData.setKey2(salesOrderV1Entity.getSalesOrderItem());
        failData.setKey3(salesOrderV1Entity.getScheduleLineItem());
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }
}

package com.jnj.pangea.plan.cns_material_plan_status.service;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class PlanCnsMaterialPlanStatus_3ServiceImpl {

    private static PlanCnsMaterialPlanStatus_3ServiceImpl instance;

    public static PlanCnsMaterialPlanStatus_3ServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatus_3ServiceImpl();
        }
        return instance;
    }

    private  PlanCnsCustExclDaoImpl planCnsCustExclDao = PlanCnsCustExclDaoImpl.getInstance();

    private EDMSourceSystemV1DaoImpl edmSourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    private EDMMaterialPlantV1DaoImpl edmMaterialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();

    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();

    private PlanCnsSoTypeInclDaoImpl planCnsSoTypeInclDao = PlanCnsSoTypeInclDaoImpl.getInstance();

    private EDMPlantV1DaoImpl edmPlantV1Dao = EDMPlantV1DaoImpl.getInstance();

    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Set<String> f1Set,Set<String> f2Set,String time) {
        ResultObject resultObject = new ResultObject();
        EDMSalesOrderV1Entity edmSalesOrderV1Entity = (EDMSalesOrderV1Entity) o;
        //F2 filter
        boolean F2 = reluesTwo(edmSalesOrderV1Entity);
        if(F2==true){
            return null;
        }

        //F1 filter
        String localPlant = StringUtils.trim(edmSalesOrderV1Entity.getLocalPlant());

        boolean F1A = f1Set.contains(localPlant);

        if(F1A!=true){
            return null;
        }
        //F1 filter
        EDMMaterialPlantV1Entity eDMMaterialPlantV1Entity = null;
        if(edmSalesOrderV1Entity.getLocalMaterialNumber()!=null&&!edmSalesOrderV1Entity.getLocalMaterialNumber().equals("")){
            eDMMaterialPlantV1Entity = edmMaterialPlantV1Dao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
        }
        if(eDMMaterialPlantV1Entity!=null && !eDMMaterialPlantV1Entity.equals("")){
            String localMaterialType = StringUtils.trim(eDMMaterialPlantV1Entity.getLocalMrpType());
            boolean F1B = f2Set.contains(localMaterialType);
            if(F1B!=true){
                return null;
            }
        }

        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();
        //J1
        boolean flag = false;
        if(time!=null&&!time.equals("")){
            flag  = Determine(time,edmSalesOrderV1Entity);
        }
        boolean flagTwo = false;
        PlanCnsSoTypeInclEntity entityWithSalesOrgAndOrderType = null;
        if(!edmSalesOrderV1Entity.getLocalSalesOrg().equals("")&&!edmSalesOrderV1Entity.getLocalOrderType().equals("")){
            entityWithSalesOrgAndOrderType =  planCnsSoTypeInclDao.getEntityWithSalesOrgAndOrderType(edmSalesOrderV1Entity.getLocalSalesOrg(), edmSalesOrderV1Entity.getLocalOrderType());
            if(entityWithSalesOrgAndOrderType!=null){
                EDMPlantV1Entity plantWithLocalPlantAndCountry = edmPlantV1Dao.getPlantWithLocalPlantAndCountry(edmSalesOrderV1Entity.getLocalPlant(), entityWithSalesOrgAndOrderType.getCountry());
                if(plantWithLocalPlantAndCountry!=null&&!plantWithLocalPlantAndCountry.equals("")){
                    flagTwo = true;
                }
            }
        }
        boolean flagThree = false;
        if(edmSalesOrderV1Entity.getLocalPlant()!=null&&edmSalesOrderV1Entity.getLocalMaterialNumber()!=null){
            PlanCnsMaterialPlanStatusEntity entityWithLocalMaterialNumberAndlLocalPlant = planCnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlant(edmSalesOrderV1Entity.getLocalMaterialNumber(), edmSalesOrderV1Entity.getLocalPlant());
            if(entityWithLocalMaterialNumberAndlLocalPlant!=null){
                String dpRelevant = entityWithLocalMaterialNumberAndlLocalPlant.getDpRelevant();
                if(dpRelevant.equals("X")){
                    flagThree = true;
                }
            }

        }
        if(flag&&flagTwo&& flagThree){
            materialPlanStatusBo.setLocalPlant(edmSalesOrderV1Entity.getLocalPlant());
            materialPlanStatusBo.setLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
        }else{
            return null;
        }
        //D1
        materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);

        //T1
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = edmSourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if(entityWithLocalSourceSystem!=null&&!entityWithLocalSourceSystem.equals("")){
            materialPlanStatusBo.setSourceSystem(entityWithLocalSourceSystem.getSourceSystem());
        }
        //J2
        String MaterialNumber = getMaterialNumber(edmSalesOrderV1Entity);
        if(MaterialNumber!=null&&!MaterialNumber.equals("")){
            materialPlanStatusBo.setMaterialNumber(MaterialNumber);
        }
        //T2&&T3
        EDMMaterialGlobalV1Entity global = getGlobal(eDMMaterialPlantV1Entity);
        if(global!=null&&!global.equals("")){
            //T4
            if(global.getLocalDpParentCode()!=null&&!global.getLocalDpParentCode().equals("")){
                materialPlanStatusBo.setLocalParentCode(global.getLocalDpParentCode());
                materialPlanStatusBo.setParentActive("X");
            }
            if(global.getPrimaryPlanningCode()!=null&&!global.getPrimaryPlanningCode().equals("")){
                materialPlanStatusBo.setPpc(global.getPrimaryPlanningCode());
            }
        }
        //D2
        materialPlanStatusBo.setSpRelevant("");
        materialPlanStatusBo.setNoPlanRelevant("");
        //T5
        if(materialPlanStatusBo.getDpRelevant().equals("X")||materialPlanStatusBo.getDpRelevant().equals("X")||materialPlanStatusBo.getNoPlanRelevant().equals("X")){
            materialPlanStatusBo.setActive("X");
        }

        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private boolean Determine(String time, EDMSalesOrderV1Entity edmSalesOrderV1Entity) {
        int traztLong = (int) Double.parseDouble(time);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);
        Date localRequestedDateFormat = DateUtils.stringToDate(format, DateUtils.F_yyyyMMdd);
        Date fromDueDate = DateUtils.offsetDate(localRequestedDateFormat, -traztLong);
        String s = DateUtils.dateToString(fromDueDate, DateUtils.F_yyyyMMdd);
        int LocalOrderCreateDt = Integer.parseInt(edmSalesOrderV1Entity.getLocalOrderCreateDt());
        int SS =Integer.parseInt(s);
        if(LocalOrderCreateDt>SS){
            return true;
        }
        return false;
    }

    public String getMaterialNumber(EDMSalesOrderV1Entity edmSalesOrderV1Entity) {
        if(edmSalesOrderV1Entity.getLocalMaterialNumber()!=null&&!edmSalesOrderV1Entity.getLocalMaterialNumber().equals("")){
            EDMMaterialGlobalV1Entity entityWithLocalMaterialNumber = edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumber(edmSalesOrderV1Entity.getLocalMaterialNumber());
           if(entityWithLocalMaterialNumber!=null&&!entityWithLocalMaterialNumber.equals("")){
               return  entityWithLocalMaterialNumber.getMaterialNumber();
           }
        }
        return null;
    }

    public boolean  reluesTwo(EDMSalesOrderV1Entity edmSalesOrderV1Entity){
        PlanCnsCustExclEntity entityWithSalesOrgAndCustomerShipTo = null;
        //F2 rules
        if(edmSalesOrderV1Entity.getLocalSalesOrg()!=null && !edmSalesOrderV1Entity.getLocalSalesOrg().equals("")){
            entityWithSalesOrgAndCustomerShipTo = planCnsCustExclDao.getEntityWithSalesOrgAndCustomerShipTo(edmSalesOrderV1Entity.getLocalSalesOrg());
        }
        if(entityWithSalesOrgAndCustomerShipTo!=null){
            String localShipToParty = edmSalesOrderV1Entity.getLocalShipToParty();
            if(localShipToParty.equalsIgnoreCase(entityWithSalesOrgAndCustomerShipTo.getCustomerShipTo())){
                return true;
            }
        }
        return  false;
    }
    public EDMMaterialGlobalV1Entity getGlobal(EDMMaterialPlantV1Entity eDMMaterialPlantV1Entity){
        if(eDMMaterialPlantV1Entity!=null&&!eDMMaterialPlantV1Entity.equals("")){
           EDMMaterialGlobalV1Entity entityWithLocalMaterialNumber = edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumber(eDMMaterialPlantV1Entity.getLocalMaterialNumber());
            return  entityWithLocalMaterialNumber;
       }
        return null;
    }

}

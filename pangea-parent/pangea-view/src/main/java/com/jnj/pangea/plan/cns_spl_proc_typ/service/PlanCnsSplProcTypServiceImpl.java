package com.jnj.pangea.plan.cns_spl_proc_typ.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ProjectOneT460TDaoImpl;
import com.jnj.pangea.common.entity.projectone.T460AEntity;
import com.jnj.pangea.common.entity.projectone.T460TEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_fi.service.EDMMatPlantFiServiceImpl;
import com.jnj.pangea.plan.cns_spl_proc_typ.bo.PlanCnsSplProcTypBo;

import java.util.List;

public class PlanCnsSplProcTypServiceImpl implements ICommonService{
    private static PlanCnsSplProcTypServiceImpl instance;

    public static PlanCnsSplProcTypServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSplProcTypServiceImpl();
        }
        return instance;
    }
    private EDMSourceSystemV1DaoImpl systemV1Dao= EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneT460TDaoImpl projectOneT460TDao= ProjectOneT460TDaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject=new ResultObject();
        T460AEntity t460AEntity= (T460AEntity) o;
        PlanCnsSplProcTypBo planCnsSplProcTypBo = new PlanCnsSplProcTypBo();
        //T1
        String  sourceSystem=getFieldWithT1(IConstant.VALUE.PROJECT_ONE);

        //J1
        String sobsl="";
        if(null!=t460AEntity){
            sobsl = t460AEntity.getSobsl();
        }
        String  ltext=getFieldWithJ1(sobsl,IConstant.VALUE.EN);

        planCnsSplProcTypBo.setSourceSystem(sourceSystem);
        planCnsSplProcTypBo.setLocalSplProcType(sobsl);
        planCnsSplProcTypBo.setLocalSplProcTypeDesc(ltext);
        planCnsSplProcTypBo.setSplProcType("");
        planCnsSplProcTypBo.setSplProcTypeDesc("");

        resultObject.setBaseBo(planCnsSplProcTypBo);
        return resultObject;
    }

    private String getFieldWithJ1(String sobsl,String spras) {
        T460TEntity t460TEntity= projectOneT460TDao.getEntityWithSobslAndSpras(sobsl,spras);
        if (null!=t460TEntity) {
            return t460TEntity.getLtext();
        }
        return "";
    }

    private String getFieldWithT1(String localSourceSystem){
        return systemV1Dao.getSourceSystemWithLocalSourceSystem(localSourceSystem);
    }
}

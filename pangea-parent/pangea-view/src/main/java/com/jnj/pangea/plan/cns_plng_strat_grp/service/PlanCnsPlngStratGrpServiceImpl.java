package com.jnj.pangea.plan.cns_plng_strat_grp.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT461XDaoImpl;
import com.jnj.pangea.common.entity.project_one.T461PEntity;
import com.jnj.pangea.common.entity.project_one.T461XEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_plng_strat_grp.bo.PlanCnsPlngStratGrpBo;

public class PlanCnsPlngStratGrpServiceImpl implements ICommonService {

    private static PlanCnsPlngStratGrpServiceImpl instance;

    public static PlanCnsPlngStratGrpServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlngStratGrpServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    ProjectOneT461XDaoImpl t461XDao = ProjectOneT461XDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        T461PEntity t461PEntity = (T461PEntity) o;

        PlanCnsPlngStratGrpBo planCnsPlngStratGrpBo = new PlanCnsPlngStratGrpBo();

        String sourceSystem = null;
        if(null != sourceSystemV1Dao.getSourceSystemWithProjectOne()) {
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            planCnsPlngStratGrpBo.setSourceSystem(sourceSystem);
        }

        String strgr = t461PEntity.getStrgr();
        planCnsPlngStratGrpBo.setLocalPlanStratGrp(strgr);

        T461XEntity t461XEntity = t461XDao.getEntityWithStrgrAndSpras(strgr);
        if (null != t461XEntity){
            planCnsPlngStratGrpBo.setLocalPlanStratGrpDesc(t461XEntity.getText40());
        }

        resultObject.setBaseBo(planCnsPlngStratGrpBo);
        return resultObject;
    }
}

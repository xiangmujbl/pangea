package com.jnj.pangea.plan.cns_abc_ind.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ProjectOneTmabctDaoImpl;
import com.jnj.pangea.common.entity.projectone.TmabcEntity;
import com.jnj.pangea.common.entity.projectone.TmabctEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_abc_ind.bo.PlanCnsAbcIndBo;

public class PlanCnsAbcIndServiceImpl implements ICommonService {
    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new PlanCnsAbcIndServiceImpl();
        }
        return instance;
    }

    private ProjectOneTmabctDaoImpl tmabctDao = ProjectOneTmabctDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        TmabcEntity tmabcEntity = (TmabcEntity) o;

        PlanCnsAbcIndBo planCnsAbcIndBo = new PlanCnsAbcIndBo();

        //sourceSystem
        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        planCnsAbcIndBo.setSourceSystem(sourceSystem);

        //localIndicator
        String maabc = tmabcEntity.getMaabc();
        planCnsAbcIndBo.setLocalIndicator(maabc);

        // localIndicatorDescription
        TmabctEntity tmabctEntity = tmabctDao.getEntityWithMaabc(maabc);
        if (null != tmabctEntity) {
            planCnsAbcIndBo.setLocalIndicatorDescription(tmabctEntity.getTmabc());
        }

        resultObject.setBaseBo(planCnsAbcIndBo);

        return resultObject;
    }
}

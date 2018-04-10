package com.jnj.pangea.plan.cns_lot_size_key_trans.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT439TDaoImpl;
import com.jnj.pangea.common.entity.project_one.T439TEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_lot_size_key_trans.bo.PlanCnsLotSizeKeyTransBo;

public class PlanCnsLotSizeKeyTransServiceImpl implements ICommonService {

    private static PlanCnsLotSizeKeyTransServiceImpl instance;

    public static PlanCnsLotSizeKeyTransServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsLotSizeKeyTransServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    ProjectOneT439TDaoImpl t439TDao = ProjectOneT439TDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        T439TEntity t439TEntity = (T439TEntity) o;

        PlanCnsLotSizeKeyTransBo cnsLotSizeKeyBo = new PlanCnsLotSizeKeyTransBo();

        String sourceSystem = null;
        if (null != sourceSystemV1Dao.getSourceSystemWithProjectOne()) {
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            cnsLotSizeKeyBo.setSourceSystem(sourceSystem);
        }

        String disls = t439TEntity.getDisls();
        cnsLotSizeKeyBo.setLocalLotSizeKey(disls);

        T439TEntity t439TEntity1 = t439TDao.getEntityWithDislsAndSpras(disls, IConstant.VALUE.EN);
        if (null != t439TEntity1) {
            cnsLotSizeKeyBo.setLocalLotSizeKeyDescription(t439TEntity1.getLoslt());
        }

        resultObject.setBaseBo(cnsLotSizeKeyBo);
        return resultObject;
    }
}

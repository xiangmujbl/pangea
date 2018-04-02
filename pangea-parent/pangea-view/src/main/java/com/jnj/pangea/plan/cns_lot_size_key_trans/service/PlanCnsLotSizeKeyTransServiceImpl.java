package com.jnj.pangea.plan.cns_lot_size_key_trans.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT439TDaoImpl;
import com.jnj.pangea.common.entity.project_one.T439Entity;
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

        T439Entity t439Entity = (T439Entity) o;

        PlanCnsLotSizeKeyTransBo cnsLotSizeKeyBo = new PlanCnsLotSizeKeyTransBo();

        String sourceSystem = null;
        if(null != sourceSystemV1Dao.getSourceSystemWithProjectOne()) {
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            cnsLotSizeKeyBo.setSourceSystem(sourceSystem);
        }

        String disls = t439Entity.getDisls();
        cnsLotSizeKeyBo.setLocalLotSizeKey(disls);

        T439TEntity t439TEntity = t439TDao.getEntityWithDislsAndSpras(disls);
        if (null != t439TEntity){
            cnsLotSizeKeyBo.setLocalLotSizeKeyDescription(t439TEntity.getLoslt());
        }

        resultObject.setBaseBo(cnsLotSizeKeyBo);

        return resultObject;
    }
}

package com.jnj.pangea.plan.cns_lot_size_key.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT439TDaoImpl;
import com.jnj.pangea.common.entity.project_one.T439Entity;
import com.jnj.pangea.common.entity.project_one.T439TEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_lot_size_key.bo.PlanCnsLotSizeKeyBo;

public class PlanCnsLotSizeKeyServiceImpl implements ICommonService {

    private static PlanCnsLotSizeKeyServiceImpl instance;

    public static PlanCnsLotSizeKeyServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsLotSizeKeyServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    ProjectOneT439TDaoImpl t439TDao = ProjectOneT439TDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        T439Entity t439Entity = (T439Entity) o;

        PlanCnsLotSizeKeyBo cnsLotSizeKeyBo = new PlanCnsLotSizeKeyBo();

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

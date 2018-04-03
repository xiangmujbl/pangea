package com.jnj.pangea.plan.cns_lot_size_key.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT439ADaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT439TDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.T439AEntity;
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

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneT439TDaoImpl t439tDao = ProjectOneT439TDaoImpl.getInstance();
    private ProjectOneT439ADaoImpl t439aDao = ProjectOneT439ADaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        T439TEntity t439tEntity = (T439TEntity) o;

        PlanCnsLotSizeKeyBo cnsLotSizeKeyBo = new PlanCnsLotSizeKeyBo();
        String sourceSystem = null;
        if(null != sourceSystemV1Dao.getSourceSystemWithProjectOne()) {
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            cnsLotSizeKeyBo.setSourceSystem(sourceSystem);
        }

        cnsLotSizeKeyBo.setLocalLotSizeKey(t439tEntity.getDisls());

        T439TEntity t439TEntityClone = t439tDao.getEntityWithSpras();
        if (null != t439TEntityClone){
            cnsLotSizeKeyBo.setLocalLotSizeKeyDescription(t439TEntityClone.getLoslt());
        }

        T439AEntity t439aEntity = t439aDao.getEntityWithDisls(t439tEntity.getDisls());
        if (null != t439aEntity){
            cnsLotSizeKeyBo.setPeriod(t439aEntity.getLoskz());
            cnsLotSizeKeyBo.setQuantity(t439aEntity.getPeraz());
        }

        cnsLotSizeKeyBo.setLotSizeKey("");
        cnsLotSizeKeyBo.setLotSizeKeyDescription("");
        cnsLotSizeKeyBo.setComments("");

        resultObject.setBaseBo(cnsLotSizeKeyBo);
        return resultObject;
    }
}

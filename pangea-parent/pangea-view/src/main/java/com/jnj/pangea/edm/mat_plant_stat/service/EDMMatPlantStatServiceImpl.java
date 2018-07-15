package com.jnj.pangea.edm.mat_plant_stat.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.T141Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_stat.bo.EDMMatPlantStatBo;

public class EDMMatPlantStatServiceImpl implements ICommonService {

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantStatServiceImpl();
        }
        return instance;
    }
    private EDMSourceSystemV1DaoImpl EDMSourceSystemV1Dao=EDMSourceSystemV1DaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        T141Entity t141Entity = (T141Entity) o;

        EDMMatPlantStatBo matPlantStatBo = new EDMMatPlantStatBo();

        // rule T1
        matPlantStatBo.setSourceSystem(EDMSourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE).getSourceSystem());

        matPlantStatBo.setLocalPlantStatus(t141Entity.getMmsta());

        resultObject.setBaseBo(matPlantStatBo);

        return resultObject;
    }

}

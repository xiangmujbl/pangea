package com.jnj.pangea.edm.mat_plant_stat.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMMatPlantStatDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectone.T141Entity;
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
    private EDMMatPlantStatDaoImpl EDMMatPlantStatDao=EDMMatPlantStatDaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        T141Entity t141Entity = (T141Entity) o;

        EDMMatPlantStatBo matPlantStatBo = new EDMMatPlantStatBo();

        // rule T1
        matPlantStatBo.setSourceSystem(EDMMatPlantStatDao.getFieldWithT1().getSourceSystem());

        matPlantStatBo.setLocalPlantStatus(t141Entity.getMmsta());

        resultObject.setBaseBo(matPlantStatBo);

        return resultObject;
    }

}

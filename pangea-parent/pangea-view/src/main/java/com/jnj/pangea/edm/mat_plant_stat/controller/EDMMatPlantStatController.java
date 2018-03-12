package com.jnj.pangea.edm.mat_plant_stat.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.projectone.T141Entity;
import com.jnj.pangea.edm.mat_plant_stat.Bo.EDMMatPlantStatBo;
import com.jnj.pangea.edm.mat_plant_stat.service.EDMMatPlantStatServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMMatPlantStatController extends CommonController {

    EDMMatPlantStatServiceImpl matPlantStatService = new EDMMatPlantStatServiceImpl();

    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        T141Entity t141Entity = BeanUtil.mapToBean(rawMap,new T141Entity());
        EDMMatPlantStatBo matPlantStatBo = new EDMMatPlantStatBo();

        matPlantStatService.getSourceSystem(matPlantStatBo);
        matPlantStatBo.setLocalPlantStatus(t141Entity.getMmsta());

        resultObject.setBaseBo(matPlantStatBo);
        return resultObject;
    }
}

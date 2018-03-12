package com.jnj.pangea.edm.global_base_unit.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.GlobalBaseUnitEntity;
import com.jnj.pangea.edm.global_base_unit.bo.EDMGlobalBaseUnitBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMGlobalBaseUnitController extends CommonController {
    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        GlobalBaseUnitEntity globalBaseUnitEntity = BeanUtil.mapToBean(rawMap,new GlobalBaseUnitEntity());
        EDMGlobalBaseUnitBo globalBaseUnitBo = new EDMGlobalBaseUnitBo();
        globalBaseUnitBo.setGbu(globalBaseUnitEntity.getGbu());
        globalBaseUnitBo.setGbuName(globalBaseUnitEntity.getGbuName());

        resultObject.setBaseBo(globalBaseUnitBo);
        return resultObject;
    }
}

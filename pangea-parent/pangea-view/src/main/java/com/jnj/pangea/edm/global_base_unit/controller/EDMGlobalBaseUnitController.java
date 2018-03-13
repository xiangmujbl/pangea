package com.jnj.pangea.edm.global_base_unit.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.GlobalBaseUnitEntity;
import com.jnj.pangea.edm.global_base_unit.bo.EDMGlobalBaseUnitBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMGlobalBaseUnitController extends CommonController {
    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        GlobalBaseUnitEntity globalBaseUnitEntity = BeanUtil.mapToBean(raw.getValue().toMap(), GlobalBaseUnitEntity.class);
        EDMGlobalBaseUnitBo globalBaseUnitBo = new EDMGlobalBaseUnitBo();
        globalBaseUnitBo.setGbu(globalBaseUnitEntity.getGbu());
        globalBaseUnitBo.setGbuName(globalBaseUnitEntity.getGbuName());

        resultObject.setBaseBo(globalBaseUnitBo);
        return resultObject;
    }
}

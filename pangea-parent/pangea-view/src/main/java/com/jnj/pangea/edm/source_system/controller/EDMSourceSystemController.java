package com.jnj.pangea.edm.source_system.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.SourceSystemEntity;
import com.jnj.pangea.edm.source_system.bo.EDMSourceSystemBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMSourceSystemController extends CommonController {

    @Override
    public ResultObject process(Map<String, Object> rawMap) {

        ResultObject resultObject = new ResultObject();

        SourceSystemEntity sourceSystemEntity = BeanUtil.mapToBean(rawMap, new SourceSystemEntity());

        EDMSourceSystemBo sourceSystemBo = new EDMSourceSystemBo();
        sourceSystemBo.setSourceSystem(sourceSystemEntity.getSourceSystem());
        sourceSystemBo.setSourceSystemName(sourceSystemEntity.getSourceSystemName());
        sourceSystemBo.setLocalSourceSystem(sourceSystemEntity.getLocalSourceSystem());
        sourceSystemBo.setLocalSourceSystemName(sourceSystemEntity.getLocalSourceSystemName());

        resultObject.setBaseBo(sourceSystemBo);

        return resultObject;
    }
}
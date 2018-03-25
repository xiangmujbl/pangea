package com.jnj.pangea.${system}.${_name}.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.${mainEntitySystem}.${mainEntityName?cap_first}Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.${system}.${_name}.service.${fullName}ServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class ${fullName}Controller extends CommonController {

    private ICommonService service = ${fullName}ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ${mainEntityName?cap_first}Entity.class), null);
    }
}

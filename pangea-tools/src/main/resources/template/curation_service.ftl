package com.jnj.pangea.${system}.${_name}.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
<#list entities as entity>
import com.jnj.pangea.common.entity.${entity.system}.${entity.name?cap_first}Entity;
import com.jnj.pangea.common.dao.impl.${entity.system}.${entity.name?cap_first}DaoImpl;
</#list>
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.${system}.${_name}.bo.${fullName}Bo;

public class ${fullName}ServiceImpl implements ICommonService {

    private static ${fullName}ServiceImpl instance;

    public static ${fullName}ServiceImpl getInstance() {
        if (instance == null) {
            instance = new ${fullName}ServiceImpl();
        }
        return instance;
    }

<#list entities as entity>
    private ${entity.name?cap_first}DaoImpl ${entity.name}Dao = ${entity.name?cap_first}DaoImpl.getInstance();
</#list>

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ${mainEntityName?cap_first}Entity ${mainEntityName}Entity = (${mainEntityName?cap_first}Entity) o;

        ${fullName}Bo ${name?uncap_first}Bo = new ${fullName}Bo();
        // TODO add logic

        resultObject.setBaseBo(${name?uncap_first}Bo);
        return resultObject;
    }
}

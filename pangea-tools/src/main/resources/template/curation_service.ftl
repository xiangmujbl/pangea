package com.jnj.pangea.${system}.${_name}.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
<#list entities as entity>
import com.jnj.pangea.common.entity.${entity.system}.${entity.fullName}Entity;
<#if entity.fullName != main.fullName>
import com.jnj.pangea.common.dao.impl.${entity.system}.${entity.fullName}DaoImpl;
</#if>
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
<#if entity.fullName != main.fullName>
    private ${entity.fullName}DaoImpl ${entity.name?uncap_first}Dao = ${entity.fullName}DaoImpl.getInstance();
</#if>
</#list>

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ${main.fullName}Entity ${main.name?uncap_first}Entity = (${main.fullName}Entity) o;

        ${fullName}Bo ${name?uncap_first}Bo = new ${fullName}Bo();
        // TODO add logic

        resultObject.setBaseBo(${name?uncap_first}Bo);
        return resultObject;
    }
}

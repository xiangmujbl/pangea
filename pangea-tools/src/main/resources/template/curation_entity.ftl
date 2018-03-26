package com.jnj.pangea.common.entity.${entity.system};

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ${entity.fullName}Entity extends CommonEntity {

<#list entity.fields as field>
    private String ${field};
</#list>

    public ${entity.fullName}Entity(Map<String, Object> map) {
        super(map);

    <#list entity.fields as field>
        set${field?cap_first}((String) map.get("${field}"));
    </#list>
    }

<#list entity.fields as field>
    public String get${field?cap_first}() {
        return this.${field};
    }

    public void set${field?cap_first}(String ${field?uncap_first}) {
        this.${field} = ${field?uncap_first};
    }

</#list>
}

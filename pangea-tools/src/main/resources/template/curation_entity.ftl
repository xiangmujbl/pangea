package com.jnj.pangea.common.entity.${entitySystem};

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ${entityName?cap_first}Entity extends CommonEntity {

<#list entityFields as field>
    private String ${field};
</#list>

    public ${entityName?cap_first}Entity(Map<String, Object> map) {
        super(map);

    <#list entityFields as field>
        set${field?cap_first}((String) map.get("${field}"));
    </#list>
    }

<#list entityFields as field>
    public String get${field?cap_first}() {
        return ${field};
    }

    public void set${field?cap_first}(String ${field?uncap_first}) {
        ${field} = ${field?uncap_first};
    }

</#list>
}

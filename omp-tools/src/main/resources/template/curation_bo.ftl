package com.jnj.pangea.${system}.${_name}.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class ${fullName}Bo extends BaseBo {

<#list boFields as field>
    private String ${field};
</#list>

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("", "")
                .toJsonString();
    }

<#list boFields as field>
    public String get${field?cap_first}() {
        return this.${field};
    }

    public void set${field?cap_first}(String ${field?uncap_first}) {
        this.${field} = ${field?uncap_first};
    }

</#list>
}

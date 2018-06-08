package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMFormV1Entity extends CommonEntity {

    private String formName;
    private String formDescription;

    public EDMFormV1Entity(Map<String, Object> map) {
        super(map);

        setFormName((String) map.get("formName"));
        setFormDescription((String) map.get("formDescription"));
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormDescription() {
        return this.formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

}

package com.jnj.pangea.edm.form_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * EDMForm/Functional name - rework - Curation
 * AEAZ-8295
 */
public class EDMFormV1Bo extends BaseBo {

    private String  formName ;
    private String formDescription ;

    
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("formName", this.formName)
                .toJsonString();
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    @Override
    public String toString() {
        return "EDMFormV1{" +
                "formName='" + formName + '\'' +
                ", formDescription='" + formDescription + '\'' +
                '}';
    }
}

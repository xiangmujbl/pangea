package com.jnj.pangea.common.entry.ngems;

import com.jnj.pangea.common.CommonEntry;

public class FormEntity extends CommonEntry {
    private String formName;
    private String formDescription;

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

}

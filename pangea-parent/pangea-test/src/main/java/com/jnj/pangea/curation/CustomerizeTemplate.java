package com.jnj.pangea.curation;


import com.jnj.adf.curation.customize.CETemplateImpl;
import com.jnj.adf.curation.customize.Template;

/**
 * Created by XZhan290 on 2018/4/20.
 */
public class CustomerizeTemplate {

    public static void main(String[] args) {

        Template template = new CETemplateImpl();
        String customizeXml = "auto_curation/xml/samples/edm_material_global.xml";
        template.generateAll(customizeXml);
    }

}

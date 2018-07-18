package com.jnj.pangea.curation;


import com.jnj.adf.curation.customize.CETemplateImpl;
import com.jnj.adf.curation.customize.Template;

/**
 * Created by XZhan290 on 2018/4/20.
 */
public class CustomerizeTemplate {

    public static void main(String[] args) {

        Template template = new CETemplateImpl();
        String customizeXml = "auto_curation/xml/gdm/OMPGdmPLOFromERPV1.xml";
//        String customizeHook = "auto_curation/lib/pangea-hook.jar";
//        template.generateAll(customizeHook, customizeXml);
        template.generateAll(customizeXml);
    }

}

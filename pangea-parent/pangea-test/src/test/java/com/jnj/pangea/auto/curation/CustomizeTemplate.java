package com.jnj.pangea.auto.curation;

import com.jnj.adf.curation.customize.CETemplateImpl;
import com.jnj.adf.curation.customize.Template;

/**
 * Created by XZhan290 on 2018/4/20.
 */
public class CustomizeTemplate {

    public static void main(String[] args) {
        Template template = new CETemplateImpl();
        String customizeXml = "jde/xml/omp/gdm_product_unit_conversion.xml";
        template.generateAll(customizeXml);
    }
}
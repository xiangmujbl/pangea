package com.jnj.pangea.auto.curation;


import com.jnj.adf.curation.customize.CETemplateImpl;
import com.jnj.adf.curation.customize.Template;

import java.io.IOException;

/**
 * Created by XZhan290 on 2018/4/20.
 */
public class CustomerizeTemplate {

    public static void main(String[] args) throws IOException {

        Template template = new CETemplateImpl();
        template.generateAll("edm_currency.xml");

//        JarGenerate jarGenerate = new JarGenerateImpl();
//        jarGenerate.jarGenerate();
//        template.generateAll(customizeXml,"jar/omega-hooks.jar");
    }

}

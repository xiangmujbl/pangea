package com.jnj.pangea.curation;

import com.jnj.adf.process.GenFeature;
import com.jnj.adf.process.GenFeatureImpl;

public class TestGenFeature {

    public static void main(String[] args) {
        GenFeature o = new GenFeatureImpl();
        o.genFeatureByExcel("auto_curation/feature/gdm/EDMPlant.xlsx", "auto_curation/xml/samples/edm_material_global.xml", "AEAZ-1234");
    }
}

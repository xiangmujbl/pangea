package com.jnj.pangea.auto.curation;

import com.jnj.adf.process.GenFeature;
import com.jnj.adf.process.GenFeatureImpl;

public class TestGenFeature {

    public static void main(String[] args){
        GenFeature o = new GenFeatureImpl();
        o.genFeatureByExcel("autocuration/omega/data/GDMLocationType.xlsx","xml/omp/gdm_location_type.xml", "AEAZ-3944");
    }
}

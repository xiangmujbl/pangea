package com.jnj.pangea.edm.material.auom;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/material/auom/EDMMaterialAUOM_Projectone.feature"},
        tags = {"@pangea_test"},
        plugin = {"pretty", "html:target/Destination"},
        glue = "com.jnj.pangea.sentence")
public class MaterialAuomTest_ProjectOne {

}
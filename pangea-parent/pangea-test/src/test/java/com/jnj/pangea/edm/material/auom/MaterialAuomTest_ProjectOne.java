package com.jnj.pangea.edm.material.auom;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/material/auom/EDMMaterialAUOM_Projectone.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-511 EDMMaterialAUOM.json"})
public class MaterialAuomTest_ProjectOne {

}
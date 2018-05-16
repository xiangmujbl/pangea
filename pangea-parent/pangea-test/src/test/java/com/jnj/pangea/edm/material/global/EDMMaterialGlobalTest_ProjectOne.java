package com.jnj.pangea.edm.material.global;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/material/global/EDMMaterialGlobal_ProjectOne.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-509 EDMMaterialGlobal.json"})
public class EDMMaterialGlobalTest_ProjectOne {

}
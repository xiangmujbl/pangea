package com.jnj.pangea.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMMatPlantFi_ProjectOne.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ- EDMMatPlantFi.json"})
public class EDMMatPlantFiTest_ProjectOne {

}
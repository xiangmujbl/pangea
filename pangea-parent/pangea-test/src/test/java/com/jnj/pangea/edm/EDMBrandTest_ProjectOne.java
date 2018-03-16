package com.jnj.pangea.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMBrand_ProjectOne.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:src/test/resources/cucumber_test/reports/json/EDMBrand.json"})
//        plugin = {"pretty", "html:target/Destination"})
public class EDMBrandTest_ProjectOne {

}
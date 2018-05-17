package com.jnj.pangea.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMInspectionLot.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/EDMInspectionLot.json"})
public class EDMInspectionLotTest {
}

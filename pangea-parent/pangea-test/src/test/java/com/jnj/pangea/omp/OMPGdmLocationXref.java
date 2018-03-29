package com.jnj.pangea.omp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/omp/OMPGdmLocationXref.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/ OMPGdmLocationXref.json"})
public class OMPGdmLocationXref {
}

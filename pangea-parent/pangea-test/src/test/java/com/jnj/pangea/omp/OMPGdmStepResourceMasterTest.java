package com.jnj.pangea.omp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/omp/OMPGdmStepResourceMaster.feature"},
        tags = {"@Scenario1"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-9092 OMPGdmStepResourceMaster.json"})
public class OMPGdmStepResourceMasterTest {
}


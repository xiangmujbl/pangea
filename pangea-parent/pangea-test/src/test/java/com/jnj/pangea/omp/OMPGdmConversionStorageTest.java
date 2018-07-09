package com.jnj.pangea.omp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/omp/OMPGdmConversionStorage.feature"},
        tags = {"@Scenario1"},
//        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-3688 OMPGdmConversionStorage.json"})
public class OMPGdmConversionStorageTest {

}

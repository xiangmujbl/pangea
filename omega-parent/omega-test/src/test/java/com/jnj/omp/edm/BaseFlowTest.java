package com.jnj.omp.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        tags = {"@omega_test"},
        glue = "com.jnj.omp.sentence",
        plugin = {"pretty", "html:target/Destination", "json:target/Destination/PangeaTest.json"})
public class BaseFlowTest {
}

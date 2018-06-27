package com.jnj.pangea;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        tags = {"@pangea","@AEAZ-5745"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "html:target/Destination", "json:target/Destination/AEAZ-5745 OMPGDMInTransitStock.json"})
public class BaseFlowTest {
}

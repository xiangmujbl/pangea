package com.jnj.pangea;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/unit_of_measure.feature"},
        tags = {"@pangea_test"},
        plugin = {"pretty", "html:target/Destination"})
public class UnitOfMeasureTest {

}
package com.jnj.omp.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMCountry.feature"},
        tags = {"@omega_test"},
        glue = "com.jnj.omp.sentence",
        plugin = {"pretty", "html:target/Destination"})
public class EDMCountryTest {
}
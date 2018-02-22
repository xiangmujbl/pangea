package com.jnj.pangea;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/material_auom.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence.PangeaSteps",
        plugin = {"pretty", "html:target/Destination"})
public class MaterialAuomTest {

}
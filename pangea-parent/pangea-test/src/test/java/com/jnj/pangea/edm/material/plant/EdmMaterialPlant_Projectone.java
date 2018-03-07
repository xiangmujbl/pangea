package com.jnj.pangea.edm.material.plant;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/material/plant/EdmMaterialPlant_Projectone.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "html:target/Destination"})
public class EdmMaterialPlant_Projectone {
}

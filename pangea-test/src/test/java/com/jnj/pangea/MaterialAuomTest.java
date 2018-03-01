package com.jnj.pangea;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/materialAUOM/EDMMaterialAUOM_Projectone.feature"},
        tags = {"@EDMMaterialAUOM_projectOne"},
        plugin = {"pretty", "html:target/Destination"})
public class MaterialAuomTest {

}
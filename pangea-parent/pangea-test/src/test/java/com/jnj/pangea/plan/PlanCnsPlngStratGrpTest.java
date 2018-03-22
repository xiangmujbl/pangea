package com.jnj.pangea.plan;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/plan/PlanCnsPlngStratGrp.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/CnsLotSizeKey.json"})
public class PlanCnsPlngStratGrpTest {

}

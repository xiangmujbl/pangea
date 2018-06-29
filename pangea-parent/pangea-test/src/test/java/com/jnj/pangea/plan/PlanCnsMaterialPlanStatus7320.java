package com.jnj.pangea.plan;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/plan/PlanCnsMaterialPlanStatus7320.feature"},
        tags = {"@Scenario3"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-7320 PlanCnsMaterialPlanStatus.json"})
public class PlanCnsMaterialPlanStatus7320 {

}

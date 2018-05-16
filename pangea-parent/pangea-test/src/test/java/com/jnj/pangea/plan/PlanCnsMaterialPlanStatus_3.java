package com.jnj.pangea.plan;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/plan/PlanCnsMaterialPlanStatus_3.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-3216 PlanCnsMaterialPlanStatus_3.json"})
public class PlanCnsMaterialPlanStatus_3 {

}

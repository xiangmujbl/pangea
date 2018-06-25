package com.jnj.pangea.omp;

/*
 * Create by IntelliJ IDEA
 * @author:zhiqiang.Tao
 * @date:2018/6/13 16:49
 * @describe:
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/omp/OMPGdmForecast.feature"},
        tags = {"@Scenario1"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-6186 OMPGdmForecast.json"})
public class OMPGdmForecastTest {
}
package com.jnj.pangea.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EDMSalesHistoryV1Test
 * @Package com.jnj.pangea.edm
 * @Description: cucumber test of EDMSalesHistoryV1 - AEAZ-6188
 * @date 2018/6/14 15:00
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMSalesHistoryV1.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-5739 EDMSalesHistoryV1.json"})
public class EDMSalesHistoryV1Test {

}

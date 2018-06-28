package com.jnj.pangea.omp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/omp/OMPGdmStockInventoryStocks.feature"},
        tags = {"@Scenario1"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-6178 OMPGdmStockInventoryStocks.json"})
public class OMPGdmStock_InventoryStocksTest {
}

package com.jnj.pangea.edm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/edm/EDMMfgRtngSeq.feature"},
        tags = {"@pangea_test"},
        glue = "com.jnj.pangea.sentence",
        plugin = {"pretty", "json:target/reports/json/AEAZ-7854 EDMMfgRtngSeq.json"})
public class EDMMfgRtngSeqTest {
}

package com.jnj.pangea.common;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.utils.Util;
import com.jnj.pangea.common.utils.RestUtil;
import com.jnj.pangea.common.utils.Utils;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class CommonSteps extends TestBase {

    public CommonSteps() {

        When("^I import data from the excelFile \"([^\"]*)\" in the sheet \"([^\"]*)\"$", (String excelPath, String sheetName) -> {
            doExcelData(excelPath, sheetName.split(","), "import_data");
        });

        And("^I verify the data from DB match the expected data from excelFile \"([^\"]*)\" in the sheet \"([^\"]*)\"$", (String excelPath, String sheetName) -> {
            doExcelData(excelPath, sheetName.split(","), "check_data_exist");
        });

        And("^I check region data deleted in the excelFile \"([^\"]*)\" in the sheet \"([^\"]*)\"$", (String excelPath, String sheetName) -> {
            doExcelData(excelPath, sheetName.split(","), "check_data_no_exist");
        });

        And("^I delete all data in the excelFile \"([^\"]*)\" in the sheet \"([^\"]*)\"$", (String excelPath, String sheetName) -> {
            doExcelData(excelPath, sheetName.split(","), "delete_data");
        });

        Given("^I import \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String regionPath, String keyFields, DataTable table) -> {
            Set<String> keySet = importData(table.raw(), regionPath, keyFields.split(","));
            ((Map) keys.get()).put(regionPath, keySet);
        });

        And("^init client$", () -> {
            setUp();
        });

        And("^I check region data \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String regionPath, String keyFields, DataTable table) -> {
            checkData(table.raw(), keyFields.split(","), regionPath);
        });

        And("^the following expected data should exist in the region \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String regionPath, String keyFields, DataTable table) -> {
            checkData(table.raw(), keyFields.split(","), regionPath);
        });

        And("^I check region data \"([^\"]*)\" by keyFields \"([^\"]*)\" with seperator \"([^\"]*)\"$", (String regionPath, String keyFields, String seperator, DataTable table) -> {
            checkDataWithSeperator(table.raw(), keyFields.split(","), regionPath, seperator);
        });

        And("^I check region with no data \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String regionPath, String keyFields, DataTable table) -> {
            checkNoKeys(table.raw(), keyFields.split(","), regionPath);
        });

        And("wait (\\d+) millisecond", (n) -> {
            Util.sleep(Long.parseLong(n.toString()));
        });

        And("I wait \"([^\"]*)\" Async Queue complete", (String fullPath) -> {
            Utils.waitAqComplete(getRealRegionPath(fullPath));
        });

        And("^I delete the test data$", () -> {
            ((Map) keys.get()).forEach((regionPath, keySet) -> {
                ((Set<String>) keySet).forEach((key) -> {
                    adfService.onPath(getRealRegionPath(regionPath + "")).remove(key);
                });
            });
            ((Map) keys.get()).forEach((regionPath, keySet) -> {
                ((Set<String>) keySet).forEach((key) -> {
                    String vv = adfService.onPath(getRealRegionPath(regionPath + "")).get(key);
                    Assert.assertNull(vv);
                });
            });
            ((Map) keys.get()).clear();
            System.out.println("finish delete test data:" + new Date());
        });

        And("^I check view cleared$", () -> {
            System.out.println("start check view data:" + new Date());
            ((HashMap) viewKeys.get()).forEach((regionPath, keySet) -> {
                ((Set<String>) keySet).forEach((key) -> {
                    String vv = adfService.onPath(regionPath + "").get(key);
                    Assert.assertNull(vv);
                });
            });
            ((HashMap) viewKeys.get()).clear();
        });

        And("^I delete data from \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String regionPath, String keyFieldsStr, DataTable table) -> {
            removeData(table.raw(), regionPath, keyFieldsStr.split(","));
        });

        And("^I check data on region \"([^\"]*)\" by fields \"([^\"]*)\" using Fuzzy query$", (String regionPath, String keyFieldsStr, DataTable table) -> {
            checkDataByFuzzyQuery(table.raw(), regionPath, keyFieldsStr.split(","));
        });

        And("^I check data count on region \"([^\"]*)\" by fields \"([^\"]*)\" using Fuzzy query$", (String regionPath, String keyFieldsStr, DataTable table) -> {
            checkDataCountByFuzzyQuery(table.raw(), regionPath, keyFieldsStr.split(","));
        });

        And("^I delete data on region \"([^\"]*)\" by fields \"([^\"]*)\" using Fuzzy query$", (String regionPath, String keyFieldsStr, DataTable table) -> {
            deleteDataByFuzzyQuery(table.raw(), regionPath, keyFieldsStr.split(","));
        });

        And("^I check region \"([^\"]*)\" by keyFields \"([^\"]*)\",field \"([^\"]*)\" from \"([^\"]*)\"$", (String regionPath, String keyFields, String dynamicKeyField, String cacheKey, DataTable table) -> {
            checkDataByDynamicField(table.raw(), keyFields.split(","), regionPath, dynamicKeyField, cacheKey);
        });

        And("^I check region with no data \"([^\"]*)\" by keyFields \"([^\"]*)\",field \"([^\"]*)\" from \"([^\"]*)\"$", (String regionPath, String keyFields, String dynamicKeyField, String cacheKey, DataTable table) -> {
            checkDataByDynamicField(table.raw(), keyFields.split(","), regionPath, dynamicKeyField, cacheKey);
        });

        Given("^Rest set up$", () -> {
            setUp();
        });

        When("^I invoke \"([^\"]*)\" with method \"([^\"]*)\" and request body (.*)$", (String apiUrl, String methodStr, String requestBody) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByBody(url, requestBody, methodStr, true);
        });

        When("^I invoke \"([^\"]*)\" with method \"([^\"]*)\"$", (String apiUrl, String methodStr) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByBody(url, (String) null, methodStr, true);
        });

        When("^I invoke \"([^\"]*)\" with method \"([^\"]*)\" and request data$", (String apiUrl, String methodStr, DataTable table) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByData(url, table, methodStr, true);
        });

        When("^I invoke \"([^\"]*)\" with method \"([^\"]*)\" and request paramData (.*)$", (String apiUrl, String methodStr, String paramData) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByParamData(url, paramData, methodStr, true);
        });

        When("^I basic invoke \"([^\"]*)\" with method \"([^\"]*)\" and request body (.*)$", (String apiUrl, String methodStr, String requestBody) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByBody(url, requestBody, methodStr, false);
        });

        When("^I basic invoke \"([^\"]*)\" with method \"([^\"]*)\"$", (String apiUrl, String methodStr) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByBody(url, (String) null, methodStr, false);
        });

        When("^I basic invoke \"([^\"]*)\" with method \"([^\"]*)\" and request data$", (String apiUrl, String methodStr, DataTable table) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByData(url, table, methodStr, false);
        });

        When("^I basic invoke \"([^\"]*)\" with method \"([^\"]*)\" and request paramData (.*)$", (String apiUrl, String methodStr, String paramData) -> {
            String url = baseUrl + apiUrl;
            RestUtil.requestByParamData(url, paramData, methodStr, false);
        });

        Then("^I check the response with response code (\\d+) and response body (.*)$", (Integer expectedresponseCode, String expectedResponseBody) -> {
            RestUtil.checkResponse(expectedresponseCode, expectedResponseBody);
        });

        And("^I hold the \"([^\"]*)\" from response$", (String key) -> {
            ResponseEntity<String> response = RestUtil.getResponse();
            Assert.assertNotNull(response);
            JsonObject jo = JsonObject.append((String) response.getBody());
            Map<String, Object> map = jo.toMap();
            Object o = map.get(key);
            Assert.assertNotNull(o);
            if (o instanceof ArrayList) {
                String holdValue = (String) ((ArrayList) o).get(0);
                holdValue(key, holdValue);
            } else {
                if (!(o instanceof String)) {
                    throw new RuntimeException("UnSupport type:" + o.getClass());
                }

                holdValue(key, o);
            }

            System.out.println("hold key:" + key + ",Value:" + o);
        });

        Then("^the entry size and index size should all be \"([^\"]*)\" in the region \"([^\"]*)\"$", (String dataSize, String regionName) -> {
            assertDataSize(dataSize, regionName);
        });
    }
}

package com.jnj.pangea.common;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.ADFService;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.PageResults;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.config.annotations.EnableADF;
import com.jnj.adf.grid.client.api.support.ADFServiceContext;
import com.jnj.adf.grid.client.api.support.ADFServiceContext.Keys;
import com.jnj.adf.grid.data.ReservedFields;
import com.jnj.adf.grid.indexer.lucene.internal.distributed.LuceneParam;
import com.jnj.adf.grid.query.impl.LuceneQueryService;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.utils.SpringBeanUtils;
import com.jnj.pangea.common.utils.ExcelData;
import com.jnj.pangea.common.utils.RegionData;
import com.jnj.pangea.common.utils.ResolveData;
import com.jnj.pangea.common.utils.Utils;
import com.jnj.pangea.sentence.PangeaSteps;
import cucumber.api.java8.En;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

public class TestBase implements En {

    public static String regionAlias = "";
    public static Properties regionAliasProperties = null;

    public static final String TEST_DATA_FLAG = "_test_reservation_";
    public static final String TOKEN = "token";
    public static final String IMPORT_DATA = "import_data";
    public static final String DELETE_DATA = "delete_data";
    public static final String CHECK_DATA_EXIST = "check_data_exist";
    public static final String CHECK_DATA_EXIST_FOR_UI = "check_data_exist_FOR_UI";
    public static final String CHECK_DATA_NO_EXIST = "check_data_no_exist";
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected StringBuffer verificationErrors = new StringBuffer();
    protected static ADFService adfService;
    protected static String baseUrl;
    public static String USERNAME = "admin";
    public static String PASSWORD = "MabvadTOiR0";
    private static String tokenId = "";
    private RegionData regionData;
    public static String TEST_BASEURL = "";
    public static String TEST_USERNAME = "";
    public static String TEST_PASSWORD = "";
    public static String TEST_GRID_NAME = "";
    public static String TEST_CTRL_NAME = "";
    protected static ThreadLocal<Map<String, Set<String>>> keys = new ThreadLocal();
    protected static ThreadLocal<HashMap<String, Set<String>>> viewKeys = new ThreadLocal();
    protected static ThreadLocal<Map<String, Object>> cache = new ThreadLocal();

    public TestBase() {
    }

    public RegionData getRegionData() {
        return this.regionData;
    }

    public void setRegionData(RegionData regionData) {
        this.regionData = regionData;
    }

    public void setUp() {
    }

    public static String getRealRegionPath(String region) {

        String regionName = regionAliasProperties.getProperty(region);
        Assert.assertNotNull("region alias not defined! " + region, regionName);
        return regionName;
    }

    public void doExcelData(String excelPath, String[] sheetName, String operationFlg) {
        List<ExcelData> excelDataList = ResolveData.readExcel(excelPath);
        List<RegionData> regions = null;
        List<List<String>> columnAndDataList = null;
        String regionPath = "";
        String[] keyFields = null;
        if (excelDataList != null && excelDataList.size() != 0) {
            Iterator var9 = excelDataList.iterator();

            while (true) {
                do {
                    ExcelData excelData;
                    do {
                        do {
                            if (!var9.hasNext()) {
                                return;
                            }

                            excelData = (ExcelData) var9.next();
                            regions = excelData.getRegionList();
                        } while (!this.isSpecificSheet(sheetName, excelData.getSheetName()));
                    } while (regions == null);
                } while (regions.size() == 0);

                Set<String> set = null;
                Iterator var12 = regions.iterator();

                while (var12.hasNext()) {
                    RegionData region = (RegionData) var12.next();
                    columnAndDataList = region.getValues();
                    this.getRegionData(columnAndDataList);
                    columnAndDataList.add(0, region.getFields());
                    regionPath = region.getPath();
                    keyFields = new String[region.getKeyField().size()];
                    region.getKeyField().toArray(keyFields);
                    if ("import_data".equals(operationFlg)) {
                        set = this.importData(columnAndDataList, regionPath, keyFields);
                        Utils.waitAqComplete(region.getPath());
                        ((Map) keys.get()).put(regionPath, set);
                    } else if ("check_data_exist".equals(operationFlg)) {
                        this.checkData(columnAndDataList, keyFields, regionPath);
                        Utils.waitAqComplete(region.getPath());
                    } else if ("check_data_no_exist".equals(operationFlg)) {
                        this.checkNoKeys(columnAndDataList, keyFields, regionPath);
                    } else if ("delete_data".equals(operationFlg)) {
                        this.removeData(columnAndDataList, regionPath, keyFields);
                    } else if ("check_data_exist_FOR_UI".equals(operationFlg)) {
                        TestBase testBase = new TestBase();
                        this.checkDataForUi(columnAndDataList, keyFields, testBase.getRegionData());
                    }
                }
            }
        }
    }

    public void getRegionData(List<List<String>> values) {
        if (null != values) {
            for (int i = 0; i < values.size(); ++i) {
                ((List) values.get(i)).remove(0);
            }

        }
    }

    public boolean isSpecificSheet(String[] specSheetName, String sheetName) {
        if (null != specSheetName && specSheetName.length != 0) {
            for (int i = 0; i < specSheetName.length; ++i) {
                if (sheetName.equalsIgnoreCase(specSheetName[i])) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public Set<String> importData(List<List<String>> list, String regionPath, String[] keyFields) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        map.forEach((k, v) -> {
            JsonObject o = JsonObject.append(v).append("_test_reservation_", "1");
            map.put(k, o.toJson());
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            if (null != value) {
                JsonObject jo = JsonObject.append(value);
                if ("1".equals(jo.getValue("_test_reservation_"))) {
                    LogUtil.getAppLog().info("delete old data,key:{}", new Object[]{k});
                    adfService.onPath(getRealRegionPath(regionPath)).remove(k);
                } else {
                    Assert.assertNull(value);
                }
            }

        });
        adfService.onPath(getRealRegionPath(regionPath)).putAll(map);
        return map.keySet();
    }

    public void removeData(List<List<String>> list, String regionPath, String[] keyFields) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        removeAll(map.keySet(), getRealRegionPath(regionPath));
    }

    public static void removeAll(Set<String> keySet, String regionPath) {
        Assert.assertNotNull(keySet);
        Iterator var2 = keySet.iterator();

        while (var2.hasNext()) {
            String key = (String) var2.next();
            adfService.onPath(getRealRegionPath(regionPath)).remove(key);
        }

    }

    public Set<String> checkData(List<List<String>> list, String[] keyFields, String regionPath) {
        ((HashMap) viewKeys.get()).clear();
        Assert.assertEquals(list.size() - 1, adfService.onPath(getRealRegionPath(regionPath)).count()); //-1 for headers
        Map<String, String> map = Utils.parseList(list, keyFields);
        map.forEach((k, source) -> {
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            System.out.println("----------------------------------------------");
            long count = adfService.onPath(getRealRegionPath(regionPath)).count();
            System.out.println("region:" + getRealRegionPath(regionPath) + ",count:" + count);
            System.out.println("expect:" + source);
            System.out.println("actual:key:" + k + "value:" + value);
            System.out.println("----------------------------------------------");
            this.assertEqueals(source, value);
        });
        Set<String> viewKeys1 = map.keySet();
        ((HashMap) viewKeys.get()).put(getRealRegionPath(regionPath), viewKeys1);
        return viewKeys1;
    }

    public void checkDataForUi(List<List<String>> expectedDataList, String[] keyFields, RegionData regionDataForUi) {
        Map<String, String> expectedDataMap = Utils.parseList(expectedDataList, keyFields);
        List<List<String>> actualColumnAndDataList = this.regionData.getValues();
        actualColumnAndDataList.add(0, this.regionData.getFields());
        Map<String, String> actualDataMap = Utils.parseList(actualColumnAndDataList, keyFields);
        expectedDataMap.forEach((expectedKey, expectedValue) -> {
            actualDataMap.forEach((actualKey, actualValue) -> {
                if (expectedKey.equals(actualKey)) {
                    System.out.println("----------------------------------------------");
                    System.out.println("regionPath :" + this.regionData.getPath());
                    System.out.println("expect:" + expectedValue);
                    System.out.println("actualKey:" + actualKey);
                    System.out.println("actualValue:" + actualValue);
                    System.out.println("----------------------------------------------");
                    this.assertEqueals(expectedValue, actualValue);
                }

            });
        });
    }

    public Set<String> checkDataWithSeperator(List<List<String>> list, String[] keyFields, String regionPath, String seperator) {
        Map<String, String> map = Utils.parseListNoKeyInValue(list, keyFields);
        map.forEach((k, source) -> {
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            System.out.println("----------------------------------------------");
            System.out.println("expect:" + source);
            System.out.println("actual:key:" + k + "value:" + value);
            System.out.println("----------------------------------------------");
            this.assertEquealsWithSeperator(source, value, seperator);
        });
        return map.keySet();
    }

    public Set<String> checkDataByDynamicField(List<List<String>> list, String[] keyFields, String regionPath, String dynamicKeyField, String cacheKey) {
        String holdValue = (String) ((Map) cache.get()).get(cacheKey);
        Map<String, String> map = Utils.parseList(list, keyFields, dynamicKeyField, holdValue);
        map.forEach((k, source) -> {
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            System.out.println("----------------------------------------------");
            long count = adfService.onPath(getRealRegionPath(regionPath)).count();
            System.out.println("region:" + getRealRegionPath(regionPath) + ",count:" + count);
            System.out.println("expect:" + source);
            System.out.println("actual:key:" + k + "value:" + value);
            System.out.println("----------------------------------------------");
            this.assertEqueals(source, value);
        });
        return map.keySet();
    }

    public Set<String> checkNoKeysByDynamicField(List<List<String>> list, String[] keyFields, String regionPath, String dynamicKeyField, String cacheKey) {
        String holdValue = (String) ((Map) cache.get()).get(cacheKey);
        Map<String, String> map = Utils.parseList(list, keyFields, dynamicKeyField, holdValue);
        map.forEach((k, source) -> {
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            Assert.assertNull(value);
        });
        return map.keySet();
    }

    public void checkDataByFuzzyQuery(List<List<String>> list, String regionPath, String[] keyFields) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            String keyStr = (String) it.next();
            JsonObject keyJo = JsonObject.append(keyStr);
            ADFCriteria criteria = QueryHelper.buildCriteria();
            String[] var9 = keyFields;
            int var10 = keyFields.length;

            for (int var11 = 0; var11 < var10; ++var11) {
                String key = var9[var11];
                criteria.and(key).is(keyJo.getValue(key));
            }

            String queryString = criteria.toQueryString();
            List<String> qr = adfService.onPath(getRealRegionPath(regionPath)).queryForList(queryString);
            LogUtil.getAppLog().info("Query: {}", new Object[]{queryString});
            Assert.assertNotNull(qr);
            JsonObject expectedJson = JsonObject.append((String) map.get(keyStr));
            Iterator var20 = qr.iterator();

            while (var20.hasNext()) {
                String qrJsonStr = (String) var20.next();
                JsonObject actualJson = JsonObject.append(qrJsonStr);
                Iterator var15 = expectedJson.toMap().entrySet().iterator();

                while (var15.hasNext()) {
                    Entry<String, Object> entry = (Entry) var15.next();
                    if (StringUtils.isEmpty((String) entry.getValue())) {
                        Assert.assertTrue(StringUtils.isEmpty((String) actualJson.getValue((String) entry.getKey(), Object.class)));
                    } else {
                        Assert.assertEquals(entry.getValue(), actualJson.getValue((String) entry.getKey()));
                    }
                }
            }
        }

    }

    public void checkDataCountByFuzzyQuery(List<List<String>> list, String regionPath, String[] keyFields) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            String keyStr = (String) it.next();
            JsonObject keyJo = JsonObject.append(keyStr);
            ADFCriteria criteria = QueryHelper.buildCriteria();
            String[] var9 = keyFields;
            int var10 = keyFields.length;

            for (int var11 = 0; var11 < var10; ++var11) {
                String key = var9[var11];
                criteria.and(key).is(keyJo.getValue(key));
            }

            PageResults<String> pr = adfService.onPath(getRealRegionPath(regionPath)).queryPage(criteria.toQueryString());
            LogUtil.getAppLog().info("Query:{}", new Object[]{criteria.toQueryString()});
            Assert.assertNotNull(pr);
            JsonObject value = JsonObject.append((String) map.get(keyStr));
            Assert.assertEquals(value.getValue("count"), (new Long(pr.getTotalCount())).toString());
        }

    }

    public void deleteDataByFuzzyQuery(List<List<String>> list, String regionPath, String[] keyFields) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            String keyStr = (String) it.next();
            JsonObject keyJo = JsonObject.append(keyStr);
            ADFCriteria criteria = QueryHelper.buildCriteria();
            String[] var9 = keyFields;
            int var10 = keyFields.length;

            String query;
            for (int var11 = 0; var11 < var10; ++var11) {
                query = var9[var11];
                criteria.and(query).is(keyJo.getValue(query));
            }

            int pageNo = 1;
            int pageSize = 50;
            PageResults<String> pr = null;
            query = criteria.toQueryString();
            HashSet cacheKeys = new HashSet();

            Iterator var14;
            do {
                pr = adfService.onPath(getRealRegionPath(regionPath)).queryPage(query, pageNo, pageSize);
                LogUtil.getAppLog().info("do query:{},pageNo:{},pageSize:{}", new Object[]{query, Integer.valueOf(pageNo), Integer.valueOf(pageSize)});
                var14 = pr.getEntries().iterator();

                while (var14.hasNext()) {
                    Entry<String, String> ent = (Entry) var14.next();
                    String key = (String) ent.getKey();
                    String value = (String) ent.getValue();
                    if (cacheKeys.contains(key)) {
                        LogUtil.getAppLog().error("promise never happen,do query:{},pageNo:{},pageSize:{}", new Object[]{query, Integer.valueOf(pageNo), Integer.valueOf(pageSize)});
                        break;
                    }

                    cacheKeys.add(key);
                    LogUtil.getAppLog().info("prepared to delete test data,key:{},value:{}", new Object[]{key, value});
                }
            } while (!pr.lastPage());

            var14 = cacheKeys.iterator();

            while (var14.hasNext()) {
                String key = (String) var14.next();
                adfService.onPath(getRealRegionPath(regionPath)).remove(key);
                LogUtil.getAppLog().info("delete test data,key:{}", new Object[]{key});
            }
        }

    }

    public Set<String> checkNoKeys(List<List<String>> list, String[] keyFields, String regionPath) {
        Map<String, String> map = Utils.parseList(list, keyFields);
        map.forEach((k, source) -> {
            String value = adfService.onPath(getRealRegionPath(regionPath)).get(k);
            Assert.assertNull(value);
        });
        return map.keySet();
    }

    private void assertEqueals(String source, String value) {
        JsonObject from = JsonObject.append(source);
        JsonObject to = JsonObject.append(value);
        from.toMap().forEach((k, v) -> {
            Assert.assertEquals(v, to.getValue(k, Object.class) == null ? "" : to.getValue(k));
        });
    }

    private void assertEquealsWithSeperator(String source, String value, String seperator) {
        JsonObject from = JsonObject.append(source);
        JsonObject to = JsonObject.append(value);
        from.toMap().forEach((k, v) -> {
            String[] fromArr = StringUtils.split((String) v, seperator);
            String toStr = StringUtils.trim(to.getValue(k, Object.class) == null ? "" : to.getValue(k));
            String[] toArr = StringUtils.split(toStr, seperator);
            Set<String> fromSet = new HashSet();
            Set<String> toSet = new HashSet();
            String[] var9 = fromArr;
            int var10 = fromArr.length;

            int var11;
            String s;
            for (var11 = 0; var11 < var10; ++var11) {
                s = var9[var11];
                fromSet.add(StringUtils.trim(s));
            }

            var9 = toArr;
            var10 = toArr.length;

            for (var11 = 0; var11 < var10; ++var11) {
                s = var9[var11];
                toSet.add(StringUtils.trim(s));
            }

            Iterator it = fromSet.iterator();

            while (it.hasNext()) {
                String fromValue = StringUtils.trim((String) it.next());
                if (!toSet.contains(fromValue)) {
                    Assert.assertEquals(fromValue, (Object) null);
                }
            }

        });
    }

    protected void holdValue(String key, Object v) {
        ((Map) cache.get()).put(key, v);
    }

    protected Object getHoldValue(String key) {
        return ((Map) cache.get()).get(key);
    }

    protected String readFile(String file) {
        InputStream expectedJson = this.getClass().getResourceAsStream(file);
        String str = "";

        try {
            str = IOUtils.toString(expectedJson, "UTF-8");
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return str;
    }

    public static String getTokenId() {
        return tokenId;
    }

    public static void setTokenId(String strTokenId) {
        tokenId = strTokenId;
    }

    public void assertDataSize(String dataSize, String regionName) {
        String indexSize = "0";
        String entrySize = "0";
        LuceneQueryService service = new LuceneQueryService();
        LuceneParam queryParam = new LuceneParam("*:*", QueryHelper.buildCriteria(ReservedFields.DELETED_FLAG.fieldName()).is(false).toQueryString(), (String) null);
        queryParam.setPageNo(1);
        queryParam.setPageSize(1);
        ADFServiceContext.setValue(Keys.PATH, getRealRegionPath(regionName));
        Long totalCount = service.count(queryParam);
        if (null != totalCount) {
            indexSize = totalCount.toString();
        }

        List<Object> list = adfService.onPath(getRealRegionPath(regionName)).queryOql(" 1=1 ");
        if (null != list) {
            entrySize = String.valueOf(list.size());
        }

        Assert.assertEquals(dataSize, entrySize);
        Assert.assertEquals(dataSize, indexSize);
    }

    static {
        keys.set(new HashMap());
        viewKeys.set(new HashMap());
        cache.set(new HashMap());
        ApplicationContext applicationContext = SpringBeanUtils.initContext(TestBase.ADFConfig.class);
        adfService = (ADFService) applicationContext.getBean(ADFService.class);
        baseUrl = ADFConfigHelper.getProperty("restUrl");
        USERNAME = ADFConfigHelper.getProperty("login.name");
        PASSWORD = ADFConfigHelper.getProperty("login.password");
        String gridName = ADFConfigHelper.getProperty("gridName");
        TEST_USERNAME = ADFConfigHelper.getProperty("ui.login.name");
        TEST_PASSWORD = ADFConfigHelper.getProperty("ui.login.password");
        TEST_GRID_NAME = ADFConfigHelper.getProperty("ui.gridName");
        TEST_BASEURL = ADFConfigHelper.getProperty("ui.baseUrl");
        TEST_CTRL_NAME = ADFConfigHelper.getProperty("ui.controlName");
        adfService.login(USERNAME, PASSWORD);
        regionAlias = ADFConfigHelper.getProperty("regionAlias");
        loadAlias();
    }

    private static void loadAlias() {
        if (StringUtils.isNotEmpty(regionAlias) && null == regionAliasProperties) {
            try {
                regionAliasProperties = new Properties();
                regionAliasProperties.load(TestBase.class.getClassLoader().getResourceAsStream(regionAlias));
                System.out.println("load region alias:");
                System.out.println("-----------------------------------------------------");
                System.out.println(regionAliasProperties);
                System.out.println("-----------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @EnableADF
    protected static class ADFConfig {
        protected ADFConfig() {
        }
    }
}

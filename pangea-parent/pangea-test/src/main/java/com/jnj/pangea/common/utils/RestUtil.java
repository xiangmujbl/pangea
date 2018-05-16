package com.jnj.pangea.common.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.pangea.common.TestBase;
import cucumber.api.DataTable;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class RestUtil {

    public static RestTemplate restTemplate = new RestTemplate();
    public static ThreadLocal<ResponseEntity<String>> localresponse = new ThreadLocal();
    private static final Map<String, HttpMethod> mappings;

    public RestUtil() {
    }

    public static void requestByBody(String url, String body, String methodStr, boolean is_directly) {
        HttpMethod httpMethod = resolve(methodStr.toUpperCase());
        if (is_directly) {
            directRequest(url, body, httpMethod);
        } else {
            doRequest(url, TestBase.USERNAME, TestBase.PASSWORD, body, httpMethod, (Map) null, (Map) null, (Map) null);
        }

    }

    public static void requestByData(String url, DataTable table, String methodStr, boolean is_directly) {
        HttpMethod httpMethod = resolve(methodStr.toUpperCase());
        List<List<String>> rawList = table.raw();
        String requestBody = null;
        Map<String, String> uriParamMap = null;
        Map<String, String> queryParamMap = null;
        Map<String, String> bodyParamMap = null;
        if (rawList.size() > 0) {
            if (((List) rawList.get(0)).size() == 2) {
                if (HttpMethod.GET == httpMethod) {
                    queryParamMap = table.asMap(String.class, String.class);
                } else {
                    bodyParamMap = table.asMap(String.class, String.class);
                }
            } else if (((List) rawList.get(0)).size() == 3) {
                Iterator var10 = rawList.iterator();

                while (var10.hasNext()) {
                    List<String> row = (List) var10.next();
                    if ("uri".equals(row.get(0))) {
                        uriParamMap = uriParamMap == null ? new HashMap() : uriParamMap;
                        uriParamMap.put(row.get(1), row.get(2));
                    } else if ("query".equals(row.get(0))) {
                        queryParamMap = queryParamMap == null ? new HashMap() : queryParamMap;
                        ((Map) queryParamMap).put(row.get(1), row.get(2));
                    } else if ("body".equals(row.get(0))) {
                        bodyParamMap = bodyParamMap == null ? new HashMap() : bodyParamMap;
                        ((Map) bodyParamMap).put(row.get(1), row.get(2));
                    }
                }
            }
        }

        if (is_directly) {
            directRequest(url, (String) requestBody, httpMethod);
        } else {
            doRequest(url, TestBase.USERNAME, TestBase.PASSWORD, (String) requestBody, httpMethod, uriParamMap, (Map) bodyParamMap, (Map) queryParamMap);
        }

    }

    public static void requestByParamData(String url, String paramData, String methodStr, boolean is_directly) {
        HttpMethod httpMethod = resolve(methodStr.toUpperCase());
        ObjectMapper paramDataMapper = new ObjectMapper();
        String requestBody = null;
        Map<String, String> uriParamMap = null;
        Map<String, String> queryParamMap = null;
        Map<String, String> bodyParamMap = null;
        String paramTypeKey = "paramType";

        try {
            List<LinkedHashMap<String, String>> paramDataList = (List) paramDataMapper.readValue(paramData, List.class);
            if (paramDataList.size() > 0) {
                for (int i = 0; i < paramDataList.size(); ++i) {
                    Map<String, String> map = (Map) paramDataList.get(i);
                    if (map.size() == 1) {
                        if (HttpMethod.GET == httpMethod) {
                            queryParamMap = queryParamMap == null ? new HashMap() : queryParamMap;
                            queryParamMap.putAll(map);
                        } else {
                            bodyParamMap = bodyParamMap == null ? new HashMap() : bodyParamMap;
                            bodyParamMap.putAll(map);
                        }
                    } else {
                        String paramTypeValue = (String) map.get(paramTypeKey);
                        map.remove(paramTypeKey);
                        if ("uri".equalsIgnoreCase(paramTypeValue)) {
                            uriParamMap = uriParamMap == null ? new HashMap() : uriParamMap;
                            uriParamMap.putAll(map);
                        } else if ("query".equalsIgnoreCase(paramTypeValue)) {
                            queryParamMap = queryParamMap == null ? new HashMap() : queryParamMap;
                            queryParamMap.putAll(map);
                        } else if ("body".equalsIgnoreCase(paramTypeValue)) {
                            bodyParamMap = bodyParamMap == null ? new HashMap() : bodyParamMap;
                            bodyParamMap.putAll(map);
                        }
                    }
                }
            }
        } catch (JsonParseException var15) {
            var15.printStackTrace();
        } catch (JsonMappingException var16) {
            var16.printStackTrace();
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        if (is_directly) {
            queryParamMap = queryParamMap == null ? new HashMap() : queryParamMap;
            queryParamMap.put("token", TestBase.getTokenId());
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            Iterator var19 = queryParamMap.entrySet().iterator();

            while (var19.hasNext()) {
                Entry<String, String> entry = (Entry) var19.next();
                builder.queryParam((String) entry.getKey(), new Object[]{entry.getValue()});
            }

            if (uriParamMap != null) {
                url = builder.buildAndExpand(uriParamMap).toUriString();
            } else {
                url = builder.toUriString();
            }

            directRequest(url, (String) requestBody, httpMethod);
        } else {
            doRequest(url, TestBase.USERNAME, TestBase.PASSWORD, (String) requestBody, httpMethod, uriParamMap, bodyParamMap, queryParamMap);
        }

    }

    public static ResponseEntity<String> directRequest(String url, String body, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request, String.class, new Object[0]);
        localresponse.set(response);
        System.out.println("response:" + response);
        return response;
    }

    public static void checkResponse(Integer expectedReponseCode, String expectedResponseBody) {
        HttpStatus expectedHttpStatus = HttpStatus.valueOf(expectedReponseCode.intValue());
        if (!HttpStatus.INTERNAL_SERVER_ERROR.equals(expectedHttpStatus)) {
            HttpStatus httpStatus = ((ResponseEntity) localresponse.get()).getStatusCode();
            Assert.assertEquals(expectedHttpStatus, httpStatus);
            JsonObject expectedJson = JsonObject.append(expectedResponseBody);
            JsonObject responseJson = JsonObject.append((String) ((ResponseEntity) localresponse.get()).getBody());
            Map<String, Object> expectedMap = expectedJson.toMap();
            Map<String, Object> responseMap = responseJson.toMap();
            compareMap(expectedMap, responseMap);
        }
    }

    public static ResponseEntity<String> getResponse() {
        return (ResponseEntity) localresponse.get();
    }

    private static ResponseEntity<String> doRequest(String url, String username, String password, String body, HttpMethod httpMethod, Map<String, String> uriParams, Map<String, String> bodyParams, Map<String, String> queryParams) {
        try {
            password = Utils.decrypt(password);
            uriParams = uriParams == null ? new HashMap() : uriParams;
            queryParams = queryParams == null ? new HashMap() : queryParams;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            Iterator var19 = ((Map) queryParams).entrySet().iterator();

            while (var19.hasNext()) {
                Entry<String, String> entry = (Entry) var19.next();
                builder.queryParam((String) entry.getKey(), new Object[]{entry.getValue()});
            }

            url = builder.buildAndExpand((Map) uriParams).toUriString();
            String plainCreds = username + ":" + password;
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            headers.add("Content-Type", "application/json");
            if (StringUtils.isEmpty(body)) {
                if (bodyParams != null) {
                    bodyParams.put("token", TestBase.getTokenId());
                    body = parseMapToParams(bodyParams);
                } else {
                    body = "";
                }
            }

            HttpEntity<String> request = new HttpEntity(body, headers);
            System.out.println("url" + url + ",request:" + request);
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request, String.class, new Object[0]);
            localresponse.set(response);
            System.out.println("response:" + response);
            return response;
        } catch (Exception var16) {
            var16.printStackTrace();
            ResponseEntity<String> responseEx = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEx;
        }
    }

    private static void compareMap(Map<String, Object> expectedMap, Map<String, Object> responseMap) {
        Iterator var2 = expectedMap.entrySet().iterator();

        while (var2.hasNext()) {
            Entry<String, Object> entry = (Entry) var2.next();
            String key = (String) entry.getKey();
            Object expectVal = entry.getValue();
            Object responseValue = responseMap.get(key);
            if (expectVal instanceof Map) {
                Assert.assertTrue(responseValue instanceof Map);
                compareMap((Map) expectVal, (Map) responseValue);
            } else if (expectVal instanceof ArrayList) {
                ArrayList expectList = (ArrayList) expectVal;
                int i = 0;
                if (i < expectList.size()) {
                    Object o = expectList.get(i);
                    if (o instanceof LinkedHashMap) {
                        checkArrayListLinkHashMap(expectVal, responseValue);
                    } else if (o instanceof String) {
                        checkArrayListString(expectVal, responseValue);
                    }
                }
            } else {
                Assert.assertEquals(expectVal, responseMap.get(key));
            }
        }

    }

    private static void checkArrayListString(Object expectVal, Object responseValue) {
        ArrayList<String> expectList = (ArrayList) expectVal;
        ArrayList<String> actualList = (ArrayList) responseValue;
        Assert.assertEquals((long) expectList.size(), (long) actualList.size());
        String[] expArr = new String[expectList.size()];
        String[] actualArr = new String[actualList.size()];
        expectList.toArray(expArr);
        actualList.toArray(actualArr);

        for (int i = 0; i < expArr.length; ++i) {
            Assert.assertEquals(expArr[i], actualArr[i]);
        }

    }

    private static void checkArrayListLinkHashMap(Object expectVal, Object responseValue) {
        ArrayList<LinkedHashMap> expectList = (ArrayList) expectVal;
        ArrayList<LinkedHashMap> actualList = (ArrayList) responseValue;
        HashSet<Integer> hasCompared = new HashSet();

        for (int i = 0; i < expectList.size(); ++i) {
            LinkedHashMap elp = (LinkedHashMap) expectList.get(i);
            boolean match = false;

            for (int j = 0; j < actualList.size(); ++j) {
                if (!hasCompared.contains(new Integer(j))) {
                    LinkedHashMap alp = (LinkedHashMap) actualList.get(j);

                    for (Iterator var10 = elp.keySet().iterator(); var10.hasNext(); match = true) {
                        Object k = var10.next();
                        if (!Objects.equals(alp.get(k), elp.get(k))) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        hasCompared.add(new Integer(j));
                        break;
                    }
                }
            }

            if (!match) {
                Assert.assertEquals(elp, (Object) null);
                break;
            }
        }

    }

    private static String parseMapToParams(Map<String, String> paramMap) {
        JsonObject json = JsonObject.create();
        paramMap.forEach((k, v) -> {
            json.append(k, v);
        });
        return json.toJson();
    }

    public static HttpMethod resolve(String method) {
        return method != null ? (HttpMethod) mappings.get(method) : null;
    }

    static {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
        mappings = new HashMap(8);
        HttpMethod[] var4 = HttpMethod.values();
        int var5 = var4.length;

        for (int var2 = 0; var2 < var5; ++var2) {
            HttpMethod httpMethod = var4[var2];
            mappings.put(httpMethod.name(), httpMethod);
        }

    }

    class SortByField implements Comparator<LinkedHashMap<?, ?>> {
        private String sortField;

        public SortByField(String sortField) {
            this.sortField = sortField;
        }

        public int compare(LinkedHashMap l1, LinkedHashMap l2) {
            Object o1 = l1.get(this.sortField);
            Object o2 = l2.get(this.sortField);
            return o1 instanceof Comparable ? ((Comparable) o1).compareTo((Comparable) o2) : o1.toString().compareTo(o2.toString());
        }
    }
}

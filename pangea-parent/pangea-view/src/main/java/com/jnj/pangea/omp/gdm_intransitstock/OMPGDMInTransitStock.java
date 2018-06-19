package  com.jnj.pangea.omp.gdm_intransitstock;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.adf.client.api.query.QueryHelper;
import org.apache.commons.lang3.StringUtils;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.inner.StringInner;
import com.jnj.pangea.common.Utils;
import java.util.*;


@SuppressWarnings("unchecked")
public class OMPGDMInTransitStock implements IEventProcessor {

    private final String FAILREGION = "/plan/edm_failed_data";

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> resultList = new ArrayList<>();

        list.stream().forEach(e -> {
            try {
                RawDataBuilder builder = new RawDataBuilder();

                Map<String, RawDataBuilder> failMap = new HashMap<>();

                List<ViewResultItem> itemList = new ArrayList<>();

                boolean isOk = buildView(e.getValue(), builder, failMap, itemList);
                if (itemList != null && itemList.size() > 0) {
                    resultList.addAll(itemList);
                }

                if (isOk) {
                    Map<String, Object> data = (Map<String, Object>) builder.toRawData();
                    String viewKey = JsonObject.create()
                            .append("customerId", data.get("customerId"))
                            .append("productId", data.get("productId"))
                            .append("quantity", data.get("quantity"))
                            .toJson();

                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(viewKey, data);
                    resultList.add(viewRaw);
                } else {
                    failMap.forEach((key, value) -> {
                        ViewResultItem viewRaw = ViewResultBuilder.newResultItem(FAILREGION, key, (Map<String, Object>) value.toRawData());
                        resultList.add(viewRaw);
                    });
                }
            } catch (Exception exception) {
                LogUtil.getCoreLog().info("OMPGDMInTransitStock Exception occured. key = {}.", e.getKey());
                LogUtil.getCoreLog().info("OMPGDMInTransitStock Exception:", exception);
            }
        });

        return resultList;
    }

    public boolean buildView(RawDataValue raw, RawDataBuilder builder, Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

        Map map = raw.toMap();

        String demandGroup = String.valueOf(map.get("demandGroup"));
        String date = String.valueOf(map.get("date"));
        String localNumerator = null;
        String uom = String.valueOf(map.get("uom"));
        String quantity = String.valueOf(map.get("quantity"));
        String productId = String.valueOf(map.get("productId"));
        String sourceSystem = String.valueOf(map.get("sourceSystem"));
        String localDenominator = null;
        String customerId = null;
        String localDpParentCode = null;
        String localAuom = null;
        String localMaterialNumber = null;


        Map QueryGrpAsgnEntityMap = QueryGrpAsgnEntity(sourceSystem);
        if (QueryGrpAsgnEntityMap != null) {
            customerId = String.valueOf(QueryGrpAsgnEntityMap.get("customerId"));
        }


        if (QueryGrpAsgnEntityMap == null) {


            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "productId do not exist in edm_material", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        }


        builder.put("customerId", demandGroup);


        Map QueryMaterialGlobalMap = QueryMaterialGlobal(sourceSystem, productId);
        if (QueryMaterialGlobalMap != null) {
            localMaterialNumber = String.valueOf(QueryMaterialGlobalMap.get("localMaterialNumber"));
            localDpParentCode = String.valueOf(QueryMaterialGlobalMap.get("localDpParentCode"));
        }


        if (QueryMaterialGlobalMap == null) {

            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "localDpParentCode do not exist in edm material", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        }
        if(localDpParentCode==null){
            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "localDpParentCode do not exist in edm material", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        }
        String localDpParentCode1 = "LA_"+localDpParentCode;
        builder.put("productId", localDpParentCode1);

        String dueDate = null;


        Map QueryAuomEntityMap = QueryAuomEntity(uom, productId);
        if (QueryAuomEntityMap != null) {
            localAuom = String.valueOf(QueryAuomEntityMap.get("localAuom"));
            localDenominator = String.valueOf(QueryAuomEntityMap.get("localDenominator"));
            localNumerator = String.valueOf(QueryAuomEntityMap.get("localNumerator"));
        }


        if (QueryAuomEntityMap == null) {
            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "unit do not exist in edm_Auom", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        } else {


            dueDate = StringInner.join(date, " 00:00:00");
        }
        builder.put("dueDate", dueDate);


        quantity = Utils.QueryCnsIntransitstock(productId, sourceSystem, demandGroup);

        if (StringInner.isStringNotEmpty(quantity) && StringInner.isStringNotEmpty(demandGroup) && StringInner.isStringNotEmpty(productId)) {


            Map QueryGdmInTransitStockMap = QueryGdmInTransitStock(demandGroup, localDpParentCode1, quantity);
            if (QueryGdmInTransitStockMap != null) {
                customerId = String.valueOf(QueryGdmInTransitStockMap.get("customerId"));
                productId = String.valueOf(QueryGdmInTransitStockMap.get("productId"));
                quantity = String.valueOf(QueryGdmInTransitStockMap.get("quantity"));
            }
            if (QueryGdmInTransitStockMap != null) {
                return false;
            }
        }


        builder.put("quantity", quantity);


        return true;
    }


    public Map QueryMaterialGlobal(String sourceSystem, String localMaterialNumber) {

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria9 = adfCriteria2.and(adfCriteria1);

        ADFCriteria adfCriteria = groupCriteria9;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_global_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public Map QueryAuomEntity(String localAuom, String localMaterialNumber) {
        if(StringUtils.isEmpty(localAuom)||StringUtils.isEmpty(localMaterialNumber)){
            return null;
        }
        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localAuom").is(localAuom);
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria10 = adfCriteria4.and(adfCriteria3);

        ADFCriteria adfCriteria = groupCriteria10;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_auom_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public Map QueryGrpAsgnEntity(String sourceSystem) {

        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria11 = adfCriteria5;

        ADFCriteria adfCriteria = groupCriteria11;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/plan/cns_dem_grp_asgn", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public Map QueryGdmInTransitStock(String customerId, String productId, String quantity) {

        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("customerId").is(customerId);
        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("productId").is(productId);
        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("quantity").is(quantity);
        ADFCriteria groupCriteria12 = adfCriteria8.and(adfCriteria7).and(adfCriteria6);
        ADFCriteria adfCriteria = groupCriteria12;
        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("queryStr:"+queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/omp/gdm_in_transit_stock", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            LogUtil.getCoreLog().info("return:"+map.toString());
            return map;
        }
        LogUtil.getCoreLog().info("returnNull:");
        return null;

    }


    private void writeFailDataToRegion(Map<String, RawDataBuilder> failMap, String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2, String key3, String key4, String key5, String businessArea) {
        String keyJson = JsonObject.create()
                .append("functionalArea", functionalArea)
                .append("interfaceID", interfaceID)
                .append("errorCode", errorCode)
                .append("sourceSystem", sourceSystem)
                .append("key1", key1)
                .append("key2", key2)
                .append("key3", key3)
                .toJson();

        RawDataBuilder failBuilder = new RawDataBuilder();
        failBuilder.put("functionalArea", functionalArea);
        failBuilder.put("interfaceID", interfaceID);
        failBuilder.put("errorCode", errorCode);
        failBuilder.put("sourceSystem", sourceSystem);
        failBuilder.put("key1", key1);
        failBuilder.put("key2", key2);
        failBuilder.put("key3", key3);
        failBuilder.put("businessArea", businessArea);
        failBuilder.put("errorValue", errorValue);
        failMap.put(keyJson, failBuilder);
    }

}
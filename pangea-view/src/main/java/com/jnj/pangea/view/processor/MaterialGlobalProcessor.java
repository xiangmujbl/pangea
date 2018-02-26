package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.MaterialGlobalBo;
import com.jnj.pangea.view.common.Utils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialGlobalProcessor extends BaseProcessor implements IEventProcessor {

    private static String REGION_SOURCE_SYSTEM = "/edm/source_system_v1";
    private static String REGION_MAKT = "/project_one/makt";
    private static String REGION_MATERIAL_LINKAGE = "/ngems/material_linkage";
    private static String REGION_GOLDEN_MATERIAL = "/ngems/golden_material";

    private String sourceSystem = "";

    @PostConstruct
    private void initData() {
        String queryString = QueryHelper.buildCriteria("sourceSystem").and("localSourceSystem").is("project_one").toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM, queryString);
        if (null != result) {
            sourceSystem = result.getValue().get("sourceSystem") + "";
        }
    }

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            String key = Utils.generateKey(rawDataMap, "sourceSystem", "localMaterialNumber");

            MaterialGlobalBo materialGlobalBo = new MaterialGlobalBo();

            // rule T1
            materialGlobalBo.setSourceSystem(sourceSystem);

            String matnr = getStringField(rawDataMap, "matnr");
            materialGlobalBo.setLocalMaterialNumber(matnr);

            // rule J1
            materialGlobalBo.setLocalRefDescription(getFieldWithJ1(matnr));

            materialGlobalBo.setLocalMaterialType(getStringField(rawDataMap, "matnr"));
            materialGlobalBo.setBaseUom(getStringField(rawDataMap, "matnr"));

            // rule J2
            Map<String, Object> enrichMap = getFieldWithJ2(matnr);

            materialGlobalBo.setMaterialNumber(getStringField(enrichMap, "materialNumber"));
            materialGlobalBo.setRefDescription(getStringField(enrichMap, "materialDescription"));
            materialGlobalBo.setMaterialType(getStringField(enrichMap, "materialType"));
            materialGlobalBo.setBaseUom(getStringField(enrichMap, "baseUom"));
            materialGlobalBo.setLocalDpParentCode("");
            materialGlobalBo.setParentCode(getStringField(enrichMap, "parentCode"));
            materialGlobalBo.setGlobalDpParentCode("");
            materialGlobalBo.setForm(getStringField(enrichMap, "form"));
            materialGlobalBo.setCategory(getStringField(enrichMap, "category"));
            materialGlobalBo.setSubBrand(getStringField(enrichMap, "subBrand"));
            materialGlobalBo.setBrand(getStringField(enrichMap, "brand"));
            materialGlobalBo.setFranchise(getStringField(enrichMap, "franchise"));
            materialGlobalBo.setGlobalBusinessUnit(getStringField(enrichMap, "globalBusinessUnit"));
            materialGlobalBo.setProductFamily(getStringField(enrichMap, "productFamily"));
            materialGlobalBo.setLocalManufacturingTechnology("");
            materialGlobalBo.setManufacturingTechnology(getStringField(enrichMap, "manufacturingTechnology"));

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(key, materialGlobalBo.toMap());
            result.add(viewRaw);
        });
        return result;
    }

    private String getFieldWithJ1(String matnr) {

        String queryEnString = QueryHelper.buildCriteria("matnr").is(matnr).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(REGION_MAKT, queryEnString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String spras = jsonObj.get("spras") + "";
            String maktx = jsonObj.get("maktx") + "";
            if ("EN".equals(spras)) {
                return maktx;
            } else if ("PT".equals(spras)) {
                return maktx;
            } else if ("ES".equals(spras)) {
                return maktx;
            }
        }
        return "";
    }

    private Map<String, Object> getFieldWithJ2(String matnr) {
        String queryString = QueryHelper
                .buildCriteria("matnr").is(matnr)
                .and("sourceSystem").is(sourceSystem)
                .toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_MATERIAL_LINKAGE, queryString);
        if (null != result.getValue()) {
            String materialNumber = result.getValue().get("materialNumber") + "";
            queryString = QueryHelper.buildCriteria("materialNumber").is(materialNumber).toQueryString();

            result = AdfViewHelper.queryForMap(REGION_GOLDEN_MATERIAL, queryString);
            if (null != result) {
                return result.getValue();
            }
        }
        return null;
    }
}
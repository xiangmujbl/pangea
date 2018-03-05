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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialGlobalProcessor extends BaseProcessor implements IEventProcessor {

    private static String REGION_SOURCE_SYSTEM = "/edm/source_system_v1";
    private static String REGION_MAKT = "/project_one/makt";
    private static String REGION_MATERIAL_LINKAGE = "/ngems/material_linkage";
    private static String REGION_GOLDEN_MATERIAL = "/ngems/golden_material";

    private String sourceSystem = null;

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();

            MaterialGlobalBo materialGlobalBo = new MaterialGlobalBo();

            // rule T1
            if (null == sourceSystem) {
                sourceSystem = getFieldWithT1();
            }
            materialGlobalBo.setSourceSystem(sourceSystem);

            String matnr = getStringField(rawDataMap, "matnr");
            materialGlobalBo.setLocalMaterialNumber(matnr);

            // rule J1
            materialGlobalBo.setLocalRefDescription(getFieldWithJ1(matnr));

            materialGlobalBo.setLocalMaterialType(getStringField(rawDataMap, "mtart"));
            materialGlobalBo.setLocalBaseUnit(getStringField(rawDataMap, "meins"));

            // rule J2
            Map<String, Object> enrichMap = getFieldsWithJ2(matnr);

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
            materialGlobalBo.setManufacturingTechnology(getStringField(enrichMap, "manufTechnology"));

            materialGlobalBo.setLocalMaterialGroup(getStringField(rawDataMap, "matkl"));
            materialGlobalBo.setMaterialGroup(getStringField(rawDataMap, ""));
            materialGlobalBo.setFlagForDeletion(getStringField(rawDataMap, "lvorm"));
            materialGlobalBo.setMaterialStatus(getStringField(rawDataMap, "mstae"));
            materialGlobalBo.setDivision(getStringField(rawDataMap, "spart"));

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(materialGlobalBo.getKey(), materialGlobalBo.toMap());
            result.add(viewRaw);
        });
        return result;
    }

    private String getFieldWithT1() {
        String queryString = QueryHelper.buildCriteria("localSourceSystem").is("project_one").toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM, queryString);
        if (null != result) {
            return result.getValue().get("sourceSystem") + "";
        }
        return "";
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

    private Map<String, Object> getFieldsWithJ2(String matnr) {
        String queryString = QueryHelper
                .buildCriteria("localMaterialNumber").is(matnr)
                .and("sourceSystem").is(sourceSystem)
                .toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_MATERIAL_LINKAGE, queryString);

        if (null != result && null != result.getValue()) {
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
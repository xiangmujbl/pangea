package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.EDMMaterialPlantBo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EDMMaterialPlantProcessor extends BaseProcessor implements IEventProcessor {
    private static String REGION_SOURCE_SYSTEM = "/edm/source_system_v1";
    private static String REGION_MATERIAL_GLOBAL="/edm/material_global_v1";
    private static String REGION_PLANT="/edm/plant_v1";
    private static String REGION_CNS_MAT_PLANT_STAT="/pangea/cns_mat_plant_stat";
    private static String SOURCESYSTEM_PROJECTONE="project_one";

    @PostConstruct
    private void initData() {
    }

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {
            RawDataValue rawValue = raw.getValue();
            Map<String, Object> rawDataMap = rawValue.toMap();
            EDMMaterialPlantBo eaterialPlant= new EDMMaterialPlantBo();
            // get Field "sourceSystem" whith source_system_v1
            String sourceSystem=getSourceSystem();

            String matnr=getStringField(rawDataMap,"matnr");
            String werks=getStringField(rawDataMap,"werks");
            String mmsta=getStringField(rawDataMap,"mmsta");
            LogUtil.getCoreLog().info("=========================matnr:"+matnr+" werks:"+werks);
            eaterialPlant.setSourceSystem(sourceSystem);
            eaterialPlant.setLocalMaterialNumber(matnr);
            eaterialPlant.setLocalPlant(werks);
            eaterialPlant.setLocalPlantStatus(mmsta);
            eaterialPlant.setMaterialNumber(getMaterialNumber(sourceSystem,matnr));
            eaterialPlant.setPlant(getPlant(sourceSystem,werks));
            eaterialPlant.setPlantStatus(getPlantStatus(sourceSystem,mmsta));

            ViewResultItem viewRaw = ViewResultBuilder.newResultItem(eaterialPlant.getKey(), eaterialPlant.toMap());
            result.add(viewRaw);
        });
        return result;
    }

    private String getSourceSystem() {
        String sourceSystem="";
        String queryString = QueryHelper.buildCriteria("localSourceSystem").is(SOURCESYSTEM_PROJECTONE).toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_SOURCE_SYSTEM, queryString);
        if (null != result && null !=result.getValue()) {
            sourceSystem = result.getValue().get("sourceSystem") + "";
        }
        return sourceSystem;
    }

    private String getMaterialNumber(String sourceSystem,String matnr){
        String materialNumber="";
        String queryString = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem)
                .and("localMaterialNumber").is(matnr).toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_MATERIAL_GLOBAL, queryString);
        if (null != result && null !=result.getValue()) {
            materialNumber = result.getValue().get("materialNumber")+"";
        }
        return materialNumber;
    }

    private String getPlant(String sourceSystem,String werks){
        String plant="";
        String queryString = QueryHelper.buildCriteria("localPlant").is(werks)
                .and("sourceSystem").is(sourceSystem).toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_PLANT, queryString);
        if (null != result && null !=result.getValue()) {
            plant = result.getValue().get("plant") + "";
        }
        return plant;
    }

    private String getPlantStatus(String sourceSystem,String mmsta){
        String plantStatus="";
        String queryString = QueryHelper.buildCriteria("localPlantStatus").is(mmsta)
                .and("sourceSystem").is(sourceSystem).toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(REGION_CNS_MAT_PLANT_STAT, queryString);
        if (null != result && null !=result.getValue()) {
            plantStatus = result.getValue().get("plantStatus") + "";
        }
        return plantStatus;
    }

}
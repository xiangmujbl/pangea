package com.jnj.pangea.view.processor;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.CurationCache;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.view.bo.EDMMaterialAUOMProjectoneBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMMaterialAUOMProjectoneProcessor extends BaseProcessor implements IEventProcessor {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        LogUtil.getCoreLog().info("========================start===================");

        CurationCache.dump();

        List<ViewResultItem> result = new ArrayList<>();

        list.forEach(mainRaw -> {
            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();

            LogUtil.getCoreLog().info(">>>>>>>>>>>start>>>>>>>>>key:{}",key);

            EDMMaterialAUOMProjectoneBo EDMMaterialAUOMProjectoneBo = new EDMMaterialAUOMProjectoneBo();

            boolean flag = buildView(key,mainValue,EDMMaterialAUOMProjectoneBo);

            LogUtil.getCoreLog().info(">>>>>>>>>>>result:{}",flag);

            if(flag){
                ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(EDMMaterialAUOMProjectoneBo.getKey(), EDMMaterialAUOMProjectoneBo.toMap());
                result.add(viewResultItem);
            }

        });

        return result;
    }

    private boolean buildView(String key, RawDataValue mainData, EDMMaterialAUOMProjectoneBo EDMMaterialAUOMProjectoneBo) {

        boolean isOk = processSourceSystem(key,EDMMaterialAUOMProjectoneBo);
        if(!isOk){
            LogUtil.getCoreLog().warn(">>>key:{},processSourceSystem of flag:{}",key,isOk);
            return false;
        }

        isOk = processSystem(mainData,EDMMaterialAUOMProjectoneBo);
        if(!isOk){
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}",key,isOk);
            return false;
        }

        isOk = processMaterialNumber(key, mainData, EDMMaterialAUOMProjectoneBo);
        if(!isOk){
           LogUtil.getCoreLog().warn(">>>key:{},processMaterialNumber of flag:{}",key,isOk);
            return false;
        }

        return true;
    }

    private final boolean processMaterialNumber(String key, RawDataValue mainData, EDMMaterialAUOMProjectoneBo EDMMaterialAUOMProjectoneBo){

        String matnr = (String) mainData.getField("matnr");
        String sourceSystem = EDMMaterialAUOMProjectoneBo.getSourceSystem();
        String queryString = QueryHelper.buildCriteria("localMaterialNumber").is(matnr).and("sourceSystem").is(sourceSystem).toQueryString();

        List<Map.Entry<String, String>> materialList = AdfViewHelper.queryForList("/edm/material_global_v1", queryString);

        String materialNumber = null;
        for (Map.Entry<String, String> entry : materialList) {
            String value = entry.getValue();
            Map valueMap = JsonUtils.jsonToObject(value, Map.class);
            materialNumber = StringUtils.trim((String) valueMap.get("materialNumber"));
        }

        if(StringUtils.isEmpty(materialNumber)){
            LogUtil.getCoreLog().info(">>>query /edm/material_global_v1 is null, query condition:{}",queryString);
            //@TODO write fail data to region or file, T1
            return false;
        }

        EDMMaterialAUOMProjectoneBo.setMaterialNumber(materialNumber);

        return true;
    }

    private final boolean processSourceSystem(String key, EDMMaterialAUOMProjectoneBo EDMMaterialAUOMProjectoneBo) {

        String queryString = QueryHelper.buildCriteria("localSourceSystem").is("project_one").toQueryString();

        List<Map.Entry<String, String>> sourceList = AdfViewHelper.queryForList("/edm/source_system_v1", queryString);

        String sourceSystem = null;
        for (Map.Entry<String, String> entry : sourceList) {
            String value = entry.getValue();
            Map valueMap = JsonUtils.jsonToObject(value, Map.class);
            sourceSystem = StringUtils.trim((String) valueMap.get("sourceSystem"));
        }

        if(StringUtils.isEmpty(sourceSystem)){
            LogUtil.getCoreLog().info(">>>query /edm/source_system_v1 data is null for project_one, query condition:{}",queryString);
            //@TODO write fail data to region or file, T1
            return false;
        }

        EDMMaterialAUOMProjectoneBo.setSourceSystem(sourceSystem);

        return true;
    }

    private final boolean processSystem(RawDataValue mainData, EDMMaterialAUOMProjectoneBo EDMMaterialAUOMProjectoneBo) {

        //localMaterialNumber
        String matnr = (String) mainData.getField("matnr");
        EDMMaterialAUOMProjectoneBo.setLocalMaterialNumber(StringUtils.trim(matnr));

        //localAuom
        String localAuom = (String) mainData.getField("meinh");
        EDMMaterialAUOMProjectoneBo.setLocalAuom(StringUtils.trim(localAuom));

        //localNumerator
        String localNumerator = (String) mainData.getField("umrez");
        EDMMaterialAUOMProjectoneBo.setLocalNumerator(StringUtils.trim(localNumerator));

        //localDenominator
        String localDenominator = (String) mainData.getField("umren");
        EDMMaterialAUOMProjectoneBo.setLocalDenominator(StringUtils.trim(localDenominator));

        return true;
    }

}

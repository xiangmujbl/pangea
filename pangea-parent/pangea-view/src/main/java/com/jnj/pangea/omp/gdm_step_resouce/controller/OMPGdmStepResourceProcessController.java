package com.jnj.pangea.omp.gdm_step_resouce.controller;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.omp.gdm_step_resouce.service.OMPGdmStepResourceProcessService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OMPGdmStepResourceProcessController extends BaseController {

    //    private final String FAILREGION = "/qa/plan/edm_failed_data";
    private OMPGdmStepResourceProcessService service = OMPGdmStepResourceProcessService.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> resultList = new ArrayList<>();

        list.stream().forEach(e -> {
            try {
                List<RawDataBuilder> rawDataBuilderList = new ArrayList<>();
                Map<String, RawDataBuilder> failMap = new HashMap<>();
                boolean isOk = service.buildView(e.getValue(), rawDataBuilderList, failMap);
                if (isOk) {
                    rawDataBuilderList.forEach(rawDataBuilder -> {
                        Map<String, Object> data = (Map<String, Object>) rawDataBuilder.toRawData();
                        String viewKey = JsonObject.create().append("stepResourceId",
                                data.get("stepResourceId")).toJson();

                        ViewResultItem viewRaw = ViewResultBuilder.newResultItem(viewKey, data);
                        resultList.add(viewRaw);
                    });

                    failMap.forEach((key, value) -> {
                        ViewResultItem viewRawFail = ViewResultBuilder
                                .newResultItem(new FailData().getFailRegion(), key,
                                        (Map<String, Object>) value.toRawData());
                        resultList.add(viewRawFail);
                    });
                }
            } catch (Exception exception) {
                LogUtil.getCoreLog().info("OMPGdmStepResourceProcessV1Controller Exception occured. key = {}.", e.getKey());
                LogUtil.getCoreLog().info("OMPGdmStepResourceProcessV1Controller Exception:", exception);
            }
        });
        return resultList;
    }

}

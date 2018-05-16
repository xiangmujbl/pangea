package com.jnj.pangea.omp.gdm_unit_evol.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_evol.service.OMPGdmUnitEvolServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OMPGdmUnitEvolController extends BaseController {

    private OMPGdmUnitEvolServiceImpl unitEvolServiceImpl = (OMPGdmUnitEvolServiceImpl) OMPGdmUnitEvolServiceImpl.getInstance();

    private Map<String, Object> extraParam = new HashMap<>();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            List<ResultObject> resultObjectList = unitEvolServiceImpl.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanConsTimeDepXchangeEntity.class), extraParam);
            for (ResultObject resultObject:resultObjectList) {
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    if (null != baseBo) {
                        result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                    }
                } else {
                    if (null != resultObject.getFailData()) {
                        FailData failData = resultObject.getFailData();
                        result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                    }
                }
            }
        });
        return result;
    }
}

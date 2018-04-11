package com.jnj.pangea.edm.planned_order_v1_latam.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.PlafEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.planned_order_v1_latam.service.EDMPlannedOrderV1LatamServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPlannedOrderV1LatamController extends CommonController {

    private ICommonService service = EDMPlannedOrderV1LatamServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlafEntity.class), null);
    }
}

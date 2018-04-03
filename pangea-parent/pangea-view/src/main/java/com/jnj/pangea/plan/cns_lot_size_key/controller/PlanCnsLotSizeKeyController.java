package com.jnj.pangea.plan.cns_lot_size_key.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.T439TEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_lot_size_key.service.PlanCnsLotSizeKeyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsLotSizeKeyController extends CommonController {

    private ICommonService service = PlanCnsLotSizeKeyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T439TEntity.class), null);
    }
}

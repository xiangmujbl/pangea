package com.jnj.pangea.omp.gdm_lot_size_key.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsLotSizeKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_lot_size_key.service.OMPGdmLotSizeKeyServiceImpl;
import com.jnj.pangea.omp.lot_size_key.service.OMPLotSizeKeyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmLotSizeKeyController extends CommonController {

    private ICommonService service = OMPGdmLotSizeKeyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsLotSizeKeyEntity.class), null);
    }
}

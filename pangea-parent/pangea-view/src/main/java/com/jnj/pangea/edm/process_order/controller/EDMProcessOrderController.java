package com.jnj.pangea.edm.process_order.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.AfkoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.process_order.service.EDMProcessOrderServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMProcessOrderController extends CommonController {

    private ICommonService service = EDMProcessOrderServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), AfkoEntity.class), null);
    }
}

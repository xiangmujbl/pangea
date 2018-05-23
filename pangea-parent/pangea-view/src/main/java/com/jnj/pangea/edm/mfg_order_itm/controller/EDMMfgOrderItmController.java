package com.jnj.pangea.edm.mfg_order_itm.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.AfpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_itm.service.EDMMfgOrderItmServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgOrderItmController extends CommonController {

    private ICommonService service = EDMMfgOrderItmServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), AfpoEntity.class), null);
    }
}

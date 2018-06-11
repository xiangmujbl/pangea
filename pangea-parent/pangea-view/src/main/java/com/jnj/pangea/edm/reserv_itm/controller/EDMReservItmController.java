package com.jnj.pangea.edm.reserv_itm.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOneResbEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.reserv_itm.service.EDMReservItmServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMReservItmController extends CommonController {

    private ICommonService service = EDMReservItmServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneResbEntity.class), null);
    }
}

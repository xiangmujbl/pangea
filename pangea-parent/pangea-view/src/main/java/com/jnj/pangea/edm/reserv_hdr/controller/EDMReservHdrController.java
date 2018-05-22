package com.jnj.pangea.edm.reserv_hdr.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.RkpfEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.reserv_hdr.service.EDMReservHdrServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMReservHdrController extends CommonController {

    private ICommonService service = EDMReservHdrServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), RkpfEntity.class), null);
    }
}

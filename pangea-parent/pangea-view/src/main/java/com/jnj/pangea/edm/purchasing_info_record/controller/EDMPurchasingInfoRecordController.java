package com.jnj.pangea.edm.purchasing_info_record.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.EinaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchasing_info_record.service.EDMPurchasingInfoRecordServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPurchasingInfoRecordController extends CommonController {

    private ICommonService service = EDMPurchasingInfoRecordServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EinaEntity.class), null);
    }
}

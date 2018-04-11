package com.jnj.pangea.edm.purchasing_info_record_v1_latam.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.EinaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchasing_info_record_v1_latam.service.EDMPurchasingInfoRecordV1LatamServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPurchasingInfoRecordV1LatamController extends CommonController {

    private ICommonService service = EDMPurchasingInfoRecordV1LatamServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EinaEntity.class), null);
    }
}

package com.jnj.pangea.omp.gdm_batch.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMBatchMasterV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_batch.service.OMPGdmBatchServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmBatchController extends CommonController {

    private ICommonService service = OMPGdmBatchServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMBatchMasterV1Entity.class), null);
    }
}

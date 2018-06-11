package com.jnj.pangea.omp.gdm_lfu.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_lfu.service.OMPGdmLfuServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class OMPGdmLfuController extends CommonListController {

    private ICommonListService service = OMPGdmLfuServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
    }
}

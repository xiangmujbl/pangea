package com.jnj.pangea.omp.gdm_bom_element_Process.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderEntity;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_bom_element_Process.service.OMPGdmbomelementServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class OMPGdmbomelementController extends CommonListController {

    private ICommonListService service = OMPGdmbomelementServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMfgOrderEntity.class), null);
    }
}

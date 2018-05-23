package com.jnj.pangea.omp.gdm_bom_element.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.edm.EDMMatlBomEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_bom_element.service.OMPGdmBomElementServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class OMPGdmBomElementController extends CommonListController {

    private OMPGdmBomElementServiceImpl service = OMPGdmBomElementServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMatlBomEntity.class), null);
    }
}

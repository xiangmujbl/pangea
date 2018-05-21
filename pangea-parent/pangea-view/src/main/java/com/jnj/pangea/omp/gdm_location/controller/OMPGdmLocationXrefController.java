package com.jnj.pangea.omp.gdm_location.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location.service.OMPGdmLocationXrefServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmLocationXrefController extends CommonController {

    private ICommonService service = OMPGdmLocationXrefServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsPlnSplLocEntity.class), null);
    }
}

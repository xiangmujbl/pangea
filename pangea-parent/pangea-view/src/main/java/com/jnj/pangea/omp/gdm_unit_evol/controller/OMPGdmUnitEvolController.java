package com.jnj.pangea.omp.gdm_unit_evol.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_evol.service.OMPGdmUnitEvolServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashMap;
import java.util.Map;

public class OMPGdmUnitEvolController extends CommonController {

    private ICommonService service = OMPGdmUnitEvolServiceImpl.getInstance();
    private Map<String, Object> extraParam = new HashMap<>();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanConsTimeDepXchangeEntity.class), extraParam);
    }
}

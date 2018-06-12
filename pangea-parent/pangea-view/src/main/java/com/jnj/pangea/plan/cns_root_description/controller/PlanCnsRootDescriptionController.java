package com.jnj.pangea.plan.cns_root_description.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_root_description.service.PlanCnsRootDescriptionServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashMap;
import java.util.Map;

public class PlanCnsRootDescriptionController extends CommonController {

    private ICommonService service = PlanCnsRootDescriptionServiceImpl.getInstance();

    private Map<String, Object> extraParam = new HashMap<>();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), extraParam);
    }
}

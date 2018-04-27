package com.jnj.pangea.plan.cns_prod_cty_affl.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_prod_cty_affl.service.PlanCnsProdCtyAfflServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsProdCtyAfflController extends CommonController {

    private ICommonService service = PlanCnsProdCtyAfflServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
    }

}

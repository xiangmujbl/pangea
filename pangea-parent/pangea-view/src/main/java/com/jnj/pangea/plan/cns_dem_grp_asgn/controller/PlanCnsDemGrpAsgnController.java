package com.jnj.pangea.plan.cns_dem_grp_asgn.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.ems.EmsCustomerGroupEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_dem_grp_asgn.service.PlanCnsDemGrpAsgnServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class PlanCnsDemGrpAsgnController extends CommonController {

    private ICommonService service = PlanCnsDemGrpAsgnServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EmsCustomerGroupEntity.class), null);
    }
}
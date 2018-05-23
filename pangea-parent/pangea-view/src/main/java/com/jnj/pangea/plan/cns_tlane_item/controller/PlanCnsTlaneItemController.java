package com.jnj.pangea.plan.cns_tlane_item.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_tlane_item.service.PlanCnsTlaneItemServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;
import java.util.Map;

public class PlanCnsTlaneItemController extends CommonListController {

    private PlanCnsTlaneItemServiceImpl planCnsTlaneItemService = PlanCnsTlaneItemServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return planCnsTlaneItemService.buildView(raw.getKey(),
                BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsTlaneControlEntity.class), raw.getValue().toMap());
    }
}

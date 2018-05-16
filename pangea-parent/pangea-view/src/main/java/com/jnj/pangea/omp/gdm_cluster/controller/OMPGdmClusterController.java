package com.jnj.pangea.omp.gdm_cluster.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_cluster.service.OMPGdmClusterServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashMap;
import java.util.Map;

public class OMPGdmClusterController extends CommonController {

    private ICommonService service = OMPGdmClusterServiceImpl.getInstance();

    private Map<String, Object> extraParam = new HashMap<>();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsClustersEntity.class), extraParam);
    }
}

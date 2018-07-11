package com.jnj.pangea.omp.gdm_matl_mfg_allc.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.edm.EDMBatchMasterV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.project_one.PlmzEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_matl_mfg_allc.service.OMPGdmMatlMfgAllcServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class OMPGdmMatlMfgAllcController extends CommonController {

    private ICommonService service = OMPGdmMatlMfgAllcServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlmzEntity.class), null);
    }
}

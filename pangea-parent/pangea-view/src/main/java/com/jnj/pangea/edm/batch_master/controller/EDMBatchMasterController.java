package com.jnj.pangea.edm.batch_master.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.controller.CommonListController;
import com.jnj.pangea.common.entity.project_one.Mch1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.batch_master.service.EDMBatchMasterServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class EDMBatchMasterController extends CommonListController {

    private EDMBatchMasterServiceImpl service = EDMBatchMasterServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), Mch1Entity.class), null);
    }
}

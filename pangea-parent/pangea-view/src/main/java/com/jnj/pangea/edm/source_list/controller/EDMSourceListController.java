package com.jnj.pangea.edm.source_list.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.EordEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.source_list.service.EDMSourceListServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMSourceListController extends CommonController {

    private ICommonService service = EDMSourceListServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EordEntity.class), null);
    }
}

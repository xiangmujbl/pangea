package com.jnj.pangea.edm.bom_item_node.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.StasEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_item_node.service.EDMBomItemNodeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMBomItemNodeController extends CommonController {

    private ICommonService service = EDMBomItemNodeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), StasEntity.class), null);
    }
}

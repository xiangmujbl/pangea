package com.jnj.pangea.edm.matl_prod_versn.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectOne.ProjectOneMkalEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_prod_versn.service.EDMMatlProdVersnServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMatlProdVersnController extends CommonController {

    private ICommonService service = EDMMatlProdVersnServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneMkalEntity.class), null);
    }
}

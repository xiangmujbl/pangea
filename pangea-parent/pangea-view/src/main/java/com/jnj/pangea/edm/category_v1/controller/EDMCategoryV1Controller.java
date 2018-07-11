package com.jnj.pangea.edm.category_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.infa_mdm.ClKupCatgV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.category_v1.service.EDMCategoryV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * EDMCategory - rework - Curation
 * AEAZ-8294
 */
public class EDMCategoryV1Controller extends CommonController {

    private ICommonService service = EDMCategoryV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ClKupCatgV1Entity.class), null);
    }
}

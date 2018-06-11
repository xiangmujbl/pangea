package com.jnj.pangea.edm.bom_item.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMProjectOneSTPOEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_item.service.BOMITEMServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation
 * AEAZ-3268
 */
public class BOMITEMController extends CommonController {

    private ICommonService service = BOMITEMServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMProjectOneSTPOEntity.class), null);
    }
}

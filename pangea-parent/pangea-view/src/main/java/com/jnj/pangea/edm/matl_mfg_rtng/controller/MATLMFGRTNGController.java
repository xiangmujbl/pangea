package com.jnj.pangea.edm.matl_mfg_rtng.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMProjectOneMAPLEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_mfg_rtng.service.MATLMFGRTNGServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation
 * AEAZ-3268
 */
public class MATLMFGRTNGController extends CommonController {

    private ICommonService service = MATLMFGRTNGServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMProjectOneMAPLEntity.class), null);
    }
}

package com.jnj.pangea.edm.mfg_rtng_itm_v2.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.PlpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm_v2.service.EDMMfgRtngItmV2ServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMfgRtngItmV2Controller extends CommonController {

    private ICommonService service = EDMMfgRtngItmV2ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {

        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlpoEntity.class), null);
    }
}

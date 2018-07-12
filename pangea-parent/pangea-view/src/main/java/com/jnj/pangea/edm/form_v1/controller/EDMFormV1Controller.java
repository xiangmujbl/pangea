package com.jnj.pangea.edm.form_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.infa_mdm.ClKupFormV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.form_v1.service.EDMFormV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * EDMForm/Functional name - rework - Curation
 * AEAZ-8295
 */
public class EDMFormV1Controller extends CommonController {

    private ICommonService service = EDMFormV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        LogUtil.getLogger().info("=============RawDataEvent==="+raw.getValue().toString());
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ClKupFormV1Entity.class), null);
    }
}

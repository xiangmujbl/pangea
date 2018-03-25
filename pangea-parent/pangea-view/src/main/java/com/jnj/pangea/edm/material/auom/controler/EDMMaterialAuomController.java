package com.jnj.pangea.edm.material.auom.controler;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.MarmEntity;
import com.jnj.pangea.edm.material.auom.service.EDMMaterialAuomServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMMaterialAuomController extends CommonController {

    private EDMMaterialAuomServiceImpl materialAuomService = EDMMaterialAuomServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return materialAuomService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MarmEntity.class), null);

    }


}

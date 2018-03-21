package com.jnj.pangea.edm.material.auom.controler;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectone.MarmEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.auom.service.EDMMaterialAuomServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

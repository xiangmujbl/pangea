package com.jnj.pangea.edm.currency.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.currency.service.EDMCurrencyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMCurrencyController extends CommonController {

    private ICommonService edmCurrencyyService = EDMCurrencyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmCurrencyyService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EMSFMdmCurrenciesEntity.class), null);
    }

}

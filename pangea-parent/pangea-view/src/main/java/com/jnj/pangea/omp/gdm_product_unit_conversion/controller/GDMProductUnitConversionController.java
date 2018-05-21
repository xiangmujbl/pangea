package com.jnj.pangea.omp.gdm_product_unit_conversion.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.controller.ListResultObjectCommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product_unit_conversion.service.GDMProductUnitConversionServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;

public class GDMProductUnitConversionController extends ListResultObjectCommonController {

    private GDMProductUnitConversionServiceImpl service = GDMProductUnitConversionServiceImpl.getInstance();

    @Override
    public List<ResultObject> process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), null);
    }
}
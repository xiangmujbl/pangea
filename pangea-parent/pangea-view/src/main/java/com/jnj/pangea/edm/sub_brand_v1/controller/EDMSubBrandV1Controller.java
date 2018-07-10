package com.jnj.pangea.edm.sub_brand_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.infa_mdm.ClkupSubBrndEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sub_brand_v1.service.EDMSubBrandV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * EDMSub-Brand- rework - Curation
 * AEAZ-8297
 */
public class EDMSubBrandV1Controller extends CommonController {

    private ICommonService service = EDMSubBrandV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        LogUtil.getLogger().info("=====================raw"+raw.getKey()+";"+raw.getValue());
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ClkupSubBrndEntity.class), null);
    }
}

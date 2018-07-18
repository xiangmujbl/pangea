package com.jnj.pangea.edm.franchise_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.infa_mdm.ClKupFranV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.franchise_v1.service.EDMfFranchiseV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;
/**
 * EDMFranchise- rework - Curation
 * AEAZ-8299
 */
public class EDMfFranchiseV1Controller extends CommonController {

    private ICommonService service = EDMfFranchiseV1ServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ClKupFranV1Entity.class), null);
    }
}

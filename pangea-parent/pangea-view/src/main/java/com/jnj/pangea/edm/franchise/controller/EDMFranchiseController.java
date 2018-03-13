package com.jnj.pangea.edm.franchise.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.FranchiseEntity;
import com.jnj.pangea.edm.franchise.bo.EDMFranchiseBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMFranchiseController extends CommonController {

    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        FranchiseEntity franchiseEntity = BeanUtil.mapToBean(raw.getValue().toMap(), FranchiseEntity.class);
        EDMFranchiseBo franchiseBo = new EDMFranchiseBo();
        franchiseBo.setFranchise(franchiseEntity.getFranchise());
        franchiseBo.setFranchiseDescription(franchiseEntity.getFranchiseDescription());

        resultObject.setBaseBo(franchiseBo);
        return resultObject;
    }
}

package com.jnj.pangea.edm.franchise.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.FranchiseEntity;
import com.jnj.pangea.edm.franchise.bo.EDMFranchiseBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMFranchiseController extends CommonController{

    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        FranchiseEntity franchiseEntity = BeanUtil.mapToBean(rawMap,new FranchiseEntity());
        EDMFranchiseBo franchiseBo = new EDMFranchiseBo();
        franchiseBo.setFranchise(franchiseEntity.getFranchise());
        franchiseBo.setFranchiseDescription(franchiseEntity.getFranchiseDescription());

        resultObject.setBaseBo(franchiseBo);
        return resultObject;
    }
}

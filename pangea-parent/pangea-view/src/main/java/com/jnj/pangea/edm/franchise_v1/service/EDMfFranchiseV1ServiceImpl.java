package com.jnj.pangea.edm.franchise_v1.service;


import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.infa_mdm.ClKupFranV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.franchise_v1.bo.EDMFranchiseV1Bo;

/**
 * EDMFranchise- rework - Curation
 * AEAZ-8299
 */
public class EDMfFranchiseV1ServiceImpl implements ICommonService {

    private static EDMfFranchiseV1ServiceImpl instance;

    public static EDMfFranchiseV1ServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMfFranchiseV1ServiceImpl();
        }
        return instance;
    }
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMFranchiseV1Bo EDMfFranchiseBo = new EDMFranchiseV1Bo();
        ClKupFranV1Entity clKupFranEntity=(ClKupFranV1Entity)o;
        if(clKupFranEntity!=null){
            EDMfFranchiseBo.setFranchise(clKupFranEntity.getFranCd());
            EDMfFranchiseBo.setFranchiseDescription(clKupFranEntity.getFranNm());
        }
        resultObject.setBaseBo(EDMfFranchiseBo);
        return resultObject;
    }

}

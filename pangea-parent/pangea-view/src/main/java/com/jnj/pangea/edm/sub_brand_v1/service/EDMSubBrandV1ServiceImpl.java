package com.jnj.pangea.edm.sub_brand_v1.service;


import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.infa_mdm.ClkupSubBrndEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sub_brand_v1.bo.EDMSubBrandV1Bo;


/**
 * EDMSub-Brand- rework - Curation
 * AEAZ-8297
 */
public class EDMSubBrandV1ServiceImpl implements ICommonService {

    private static EDMSubBrandV1ServiceImpl instance;

    public static EDMSubBrandV1ServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMSubBrandV1ServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMSubBrandV1Bo brandV1Bo = new EDMSubBrandV1Bo();
        ClkupSubBrndEntity clkupSubBrndEntity=(ClkupSubBrndEntity)o;
        if(clkupSubBrndEntity!=null){
            brandV1Bo.setSubBrand(clkupSubBrndEntity.getSubBrndCd());
            brandV1Bo.setSubBrandDescription(clkupSubBrndEntity.getSubBrndDescnTxt());
        }
        resultObject.setBaseBo(brandV1Bo);
        return resultObject;
    }

}

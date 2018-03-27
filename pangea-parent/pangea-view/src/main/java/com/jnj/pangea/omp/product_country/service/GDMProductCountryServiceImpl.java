package com.jnj.pangea.omp.product_country.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.plan.CnsProdCountryAffEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_country.bo.GDMProductCountryBo;

public class GDMProductCountryServiceImpl implements ICommonService {
    private static GDMProductCountryServiceImpl instance;

    public static GDMProductCountryServiceImpl getInstance() {
        if (instance == null) {
            instance = new GDMProductCountryServiceImpl();
        }
        return instance;
    }

    EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        CnsProdCountryAffEntity prodCountryAffEntity = (CnsProdCountryAffEntity) o;

        GDMProductCountryBo productCountryBo = new GDMProductCountryBo();

        String dpParentCode = prodCountryAffEntity.getDpParentCode();
        String country = prodCountryAffEntity.getCountry();
        if (null != dpParentCode && null != country){
            String uniqueId = prodCountryAffEntity.getDpParentCode() + prodCountryAffEntity.getCountry();
            productCountryBo.setUniqueId(uniqueId);
            productCountryBo.setActiveFcterp(IConstant.VALUE.YES);
            productCountryBo.setCountryGroup(prodCountryAffEntity.getCountryGroup());

            EDMCountryEntity countryEntity = countryV1Dao.getEntityWithLocalCountry(country);
            if (null != countryEntity){
                productCountryBo.setCountryId(countryEntity.getCountryCode());
            }
            productCountryBo.setDpPlannerId(prodCountryAffEntity.getDpPlanner());
            productCountryBo.setDpSegmentation(prodCountryAffEntity.getDpSegmentation());
            productCountryBo.setProductClassification(prodCountryAffEntity.getProductClassification());
            productCountryBo.setProductId(dpParentCode);
            productCountryBo.setProductStatus(prodCountryAffEntity.getProductStatus());
            productCountryBo.setRootSize(prodCountryAffEntity.getRootSize());
            productCountryBo.setSegmentation(prodCountryAffEntity.getDpSegmentation());

            resultObject.setBaseBo(productCountryBo);
        }else {
            FailData failData = writeFailDataToRegion(prodCountryAffEntity, "C1", "All Key fields not Exist");
            resultObject.setFailData(failData);
        }

        return resultObject;
    }

    private FailData writeFailDataToRegion(CnsProdCountryAffEntity prodCountryAffEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("GDMProductCountry");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("CONS_LATAM");
        failData.setKey1(prodCountryAffEntity.getDpPlanner());
        failData.setKey2(prodCountryAffEntity.getCountry());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}

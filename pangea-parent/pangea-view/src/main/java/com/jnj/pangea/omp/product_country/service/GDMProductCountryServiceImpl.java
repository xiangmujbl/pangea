package com.jnj.pangea.omp.product_country.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.plan.CnsProdCtyAfflEntity;
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
        CnsProdCtyAfflEntity prodCtyAfflEntity = (CnsProdCtyAfflEntity) o;

        GDMProductCountryBo productCountryBo = new GDMProductCountryBo();

        String dpParentCode = prodCtyAfflEntity.getDpParentCode();
        String country = prodCtyAfflEntity.getCountry();
        if (null != dpParentCode && null != country) {
            String uniqueId = IConstant.VALUE.LA_ + prodCtyAfflEntity.getDpParentCode() + prodCtyAfflEntity.getCountry();
            productCountryBo.setUniqueId(uniqueId);
            productCountryBo.setActiveFcterp(IConstant.VALUE.YES);
            productCountryBo.setCountryGroup(prodCtyAfflEntity.getCountryGroup());

            EDMCountryEntity countryEntity = countryV1Dao.getEntityWithLocalCountry(country);
            if (null != countryEntity) {
                productCountryBo.setCountryId(countryEntity.getCountryCode());
            }
            productCountryBo.setDpPlannerId(prodCtyAfflEntity.getDpPlanner());
            productCountryBo.setDpSegmentation(prodCtyAfflEntity.getDpSegmentation());
            productCountryBo.setProductClassification(prodCtyAfflEntity.getProductClassification());
            productCountryBo.setProductId(dpParentCode);
            productCountryBo.setProductStatus(prodCtyAfflEntity.getProductStatus());
            productCountryBo.setRootSize(prodCtyAfflEntity.getRootSize());
            productCountryBo.setSegmentation(prodCtyAfflEntity.getDpSegmentation());

            resultObject.setBaseBo(productCountryBo);
        } else {
            FailData failData = writeFailDataToRegion(prodCtyAfflEntity, IConstant.FAILED.ERROR_CODE.C1, "All Key fields not Exist");
            resultObject.setFailData(failData);
        }

        return resultObject;
    }

    private FailData writeFailDataToRegion(CnsProdCtyAfflEntity prodCountryAffEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.GDM_PRODUCT_COUNTRY);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("");
        failData.setKey1(prodCountryAffEntity.getDpPlanner());
        failData.setKey2(prodCountryAffEntity.getCountry());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}

package com.jnj.pangea.omp.product_country.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsProdCtyAffEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_country.bo.GDMProductCountryBo;
import org.apache.commons.lang3.StringUtils;

public class GDMProductCountryServiceImpl implements ICommonService {

    private static GDMProductCountryServiceImpl instance;

    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();

    public static GDMProductCountryServiceImpl getInstance() {
        if (instance == null) {
            instance = new GDMProductCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        CnsProdCtyAffEntity prodCtyAfflEntity = (CnsProdCtyAffEntity) o;
        GDMProductCountryBo productCountryBo = new GDMProductCountryBo();

        String dpParentCode = prodCtyAfflEntity.getDpParentCode();
        String country = prodCtyAfflEntity.getCountry();
        if (StringUtils.isNotEmpty(dpParentCode) && StringUtils.isNotEmpty(country)) {
            // C1
            String uniqueId = IConstant.VALUE.LA_ + prodCtyAfflEntity.getDpParentCode() + prodCtyAfflEntity.getCountry();
            productCountryBo.setUniqueId(uniqueId);
            // D1
            productCountryBo.setActiveFCTERP(IConstant.VALUE.YES);
            productCountryBo.setCountryGroup(prodCtyAfflEntity.getCountryGrp());
            productCountryBo.setCountryId(prodCtyAfflEntity.getCountry());
            productCountryBo.setDpPlannerId(prodCtyAfflEntity.getDpPlannerId());
            productCountryBo.setDpSegmentation(prodCtyAfflEntity.getDpSegmentation());
            // T1
            if (StringUtils.isNotEmpty(prodCtyAfflEntity.getOvrPrdClass())) {
                productCountryBo.setProductClassification(prodCtyAfflEntity.getOvrPrdClass());
            } else if (StringUtils.isNotEmpty(prodCtyAfflEntity.getProdClassification())) {
                productCountryBo.setProductClassification(prodCtyAfflEntity.getProdClassification());
            } else {
                FailData failData = writeFailDataToRegion(prodCtyAfflEntity, IConstant.FAILED.ERROR_CODE.T1, "All Key fields not Exist");
                resultObject.setFailData(failData);
                return resultObject;
            }
            // T2
            if (StringUtils.isNotEmpty(prodCtyAfflEntity.getOvrPrdStat())) {
                productCountryBo.setProductStatus(prodCtyAfflEntity.getOvrPrdStat());
            } else if (StringUtils.isNotEmpty(prodCtyAfflEntity.getProdStatus())) {
                productCountryBo.setProductStatus(prodCtyAfflEntity.getProdStatus());
            } else {
                FailData failData = writeFailDataToRegion(prodCtyAfflEntity, IConstant.FAILED.ERROR_CODE.T2, "All Key fields not Exist");
                resultObject.setFailData(failData);
                return resultObject;
            }
            //T3
            PlanCnsMaterialPlanStatusEntity entityWithLocalParentCode = planCnsMaterialPlanStatusDao.getEntityWithLocalParentCode(dpParentCode);
            if(entityWithLocalParentCode != null && entityWithLocalParentCode.getDpRelevant() != null ){
                if(!entityWithLocalParentCode.getDpRelevant().equals(IConstant.VALUE.X)){
                    return resultObject;
                }else{
                    String productId = IConstant.VALUE.LA_ + prodCtyAfflEntity.getDpParentCode();
                    productCountryBo.setProductId(productId);
                }
            }
            productCountryBo.setRootSize(prodCtyAfflEntity.getRootSize());
            productCountryBo.setDpSegmentation(prodCtyAfflEntity.getDpSegmentation());
            resultObject.setBaseBo(productCountryBo);
        } else {
            FailData failData = writeFailDataToRegion(prodCtyAfflEntity, IConstant.FAILED.ERROR_CODE.C1, "All Key fields not Exist");
            resultObject.setFailData(failData);
        }

        return resultObject;
    }

    private FailData writeFailDataToRegion(CnsProdCtyAffEntity prodCountryAffEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.GDM_PRODUCT_COUNTRY);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("");
        failData.setKey1(prodCountryAffEntity.getSourceSystem());
        failData.setKey2(prodCountryAffEntity.getDpParentCode());
        failData.setKey3(prodCountryAffEntity.getCountry());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}

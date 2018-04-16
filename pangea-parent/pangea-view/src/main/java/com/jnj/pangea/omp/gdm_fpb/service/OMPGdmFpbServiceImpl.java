package com.jnj.pangea.omp.gdm_fpb.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanQtyDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanValDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialAuomV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_fpb.bo.OMPGdmFpbBo;

import java.util.HashMap;
import java.util.Map;

public class OMPGdmFpbServiceImpl implements ICommonService {

    private static OMPGdmFpbServiceImpl instance;

    public static OMPGdmFpbServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmFpbServiceImpl();
        }
        return instance;
    }

    private PlanCnsFinPlanQtyDaoImpl cnsFinPlanQtyDao = PlanCnsFinPlanQtyDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsFinPlanValDaoImpl cnsFinPlanValDao = PlanCnsFinPlanValDaoImpl.getInstance();
    private EDMMaterialAuomV1DaoImpl materialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        Map<String, Object> extraParam  = (HashMap) o2;

        OMPGdmFpbBo gdmFpbBo = new OMPGdmFpbBo();

        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(sourceSystem);

        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber);
        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber);

        String productId = sourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode();
        gdmFpbBo.setProductId(productId);

//        String extraParamKey = productId;
        if(extraParam.containsKey(productId)){
            String extraParamValue = String.format("%03d",Integer.parseInt(extraParam.get(productId).toString()) + 1);
            extraParam.put(productId,extraParamValue);
        } else {
            extraParam.put(productId,"1");
        }

        String sequeceNumber = "";
        String fpbId = productId +extraParam.get(productId);
        gdmFpbBo.setFpbId(fpbId);

        if (null != finPlanValEntity) {
            gdmFpbBo.setValue(finPlanValEntity.getValue());
        }

        if (null != finPlanQtyEntity) {
            String country = finPlanQtyEntity.getCountry();
            EDMCountryV1Entity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
            if (null != countryV1Entity) {
                gdmFpbBo.setCountryId(countryV1Entity.getCountryCode());
            }

            String currency = finPlanQtyEntity.getCurrency();
            EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
            if (null != currencyV1Entity) {
                gdmFpbBo.setCurrencyId(currencyV1Entity.getCurrencyCode());
            }

            String unitId = finPlanQtyEntity.getUnitId();
            if (null != unitId && unitId.equals(materialGlobalV1Entity.getLocalBaseUom())) {
                EDMMaterialAuomV1Entity materialAuomV1Entity = materialAuomV1Dao.getEntityWithConditions(localMaterialNumber, unitId);
                if (null != materialAuomV1Entity) {
                    String volume = Integer.parseInt(finPlanQtyEntity.getQuantity()) * (Integer.parseInt(materialAuomV1Entity.getLocalNumerator()) / Integer.parseInt(materialAuomV1Entity.getLocalDenominator())) + "";
                    gdmFpbBo.setVolume(volume);
                }
            }
        }

        LogUtil.getCoreLog().info("gdmFpbBo:"+gdmFpbBo);
        resultObject.setBaseBo(gdmFpbBo);
        return resultObject;
    }
}

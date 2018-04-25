package com.jnj.pangea.omp.gdm_lfu.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanQtyDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanValDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialAuomV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_lfu.bo.OMPGdmLfuBo;

import java.util.UUID;

public class OMPGdmLfuServiceImpl implements ICommonService {

    private static OMPGdmLfuServiceImpl instance;

    public static OMPGdmLfuServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLfuServiceImpl();
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

        OMPGdmLfuBo gdmLfuBo = new OMPGdmLfuBo();

        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(sourceSystem);

        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.LFU);
        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.LFU);

        String productId = sourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode();
        gdmLfuBo.setProductId(productId);

        String sequeceNumber = UUID.randomUUID().toString();
        String lfuId = productId +sequeceNumber;
        gdmLfuBo.setLfuId(lfuId);

        if (null != finPlanValEntity) {
            gdmLfuBo.setValue(finPlanValEntity.getValue());
        }

        if (null != finPlanQtyEntity) {
            String country = finPlanQtyEntity.getCountry();
            EDMCountryEntity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
            if (null != countryV1Entity) {
                gdmLfuBo.setCountryId(countryV1Entity.getCountryCode());
            }

            String currency = finPlanQtyEntity.getCurrency();
            EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
            if (null != currencyV1Entity) {
                gdmLfuBo.setCurrencyId(currencyV1Entity.getCurrencyCode());
            }

            String unitId = finPlanQtyEntity.getUnitId();
            if (null != unitId && unitId.equals(materialGlobalV1Entity.getLocalBaseUom())) {
                EDMMaterialAuomV1Entity materialAuomV1Entity = materialAuomV1Dao.getEntityWithConditions(localMaterialNumber, unitId);

                if (null != materialAuomV1Entity) {
                    try {
                        int quantity = Integer.parseInt(finPlanQtyEntity.getQuantity());
                        int localNumerator = Integer.parseInt(materialAuomV1Entity.getLocalNumerator());
                        int localDenominator = Integer.parseInt(materialAuomV1Entity.getLocalDenominator());
                        if (0!=quantity && 0!=localNumerator && 0!=localDenominator){
                            String volume = quantity*(localNumerator/localDenominator)+"";
                            gdmLfuBo.setVolume(volume);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        resultObject.setBaseBo(gdmLfuBo);
        return resultObject;
    }
}

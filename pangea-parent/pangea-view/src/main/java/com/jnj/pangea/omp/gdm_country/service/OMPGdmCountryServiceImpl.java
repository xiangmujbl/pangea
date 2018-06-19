package com.jnj.pangea.omp.gdm_country.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryInputDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryInputEntity;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_country.bo.OMPGdmCountryBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class OMPGdmCountryServiceImpl implements ICommonService {

    private EDMCountryInputDaoImpl EDMCountryInputDao=EDMCountryInputDaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl EDMCurrencyV1Dao=EDMCurrencyV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private static OMPGdmCountryServiceImpl instance;

    public static OMPGdmCountryServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMCountryV1Entity countryV1Entity = (EDMCountryV1Entity) o;
        OMPGdmCountryBo gdmCountryBo = new OMPGdmCountryBo();
        if (null != countryV1Entity) {
            String sourceSystem = countryV1Entity.getSourceSystem();

            // rules J1  T2
            if (StringUtils.isNotEmpty(sourceSystem)){
                EDMCountryInputEntity EDMCountryInputEntity
                        = EDMCountryInputDao.getEntityWithFormName(countryV1Entity.getSourceSystem(), countryV1Entity.getLocalCountry());

                if (null == EDMCountryInputEntity) {
                    FailData failData = writeFailDataToRegion(countryV1Entity, "J1", "localcountry do not exist in edm country");
                    resultObject.setFailData(failData);
                    return resultObject;
                }
                EDMCurrencyV1Entity EDMCurrencyV1Entity = EDMCurrencyV1Dao.getEntityWithLocalCurrencyAndSourceSystem(EDMCountryInputEntity.getLocalCurrency(), sourceSystem);

                if (null == EDMCurrencyV1Entity) {

                    FailData failData = writeFailDataToRegion(countryV1Entity, "J1", "localCurrecny do not exist in edm currency");
                    resultObject.setFailData(failData);
                    return resultObject;
                }
                LogUtil.getCoreLog().info("))))))))))))))))))))"+EDMCurrencyV1Entity.getCurrencyCode());
                gdmCountryBo.setCurrencyId(EDMCurrencyV1Entity.getCurrencyCode());


                resultObject.setBaseBo(gdmCountryBo);
            }

            if ((countryV1Entity.getCountryCode() !=null
                    && StringUtils.isNotEmpty(countryV1Entity.getCountryCode().trim()))
                    &&(countryV1Entity.getCountryName() !=null
                    && StringUtils.isNotEmpty(countryV1Entity.getCountryName().trim()))){

                if (StringUtils.isNotEmpty(sourceSystem)) {

                    List<PlanCnsPlanParameterEntity> planCnsPlanParameterEntityList = planCnsPlanParameterDao.getEntitiesWithSourceSystem(sourceSystem);

                    if (planCnsPlanParameterEntityList.size() > 0) {
                        // rules T1
                        for (PlanCnsPlanParameterEntity entity:planCnsPlanParameterEntityList) {
                            if (countryV1Entity.getSourceSystem().equals(entity.getSourceSystem()) ){
                                gdmCountryBo.setCountryId(countryV1Entity.getCountryCode());
                                break;
                            }
                        }
                        gdmCountryBo.setCountryDescription(countryV1Entity.getCountryName());

                        // rules D1
                        gdmCountryBo.setActiveFCTERP(IConstant.VALUE.YES);
                        gdmCountryBo.setActiveOPRERP(IConstant.VALUE.YES);

                        // rules D2
                        gdmCountryBo.setActiveSOPERP(IConstant.VALUE.NO);

                    }
                    else{
                        return null;
                    }
                }
            }else{
                return null;
            }

        }

        return resultObject;
    }
    private FailData writeFailDataToRegion(EDMCountryV1Entity EDMCountryV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_COUNTRY);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("");
        failData.setKey1(EDMCountryV1Entity.getSourceSystem());
        failData.setKey2(EDMCountryV1Entity.getLocalCountry());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}

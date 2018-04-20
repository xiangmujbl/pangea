package com.jnj.pangea.omp.gdm_country.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_country.bo.OMPGdmCountryBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmCountryServiceImpl implements ICommonService {

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
            if (StringUtils.isNotEmpty(sourceSystem)) {

                PlanCnsPlanParameterEntity planCnsPlanParameterEntity = planCnsPlanParameterDao.getEntitiesWithSourceSystem(sourceSystem);

                if (null != planCnsPlanParameterEntity) {

                    // rules T1
                    gdmCountryBo.setCountryId(countryV1Entity.getCountryCode());
                    gdmCountryBo.setCountryDescription(countryV1Entity.getCountryName());

                    // rules D1
                    gdmCountryBo.setActiveFCTERP(IConstant.VALUE.YES);
                    gdmCountryBo.setActiveOPRERP(IConstant.VALUE.YES);

                    // rules D2
                    gdmCountryBo.setActiveSOPERP(IConstant.VALUE.NO);
                    gdmCountryBo.setMrc("");
                }
                resultObject.setBaseBo(gdmCountryBo);
            }
        }
        return resultObject;
    }
}

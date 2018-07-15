package com.jnj.pangea.edm.country.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmCountriesDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCountryInputDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanRegionDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCountryServiceImpl implements ICommonService {


    private static ICommonService instance;

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EMSFMdmCountriesDaoImpl emsfMdmCountriesDao = EMSFMdmCountriesDaoImpl.getInstance();
    private PlanCnsCountryInputDaoImpl planCnsCountryInputDao = PlanCnsCountryInputDaoImpl.getInstance();
    private PlanCnsPlanRegionDaoImpl planCnsPlanRegionDao = PlanCnsPlanRegionDaoImpl.getInstance();
    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        EMSFMdmCountriesEntity mainData = (EMSFMdmCountriesEntity) o;

        EDMCountryBo edmCountryBo = new EDMCountryBo();

        // J1
        String zSourceSystem = mainData.getzSourceSystem();
        //T3
        if(StringUtils.isEmpty(zSourceSystem)){
            return null;
        }
        if (StringUtils.isNotEmpty(zSourceSystem)) {

            edmCountryBo.setSourceSystem(sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(zSourceSystem));
            //T3
            if(StringUtils.isEmpty(edmCountryBo.getSourceSystem())){
                return null;
            }
        }



        edmCountryBo.setLocalCountry(mainData.getMdmCode());

        String zEntCodeIso3166Alpha2 = mainData.getzEntCodeIso3166Alpha2();

        edmCountryBo.setCountryCode(zEntCodeIso3166Alpha2);

        // T2
        if (StringUtils.isNotEmpty(zEntCodeIso3166Alpha2)) {
            EMSFMdmCountriesEntity emsfMdmCountriesEntity = emsfMdmCountriesDao.getMdmNameWithzSourceSystemAndMdmCode(IConstant.VALUE.EMS, zEntCodeIso3166Alpha2);
            if (emsfMdmCountriesEntity != null) {
                edmCountryBo.setCountryName(emsfMdmCountriesEntity.getMdmName());
            }
        }
        //  J2
        String consumerPlanningRegion = planCnsCountryInputDao.getConsumerPlanningRegion( edmCountryBo.getSourceSystem(), edmCountryBo.getLocalCountry());
        if(StringUtils.isNotBlank(consumerPlanningRegion)){
            edmCountryBo.setConsumerPlanningRegion(consumerPlanningRegion);
            //J3
            String planningRegionDesc = planCnsPlanRegionDao.getPlanningRegionDesc(edmCountryBo.getConsumerPlanningRegion());
            if(StringUtils.isNotBlank(planningRegionDesc)){
                edmCountryBo.setConsumerPlannRegDesc(planningRegionDesc);
            }
        }

        resultObject.setBaseBo(edmCountryBo);
        return resultObject;
    }

}

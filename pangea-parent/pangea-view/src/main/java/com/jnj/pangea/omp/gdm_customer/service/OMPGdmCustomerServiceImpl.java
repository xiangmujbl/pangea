package com.jnj.pangea.omp.gdm_customer.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsClustersDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_customer.bo.OMPGdmCustomerBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmCustomerServiceImpl implements ICommonService {

    private static OMPGdmCustomerServiceImpl instance;

    public static OMPGdmCustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmCustomerServiceImpl();
        }
        return instance;
    }

    private PlanCnsClustersDaoImpl cnsClustersDao = PlanCnsClustersDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsDemGrpAsgnEntity cnsDemGrpAsgnEntity = (PlanCnsDemGrpAsgnEntity) o;
        OMPGdmCustomerBo gdmCustomerBo = new OMPGdmCustomerBo();

        // J1
        String countryID = cnsDemGrpAsgnEntity.getCountryAffiliate();
        if (StringUtils.isNotEmpty(countryID)) {

            PlanCnsClustersEntity planCnsClustersEntity = cnsClustersDao.getEntityWithCountryID(countryID);
            if (null != planCnsClustersEntity) {
                gdmCustomerBo.setCustCluster(planCnsClustersEntity.getCountryId());
                gdmCustomerBo.setSalesOrganization(planCnsClustersEntity.getCluster());
                gdmCustomerBo.setSubCluster(planCnsClustersEntity.getSubCluster());
            }
        }

        // J2
        String localCountry = cnsDemGrpAsgnEntity.getCountryAffiliate();
        if (StringUtils.isNotEmpty(localCountry)) {

            EDMCountryEntity eDMCountryEntity = countryV1Dao.getEntityWithLocalCountry(localCountry);
            if (null != eDMCountryEntity) {
                gdmCustomerBo.setRegionId(eDMCountryEntity.getConsumerPlanningRegion());

                gdmCustomerBo.setCustomerId(cnsDemGrpAsgnEntity.getDemandGroup());
                gdmCustomerBo.setActive(IConstant.VALUE.YES);
                gdmCustomerBo.setActiveFCTERP(IConstant.VALUE.YES);
                gdmCustomerBo.setActiveOPRERP(IConstant.VALUE.NO);
                gdmCustomerBo.setActiveSOPERP(IConstant.VALUE.NO);
                gdmCustomerBo.setAggrSoldTo(IConstant.VALUE.BLANK);
                gdmCustomerBo.setChannel(cnsDemGrpAsgnEntity.getChannel());
                gdmCustomerBo.setChannelDescription(cnsDemGrpAsgnEntity.getChannelDescription());
                gdmCustomerBo.setCountryId(cnsDemGrpAsgnEntity.getCountryAffiliate());
                gdmCustomerBo.setDistributionChannel(IConstant.VALUE.BLANK);
                gdmCustomerBo.setDistributor(IConstant.VALUE.NO);
                gdmCustomerBo.setDivision(IConstant.VALUE.BLANK);
                gdmCustomerBo.setECommerce(IConstant.VALUE.NO);
                gdmCustomerBo.setGlobalCustomerId(IConstant.VALUE.BLANK);
                gdmCustomerBo.setName(cnsDemGrpAsgnEntity.getCustomerName());
                gdmCustomerBo.setPartner(IConstant.VALUE.BLANK);
                gdmCustomerBo.setPlanningCustomerGroupId(IConstant.VALUE.BLANK);
                gdmCustomerBo.setSourceLocationId(IConstant.VALUE.BLANK);
                gdmCustomerBo.setUcn(IConstant.VALUE.ZERO);
                resultObject.setBaseBo(gdmCustomerBo);
            }
        }

        return resultObject;
    }
}

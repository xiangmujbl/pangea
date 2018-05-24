package com.jnj.pangea.omp.gdm_customer.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustChannelDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsClustersDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanDemGrpEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_customer.bo.OMPGdmCustomerBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

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
    private PlanCnsDemGrpAsgnDaoImpl cnsDemGrpAsgnDao = PlanCnsDemGrpAsgnDaoImpl.getInstance();
    private PlanCnsCustChannelDaoImpl cnsCustChannelDao = PlanCnsCustChannelDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsPlanDemGrpEntity cnsPlanDemGrpEntity = (PlanCnsPlanDemGrpEntity) o;
        OMPGdmCustomerBo gdmCustomerBo = new OMPGdmCustomerBo();

        // J4,J6,J7
        if (StringUtils.isNotEmpty(cnsPlanDemGrpEntity.getDemandGroupId()) && StringUtils.isNotEmpty(cnsPlanDemGrpEntity.getSourceSystem())) {

            List<PlanCnsDemGrpAsgnEntity> cnsDemGrpAsgnEntityList = cnsDemGrpAsgnDao.getEntityWithDemandGroupAndSourceSystem(cnsPlanDemGrpEntity.getDemandGroupId(), cnsPlanDemGrpEntity.getSourceSystem());
            if (!cnsDemGrpAsgnEntityList.isEmpty()) {

                PlanCnsDemGrpAsgnEntity cnsDemGrpAsgnEntity = cnsDemGrpAsgnEntityList.get(0);
                gdmCustomerBo.setChannel(cnsDemGrpAsgnEntity.getChannel());
                gdmCustomerBo.setCountryId(cnsDemGrpAsgnEntity.getCountryAffiliate());
                gdmCustomerBo.setSalesOrganization(cnsDemGrpAsgnEntity.getSalesOrganization());

                // J1,J3
                String countryID = cnsDemGrpAsgnEntity.getCountryAffiliate();
                String sourceSystem = cnsDemGrpAsgnEntity.getSourceSystem();
                if (StringUtils.isNotEmpty(countryID) && StringUtils.isNotEmpty(sourceSystem)) {

                    PlanCnsClustersEntity planCnsClustersEntity = cnsClustersDao.getEntityWithCountryIdAndSourceSystem(countryID, sourceSystem);
                    if (null != planCnsClustersEntity) {
                        gdmCustomerBo.setCustCluster(planCnsClustersEntity.getCluster());
                        gdmCustomerBo.setSubCluster(planCnsClustersEntity.getSubCluster());

                        // J2
                        String localCountry = cnsDemGrpAsgnEntity.getCountryAffiliate();
                        if (StringUtils.isNotEmpty(localCountry)) {

                            EDMCountryEntity eDMCountryEntity = countryV1Dao.getEntityWithLocalCountryAndSourceSystem(localCountry, sourceSystem);
                            if (null != eDMCountryEntity) {
                                gdmCustomerBo.setRegionId(eDMCountryEntity.getConsumerPlanningRegion());

                                // J5
                                String salesOrg = cnsDemGrpAsgnEntity.getSalesOrganization();
                                String channel = cnsDemGrpAsgnEntity.getChannel();
                                if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(channel)) {
                                    List<PlanCnsCustChannelEntity> cnsCustChannelEntityList = cnsCustChannelDao.getEntitiesWithSourceSystemAndSalesOrgAndChannel(sourceSystem, salesOrg, channel);
                                    if (!cnsCustChannelEntityList.isEmpty()) {

                                        PlanCnsCustChannelEntity cnsCustChannelEntity = cnsCustChannelEntityList.get(0);
                                        gdmCustomerBo.setChannelDescription(cnsCustChannelEntity.getChannelDesc());
                                    }
                                }

                                gdmCustomerBo.setCustomerId(cnsPlanDemGrpEntity.getDemandGroupId());
                                gdmCustomerBo.setActive(IConstant.VALUE.YES);
                                gdmCustomerBo.setActiveFCTERP(IConstant.VALUE.YES);
                                gdmCustomerBo.setActiveOPRERP(IConstant.VALUE.NO);
                                gdmCustomerBo.setActiveSOPERP(IConstant.VALUE.NO);
                                gdmCustomerBo.setChannel(cnsDemGrpAsgnEntity.getChannel());
                                gdmCustomerBo.setCountryId(cnsDemGrpAsgnEntity.getCountryAffiliate());
                                gdmCustomerBo.setDistributionChannel(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setDistributor(IConstant.VALUE.NO);
                                gdmCustomerBo.setECommerce(IConstant.VALUE.NO);
                                gdmCustomerBo.setForecastSource(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setGlobalCustomerId(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setName(cnsPlanDemGrpEntity.getDemandGroupDesc());
                                gdmCustomerBo.setPartner(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setPartnerCountry(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setPartnerName(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setPartnerRegion(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setPartnerRole(IConstant.VALUE.BLANK);
                                gdmCustomerBo.setPlanningCustomerGroupID(cnsPlanDemGrpEntity.getDemandGroupId());
                                gdmCustomerBo.setSourceLocationId(IConstant.VALUE.BLANK);

                                resultObject.setBaseBo(gdmCustomerBo);
                            }
                        }
                    }
                }
            }
        }

        return resultObject;
    }
}

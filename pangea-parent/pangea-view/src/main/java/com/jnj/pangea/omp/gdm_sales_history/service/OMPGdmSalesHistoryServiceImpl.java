package com.jnj.pangea.omp.gdm_sales_history.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustExclDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsSoTypeInclEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsSoTypeInclDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCertDeterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCertDeterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.common.entity.project_one.TvroEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTvroDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsOrdRejEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsOrdRejDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_sales_history.bo.OMPGdmSalesHistoryBo;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OMPGdmSalesHistoryServiceImpl implements ICommonService {

    private static OMPGdmSalesHistoryServiceImpl instance;

    public static OMPGdmSalesHistoryServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmSalesHistoryServiceImpl();
        }
        return instance;
    }

    private PlanCnsCustExclDaoImpl cnsCustExclDao = PlanCnsCustExclDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private PlanCnsSoTypeInclDaoImpl cnsSoTypeInclDao = PlanCnsSoTypeInclDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsCertDeterDaoImpl cnsCertDeterDao = PlanCnsCertDeterDaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();
    private PlanCnsDemGrpAsgnDaoImpl cnsDemGrpAsgnDao = PlanCnsDemGrpAsgnDaoImpl.getInstance();
    private ProjectOneKnvhDaoImpl knvhDao = ProjectOneKnvhDaoImpl.getInstance();
    private ProjectOneTvroDaoImpl tvroDao = ProjectOneTvroDaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsOrdRejDaoImpl cnsOrdRejDao = PlanCnsOrdRejDaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    private SimpleDateFormat YMDSdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat DMYSdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMSalesOrderV1Entity salesOrderV1Entity = (EDMSalesOrderV1Entity) o;

        OMPGdmSalesHistoryBo gdmSalesHistoryBo = new OMPGdmSalesHistoryBo();
        String localSalesOrg = salesOrderV1Entity.getLocalSalesOrg();
        String localShipToParty = salesOrderV1Entity.getLocalShipToParty();
        String localOrderType = salesOrderV1Entity.getLocalOrderType();
        String localItemCategory = salesOrderV1Entity.getLocalItemCategory();

        String salesHistoryId = salesOrderV1Entity.getSalesOrderNo() + salesOrderV1Entity.getSalesOrderItem();
        gdmSalesHistoryBo.setSalesHistoryId(salesHistoryId);
        gdmSalesHistoryBo.setActiveFCTERP(IConstant.VALUE.YES);

        gdmSalesHistoryBo.setCertaintyId(checkT2(localSalesOrg, localOrderType, localItemCategory));

        String currencyId = checkT4(salesOrderV1Entity.getLocalSDItemCurrency());
        if (StringUtils.isNotEmpty(currencyId)){
            gdmSalesHistoryBo.setCurrencyId(currencyId);
        }else {
            FailData failData = writeFailData(salesOrderV1Entity, IConstant.FAILED.ERROR_CODE.T4, "Unable to find the Enterprise currency code");
            resultObject.setFailData(failData);
            return resultObject;
        }

        String customerId = checkT5(localShipToParty, localSalesOrg, salesOrderV1Entity.getLocalRequestedDate());
        if (StringUtils.isNotEmpty(customerId)) {
            gdmSalesHistoryBo.setCustomerId(customerId);
        } else {
            FailData failData = writeFailData(salesOrderV1Entity, IConstant.FAILED.ERROR_CODE.T5, "Demand group can not be determined");
            resultObject.setFailData(failData);
            return resultObject;
        }

        String dueDate = checkT6(salesOrderV1Entity.getLocalRoute(), salesOrderV1Entity.getLocalRequestedDate());
        if (StringUtils.isNotEmpty(dueDate)) {
            gdmSalesHistoryBo.setDueDate(dueDate);
            gdmSalesHistoryBo.setFromDueDate(checkF2T6(dueDate));
        }
        String locationId = salesOrderV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + salesOrderV1Entity.getLocalPlant();
        gdmSalesHistoryBo.setLocationId(locationId);

        gdmSalesHistoryBo.setOrderType(salesOrderV1Entity.getLocalOrderType());

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(salesOrderV1Entity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity) {
            gdmSalesHistoryBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            gdmSalesHistoryBo.setSalesUnit(checkT10(materialGlobalV1Entity.getLocalBaseUom()));
        }

        gdmSalesHistoryBo.setQuantity(checkJ2T9(salesOrderV1Entity));

        resultObject.setBaseBo(gdmSalesHistoryBo);
        return resultObject;
    }

    private String getParameterValue(String attribute) {
        PlanCnsPlanParameterEntity planParameterEntity = cnsPlanParameterDao.getEntityWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_SALES_HISTORY, attribute, IConstant.VALUE.LESS_MONTH, IConstant.VALUE.I);
        if (null != planParameterEntity) {
            return planParameterEntity.getParameterValue();
        }
        return null;
    }

    private PlanCnsCustExclEntity checkF1(String localSalesOrg, String localShipToParty) {
        return cnsCustExclDao.getEntityWithSalesOrgAndCustomerShipTo(localSalesOrg, localShipToParty);
    }

    private String checkT2(String localSalesOrg, String localOrderType, String localItemCategory) {
        PlanCnsCertDeterEntity certDeterEntity = cnsCertDeterDao.getEntitiesWithConditions(localSalesOrg, localOrderType, localItemCategory);
        if (null != certDeterEntity) {
            return certDeterEntity.getCertaintyKey();
        } else {
            certDeterEntity = cnsCertDeterDao.getEntitiesWithSalesOrgAndItemCategory(localSalesOrg, localItemCategory);
            if (null != certDeterEntity) {
                return certDeterEntity.getCertaintyKey();
            } else {
                certDeterEntity = cnsCertDeterDao.getEntitiesWithSalesOrgAndOrderType(localSalesOrg, localOrderType);
                if (null != certDeterEntity) {
                    return certDeterEntity.getCertaintyKey();
                }
            }
        }
        return IConstant.VALUE.BASE;
    }

    private String checkT4(String localSdItemCurrency) {
        EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(localSdItemCurrency);
        if (null != currencyV1Entity) {
            return currencyV1Entity.getCurrencyCode();
        }
        return null;
    }

    private String checkT5(String localShipToParty, String localSalesOrg, String localRequestedDate) {
        PlanCnsDemGrpAsgnEntity demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntitiesWithCustomerIdAndSalesOrganization(localShipToParty, localSalesOrg);
        if (null != demGrpAsgnEntity) {
            return demGrpAsgnEntity.getDemandGroup();
        } else {
            KnvhEntity knvhEntity = knvhDao.getEntityWithKunnrAndVkorg(localShipToParty, localSalesOrg);
            if (null != knvhEntity) {
                String kunnr = knvhEntity.getHkunnr();
                String datbi = knvhEntity.getDatbi();
                try {
                    if (StringUtils.isNotEmpty(kunnr)) {
                        if (YMDSdf.parse(localRequestedDate).getTime() <= YMDSdf.parse(datbi).getTime()) {
                            demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerId(kunnr);
                            if (null != demGrpAsgnEntity) {
                                return demGrpAsgnEntity.getDemandGroup();
                            } else {
                                demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntitiesWithCustomerIdAndSalesOrganization(knvhEntity.getKunnr(), localSalesOrg);
                                if (null != demGrpAsgnEntity) {
                                    return demGrpAsgnEntity.getDemandGroup();
                                }
                            }
                        } else {
                            demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntitiesWithCustomerIdAndSalesOrganization(knvhEntity.getKunnr(), localSalesOrg);
                            if (null != demGrpAsgnEntity) {
                                return demGrpAsgnEntity.getDemandGroup();
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }

        }
        return null;
    }

    private String checkT6(String localRoute, String localRequestedDate) {

        TvroEntity tvroEntity = tvroDao.getEntityWithRoute(localRoute);
        if (null != tvroEntity) {
            String trazt = tvroEntity.getTrazt();
            try {
                Long traztLong = Long.parseLong(trazt);
                Date localRequestedDateFormat = YMDSdf.parse(localRequestedDate);

                Long time = localRequestedDateFormat.getTime() - traztLong*1000*60*60*24;
                Date dueDate = new Date(time);
                return DMYSdf.format(dueDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private String checkF2T6(String dueDate) {
        String parameterValue = getParameterValue(IConstant.VALUE.RESTRICT_SELECT);
        try {
            Date dueDateFormat = DMYSdf.parse(dueDate);
            Long parameterValueLong = Long.parseLong(parameterValue);
            Long time = dueDateFormat.getTime() - parameterValueLong*24*60*60*1000;
            Date fromDueDate = new Date(time);
            return DMYSdf.format(fromDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PlanCnsOrdRejEntity checkJ2(EDMSalesOrderV1Entity salesOrderV1Entity) {
        return cnsOrdRejDao.getEntityWithSalesOrgAndRejCd(salesOrderV1Entity.getLocalSalesOrg(), salesOrderV1Entity.getLocalRejReason());
    }

    private String checkJ2T9(EDMSalesOrderV1Entity salesOrderV1Entity) {
        PlanCnsOrdRejEntity ordRejEntity = null;
        if (null != salesOrderV1Entity.getLocalRejReason() || !"".equals(salesOrderV1Entity.getLocalRejReason())) {
            ordRejEntity = checkJ2(salesOrderV1Entity);
        }
        if (null != ordRejEntity || null == salesOrderV1Entity.getLocalRejReason() || "".equals(salesOrderV1Entity.getLocalRejReason())) {
            try {
                Double salesOrderQty = Double.parseDouble(salesOrderV1Entity.getSalesOrderQty());
                int localNumtoBase = Integer.parseInt(salesOrderV1Entity.getLocalNumtoBase());
                int localDentoBase = Integer.parseInt(salesOrderV1Entity.getLocalDentoBase());
                if (0 != salesOrderQty && 0 != localNumtoBase && 0 != localDentoBase) {
                    return salesOrderQty * localNumtoBase / localDentoBase + "";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return IConstant.VALUE.ZERO;
        }
        return null;
    }

    private String checkT10(String localBaseUom) {
        PlanCnsPlanUnitEntity planUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUom);
        if (null != planUnitEntity) {
            return planUnitEntity.getUnit();
        }
        return null;
    }

    private FailData writeFailData(EDMSalesOrderV1Entity salesOrderV1Entity, String errorCode, String errorValue) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.EDM_SALES_HISTORY);
        failData.setSourceSystem(salesOrderV1Entity.getSourceSystem());
        failData.setKey1(salesOrderV1Entity.getSalesOrderNo());
        failData.setKey2(salesOrderV1Entity.getSalesOrderItem());
        failData.setKey3(salesOrderV1Entity.getScheduleLineItem());
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }
}

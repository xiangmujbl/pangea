package com.jnj.pangea.omp.gdm_sales_history.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTvroDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import com.jnj.pangea.common.entity.project_one.TvroEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_sales_history.bo.OMPGdmSalesHistoryBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMSalesOrderV1Entity salesOrderV1Entity = (EDMSalesOrderV1Entity) o;
        Set<String> scheduleLineItemSet = (Set<String>) o2;

        OMPGdmSalesHistoryBo gdmSalesHistoryBo = new OMPGdmSalesHistoryBo();

        if (scheduleLineItemSet.contains(salesOrderV1Entity.getScheduleLineItem())){
            return null;
        }

        if (!checkJ1(salesOrderV1Entity)) {
            return null;
        }
        String localSalesOrg = salesOrderV1Entity.getLocalSalesOrg();
        String localShipToParty = salesOrderV1Entity.getLocalShipToParty();
        String localOrderType = salesOrderV1Entity.getLocalOrderType();
        String localItemCategory = salesOrderV1Entity.getLocalItemCategory();

        String salesHistoryId = salesOrderV1Entity.getSalesOrderNo() + salesOrderV1Entity.getSalesOrderItem();
        gdmSalesHistoryBo.setSalesHistoryId(salesHistoryId);
        gdmSalesHistoryBo.setActiveFCTERP(IConstant.VALUE.YES);

        gdmSalesHistoryBo.setCertaintyId(checkT2(localSalesOrg, localOrderType, localItemCategory));

        String currencyId = checkT4(salesOrderV1Entity.getLocalSDItemCurrency());
        if (StringUtils.isNotEmpty(currencyId)) {
            gdmSalesHistoryBo.setCurrencyId(currencyId);
        } else {
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

        String fromDueDate = checkT6(salesOrderV1Entity.getLocalRoute(), salesOrderV1Entity.getLocalRequestedDate());
        if (StringUtils.isNotEmpty(fromDueDate)) {
            gdmSalesHistoryBo.setFromDueDate(fromDueDate);
            if(checkF2T6(fromDueDate)){
                gdmSalesHistoryBo.setDueDate(fromDueDate);
            }else{
                return null;
            }
        }
        String locationId = salesOrderV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + salesOrderV1Entity.getLocalPlant();
        gdmSalesHistoryBo.setLocationId(locationId);

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(salesOrderV1Entity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity) {
            gdmSalesHistoryBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            gdmSalesHistoryBo.setSalesUnit(checkT10(materialGlobalV1Entity.getLocalBaseUom()));
        } else {
            FailData failData = writeFailData(salesOrderV1Entity, IConstant.FAILED.ERROR_CODE.T8, "Material not found in material global");
            resultObject.setFailData(failData);
            return resultObject;
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
        return cnsCustExclDao.getEntityWithSalesOrgAndNotCustomerShipTo(localSalesOrg, localShipToParty);
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
                } else {
                    return IConstant.VALUE.BASE;
                }
            }
        }
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
                        if (StringUtils.isNotEmpty(localRequestedDate) && StringUtils.isNotEmpty(datbi) && Integer.parseInt(localRequestedDate) <= Integer.parseInt(datbi)) {
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
                } catch (Exception e) {
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
            String traztd = tvroEntity.getTraztd();
            if (StringUtils.isNotEmpty(traztd) && StringUtils.isNotEmpty(localRequestedDate)) {

                int traztdNum = Integer.parseInt(traztd)/240000;
                Date localRequestedDateFormat = DateUtils.stringToDate(localRequestedDate, DateUtils.F_yyyyMMdd);
                Date fromDueDate = DateUtils.offsetDate(localRequestedDateFormat, -traztdNum);
                return DateUtils.dateToString(fromDueDate, DateUtils.dd_MM_yyyy_HHmmss);
            }
        }
        return null;
    }

    private boolean checkF2T6(String fromDueDate) {
        String parameterValue = getParameterValue(IConstant.VALUE.RESTRICT_SELECT);
        if (StringUtils.isNotEmpty(fromDueDate) && StringUtils.isNotEmpty(StringUtils.trim(parameterValue))) {
            Date presentDate = new Date();
            int parameterValueLong = (int) Double.parseDouble(parameterValue);
            Date resultDate = DateUtils.offsetDate(presentDate, -parameterValueLong);
            Date dueDateFormat = DateUtils.stringToDate(fromDueDate, DateUtils.dd_MM_yyyy_HHmmss);
            if (dueDateFormat.getTime() >= resultDate.getTime()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkJ1(EDMSalesOrderV1Entity salesOrderV1Entity) {
        Date presentDate = new Date();
        Date localOrderCreateDateFormat = DateUtils.stringToDate(salesOrderV1Entity.getLocalOrderCreateDt(), DateUtils.F_yyyyMMdd);
        int parameterValueInt = Integer.parseInt(getParameterValue(IConstant.VALUE.INITIAL_SELECT));
        Date resultDate = DateUtils.offsetDate(presentDate, -parameterValueInt);
        if (localOrderCreateDateFormat.getTime() >= resultDate.getTime()) {
            PlanCnsSoTypeInclEntity soTypeInclEntity = cnsSoTypeInclDao.getEntityWithSalesOrgAndOrderType(salesOrderV1Entity.getLocalSalesOrg(), salesOrderV1Entity.getLocalOrderType());
            if (null != soTypeInclEntity) {
                String localPlant = salesOrderV1Entity.getLocalPlant();
                EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithLocalPlantAndCountry(localPlant, soTypeInclEntity.getCountry());

                PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithThereConditions(salesOrderV1Entity.getLocalMaterialNumber(), localPlant, IConstant.VALUE.X);

                if (null != plantV1Entity && null != materialPlanStatusEntity) {
                    return true;
                }
            }
        }
        return false;
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
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_SALES_HISTORY);
        failData.setSourceSystem(salesOrderV1Entity.getSourceSystem());
        failData.setKey1(salesOrderV1Entity.getSalesOrderNo());
        failData.setKey2(salesOrderV1Entity.getSalesOrderItem());
        failData.setKey3(salesOrderV1Entity.getScheduleLineItem());
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }
}

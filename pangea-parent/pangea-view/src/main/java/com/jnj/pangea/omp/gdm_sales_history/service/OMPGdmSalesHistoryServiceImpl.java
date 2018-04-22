package com.jnj.pangea.omp.gdm_sales_history.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustExclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsSoTypeInclDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCertDeterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsCertDeterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsOrdRejEntity;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKnvhEntity;
import com.jnj.pangea.common.dao.impl.projectOne.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneTvroEntity;
import com.jnj.pangea.common.dao.impl.projectOne.ProjectOneTvroDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsOrdRejDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_sales_history.bo.OMPGdmSalesHistoryBo;

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
    private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private PlanCnsOrdRejDaoImpl cnsOrdRejDao = PlanCnsOrdRejDaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMSalesOrderV1Entity salesOrderV1Entity = (EDMSalesOrderV1Entity) o;

        OMPGdmSalesHistoryBo gdmSalesHistoryBo = new OMPGdmSalesHistoryBo();

        String localSalesOrg = salesOrderV1Entity.getLocalSalesOrg();
        String localShipToParty = salesOrderV1Entity.getLocalShipToParty();
        String localOrderType = salesOrderV1Entity.getLocalOrderType();
        String localItemCategory = salesOrderV1Entity.getLocalItemCategory();

        String salesHistoryId = salesOrderV1Entity.getSalesOrderNo()+salesOrderV1Entity.getSalesOrderItem();
        gdmSalesHistoryBo.setSalesHistoryId(salesHistoryId);
        gdmSalesHistoryBo.setActiveFCTERP(IConstant.VALUE.YES);

        gdmSalesHistoryBo.setCertaintyId(checkT2(localSalesOrg,localOrderType,localItemCategory));

        gdmSalesHistoryBo.setCurrencyId(checkT4(salesOrderV1Entity.getLocalSdItemCurrency()));

        gdmSalesHistoryBo.setCustomerId(checkT5(localShipToParty,localSalesOrg,salesOrderV1Entity.getLocalRequestedDate()));

        gdmSalesHistoryBo.setDueDate(checkT6(salesOrderV1Entity.getLocalRoute(),salesOrderV1Entity.getLocalRequestedDate()));

        gdmSalesHistoryBo.setFromDueDate(checkT6(salesOrderV1Entity.getLocalRoute(),salesOrderV1Entity.getLocalRequestedDate()));

        String locationId = salesOrderV1Entity.getSourceSystem()+IConstant.VALUE.UNDERLINE+salesOrderV1Entity.getLocalPlant();
        gdmSalesHistoryBo.setLocationId(locationId);

        gdmSalesHistoryBo.setOrderType(salesOrderV1Entity.getLocalOrderType());

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialNumber(salesOrderV1Entity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity){
            gdmSalesHistoryBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
        }

        gdmSalesHistoryBo.setQuantity(checkT9(salesOrderV1Entity));

        gdmSalesHistoryBo.setSalesUnit(checkT10(materialGlobalV1Entity.getLocalBaseUom()));

        resultObject.setBaseBo(gdmSalesHistoryBo);
        return resultObject;
    }

    private String checkT2(String localSalesOrg,String localOrderType,String localItemCategory){
        PlanCnsCertDeterEntity certDeterEntity = cnsCertDeterDao.getEntitiesWithConditions(localSalesOrg,localOrderType,localItemCategory);
        if (null != certDeterEntity){
            return certDeterEntity.getCertaintyKey();
        }else if(null != cnsCertDeterDao.getEntitiesWithSalesOrgAndItemCategory(localSalesOrg,localItemCategory)){
            return certDeterEntity.getCertaintyKey();
        }else if(null != cnsCertDeterDao.getEntitiesWithSalesOrgAndOrderType(localSalesOrg,localOrderType)){
            return certDeterEntity.getCertaintyKey();
        }
        return IConstant.VALUE.BASE;
    }

    private String checkT4(String localSdItemCurrency){
        EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(localSdItemCurrency);
        if (null != currencyV1Entity){
            return currencyV1Entity.getCurrencyCode();
        }
        return null;
    }

    private String checkT5(String localShipToParty,String localSalesOrg,String localRequestedDate){
        PlanCnsDemGrpAsgnEntity demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntitiesWithCustomerIdAndSalesOrganization(localShipToParty,localSalesOrg);
        if (null != demGrpAsgnEntity){
            return demGrpAsgnEntity.getDemandGroup();
        }else {
            ProjectOneKnvhEntity knvhEntity = knvhDao.getEntityWithConditions(localShipToParty,localSalesOrg,localRequestedDate);
            if (null != knvhEntity){
                demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerId(knvhEntity.getHkunnr());
                if (null != demGrpAsgnEntity){
                    return demGrpAsgnEntity.getDemandGroup();
                }else {
                    demGrpAsgnEntity = cnsDemGrpAsgnDao.getEntitiesWithCustomerIdAndSalesOrganization(knvhEntity.getKunnr(),localSalesOrg);
                    if (null != demGrpAsgnEntity) {
                        return demGrpAsgnEntity.getDemandGroup();
                    }
                }
            }

        }
        return null;
    }

    private String checkT6(String localRoute,String localRequestedDate){
        SimpleDateFormat sdf =   new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
        ProjectOneTvroEntity tvroEntity = tvroDao.getEntityWithRoute(localRoute);
        if (null != tvroEntity){
            String trazt = tvroEntity.getTrazt();
            try {
                Date traztFormat = sdf.parse(trazt);
                Date localRequestedDateFormat = sdf.parse(localRequestedDate);

                Long time = localRequestedDateFormat.getTime()-traztFormat.getTime();
                Date dueDate = new Date(time);
                return sdf.format(dueDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String checkT9(EDMSalesOrderV1Entity salesOrderV1Entity){
        PlanCnsOrdRejEntity cnsOrdRejEntity = cnsOrdRejDao.getEntityWithSalesOrgAndRejCd(salesOrderV1Entity.getLocalSalesOrg(),salesOrderV1Entity.getLocalRejReason());
        if (null != cnsOrdRejEntity){
            return IConstant.VALUE.ZERO;
        }else {
            try {
                int salesOrderQty = Integer.parseInt(salesOrderV1Entity.getSalesOrderQty());
                int localNumtoBase = Integer.parseInt(salesOrderV1Entity.getLocalNumtoBase());
                int localDentoBase = Integer.parseInt(salesOrderV1Entity.getLocalDentoBase());
                if (0!=salesOrderQty && 0!=localNumtoBase && 0!=localDentoBase){
                    return salesOrderQty*localNumtoBase/localDentoBase+"";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String checkT10(String localBaseUom){
        CnsPlanUnitEntity planUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUom);
        if (null != planUnitEntity){
            return planUnitEntity.getUnit();
        }
        return null;
    }
}

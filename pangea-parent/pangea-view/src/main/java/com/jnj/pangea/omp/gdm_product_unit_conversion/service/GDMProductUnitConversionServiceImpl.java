package com.jnj.pangea.omp.gdm_product_unit_conversion.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialAuomDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.omp.gdm_product_unit_conversion.bo.GDMProductUnitConversionBo;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GDMProductUnitConversionServiceImpl {

    private static GDMProductUnitConversionServiceImpl instance;

    private final DecimalFormat df = new DecimalFormat("0.0000000000");


    public static GDMProductUnitConversionServiceImpl getInstance() {
        if (instance == null) {
            instance = new GDMProductUnitConversionServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private EDMMaterialAuomDaoImpl edmMaterialAuomDao = EDMMaterialAuomDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<>();

        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        //J1
        List<PlanCnsMaterialPlanStatusEntity> planCnsMaterialPlanStatusEntityList = planCnsMaterialPlanStatusDao.getEntitiesWithLocalMaterialNumberAndSourceSystem(edmMaterialGlobalV1Entity.getLocalMaterialNumber(), edmMaterialGlobalV1Entity.getSourceSystem());
        if (null == planCnsMaterialPlanStatusEntityList || planCnsMaterialPlanStatusEntityList.size() == 0) {
            return resultObjectList;
        }

        PlanCnsMaterialPlanStatusEntity T1Entity = null;
        PlanCnsMaterialPlanStatusEntity T2Entity = null;
        PlanCnsMaterialPlanStatusEntity T3Entity = null;
        PlanCnsMaterialPlanStatusEntity T4Entity = null;
        for (PlanCnsMaterialPlanStatusEntity entity : planCnsMaterialPlanStatusEntityList) {
            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getActive())) {
                T1Entity = entity;
            }
            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getDpRelevant())) {
                T2Entity = entity;
            }

            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getSpRelevant())) {
                T3Entity = entity;
            }

            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getNoPlanRelevant())) {
                T4Entity = entity;
            }

        }

        //T1
        List<String> productIdList = new ArrayList<>();
        if (null != T1Entity && IConstant.VALUE.X.equalsIgnoreCase(T1Entity.getActive())) {
            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                productIdList.add(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            }

            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                productIdList.add(IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode());
            }
        }

        //J2,T5
        if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalMaterialNumber())) {
            List<EDMMaterialAuomV1Entity> edmMaterialAuomV1EntityList = edmMaterialAuomDao.getEntityWithSourceSystemAndLocalMaterialNum
                    (edmMaterialGlobalV1Entity.getSourceSystem(), edmMaterialGlobalV1Entity.getLocalMaterialNumber());
            if (null != edmMaterialAuomV1EntityList && edmMaterialAuomV1EntityList.size() > 0) {
                for (EDMMaterialAuomV1Entity edmMaterialAuomV1Entity : edmMaterialAuomV1EntityList) {

                    String factor = "";
                    List<PlanCnsPlanUnitEntity> planCnsPlanUnitEntityList = planCnsPlanUnitDao.getCnsPlanUnitEntityListWithSourceSystemAndLocalUom
                            (edmMaterialAuomV1Entity.getSourceSystem(), edmMaterialAuomV1Entity.getLocalAuom());
                    if (null == planCnsPlanUnitEntityList || planCnsPlanUnitEntityList.size() <= 0) {
                        continue;
                    }

                    for (PlanCnsPlanUnitEntity planCnsPlanUnitEntity : planCnsPlanUnitEntityList) {
                        for (String productId : productIdList) {
                            //J2
                            if (StringUtils.isNotEmpty(edmMaterialAuomV1Entity.getLocalDenominator())) {
                                Pattern pattern = Pattern.compile(IConstant.VALUE.PATTERN_DIGITAL);
                                if (pattern.matcher(edmMaterialAuomV1Entity.getLocalNumerator()).matches() && pattern.matcher(edmMaterialAuomV1Entity.getLocalDenominator()).matches()) {
                                    int localNumerator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalNumerator());
                                    int localDenominator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalDenominator());
                                    factor = String.valueOf(df.format((double) localDenominator / localNumerator));
                                }
                            }

                            //T5
                            String unit = planCnsPlanUnitEntity.getUnit();

                            if ((StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode()) && StringUtils.startsWithIgnoreCase(productId, IConstant.VALUE.LA_)) &&
                                    (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                            } else if  (((null != T3Entity && (IConstant.VALUE.X.equals(T3Entity.getSpRelevant()) || IConstant.VALUE.X.equals(T3Entity.getNoPlanRelevant())))
                                    || (null != T4Entity && (IConstant.VALUE.X.equals(T4Entity.getNoPlanRelevant())))) &&
                                    (IConstant.VALUE.SP1.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                            } else if ((null != T2Entity && IConstant.VALUE.X.equals(T2Entity.getDpRelevant())) &&
                                    (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                            } else {
                                //skip this record
                                continue;
                            }

                            String ActiveFCTERP = "";
                            String ActiveOPRERP = "";
                            //T2 ActiveFCTERP
                            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode()) &&  StringUtils.startsWithIgnoreCase(productId, IConstant.VALUE.LA_) &&
                                    (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) ||
                                            IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                                ActiveFCTERP = IConstant.VALUE.YES;
                            } else if ((null != T2Entity && IConstant.VALUE.X.equals(T2Entity.getDpRelevant())) &&
                                    (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                                ActiveFCTERP = IConstant.VALUE.YES;
                            } else {
                                ActiveFCTERP = IConstant.VALUE.NO;
                            }

                            //T3 ActiveOPRERP
                            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode()) && StringUtils.startsWithIgnoreCase(productId, IConstant.VALUE.LA_)) {
                                ActiveOPRERP = IConstant.VALUE.NO;
                            } else {
                                if ((null != T3Entity && (IConstant.VALUE.X.equals(T3Entity.getSpRelevant()) || IConstant.VALUE.X.equals(T3Entity.getNoPlanRelevant())))
                                        || (null != T4Entity && (IConstant.VALUE.X.equals(T4Entity.getNoPlanRelevant())))) {

                                    if (IConstant.VALUE.SP1.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                                        ActiveOPRERP = IConstant.VALUE.YES;
                                    } else {
                                        ActiveOPRERP = IConstant.VALUE.NO;
                                    }
                                } else {
                                    ActiveOPRERP = IConstant.VALUE.NO;
                                }
                            }
                            //T4
                            String Active = "";
                            if (IConstant.VALUE.YES.equals(ActiveFCTERP) || IConstant.VALUE.YES.equals(ActiveOPRERP)) {
                                Active = IConstant.VALUE.YES;
                            } else {
                                //skip this record
                                continue;
                            }

                            if (StringUtils.isNotBlank(unit)) {
                                ResultObject resultObject = new ResultObject();

                                GDMProductUnitConversionBo bo = new GDMProductUnitConversionBo();
                                bo.setProductId(productId);
                                bo.setUnitId(unit);
                                bo.setGdmProductUnitConversionId(productId + unit);
                                bo.setFactor(factor);
                                bo.setActiveFCTERP(ActiveFCTERP);
                                bo.setActiveOPRERP(ActiveOPRERP);
                                bo.setActive(Active);
                                //D1 Comments,leave Blank, example " "
                                bo.setComments("");
                                //D2 ActiveSOPERP , default "NO"
                                bo.setActiveSOPERP(IConstant.VALUE.NO);

                                resultObject.setBaseBo(bo);

                                resultObjectList.add(resultObject);
                            }
                        }
                    }
                }
            } else {
                return resultObjectList;
            }
        }
        return resultObjectList;
    }

    private FailData writeFailDataToRegion(EDMMaterialGlobalV1Entity materialGlobalV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_UNIT_CONVERSION);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(IConstant.VALUE.OMP);
        failData.setKey1(materialGlobalV1Entity.getLocalMaterialNumber());
        failData.setKey2("");
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}
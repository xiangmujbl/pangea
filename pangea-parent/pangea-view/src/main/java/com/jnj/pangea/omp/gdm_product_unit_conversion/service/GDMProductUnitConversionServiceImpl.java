package com.jnj.pangea.omp.gdm_product_unit_conversion.service;

import com.jnj.adf.grid.utils.LogUtil;
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

    private final DecimalFormat df = new DecimalFormat("0.00");


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
        List<GDMProductUnitConversionBo> gdmProductUnitConversionBoList = new ArrayList<>();
        List<PlanCnsMaterialPlanStatusEntity> planCnsMaterialPlanStatusEntityList = planCnsMaterialPlanStatusDao.getEntitiesWithLocalMaterialNumberAndSourceSystem(edmMaterialGlobalV1Entity.getLocalMaterialNumber(), edmMaterialGlobalV1Entity.getSourceSystem());
        if (null == planCnsMaterialPlanStatusEntityList || planCnsMaterialPlanStatusEntityList.size() == 0) {
            return resultObjectList;
        }

        PlanCnsMaterialPlanStatusEntity T1Entity = null;
        PlanCnsMaterialPlanStatusEntity T2Entity = null;
        PlanCnsMaterialPlanStatusEntity T3Entity = null;
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
        }

        //T1
        if (null != T1Entity && IConstant.VALUE.X.equalsIgnoreCase(T1Entity.getActive())) {

            GDMProductUnitConversionBo gdmProductUnitConversionBo = new GDMProductUnitConversionBo();
            gdmProductUnitConversionBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            gdmProductUnitConversionBoList.add(gdmProductUnitConversionBo);

            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {


                GDMProductUnitConversionBo gdmProductUnitConversionBo1 = new GDMProductUnitConversionBo();
                gdmProductUnitConversionBo1.setProductId(IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode());
                gdmProductUnitConversionBoList.add(gdmProductUnitConversionBo1);

            }
        }

        //J2,T5
        String factor = "";
        String unit = "";
        if (StringUtils.isNotEmpty(edmMaterialGlobalV1Entity.getLocalMaterialNumber()) && StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalBaseUom())) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithSourceSystemAndLocalMaterialNumAndLocalAuom(edmMaterialGlobalV1Entity.getSourceSystem(), edmMaterialGlobalV1Entity.getLocalMaterialNumber(), edmMaterialGlobalV1Entity.getLocalBaseUom());
            if (null != edmMaterialAuomV1Entity) {
                if (StringUtils.isNotEmpty(edmMaterialAuomV1Entity.getLocalDenominator())) {
                    Pattern pattern = Pattern.compile(IConstant.VALUE.PATTERN_DIGITAL);
                    if (pattern.matcher(edmMaterialAuomV1Entity.getLocalNumerator()).matches() && pattern.matcher(edmMaterialAuomV1Entity.getLocalDenominator()).matches()) {
                        int localNumerator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalNumerator());
                        int localDenominator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalDenominator());
                        factor = String.valueOf(df.format((float) localNumerator / localDenominator));
                    }
                }

                PlanCnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithSourceSystemAndLocalUom(edmMaterialAuomV1Entity.getSourceSystem(), edmMaterialAuomV1Entity.getLocalAuom());
                if (null != cnsPlanUnitEntity && StringUtils.isBlank(unit)) {
                    unit = cnsPlanUnitEntity.getUnit();
                }

                if (StringUtils.isBlank(unit)) {
                    return resultObjectList;
                }

            } else {
                return resultObjectList;
            }
        }


        String materialNumber = edmMaterialGlobalV1Entity.getMaterialNumber();
        //T2,T3
        String ActiveFCTERP = "";
        String ActiveOPRERP = "";
        if (StringUtils.isNotBlank(materialNumber)) {
            List<EDMMaterialAuomV1Entity> edmMaterialAuomV1EntityList = edmMaterialAuomDao.getEntityListWithMaterialNum(materialNumber);

            if (null != edmMaterialAuomV1EntityList && edmMaterialAuomV1EntityList.size() > 0) {
                for (EDMMaterialAuomV1Entity edmMaterialAuomV1Entity : edmMaterialAuomV1EntityList) {

                    PlanCnsPlanUnitEntity planCnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialAuomV1Entity.getLocalAuom());
                    if (null == planCnsPlanUnitEntity) {
                        ResultObject resultObject = new ResultObject();
                        FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, "E1", "No Enterprise UOM is maintained");
                        resultObject.setFailData(failData);
                        resultObjectList.add(resultObject);
                    } else {
                        //T2 ActiveFCTERP
                        if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                            //unit = planCnsPlanUnitEntity.getUnit();
                            if (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                                if (StringUtils.isBlank(ActiveFCTERP)) {
                                    ActiveFCTERP = IConstant.VALUE.YES;
                                }
                            }
                        } else {
                            if (null != T2Entity && IConstant.VALUE.X.equals(T2Entity.getDpRelevant())) {
                                // unit = planCnsPlanUnitEntity.getUnit();
                                if (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                                    if (StringUtils.isBlank(ActiveFCTERP)) {
                                        ActiveFCTERP = IConstant.VALUE.YES;
                                    }
                                }
                            }
                        }

                        //T3 ActiveOPRERP
                        if (null != T3Entity && IConstant.VALUE.X.equals(T3Entity.getSpRelevant())) {
                            if (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                                if (StringUtils.isBlank(ActiveOPRERP)) {
                                    ActiveOPRERP = IConstant.VALUE.YES;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (StringUtils.isBlank(ActiveFCTERP)) {
            ActiveFCTERP = IConstant.VALUE.NO;
        }
        if (StringUtils.isBlank(ActiveOPRERP)) {
            ActiveOPRERP = IConstant.VALUE.NO;
        }

        //T4
        String Active = "";
        if (IConstant.VALUE.YES.equals(ActiveFCTERP) || IConstant.VALUE.YES.equals(ActiveOPRERP)) {
            Active = IConstant.VALUE.YES;
        } else {
            //skip this record
            return resultObjectList;
        }

        //T5
//        String unit = "";
//        if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getSourceSystem())) {
//            List<EDMMaterialAuomV1Entity> materialAuomV1EntityList = edmMaterialAuomDao.getEntityWithSourceSystemAndLocalMaterialNum(edmMaterialGlobalV1Entity.getSourceSystem(), edmMaterialGlobalV1Entity.getLocalMaterialNumber());
//            if (null != materialAuomV1EntityList && materialAuomV1EntityList.size() > 0) {
//                for (EDMMaterialAuomV1Entity materialAuomV1Entity : materialAuomV1EntityList) {
//                    PlanCnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithSourceSystemAndLocalUom(materialAuomV1Entity.getSourceSystem(), materialAuomV1Entity.getLocalAuom());
//                    if (null != cnsPlanUnitEntity && StringUtils.isBlank(unit)) {
//                        unit = cnsPlanUnitEntity.getUnit();
//                    }
//                }
//            } else {
//                LogUtil.getCoreLog().info(" materialAuomV1EntityList is null and edmMaterialGlobalV1Entity getLocalMaterialNumber = = " + edmMaterialGlobalV1Entity.getLocalMaterialNumber());
//                return resultObjectList;
//            }
//        } else {
//            LogUtil.getCoreLog().info(" edmMaterialGlobalV1Entity getSourceSystem is null and edmMaterialGlobalV1Entity getLocalMaterialNumber = = " + edmMaterialGlobalV1Entity.getLocalMaterialNumber());
//            return resultObjectList;
//        }
//        if (StringUtils.isBlank(unit)) {
//            return resultObjectList;
//        }

        //Set common field
        for (GDMProductUnitConversionBo gdmProductUnitConversionBo : gdmProductUnitConversionBoList) {
            ResultObject resultObject = new ResultObject();

            gdmProductUnitConversionBo.setUnitId(unit);
            //C1 CONCATENATE 'GDMProductUnitConversion-ProductID', 'GDMProductUnitConversion-UnitId'
            gdmProductUnitConversionBo.setGdmProductUnitConversionId(gdmProductUnitConversionBo.getProductId() + gdmProductUnitConversionBo.getUnitId());
            gdmProductUnitConversionBo.setFactor(factor);
            gdmProductUnitConversionBo.setActiveFCTERP(ActiveFCTERP);
            gdmProductUnitConversionBo.setActiveOPRERP(ActiveOPRERP);
            gdmProductUnitConversionBo.setActive(Active);
            //D1 Comments,leave Blank, example " "
            gdmProductUnitConversionBo.setComments("");
            //D2 ActiveSOPERP , default "NO"
            gdmProductUnitConversionBo.setActiveSOPERP(IConstant.VALUE.NO);

            resultObject.setBaseBo(gdmProductUnitConversionBo);
            resultObjectList.add(resultObject);
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
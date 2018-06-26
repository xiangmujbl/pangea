package com.jnj.pangea.omp.product_detail.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.omp.product_detail.bo.OMPProductDetailBo;
import org.apache.commons.lang3.StringUtils;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OMPProductDetailServiceImpl {

    private static OMPProductDetailServiceImpl instance;

    public static OMPProductDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProductDetailServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        Map<String, Object> extraParam = (HashMap) o2;

        List<OMPProductDetailBo> BoList = new ArrayList<OMPProductDetailBo>();

        //rules J1
        LogUtil.getCoreLog().info("-----------------------{}---------------",materialGlobalV1Entity.getPrimaryPlanningCode());
        if(StringUtils.isBlank(materialGlobalV1Entity.getPrimaryPlanningCode())){
        	ResultObject resultObject = new ResultObject();
        	FailData failData = new FailData();
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_DETAIL);
            failData.setErrorCode("J1");
            failData.setSourceSystem(IConstant.VALUE.CONS_LATAM);
            failData.setKey1(materialGlobalV1Entity.getSourceSystem());
            failData.setKey2(materialGlobalV1Entity.getLocalMaterialNumber());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            LogUtil.getCoreLog().info("-----------------------{}---------------",materialGlobalV1Entity.getLocalMaterialNumber());
            failData.setErrorValue("PPC does not exist for localMaterialNumber in edm material");
            
			resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        

        List<PlanCnsMaterialPlanStatusEntity> cnsMaterialPlanStatusEntityList = cnsMaterialPlanStatusDao.getEntitiesWithLocalMaterialNumberAndSourceSystem(localMaterialNumber, IConstant.VALUE.CONS_LATAM);
        if (!cnsMaterialPlanStatusEntityList.isEmpty()) {

            PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusEntityList.get(0);

            if (null != cnsMaterialPlanStatusEntity && (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())
                    || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant())
                    || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant()))) {

                String sourceSystem = materialGlobalV1Entity.getSourceSystem();
                if (StringUtils.isNotEmpty(sourceSystem)) {
                    PlanCnsPlanParameterEntity cnsPlanParameterEntity = cnsPlanParameterDao.getEntityWithAttributeAndDataObject(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.SEND_TO_OMP);

                    //rules T1
                    getFieldWithT1(materialGlobalV1Entity, cnsMaterialPlanStatusEntity, cnsMaterialPlanStatusEntityList, cnsPlanParameterEntity, BoList, extraParam);
                }

                if (BoList == null || BoList.size() == 0) {
                    return resultObjectList;
                }

                for (OMPProductDetailBo bo : BoList) {
                    ResultObject resultObject = new ResultObject();

                    //rules T4 and T5
                    if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())) {
                        bo.setCLASS(IConstant.VALUE.PGA);
                        bo.setDescription(IConstant.VALUE.PANGEA);
                    }
                    
//                    
//                    UUID uuid = UUID.randomUUID();
//                    String randomUUIDString = uuid.toString();
//                    bo.setProductDetailId(randomUUIDString);

                    //rules D1
                    bo.setActiveSOPERP(IConstant.VALUE.NO);

                    resultObject.setBaseBo(bo);
                    resultObjectList.add(resultObject);
                }
            }
        }

        return resultObjectList;
    }

    /**
     * rules T1
     *
     * @param materialGlobalV1Entity
     * @param cnsMaterialPlanStatusEntity
     * @param cnsMaterialPlanStatusEntityList
     * @param cnsPlanParameterEntity
     * @param boList
     * @param extraParam
     * @return
     */
    private void getFieldWithT1(EDMMaterialGlobalV1Entity materialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity,
                                List<PlanCnsMaterialPlanStatusEntity> cnsMaterialPlanStatusEntityList, PlanCnsPlanParameterEntity cnsPlanParameterEntity,
                                List<OMPProductDetailBo> boList, Map<String, Object> extraParam) {

        boolean checkDpRelevant = false;
        boolean checkSpRelevantAndNoPlanRelevant = false;

        // one data
        if (StringUtils.isNotEmpty(materialGlobalV1Entity.getPrimaryPlanningCode())) {

            OMPProductDetailBo productDetailBo1 = new OMPProductDetailBo();
            productDetailBo1.setName(IConstant.VALUE.LATAM_SKU);

            if (isNumeric(materialGlobalV1Entity.getLocalMaterialNumber())){

                productDetailBo1.setValue(materialGlobalV1Entity.getLocalMaterialNumber().replaceFirst("^0*", ""));
            } else {

                productDetailBo1.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            }
            
            productDetailBo1.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT +
                    IConstant.VALUE.LATAM_SKU + IConstant.VALUE.BACK_SLANT + productDetailBo1.getValue());
            
        //    extraParam.put(productDetailBo1.getProductDetailId(), String.valueOf(extraParam.size() + 1));
            
            productDetailBo1.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());

            for (PlanCnsMaterialPlanStatusEntity materialGlobalV1EntityTmp : cnsMaterialPlanStatusEntityList) {

                if (IConstant.VALUE.X.equals(materialGlobalV1EntityTmp.getDpRelevant())) {

                    checkDpRelevant = true;
                    break;
                }
            }

            for (PlanCnsMaterialPlanStatusEntity materialGlobalV1EntityTmp : cnsMaterialPlanStatusEntityList) {

                if (IConstant.VALUE.X.equals(materialGlobalV1EntityTmp.getSpRelevant()) || IConstant.VALUE.X.equals(materialGlobalV1EntityTmp.getNoPlanRelevant())) {

                    checkSpRelevantAndNoPlanRelevant = true;
                    break;
                }
            }

            if (checkDpRelevant) {

                productDetailBo1.setActiveFCTERP(IConstant.VALUE.YES);
            } else {

                productDetailBo1.setActiveFCTERP(IConstant.VALUE.NO);
            }

            if (checkSpRelevantAndNoPlanRelevant) {

                productDetailBo1.setActiveOPRERP(IConstant.VALUE.YES);
            } else {

                productDetailBo1.setActiveOPRERP(IConstant.VALUE.NO);
            }

            boList.add(productDetailBo1);
        }



        // two and three data
        if (checkDpRelevant && StringUtils.isNotEmpty(materialGlobalV1Entity.getLocalDpParentCode())
                && cnsPlanParameterEntity != null && StringUtils.isNotEmpty(cnsPlanParameterEntity.getParameterValue())) {
            OMPProductDetailBo productDetailBo2 = new OMPProductDetailBo();
            
           // extraParam.put(productDetailBo2.getProductDetailId(), String.valueOf(extraParam.size() + 1));
            
            productDetailBo2.setProductId(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo2.setName(IConstant.VALUE.LATAM_SKU);

            if (isNumeric(materialGlobalV1Entity.getLocalMaterialNumber())){

                productDetailBo2.setValue(materialGlobalV1Entity.getLocalMaterialNumber().replaceFirst("^0*", ""));
            } else {

                productDetailBo2.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            }
            
            productDetailBo2.setProductDetailId(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode() +
                    IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_SKU + IConstant.VALUE.BACK_SLANT
                    + productDetailBo2.getValue());

            productDetailBo2.setActiveFCTERP(IConstant.VALUE.YES);
            productDetailBo2.setActiveOPRERP(IConstant.VALUE.NO);
            boList.add(productDetailBo2);

           // extraParam.put(productDetailBo3.getProductDetailId(), String.valueOf(extraParam.size() + 1));
            
            OMPProductDetailBo productDetailBo3 = new OMPProductDetailBo();
            productDetailBo3.setName(IConstant.VALUE.LATAM_ROOT);
            productDetailBo3.setValue(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo3.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA +
                    IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_ROOT + IConstant.VALUE.BACK_SLANT + productDetailBo3.getValue());
            
            productDetailBo3.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            productDetailBo3.setActiveFCTERP(IConstant.VALUE.YES);
            productDetailBo3.setActiveOPRERP(IConstant.VALUE.NO);
            boList.add(productDetailBo3);

        }
    }
    /**
     * check Numeric
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}

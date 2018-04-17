package com.jnj.pangea.omp.product_detail.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_detail.bo.OMPProductDetailBo;

import java.util.ArrayList;
import java.util.List;

public class OMPProductDetailServiceImpl{

    private static OMPProductDetailServiceImpl instance;

    public static OMPProductDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProductDetailServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        List<OMPProductDetailBo> BoList = new ArrayList<OMPProductDetailBo>();

        if (materialGlobalV1Entity==null){
            return  new ArrayList<ResultObject>();
        }
         //rules J1
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        if ("".equals(localMaterialNumber)){
            return  new ArrayList<ResultObject>();
        }
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndsourceSystem(localMaterialNumber,IConstant.VALUE.CONS_LATAM);
        if (cnsMaterialPlanStatusEntity==null){
            return  new ArrayList<ResultObject>();
        }
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(materialGlobalV1Entity.getSourceSystem());
        if (edmSourceSystemV1Entity==null){
            return  new ArrayList<ResultObject>();
        }

        //rules T1
        List<OMPProductDetailBo> boList = getFieldWithT1(materialGlobalV1Entity, cnsMaterialPlanStatusEntity, edmSourceSystemV1Entity, BoList);
        if (boList==null||boList.size()==0){
            return resultObjectList;
        }

        for (OMPProductDetailBo bo:boList){
            ResultObject resultObject=new ResultObject();
            //rules T2
            if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant()) && (edmSourceSystemV1Entity.getLocalSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode()).equals(bo.getProductId()) ){
                bo.setActiveFCTERP(IConstant.VALUE.YES);
            }else{
                bo.setActiveFCTERP(IConstant.VALUE.NO);
            }

            //rules T3
            if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant())){
                bo.setActiveOPRERP(IConstant.VALUE.YES);
            }else{
                bo.setActiveOPRERP(IConstant.VALUE.NO);
            }

            //rules T4 and T5
            if (IConstant.VALUE.PROJECT_ONE.equals(materialGlobalV1Entity.getSourceSystem())){
                bo.setCLASS(IConstant.VALUE.PGA);
                bo.setDescription(IConstant.VALUE.PANGEA);
            }

            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }

        return resultObjectList;
    }

    /**
     * rules T1
     * @param materialGlobalV1Entity
     * @param cnsMaterialPlanStatusEntity
     * @param edmSourceSystemV1Entity
     * @param boList
     * @return
     */
    private List<OMPProductDetailBo> getFieldWithT1(EDMMaterialGlobalV1Entity materialGlobalV1Entity,PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity, EDMSourceSystemV1Entity edmSourceSystemV1Entity, List<OMPProductDetailBo> boList) {
        OMPProductDetailBo productDetailBo=new OMPProductDetailBo();
        productDetailBo.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
        productDetailBo.setName(IConstant.VALUE.LATAM_SKU);
        productDetailBo.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
        productDetailBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
        boList.add(productDetailBo);


        if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())){
            OMPProductDetailBo productDetailBo1=new OMPProductDetailBo();
            productDetailBo1.setProductDetailId(edmSourceSystemV1Entity.getLocalSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
            productDetailBo1.setName(IConstant.VALUE.LATAM_SKU);
            productDetailBo1.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            productDetailBo1.setProductId(edmSourceSystemV1Entity.getSourceSystem()+"_"+materialGlobalV1Entity.getPrimaryPlanningCode());
            boList.add(productDetailBo1);
        }

        if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem()) && IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())){
            OMPProductDetailBo productDetailBo1=new OMPProductDetailBo();
            productDetailBo1.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_ROOT);
            productDetailBo1.setName(IConstant.VALUE.LATAM_ROOT);
            productDetailBo1.setValue(edmSourceSystemV1Entity.getSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo1.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());

            OMPProductDetailBo productDetailBo2=new OMPProductDetailBo();
            productDetailBo2.setProductDetailId(edmSourceSystemV1Entity.getLocalSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_ROOT);
            productDetailBo2.setName(IConstant.VALUE.LATAM_ROOT);
            productDetailBo2.setValue(edmSourceSystemV1Entity.getSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo2.setProductId(edmSourceSystemV1Entity.getSourceSystem()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode());

            boList.add(productDetailBo1);
            boList.add(productDetailBo2);
        }

        if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())&&(IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant())||IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant()))&&(!"".equals(materialGlobalV1Entity.getLocalManufacturingTechnology()))){
            OMPProductDetailBo productDetailBo1=new OMPProductDetailBo();
            productDetailBo1.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_TECH);
            productDetailBo1.setName(IConstant.VALUE.LATAM_TECH);
            productDetailBo1.setValue(materialGlobalV1Entity.getLocalManufacturingTechnology());
            productDetailBo1.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());

            boList.add(productDetailBo1);
        }

        return  boList;
    }

}

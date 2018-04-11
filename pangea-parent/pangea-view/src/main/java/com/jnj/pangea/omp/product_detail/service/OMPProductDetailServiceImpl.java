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

public class OMPProductDetailServiceImpl implements ICommonService {

    private static OMPProductDetailServiceImpl instance;

    public static OMPProductDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProductDetailServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        List<OMPProductDetailBo> BoList = new ArrayList<OMPProductDetailBo>();

        if (materialGlobalV1Entity==null){
            return  new ArrayList<ResultObject>();
        }
         //rules J1
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumber(materialGlobalV1Entity.getLocalMaterialNumber());
        if (cnsMaterialPlanStatusEntity==null){
            return  new ArrayList<ResultObject>();
        }
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(materialGlobalV1Entity.getSourceSystem());
        if (edmSourceSystemV1Entity==null){
            return  new ArrayList<ResultObject>();
        }

        //rules T1
        getFieldWithT1(materialGlobalV1Entity,cnsMaterialPlanStatusEntity,edmSourceSystemV1Entity,BoList);


        return resultObjectList;
    }

    private void getFieldWithT1(EDMMaterialGlobalV1Entity materialGlobalV1Entity,PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity, EDMSourceSystemV1Entity edmSourceSystemV1Entity, List<OMPProductDetailBo> boList) {
            OMPProductDetailBo productDetailBo=new OMPProductDetailBo();
            productDetailBo.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
            productDetailBo.setName(IConstant.VALUE.LATAM_SKU);
            productDetailBo.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            productDetailBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            boList.add(productDetailBo);
        if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())){
            OMPProductDetailBo productDetailBo1=new OMPProductDetailBo();
            productDetailBo1.setProductDetailId(edmSourceSystemV1Entity.getLocalSourceSystem()+"_"+materialGlobalV1Entity.getLocalDpParentCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
            productDetailBo1.setName(IConstant.VALUE.LATAM_SKU);
            productDetailBo1.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            productDetailBo1.setProductId(edmSourceSystemV1Entity.getSourceSystem()+"_"+materialGlobalV1Entity.getPrimaryPlanningCode());
            boList.add(productDetailBo1);
        }


    }
}

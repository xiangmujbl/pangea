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
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        OMPProductDetailBo productDetailBo = new OMPProductDetailBo();
        if (materialGlobalV1Entity==null){
            return  resultObject;
        }
         //rules J1
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumber(materialGlobalV1Entity.getLocalMaterialNumber());
        if (cnsMaterialPlanStatusEntity==null){
            return  resultObject;
        }
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(materialGlobalV1Entity.getSourceSystem());
        if (edmSourceSystemV1Entity==null){
            return  resultObject;
        }

        //rules T1
        getFieldWithT1(materialGlobalV1Entity,cnsMaterialPlanStatusEntity,edmSourceSystemV1Entity,productDetailBo);

        resultObject.setBaseBo(productDetailBo);
        return resultObject;
    }

    private void getFieldWithT1(EDMMaterialGlobalV1Entity materialGlobalV1Entity,PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity, EDMSourceSystemV1Entity edmSourceSystemV1Entity, OMPProductDetailBo productDetailBo) {
        if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())){
            productDetailBo.setProductDetailId(edmSourceSystemV1Entity.getLocalSourceSystem()+"_"+materialGlobalV1Entity.getLocalDpParentCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
            productDetailBo.setName(IConstant.VALUE.LATAM_SKU);
            productDetailBo.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            productDetailBo.setProductId(edmSourceSystemV1Entity.getSourceSystem()+"_"+materialGlobalV1Entity.getPrimaryPlanningCode());
            return;
        }
        productDetailBo.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode()+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.PGA+IConstant.VALUE.BACK_SLANT+IConstant.VALUE.LATAM_SKU);
        productDetailBo.setName(IConstant.VALUE.LATAM_SKU);
        productDetailBo.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
        productDetailBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());

    }
}

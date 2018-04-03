package com.jnj.pangea.omp.gdm_product.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProductFamilyV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMProductFamilyV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMFormV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMFormV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCategoryV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCategoryV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSubBrandV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSubBrandV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMBrandV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMFranchiseV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMFranchiseV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMGlobalBaseUnitV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMGlobalBaseUnitV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product.bo.OMPGdmProductBo;

public class OMPGdmProductServiceImpl implements ICommonService {

    private static OMPGdmProductServiceImpl instance;

    public static OMPGdmProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMProductFamilyV1DaoImpl productFamilyV1Dao = EDMProductFamilyV1DaoImpl.getInstance();
    private EDMFormV1DaoImpl formV1Dao = EDMFormV1DaoImpl.getInstance();
    private EDMCategoryV1DaoImpl categoryV1Dao = EDMCategoryV1DaoImpl.getInstance();
    private EDMSubBrandV1DaoImpl subBrandV1Dao = EDMSubBrandV1DaoImpl.getInstance();
    private EDMBrandV1DaoImpl brandV1Dao = EDMBrandV1DaoImpl.getInstance();
    private EDMFranchiseV1DaoImpl franchiseV1Dao = EDMFranchiseV1DaoImpl.getInstance();
    private EDMGlobalBaseUnitV1DaoImpl globalBaseUnitV1Dao = EDMGlobalBaseUnitV1DaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
        // TODO add logic

        resultObject.setBaseBo(gdmProductBo);
        return resultObject;
    }
}

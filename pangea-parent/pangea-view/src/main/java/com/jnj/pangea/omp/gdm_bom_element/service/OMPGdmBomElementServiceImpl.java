package com.jnj.pangea.omp.gdm_bom_element.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMatlBomEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMBomItemDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMBomHdrDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlProdVersnEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMatlProdVersnDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlMfgRtngEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMatlMfgRtngDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgRtngItmNdeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgRtngItmDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_bom_element.bo.OMPGdmBomElementBo;

public class OMPGdmBomElementServiceImpl implements ICommonService {

    private static OMPGdmBomElementServiceImpl instance;

    public static OMPGdmBomElementServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBomElementServiceImpl();
        }
        return instance;
    }

    private EDMMaterialPlantV1DaoImpl materialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
    private EDMBomItemDaoImpl bomItemDao = EDMBomItemDaoImpl.getInstance();
    private EDMBomHdrDaoImpl bomHdrDao = EDMBomHdrDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl matlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private EDMMatlMfgRtngDaoImpl matlMfgRtngDao = EDMMatlMfgRtngDaoImpl.getInstance();
    private EDMMfgRtngItmNdeDaoImpl mfgRtngItmNdeDao = EDMMfgRtngItmNdeDaoImpl.getInstance();
    private EDMMfgRtngItmDaoImpl mfgRtngItmDao = EDMMfgRtngItmDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;

        OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
        //J1


        resultObject.setBaseBo(gdmBomElementBo);
        return resultObject;
    }


}

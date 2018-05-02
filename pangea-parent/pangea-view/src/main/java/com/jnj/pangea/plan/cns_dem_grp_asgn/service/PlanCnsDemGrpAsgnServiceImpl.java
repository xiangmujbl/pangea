package com.jnj.pangea.plan.cns_dem_grp_asgn.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ems.EmsCustomerGroupEntity;
import com.jnj.pangea.common.service.ICommonService;
//import com.jnj.pangea.plan.cns_dem_grp_asgn.bo.PlanCnsDemGrpAsgnBo;

public class PlanCnsDemGrpAsgnServiceImpl implements ICommonService {

    private static PlanCnsDemGrpAsgnServiceImpl instance;

    public static PlanCnsDemGrpAsgnServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EmsCustomerGroupEntity customerGroupEntity = (EmsCustomerGroupEntity) o;

//        PlanCnsDemGrpAsgnBo cnsDemGrpAsgnBo = new PlanCnsDemGrpAsgnBo();
//        cnsDemGrpAsgnBo.setSourceSystem(customerGroupEntity.getSourceSystem());
//        cnsDemGrpAsgnBo.setCustomerShipTo(customerGroupEntity.getCustomerShipTo());
//        cnsDemGrpAsgnBo.setSubFranchise(customerGroupEntity.getSubFranchise());
//        cnsDemGrpAsgnBo.setGroup(customerGroupEntity.getPlanningCustomerGroup());
//        cnsDemGrpAsgnBo.setFromDate(customerGroupEntity.getFromDate());
//        cnsDemGrpAsgnBo.setToDate(customerGroupEntity.getToDate());
//
//        resultObject.setBaseBo(cnsDemGrpAsgnBo);
        return resultObject;
    }
}

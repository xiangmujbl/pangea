package com.jnj.pangea.omp.gdm_pos.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPosEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_pos.bo.OMPGdmPosBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmPosServiceImpl implements ICommonService {

    private static OMPGdmPosServiceImpl instance;

    public static OMPGdmPosServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmPosServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private PlanCnsDemGrpAsgnDaoImpl cnsDemGrpAsgnDao = PlanCnsDemGrpAsgnDaoImpl.getInstance();
    private ProjectOneKnvhDaoImpl knvhDao = ProjectOneKnvhDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsDpPosEntity cnsDpPosEntity = (PlanCnsDpPosEntity) o;

        OMPGdmPosBo gdmPosBo = new OMPGdmPosBo();

        String customer = cnsDpPosEntity.getCustomer();

        // J1,J2,J3,T1
        if (StringUtils.isNotEmpty(customer)) {

            PlanCnsDemGrpAsgnEntity planCnsDemGrpAsgnEntity = cnsDemGrpAsgnDao.getEntityWithCustomerShipTo(customer);
            if (null != planCnsDemGrpAsgnEntity) {
                String group = planCnsDemGrpAsgnEntity.getGroup();
                if (StringUtils.isNotEmpty(group)) {

                } else {

                    resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP, IConstant.FAILED.INTERFACE_ID.GDM_UNIT_EVOL, IConstant.FAILED.ERROR_CODE.C1,
                            "Unable to Find the group", "omp", cnsDpPosEntity.getLocalMaterial(),
                            cnsDpPosEntity.getCustomer(),cnsDpPosEntity.getYearMonth()));
                }
            }
         }

        resultObject.setBaseBo(gdmPosBo);
        return resultObject;
    }
}

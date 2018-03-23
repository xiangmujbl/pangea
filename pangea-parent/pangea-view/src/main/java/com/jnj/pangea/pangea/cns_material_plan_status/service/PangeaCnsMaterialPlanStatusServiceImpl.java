package com.jnj.pangea.pangea.cns_material_plan_status.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.*;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFZEnterprisePlants;
import com.jnj.pangea.common.entity.plan.CnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.pangea.cns_material_plan_status.bo.PangeaCnsMaterialPlanStatusBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PangeaCnsMaterialPlanStatusServiceImpl implements ICommonService {

    private static PangeaCnsMaterialPlanStatusServiceImpl instance;

    public static PangeaCnsMaterialPlanStatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new PangeaCnsMaterialPlanStatusServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private ProjectOneT001KDaoImpl t001KDao = ProjectOneT001KDaoImpl.getInstance();
    private ProjectOneT001DaoImpl t001Dao = ProjectOneT001DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;

        PangeaCnsMaterialPlanStatusBo materialPlanStatusBo = new PangeaCnsMaterialPlanStatusBo();

        // T1
        materialPlanStatusBo.setSourceSystem(getFieldWithT1());


        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private String getFieldWithT1() {
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemEntity) {
            return sourceSystemEntity.getSourceSystem();
        }
        return "";
    }

//    "Get  material_global_v1 where
//    localMaterialType IN ParameterValues with below extraction (  /plan/cns_plan_parameter-sourceSystem = 'CONS_LATAM'
//                                                                  /plan/cns_plan_parameter-dataObject = 'cns_material_plan_status')
//                                                                  /plan/cns_plan_parameter-attribute IN 'DPRelevant' AND 'SPRelevant')
//                                                                  /plan/cns_plan_parameter-parameter = 'MaterialType') AND
//    flagForDeletion <> 'X'
//    If no records found, skip entry"

//    "Check material_plant_v1 where localPlant = material_global_v1-localMaterialNumber werks IN ParameterValues with below extraction
//            (        /plan/cns_plan_parameter-sourceSystem = 'CONS_LATAM'
//                    /plan/cns_plan_parameter-dataObject = 'cns_material_plan_status')
//                    /plan/cns_plan_parameter-attribute = 'SPRelevant')
//                    /plan/cns_plan_parameter-parameter = 'Plant') AND
//    localMRPType IN (/plan/cns_plan_parameter-sourceSystem = 'CONS_LATAM'
//                    /plan/cns_plan_parameter-dataObject = 'cns_material_plan_status'
//                    /plan/cns_plan_parameter-attribute = 'SPRelevant'
//                    /plan/cns_plan_parameter-parameter = 'MRPType'
//                    /plan/cns_plan_parameter-inclExcl = 'I')
//    If return holds good, set X, Else leave Blank"

//    Get material_global_v1-localDpParentCode where material_global_v1-localMaterialNumber = material_plant_v1-localMaterialNumber


    private boolean checkWithT123() {

        List<CnsPlanParameterEntity> t1DpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);
        List<CnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);


        return true;
    }


    private FailData checkT1(EMSFZEnterprisePlants enterprisePlants, String sourceSystem) {
        FailData failData = null;
        if (StringUtils.isEmpty(sourceSystem) || IConstant.VALUE.EMS.equals(sourceSystem)) {
            failData = new FailData();
            failData.setErrorCode("T1");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMPlant");
            failData.setSourceSystem("project_one");
            failData.setKey1(enterprisePlants.getzPlantSourceSystem());
            failData.setKey2(enterprisePlants.getzPlant());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
        }
        return failData;
    }

}

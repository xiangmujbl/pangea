package com.jnj.pangea.plan.cns_root_description.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanUserOverrideDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanUserOverrideEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_root_description.bo.PlanCnsRootDescriptionBo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanCnsRootDescriptionServiceImpl implements ICommonService {

    private static PlanCnsRootDescriptionServiceImpl instance;

    public static PlanCnsRootDescriptionServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsRootDescriptionServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanUserOverrideDaoImpl userOverrideDao = PlanUserOverrideDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        Map<String, Object> extraParam = (HashMap) o2;

        PlanCnsRootDescriptionBo cnsRootDescriptionBo = new PlanCnsRootDescriptionBo();

        // rules R1
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        if (StringUtils.isNotEmpty(localMaterialNumber)) {

            PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumber(localMaterialNumber);
            if (null != cnsMaterialPlanStatusEntity) {

                String localParentCode = cnsMaterialPlanStatusEntity.getLocalParentCode();
                if (StringUtils.isNotEmpty(localParentCode) && !extraParam.containsKey(localParentCode)) {

                    cnsRootDescriptionBo.setLocalDpParentCode(cnsMaterialPlanStatusEntity.getLocalParentCode());

                    // rules T1
                    EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
                    if (null != sourceSystemV1Entity) {
                        cnsRootDescriptionBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());

                        // ovrRootDesc mapping
                        cnsRootDescriptionBo.setOvrRootDesc(setOvrRootDesc(sourceSystemV1Entity, localParentCode));
                    }

                    // rules R2
                    List<EDMMaterialGlobalV1Entity> materialGlobalV1EntityList = edmMaterialGlobalV1Dao.getCloneEntitiesWithLocalDpParentCode(localParentCode);
                    if (!materialGlobalV1EntityList.isEmpty()) {
                        cnsRootDescriptionBo.setRootDesc(materialGlobalV1EntityList.get(0).getRefDescription());
                    }

                    resultObject.setBaseBo(cnsRootDescriptionBo);
                }

            }
        }
        return resultObject;
    }

    private String setOvrRootDesc (EDMSourceSystemV1Entity sourceSystemV1Entity, String localParentCode) {

        String ovrRootDesc = "";
        if (StringUtils.isNotEmpty(sourceSystemV1Entity.getSourceSystem())) {
            PlanUserOverrideEntity userOverrideEntity = userOverrideDao.getEntityWithKey1AndKey2(IConstant.REGION.PLAN_CNS_ROOT_DESCRIPTION,
                    IConstant.VALUE.PROJECT_ONE, sourceSystemV1Entity.getSourceSystem() ,localParentCode);

            if (null != userOverrideEntity) {

                ovrRootDesc = userOverrideEntity.getValue1();
            }
        }
        return  ovrRootDesc;
    }
}

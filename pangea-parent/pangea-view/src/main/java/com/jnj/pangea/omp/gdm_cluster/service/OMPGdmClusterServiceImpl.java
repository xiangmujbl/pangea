package com.jnj.pangea.omp.gdm_cluster.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_cluster.bo.OMPGdmClusterBo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OMPGdmClusterServiceImpl implements ICommonService {

    private static OMPGdmClusterServiceImpl instance;

    public static OMPGdmClusterServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmClusterServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsClustersEntity cnsClustersEntity = (PlanCnsClustersEntity) o;
        Map<String, Object> extraParam = (HashMap) o2;

        OMPGdmClusterBo gdmClusterBo = new OMPGdmClusterBo();

        if (StringUtils.isNotEmpty(cnsClustersEntity.getSourceSystem())) {
            List<PlanCnsPlanParameterEntity> cnsPlanParameterEntityList = cnsPlanParameterDao.getEntitiesWithSourceSystem(cnsClustersEntity.getSourceSystem());
            if (cnsPlanParameterEntityList.size() > 0) {
                String extraParamKey = cnsClustersEntity.getSourceSystem() + cnsClustersEntity.getCluster();
                if (!extraParam.containsKey(extraParamKey)) {
                    extraParam.put(extraParamKey, null);
                    gdmClusterBo.setClusterId(extraParamKey);
                    gdmClusterBo.setActiveFCTERP(IConstant.VALUE.YES);
                    gdmClusterBo.setClusterDescription(cnsClustersEntity.getClusterDesc());
                    resultObject.setBaseBo(gdmClusterBo);
                }
            }
        }
        return resultObject;
    }
}

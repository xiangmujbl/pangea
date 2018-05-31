package com.jnj.pangea.omp.gdm_subcluster.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsSubClusterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_subcluster.bo.OMPGdmSubClusterBo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OMPGdmSubClusterServiceImpl implements ICommonService{

    private static OMPGdmSubClusterServiceImpl instance;

    public static OMPGdmSubClusterServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmSubClusterServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsClustersEntity cnsClustersEntity = (PlanCnsClustersEntity) o;
        Map<String, Object> extraParam = (HashMap) o2;

        OMPGdmSubClusterBo gdmSubClusterBo = new OMPGdmSubClusterBo();

        gdmSubClusterBo.setSubClusterId(cnsClustersEntity.getSubCluster());
        gdmSubClusterBo.setDescription(cnsClustersEntity.getSubClusterDesc());
        gdmSubClusterBo.setClusterId(cnsClustersEntity.getCluster());

        resultObject.setBaseBo(gdmSubClusterBo);

        return resultObject;
    }
}

package com.jnj.pangea.omp.gdm_cluster.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_cluster.bo.OMPGdmClusterBo;

public class OMPGdmClusterServiceImpl implements ICommonService {

    private static OMPGdmClusterServiceImpl instance;

    public static OMPGdmClusterServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmClusterServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsClustersEntity cnsClustersEntity = (PlanCnsClustersEntity) o;

        OMPGdmClusterBo gdmClusterBo = new OMPGdmClusterBo();
        if (null==cnsClustersEntity || "".equals(cnsClustersEntity.getCluster())||"".equals(cnsClustersEntity.getCountryId())||"".equals(cnsClustersEntity.getSubCluster())){
            FailData failData = new FailData();
            failData.setErrorCode("C1");
            failData.setErrorValue("All Key fields not Exist");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("OMPGdmCluster");
            failData.setSourceSystem("omp");
            failData.setKey1(cnsClustersEntity.getCluster());
            failData.setKey2(cnsClustersEntity.getCountryId());
            failData.setKey3(cnsClustersEntity.getSubCluster());
            failData.setKey4("");
            failData.setKey5("");
            resultObject.setFailData(failData);
            return resultObject;
        }
        gdmClusterBo.setClusterId(cnsClustersEntity.getCountryId()+cnsClustersEntity.getCluster()+cnsClustersEntity.getSubCluster());
        gdmClusterBo.setActiveFCTERP(IConstant.VALUE.YES);
        gdmClusterBo.setClusterDescription(cnsClustersEntity.getCluster());
        gdmClusterBo.setClusterNr(cnsClustersEntity.getCluster());
        gdmClusterBo.setCountryId(cnsClustersEntity.getCountryId());
        gdmClusterBo.setSubFranchise(cnsClustersEntity.getSubCluster());
        resultObject.setBaseBo(gdmClusterBo);
        return resultObject;
    }
}

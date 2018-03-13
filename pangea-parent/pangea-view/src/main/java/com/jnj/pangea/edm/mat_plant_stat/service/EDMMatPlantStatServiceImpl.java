package com.jnj.pangea.edm.mat_plant_stat.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_stat.Bo.EDMMatPlantStatBo;

import java.util.List;

public class EDMMatPlantStatServiceImpl implements ICommonService {

    public void getSourceSystem(EDMMatPlantStatBo matPlantStatBo) {
        String sourceSystem=null;
        String queryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALSOURCESYSTEM).is(CommonRegionPath.LOCALSOURCESYSTEM_PROJECT_ONE).toQueryString();
        List<EDMSourceSystemV1Entity> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);

        for (EDMSourceSystemV1Entity entry : sourceList) {
            sourceSystem = entry.getSourceSystem();
        }
        matPlantStatBo.setSourceSystem(sourceSystem);
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        return null;
    }
}

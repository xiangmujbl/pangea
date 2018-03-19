package com.jnj.pangea.edm.mat_plant_stat.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectone.T141Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_stat.Bo.EDMMatPlantStatBo;

import java.util.List;

public class EDMMatPlantStatServiceImpl implements ICommonService {

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantStatServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        T141Entity t141Entity = (T141Entity) o;

        EDMMatPlantStatBo matPlantStatBo = new EDMMatPlantStatBo();

        // rule T1
        matPlantStatBo.setSourceSystem(getFieldWithT1());

        matPlantStatBo.setLocalPlantStatus(t141Entity.getMmsta());

        resultObject.setBaseBo(matPlantStatBo);

        return resultObject;
    }

    private String getFieldWithT1() {
        String systemQueryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALSOURCESYSTEM).is(CommonRegionPath.LOCALSOURCESYSTEM_PROJECT_ONE).toQueryString();
        EDMSourceSystemV1Entity sourceSystemV1Entry = commonDao.queryForObject(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, systemQueryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystemV1Entry) {
            return sourceSystemV1Entry.getSourceSystem();
        }
        return "";
    }
}

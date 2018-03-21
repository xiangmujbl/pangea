package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class EDMMatPlantStatDaoImpl extends CommonDaoImpl{
    private static EDMMatPlantStatDaoImpl instance;

    public static EDMMatPlantStatDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantStatDaoImpl();
        }
        return instance;
    }
    public EDMSourceSystemV1Entity getFieldWithT1() {
        String systemQueryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        EDMSourceSystemV1Entity sourceSystemV1Entry = commonDao.queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, systemQueryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystemV1Entry) {
            return sourceSystemV1Entry;
        }
        return null;
    }
}

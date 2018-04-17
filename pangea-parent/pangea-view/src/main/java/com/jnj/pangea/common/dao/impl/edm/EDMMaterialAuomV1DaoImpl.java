package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;

public class EDMMaterialAuomV1DaoImpl extends CommonDaoImpl {

    private static EDMMaterialAuomV1DaoImpl instance;

    public static EDMMaterialAuomV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialAuomV1Entity getEntityWithConditions(String localMaterialNumber,String localAuom) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }
}

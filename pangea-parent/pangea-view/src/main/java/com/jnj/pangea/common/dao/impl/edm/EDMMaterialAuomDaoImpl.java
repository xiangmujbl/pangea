package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;

public class EDMMaterialAuomDaoImpl extends CommonDaoImpl {
    private static EDMMaterialAuomDaoImpl instance;
    public static EDMMaterialAuomDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomDaoImpl();
        }
        return instance;
    }

    public EDMMaterialAuomV1Entity getEntityWithLocalMaterialNumAndLocalAuom(String localMaterialNum, String localAuom) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNum).and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom).toQueryString();
        LogUtil.getCoreLog().info("queryString:{}",queryString);
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1,queryString,EDMMaterialAuomV1Entity.class);

    }

}

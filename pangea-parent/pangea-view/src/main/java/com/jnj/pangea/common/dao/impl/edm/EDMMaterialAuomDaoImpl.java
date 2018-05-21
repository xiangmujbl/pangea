package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;

import java.util.List;

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
//        LogUtil.getCoreLog().info("queryString:{}",queryString);
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1,queryString,EDMMaterialAuomV1Entity.class);

    }

    public EDMMaterialAuomV1Entity getEntityWithMaterialNum(String materialNum) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.MATERIAL_NUMBER).is(materialNum)
                .toQueryString();
        LogUtil.getCoreLog().info("getEntityWithMaterialNum queryString:{}",queryString);
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1,queryString,EDMMaterialAuomV1Entity.class);

    }

    public List<EDMMaterialAuomV1Entity> getEntityList() {
        String queryString = "*:*";
        LogUtil.getCoreLog().info("getEntityWithMaterialNum queryString:{}",queryString);
        return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1,queryString,EDMMaterialAuomV1Entity.class);

    }


}

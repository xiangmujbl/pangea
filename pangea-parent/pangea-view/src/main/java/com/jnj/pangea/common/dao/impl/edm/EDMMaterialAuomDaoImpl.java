package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang.StringUtils;

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
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);

    }

    public List<EDMMaterialAuomV1Entity> getEntityWithLocalMaterialNum(String localMaterialNum) {
        if (StringUtils.isNotBlank(localMaterialNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNum).toQueryString();
            return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);

        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityListWithMaterialNum(String materialNum) {
        if (StringUtils.isNotBlank(materialNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.MATERIAL_NUMBER).is(materialNum)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public EDMMaterialAuomV1Entity getEntityWithMaterialNum(String materialNum) {
        if (StringUtils.isNotBlank(materialNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.MATERIAL_NUMBER).is(materialNum)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityWithSourceSystemAndLocalMaterialNum(String sourceSystem, String localMaterialNum) {
        if (StringUtils.isNotBlank(localMaterialNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public EDMMaterialAuomV1Entity getEntityWithSourceSystemAndLocalMaterialNumAndLocalAuom(String sourceSystem, String localMaterialNum, String localAuom) {
        if (StringUtils.isNotBlank(localMaterialNum) && StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localAuom)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNum)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }

    public List<EDMMaterialAuomV1Entity> getEntityList() {
        String queryString = "*:*";
        return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }


}

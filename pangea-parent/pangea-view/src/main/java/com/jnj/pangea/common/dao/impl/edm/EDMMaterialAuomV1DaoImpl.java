package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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
    public EDMMaterialAuomV1Entity getEntityWithLocalMaterialNumber(String localMaterialNumber,String localAuom,String sourceSystem) {
        if (null!=localMaterialNumber && null!= localAuom && null!= sourceSystem){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom).and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }
    public EDMMaterialAuomV1Entity getListWithLocalMaterialNumber(String localMaterialNumber,String localAuom,String sourceSystem) {
        if (null!=localMaterialNumber && null!= localAuom && null!= sourceSystem){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom)
                    .and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
        }
        return null;
    }
    public List<EDMMaterialAuomV1Entity> getListWithLocalAuom(String localMaterialNumber,String localAuom,String sourceSystem){
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom)
                .and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }

    public EDMMaterialAuomV1Entity getEntityWithConditions(String localMaterialNumber,String localAuom,String sourceSystem) {

        ADFCriteria adfCriteria=QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_MATERIAL_NUMBER);
        if(StringUtils.isNotBlank(localMaterialNumber)){
            adfCriteria.is(localMaterialNumber);
        }else{
            adfCriteria.isNull();
        }
        if(StringUtils.isNotBlank(localAuom)){
            adfCriteria.and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).is(localAuom);
        }else{
            adfCriteria.and(IConstant.EDM_MATERIAL_AUOM_V1.LOCAL_AUOM).isNull();
        }

        if(StringUtils.isNotBlank(sourceSystem)){
            adfCriteria.and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).is(sourceSystem);
        }else{
            adfCriteria.and(IConstant.EDM_MATERIAL_AUOM_V1.SOURCE_SYSTEM).isNull();
        }
        String queryString=adfCriteria.toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_AUOM_V1, queryString, EDMMaterialAuomV1Entity.class);
    }
}

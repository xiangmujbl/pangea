package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;
import org.apache.commons.lang3.StringUtils;

public class EDMBomHdrDaoImpl extends CommonDaoImpl {

    private static EDMBomHdrDaoImpl instance;

    public static EDMBomHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomHdrDaoImpl();
        }
        return instance;
    }

    public EDMBomHdrEntity getEntityWithConditions(String srcSysCd,String bomNum,String altBomNum,String bomCatCd) {

        if(StringUtils.isNotBlank(srcSysCd)&& StringUtils.isNotBlank(bomNum) &&StringUtils.isNotBlank(altBomNum) && StringUtils.isNotBlank(bomCatCd)){
            String queryString = QueryHelper.buildCriteria(IConstant.BOM_HDR.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.BOM_HDR.BOMNUM).is(bomNum)
                    .and(IConstant.BOM_HDR.ALTBOMNUM).is(altBomNum)
                    .and(IConstant.BOM_HDR.BOMCATCD).is(bomCatCd)
                    .toQueryString();
            return queryForObject(IConstant.REGION.BOM_HDR, queryString, EDMBomHdrEntity.class);
        }
        return null;
    }
}

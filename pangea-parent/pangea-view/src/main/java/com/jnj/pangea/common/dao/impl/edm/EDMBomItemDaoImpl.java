package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import org.apache.commons.lang3.StringUtils;

public class EDMBomItemDaoImpl extends CommonDaoImpl {

    private static EDMBomItemDaoImpl instance;

    public static EDMBomItemDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomItemDaoImpl();
        }
        return instance;
    }

    public EDMBomItemEntity getEntityWithConditions(String bomNum, String srcSysCd) {
        if (StringUtils.isNotBlank(bomNum) && StringUtils.isNotBlank(srcSysCd)) {
            String queryString = QueryHelper.buildCriteria(IConstant.BOM_ITEM.BOMNUM).is(bomNum)
                    .and(IConstant.BOM_ITEM.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.BOM_ITEM.BOMCATCD).is(IConstant.VALUE.M)
                    .toQueryString();
            return queryForObject(IConstant.REGION.BOM_ITEM, queryString, EDMBomItemEntity.class);
        }
        return null;
    }

    public EDMBomItemEntity getEntityWithConditions1(String srcSysCd, String bomCatCd, String bomNum) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_SRCSYSCD);
        if (StringUtils.isNotBlank(srcSysCd)) {
            adfCriteria.is(srcSysCd);
        } else {
            adfCriteria.isNull();
        }

        if (StringUtils.isNotBlank(bomCatCd)) {
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMCATCD).is(bomCatCd);
        } else {
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMCATCD).isNull();
        }

        if (StringUtils.isNotBlank(bomNum)) {
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMNUM).is(bomNum);
        } else {
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMNUM).isNull();
        }
        String queryString = adfCriteria.toQueryString();
        return queryForObject(IConstant.REGION.EDM_BOM_ITEM, queryString, EDMBomItemEntity.class);
    }
}
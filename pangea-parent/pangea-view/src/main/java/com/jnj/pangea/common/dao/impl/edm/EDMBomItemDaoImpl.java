package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
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
   
 public EDMBomItemEntity getEntityWithConditions(String srcSysCd,String bomCatCd,String bomNum) {
        ADFCriteria adfCriteria=QueryHelper.buildCriteria();

        if(StringUtils.isBlank(srcSysCd)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_SRCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_SRCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(bomCatCd)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMCATCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMCATCD).is(bomCatCd.trim()));
        }

        if(StringUtils.isBlank(bomNum)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_BOMNUM).is(bomNum.trim()));
        }


        return queryForObject(IConstant.REGION.EDM_BOM_ITEM, adfCriteria.toQueryString(), EDMBomItemEntity.class);
    }
}


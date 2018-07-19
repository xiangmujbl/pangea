package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang3.StringUtils;

public class EDMBomItemDaoImpl extends CommonDaoImpl {

    public static final String FIELD_STAS_STLTY_VALUE = "stlty";
    public static final String FIELD_MATLRTNGVALID_TO = "99991231";
    public static final String FIELD_STAS_STLNR_VALUE = "stlnr";
    public static final String FIELD_STAS_STLKN_VALUE = "stlkn";
    public static final String FIELD_STAS_STASZ_VALUE = "stasz";
    public static final String FIELD_LEKNZ_VALUE_X = "x";
    public static final String BOMNUM = "bomNum";
    public static final String SRCSYSCD = "srcSysCd";
    public static final String BOMCATCD = "bomCatCd";

    private static EDMBomItemDaoImpl instance;

    public static EDMBomItemDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomItemDaoImpl();
        }
        return instance;
    }
 public EDMBomItemEntity getEntityWithConditions(String bomNum, String srcSysCd) {
        if (StringUtils.isNotBlank(bomNum) && StringUtils.isNotBlank(srcSysCd)) {
            String queryString = QueryHelper.buildCriteria(BOMNUM).is(bomNum)
                    .and(SRCSYSCD).is(srcSysCd)
                    .and(BOMCATCD).is(IConstant.VALUE.M)
                    .toQueryString();
            return queryForObject(RegionsConstant.BOM_ITEM, queryString, EDMBomItemEntity.class);
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


        return queryForObject(RegionsConstant.EDM_BOM_ITEM, adfCriteria.toQueryString(), EDMBomItemEntity.class);
    }
}


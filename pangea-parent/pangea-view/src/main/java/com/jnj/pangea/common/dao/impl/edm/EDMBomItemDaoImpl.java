package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import org.apache.commons.lang3.StringUtils;

public class EDMBomItemDaoImpl extends CommonDaoImpl {

    public static final String EDM_BOM_ITEM = "/edm/bom_item";

    public static final String BOMNUM = "bomNum";
    public static final String SRCSYSCD = "srcSysCd";
    public static final String BOMCATCD = "bomCatCd";

    private static final String BOMCAT_M = "M";

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
                    .and(BOMCATCD).is(BOMCAT_M)
                    .toQueryString();
            return queryForObject(EDM_BOM_ITEM, queryString, EDMBomItemEntity.class);
        }
        return null;
    }
   
 public EDMBomItemEntity getEntityWithConditions(String srcSysCd,String bomCatCd,String bomNum) {
        ADFCriteria adfCriteria=QueryHelper.buildCriteria();

        if(StringUtils.isBlank(srcSysCd)){
            adfCriteria.and(QueryHelper.buildCriteria(SRCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(bomCatCd)){
            adfCriteria.and(QueryHelper.buildCriteria(BOMCATCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(BOMCATCD).is(bomCatCd.trim()));
        }

        if(StringUtils.isBlank(bomNum)){
            adfCriteria.and(QueryHelper.buildCriteria(BOMNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(BOMNUM).is(bomNum.trim()));
        }


        return queryForObject(EDM_BOM_ITEM, adfCriteria.toQueryString(), EDMBomItemEntity.class);
    }
}


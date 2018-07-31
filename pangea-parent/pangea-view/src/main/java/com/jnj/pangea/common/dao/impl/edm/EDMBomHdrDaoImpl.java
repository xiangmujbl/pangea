package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class EDMBomHdrDaoImpl extends CommonDaoImpl {

    public static final String BOM_HDR = "/edm/bom_hdr";

    private static EDMBomHdrDaoImpl instance;


    public static final String SRCSYSCD = "srcSysCd";
    public static final String ALTBOMNUM = "altBomNum";
    public static final String BOMNUM = "bomNum";
    public static final String BOMCATCD = "bomCatCd";


    public static EDMBomHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomHdrDaoImpl();
        }
        return instance;
    }

    public EDMBomHdrEntity getEntityWithConditions(String srcSysCd,String bomNum,String altBomNum,String bomCatCd) {

        if(StringUtils.isNotBlank(srcSysCd)&& StringUtils.isNotBlank(bomNum) &&StringUtils.isNotBlank(altBomNum) && StringUtils.isNotBlank(bomCatCd)){
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(BOMNUM).is(bomNum)
                    .and(ALTBOMNUM).is(altBomNum)
                    .and(BOMCATCD).is(bomCatCd)
                    .toQueryString();
            return queryForObject(BOM_HDR, queryString, EDMBomHdrEntity.class);
        }
        return null;
    }

    public EDMBomHdrEntity getEntityWithFiveConditions(String srcSysCd, String bomNum, String altBomNum, String bomCatCd) {
        if(StringUtils.isNotBlank(srcSysCd)&& StringUtils.isNotBlank(bomNum) &&StringUtils.isNotBlank(altBomNum) && StringUtils.isNotBlank(bomCatCd)){
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(BOMNUM).is(bomNum)
                    .and(ALTBOMNUM).is(altBomNum)
                    .and(BOMCATCD).is(bomCatCd)
                    .toQueryString();
            List<EDMBomHdrEntity> bomHdrEntityList = queryForList(BOM_HDR, queryString, EDMBomHdrEntity.class);
            for (EDMBomHdrEntity bomHdrEntity:bomHdrEntityList) {
                String bomVld_ToDt = bomHdrEntity.getBomVld_ToDt();
                if(StringUtils.isNotEmpty(bomVld_ToDt)){

                    Date bomVld_ToDtFormat = DateUtils.stringToDate(bomVld_ToDt,DateUtils.yyyy_MM_dd);
                    Date currentDate = new Date();
                    Date resultDate = DateUtils.offsetDate(currentDate,-30);

                    if (null == bomVld_ToDtFormat){
                        bomVld_ToDtFormat = DateUtils.stringToDate(bomVld_ToDt,DateUtils.F_yyyyMMdd);
                    }
                    if (bomVld_ToDtFormat.getTime() >= resultDate.getTime()){
                        return bomHdrEntity;
                    }
                }
            }
        }
        return null;
    }
}

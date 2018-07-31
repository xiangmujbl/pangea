package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlProdVersnEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMatlProdVersnDaoImpl extends CommonDaoImpl {

    public static final String EDM_MATL_PROD_VERSN = "/edm/matl_prod_versn";

    public static final String SRCSYSCD = "srcSysCd";
    public static final String PLNTCD = "plntCd";
    public static final String MATLNUM = "matlNum";
    public static final String ALTBOMNUM = "altBomNum";

    public static final String MATL_PROD_VERSN_SRCSYSCD = "srcSysCd";
    public static final String MATL_PROD_VERSN_MATLNUM = "matlNum";
    public static final String MATL_PROD_VERSN_PLNTCD = "plntCd";
    public static final String MATL_PROD_VERSN_PRDNTVRSNNUM = "prdntVrsnNum";

    private static EDMMatlProdVersnDaoImpl instance;

    public static EDMMatlProdVersnDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlProdVersnDaoImpl();
        }
        return instance;
    }

    public List<EDMMatlProdVersnEntity> getEntityWithFourConditions(String srcSysCd, String plantCd, String matlNum, String altBomNum) {
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(altBomNum)) {
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(PLNTCD).is(plantCd)
                    .and(MATLNUM).is(matlNum)
                    .and(ALTBOMNUM).is(altBomNum)
                    .toQueryString();
            return queryForList(EDM_MATL_PROD_VERSN, queryString, EDMMatlProdVersnEntity.class);

        }
        return null;
    }

    public List<EDMMatlProdVersnEntity> getEntityListWithFourConditions(String srcSysCd, String plantCd, String matlNum, String altBomNum) {
        List<EDMMatlProdVersnEntity> matlProdVersnEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(altBomNum)) {
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(PLNTCD).is(plantCd)
                    .and(MATLNUM).is(matlNum)
                    .and(ALTBOMNUM).is(altBomNum)
                    .toQueryString();
            matlProdVersnEntityList = queryForList(EDM_MATL_PROD_VERSN, queryString, EDMMatlProdVersnEntity.class);
        }
        return matlProdVersnEntityList;
    }

    public List<EDMMatlProdVersnEntity> getEntityWithConditions(String srcSysCd, String matlNum, String plntCd, String prdntVrsnNum) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria();
        if (StringUtils.isBlank(srcSysCd)) {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_SRCSYSCD).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_SRCSYSCD).is(srcSysCd.trim()));
        }

        if (StringUtils.isBlank(matlNum)) {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_MATLNUM).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_MATLNUM).is(matlNum.trim()));
        }

        if (StringUtils.isBlank(plntCd)) {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_PLNTCD).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_PLNTCD).is(plntCd.trim()));
        }

        if (StringUtils.isBlank(prdntVrsnNum)) {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_PRDNTVRSNNUM).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(MATL_PROD_VERSN_PRDNTVRSNNUM).is(prdntVrsnNum.trim()));
        }

        // LogUtil.getCoreLog().info("EDMMatlProdVersnDaoImpl q "+adfCriteria.toQueryString());


        return queryForList(EDM_MATL_PROD_VERSN, adfCriteria.toQueryString(), EDMMatlProdVersnEntity.class);
    }
}

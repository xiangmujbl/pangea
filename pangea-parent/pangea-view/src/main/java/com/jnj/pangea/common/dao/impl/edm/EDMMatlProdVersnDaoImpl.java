package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlProdVersnEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMatlProdVersnDaoImpl extends CommonDaoImpl {

    private static EDMMatlProdVersnDaoImpl instance;

    public static EDMMatlProdVersnDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlProdVersnDaoImpl();
        }
        return instance;
    }

    public List<EDMMatlProdVersnEntity> getEntityWithFourConditions(String srcSysCd, String plantCd, String matlNum, String altBomNum) {
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(altBomNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MATL_PROD_VERSN.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MATL_PROD_VERSN.PLNTCD).is(plantCd)
                    .and(IConstant.MATL_PROD_VERSN.MATLNUM).is(matlNum)
                    .and(IConstant.MATL_PROD_VERSN.ALTBOMNUM).is(altBomNum)
                    .toQueryString();
            return queryForList(IConstant.REGION.MATL_PROD_VERSN, queryString, EDMMatlProdVersnEntity.class);

        }
        return null;
    }

    public List<EDMMatlProdVersnEntity> getEntityListWithFourConditions(String srcSysCd, String plantCd, String matlNum, String altBomNum) {
        List<EDMMatlProdVersnEntity> matlProdVersnEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(altBomNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MATL_PROD_VERSN.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MATL_PROD_VERSN.PLNTCD).is(plantCd)
                    .and(IConstant.MATL_PROD_VERSN.MATLNUM).is(matlNum)
                    .and(IConstant.MATL_PROD_VERSN.ALTBOMNUM).is(altBomNum)
                    .toQueryString();
            matlProdVersnEntityList = queryForList(IConstant.REGION.MATL_PROD_VERSN, queryString, EDMMatlProdVersnEntity.class);
        }
        return matlProdVersnEntityList;
    }

    public List<EDMMatlProdVersnEntity> getEntityWithConditions(String srcSysCd, String matlNum, String plntCd, String prdntVrsnNum) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria();
        if (StringUtils.isBlank(srcSysCd)) {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_SRCSYSCD).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_SRCSYSCD).is(srcSysCd.trim()));
        }

        if (StringUtils.isBlank(matlNum)) {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_MATLNUM).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_MATLNUM).is(matlNum.trim()));
        }

        if (StringUtils.isBlank(plntCd)) {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_PLNTCD).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_PLNTCD).is(plntCd.trim()));
        }

        if (StringUtils.isBlank(prdntVrsnNum)) {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_PRDNTVRSNNUM).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MATL_PROD_VERSN.FIELD_PRDNTVRSNNUM).is(prdntVrsnNum.trim()));
        }

        // LogUtil.getCoreLog().info("EDMMatlProdVersnDaoImpl q "+adfCriteria.toQueryString());


        return queryForList(IConstant.REGION.EDM_MATL_PROD_VERSN, adfCriteria.toQueryString(), EDMMatlProdVersnEntity.class);
    }
}

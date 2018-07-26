package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMWrkCtrEntity;
import com.jnj.pangea.edm.wrk_ctr.bo.EDMWrkCtrBo;

public class EDMWrkCtrDaoImpl extends CommonDaoImpl {

    public static final String EDM_WRK_CTR = "/edm/wrk_ctr";

    public static final String SRC_SYS_CD = "srcSysCd";
    public static final String CAPY_NUM = "capyNum";

    private static EDMWrkCtrDaoImpl instance;

    public static EDMWrkCtrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrDaoImpl();
        }
        return instance;
    }

    public EDMWrkCtrEntity getEntityWithSrcSysCdAndCapyNum(String srcSysCd,String capyNum) {
        String queryString = QueryHelper.buildCriteria(SRC_SYS_CD).is(srcSysCd).
                and(CAPY_NUM).is(capyNum).toQueryString();
                //and(IConstant.EDM_CAPY_HDR.CAP_CAT_CD).is(catcd).is(IConstant.VALUE.STR_ONE).toQueryString();
        return queryForObject(EDM_WRK_CTR, queryString, EDMWrkCtrEntity.class);

    }
    public EDMWrkCtrEntity getEntityWithAttribute(String srcSysCd) {
        String queryString = QueryHelper.buildCriteria(SRC_SYS_CD).is(srcSysCd).toQueryString();
        return queryForObject(EDM_WRK_CTR, queryString, EDMWrkCtrEntity.class);

    }
}

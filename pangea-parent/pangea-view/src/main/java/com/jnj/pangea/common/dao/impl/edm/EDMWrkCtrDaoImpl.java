package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMWrkCtrEntity;
import com.jnj.pangea.edm.wrk_ctr.bo.EDMWrkCtrBo;

public class EDMWrkCtrDaoImpl extends CommonDaoImpl {

    private static EDMWrkCtrDaoImpl instance;

    public static EDMWrkCtrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrDaoImpl();
        }
        return instance;
    }

    public EDMWrkCtrEntity getEntityWithSrcSysCdAndCapyNum(String srcSysCd,String capyNum) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_WRK_CTR.SRC_SYS_CD).is(srcSysCd).
                and(IConstant.EDM_WRK_CTR.CAPY_NUM).is(capyNum).toQueryString();
                //and(IConstant.EDM_CAPY_HDR.CAP_CAT_CD).is(catcd).is(IConstant.VALUE.STR_ONE).toQueryString();
        return queryForObject(IConstant.REGION.EDM_WRK_CTR, queryString, EDMWrkCtrEntity.class);

    }
    public EDMWrkCtrEntity getEntityWithAttribute(String srcSysCd) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_WRK_CTR.SRC_SYS_CD).is(srcSysCd).toQueryString();
        return queryForObject(IConstant.REGION.EDM_WRK_CTR, queryString, EDMWrkCtrEntity.class);

    }
}

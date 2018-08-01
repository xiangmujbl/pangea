package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMWrkCtrCapyEntity;

import java.util.List;

public class EDMWrkCtrCapyDaoImpl extends CommonDaoImpl {

    private static EDMWrkCtrCapyDaoImpl instance;

    public static EDMWrkCtrCapyDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrCapyDaoImpl();
        }
        return instance;
    }

    public List<EDMWrkCtrCapyEntity> getEntityWithwrkCtrUsgCd(String srcSysCd, String wrkCtrNum, String wrkCtrTypeCd) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_WRK_CTR.SRC_SYS_CD).is(srcSysCd)
                .and("wrkCtrNum").is(wrkCtrNum)
                .and("wrkCtrTypeCd").is(wrkCtrTypeCd).toQueryString();
//        LogUtil.getCoreLog().info("-----EDMWrkCtrCapy>>"+queryString);
        return queryForList("/edm/wrk_ctr_capy", queryString, EDMWrkCtrCapyEntity.class);

    }

}

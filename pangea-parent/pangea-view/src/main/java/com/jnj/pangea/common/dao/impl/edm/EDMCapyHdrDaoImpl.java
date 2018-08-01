package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCapyHdrEntity;

import java.util.List;

public class EDMCapyHdrDaoImpl extends CommonDaoImpl {

    private static EDMCapyHdrDaoImpl instance;

    public static EDMCapyHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCapyHdrDaoImpl();
        }
        return instance;
    }

    public List<EDMCapyHdrEntity> getEntityWithwrkCtrUsgCd(String srcSysCd, String capyNum, String capyCatCd) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_WRK_CTR.SRC_SYS_CD).is(srcSysCd)
                .and("capyNum").is(capyNum)
                .and("capyCatCd").is(capyCatCd).toQueryString();
//        LogUtil.getCoreLog().info("-----EDMCapyHdr>>"+queryString);
        return queryForList("/edm/capy_hdr", queryString, EDMCapyHdrEntity.class);

    }

}

package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtgParmEntity;

import java.util.List;

public class EDMMfgRtgParmDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtgParmDaoImpl instance;

    public static EDMMfgRtgParmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtgParmDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtgParmEntity> getEntityWithConditions(String srcSysCd, String rtgTypeCd, String rtgGrpCd, String rtgNodeNum, String charCd) {
        String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_HDR.SRCSYSCD).is(srcSysCd)
                .and("rtgTypeCd").is(rtgTypeCd)
                .and("rtgGrpCd").is(rtgGrpCd)
                .and("rtgNodeNum").is(rtgNodeNum)
                .and("charCd").is(charCd)
                .toQueryString();
//        LogUtil.getCoreLog().info("-----EDMMfgRtgParm>>"+queryString);
        return queryForList("/edm/mfg_rtg_parm", queryString, EDMMfgRtgParmEntity.class);
    }
}

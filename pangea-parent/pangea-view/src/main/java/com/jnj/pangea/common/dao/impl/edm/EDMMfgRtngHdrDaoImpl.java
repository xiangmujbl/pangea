package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngHdrEntity;

import java.util.List;

public class EDMMfgRtngHdrDaoImpl extends CommonDaoImpl {

    public static final String FIELD_LOEKZ_VALUE_X = "x";
    public static final String FIELD_MATLRTNGVALID_TO = "99991231";
    public static final String FIELD_NAME_PLNNR = "plnnr";
    public static final String FIELD_NAME_PLNTY = "plnty";
    public static final String FIELD_NAME_PLNAL = "plnal";
    public static final String FIELD_NAME_PLNKN = "plnkn";
    public static final String FIELD_NAME_KNNRN = "knnrn";
    public static final String FIELD_PLNTY_VALUE_2 = "2";
    public static final String FIELD_PLNTY_VALUE_N = "N";
    public static final String SOFT_ZAEHL_VALUE = "zaehl";
    public static final String SRCSYSCD = "srcSysCd";
    public static final String RTNGTYPCD = "rtngTypCd";
    public static final String RTNGGRPCD = "rtngGrpCd";
    public static final String RTNGGRPCNTRNBR = "rtngGrpCntrNbr";

    private static EDMMfgRtngHdrDaoImpl instance;

    public static EDMMfgRtngHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngHdrDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngHdrEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd, String rtngGrpCntrNbr) {
        String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                .and(RTNGTYPCD).is(rtngTypCd)
                .and(RTNGGRPCD).is(rtngGrpCd)
                .and(RTNGGRPCNTRNBR).is(rtngGrpCntrNbr)
                .toQueryString();
        return queryForList(RegionsConstant.MFG_RTNG_HDR, queryString, EDMMfgRtngHdrEntity.class);
    }
}

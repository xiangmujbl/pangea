package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMatlMfgRtngEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMatlMfgRtngDaoImpl extends CommonDaoImpl {

    public static final String FIELD_LOEKZ_VALUE_X = "x";
    public static final String FIELD_MATLRTNGVALID_TO = "99991231";
    public static final String FIELD_NAME_PLNNR = "plnnr";
    public static final String FIELD_NAME_PLNTY = "plnty";
    public static final String FIELD_NAME_PLNAL = "plnal";
    public static final String FIELD_NAME_ZKRIZ = "zkriz";
    public static final String SRCSYSCD = "srcSysCd";
    public static final String RTNGTYPCD = "rtngTypCd";
    public static final String RTNGGRPCNTRNUM = "rtngGrpcntrNum";
    public static final String PLNTCD = "plntCd";
    public static final String RNTGGRPCNTRNBR = "rntgGrpCntrNbr";
    public static final String MATLNUM = "matlNum";
    public static final String RNTGGRPCD = "rntgGrpCd";
    public static final String RTNGGRPCD = "rtngGrpCd";
    public static final String FIELD_MATLRTNGVALID_TO_NULL = "";

    private static EDMMatlMfgRtngDaoImpl instance;

    public static EDMMatlMfgRtngDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlMfgRtngDaoImpl();
        }
        return instance;
    }

    public List<EDMMatlMfgRtngEntity> getEntityWithFiveConditions(String srcSysCd, String matlNum, String plantCd,String rntgGrpCd,String rntgGrpCntrNum) {
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd)
                && StringUtils.isNotBlank(matlNum) ) {
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(MATLNUM).is(matlNum)
                    .and(RNTGGRPCD).is(rntgGrpCd)
                    .and(RNTGGRPCNTRNBR).is(rntgGrpCntrNum)
                    .and(PLNTCD).is(plantCd).
                            toQueryString();
            return queryForList(RegionsConstant.MATL_MFG_RTNG, queryString, EDMMatlMfgRtngEntity.class);
        }
        return null;
    }

    public List<EDMMatlMfgRtngEntity> getEntityWithThreeConditions(String srcSysCd,String matlNum,String plntCd) {
        List<EDMMatlMfgRtngEntity> matlMfgRtngEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(plntCd)) {
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(MATLNUM).is(matlNum)
                    .and(PLNTCD).is(plntCd).toQueryString();
            matlMfgRtngEntityList = queryForList(RegionsConstant.MATL_MFG_RTNG, queryString, EDMMatlMfgRtngEntity.class);
        }
        return matlMfgRtngEntityList;
    }
}

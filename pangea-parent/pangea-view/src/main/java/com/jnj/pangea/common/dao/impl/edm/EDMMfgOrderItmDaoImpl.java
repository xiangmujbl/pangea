package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMfgOrderItmDaoImpl extends CommonDaoImpl {

    public static final String EDM_MFG_ORDER_ITM_SRCSYSCD = "srcSysCd";
    public static final String EDM_MFG_ORDER_ITM_MFGORDRNUM = "mfgOrdrNum";

    private static EDMMfgOrderItmDaoImpl instance;

    public static EDMMfgOrderItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderItmDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgOrderItmEntity> getEntityWithConditions(String srcSysCd, String mfgOrdrNum) {



        ADFCriteria adfCriteria=QueryHelper.buildCriteria();
        if(StringUtils.isBlank(srcSysCd)){
            adfCriteria.and(QueryHelper.buildCriteria(EDM_MFG_ORDER_ITM_SRCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(EDM_MFG_ORDER_ITM_SRCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(mfgOrdrNum)){
            adfCriteria.and(QueryHelper.buildCriteria(EDM_MFG_ORDER_ITM_MFGORDRNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(EDM_MFG_ORDER_ITM_MFGORDRNUM).is(mfgOrdrNum.trim()));
        }

        return queryForList(RegionsConstant.EDM_MFG_ORDER_ITM, adfCriteria.toQueryString(), EDMMfgOrderItmEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMfgOrderItmDaoImpl extends CommonDaoImpl {

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
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_SRCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_SRCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(mfgOrdrNum)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_MFGORDRNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_MFGORDRNUM).is(mfgOrdrNum.trim()));
        }

        return queryForList(IConstant.REGION.EDM_MFG_ORDER_ITM, adfCriteria.toQueryString(), EDMMfgOrderItmEntity.class);
    }
}

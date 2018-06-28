package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import org.apache.commons.lang3.StringUtils;
import com.jnj.pangea.common.entity.edm.EDMSalesHistoryV1Entity;

import java.util.List;

public class EDMSalesHistoryV1DaoImpl extends CommonDaoImpl {

    private static EDMSalesHistoryV1DaoImpl instance;

    public static EDMSalesHistoryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesHistoryV1DaoImpl();
        }
        return instance;
    }

    public List<EDMSalesHistoryV1Entity> getSalesHistoryForDeliveryDoc(String devlDocId, String delvDocLineNbr, String sourceSystem, String docCatg){
        if(StringUtils.isNotEmpty(devlDocId) && StringUtils.isNotEmpty(delvDocLineNbr)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_SALES_HISTORY_V1.LOCAL_SUBS_DOC_NO).is(devlDocId)
                    .and(IConstant.EDM_SALES_HISTORY_V1.LOCAL_SUBS_DOC_LINENBR).is(delvDocLineNbr)
                    .and(IConstant.EDM_SALES_HISTORY_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.EDM_SALES_HISTORY_V1.LOCAL_SUBS_DOC_CATG).is(docCatg)
                    .toQueryString();
            LogUtil.getLogger().info("++++++ QUERY : "+queryString);
            return queryForList(IConstant.REGION.EDM_SALES_HISTORY_V1, queryString, EDMSalesHistoryV1Entity.class);
        }
        return null;
    }

    public EDMSalesHistoryV1Entity getFirstSalesHistoryForDeliveryDoc(String devlDocId, String delvDocLineNbr, String sourceSystem, String docCatg) {
        if (StringUtils.isNotEmpty(devlDocId) && StringUtils.isNotEmpty(delvDocLineNbr)) {
            List<EDMSalesHistoryV1Entity> list = getSalesHistoryForDeliveryDoc(devlDocId,delvDocLineNbr,sourceSystem,docCatg);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

}

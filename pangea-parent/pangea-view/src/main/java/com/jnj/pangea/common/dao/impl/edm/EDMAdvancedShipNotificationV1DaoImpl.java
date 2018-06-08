package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import org.apache.commons.lang3.StringUtils;

public class EDMAdvancedShipNotificationV1DaoImpl extends CommonDaoImpl {

    private static EDMAdvancedShipNotificationV1DaoImpl instance;

    public static EDMAdvancedShipNotificationV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMAdvancedShipNotificationV1DaoImpl();
        }
        return instance;
    }

    public EDMAdvancedShipNotificationV1Entity getEntityByDelvDocId(String delvDocId){
        if(StringUtils.isNotEmpty(delvDocId)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_ADVANCE_SHIP_NOTIFICATION_V1.DELV_DOC_ID).is(delvDocId)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_ADVANCE_SHIP_NOTIFICATION_V1, queryString, EDMAdvancedShipNotificationV1Entity.class);
        }
        return null;
    }

    public EDMAdvancedShipNotificationV1Entity getEntityByDelvDocIdAndDelvLineNbrAndSourceSystem(String delvDocId, String delvLineNbr, String sourceSystem){
        if(StringUtils.isNotEmpty(delvDocId)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_ADVANCE_SHIP_NOTIFICATION_V1.DELV_DOC_ID).is(delvDocId)
                    .and(IConstant.EDM_ADVANCE_SHIP_NOTIFICATION_V1.DELV_LINE_NBR).is(delvLineNbr)
                    .and(IConstant.EDM_ADVANCE_SHIP_NOTIFICATION_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_ADVANCE_SHIP_NOTIFICATION_V1, queryString, EDMAdvancedShipNotificationV1Entity.class);
        }
        return null;
    }

}

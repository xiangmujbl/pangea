package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import org.apache.commons.lang3.StringUtils;

public class EDMAdvancedShipNotificationV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_ADVANCED_SHIP_NOTIFICATION_V1 = "/edm/advanced_ship_notification_v1";

    public static final String DELV_DOC_ID = "delvDocId";
    public static final String DELV_LINE_NBR = "delvLineNbr";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static EDMAdvancedShipNotificationV1DaoImpl instance;

    public static EDMAdvancedShipNotificationV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMAdvancedShipNotificationV1DaoImpl();
        }
        return instance;
    }

    public EDMAdvancedShipNotificationV1Entity getEntityByDelvDocId(String delvDocId){
        if(StringUtils.isNotEmpty(delvDocId)){
            String queryString = QueryHelper.buildCriteria(DELV_DOC_ID).is(delvDocId)
                    .toQueryString();
            return queryForObject(EDM_ADVANCED_SHIP_NOTIFICATION_V1, queryString, EDMAdvancedShipNotificationV1Entity.class);
        }
        return null;
    }

    public EDMAdvancedShipNotificationV1Entity getEntityByDelvDocIdAndDelvLineNbrAndSourceSystem(String delvDocId, String delvLineNbr, String sourceSystem){
        if(StringUtils.isNotEmpty(delvDocId)){
            String queryString = QueryHelper.buildCriteria(DELV_DOC_ID).is(delvDocId)
                    .and(DELV_LINE_NBR).is(delvLineNbr)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(EDM_ADVANCED_SHIP_NOTIFICATION_V1, queryString, EDMAdvancedShipNotificationV1Entity.class);
        }
        return null;
    }

}

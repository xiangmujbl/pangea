package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMOutboundDeliveryLineV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMOutboundDeliveryLineV1DaoImpl extends CommonDaoImpl {

    private static EDMOutboundDeliveryLineV1DaoImpl instance;

    public static EDMOutboundDeliveryLineV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMOutboundDeliveryLineV1DaoImpl();
        }
        return instance;
    }

    public List<EDMOutboundDeliveryLineV1Entity> getOutboundDeliveryLinesByDeliveryDocId(String devlDocId){
        if(StringUtils.isNotEmpty(devlDocId)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_OUTBOUND_DELIVERY_LINE_V1.DELVDOCID).is(devlDocId)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_OUTBOUND_DELIVERY_LINE_V1, queryString, EDMOutboundDeliveryLineV1Entity.class);
        }
        return null;
    }
}

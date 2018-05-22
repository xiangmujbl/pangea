package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMJNJCalendarV1Entity;

import java.util.List;

public class EDMJNJCalendarV1DaoImpl extends CommonDaoImpl {

    private static EDMJNJCalendarV1DaoImpl instance;

    public static EDMJNJCalendarV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMJNJCalendarV1DaoImpl();
        }
        return instance;
    }

    public List<EDMJNJCalendarV1Entity> getEntityWithFiscalPeriod(String fiscalPeriod) {
        if (null != fiscalPeriod && !"".equals(fiscalPeriod)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_JNJ_CALENDAR_V1.FISCALPERIOD).is(fiscalPeriod).toQueryString();
            return queryForList(IConstant.REGION.EDM_JNJ_CALENDAR_V1, queryString, EDMJNJCalendarV1Entity.class);
        }
        return null;
    }

}

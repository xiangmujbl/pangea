package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMJNJCalendarV1Entity;

import java.util.List;

public class EDMJNJCalendarV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_JNJ_CALENDAR_V1 = "/edm/jnj_calendar_v1";

    public static final String FISCALPERIOD = "fiscalPeriod";

    private static EDMJNJCalendarV1DaoImpl instance;

    public static EDMJNJCalendarV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMJNJCalendarV1DaoImpl();
        }
        return instance;
    }

    public List<EDMJNJCalendarV1Entity> getEntityWithFiscalPeriod(String fiscalPeriod) {
        if (null != fiscalPeriod && !"".equals(fiscalPeriod)) {
            String queryString = QueryHelper.buildCriteria(FISCALPERIOD).is(fiscalPeriod).toQueryString();
            return queryForList(EDM_JNJ_CALENDAR_V1, queryString, EDMJNJCalendarV1Entity.class);
        }
        return null;
    }

}

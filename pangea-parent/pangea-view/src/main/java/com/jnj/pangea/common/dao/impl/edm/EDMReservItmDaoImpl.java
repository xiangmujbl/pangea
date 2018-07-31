package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMReservItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMReservItmDaoImpl extends CommonDaoImpl {

    public static final String EDM_RESERV_ITM = "/edm/reserv_itm";

    public static final String RESERV_ITM_SOURCESYSCD = "sourceSysCd";
    public static final String RESERV_ITM_RSRVTNNUM = "rsrvtnNum";

    private static EDMReservItmDaoImpl instance;

    public static EDMReservItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMReservItmDaoImpl();
        }
        return instance;
    }

    public List<EDMReservItmEntity> getEntityWithConditions(String sourceSysCd,String rsrvtnNum) {
        ADFCriteria adfCriteria= QueryHelper.buildCriteria();
        if(StringUtils.isNotBlank(sourceSysCd)){
            adfCriteria.and(RESERV_ITM_SOURCESYSCD).is(sourceSysCd);
        }else{
            adfCriteria.and(RESERV_ITM_SOURCESYSCD).isNull();
        }
        if(StringUtils.isNotBlank(rsrvtnNum)){
            adfCriteria.and(RESERV_ITM_RSRVTNNUM).is(rsrvtnNum);
        }else{
            adfCriteria.and(RESERV_ITM_RSRVTNNUM).isNull();
        }
        return queryForList(EDM_RESERV_ITM, adfCriteria.toQueryString(), EDMReservItmEntity.class);
    }
}

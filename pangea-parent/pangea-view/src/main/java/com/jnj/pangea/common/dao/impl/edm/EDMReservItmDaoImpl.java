package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMReservItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMReservItmDaoImpl extends CommonDaoImpl {

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
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.RESERV_ITM.FIELD_SOURCESYSCD).is(sourceSysCd);
        }else{
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.RESERV_ITM.FIELD_SOURCESYSCD).isNull();
        }
        if(StringUtils.isNotBlank(rsrvtnNum)){
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.RESERV_ITM.FIELD_RSRVTNNUM).is(rsrvtnNum);
        }else{
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.RESERV_ITM.FIELD_RSRVTNNUM).isNull();
        }
        return queryForList(IConstant.REGION.EDM_RESERV_ITM, adfCriteria.toQueryString(), EDMReservItmEntity.class);
    }
}

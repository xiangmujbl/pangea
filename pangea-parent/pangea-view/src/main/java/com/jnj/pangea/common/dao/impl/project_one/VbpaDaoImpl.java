package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbpaEntity;

public class VbpaDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBPA = "/project_one/vbpa";

    public static final String VBELN = "vbeln";
    public static final String POSNR = "posnr";
    public static final String PARVW = "parvw";

    private static VbpaDaoImpl instance;

    public static VbpaDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbpaDaoImpl();
        }
        return instance;
    }

    public VbpaEntity getEntityWithPosnrAndParvwAndVbelnAndPosnrIsNullOrBlankOr000000(String vbeln, String parvw) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria(POSNR).is("000000");
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria(POSNR).isNull();
        ADFCriteria criteria = adfCriteria1.or(adfCriteria);
        String queryString = QueryHelper.buildCriteria(PARVW).is(parvw).and(VBELN).is(vbeln).and(criteria).toQueryString();

        return queryForObject(PROJECT_ONE_VBPA, queryString, VbpaEntity.class);
    }
}

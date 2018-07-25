package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbepEntity;

import java.util.List;

public class VbepDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBEP = "/project_one/vbep";

    public static final String VBELN = "vbeln";
    public static final String POSNR = "posnr";

    private static VbepDaoImpl instance;

    public static VbepDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbepDaoImpl();
        }
        return instance;
    }

    public List<VbepEntity> getEntityWithVbelnAndPosnr(String vbeln, String posnr) {
        String queryString;
        if (null==posnr||"".equals(posnr)){
            queryString = QueryHelper.buildCriteria(VBELN).is(vbeln).and(POSNR).isNull().toQueryString();
        }else {
            queryString= QueryHelper.buildCriteria(VBELN).is(vbeln).and(POSNR).is(posnr).toQueryString();
        }
        return queryForList(PROJECT_ONE_VBEP, queryString, VbepEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl.ngems;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class NgemsGoldenMaterialDaoImpl extends CommonDaoImpl {

    public static final String NGEMS_GOLDEN_MATERIAL = "/ngems/golden_material";

    public static final String MATERIAL_NUMBER = "materialNumber";

    private static NgemsGoldenMaterialDaoImpl instance;

    public static NgemsGoldenMaterialDaoImpl getInstance() {
        if (instance == null) {
            instance = new NgemsGoldenMaterialDaoImpl();
        }
        return instance;
    }
    public GoldenMaterialEntity getEntityWithMaterialNumber(String materialNumber){
       String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return commonDao.queryForObject(NGEMS_GOLDEN_MATERIAL, queryString, GoldenMaterialEntity.class);
    }
}

package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;
import com.jnj.pangea.common.entity.ngems.MaterialLinkageEntity;
import com.jnj.pangea.common.entity.projectone.MaktEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class EDMMaterialGlobalDaoImpl extends CommonDaoImpl {
    private static EDMMaterialGlobalDaoImpl instance;
    public static EDMMaterialGlobalDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalDaoImpl();
        }
        return instance;
    }
    public MaktEntity getFieldWithJ1(String matnr) {

        if (StringUtils.isEmpty(matnr)) {
            return null;
        }

        List<MaktEntity> items = null;

        String queryEnString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MAKT.MATNR).is(matnr).toQueryString();

        if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
            queryEnString = IConstant.PROJECT_ONE_MAKT.MATNR + ":\"" + AdfLuceneHelper.keyword(matnr) + "\"";
        }
        items = commonDao.queryForList(IConstant.REGION.PROJECT_ONE_MAKT, queryEnString, MaktEntity.class);

        for (MaktEntity item : items) {
            String spras = item.getSpras();
            String maktx = item.getMaktx();
            if (IConstant.VALUE.EN.equals(spras)) {
                return item;
            } else if (IConstant.VALUE.PT.equals(spras)) {
                return item;
            } else if (IConstant.VALUE.SP.equals(spras)) {
                return item;
            }
        }
        return null;
    }

    public GoldenMaterialEntity getFieldsWithJ2(String matnr, String sourceSystem) {

        if (StringUtils.isEmpty(matnr)) {
            return null;
        }
        if (StringUtils.isEmpty(sourceSystem)) {
            return null;
        }

        String queryString = QueryHelper
                .buildCriteria(IConstant.NGEMS_MATERIAL_LINKAGE.LOCAL_MATERIAL_NUMBER).is(matnr)
                .and(IConstant.NGEMS_MATERIAL_LINKAGE.SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();

        MaterialLinkageEntity materialLinkageEntity = commonDao.queryForObject(IConstant.REGION.NGEMS_MATERIAL_LINKAGE, queryString, MaterialLinkageEntity.class);
        if (null != materialLinkageEntity) {

            String materialNumber = materialLinkageEntity.getMaterialNumber();
            queryString = QueryHelper.buildCriteria(IConstant.NGEMS_GOLDEN_MATERIAL.MATERIAL_NUMBER).is(materialNumber).toQueryString();
            return commonDao.queryForObject(IConstant.REGION.NGEMS_GOLDEN_MATERIAL, queryString, GoldenMaterialEntity.class);
        }
        return null;
    }
}

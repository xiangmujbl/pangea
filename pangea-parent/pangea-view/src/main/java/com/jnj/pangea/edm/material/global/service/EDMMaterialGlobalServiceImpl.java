package com.jnj.pangea.edm.material.global.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;
import com.jnj.pangea.common.entity.ngems.MaterialLinkageEntity;
import com.jnj.pangea.common.entity.projectone.MaktEntity;
import com.jnj.pangea.common.entity.projectone.MaraEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.global.bo.EDMMaterialGlobalBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public class EDMMaterialGlobalServiceImpl implements ICommonService {

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        MaraEntity maraEntity = (MaraEntity) o;
        String sourceSystem = (String) o2;

        FailData failData = checkT1(maraEntity, sourceSystem);
        if (null != failData) {
            resultObject.setFailData(failData);
            return resultObject;
        }

        EDMMaterialGlobalBo materialGlobalBo = new EDMMaterialGlobalBo();

        materialGlobalBo.setSourceSystem(sourceSystem);

        String matnr = maraEntity.getMatnr();
        materialGlobalBo.setLocalMaterialNumber(matnr);

        // rule J1
        materialGlobalBo.setLocalRefDescription(getFieldWithJ1(matnr));

        materialGlobalBo.setLocalMaterialType(maraEntity.getMtart());
        materialGlobalBo.setLocalBaseUom(maraEntity.getMeins());
        materialGlobalBo.setLocalMaterialGroup(maraEntity.getMatkl());
        materialGlobalBo.setFlagForDeletion(maraEntity.getLvorm());
        materialGlobalBo.setMaterialStatus(maraEntity.getMstae());
        materialGlobalBo.setDivision(maraEntity.getSpart());
        materialGlobalBo.setMaterialGroup("");

        // rule J2
        GoldenMaterialEntity goldenMaterialEntity = getFieldsWithJ2(matnr, sourceSystem);
        if (null != goldenMaterialEntity) {
            materialGlobalBo.setMaterialNumber(goldenMaterialEntity.getMaterialNumber());
            materialGlobalBo.setRefDescription(goldenMaterialEntity.getMaterialDescription());
            materialGlobalBo.setMaterialType(goldenMaterialEntity.getMaterialType());
            materialGlobalBo.setBaseUom(goldenMaterialEntity.getBaseUom());
            materialGlobalBo.setParentCode(goldenMaterialEntity.getParentCode());
            materialGlobalBo.setForm(goldenMaterialEntity.getForm());
            materialGlobalBo.setCategory(goldenMaterialEntity.getCategory());
            materialGlobalBo.setSubBrand(goldenMaterialEntity.getSubBrand());
            materialGlobalBo.setBrand(goldenMaterialEntity.getBrand());
            materialGlobalBo.setFranchise(goldenMaterialEntity.getFranchise());
            materialGlobalBo.setGlobalBusinessUnit(goldenMaterialEntity.getGlobalBusinessUnit());
            materialGlobalBo.setProductFamily(goldenMaterialEntity.getProductFamily());
            materialGlobalBo.setManufacturingTechnology(goldenMaterialEntity.getManufTechnology());
        }
        materialGlobalBo.setLocalManufacturingTechnology("");
        materialGlobalBo.setGlobalDpParentCode("");
        materialGlobalBo.setLocalDpParentCode("");

        resultObject.setBaseBo(materialGlobalBo);

        return resultObject;
    }

    private String getFieldWithJ1(String matnr) {

        if (StringUtils.isEmpty(matnr)) {
            return "";
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
                return maktx;
            } else if (IConstant.VALUE.PT.equals(spras)) {
                return maktx;
            } else if (IConstant.VALUE.SP.equals(spras)) {
                return maktx;
            }
        }
        return "";
    }

    private GoldenMaterialEntity getFieldsWithJ2(String matnr, String sourceSystem) {

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

    private FailData checkT1(MaraEntity maraEntity, String sourceSystem) {
        FailData failData = null;
        if (StringUtils.isEmpty(sourceSystem)) {
            failData = new FailData();
            failData.setErrorCode("T1");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMMaterialGlobal");
            failData.setSourceSystem("project_one");
            failData.setKey1(maraEntity.getMatnr());
            failData.setKey2("");
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
        }
        return failData;
    }

}

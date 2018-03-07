package com.jnj.pangea.edm.material.global.material_global.service;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.GoldenMaterialEntity;
import com.jnj.pangea.common.entry.ngems.MaterialLinkageEntity;
import com.jnj.pangea.common.entry.projectone.MaktEntity;
import com.jnj.pangea.common.entry.projectone.MaraEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.global.material_global.bo.EDMMaterialGlobalBo;
import com.jnj.pangea.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public class EDMMaterialGlobalServiceImpl implements ICommonService {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        MaraEntity maraEntity = (MaraEntity) o;
        String sourceSystem = (String) o2;

        ResultObject resultObject = new ResultObject();

        EDMMaterialGlobalBo materialGlobalBo = new EDMMaterialGlobalBo();

        materialGlobalBo.setSourceSystem(sourceSystem);

        String matnr = maraEntity.getMatnr();
        materialGlobalBo.setLocalMaterialNumber(matnr);

        // rule J1
        materialGlobalBo.setLocalRefDescription(getFieldWithJ1(matnr));

        materialGlobalBo.setLocalMaterialType(maraEntity.getMtart());
        materialGlobalBo.setLocalBaseUnit(maraEntity.getMeins());
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

        return resultObject;
    }

    private String getFieldWithJ1(String matnr) {
        if (StringUtils.isEmpty(matnr)) {
            return "";
        }
        List<Map.Entry<String, String>> items = null;

        String queryEnString = "";

        // TODO add ce support
        if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
            return "";
        }

        queryEnString = QueryHelper.buildCriteria("matnr").is(matnr).toQueryString();
        items = AdfViewHelper.queryForList(CommonRegionPath.PROJECT_ONE_MAKT, queryEnString, -1);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();

            MaktEntity maktEntity = BeanUtil.mapToBean(jsonObj, new MaktEntity());
            String spras = maktEntity.getSpras();
            String maktx = maktEntity.getMaktx();
            if ("E".equals(spras)) {
                return maktx;
            } else if ("P".equals(spras)) {
                return maktx;
            } else if ("S".equals(spras)) {
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
                .buildCriteria("localMaterialNumber").is(matnr)
                .and("sourceSystem").is(sourceSystem)
                .toQueryString();
        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(CommonRegionPath.NGEMS_MATERIAL_LINKAGE, queryString);

        if (null != result && null != result.getValue()) {

            MaterialLinkageEntity materialLinkageEntity = BeanUtil.mapToBean(result.getValue(), new MaterialLinkageEntity());
            String materialNumber = materialLinkageEntity.getMaterialNumber();

            queryString = QueryHelper.buildCriteria("materialNumber").is(materialNumber).toQueryString();
            result = AdfViewHelper.queryForMap(CommonRegionPath.NGEMS_GOLDEN_MATERIAL, queryString);
            if (null != result) {
                return BeanUtil.mapToBean(result.getValue(), new GoldenMaterialEntity());
            }
        }
        return null;
    }

}

package com.jnj.pangea.edm.material.global.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMMaterialGlobalDaoImpl;
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
    private EDMMaterialGlobalDaoImpl EDMMaterialGlobalDao=EDMMaterialGlobalDaoImpl.getInstance();
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
        materialGlobalBo.setLocalRefDescription(EDMMaterialGlobalDao.getFieldWithJ1(matnr).getMaktx());

        materialGlobalBo.setLocalMaterialType(maraEntity.getMtart());
        materialGlobalBo.setLocalBaseUom(maraEntity.getMeins());
        materialGlobalBo.setLocalMaterialGroup(maraEntity.getMatkl());
        materialGlobalBo.setFlagForDeletion(maraEntity.getLvorm());
        materialGlobalBo.setMaterialStatus(maraEntity.getMstae());
        materialGlobalBo.setDivision(maraEntity.getSpart());
        materialGlobalBo.setMaterialGroup("");

        materialGlobalBo.setBatchManageIndicator(maraEntity.getXchpf());
        materialGlobalBo.setMinRemShelfLife(maraEntity.getMhdrz());
        materialGlobalBo.setTotalShelfLife(maraEntity.getMhdhb());
        // rule J2
        GoldenMaterialEntity goldenMaterialEntity = EDMMaterialGlobalDao.getFieldsWithJ2(matnr, sourceSystem);
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

            materialGlobalBo.setPrimaryPlanningCode(goldenMaterialEntity.getPrimaryPlanningCode());
        }
        materialGlobalBo.setLocalManufacturingTechnology("");
        materialGlobalBo.setGlobalDpParentCode("");
        materialGlobalBo.setLocalDpParentCode("");

        resultObject.setBaseBo(materialGlobalBo);

        return resultObject;
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

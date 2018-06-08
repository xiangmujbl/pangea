package com.jnj.pangea.edm.material.global.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.ngems.NgemsGoldenMaterialDaoImpl;
import com.jnj.pangea.common.dao.impl.ngems.NgemsMaterialLinkageDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMaktDaoImpl;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;
import com.jnj.pangea.common.entity.ngems.MaterialLinkageEntity;
import com.jnj.pangea.common.entity.project_one.MaktEntity;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
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

    private ProjectOneMaktDaoImpl maktDao = ProjectOneMaktDaoImpl.getInstance();
    private NgemsMaterialLinkageDaoImpl linkageDao = NgemsMaterialLinkageDaoImpl.getInstance();
    private NgemsGoldenMaterialDaoImpl goldenMaterialDao = NgemsGoldenMaterialDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        MaraEntity maraEntity = (MaraEntity) o;
        String sourceSystem = (String) o2;

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

        materialGlobalBo.setBatchManageIndicator(maraEntity.getXchpf());
        materialGlobalBo.setMinRemShelfLife(maraEntity.getMhdrz());
        materialGlobalBo.setTotalShelfLife(maraEntity.getMhdhb());
        // rule J2
        String materialNumber = getFieldWithJ2(matnr, sourceSystem);
        if (StringUtils.isNotEmpty(materialNumber)) {
            GoldenMaterialEntity goldenMaterialEntity = goldenMaterialDao.getEntityWithMaterialNumber(materialNumber);
            if (null != goldenMaterialEntity) {
                materialGlobalBo.setMaterialNumber(goldenMaterialEntity.getMaterialNumber());
                materialGlobalBo.setRefDescription(goldenMaterialEntity.getMaterialDescription());
                materialGlobalBo.setMaterialType(goldenMaterialEntity.getMaterialType());
                materialGlobalBo.setBaseUom(goldenMaterialEntity.getBaseUom());
                materialGlobalBo.setParentCode(goldenMaterialEntity.getParentCode());
                // J3
                if (StringUtils.isEmpty(goldenMaterialEntity.getGlobalDpParentCode()) &&
                        StringUtils.isNotEmpty(goldenMaterialEntity.getParentCode())) {

                    materialGlobalBo.setGlobalDpParentCode(goldenMaterialEntity.getParentCode());
                }
                if (StringUtils.isEmpty(goldenMaterialEntity.getParentCode())) {

                    materialGlobalBo.setGlobalDpParentCode("");
                }

                materialGlobalBo.setForm(goldenMaterialEntity.getForm());
                materialGlobalBo.setCategory(goldenMaterialEntity.getCategory());
                materialGlobalBo.setSubBrand(goldenMaterialEntity.getSubBrand());
                materialGlobalBo.setBrand(goldenMaterialEntity.getBrand());
                materialGlobalBo.setFranchise(goldenMaterialEntity.getFranchise());
                materialGlobalBo.setGlobalBusinessUnit(goldenMaterialEntity.getGlobalBusinessUnit());
                materialGlobalBo.setProductFamily(goldenMaterialEntity.getProductFamily());
                materialGlobalBo.setManufacturingTechnology(goldenMaterialEntity.getManufTechnology());
                // J4
                String primaryPlanningCode = goldenMaterialEntity.getPrimaryPlanningCode();
                if (StringUtils.isNotEmpty(primaryPlanningCode)) {
                    materialGlobalBo.setPrimaryPlanningCode(goldenMaterialEntity.getPrimaryPlanningCode());
                } else {
                    materialGlobalBo.setPrimaryPlanningCode(goldenMaterialEntity.getMaterialNumber());
                }
            }
        }
        materialGlobalBo.setLocalManufacturingTechnology("");
        materialGlobalBo.setLocalDpParentCode("");

        resultObject.setBaseBo(materialGlobalBo);

        return resultObject;
    }

    public String getFieldWithJ1(String matnr) {
        List<MaktEntity> maktEntities = maktDao.getEntityWithMatnr(matnr);
        for (MaktEntity item : maktEntities) {
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

    public String getFieldWithJ2(String matnr, String sourceSystem) {
        MaterialLinkageEntity linkageEntity = linkageDao.getEntityWithLocalMaterialNumberAndSourceSystem(matnr, sourceSystem);
        String materialNumber = "";
        if (null != linkageEntity) {
            materialNumber = linkageEntity.getMaterialNumber();
        }
        return materialNumber;
    }
}

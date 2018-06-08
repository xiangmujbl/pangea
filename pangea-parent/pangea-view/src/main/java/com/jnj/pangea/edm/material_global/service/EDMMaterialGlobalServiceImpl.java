package com.jnj.pangea.edm.material_global.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;
import com.jnj.pangea.common.entity.ngems.MaterialLinkageEntity;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
import com.jnj.pangea.common.entity.project_one.MaktEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMaktDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ngems.NgemsMaterialLinkageDaoImpl;
import com.jnj.pangea.common.dao.impl.ngems.NgemsGoldenMaterialDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material_global.bo.EDMMaterialGlobalBo;
import org.apache.commons.lang.StringUtils;

public class EDMMaterialGlobalServiceImpl implements ICommonService {

    private static EDMMaterialGlobalServiceImpl instance;

    public static EDMMaterialGlobalServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialGlobalServiceImpl();
        }
        return instance;
    }

    private ProjectOneMaktDaoImpl maktDao = ProjectOneMaktDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private NgemsMaterialLinkageDaoImpl materialLinkageDao = NgemsMaterialLinkageDaoImpl.getInstance();
    private NgemsGoldenMaterialDaoImpl goldenMaterialDao = NgemsGoldenMaterialDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        MaraEntity maraEntity = (MaraEntity) o;

        EDMMaterialGlobalBo materialGlobalBo = new EDMMaterialGlobalBo();
        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            materialGlobalBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());
        }

        // rules J1
        String matnr = maraEntity.getMatnr();
        if (StringUtils.isNotEmpty(matnr)) {
            MaktEntity maktEntityEn = maktDao.getEntityWithMatnrAndSpras(matnr, IConstant.VALUE.EN);
            if (null != maktEntityEn) {
                materialGlobalBo.setLocalRefDescription(maktEntityEn.getMaktx());
            } else {
                MaktEntity maktEntityPt = maktDao.getEntityWithMatnrAndSpras(matnr, IConstant.VALUE.PT);
                if (null != maktEntityPt) {
                    materialGlobalBo.setLocalRefDescription(maktEntityPt.getMaktx());
                } else {
                    MaktEntity maktEntitySp = maktDao.getEntityWithMatnrAndSpras(matnr, IConstant.VALUE.SP);
                    if (null != maktEntitySp) {
                        materialGlobalBo.setLocalRefDescription(maktEntitySp.getMaktx());
                    }
                }
            }
        }

        // rules J2
        String sourceSystem = sourceSystemV1Entity.getSourceSystem();
        String localMaterialNumber = maraEntity.getMatnr();
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
            MaterialLinkageEntity materialLinkageEntity = materialLinkageDao.
                    getEntityWithLocalMaterialNumberAndSourceSystem(localMaterialNumber, sourceSystem);
            if (null != materialLinkageEntity) {
                String materialNumber = materialLinkageEntity.getMaterialNumber();
                if (StringUtils.isNotEmpty(materialNumber)) {
                    GoldenMaterialEntity goldenMaterialEntity = goldenMaterialDao.getEntityWithMaterialNumber(materialNumber);
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

                        // rules J3
                        if (StringUtils.isNotEmpty(goldenMaterialEntity.getGlobalDpParentCode())) {
                            materialGlobalBo.setGlobalDpParentCode(goldenMaterialEntity.getGlobalDpParentCode());
                        } else if (StringUtils.isEmpty(goldenMaterialEntity.getGlobalDpParentCode()) && StringUtils.isNotEmpty(goldenMaterialEntity.getParentCode())) {
                            materialGlobalBo.setGlobalDpParentCode(goldenMaterialEntity.getParentCode());
                        } else if (StringUtils.isEmpty(goldenMaterialEntity.getParentCode())) {
                            materialGlobalBo.setGlobalDpParentCode("");
                        }
                    }
                }
            }
        }
        materialGlobalBo.setLocalMaterialNumber(maraEntity.getMatnr());
        materialGlobalBo.setLocalMaterialType(maraEntity.getMtart());
        materialGlobalBo.setLocalBaseUom(maraEntity.getMeins());
        materialGlobalBo.setLocalDpParentCode("");
        materialGlobalBo.setLocalManufacturingTechnology("");
        materialGlobalBo.setLocalMaterialGroup(maraEntity.getMatkl());
        materialGlobalBo.setMaterialGroup("");
        materialGlobalBo.setFlagForDeletion(maraEntity.getLvorm());
        materialGlobalBo.setMaterialStatus(maraEntity.getMstae());
        materialGlobalBo.setDivision(maraEntity.getSpart());
        materialGlobalBo.setBatchManageIndicator(maraEntity.getXchpf());
        materialGlobalBo.setMinRemShelfLife(maraEntity.getMhdrz());
        materialGlobalBo.setTotalShelfLife(maraEntity.getMhdhb());

        resultObject.setBaseBo(materialGlobalBo);
        return resultObject;
    }
}

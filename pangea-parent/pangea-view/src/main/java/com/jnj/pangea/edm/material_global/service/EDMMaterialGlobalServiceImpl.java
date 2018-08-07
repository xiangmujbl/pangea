package com.jnj.pangea.edm.material_global.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.p360.MaterialEntity;
import com.jnj.pangea.common.entity.p360.MaterialCrossReferenceEntity;
import com.jnj.pangea.common.entity.plan.EdmMatInputEntity;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
import com.jnj.pangea.common.entity.project_one.MaktEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMaktDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.p360.P360MaterialCrossReferenceDaoImpl;
import com.jnj.pangea.common.dao.impl.p360.P360MaterialDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.EdmMatInputDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material_global.bo.EDMMaterialGlobalBo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class EDMMaterialGlobalServiceImpl implements ICommonService {

	private static EDMMaterialGlobalServiceImpl instance;
	private static final String EN = "E";
	private static final String PT = "P";
	private static final String SP = "S";
	private static final String NG_EMS = "NG_EMS";

	public static EDMMaterialGlobalServiceImpl getInstance() {
		if (instance == null) {
			instance = new EDMMaterialGlobalServiceImpl();
		}
		return instance;
	}

	private ProjectOneMaktDaoImpl maktDao = ProjectOneMaktDaoImpl.getInstance();
	private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
	private P360MaterialCrossReferenceDaoImpl p360MaterialCrossReferenceDao = P360MaterialCrossReferenceDaoImpl
			.getInstance();
	private P360MaterialDaoImpl p360MaterialDao = P360MaterialDaoImpl.getInstance();
	private EdmMatInputDaoImpl edmMatInputDaoImpl = EdmMatInputDaoImpl.getInstance();

	@Override
	public ResultObject buildView(String key, Object o, Object o2) {

		ResultObject resultObject = new ResultObject();
		MaraEntity maraEntity = (MaraEntity) o;
		String sourceSystem = "";

		EDMMaterialGlobalBo materialGlobalBo = new EDMMaterialGlobalBo();
		// rules T1
		EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
		if (null != sourceSystemV1Entity) {
			sourceSystem = sourceSystemV1Entity.getSourceSystem();
			materialGlobalBo.setSourceSystem(sourceSystem);
		}

		// rules J1
		String localRefDescription = checkJ1(maraEntity.getMatnr());
		if (StringUtils.isNotEmpty(localRefDescription)) {
			materialGlobalBo.setLocalRefDescription(localRefDescription);
		}
		
		// rules J2
		String localMaterialNumber = maraEntity.getMatnr();

		if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
			List<EDMSourceSystemV1Entity> sourceSystemV1EntityList = sourceSystemV1Dao
					.getEntityListWithSourceSystemAndSourceSystemType(sourceSystem, NG_EMS);
			if (null != sourceSystemV1EntityList && sourceSystemV1EntityList.size() > 0) {
				String localSourceSystem = sourceSystemV1EntityList.get(0).getLocalSourceSystem();
				MaterialCrossReferenceEntity materialCrossReferenceEntity = p360MaterialCrossReferenceDao
						.getEntityWithLocalMaterialNumberAndLocalSourceSystem(localMaterialNumber, localSourceSystem);

				if (null != materialCrossReferenceEntity) {
					String materialNumber = materialCrossReferenceEntity.getEnterpriseMaterialNumber();
					if (StringUtils.isNotEmpty(materialNumber)) {
						MaterialEntity materialEntity = p360MaterialDao.getEntityWithMaterialNumber(materialNumber);
						if (null != materialEntity) {
							materialGlobalBo.setMaterialNumber(materialEntity.getEnterpriseMaterialNumber());
							materialGlobalBo.setRefDescription(materialEntity.getMaterialDescription());
							materialGlobalBo.setMaterialType(materialEntity.getMaterialType());
							materialGlobalBo.setBaseUom(materialEntity.getBaseUnitOfMeasure());
							materialGlobalBo.setParentCode(materialEntity.getParentCode());

							materialGlobalBo.setGlobalDpParentCode(materialEntity.getGlobalDpParentCode());
							materialGlobalBo.setForm(materialEntity.getForm());
							materialGlobalBo.setCategory(materialEntity.getCategory());
							materialGlobalBo.setSubBrand(materialEntity.getSubBrand());
							materialGlobalBo.setBrand(materialEntity.getBrand());
							materialGlobalBo.setFranchise(materialEntity.getFranchise());
							materialGlobalBo.setGlobalBusinessUnit(materialEntity.getGlobalBusinessUnitGbu());
							materialGlobalBo.setProductFamily(materialEntity.getProductFamily());
							materialGlobalBo.setManufacturingTechnology(materialEntity.getManufacturingTechnology());
							materialGlobalBo.setPrimaryPlanningCode(materialEntity.getEnterpriseMaterialNumber());
						}
					}
				}
			}
		}
		materialGlobalBo.setLocalMaterialNumber(maraEntity.getMatnr());
		materialGlobalBo.setLocalMaterialType(maraEntity.getMtart());
		materialGlobalBo.setLocalBaseUom(maraEntity.getMeins());
		materialGlobalBo.setLocalDpParentCode(maraEntity.getZzplnrootid());

		// rules T3
		if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
			EdmMatInputEntity edmMatInputEntity = edmMatInputDaoImpl
					.getEntityWithLocalMaterialNumberAndSourceSystem(localMaterialNumber, sourceSystem);
			if (null != edmMatInputEntity) {
				materialGlobalBo.setLocalManufacturingTechnology(edmMatInputEntity.getLocalTechnology());
			}
		}
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
	
	private String checkJ1(String matnr) {
		if (StringUtils.isNotEmpty(matnr)) {
			MaktEntity maktEntityEn = maktDao.getEntityWithMatnrAndSpras(matnr, EN);
			if (null != maktEntityEn) {
				return maktEntityEn.getMaktx();
			} else {
				MaktEntity maktEntityPt = maktDao.getEntityWithMatnrAndSpras(matnr, PT);
				if (null != maktEntityPt) {
					return maktEntityPt.getMaktx();
				} else {
					MaktEntity maktEntitySp = maktDao.getEntityWithMatnrAndSpras(matnr, SP);
					if (null != maktEntitySp) {
						return maktEntitySp.getMaktx();
					}
				}
			}
		}
		return null;
	}
}
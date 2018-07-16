package com.jnj.pangea.omp.gdm_product.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.common.entity.edm.EDMProductFamilyV1Entity;
import com.jnj.pangea.common.entity.edm.EDMFormV1Entity;
import com.jnj.pangea.common.entity.edm.EDMCategoryV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSubBrandV1Entity;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.entity.edm.EDMFranchiseV1Entity;
import com.jnj.pangea.common.entity.edm.EDMGlobalBusinessUnitV1Entity;
import com.jnj.pangea.omp.gdm_product.bo.OMPGdmProductBo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OMPGdmProductServiceImpl {

	private static OMPGdmProductServiceImpl instance;
	private static final String J1_MSG = "Unable to find DPParentCode";

	public static OMPGdmProductServiceImpl getInstance() {
		if (instance == null) {
			instance = new OMPGdmProductServiceImpl();
		}
		return instance;
	}

	private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
	private EDMProductFamilyV1DaoImpl productFamilyV1Dao = EDMProductFamilyV1DaoImpl.getInstance();
	private EDMFormV1DaoImpl formV1Dao = EDMFormV1DaoImpl.getInstance();
	private EDMCategoryV1DaoImpl categoryV1Dao = EDMCategoryV1DaoImpl.getInstance();
	private EDMSubBrandV1DaoImpl subBrandV1Dao = EDMSubBrandV1DaoImpl.getInstance();
	private EDMBrandV1DaoImpl brandV1Dao = EDMBrandV1DaoImpl.getInstance();
	private EDMFranchiseV1DaoImpl franchiseV1Dao = EDMFranchiseV1DaoImpl.getInstance();
	private EDMGlobalBusinessUnitV1DaoImpl globalBaseUnitV1Dao = EDMGlobalBusinessUnitV1DaoImpl.getInstance();
	private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
	private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
	private PlanCnsRootDescriptionDaoImpl rootDescriptionDao = PlanCnsRootDescriptionDaoImpl.getInstance();

	public List<ResultObject> buildView(String key, Object o, Object o2) {

		List<ResultObject> resultObjects = new LinkedList<>();
		EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

		String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
		String localDPParentCode = materialGlobalV1Entity.getLocalDpParentCode();
		String sourceSystem = materialGlobalV1Entity.getSourceSystem();
		String productFamily = materialGlobalV1Entity.getProductFamily();
		String form = materialGlobalV1Entity.getForm();
		String category = materialGlobalV1Entity.getCategory();
		String brand = materialGlobalV1Entity.getBrand();
		String franchise = materialGlobalV1Entity.getFranchise();
		String globalBusinessUnit = materialGlobalV1Entity.getGlobalBusinessUnit();
		String subBrand = materialGlobalV1Entity.getSubBrand();

		List<PlanCnsMaterialPlanStatusEntity> planCnsMaterialPlanStatusEntityList = cnsMaterialPlanStatusDao
				.getEntitiesWithLocalMaterialNumberAndSourceSystem(materialGlobalV1Entity.getLocalMaterialNumber(),
						materialGlobalV1Entity.getSourceSystem());

		boolean dpRelevant = false;
		boolean spRelevant = false;
		boolean noPlanRelevant = false;
		for (PlanCnsMaterialPlanStatusEntity entity : planCnsMaterialPlanStatusEntityList) {
			if (IConstant.VALUE.X.equalsIgnoreCase(entity.getDpRelevant())) {
				dpRelevant = true;
			}

			if (IConstant.VALUE.X.equalsIgnoreCase(entity.getSpRelevant())) {
				spRelevant = true;
			}

			if (IConstant.VALUE.X.equalsIgnoreCase(entity.getNoPlanRelevant())) {
				noPlanRelevant = true;
			}
		}

		if (planCnsMaterialPlanStatusEntityList.size() > 0) {
			if (dpRelevant || spRelevant || noPlanRelevant) {
				List<OMPGdmProductBo> productBos = new ArrayList<>();
				
				if (StringUtils.isNotEmpty(primaryPlanningCode)) {
					OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
					gdmProductBo.setProductId(primaryPlanningCode);
					String refDescription = materialGlobalV1Entity.getRefDescription();
					gdmProductBo.setDescription(refDescription);
					gdmProductBo.setShortDescription(refDescription);
					gdmProductBo.setActiveOPRERP(IConstant.VALUE.NO);
					gdmProductBo.setActiveFCTERP(IConstant.VALUE.NO);

					if (spRelevant || noPlanRelevant) {
						gdmProductBo.setActiveOPRERP(IConstant.VALUE.YES);
					}
					
					if (dpRelevant) {
						gdmProductBo.setActiveFCTERP(IConstant.VALUE.YES);
					}
					
					
					productBos.add(gdmProductBo);
				}

				String parameterValue = getParameterValue(sourceSystem);
				if (dpRelevant && StringUtils.isNotEmpty(localDPParentCode) && StringUtils.isNotEmpty(parameterValue)) {
					OMPGdmProductBo gdmProductBo1 = new OMPGdmProductBo();
					gdmProductBo1.setProductId(parameterValue + IConstant.VALUE.UNDERLINE + localDPParentCode);

					PlanCnsRootDescriptionEntity cnsRootDescriptionEntity = rootDescriptionDao
							.getEntityWithSourceSystemAndLocalDpParentCode(materialGlobalV1Entity.getSourceSystem(),
									materialGlobalV1Entity.getLocalDpParentCode());

					if (null != cnsRootDescriptionEntity) {
						String ovrRootDesc = cnsRootDescriptionEntity.getOvrRootDesc();

						if (StringUtils.isNotEmpty(ovrRootDesc)) {
							gdmProductBo1.setDescription(ovrRootDesc);
							gdmProductBo1.setShortDescription(ovrRootDesc);
						} else {
							gdmProductBo1.setDescription(cnsRootDescriptionEntity.getRootDesc());
							gdmProductBo1.setShortDescription(cnsRootDescriptionEntity.getRootDesc());
						}
					}

					gdmProductBo1.setActiveFCTERP(IConstant.VALUE.YES);
					gdmProductBo1.setActiveOPRERP(IConstant.VALUE.NO);

					gdmProductBo1.setProductId(parameterValue + IConstant.VALUE.UNDERLINE + localDPParentCode);
					productBos.add(gdmProductBo1);
				}

				if (0 == productBos.size()) {
					ResultObject resultObject = new ResultObject();
					FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1,
							J1_MSG);
					resultObject.setFailData(failData);
					resultObjects.add(resultObject);
					return resultObjects;
				}

				for (OMPGdmProductBo productBo : productBos) {
					ResultObject resultObject = new ResultObject();

					if (StringUtils.isEmpty(productBo.getProductId())) {
						continue;
					}

					productBo.setActive(IConstant.VALUE.YES);
					productBo.setActiveSOPERP(IConstant.VALUE.NO);

					productBo.setPlanningHierarchy1(productFamily);
					productBo.setPlanningHierarchy2(form);
					productBo.setPlanningHierarchy3(category);
					productBo.setPlanningHierarchy4(subBrand);
					productBo.setPlanningHierarchy5(brand);
					productBo.setPlanningHierarchy6(franchise);
					productBo.setPlanningHierarchy7(globalBusinessUnit);

					if (dpRelevant) {
						productBo.setPlanningHierarchy1Desc(checkE2(productFamily));
					}

					if (StringUtils.isNotEmpty(form)) {
						productBo.setPlanningHierarchy2Desc(checkE3(form));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E3, "There is no Form assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					if (StringUtils.isNotEmpty(category)) {
						productBo.setPlanningHierarchy3Desc(checkE4(category));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E4, "There is no Category assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					if (StringUtils.isNotEmpty(subBrand)) {
						productBo.setPlanningHierarchy4Desc(checkE5(subBrand));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E5, "There is no subBrand assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					if (StringUtils.isNotEmpty(brand)) {
						productBo.setPlanningHierarchy5Desc(checkE6(brand));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E6, "There is no brand assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					if (StringUtils.isNotEmpty(franchise)) {
						productBo.setPlanningHierarchy6Desc(checkE7(franchise));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E7, "There is no franchise assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					if (StringUtils.isNotEmpty(globalBusinessUnit)) {
						productBo.setPlanningHierarchy7Desc(checkE8(globalBusinessUnit));
					} else {
						if (dpRelevant) {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E8,
									"There is no globalBusinessUnit assigned for product");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					}

					productBo.setTechnology(materialGlobalV1Entity.getLocalManufacturingTechnology());

					String localBaseUom = materialGlobalV1Entity.getLocalBaseUom();
					if (StringUtils.isNotEmpty(localBaseUom)) {
						PlanCnsPlanUnitEntity planUnitEntity = checkE9(localBaseUom);
						if (null != planUnitEntity) {
							productBo.setUnitId(planUnitEntity.getUnit());
						} else {
							FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
									IConstant.FAILED.ERROR_CODE.E9,
									"No Plannable Enterprise UOM has been assigned to the local Unit");
							resultObject.setFailData(failData);
							resultObjects.add(resultObject);
							return resultObjects;
						}
					} else {
						FailData failData = writeFailDataToRegion(materialGlobalV1Entity,
								IConstant.FAILED.ERROR_CODE.E9,
								"No Plannable Enterprise UOM has been assigned to the local Unit");
						resultObject.setFailData(failData);
						resultObjects.add(resultObject);
						return resultObjects;
					}
					resultObject.setBaseBo(productBo);
					resultObjects.add(resultObject);
				}
			} else {
				ResultObject resultObject = new ResultObject();
				FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1,
						J1_MSG);
				resultObject.setFailData(failData);
				resultObjects.add(resultObject);
				return resultObjects;
			}
		} else {
			ResultObject resultObject = new ResultObject();
			FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1,
					J1_MSG);
			resultObject.setFailData(failData);
			resultObjects.add(resultObject);
			return resultObjects;
		}

		return resultObjects;
	}

	private String getParameterValue(String sourceSystem) {
		PlanCnsPlanParameterEntity planCnsPlanParameterEntity = planParameterDao
				.getEntityWithSourceSystemAndDataObject(sourceSystem, IConstant.VALUE.SEND_TO_OMP);
		if (null != planCnsPlanParameterEntity) {
			return planCnsPlanParameterEntity.getParameterValue();
		}
		return null;
	}

	private String checkE2(String productFamily) {
		if (null != productFamily && !"".equals(productFamily)) {
			EDMProductFamilyV1Entity productFamilyV1Entity = productFamilyV1Dao
					.getEntityWithProductFamily(productFamily);
			if (null != productFamilyV1Entity) {
				return productFamilyV1Entity.getProductFamilyName();
			}
		}
		return null;
	}

	private String checkE3(String form) {
		EDMFormV1Entity formV1Entity = formV1Dao.getEntityWithFormName(form);
		if (null != formV1Entity) {
			return formV1Entity.getFormDescription();
		}
		return null;
	}

	private String checkE4(String category) {
		EDMCategoryV1Entity categoryV1Entity = categoryV1Dao.getEntityWithCategory(category);
		if (null != categoryV1Entity) {
			return categoryV1Entity.getCategoryName();
		}
		return null;
	}

	private String checkE5(String subBrand) {
		EDMSubBrandV1Entity subBrandV1Entity = subBrandV1Dao.getEntityWithSubBrand(subBrand);
		if (null != subBrandV1Entity) {
			return subBrandV1Entity.getSubBrandDescription();
		}
		return null;
	}

	private String checkE6(String brand) {
		EDMBrandV1Entity brandV1Entity = brandV1Dao.getEntityWithBrand(brand);
		if (null != brandV1Entity) {
			return brandV1Entity.getBrandDescription();
		}
		return null;
	}

	private String checkE7(String franchise) {
		EDMFranchiseV1Entity franchiseV1Entity = franchiseV1Dao.getEntityWithFranchise(franchise);
		if (null != franchiseV1Entity) {
			return franchiseV1Entity.getFranchiseDescription();
		}
		return null;
	}

	private String checkE8(String globalBusinessUnit) {
		EDMGlobalBusinessUnitV1Entity globalBaseUnitV1Entity = globalBaseUnitV1Dao.getEntityWithGbu(globalBusinessUnit);
		if (null != globalBaseUnitV1Entity) {
			return globalBaseUnitV1Entity.getGbuName();
		}
		return null;
	}

	private PlanCnsPlanUnitEntity checkE9(String localBaseUom) {
		PlanCnsPlanUnitEntity planUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUom);
		return planUnitEntity;
	}

	private FailData writeFailDataToRegion(EDMMaterialGlobalV1Entity materialGlobalV1Entity, String ruleCode,
			String errorValue) {
		FailData failData = new FailData();
		failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
		failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT);
		failData.setErrorCode(ruleCode);
		failData.setSourceSystem(materialGlobalV1Entity.getSourceSystem());
		failData.setKey1(materialGlobalV1Entity.getLocalMaterialNumber());
		failData.setKey2("");
		failData.setKey3("");
		failData.setKey4("");
		failData.setKey5("");
		failData.setErrorValue(errorValue);
		return failData;
	}

}

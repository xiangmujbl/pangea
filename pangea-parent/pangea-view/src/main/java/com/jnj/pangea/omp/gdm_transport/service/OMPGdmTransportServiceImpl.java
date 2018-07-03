package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.CnsTlaneItemExceptionDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;

import java.text.SimpleDateFormat;
import java.util.*;

public class OMPGdmTransportServiceImpl extends OMPGdmTransportServiceParent {

    private static OMPGdmTransportServiceImpl instance;

    public static OMPGdmTransportServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmTransportServiceImpl();
        }
        return instance;
    }

    private CnsTlaneItemExceptionDaoImpl tlaneExceptionDao = CnsTlaneItemExceptionDaoImpl.getInstance();
	private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
	private EDMMaterialPlantV1DaoImpl materialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
	private EDMSourceListV1DaoImpl sourceListV1Dao = EDMSourceListV1DaoImpl.getInstance();
	private PlanCnsProcessTypeDaoImpl processTypeDao = PlanCnsProcessTypeDaoImpl.getInstance();


    public ResultObject buildView(String key, Object o, Object o2) {

		ResultObject resultObject = new ResultObject();
		OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();
		CnsTlaneItemEntity tlaneItemEntity = (CnsTlaneItemEntity) o;
		EDMSourceListV1Entity srcListV1Entity = null;
		String rule = "setup";
		this.curationSkip = false;
		this.curationFail = false;
		this.failedRules = new ArrayList<>();

		// check main data points are present
		if (tlaneItemEntity.getMaterialNumber() == null || tlaneItemEntity.getMaterialNumber().isEmpty()) {
			this.setFailedRule(rule, "Material Number null in CNS Tlane Item");
		}

		if (!this.curationFail && (tlaneItemEntity.getOriginLocation() == null || tlaneItemEntity.getOriginLocation().isEmpty())) {
			this.setFailedRule(rule, "Origin Location is Empty. Can't generate source system or plant.");
		}

		if (!this.curationFail && (tlaneItemEntity.getDestinationLocation() == null || tlaneItemEntity.getDestinationLocation().isEmpty())) {
			this.setFailedRule(rule, "Destination Location is Empty. Can't generate source system or plant.");
		}

		//N1
		if (!this.curationSkip && !this.curationFail) {
			gdmTransportBo.setTransportId(this.ruleN1(tlaneItemEntity));
		}

		if (!this.curationSkip && !this.curationFail) {
			//N2
			gdmTransportBo.setActive(IConstant.VALUE.YES);
			gdmTransportBo.setActiveOPRERP(IConstant.VALUE.YES);

			//N4
			gdmTransportBo.setMachineTypeId(IConstant.VALUE.TRANSPORT);

			//N5 version3 set default as 0.0
			gdmTransportBo.setMinQuantity(IConstant.VALUE.ZEROZERO);
			gdmTransportBo.setRequirementOffset(IConstant.VALUE.ZEROZERO);

			//N6
			gdmTransportBo.setPlanLevelId(IConstant.VALUE.STAR);
		}

		//N8
		if (!this.curationSkip && !this.curationFail) {
			gdmTransportBo.setPurchasingGroup(this.ruleN8(tlaneItemEntity));
		}

		//N9 & N10 Prep
		if (!this.curationSkip && !this.curationFail) {
			srcListV1Entity = this.ruleN9N10(tlaneItemEntity);
		}

		//N9 & N10
		if (!this.curationSkip && !this.curationFail && srcListV1Entity != null) {
			if(srcListV1Entity.getLocalPurchasingOrganization() == null
					|| "".equals(srcListV1Entity.getLocalPurchasingOrganization())){
				gdmTransportBo.setPurchasingOrganization(IConstant.VALUE.BLANK);
			}else{
				gdmTransportBo.setPurchasingOrganization(srcListV1Entity.getLocalPurchasingOrganization());

			}
			gdmTransportBo.setVendorId(srcListV1Entity.getLocalVendorAccountNumber());
		}

		//N11
		if (!this.curationSkip && !this.curationFail) {
			gdmTransportBo.setActiveSOPERP(IConstant.VALUE.NO);
		}

		//N13
		String validTo = tlaneItemEntity.getValidTo();
		SimpleDateFormat sdf = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
		try {
			Date validToDate = sdf.parse(validTo);
			Date maxDate = sdf.parse(IConstant.VALUE.MAX_DATE_VALIDTO);
			if(validToDate.getTime() > maxDate.getTime()){
				gdmTransportBo.setEndEff(IConstant.VALUE.MAX_DATE_VALIDTO + IConstant.VALUE.HH_NN_SS_ZERO);
			}else{
				gdmTransportBo.setEndEff(tlaneItemEntity.getValidTo() + IConstant.VALUE.HH_NN_SS_ZERO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		//No Rules
		if (!this.curationSkip && !this.curationFail) {
			PlanCnsProcessTypeEntity processTypeEntity =
					this.processTypeDao.getCnsProcessTypeById(tlaneItemEntity.getProcessTypeId());
			gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
			//gdmTransportBo.setEndEff(tlaneItemEntity.getValidTo() + IConstant.VALUE.HH_NN_SS_ZERO);
			gdmTransportBo.setStartEff(tlaneItemEntity.getValidFrom() + IConstant.VALUE.HH_NN_SS_ZERO);
			gdmTransportBo.setProcessTypeId(tlaneItemEntity.getProcessTypeId());
			gdmTransportBo.setTransportOffset(tlaneItemEntity.getLeadTime());
			gdmTransportBo.setTransportType(tlaneItemEntity.getMode());
			gdmTransportBo.setFromProductId(tlaneItemEntity.getMaterialNumber());
			gdmTransportBo.setToProductId(tlaneItemEntity.getMaterialNumber());
			gdmTransportBo.setFromLocationId(tlaneItemEntity.getOriginLocation());
			gdmTransportBo.setToLocationId(tlaneItemEntity.getDestinationLocation());
		}

		// set as a fail or as a success
		if (this.curationFail) {
			// failedRules could have multiple records in future if curationFail condition above is
			// removed (should stop skipping on 1st fail). for now get 1st fail only
			resultObject.setFailData(new FailData("SP", "OMPGdmTransport",
							this.failedRules.get(0).get("rule"), this.failedRules.get(0).get("error"),
							this.getSourceSystem(tlaneItemEntity.getOriginLocation()),
							this.getSourceSystem(tlaneItemEntity.getOriginLocation()),
							this.getLocalPlantNum(tlaneItemEntity.getOriginLocation()),
							tlaneItemEntity.getMaterialNumber()
					)
			);
		} else if (this.curationSkip) {
			resultObject = null;
		} else {
			resultObject.setBaseBo(gdmTransportBo);
		}

		return resultObject;
    }

	/**
	 * 1) get mat_glob_v1.local_material_number where mat_glob_v1.ppcode = tlane.matnum
	 * 2) else get mat_glob_v1.local_material_number where mat_glob_v1.material_num = tlane.matnum
	 * @param tlaneItemEntity
	 * @return
	 */
	private String getMaterialNumber (CnsTlaneItemEntity tlaneItemEntity, String srcSystem) {

		String locMatNum = null;
		List<EDMMaterialGlobalV1Entity> matGlobV1EntityList = this.materialGlobalV1Dao.getEntitiesWithPrimaryPlanningCodeAndSourceSystem(tlaneItemEntity.getMaterialNumber(),srcSystem);

		if (!matGlobV1EntityList.isEmpty()) {
			locMatNum = matGlobV1EntityList.get(0).getLocalMaterialNumber();
		}
		else {
			matGlobV1EntityList = this.materialGlobalV1Dao.getEntitiesWithMaterialNumber(tlaneItemEntity.getMaterialNumber());
			if (!matGlobV1EntityList.isEmpty()) {
				locMatNum = matGlobV1EntityList.get(0).getLocalMaterialNumber();
			}
		}

		return locMatNum;
	}

	/**
	 * 1) check for deletion indicator blank
	 * 2) skip if not.
	 * 3) concat mat num, origin loc and dest loc as transport id
	 *
	 * @param tlaneItemEntity
	 * @return
	 */
	private String ruleN1 (CnsTlaneItemEntity tlaneItemEntity) {

		String transportId = null;
		String rule = "N1";

		List<CnsTlaneItemExceptionEntity> tlaneItemExceptionEntities =
				this.tlaneExceptionDao.getEntitiesWithSeqNumMatNumTlaneName(
						tlaneItemEntity.getSequenceNumber(),
						tlaneItemEntity.getMaterialNumber(),
						tlaneItemEntity.getTlaneName()
				);

		if (!tlaneItemExceptionEntities.isEmpty()) {
			for (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity : tlaneItemExceptionEntities) {
				if (tlaneItemExceptionEntity.getDeletionIndicator().equals("X")) {
					this.curationSkip = true;
					return null;
				}
			}
		}

		transportId = tlaneItemEntity.getMaterialNumber() + IConstant.VALUE.BACK_SLANT
				+ tlaneItemEntity.getOriginLocation() + IConstant.VALUE.BACK_SLANT
				+ tlaneItemEntity.getDestinationLocation();

		return transportId;
	}

	/**
	 * 1) no matnum ? skip : continue
	 * 2) get mat_plant_v1.localPurchaseGrp where mat_plant_v1.locMatNum = locMatNum and
	 * mat_plant_v1.locPlantNum = locPlntNum and mat_plant_v1.srcSys = srcSys
	 *
	 * @param tlaneItemEntity
	 * @return
	 */
	private String ruleN8 (CnsTlaneItemEntity tlaneItemEntity) {

		String purchaseGroup = null;

		String rule = "N8";

		String localPlantNum = this.getLocalPlantNum(tlaneItemEntity.getDestinationLocation());
		String sourceSystem = this.getSourceSystem(tlaneItemEntity.getDestinationLocation());
		String locMatNum = this.getMaterialNumber(tlaneItemEntity,sourceSystem);

		//skip rest if no mat num was found
		if (locMatNum == null) {
			//this.curationSkip = true;
			return null;
		}

		List<EDMMaterialPlantV1Entity> materialPlantV1EntityList = this.materialPlantV1Dao.getEntitiesWithLocalMaterialNumberLocalPlantNumberSourceSystem(locMatNum,localPlantNum,sourceSystem);

		if (!materialPlantV1EntityList.isEmpty()) {
			purchaseGroup = materialPlantV1EntityList.get(0).getPurchsngGrpCd();
		}
		else {

			//this.curationSkip = true;
			return null;
		}

		return purchaseGroup;
	}

	/**
	 * 1) no matnum ? skip : continue
	 * 2) ...
	 *
	 * @param tlaneItemEntity
	 * @return
	 */
	private EDMSourceListV1Entity ruleN9N10 (CnsTlaneItemEntity tlaneItemEntity) {

		String rule = "N9_N10";

		String localPlantNum = this.getLocalPlantNum(tlaneItemEntity.getDestinationLocation());
		String sourceSystem = this.getSourceSystem(tlaneItemEntity.getDestinationLocation());
		String locMatNum = this.getMaterialNumber(tlaneItemEntity,sourceSystem);

		//skip rest if no mat num was found
		if (locMatNum == null) {
			//this.curationSkip = true;
			return null;
		}

		List<EDMSourceListV1Entity> sourceListV1EntityList = this.sourceListV1Dao.getEntitiesWithLocalMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(locMatNum,localPlantNum,sourceSystem);
		EDMSourceListV1Entity sourceListV1Entity;

		if (!sourceListV1EntityList.isEmpty()) {
			sourceListV1Entity = sourceListV1EntityList.get(0);
		}
		else {
			//this.curationSkip = true;
			return null;
		}

		return sourceListV1Entity;
	}

}

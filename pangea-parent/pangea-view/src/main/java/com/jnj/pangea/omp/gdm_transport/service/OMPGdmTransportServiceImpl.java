package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceListV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.CnsTlaneItemExceptionDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemEntity;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;

import java.util.*;

public class OMPGdmTransportServiceImpl {

    private static OMPGdmTransportServiceImpl instance;

    public static OMPGdmTransportServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmTransportServiceImpl();
        }
        return instance;
    }

    private PlanCnsProcessTypeDaoImpl processTypeDao = PlanCnsProcessTypeDaoImpl.getInstance();
    private CnsTlaneItemExceptionDaoImpl tlaneExceptionDao = CnsTlaneItemExceptionDaoImpl.getInstance();
    private EDMSourceListV1DaoImpl sourceListDao = EDMSourceListV1DaoImpl.getInstance();
	private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
	private EDMMaterialPlantV1DaoImpl materialPlantDao = EDMMaterialPlantV1DaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
		OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();

		CnsTlaneItemEntity tlaneItemEntity = (CnsTlaneItemEntity) o;

		boolean curationFail = false;
		List<Map<String, String>> failedRules = new ArrayList<>();

		String transportId = "";
		List<CnsTlaneItemExceptionEntity> tlaneItemExceptionEntities;
		String destLocalPlantNum = null;
		String destSourceSystem = null;
		String localMatNum = null;

		//N....
        PlanCnsProcessTypeEntity processTypeEntity = this.processTypeDao.getCnsProcessTypeById(tlaneItemEntity.getProcessTypeId());
        if (processTypeEntity != null &&
                processTypeEntity.getProcessTypeDesc() != null) {
            gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
        }

        if (tlaneItemEntity.getSequenceNumber() == null ||
                tlaneItemEntity.getSequenceNumber().equals("")) {
            curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N1");
			error.put("error", "Sequence number null");
			failedRules.add(error);
        }

        if (!curationFail) {

			//N1
			tlaneItemExceptionEntities = this.tlaneExceptionDao.getEntitiesWithRefSeqNumTlaneItem(tlaneItemEntity.getSequenceNumber());

            for (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity:tlaneItemExceptionEntities) {

				if (tlaneItemExceptionEntity != null &&
						tlaneItemExceptionEntity.getSequenceNumber() != null) {

					if (tlaneItemExceptionEntity.getDeletionIndicator().equals("X")) {
						curationFail = true;
						Map<String,String> error = new HashMap<>();
						error.put("rule", "N1");
						error.put("error", "Record found in tlane item exception with deletion indicator X");
						failedRules.add(error);
					}
				} else {
					curationFail = true;
					Map<String,String> error = new HashMap<>();
					error.put("rule", "N1");
					error.put("error", "Record found in tlane item exception");
					failedRules.add(error);
				}
			}

			if (!curationFail) {

                transportId = this.doRuleN1(tlaneItemEntity);
				gdmTransportBo.setTransportId(transportId);
			}
        }

		// check material no not null
		if (tlaneItemEntity.getMaterialNumber() == null) {
			curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N/A");
			error.put("error", "materialNumber null in CNS Tlane Item Exception");
			failedRules.add(error);
		}

		EDMMaterialGlobalV1Entity materialGlobalV1Entity = null;

		// set materials entity
		if (!curationFail) {
			materialGlobalV1Entity = this.materialGlobalDao.getEntityWithPrimaryPlanningCode(tlaneItemEntity.getMaterialNumber());

			if (materialGlobalV1Entity == null ||
					materialGlobalV1Entity.getMaterialNumber() == null ||
					materialGlobalV1Entity.getMaterialNumber().equals("")) {
				curationFail = true;

				Map<String,String> error = new HashMap<>();
				error.put("rule", "N/A");
				error.put("error", "Material Number doesn't exist in region Global Material V1");
				failedRules.add(error);
			}
		}

		// check dest location not null
		if (tlaneItemEntity.getOriginLocation() == null ||
				tlaneItemEntity.getOriginLocation().equals("")) {
			curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N/A");
			error.put("error", "tlaneItemEntity.getOriginLocation() is empty");
			failedRules.add(error);
		}

		// check dest location not null
		if (tlaneItemEntity.getDestinationLocation() == null ||
				tlaneItemEntity.getDestinationLocation().equals("")) {
			curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N/A");
			error.put("error", "tlaneItemEntity.getDestinationLocation() is empty");
			failedRules.add(error);
		}

		EDMSourceListV1Entity sourceListV1Entity = null;
		EDMMaterialPlantV1Entity materialPlantV1Entity = null;

		if (!curationFail) {
			destLocalPlantNum = this.getLocalPlantNum(tlaneItemEntity.getDestinationLocation());
			destSourceSystem = this.getSourceSystem(tlaneItemEntity.getDestinationLocation());

			// get the mat num if curation hasn't failed (will fail if entity not found)
			localMatNum = materialGlobalV1Entity.getMaterialNumber();

			sourceListV1Entity = this.sourceListDao.getEntityWithMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(localMatNum, destLocalPlantNum, destSourceSystem);
			materialPlantV1Entity = this.materialPlantDao.getEntityWithMaterialNumberPlantNumberSourceSystem(localMatNum, destLocalPlantNum, destSourceSystem);
		}

		if (materialPlantV1Entity == null ||
				materialPlantV1Entity.getLocalPurchasingGroup() == null ||
				materialPlantV1Entity.getLocalPurchasingGroup().equals(""))  {

			curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N8");
			error.put("error", "No Matching Record found in EDMMaterialPlantV1Entity");
			failedRules.add(error);
		}

		if (sourceListV1Entity == null ||
				sourceListV1Entity.getLocalPurchasingOrganization() == null ||
				sourceListV1Entity.getLocalPurchasingOrganization().equals("")) {

			curationFail = true;
			Map<String,String> error = new HashMap<>();
			error.put("rule", "N9 & N10");
			error.put("error", "No Matching Record found in EDMSourceListV1Entity");
			failedRules.add(error);
		}

		if (!curationFail) {

			//N8
			gdmTransportBo.setPurchasingGroup(materialPlantV1Entity.getLocalPurchasingGroup());
			//N9
			gdmTransportBo.setPurchasingOrganization(sourceListV1Entity.getLocalPurchasingOrganization());
			//N10
			gdmTransportBo.setVendorId(sourceListV1Entity.getLocalVendorAccountNumber());

			//simple rules
			gdmTransportBo.setActive(IConstant.VALUE.YES);
			gdmTransportBo.setActiveOPRERP(IConstant.VALUE.YES);
			gdmTransportBo.setActiveSOPERP(IConstant.VALUE.NO);
			gdmTransportBo.setMachineTypeId(IConstant.VALUE.TRANSPORT);
			gdmTransportBo.setMinQuantity("");
			gdmTransportBo.setPlanLevelId(IConstant.VALUE.STAR);
			gdmTransportBo.setRequirementOffset("");

			//no rules
			gdmTransportBo.setEndEff(tlaneItemEntity.getValidTo());
			gdmTransportBo.setFromLocationId(tlaneItemEntity.getOriginLocation());
			gdmTransportBo.setFromProductId(tlaneItemEntity.getMaterialNumber());
			gdmTransportBo.setProcessTypeId(tlaneItemEntity.getProcessTypeId());
			gdmTransportBo.setStartEff(tlaneItemEntity.getValidFrom());
			gdmTransportBo.setToLocationId(tlaneItemEntity.getDestinationLocation());
			gdmTransportBo.setToProductId(tlaneItemEntity.getMaterialNumber());
			gdmTransportBo.setTransportOffset(tlaneItemEntity.getLeadTime());
			gdmTransportBo.setTransportType(tlaneItemEntity.getMode());
		}

		// set as a fail or as a success
        if (curationFail) {
			resultObject.setFailData(new FailData("SP", "OMPGdmTransport", failedRules.get(0).get("rule"), failedRules.get(0).get("error"),
							this.getSourceSystem(tlaneItemEntity.getOriginLocation()),
							this.getSourceSystem(tlaneItemEntity.getOriginLocation()),
							this.getLocalPlantNum(tlaneItemEntity.getOriginLocation()),
					tlaneItemEntity.getMaterialNumber()
					)
			);
        } else {
            resultObject.setBaseBo(gdmTransportBo);
        }

        return resultObject;
    }


    private String doRuleN1 (CnsTlaneItemEntity tlaneItemEntity) {
        return tlaneItemEntity.getMaterialNumber() + IConstant.VALUE.BACK_SLANT+
                tlaneItemEntity.getOriginLocation() + IConstant.VALUE.BACK_SLANT+
                tlaneItemEntity.getDestinationLocation();
    }

	private ArrayList<String> getLocationStringArray (String location) {
		String[] locArray = location.split("_");
		return new ArrayList<>(Arrays.asList(locArray));
	}

	private String getLocalPlantNum (String location) {
		ArrayList<String> locList = getLocationStringArray(location);
		return locList.get(locList.size() - 1);
	}

	private String getSourceSystem (String location) {
		ArrayList<String> locList = getLocationStringArray(location);
		locList.remove(locList.size() - 1);
		return String.join("_", locList);
	}
}

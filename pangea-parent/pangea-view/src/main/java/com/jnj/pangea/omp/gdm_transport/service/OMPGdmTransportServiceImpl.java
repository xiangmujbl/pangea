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

import java.util.ArrayList;
import java.util.Arrays;

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

    private boolean curationFail = false;
    private String rule = null;
    private String error = null;

    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        CnsTlaneItemEntity tlaneItemEntity = (CnsTlaneItemEntity) o;

        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();

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

        //N
        PlanCnsProcessTypeEntity processTypeEntity = this.processTypeDao.getCnsProcessTypeById(tlaneItemEntity.getProcessTypeId());
        if (processTypeEntity != null &&
                processTypeEntity.getProcessTypeDesc() != null) {
            gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
        }

        //N1
        String transportId = "";
        CnsTlaneItemExceptionEntity tlaneItemExceptionEntity;

        if (tlaneItemEntity.getSequenceNumber() == null ||
                tlaneItemEntity.getSequenceNumber().equals("")) {
            this.curationFail = true;
            this.rule = "N1";
            this.error = "Sequence number null";
        }

        if (!this.curationFail) {
            tlaneItemExceptionEntity = this.tlaneExceptionDao.getEntityWithRefSeqNumTlaneItem(tlaneItemEntity.getSequenceNumber());

            if (tlaneItemExceptionEntity != null &&
                    tlaneItemExceptionEntity.getSequenceNumber() != null) {
                if (!tlaneItemExceptionEntity.getDeletionIndicator().equals("X")) {
                    transportId = this.doRuleN1(tlaneItemEntity);
                } else {
                    this.curationFail = true;
                    this.rule = "N1";
                    this.error = "Record found in tlane item exception but deletion indicator X";
                }
            } else {
                transportId = this.doRuleN1(tlaneItemEntity);
            }
        }
        gdmTransportBo.setTransportId(transportId);

		if (tlaneItemEntity.getMaterialNumber() == null) {
			this.curationFail = true;
			this.rule = "NA";
			this.error = "materialNumber null in CNS Tlane Item Exception";
		}

		EDMMaterialGlobalV1Entity materialGlobalV1Entity = null;

		if (!this.curationFail) {
			// set materials entity
			materialGlobalV1Entity = this.materialGlobalDao.getEntityWithPrimaryPlanningCode(tlaneItemEntity.getMaterialNumber());

			if (materialGlobalV1Entity == null ||
					materialGlobalV1Entity.getMaterialNumber() == null ||
					materialGlobalV1Entity.getMaterialNumber().equals("")) {
				this.curationFail = true;
				this.rule = "NA";
				this.error = "Material Number doesn't exist in region Global Material V1";
			}
		}


		String destLocalPlantNum = null;
		String destSourceSystem = null;
		String localMatNum = null;

		if (tlaneItemEntity.getOriginLocation() == null || tlaneItemEntity.getOriginLocation().equals("")) {
			this.curationFail = true;
			this.rule = "NA";
			this.error = "tlaneItemEntity.getOriginLocation() is empty";
		}

		// set source systems and locations
		if (tlaneItemEntity.getDestinationLocation() == null || tlaneItemEntity.getDestinationLocation().equals("")) {
			this.curationFail = true;
			this.rule = "NA";
			this.error = "tlaneItemEntity.getDestinationLocation() is empty";
		}

		if (materialGlobalV1Entity == null ||
				materialGlobalV1Entity.getMaterialNumber() == null ||
					materialGlobalV1Entity.getMaterialNumber().equals("")) {
			this.curationFail = true;
			this.rule = "NA";
			this.error = "materialNumber null in Material Global V1";
		}

		if (!this.curationFail) {
			destLocalPlantNum = this.getLocalPlantNum(tlaneItemEntity.getDestinationLocation());
			destSourceSystem = this.getSourceSystem(tlaneItemEntity.getDestinationLocation());

			// get the mat num if curation hasn't failed (will fail if entity not found)
			localMatNum = materialGlobalV1Entity.getMaterialNumber();
		}

		EDMSourceListV1Entity sourceListV1Entity = this.sourceListDao.getEntityWithMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(localMatNum, destLocalPlantNum, destSourceSystem);
		EDMMaterialPlantV1Entity materialPlantV1Entity = this.materialPlantDao.getEntityWithMaterialNumberPlantNumberSourceSystem(localMatNum, destLocalPlantNum, destSourceSystem);

		//N8
		if (materialPlantV1Entity != null &&
				materialPlantV1Entity.getLocalPurchasingGroup() != null &&
				!materialPlantV1Entity.getLocalPurchasingGroup().equals("")) {
			gdmTransportBo.setPurchasingGroup(materialPlantV1Entity.getLocalPurchasingGroup());
		} else {
			this.curationFail = true;
			this.rule = "N8";
			this.error = "No Matching Record found in EDMMaterialPlantV1Entity";
		}

		if (sourceListV1Entity != null &&
				sourceListV1Entity.getLocalPurchasingOrganization() != null &&
				!sourceListV1Entity.getLocalPurchasingOrganization().equals("")) {
			//N9
			gdmTransportBo.setPurchasingOrganization(sourceListV1Entity.getLocalPurchasingOrganization());
			//N10
			gdmTransportBo.setVendorId(sourceListV1Entity.getLocalVendorAccountNumber());

		} else {
			this.curationFail = true;
			this.rule = "N9 & N10";
			this.error = "No Matching Record found in EDMSourceListV1Entity";
		}

		// set as a fail or as a success
        if (this.curationFail) {
            resultObject.setFailData(this.writeFailDataToRegion(tlaneItemEntity, this.rule, this.error));
//            LogUtil.getCoreLog().info("***************************************FAILED************************************");
//            LogUtil.getCoreLog().info("RULE: " + this.rule);
//            LogUtil.getCoreLog().info("ERROR: " + this.error);
//            LogUtil.getCoreLog().info("***************************************FAILED************************************");
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

    private FailData writeFailDataToRegion(CnsTlaneItemEntity cnsTlaneItemEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmTransport");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(this.getSourceSystem(cnsTlaneItemEntity.getOriginLocation()));
        failData.setKey1(this.getSourceSystem(cnsTlaneItemEntity.getOriginLocation()));
        failData.setKey2(this.getLocalPlantNum(cnsTlaneItemEntity.getOriginLocation()));
        failData.setKey3(cnsTlaneItemEntity.getMaterialNumber());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }

}

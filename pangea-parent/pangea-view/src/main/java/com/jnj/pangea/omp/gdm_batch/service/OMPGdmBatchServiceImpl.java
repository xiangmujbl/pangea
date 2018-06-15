package com.jnj.pangea.omp.gdm_batch.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMInventoryStockDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBatchMasterV1Entity;
import com.jnj.pangea.common.entity.edm.EDMInventoryStockEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_batch.bo.OMPGdmBatchBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmBatchServiceImpl implements ICommonService {

    private static OMPGdmBatchServiceImpl instance;

    public static OMPGdmBatchServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBatchServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMInventoryStockDaoImpl inventoryStockDao = EDMInventoryStockDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMBatchMasterV1Entity batchMasterV1Entity = (EDMBatchMasterV1Entity) o;

        OMPGdmBatchBo gdmBatchBo = new OMPGdmBatchBo();
        if(batchMasterV1Entity==null){
            return resultObject;
        }
        //rules N2
        String result=getFieldWithN2(batchMasterV1Entity);
        if(IConstant.VALUE.Y.equals(result)){
            String matlNum = batchMasterV1Entity.getMatlNum();
            String srcSysCd = batchMasterV1Entity.getSrcSysCd();
            String btchNum = batchMasterV1Entity.getBtchNum();
            if (StringUtils.isNotEmpty(matlNum)){

                EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(matlNum);
                if (null != materialGlobalV1Entity) {

                    if(StringUtils.isEmpty(materialGlobalV1Entity.getPrimaryPlanningCode()) && StringUtils.isEmpty(materialGlobalV1Entity.getMaterialNumber())){

                        FailData failData = new FailData();
                        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
                        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_BATCH);
                        failData.setErrorCode(IConstant.FAILED.ERROR_CODE.N2);
                        failData.setSourceSystem("");
                        failData.setKey1(batchMasterV1Entity.getBtchNum());
                        failData.setKey2("");
                        failData.setKey3("");
                        failData.setKey4("");
                        failData.setKey5("");
                        failData.setErrorValue("");
                        resultObject.setFailData(failData);
                        return resultObject;
                    } else {

                        if (StringUtils.isNotEmpty(materialGlobalV1Entity.getPrimaryPlanningCode()) && StringUtils.isEmpty(materialGlobalV1Entity.getMaterialNumber())) {

                            gdmBatchBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                        } else if (StringUtils.isEmpty(materialGlobalV1Entity.getPrimaryPlanningCode()) && StringUtils.isNotEmpty(materialGlobalV1Entity.getMaterialNumber())) {

                            gdmBatchBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                        } else {

                            gdmBatchBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                        }

                        if (StringUtils.isNotEmpty(srcSysCd) && StringUtils.isNotEmpty(btchNum)) {

                            EDMInventoryStockEntity inventoryStockEntity =inventoryStockDao.getEntityWithWithSourceSystemAndLocalBatchIdAndLocalMaterial(srcSysCd, btchNum, matlNum);
                            if (null != inventoryStockEntity && checkFloatDataGreaterThanZero(inventoryStockEntity)
                                    && StringUtils.isNotEmpty(batchMasterV1Entity.getLocalPlant())){

                                //rules N3
                                String locationId = batchMasterV1Entity.getSrcSysCd() + "_" + batchMasterV1Entity.getLocalPlant();
                                String batchId = gdmBatchBo.getProductId() + "/" + locationId + "/" + batchMasterV1Entity.getBtchNum();
                                gdmBatchBo.setBatchId(batchId);

                                //rules N1
                                gdmBatchBo.setActive(IConstant.VALUE.YES);
                                gdmBatchBo.setActiveOPRERP(IConstant.VALUE.YES);
                                // rules N4
                                gdmBatchBo.setActiveSOPERP(IConstant.VALUE.NO);

                                // rules N5
                                if (StringUtils.isNotEmpty(batchMasterV1Entity.getBtchExpDt())) {

                                    gdmBatchBo.setExpirationDate(batchMasterV1Entity.getBtchExpDt());
                                } else{

                                    gdmBatchBo.setExpirationDate(IConstant.VALUE.END_EFF_CHECK + IConstant.VALUE.HH_NN_SS_ZERO);
                                }

                                // rules N6
                                if (StringUtils.isNotEmpty(batchMasterV1Entity.getBtchMfgDt())) {

                                    gdmBatchBo.setManufacturingDate(batchMasterV1Entity.getBtchMfgDt());
                                } else {

                                    gdmBatchBo.setManufacturingDate(IConstant.VALUE.START_EFF_CHECK + IConstant.VALUE.HH_NN_SS_ZERO);
                                }

                                resultObject.setBaseBo(gdmBatchBo);
                            }
                        }
                    }
                }
            }
        }

        return resultObject;
    }

    /**
     * rules N3
     * @param inventoryStockEntity
     * @return
     */
    private Boolean checkFloatDataGreaterThanZero(EDMInventoryStockEntity inventoryStockEntity) {

        float localUnrestrictedStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalUnrestrictedStock())) {
            localUnrestrictedStock = Float.parseFloat(inventoryStockEntity.getLocalUnrestrictedStock());
        }

        float localUnrestrictedSpecialStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalUnrestrictedSpecialStock())) {
            localUnrestrictedSpecialStock = Float.parseFloat(inventoryStockEntity.getLocalUnrestrictedSpecialStock());
        }

        float localUnrestrictedBatchStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalUnrestrictedBatchStock())) {
            localUnrestrictedBatchStock = Float.parseFloat(inventoryStockEntity.getLocalUnrestrictedBatchStock());
        }

        float localUnrestrictedConsignmentStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalUnrestrictedConsignmentStock())) {
            localUnrestrictedConsignmentStock = Float.parseFloat(inventoryStockEntity.getLocalUnrestrictedConsignmentStock());
        }

        float localQualityInspectionStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalQualityInspectionStock())) {
            localQualityInspectionStock = Float.parseFloat(inventoryStockEntity.getLocalQualityInspectionStock());
        }

        float localQualityInspectionSpecialStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalQualityInspectionSpecialStock())) {
            localQualityInspectionSpecialStock = Float.parseFloat(inventoryStockEntity.getLocalQualityInspectionSpecialStock());
        }

        float localQualityInspectionBatchStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalQualityInspectionBatchStock())) {
            localQualityInspectionBatchStock = Float.parseFloat(inventoryStockEntity.getLocalQualityInspectionBatchStock());
        }

        float localQualityInspectionConsignmentStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalQualityInspectionConsignmentStock())) {
            localQualityInspectionConsignmentStock = Float.parseFloat(inventoryStockEntity.getLocalQualityInspectionConsignmentStock());
        }

        float localRestrictedStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalRestrictedStock())) {
            localRestrictedStock = Float.parseFloat(inventoryStockEntity.getLocalRestrictedStock());
        }

        float localRestrictedSpecialStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalRestrictedSpecialStock())) {
            localRestrictedSpecialStock = Float.parseFloat(inventoryStockEntity.getLocalRestrictedSpecialStock());
        }

        float localRestrictedBatchStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalRestrictedSpecialStock())) {
            localRestrictedBatchStock = Float.parseFloat(inventoryStockEntity.getLocalRestrictedSpecialStock());
        }

        float localRestrictedConsignmentStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalRestrictedConsignmentStock())) {
            localRestrictedConsignmentStock = Float.parseFloat(inventoryStockEntity.getLocalRestrictedConsignmentStock());
        }

        float localBlockedStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalBlockedStock())) {
            localBlockedStock = Float.parseFloat(inventoryStockEntity.getLocalBlockedStock());
        }

        float localBlockedBatchStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalBlockedBatchStock())) {
            localBlockedBatchStock = Float.parseFloat(inventoryStockEntity.getLocalBlockedBatchStock());
        }

        float localBlockedConsignmentStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalBlockedConsignmentStock())) {
            localBlockedConsignmentStock = Float.parseFloat(inventoryStockEntity.getLocalBlockedConsignmentStock());
        }

        float localReturnsStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalReturnsStock())) {
            localReturnsStock = Float.parseFloat(inventoryStockEntity.getLocalReturnsStock());
        }

        float localReturnsBatchStock = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalReturnsBatchStock())) {
            localReturnsBatchStock = Float.parseFloat(inventoryStockEntity.getLocalReturnsBatchStock());
        }

        float localStockInTransitStorageLocation = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalStockInTransitStorageLocation())) {
            localStockInTransitStorageLocation = Float.parseFloat(inventoryStockEntity.getLocalStockInTransitStorageLocation());
        }

        float localStockInTransitPlantToPlant = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalStockInTransitPlantToPlant())) {
            localStockInTransitPlantToPlant = Float.parseFloat(inventoryStockEntity.getLocalStockInTransitPlantToPlant());
        }

        float localStockInTransitPlant = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalStockInTransitPlant())) {
            localStockInTransitPlant = Float.parseFloat(inventoryStockEntity.getLocalStockInTransitPlant());
        }

        float localStockInTransitSpecial = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalStockInTransitSpecial())) {
            localStockInTransitSpecial = Float.parseFloat(inventoryStockEntity.getLocalStockInTransitSpecial());
        }

        float localStockInTransitBatch = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalStockInTransitBatch())) {
            localStockInTransitBatch = Float.parseFloat(inventoryStockEntity.getLocalStockInTransitBatch());
        }

        float localRestrictedUseConsignment = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalRestrictedUseConsignment())) {
            localRestrictedUseConsignment = Float.parseFloat(inventoryStockEntity.getLocalRestrictedUseConsignment());
        }

        float localConsignmentStockInQualityInspection = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalConsignmentStockInQualityInspection())) {
            localConsignmentStockInQualityInspection = Float.parseFloat(inventoryStockEntity.getLocalConsignmentStockInQualityInspection());
        }

        float localUnrestrictedUseConsignment = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalUnrestrictedUseConsignment())) {
            localUnrestrictedUseConsignment = Float.parseFloat(inventoryStockEntity.getLocalUnrestrictedUseConsignment());
        }

        float localBlkdConstStkNonBm = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalBlkdConstStkNonBm())) {
            localBlkdConstStkNonBm = Float.parseFloat(inventoryStockEntity.getLocalBlkdConstStkNonBm());
        }

        float localTotalStockAllRestrictedBatches = 0;
        if (StringUtils.isNotEmpty(inventoryStockEntity.getLocalTotalStockAllRestrictedBatches())) {
            localTotalStockAllRestrictedBatches = Float.parseFloat(inventoryStockEntity.getLocalTotalStockAllRestrictedBatches());
        }

        if (localUnrestrictedStock > 0 ||
                localUnrestrictedSpecialStock > 0 ||
                localUnrestrictedBatchStock > 0 ||
                localUnrestrictedConsignmentStock > 0 ||
                localQualityInspectionStock > 0 ||
                localQualityInspectionSpecialStock > 0 ||
                localQualityInspectionBatchStock > 0 ||
                localQualityInspectionConsignmentStock > 0 ||
                localRestrictedStock > 0 ||
                localRestrictedSpecialStock > 0 ||
                localRestrictedBatchStock > 0 ||
                localRestrictedConsignmentStock > 0 ||
                localBlockedStock > 0 ||
                localBlockedBatchStock > 0 ||
                localBlockedConsignmentStock > 0 ||
                localReturnsStock > 0 ||
                localReturnsBatchStock > 0 ||
                localStockInTransitStorageLocation > 0 ||
                localStockInTransitPlantToPlant > 0 ||
                localStockInTransitPlant > 0 ||
                localStockInTransitSpecial > 0 ||
                localStockInTransitBatch > 0 ||
                localRestrictedUseConsignment  > 0 ||
                localConsignmentStockInQualityInspection > 0 ||
                localUnrestrictedUseConsignment > 0 ||
                localBlkdConstStkNonBm > 0 ||
                localTotalStockAllRestrictedBatches > 0
                ) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * rules N2
     * @param batchMasterV1Entity
     * @return
     */
    private String getFieldWithN2(EDMBatchMasterV1Entity batchMasterV1Entity) {

        String srcSysCd = batchMasterV1Entity.getSrcSysCd();
        String matlNum = batchMasterV1Entity.getMatlNum();
        String localPlant = batchMasterV1Entity.getLocalPlant();
        if(StringUtils.isNotEmpty(srcSysCd) && StringUtils.isNotEmpty(matlNum) && StringUtils.isNotEmpty(localPlant)) {
            PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusDaoEntity = cnsMaterialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity(srcSysCd, matlNum, localPlant);
            if(cnsMaterialPlanStatusDaoEntity == null){
                return IConstant.VALUE.N;
            }
            if((StringUtils.isNotEmpty(cnsMaterialPlanStatusDaoEntity.getSpRelevant()) && cnsMaterialPlanStatusDaoEntity.getSpRelevant().trim().equals(IConstant.VALUE.X)) || (StringUtils.isNotEmpty(cnsMaterialPlanStatusDaoEntity.getNoPlanRelevant()) &&  cnsMaterialPlanStatusDaoEntity.getNoPlanRelevant().trim().equals(IConstant.VALUE.X))){
                return IConstant.VALUE.Y;
            }
        }
        return IConstant.VALUE.N;
    }
}

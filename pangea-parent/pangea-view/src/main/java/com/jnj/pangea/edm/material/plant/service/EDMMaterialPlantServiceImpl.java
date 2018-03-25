package com.jnj.pangea.edm.material.plant.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMatPlantStatV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatPlantStatV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.project_one.MarcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.bo.EDMMaterialPlantBo;


public class EDMMaterialPlantServiceImpl implements ICommonService {

    private static EDMMaterialPlantServiceImpl instance;

    public static EDMMaterialPlantServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    EDMMatPlantStatV1DaoImpl matPlantStatV1Dao = EDMMatPlantStatV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        MarcEntity marcEntity = (MarcEntity) o;

        EDMMaterialPlantBo materialPlantBo = new EDMMaterialPlantBo();

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
        materialPlantBo.setSourceSystem(sourceSystem);

        String matnr = marcEntity.getMatnr();
        materialPlantBo.setLocalMaterialNumber(matnr);
        String werks = marcEntity.getWerks();
        materialPlantBo.setLocalPlant(werks);
        materialPlantBo.setLocalDeletionFlagPlant(werks);

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(sourceSystem,matnr);
        if (null != materialGlobalV1Entity){
            String materialNumber = materialGlobalV1Entity.getMaterialNumber();
            materialPlantBo.setMaterialNumber(materialNumber);
        }

        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,werks);
        if (null != plantV1Entity){
            String plant = plantV1Entity.getPlant();
            materialPlantBo.setPlant(plant);
        }

        String mmsta = marcEntity.getMmsta();
        materialPlantBo.setLocalPlantStatus(mmsta);

        EDMMatPlantStatV1Entity matPlantStatV1Entity = matPlantStatV1Dao.getPlantStatusWithLocalPlantStatusAndSourceSystem(sourceSystem,mmsta);
        if (null != matPlantStatV1Entity){
            String plantStatus = matPlantStatV1Entity.getPlantStatus();
            materialPlantBo.setPlantStatus(plantStatus);
        }

        String beskz = marcEntity.getBeskz();
        materialPlantBo.setLocalProcurementType(beskz);

        String bstfe = marcEntity.getBstfe();
        materialPlantBo.setLocalFixedlotsize(bstfe);

        String bstma = marcEntity.getBstma();
        materialPlantBo.setLocalMaximumLotSize(bstma);

        String bstmi = marcEntity.getBstmi();
        materialPlantBo.setLocalMinimumLotSize(bstmi);

        String bstrf = marcEntity.getBstrf();
        materialPlantBo.setLocalRoundingvalueForPoq(bstrf);

        String disls = marcEntity.getDisls();
        materialPlantBo.setLocalLotsize(disls);

        String dismm = marcEntity.getDismm();
        materialPlantBo.setLocalMrpType(dismm);

        String dispo = marcEntity.getDispo();
        materialPlantBo.setLocalMrpController(dispo);

        String dzeit = marcEntity.getDzeit();
        materialPlantBo.setLocalInHouseProcessingTime(dzeit);

        String eisbe = marcEntity.getEisbe();
        materialPlantBo.setLocalSafetyStock(eisbe);

        String eislo = marcEntity.getEislo();
        materialPlantBo.setLocalMinimumSafetyStock(eislo);

        String fevor = marcEntity.getFevor();
        materialPlantBo.setLocalProductionSupervisor(fevor);

        String frtme = marcEntity.getFrtme();
        materialPlantBo.setLocalProductionUnit(frtme);

        String insmk = marcEntity.getInsmk();
        materialPlantBo.setLocalPosttoInspectionStock(insmk);


        String kausf = marcEntity.getKausf();
        materialPlantBo.setLocalComponentScrapInPercent(kausf);

        String kzkri = marcEntity.getKzkri();
        materialPlantBo.setLocalCriticalpart(kzkri);

        String maabc = marcEntity.getMaabc();
        materialPlantBo.setLocalAbcIndicator(maabc);

        String mabst = marcEntity.getMabst();
        materialPlantBo.setLocalMaximumStocklevel(mabst);

        String mtvfp = marcEntity.getMtvfp();
        materialPlantBo.setLocalCheckingGroupforAvailabilityCheck(mtvfp);

        String plifz = marcEntity.getPlifz();
        materialPlantBo.setLocalPlannedDeliveryTimeInDays(plifz);

        String sbdkz = marcEntity.getSbdkz();
        materialPlantBo.setLocalDependentRequirements(sbdkz);

        String shflg = marcEntity.getShflg();
        materialPlantBo.setLocalSafetytimeIndicator(shflg);

        String shzet = marcEntity.getShzet();
        materialPlantBo.setLocalSafetyTime(shzet);

        String sobsl = marcEntity.getSobsl();
        materialPlantBo.setLocalSpecialProcurementType(sobsl);

        String strgr = marcEntity.getStrgr();
        materialPlantBo.setLocalPlanningStrategyGroup(strgr);

        String vint1 = marcEntity.getVint1();
        materialPlantBo.setLocalConsumptionPeriodBackward(vint1);

        String vint2 = marcEntity.getVint2();
        materialPlantBo.setLocalConsumptionPeriodForward(vint2);

        String vrmod = marcEntity.getVrmod();
        materialPlantBo.setLocalConsumptionMode(vrmod);

        String webaz = marcEntity.getWebaz();
        materialPlantBo.setLocalGoodsReceiptProcessingTimeInDays(webaz);

        String xchpf = marcEntity.getXchpf();
        materialPlantBo.setLocalBatchManagementRequirmentIndicator(xchpf);

        String fxhor = marcEntity.getFxhor();
        materialPlantBo.setLocalPlanningTimeFence(fxhor);

        materialPlantBo.setLocalPosttoinspstk(insmk);

        materialPlantBo.setLocalComponentScrap(kausf);

        resultObject.setBaseBo(materialPlantBo);

        return resultObject;
    }
}

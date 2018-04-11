package com.jnj.pangea.edm.planned_order_v1_latam.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlafDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.PlafEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.planned_order_v1_latam.bo.EDMPlannedOrderV1LatamBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EDMPlannedOrderV1LatamServiceImpl implements ICommonService {

    private static EDMPlannedOrderV1LatamServiceImpl instance;

    public static EDMPlannedOrderV1LatamServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlannedOrderV1LatamServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private ProjectOnePlafDaoImpl plafDaoImpl = ProjectOnePlafDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlafEntity plafEntity = (PlafEntity) o;

        EDMPlannedOrderV1LatamBo plannedOrderV1LatamBo = new EDMPlannedOrderV1LatamBo();

        // T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            plannedOrderV1LatamBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());
        }

        // N1
        String plwrk = plafEntity.getPlwrk();
        if (StringUtils.isNotEmpty(plwrk)) {
            EDMPlantV1Entity plantV1Entity = plantV1Dao.getEntityWithLocalPlant(plwrk);
            if (null != plantV1Entity) {
                plannedOrderV1LatamBo.setPlant(plantV1Entity.getPlant());

                // N3
                String plant = plantV1Entity.getPlant();
                if (StringUtils.isNotEmpty(plant)) {
                    PlafEntity plafCloneEntity = plafDaoImpl.getEntityWithPlscn(plantV1Entity.getPlant());
                    if (null != plafCloneEntity) {
                        plannedOrderV1LatamBo.setLocalPlanningScenario(plafCloneEntity.getPlscn());
                    }
                }
            }
        }

        // N2
        String matnr = plafEntity.getMatnr();
        if (StringUtils.isNotEmpty(matnr)) {
            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(matnr);
            if (null != materialGlobalV1Entity) {
                plannedOrderV1LatamBo.setMaterialNumber(materialGlobalV1Entity.getMaterialNumber());
            }
        }

        plannedOrderV1LatamBo.setMfgPlannedOrderId(plafEntity.getPlnum());
        plannedOrderV1LatamBo.setLocalPlant(plafEntity.getPlwrk());
        plannedOrderV1LatamBo.setLocalproductionPlant(plafEntity.getPwwrk());
        plannedOrderV1LatamBo.setLocalMaterialNumber(plafEntity.getMatnr());
        plannedOrderV1LatamBo.setLocalUom(plafEntity.getMeins());
        plannedOrderV1LatamBo.setLocalProcurementType(plafEntity.getBeskz());
        plannedOrderV1LatamBo.setLocalSplProcType(plafEntity.getSobes());
        plannedOrderV1LatamBo.setLocalPrdVersion(plafEntity.getNumvr());
        plannedOrderV1LatamBo.setLocalPrdOrdType(plafEntity.getPaart());
        plannedOrderV1LatamBo.setPlannedOrdQty(plafEntity.getGsmng());
        plannedOrderV1LatamBo.setLocalScrapQty(plafEntity.getAvmng());
        plannedOrderV1LatamBo.setRequirementQty(plafEntity.getBdmng());
        plannedOrderV1LatamBo.setOrderstrtDate(plafEntity.getPsttr());
        plannedOrderV1LatamBo.setOrdStrtTime(plafEntity.getPstti());
        plannedOrderV1LatamBo.setOrdFinishDate(plafEntity.getPedtr());
        plannedOrderV1LatamBo.setOrdEndTime(plafEntity.getPedti());
        plannedOrderV1LatamBo.setGrProcessDays(plafEntity.getWebaz());
        plannedOrderV1LatamBo.setOrdFirmInd(plafEntity.getAuffx());
        plannedOrderV1LatamBo.setLocalStorageLoc(plafEntity.getLgort());
        plannedOrderV1LatamBo.setLocalDocVersion(plafEntity.getVerid());
        plannedOrderV1LatamBo.setPrdStartDate(plafEntity.getTerst());
        plannedOrderV1LatamBo.setPrdFinishDate(plafEntity.getTered());
        plannedOrderV1LatamBo.setMrpController(plafEntity.getDispo());

        resultObject.setBaseBo(plannedOrderV1LatamBo);
        return resultObject;
    }
}

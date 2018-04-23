package com.jnj.pangea.edm.planned_order.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlafDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.PlafEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.planned_order.bo.EDMPlannedOrderBo;
import org.apache.commons.lang.StringUtils;

public class EDMPlannedOrderServiceImpl implements ICommonService {

    private static EDMPlannedOrderServiceImpl instance;

    public static EDMPlannedOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlannedOrderServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private ProjectOnePlafDaoImpl plafDaoImpl = ProjectOnePlafDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlafEntity plafEntity = (PlafEntity) o;

        EDMPlannedOrderBo plannedOrderBo = new EDMPlannedOrderBo();

        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            plannedOrderBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }

        // rules N1
        String plwrk = plafEntity.getPlwrk();
        if (StringUtils.isNotEmpty(plwrk)) {
            EDMPlantV1Entity plantV1Entity = plantV1Dao.getEntityWithLocalPlant(plwrk);
            if (null != plantV1Entity) {

                // rules N3
                String localPlant = plantV1Entity.getLocalPlant();
                if (StringUtils.isNotEmpty(localPlant)) {

                    PlafEntity plafCloneEntity = plafDaoImpl.getEntityWithLocalPlant(plantV1Entity.getLocalPlant());
                    if (null != plafCloneEntity) {
                        plannedOrderBo.setPlngScnroCd(plafCloneEntity.getPlscn());
                    }
                }
            }
        }

        plannedOrderBo.setMfgPlanOrdrDocId(plafEntity.getPlnum());
        plannedOrderBo.setPlanPlntCd(plafEntity.getPlwrk());
        plannedOrderBo.setPlntCd(plafEntity.getPwwrk());
        plannedOrderBo.setMatlNum(plafEntity.getMatnr());
        plannedOrderBo.setUomCd(plafEntity.getMeins());
        plannedOrderBo.setPrcmtTypeCd(plafEntity.getBeskz());
        plannedOrderBo.setSplPrcmtTypeCd(plafEntity.getSobes());
        plannedOrderBo.setPrdtnVersNum(plafEntity.getNumvr());
        plannedOrderBo.setPlanOrdrTypeCd(plafEntity.getPaart());
        plannedOrderBo.setPlanOrdrQty(plafEntity.getGsmng());
        plannedOrderBo.setFxScrapQty(plafEntity.getAvmng());
        plannedOrderBo.setReqQty(plafEntity.getBdmng());
        plannedOrderBo.setPlanOrdrStrtDt(plafEntity.getPsttr());
        plannedOrderBo.setPrdtnStrtTm(plafEntity.getPstti());
        plannedOrderBo.setPlanOrdrEndDt(plafEntity.getPedtr());
        plannedOrderBo.setPlanOrdrEndTm(plafEntity.getPedti());
        plannedOrderBo.setGrDaysLeadQty(plafEntity.getWebaz());
        plannedOrderBo.setFirmingInd(plafEntity.getAuffx());
        plannedOrderBo.setSLocCd(plafEntity.getLgort());
        plannedOrderBo.setPrdtnVers(plafEntity.getVerid());
        plannedOrderBo.setPrdtnStrtDt(plafEntity.getTerst());
        plannedOrderBo.setPrdtnFnshdDt(plafEntity.getTered());
        plannedOrderBo.setMrpCtlId(plafEntity.getDispo());

        if (StringUtils.isNotEmpty(sourceSystemV1Entity.getSourceSystem()) && StringUtils.isNotEmpty(plafEntity.getPlnum())) {
            resultObject.setBaseBo(plannedOrderBo);
        }

        return resultObject;
    }
}

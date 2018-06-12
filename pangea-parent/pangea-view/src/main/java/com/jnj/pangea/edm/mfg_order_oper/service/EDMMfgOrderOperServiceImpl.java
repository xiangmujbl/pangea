package com.jnj.pangea.edm.mfg_order_oper.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneAfvvEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_oper.bo.EDMMfgOrderOperBo;

public class EDMMfgOrderOperServiceImpl implements ICommonService {

    private static EDMMfgOrderOperServiceImpl instance;

    public static EDMMfgOrderOperServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderOperServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneAfvvEntity afvvEntity = (ProjectOneAfvvEntity) o;

        EDMMfgOrderOperBo mfgOrderOperBo = new EDMMfgOrderOperBo();
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != edmSourceSystemV1Entity) {
            mfgOrderOperBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        mfgOrderOperBo.setOrdrRtngNum(afvvEntity.getAufpl());
        mfgOrderOperBo.setOrdrRtngCtrNum(afvvEntity.getAplzl());
        mfgOrderOperBo.setOprUomCd(afvvEntity.getMeinh());
        mfgOrderOperBo.setFactDenomMeas(afvvEntity.getUmren());
        mfgOrderOperBo.setFactNumrtrMeas(afvvEntity.getUmrez());
        mfgOrderOperBo.setBaseQty(afvvEntity.getBmsch());
        mfgOrderOperBo.setActv1UomCd(afvvEntity.getVge01());
        mfgOrderOperBo.setActv1Qty(afvvEntity.getVgw01());
        mfgOrderOperBo.setActv2UomCd(afvvEntity.getVge02());
        mfgOrderOperBo.setActv2Qty(afvvEntity.getVgw02());
        mfgOrderOperBo.setActv3UomCd(afvvEntity.getVge03());
        mfgOrderOperBo.setActv3Qty(afvvEntity.getVgw03());
        mfgOrderOperBo.setActv4UomCd(afvvEntity.getVge04());
        mfgOrderOperBo.setActv4Qty(afvvEntity.getVgw04());
        mfgOrderOperBo.setActv5UomCd(afvvEntity.getVge05());
        mfgOrderOperBo.setActv5Qty(afvvEntity.getVgw05());
        mfgOrderOperBo.setActv6UomCd(afvvEntity.getVge06());
        mfgOrderOperBo.setActv6Qty(afvvEntity.getVgw06());
        mfgOrderOperBo.setMinOvrlapTimeUomCd(afvvEntity.getZeimu());
        mfgOrderOperBo.setMinOvrlapTimeInDaysQty(afvvEntity.getZminu());
        mfgOrderOperBo.setMinSendAhdQty(afvvEntity.getMinwe());
        mfgOrderOperBo.setMinPrcsgTimeUomCd(afvvEntity.getZeimb());
        mfgOrderOperBo.setMinPrcsgTimeInDaysQty(afvvEntity.getZminb());
        mfgOrderOperBo.setMaxWaitTimeUomCd(afvvEntity.getZeilm());
        mfgOrderOperBo.setMaxWaitTimeInDaysQty(afvvEntity.getZlmax());
        mfgOrderOperBo.setMinWaitTimeUomCd(afvvEntity.getZeilp());
        mfgOrderOperBo.setMinWaitTimeInDaysQty(afvvEntity.getZlpro());
        mfgOrderOperBo.setStdQueTimeUomCd(afvvEntity.getZeiwn());
        mfgOrderOperBo.setStdQueTimeInDaysQty(afvvEntity.getZwnor());
        mfgOrderOperBo.setMinQueTimeUomCd(afvvEntity.getZeiwm());
        mfgOrderOperBo.setMinQueTimeInDaysQty(afvvEntity.getZwmin());
        mfgOrderOperBo.setStdMoveTimeUomCd(afvvEntity.getZeitn());
        mfgOrderOperBo.setStdMoveTimeInDaysQty(afvvEntity.getZtnor());
        mfgOrderOperBo.setMinMoveTimeUomCd(afvvEntity.getZeitm());
        mfgOrderOperBo.setMinMoveTimeInDaysQty(afvvEntity.getZtmin());
        resultObject.setBaseBo(mfgOrderOperBo);
        return resultObject;
    }
}

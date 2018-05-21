package com.jnj.pangea.edm.wrk_ctr_cc.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.projectOne.ProjectOneCrcoEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_cc.bo.EDMWrkCtrCcBo;

public class EDMWrkCtrCcServiceImpl implements ICommonService {

    private static EDMWrkCtrCcServiceImpl instance;

    public static EDMWrkCtrCcServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrCcServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneCrcoEntity crcoEntity = (ProjectOneCrcoEntity) o;

        EDMWrkCtrCcBo wrkCtrCcBo = new EDMWrkCtrCcBo();

        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
            wrkCtrCcBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        wrkCtrCcBo.setWrkCtrTypeCd(crcoEntity.getObjty());
        wrkCtrCcBo.setWrkCtrNum(crcoEntity.getObjid());
        wrkCtrCcBo.setActvTypeSetCd(crcoEntity.getLaset());
        wrkCtrCcBo.setVldToDt(crcoEntity.getEndda());
        wrkCtrCcBo.setActvTypeNbr(crcoEntity.getLanum());
        wrkCtrCcBo.setVldFromDt(crcoEntity.getBegda());
        wrkCtrCcBo.setChgDttm(crcoEntity.getAedatKost());
        wrkCtrCcBo.setCntlAreaCd(crcoEntity.getKokrs());
        wrkCtrCcBo.setCcCd(crcoEntity.getKostl());
        wrkCtrCcBo.setActvCd(crcoEntity.getLstar());

        resultObject.setBaseBo(wrkCtrCcBo);
        return resultObject;
    }
}

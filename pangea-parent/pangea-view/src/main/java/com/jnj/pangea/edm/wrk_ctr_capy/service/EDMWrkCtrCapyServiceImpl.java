package com.jnj.pangea.edm.wrk_ctr_capy.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectOne.ProjectOneCrcaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_capy.bo.EDMWrkCtrCapyBo;

public class EDMWrkCtrCapyServiceImpl implements ICommonService {

    private static EDMWrkCtrCapyServiceImpl instance;

    public static EDMWrkCtrCapyServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrCapyServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneCrcaEntity crcaEntity = (ProjectOneCrcaEntity) o;

        EDMWrkCtrCapyBo wrkCtrCapyBo = new EDMWrkCtrCapyBo();
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
            wrkCtrCapyBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        wrkCtrCapyBo.setWrkCtrTypeCd(crcaEntity.getObjty());
        wrkCtrCapyBo.setWrkCtrNum(crcaEntity.getObjid());
        wrkCtrCapyBo.setCapyAllcNbr(crcaEntity.getCanum());
        wrkCtrCapyBo.setVldFromDt(crcaEntity.getBegda());
        wrkCtrCapyBo.setVldToDt(crcaEntity.getEndda());
        wrkCtrCapyBo.setChgDttm(crcaEntity.getAedatKapa());
        wrkCtrCapyBo.setCapyNum(crcaEntity.getKapid());
        wrkCtrCapyBo.setSetupFrmlCd(crcaEntity.getFork1());
        wrkCtrCapyBo.setRunFrmlCd(crcaEntity.getFork2());
        wrkCtrCapyBo.setTeardownFrmlCd(crcaEntity.getFork3());
        wrkCtrCapyBo.setOthFrmlCd(crcaEntity.getForkn());
        wrkCtrCapyBo.setSetupDstnCd(crcaEntity.getVert1());
        wrkCtrCapyBo.setRunDstnCd(crcaEntity.getVert2());
        wrkCtrCapyBo.setTeardownDstnCd(crcaEntity.getVert3());
        wrkCtrCapyBo.setOthDstnCd(crcaEntity.getVertn());

        resultObject.setBaseBo(wrkCtrCapyBo);
        return resultObject;
    }
}

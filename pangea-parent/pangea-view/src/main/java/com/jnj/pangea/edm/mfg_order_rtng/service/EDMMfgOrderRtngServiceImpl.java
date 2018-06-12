package com.jnj.pangea.edm.mfg_order_rtng.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.AfvcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_rtng.bo.EDMMfgOrderRtngBo;

public class EDMMfgOrderRtngServiceImpl implements ICommonService {

    private static EDMMfgOrderRtngServiceImpl instance;

    public static EDMMfgOrderRtngServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderRtngServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        AfvcEntity afvcEntity = (AfvcEntity) o;

        EDMMfgOrderRtngBo mfgOrderRtngBo = new EDMMfgOrderRtngBo();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            mfgOrderRtngBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        mfgOrderRtngBo.setOrdrRtngNum(afvcEntity.getAufpl());
        mfgOrderRtngBo.setOrdrRtngCtrNum(afvcEntity.getAplzl());
        mfgOrderRtngBo.setOperNum(afvcEntity.getVornr());
        mfgOrderRtngBo.setOperCd(afvcEntity.getSteus());
        mfgOrderRtngBo.setWrkCntrId(afvcEntity.getArbid());
        mfgOrderRtngBo.setOperDesc(afvcEntity.getLtxa1());
        resultObject.setBaseBo(mfgOrderRtngBo);
        return resultObject;
    }
}

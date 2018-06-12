package com.jnj.pangea.edm.mfg_order_seq.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.projectOne.ProjectOneAfflEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_seq.bo.EDMMfgOrderSeqBo;

public class EDMMfgOrderSeqServiceImpl implements ICommonService {

    private static EDMMfgOrderSeqServiceImpl instance;

    public static EDMMfgOrderSeqServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderSeqServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneAfflEntity afflEntity = (ProjectOneAfflEntity) o;

        EDMMfgOrderSeqBo mfgOrderSeqBo = new EDMMfgOrderSeqBo();
        // T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != edmSourceSystemV1Entity) {
            mfgOrderSeqBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        mfgOrderSeqBo.setOrdrRtngNum(afflEntity.getAufpl());
        mfgOrderSeqBo.setOrdrRtngCtrNum(afflEntity.getAplzl());
        mfgOrderSeqBo.setRtngTypCd(afflEntity.getPlnty());
        mfgOrderSeqBo.setRntgGrpCd(afflEntity.getPlnnr());
        mfgOrderSeqBo.setRntgGrpCntrNbr(afflEntity.getPlnal());
        mfgOrderSeqBo.setRtngSqncNum(afflEntity.getPlnfl());
        mfgOrderSeqBo.setRtngSqncVrsnCntrNbr(afflEntity.getZaehl());
        mfgOrderSeqBo.setChgNum(afflEntity.getAennr());
        mfgOrderSeqBo.setRtngSqncCtgryCd(afflEntity.getFlgat());
        mfgOrderSeqBo.setStrtNdeNum(afflEntity.getBknt1());
        mfgOrderSeqBo.setEndNdeNum(afflEntity.getBknt2());
        mfgOrderSeqBo.setFromLtSzQty(afflEntity.getLosvn());
        mfgOrderSeqBo.setToLtSzQty(afflEntity.getLosbs());
        mfgOrderSeqBo.setBrnchOperNum(afflEntity.getBschl1());
        mfgOrderSeqBo.setRetrnOperNum(afflEntity.getBschl2());
        resultObject.setBaseBo(mfgOrderSeqBo);
        return resultObject;
    }
}

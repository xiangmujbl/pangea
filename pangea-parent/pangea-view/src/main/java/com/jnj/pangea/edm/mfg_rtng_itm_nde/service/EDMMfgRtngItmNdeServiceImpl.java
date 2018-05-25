package com.jnj.pangea.edm.mfg_rtng_itm_nde.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.projectOne.ProjectOneQalsEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm_nde.bo.EDMMfgRtngItmNdeBo;

public class EDMMfgRtngItmNdeServiceImpl implements ICommonService {

    private static EDMMfgRtngItmNdeServiceImpl instance;

    public static EDMMfgRtngItmNdeServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmNdeServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneQalsEntity qalsEntity = (ProjectOneQalsEntity) o;

        EDMMfgRtngItmNdeBo mfgRtngItmNdeBo = new EDMMfgRtngItmNdeBo();

        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            mfgRtngItmNdeBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        //rule F1

        if(qalsEntity.getPlnty()!=null&&!qalsEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_2)&&!qalsEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_N)){
            return resultObject;
        }
        mfgRtngItmNdeBo.setRtngTypCd(qalsEntity.getPlnty());
        mfgRtngItmNdeBo.setRtngGrpCd(qalsEntity.getPlnnr());
        mfgRtngItmNdeBo.setRtngGrpCntrNbr(qalsEntity.getPlnal());
        mfgRtngItmNdeBo.setRtngSqncNum(qalsEntity.getPlnfl());
        mfgRtngItmNdeBo.setRtngNdeNum(qalsEntity.getPlnkn());
        mfgRtngItmNdeBo.setRtngNdeVrsnCntrNbr(qalsEntity.getZaehl());
        mfgRtngItmNdeBo.setValidFromDate(qalsEntity.getDatuv());
        mfgRtngItmNdeBo.setChgNum(qalsEntity.getAennr());
        mfgRtngItmNdeBo.setDelInd(qalsEntity.getLoekz());
        mfgRtngItmNdeBo.setCreateDttm(qalsEntity.getAndat());
        mfgRtngItmNdeBo.setChgDttm(qalsEntity.getAedat());

        resultObject.setBaseBo(mfgRtngItmNdeBo);
        return resultObject;
    }
}

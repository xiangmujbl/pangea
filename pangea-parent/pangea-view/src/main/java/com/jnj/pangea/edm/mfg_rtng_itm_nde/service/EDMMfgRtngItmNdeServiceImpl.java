package com.jnj.pangea.edm.mfg_rtng_itm_nde.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
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
        ProjectOnePlasEntity plasEntity = (ProjectOnePlasEntity) o;

        EDMMfgRtngItmNdeBo mfgRtngItmNdeBo = new EDMMfgRtngItmNdeBo();

        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            mfgRtngItmNdeBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        //rule F1

        if(plasEntity.getPlnty()!=null&&!plasEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_2)&&!plasEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_N)){
            return resultObject;
        }
        mfgRtngItmNdeBo.setRtngTypCd(plasEntity.getPlnty());
        mfgRtngItmNdeBo.setRtngGrpCd(plasEntity.getPlnnr());
        mfgRtngItmNdeBo.setRtngGrpCntrNbr(plasEntity.getPlnal());
        mfgRtngItmNdeBo.setRtngSqncNum(plasEntity.getPlnfl());
        mfgRtngItmNdeBo.setRtngNdeNum(plasEntity.getPlnkn());
        mfgRtngItmNdeBo.setRtngNdeVrsnCntrNbr(plasEntity.getZaehl());
        mfgRtngItmNdeBo.setValidFromDate(plasEntity.getDatuv());
        mfgRtngItmNdeBo.setChgNum(plasEntity.getAennr());
        mfgRtngItmNdeBo.setDelInd(plasEntity.getLoekz());
        mfgRtngItmNdeBo.setCreateDttm(plasEntity.getAndat());
        mfgRtngItmNdeBo.setChgDttm(plasEntity.getAedat());

        resultObject.setBaseBo(mfgRtngItmNdeBo);
        return resultObject;
    }
}

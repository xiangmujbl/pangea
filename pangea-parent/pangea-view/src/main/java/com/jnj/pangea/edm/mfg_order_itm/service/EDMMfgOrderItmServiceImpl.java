package com.jnj.pangea.edm.mfg_order_itm.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.AfpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order_itm.bo.EDMMfgOrderItmBo;

public class EDMMfgOrderItmServiceImpl implements ICommonService {

    private static EDMMfgOrderItmServiceImpl instance;

    public static EDMMfgOrderItmServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderItmServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        AfpoEntity afpoEntity = (AfpoEntity) o;

        EDMMfgOrderItmBo mfgOrderItmBo = new EDMMfgOrderItmBo();

        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != edmSourceSystemV1Entity) {
            mfgOrderItmBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }

        mfgOrderItmBo.setMfgOrdrNum(afpoEntity.getAufnr());
        mfgOrderItmBo.setLnItmNbr(afpoEntity.getPosnr());
        mfgOrderItmBo.setMfgPlnndOrdrNum(afpoEntity.getPlnum());
        mfgOrderItmBo.setMatlNum(afpoEntity.getMatnr());
        mfgOrderItmBo.setScrpQty(afpoEntity.getPsamg());
        mfgOrderItmBo.setItmQty(afpoEntity.getPsmng());
        mfgOrderItmBo.setRcvdQty(afpoEntity.getWemng());
        mfgOrderItmBo.setPrdtnUomCd(afpoEntity.getAmein());
        mfgOrderItmBo.setPlntCd(afpoEntity.getPwerk());
        mfgOrderItmBo.setFctrNmrtrMeas(afpoEntity.getUmrez());
        mfgOrderItmBo.setFctrDnmntrMeas(afpoEntity.getUmren());
        mfgOrderItmBo.setGoodRcptLdDaysQty(afpoEntity.getWebaz());
        mfgOrderItmBo.setDlvCmpltInd(afpoEntity.getElikz());
        mfgOrderItmBo.setPrdntVrsnNum(afpoEntity.getVerid());
        mfgOrderItmBo.setBtchNum(afpoEntity.getCharg());
        mfgOrderItmBo.setDelInd(afpoEntity.getXloek());
        mfgOrderItmBo.setBaseUomCd(afpoEntity.getMeins());
        resultObject.setBaseBo(mfgOrderItmBo);
        return resultObject;
    }
}

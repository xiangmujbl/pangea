package com.jnj.pangea.edm.matl_bom.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMatlBomDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlBomEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectOne.ProjectOneMastEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_bom.bo.EDMMatlBomBo;

public class EDMMatlBomServiceImpl implements ICommonService {

    private static EDMMatlBomServiceImpl instance;

    public static EDMMatlBomServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlBomServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneMastEntity mastEntity = (ProjectOneMastEntity) o;

        EDMMatlBomBo matlBomBo = new EDMMatlBomBo();
        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            matlBomBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        matlBomBo.setMatlNum(mastEntity.getMatnr());
        matlBomBo.setPlntCd(mastEntity.getWerks());
        matlBomBo.setBomUsgCd(mastEntity.getStlan());
        matlBomBo.setBomNum(mastEntity.getStlnr());
        matlBomBo.setAltBomNum(mastEntity.getStlal());
        matlBomBo.setFromLotSizeQty(mastEntity.getLosvn());
        matlBomBo.setToLotSizeQty(mastEntity.getLosbs());
        matlBomBo.setCrtDttm(mastEntity.getAndat());
        matlBomBo.setChgDttm(mastEntity.getAedat());
        resultObject.setBaseBo(matlBomBo);
        return resultObject;
    }
}

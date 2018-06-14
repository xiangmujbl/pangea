package com.jnj.pangea.edm.wrk_ctr_hier.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.CrhsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr_hier.bo.EDMWrkCtrHierBo;

public class EDMWrkCtrHierServiceImpl implements ICommonService {

    private static EDMWrkCtrHierServiceImpl instance;

    public static EDMWrkCtrHierServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrHierServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        CrhsEntity crhsEntity = (CrhsEntity) o;

        EDMWrkCtrHierBo wrkCtrHierBo = new EDMWrkCtrHierBo();
        
        //rule F1
        String flgav = crhsEntity.getFlgav();
        if(!(IConstant.VALUE.X.equalsIgnoreCase(flgav))) {
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
        	wrkCtrHierBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        wrkCtrHierBo.setTopWrkCtrTypeCd(crhsEntity.getObjtyHy());
        wrkCtrHierBo.setTopWrkCtrNum(crhsEntity.getObjidHy());
        wrkCtrHierBo.setParntWrkCtrTypeCd(crhsEntity.getObjtyUp());
        wrkCtrHierBo.setParntWrkCtrNum(crhsEntity.getObjidUp());
        wrkCtrHierBo.setChildWrkCtrTypeCd(crhsEntity.getObjtyHo());
        wrkCtrHierBo.setChildWrkCtrNum(crhsEntity.getObjidHo());
        wrkCtrHierBo.setWrkCtrTypeCd(crhsEntity.getObjtyLe());
        wrkCtrHierBo.setWrkCtrNum(crhsEntity.getObjidLe());
        wrkCtrHierBo.setStopExplsInd(crhsEntity.getFlgav());
        
        resultObject.setBaseBo(wrkCtrHierBo);
        
        }
        return resultObject;
    }
}

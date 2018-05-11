package com.jnj.pangea.edm.purchase_order_oa_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.EkpoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.purchase_order_oa_v1.bo.EDMPurchaseOrderOABo;

public class EDMPurchaseOrderOAServiceImpl implements ICommonService {

    private static EDMPurchaseOrderOAServiceImpl instance;

    public static EDMPurchaseOrderOAServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMPurchaseOrderOAServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EkpoEntity ekpoEntity = (EkpoEntity) o;
        EDMPurchaseOrderOABo purchaseOrderOABo = new EDMPurchaseOrderOABo();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem("project_one");
        if(sourceSystemV1Entity.getLocalSourceSystem().equals("project_one")) {
            purchaseOrderOABo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());

        }

        resultObject.setBaseBo(purchaseOrderOABo);
        return resultObject;
    }
}

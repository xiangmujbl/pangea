package com.jnj.pangea.edm.purchase_order_oa_v1.service;

import com.jnj.pangea.common.ResultObject;
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

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EkpoEntity ekpoEntity = (EkpoEntity) o;

        EDMPurchaseOrderOABo purchaseOrderOABo = new EDMPurchaseOrderOABo();

        resultObject.setBaseBo(purchaseOrderOABo);
        return resultObject;
    }
}

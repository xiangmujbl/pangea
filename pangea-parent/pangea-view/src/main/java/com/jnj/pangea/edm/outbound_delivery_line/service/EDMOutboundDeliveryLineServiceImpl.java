package com.jnj.pangea.edm.outbound_delivery_line.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.LipsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.outbound_delivery_line.bo.EDMOutboundDeliveryLineBo;

public class EDMOutboundDeliveryLineServiceImpl implements ICommonService {

    private static EDMOutboundDeliveryLineServiceImpl instance;

    public static EDMOutboundDeliveryLineServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMOutboundDeliveryLineServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        LipsEntity lipsEntity = (LipsEntity) o;

        EDMOutboundDeliveryLineBo outboundDeliveryLineV1Bo = new EDMOutboundDeliveryLineBo();

        // rules T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            outboundDeliveryLineV1Bo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }

        outboundDeliveryLineV1Bo.setDelvDocId(lipsEntity.getVbeln());
        outboundDeliveryLineV1Bo.setDelvLineNbr(lipsEntity.getPosnr());
        outboundDeliveryLineV1Bo.setMatlNum(lipsEntity.getMatnr());
        outboundDeliveryLineV1Bo.setBtchNum(lipsEntity.getCharg());
        outboundDeliveryLineV1Bo.setVendBtchNum(lipsEntity.getLichn());
        outboundDeliveryLineV1Bo.setShippingPlntCd(lipsEntity.getWerks());
        outboundDeliveryLineV1Bo.setBaseUnitOfMeasureCd(lipsEntity.getMeins());
        outboundDeliveryLineV1Bo.setShippedQty(lipsEntity.getLfimg());
        outboundDeliveryLineV1Bo.setActlSkuDelvQty(lipsEntity.getLgmng());
        outboundDeliveryLineV1Bo.setSlsOrdrNum(lipsEntity.getVgbel());
        outboundDeliveryLineV1Bo.setSlsOrdrLineNbr(lipsEntity.getVgpos());

        resultObject.setBaseBo(outboundDeliveryLineV1Bo);
        return resultObject;
    }
}

package com.jnj.pangea.edm.advanced_ship_notification_v1.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.LikpDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.LipsDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.entity.project_one.LipsEntity;
import com.jnj.pangea.edm.advanced_ship_notification_v1.bo.EDMAdvancedShipNotificationBo;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rtierne2 on 2018/5/10.
 */

public class EDMAdvancedShipNotificationServiceImpl {


    private static EDMAdvancedShipNotificationServiceImpl instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private LikpDaoImpl likpDao = LikpDaoImpl.getInstance();
    private LipsDaoImpl lipsDao = LipsDaoImpl.getInstance();


    public static EDMAdvancedShipNotificationServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMAdvancedShipNotificationServiceImpl();
        }
        return instance;
    }

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjects = new ArrayList<>();

        LikpEntity likpEntity = (LikpEntity) o;

        // J1
        // Join using (LIKP-VBELN) = (LIPS-VBELN) to get value of ( LIPS-POSNR) and there can be multiple values
        List<LipsEntity> lipsEntities = lipsDao.getLipsEntitiesWithVbeln(likpEntity.getVbeln());
        for (LipsEntity lipsEntity : lipsEntities) {
            resultObjects.add(setObjectByPosnr(likpEntity, lipsEntity));
        }
        return resultObjects;
    }

    private ResultObject setObjectByPosnr(LikpEntity likpEntity, LipsEntity lipsEntity) {

        ResultObject resultObject = new ResultObject();

        EDMAdvancedShipNotificationBo edmAdvancedShipNotificationBo = new EDMAdvancedShipNotificationBo();

        edmAdvancedShipNotificationBo.setDelvLineNbr(lipsEntity.getPosnr());

        // N3
        // ERP Date is informat yyyymmdd
        // Date needs to be converted in - yyyy-mm-dd
        edmAdvancedShipNotificationBo.setLocalCreatedDate(dateFormatter(likpEntity.getErdat()));

        edmAdvancedShipNotificationBo.setDelvDocId(lipsEntity.getVbeln());

        edmAdvancedShipNotificationBo.setReceivingPtId(likpEntity.getVstel());

        edmAdvancedShipNotificationBo.setLocalDeliveryType(likpEntity.getLfart());

        // N3
        // ERP Date is informat yyyymmdd
        // Date needs to be converted in - yyyy-mm-dd
        edmAdvancedShipNotificationBo.setLocalDeliveryDate(dateFormatter(likpEntity.getLfdat()));

        edmAdvancedShipNotificationBo.setLocalBillOfLading(likpEntity.getBolnr());

        edmAdvancedShipNotificationBo.setLocalExternalId(likpEntity.getLifex());

        // N3
        // ERP Date is informat yyyymmdd
        // Date needs to be converted in - yyyy-mm-dd
        edmAdvancedShipNotificationBo.setActGRDt(dateFormatter(likpEntity.getWadatist()));

        edmAdvancedShipNotificationBo.setVendorId(likpEntity.getLifnr());

        edmAdvancedShipNotificationBo.setLocalShippingPlant(likpEntity.getWerks());

        edmAdvancedShipNotificationBo.setMatlNum(lipsEntity.getMatnr());

        edmAdvancedShipNotificationBo.setLocalBatchNo(lipsEntity.getCharg());

        edmAdvancedShipNotificationBo.setLocalVendorBatchNo(lipsEntity.getLichn());

        edmAdvancedShipNotificationBo.setLocalReceivingPlant(lipsEntity.getWerks());

        edmAdvancedShipNotificationBo.setBaseUnitOfMeasureCd(lipsEntity.getMeins());

        edmAdvancedShipNotificationBo.setActlSkuDelvQty(lipsEntity.getLgmng());

        edmAdvancedShipNotificationBo.setLocalRefDocNum(lipsEntity.getVgbel());

        edmAdvancedShipNotificationBo.setLocRefDocLineNum(lipsEntity.getVgpos());

        // T1
        // Get sourceSystem from source_system_v1 using below condition:
        // source_system_v1-localSourceSystem = "project_one"
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (edmSourceSystemV1Entity != null) {
            edmAdvancedShipNotificationBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }

        // N1
        // Select only when LIKP-VBTYP= 7
        if (likpEntity.getVbtyp().equals(IConstant.VALUE.SEVEN)) {
            edmAdvancedShipNotificationBo.setLocalDeliveryCatg(likpEntity.getVbtyp());
        } else {
            // skip
            return null;
        }
        resultObject.setBaseBo(edmAdvancedShipNotificationBo);
        return resultObject;
    }

    private String dateFormatter(String dateToFormat) {

        if (StringUtils.isEmpty(dateToFormat)) {
            return "";
        }

        SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD_WITH_DASH);

        Date dFrom = null;
        try {
            dFrom = sdfFrom.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfTo.format(dFrom);
    }
}

package com.jnj.pangea.edm.advanced_ship_notification_v1.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ngems.SourceSystemEntity;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.entity.project_one.LipsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.advanced_ship_notification_v1.bo.EDMAdvancedShipNotificationBo;
import com.sun.jna.platform.win32.WinGDI;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rtierne2 on 2018/5/10.
 */

public class EDMAdvancedShipNotificationServiceImpl implements ICommonService {


    private static ICommonService instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMAdvancedShipNotificationServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        LipsEntity lipsEntity = (LipsEntity) o;
        LikpEntity likpEntity = (LikpEntity) o2;

        EDMAdvancedShipNotificationBo edmAdvancedShipNotificationBo = new EDMAdvancedShipNotificationBo();

        // T1
        // Get sourceSystem from source_system_v1 using below condition:
        // source_system_v1-localSourceSystem = "project_one"
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();

        if(edmSourceSystemV1Entity != null){
            edmAdvancedShipNotificationBo.setSrcSysCd(edmSourceSystemV1Entity.getLocalSourceSystem());
        }

        // N2
        // Select only when LIKP-LFART= 7
        if(likpEntity.getLfart().equals("7")) {
            edmAdvancedShipNotificationBo.setLocaldeliveryType(likpEntity.getLfart());

        }

        // J1
        // Join using (LIKP-VBELN) = (LIPS-VBELN) to get value of ( LIPS-POSNR) and there can be multiple values
        if(likpEntity.getVbeln().equals(lipsEntity.getVbeln())) {
            edmAdvancedShipNotificationBo.setDelvLineNbr(lipsEntity.getPosnr());
        }

        // N3
        // ERP Date is informat yyyymmdd
        // Date needs to be converted in - yyyy-mm-ddThh:mm:ss.ffffff (Example 2008-09-15T15:53:00)
        String dateToFormat_1 = likpEntity.getErdat();
        SimpleDateFormat sdfFrom_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        SimpleDateFormat sdfTo_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);

        Date dFrom_1 = null;
        try {
            dFrom_1 = sdfFrom_1.parse(dateToFormat_1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String toDate = sdfTo_1.format(dFrom_1);

        edmAdvancedShipNotificationBo.setLocalcreatedDate(toDate);

        edmAdvancedShipNotificationBo.setDelvDocID(lipsEntity.getVbeln());

        edmAdvancedShipNotificationBo.setReceivingPtID(likpEntity.getVstel());

        edmAdvancedShipNotificationBo.setLocaldeliveryCatg(likpEntity.getVbtyp());

        edmAdvancedShipNotificationBo.setLocaldeliveryDate(likpEntity.getLfdat());

        edmAdvancedShipNotificationBo.setLocalbillOfLading(likpEntity.getBolnr());

        edmAdvancedShipNotificationBo.setLocalExternalId(likpEntity.getLifex());

        edmAdvancedShipNotificationBo.setActGRDt(likpEntity.getWadatist());

        edmAdvancedShipNotificationBo.setVendorID(likpEntity.getLifnr());

        edmAdvancedShipNotificationBo.setLocalShippingPlant(likpEntity.getWerks());

        edmAdvancedShipNotificationBo.setDelvLineNbr(lipsEntity.getPosnr());

        edmAdvancedShipNotificationBo.setMatlNum(lipsEntity.getMatnr());

        edmAdvancedShipNotificationBo.setLocalbatchNo(lipsEntity.getCharg());

        edmAdvancedShipNotificationBo.setLocalvendorBatchNo(lipsEntity.getLichn());

        edmAdvancedShipNotificationBo.setLocalReceivingPlant(lipsEntity.getWerks());

        edmAdvancedShipNotificationBo.setBaseUnitOfMeasureCd(lipsEntity.getMeins());

        edmAdvancedShipNotificationBo.setActlSkuDelvQty(lipsEntity.getLgmng());

        edmAdvancedShipNotificationBo.setLocalRefDocNum(lipsEntity.getVgbel());

        edmAdvancedShipNotificationBo.setLocRefDocLineNum(lipsEntity.getVgpos());

        resultObject.setBaseBo(edmAdvancedShipNotificationBo);
        return resultObject;
    }

}

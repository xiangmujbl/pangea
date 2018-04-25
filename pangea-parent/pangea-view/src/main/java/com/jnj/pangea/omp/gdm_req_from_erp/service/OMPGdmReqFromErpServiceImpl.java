package com.jnj.pangea.omp.gdm_req_from_erp.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_req_from_erp.bo.OMPGdmReqFromErpBo;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OMPGdmReqFromErpServiceImpl implements ICommonService {

    private static OMPGdmReqFromErpServiceImpl instance;

    public static OMPGdmReqFromErpServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmReqFromErpServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity = (EDMPurchaseRequisitionV1Entity) o;

        OMPGdmReqFromErpBo gdmReqFromErpBo = new OMPGdmReqFromErpBo();

        //N1
        //No pr_doc_ic?
        //gdmReqFromErpBo.setReqFromErpId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.BACK_SLANT + edmPurchaseRequisitionV1Entity.pr);

        //N2
        try {
        String deliveryDate = edmPurchaseRequisitionV1Entity.getNeedByDt() + IConstant.VALUE.SPACE + edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD HH:NN:SS");
            Date date = sdf.parse(deliveryDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 2);
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 1);
            }
            deliveryDate = sdf.format(cal);
            gdmReqFromErpBo.setDeliveryDate(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //N3
        gdmReqFromErpBo.setDelkz(IConstant.VALUE.BA);

        //N4
        if (!edmPurchaseRequisitionV1Entity.getSourceSystem().isEmpty() && edmPurchaseRequisitionV1Entity.getSourceSystem() != null
                && !edmPurchaseRequisitionV1Entity.getPlntCd().isEmpty() && edmPurchaseRequisitionV1Entity.getPlntCd() != null){
            gdmReqFromErpBo.setLocationId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmPurchaseRequisitionV1Entity.getPlntCd());
        } else {
            //Critical error?
        }
        //N5
        if(IConstant.VALUE.CONS_LATAM.equalsIgnoreCase(edmPurchaseRequisitionV1Entity.getSourceSystem())) {
            gdmReqFromErpBo.setManualOffset(" ");
            gdmReqFromErpBo.setPrio_urg(" ");
        }

        //N6

        //N7

        //N8
        gdmReqFromErpBo.setDeleted(IConstant.VALUE.FALSE);

        //N9


        return resultObject;
    }
}

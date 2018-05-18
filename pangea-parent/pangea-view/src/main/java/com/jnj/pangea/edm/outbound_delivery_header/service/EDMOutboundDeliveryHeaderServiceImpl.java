package com.jnj.pangea.edm.outbound_delivery_header.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.outbound_delivery_header.bo.EDMOutboundDeliveryHeaderBo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EDMOutboundDeliveryHeaderServiceImpl implements ICommonService{

    private static EDMOutboundDeliveryHeaderServiceImpl instance;

    public static EDMOutboundDeliveryHeaderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMOutboundDeliveryHeaderServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        LikpEntity likpEntity = (LikpEntity)o;
        ResultObject resultObject = new ResultObject();
        EDMOutboundDeliveryHeaderBo outboundDeliveryHeaderBo = new EDMOutboundDeliveryHeaderBo();

        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();

        if(sourceSystemV1Entity != null){
            outboundDeliveryHeaderBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
            outboundDeliveryHeaderBo.setDelvDocId(likpEntity.getVbeln());
            outboundDeliveryHeaderBo.setShippingPtNum(likpEntity.getVstel());
            outboundDeliveryHeaderBo.setDelvTypeCd(likpEntity.getLfart());

            if(likpEntity.getVbtyp().equals("J")){
                outboundDeliveryHeaderBo.setSlsOrdrCarCd(likpEntity.getVbtyp());
            } else{
                outboundDeliveryHeaderBo.setSlsOrdrCarCd("");
            }
            SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
            SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD_WITH_DASH);

            try {
                Date dateFromLfdat = sdfFrom.parse(likpEntity.getLfdat());
                Date dateFromErdat = sdfFrom.parse(likpEntity.getErdat());

                String dateToLfdat = sdfTo.format(dateFromLfdat);
                String dateToErdat = sdfTo.format(dateFromErdat);

                outboundDeliveryHeaderBo.setDelvDt(dateToLfdat);
                outboundDeliveryHeaderBo.setCrtDttm(dateToErdat);
            } catch (ParseException e) {
                e.printStackTrace();
                outboundDeliveryHeaderBo.setDelvDt("");
                outboundDeliveryHeaderBo.setCrtDttm("");
            }

            outboundDeliveryHeaderBo.setSoldToCustNum(likpEntity.getKunag());
            outboundDeliveryHeaderBo.setShipToCustNum(likpEntity.getKunnr());
            outboundDeliveryHeaderBo.setBillOfLdngNum(likpEntity.getBolnr());
            outboundDeliveryHeaderBo.setDelvNum(likpEntity.getLifex());
            outboundDeliveryHeaderBo.setPlanGiDt(likpEntity.getWadat());
            outboundDeliveryHeaderBo.setActlGiDt(likpEntity.getWadatist());
            outboundDeliveryHeaderBo.setShippingCondCd(likpEntity.getVsbed());
            outboundDeliveryHeaderBo.setSupNum(likpEntity.getLifnr());
            outboundDeliveryHeaderBo.setPlntCd(likpEntity.getWerks());
        }
        resultObject.setBaseBo(outboundDeliveryHeaderBo);
        return resultObject;
    }
}

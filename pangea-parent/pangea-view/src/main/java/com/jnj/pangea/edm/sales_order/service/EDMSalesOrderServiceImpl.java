package com.jnj.pangea.edm.sales_order.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesOrderV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.*;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sales_order.bo.EDMSalesOrderBo;

import java.util.HashMap;

public class EDMSalesOrderServiceImpl implements ICommonService {

    private static EDMSalesOrderServiceImpl instance;

    public static EDMSalesOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesOrderServiceImpl();
        }
        return instance;
    }

    private VbakDaoImpl vbakDao = VbakDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private VbapDaoImpl vbapDao = VbapDaoImpl.getInstance();
    private VbepDaoImpl vbepDao = VbepDaoImpl.getInstance();
    private VbpaDaoImpl vbpaDao = VbpaDaoImpl.getInstance();
    private VbkdDaoImpl vbkdDao = VbkdDaoImpl.getInstance();
    private EDMSalesOrderV1DaoImpl salesOrderV1Dao = EDMSalesOrderV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        VbakEntity vbakEntity = (VbakEntity) o;
        EDMSalesOrderBo salesOrderBo = new EDMSalesOrderBo();

        //rule T1
        salesOrderBo.setSourceSystem(sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE).getSourceSystem());

        salesOrderBo.setSalesOrderNo(vbakEntity.getVbeln());
        salesOrderBo.setLocalOrderCreateDt(vbakEntity.getErdat());
        salesOrderBo.setLocalOrderDate(vbakEntity.getAudat());
        salesOrderBo.setLocalValidFromDt(vbakEntity.getGuebg());
        salesOrderBo.setLocalValidToDt(vbakEntity.getGueen());
        salesOrderBo.setLocalDocumentCateg(vbakEntity.getVbtyp());
        salesOrderBo.setLocalOrderType(vbakEntity.getAuart());
        salesOrderBo.setLocalOrderReason(vbakEntity.getAugru());
        salesOrderBo.setLocalDeliveryBlock(vbakEntity.getLifsk());
        salesOrderBo.setLocalBillingBlock(vbakEntity.getFaksk());
        salesOrderBo.setLocalSalesOrg(vbakEntity.getVkorg());
        salesOrderBo.setLocalDistrChannel(vbakEntity.getVtweg());
        salesOrderBo.setLocalDivision(vbakEntity.getSpart());
        salesOrderBo.setLocalSalesGroup(vbakEntity.getVkgrp());
        salesOrderBo.setLocalSalesOffice(vbakEntity.getVkbur());
        salesOrderBo.setLocalRequestedDate(vbakEntity.getVdatu());
        salesOrderBo.setLocalCustomerPO(vbakEntity.getBstnk());
        salesOrderBo.setLocalSoldToParty(vbakEntity.getKunnr());
        salesOrderBo.setLocalChangeDt(vbakEntity.getAedat());
        salesOrderBo.setLocalPricingProcedure(vbakEntity.getKalsm());

        //rule J1
        VbapEntity vbapEntity = getFieldWithJ1(vbakEntity.getVbeln());
        if (null == vbapEntity){
            FailData  failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("No Sales Item Found");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMSalesOrder");
            failData.setSourceSystem("project_one");
            failData.setKey1(vbakEntity.getVbeln());
            failData.setKey2("");
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            resultObject.setFailData(failData);
            return  resultObject;
        }
        salesOrderBo.setSalesOrderItem(vbapEntity.getPosnr());
        salesOrderBo.setLocalMaterialNumber(vbapEntity.getMatnr());
        salesOrderBo.setLocalPlant(vbapEntity.getWerks());
        salesOrderBo.setLocalItemCategory(vbapEntity.getPstyv());
        salesOrderBo.setLocalItemDlvRlvnt(vbapEntity.getLfrel());
        salesOrderBo.setLocalItemBillRlvnt(vbapEntity.getFkrel());
        salesOrderBo.setLocalRejReason(vbapEntity.getAbgru());
        salesOrderBo.setSalesOrderQty(vbapEntity.getKwmeng());
        salesOrderBo.setLocalSalesUnit(vbapEntity.getVrkme());
        salesOrderBo.setLocalNumtoBase(vbapEntity.getUmvkz());
        salesOrderBo.setLocalDentoBase(vbapEntity.getUmvkn());
        salesOrderBo.setLocalBillingBlockItem(vbapEntity.getFaksp());
        salesOrderBo.setLocalSDItemValue(vbapEntity.getNetwr());
        salesOrderBo.setLocalSDItemCurrency(vbapEntity.getWaerk());
        salesOrderBo.setLocalStorageLocation(vbapEntity.getLgort());
        salesOrderBo.setLocalShippingPoint(vbapEntity.getVstel());
        salesOrderBo.setLocalRoute(vbapEntity.getRoute());

        //rule J2
        VbepEntity vbepEntity = getFieldWithJ2(vbakEntity.getVbeln(), vbapEntity.getPosnr());
        if (null == vbapEntity){
            FailData  failData = new FailData();
            failData.setErrorCode("J2");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMSalesOrder");
            failData.setSourceSystem("project_one");
            failData.setKey1(vbakEntity.getVbeln());
            failData.setKey2("");
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            resultObject.setFailData(failData);
            return  resultObject;
        }
        salesOrderBo.setScheduleLineItem(vbepEntity.getEtenr());
        salesOrderBo.setLocalScheduleLineDate(vbepEntity.getEdatu());
        salesOrderBo.setLocalSchLineQty(vbepEntity.getWmeng());
        salesOrderBo.setLocalSchLineConfimQty(vbepEntity.getBmeng());


        //rule J3
        VbpaEntity vbpaEntity=getFieldWithJ3(vbakEntity.getVbeln(),"",IConstant.VALUE.WE);
        salesOrderBo.setLocalShipToParty(vbpaEntity.getKunnr());

        //rule J4
        VbkdEntity vbkdEntity=getFieldWithJ4(vbakEntity.getVbeln(),"");
        salesOrderBo.setLocalIncoTerms1(vbkdEntity.getInco1());
        salesOrderBo.setLocalIncoTerms2(vbkdEntity.getInco2());
        salesOrderBo.setLocalCustomerGroup(vbkdEntity.getKdgrp());

        resultObject.setBaseBo(salesOrderBo);
        return resultObject;
    }
    //J4
    private VbkdEntity getFieldWithJ4(String vbeln, String posnr) {
        VbkdEntity entity = vbkdDao.getEntityWithPosnrAndVbeln(vbeln,posnr);
        if (null != entity) {
            return entity;
        }
        VbkdEntity vbkdEntity = new VbkdEntity(new HashMap<>());
        return vbkdEntity;
    }

    //J3
    private VbpaEntity getFieldWithJ3(String vbeln,String posnr, String parvw) {
        VbpaEntity entity = vbpaDao.getEntityWithPosnrAndParvwAndVbeln(vbeln,posnr, parvw);
        if (null != entity) {
            return entity;
        }
        VbpaEntity vbpaEntity = new VbpaEntity(new HashMap<>());
        return vbpaEntity;
    }

    //J2
    private VbepEntity getFieldWithJ2(String vbeln, String posnr) {
        VbepEntity entity = vbepDao.getEntityWithVbelnAndPosnr(vbeln, posnr);
        if (null != entity) {
            return entity;
        }
        return null ;
    }
    //J1
    private VbapEntity getFieldWithJ1(String vbeln) {
        VbapEntity entity = vbapDao.getEntityWithVbeln(vbeln);
        if (null != entity) {
            return entity;
        }
        return null;

    }

}

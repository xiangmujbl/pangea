package com.jnj.pangea.edm.sales_order.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesOrderV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.*;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.edm.sales_order.bo.EDMSalesOrderBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMSalesOrderServiceImpl {

    private static EDMSalesOrderServiceImpl instance;

    public static EDMSalesOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesOrderServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private VbapDaoImpl vbapDao = VbapDaoImpl.getInstance();
    private VbepDaoImpl vbepDao = VbepDaoImpl.getInstance();
    private VbpaDaoImpl vbpaDao = VbpaDaoImpl.getInstance();
    private VbkdDaoImpl vbkdDao = VbkdDaoImpl.getInstance();
    private EDMSalesOrderV1DaoImpl salesOrderV1Dao = EDMSalesOrderV1DaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        VbakEntity vbakEntity = (VbakEntity) o;

        List<VbepEntity> vbepEntities = null;
        //rule J1
        List<VbapEntity> vbapEntities = getFieldWithJ1(vbakEntity.getVbeln());
        if (vbapEntities == null || vbapEntities.size() == 0) {
            ResultObject resultObject = new ResultObject();
            FailData failData = new FailData();
            failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
            failData.setErrorValue("No Sales Item Found");
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.EDM_SALES_ORDER);
            failData.setSourceSystem("");
            failData.setKey1(vbakEntity.getVbeln());
            failData.setKey2("");
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }

        if (null == vbapEntities || vbapEntities.size() == 0) {
            return resultObjectList;
        }
        for (VbapEntity vbapEntity : vbapEntities) {
            //rule J2
            if (vbapEntity != null) {
                vbepEntities = getFieldWithJ2(vbakEntity.getVbeln(), vbapEntity.getPosnr());
            }
            if (vbepEntities == null || vbepEntities.size() == 0) {
                ResultObject resultObject = new ResultObject();
                FailData failData = new FailData();
                failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J2);
                failData.setErrorValue("No Schedule lines Found");
                failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
                failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.EDM_SALES_ORDER);
                failData.setSourceSystem("");
                failData.setKey1(vbakEntity.getVbeln());
                failData.setKey2("");
                failData.setKey3("");
                failData.setKey4("");
                failData.setKey5("");
                resultObject.setFailData(failData);
                resultObjectList.add(resultObject);
                return resultObjectList;
            }

            for (VbepEntity vbepEntity : vbepEntities) {
                EDMSalesOrderBo salesOrderBo = new EDMSalesOrderBo();

                //rule T1
                EDMSourceSystemV1Entity sourceSystem = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
                if (sourceSystem != null) {
                    salesOrderBo.setSourceSystem(sourceSystem.getSourceSystem());
                }

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

                if (vbapEntity != null) {
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
                }

                if (vbepEntity != null) {
                    salesOrderBo.setScheduleLineItem(vbepEntity.getEtenr());
                    salesOrderBo.setLocalScheduleLineDate(vbepEntity.getEdatu());
                    salesOrderBo.setLocalSchLineQty(vbepEntity.getWmeng());
                    salesOrderBo.setLocalSchLineConfimQty(vbepEntity.getBmeng());
                }

                //rule J3
                VbpaEntity vbpaEntity = getFieldWithJ3(vbakEntity.getVbeln(), IConstant.VALUE.WE);
                if (vbpaEntity != null) {
                    salesOrderBo.setLocalShipToParty(vbpaEntity.getKunnr());
                }

                //rule J4
                VbkdEntity vbkdEntity = getFieldWithJ4(vbakEntity.getVbeln());
                if (vbkdEntity != null) {
                    salesOrderBo.setLocalIncoTerms1(vbkdEntity.getInco1());
                    salesOrderBo.setLocalIncoTerms2(vbkdEntity.getInco2());
                    salesOrderBo.setLocalCustomerGroup(vbkdEntity.getKdgrp());
                }
                ResultObject resultObject = new ResultObject();

                resultObject.setBaseBo(salesOrderBo);

                resultObjectList.add(resultObject);
            }

        }

        return resultObjectList;
    }

    //J4
    private VbkdEntity getFieldWithJ4(String vbeln) {
        VbkdEntity entity = null;
        if (StringUtils.isNotBlank(vbeln)) {
            entity = vbkdDao.getEntityWithPosnrAndVbelAndPosnrIsNullOrBlankOr000000(vbeln);
        }
        return entity;
    }

    //J3
    private VbpaEntity getFieldWithJ3(String vbeln, String parvw) {
        if (StringUtils.isNotBlank(vbeln) && StringUtils.isNotBlank(parvw)) {
            VbpaEntity entity = vbpaDao.getEntityWithPosnrAndParvwAndVbelnAndPosnrIsNullOrBlankOr000000(vbeln, parvw);
            return entity;
        }
        return null;
    }

    //J2
    private List<VbepEntity> getFieldWithJ2(String vbeln, String posnr) {
        if (StringUtils.isNotBlank(vbeln)) {
            List<VbepEntity> entity = vbepDao.getEntityWithVbelnAndPosnr(vbeln, posnr);
            return entity;
        }
        return null;
    }

    //J1
    private List<VbapEntity> getFieldWithJ1(String vbeln) {
        if (StringUtils.isNotBlank(vbeln)) {
            List<VbapEntity> entitys = vbapDao.getEntityWithVbeln(vbeln);
            return entitys;
        }
        return null;
    }

}

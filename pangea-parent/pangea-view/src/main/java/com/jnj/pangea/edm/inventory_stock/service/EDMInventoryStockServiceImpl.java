package com.jnj.pangea.edm.inventory_stock.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMarcDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMchbDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMkolDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMslbDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.common.service.ICommonListService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jnj.pangea.edm.inventory_stock.bo.EDMInventoryStockBo;
import org.apache.commons.lang3.StringUtils;

public class EDMInventoryStockServiceImpl implements ICommonListService {

    private static EDMInventoryStockServiceImpl instance;
    private String sourceSystem ="";

    public static EDMInventoryStockServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMInventoryStockServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneMchbDaoImpl mchbDao = ProjectOneMchbDaoImpl.getInstance();
    private ProjectOneMkolDaoImpl mkolDao = ProjectOneMkolDaoImpl.getInstance();
    private ProjectOneMslbDaoImpl mslbDao = ProjectOneMslbDaoImpl.getInstance();
    private ProjectOneMarcDaoImpl marcDao = ProjectOneMarcDaoImpl.getInstance();

    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        MardEntity mardEntity = (MardEntity) o;

        // set source system

        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (edmSourceSystemV1Entity != null) {
            sourceSystem=edmSourceSystemV1Entity.getSourceSystem();
        }

        //MARD

        EDMInventoryStockBo inventoryStockMardBo = new EDMInventoryStockBo();
        inventoryStockMardBo.setLocalPlant(mardEntity.getWerks());
        inventoryStockMardBo.setLocalMaterial(mardEntity.getMatnr());
        inventoryStockMardBo.setLocalStorageLocation(mardEntity.getLgort());

        inventoryStockMardBo.setLocalUnrestrictedStock(mardEntity.getLabst());

        inventoryStockMardBo.setLocalQualityInspectionStock(mardEntity.getInsme());
        inventoryStockMardBo.setLocalRestrictedStock(mardEntity.getEinme());
        inventoryStockMardBo.setLocalBlockedStock(mardEntity.getSpeme());
        inventoryStockMardBo.setLocalReturnsStock(mardEntity.getRetme());
        inventoryStockMardBo.setLocalStockInTransitPlantToPlant(mardEntity.getUmlmc());
        inventoryStockMardBo.setLocalStockInTransitPlant(mardEntity.getUmlme());
        inventoryStockMardBo.setLocalRestrictedUseConsignment(mardEntity.getKeinm());
        inventoryStockMardBo.setLocalConsignmentStockInQualityInspection(mardEntity.getKinsm());
        inventoryStockMardBo.setLocalUnrestrictedUseConsignment(mardEntity.getKlabs());

        inventoryStockMardBo.setLocalBlkdConstStkNonBm(mardEntity.getKspem());
        inventoryStockMardBo.setLocalTotalStockAllRestrictedBatches(mardEntity.getEinme());
        inventoryStockMardBo.setSourceSystem(sourceSystem);

        inventoryStockMardBo.setObjectName("MARD");
        setDefaultsForStock(inventoryStockMardBo);


        //Check stock

        if( checkforValidStock(inventoryStockMardBo)){
            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(inventoryStockMardBo);
            resultObjectList.add(resultObject);
        }


        List<MchbEntity> mchbList = joinMchb(mardEntity.getMatnr(),mardEntity.getWerks(),mardEntity.getLgort());
        List<MkolEntity> mkolList = joinMkol(mardEntity.getMatnr(),mardEntity.getWerks(),mardEntity.getLgort());
        List<MslbEntity> mslbList = joinMslb(mardEntity.getMatnr(),mardEntity.getWerks(),mardEntity.getLgort());
        List<MarcEntity> marcList = joinMarc(mardEntity.getMatnr(),mardEntity.getWerks());

        mchbList.forEach(mchbEntity -> {
            try {
                EDMInventoryStockBo inventoryStockBoMchb = new EDMInventoryStockBo();

                inventoryStockBoMchb.setLocalPlant(mchbEntity.getWerks());
                inventoryStockBoMchb.setLocalMaterial(mchbEntity.getMatnr());
                inventoryStockBoMchb.setLocalStorageLocation(mchbEntity.getLgort());
                inventoryStockBoMchb.setLocalBatchId(mchbEntity.getCharg());
                inventoryStockBoMchb.setLocalUnrestrictedBatchStock(mchbEntity.getClabs());
                inventoryStockBoMchb.setLocalQualityInspectionBatchStock(mchbEntity.getCinsm());
                inventoryStockBoMchb.setLocalRestrictedBatchStock(mchbEntity.getCeinm());
                inventoryStockBoMchb.setLocalBlockedBatchStock(mchbEntity.getCspem());
                inventoryStockBoMchb.setLocalReturnsBatchStock(mchbEntity.getCretm());
                inventoryStockBoMchb.setLocalStockInTransitBatch(mchbEntity.getCumlm());
                inventoryStockBoMchb.setSourceSystem(sourceSystem);

                inventoryStockBoMchb.setObjectName("MCHB");
                setDefaultsForStock(inventoryStockBoMchb);

                if( checkforValidStock(inventoryStockBoMchb)){
                    ResultObject resultObjectMchb = new ResultObject();
                    resultObjectMchb.setBaseBo(inventoryStockBoMchb);
                    resultObjectList.add(resultObjectMchb);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        mslbList.forEach(mslbEntity -> {

            try {
                EDMInventoryStockBo inventoryStockBoMslb = new EDMInventoryStockBo();

                inventoryStockBoMslb.setLocalPlant(mslbEntity.getWerks());
                inventoryStockBoMslb.setLocalMaterial(mslbEntity.getMatnr());
                inventoryStockBoMslb.setLocalBatchId(mslbEntity.getCharg());

                inventoryStockBoMslb.setLocalVendorNumber(mslbEntity.getLifnr());
                inventoryStockBoMslb.setLocalSpecialStockIndicator(mslbEntity.getSobkz());
                inventoryStockBoMslb.setLocalUnrestrictedSpecialStock(mslbEntity.getLblab());
                inventoryStockBoMslb.setLocalQualityInspectionSpecialStock(mslbEntity.getLbins());
                inventoryStockBoMslb.setLocalRestrictedSpecialStock(mslbEntity.getLbein());
                inventoryStockBoMslb.setLocalStockInTransitSpecial(mslbEntity.getLbuml());
                inventoryStockBoMslb.setSourceSystem(sourceSystem);

                inventoryStockBoMslb.setObjectName("MSLB");
                setDefaultsForStock(inventoryStockBoMslb);

                if( checkforValidStock(inventoryStockBoMslb)){
                    ResultObject resultObjectMslb = new ResultObject();
                    resultObjectMslb.setBaseBo(inventoryStockBoMslb);
                    resultObjectList.add(resultObjectMslb);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }


        });


        marcList.forEach(marcEntity -> {

            try {
                EDMInventoryStockBo inventoryStockBoMarc = new EDMInventoryStockBo();

                inventoryStockBoMarc.setLocalPlant(marcEntity.getWerks());
                inventoryStockBoMarc.setLocalMaterial(marcEntity.getMatnr());

                inventoryStockBoMarc.setLocalStockInTransitStorageLocation(marcEntity.getTrame());
                inventoryStockBoMarc.setSourceSystem(sourceSystem);


                inventoryStockBoMarc.setObjectName("MARC");
                setDefaultsForStock(inventoryStockBoMarc);

                if( checkforValidStock(inventoryStockBoMarc)){
                    ResultObject resultObjectMarc = new ResultObject();
                    resultObjectMarc.setBaseBo(inventoryStockBoMarc);
                    resultObjectList.add(resultObjectMarc);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });



        mkolList.forEach(mkolEntity -> {

            try {
                EDMInventoryStockBo inventoryStockBoMkol = new EDMInventoryStockBo();
                inventoryStockBoMkol.setLocalPlant(mkolEntity.getWerks());
                inventoryStockBoMkol.setLocalMaterial(mkolEntity.getMatnr());
                inventoryStockBoMkol.setLocalStorageLocation(mkolEntity.getLgort());
                inventoryStockBoMkol.setLocalBatchId(mkolEntity.getCharg());
                inventoryStockBoMkol.setLocalVendorNumber(mkolEntity.getLifnr());

                inventoryStockBoMkol.setLocalConsignmentSpecialStockIndicator(mkolEntity.getSobkz());
                inventoryStockBoMkol.setLocalUnrestrictedConsignmentStock(mkolEntity.getSlabs());
                inventoryStockBoMkol.setLocalQualityInspectionConsignmentStock(mkolEntity.getSinsm());
                inventoryStockBoMkol.setLocalRestrictedConsignmentStock(mkolEntity.getSeinm());
                inventoryStockBoMkol.setLocalBlockedConsignmentStock(mkolEntity.getSspem());
                inventoryStockBoMkol.setSourceSystem(sourceSystem);

                inventoryStockBoMkol.setObjectName("MKOL");
                setDefaultsForStock(inventoryStockBoMkol);

                if( checkforValidStock(inventoryStockBoMkol)){
                    ResultObject resultObjectMkol = new ResultObject();
                    resultObjectMkol.setBaseBo(inventoryStockBoMkol);
                    resultObjectList.add(resultObjectMkol);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        return resultObjectList;
    }

    private void setDefaultsForStock(EDMInventoryStockBo obj) {

        if( StringUtils.isBlank(obj.getLocalBlkdConstStkNonBm()))
            obj.setLocalBlkdConstStkNonBm(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalBlockedBatchStock()))
            obj.setLocalBlockedBatchStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalBlockedConsignmentStock()))
            obj.setLocalBlockedConsignmentStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalBlockedStock()))
            obj.setLocalBlockedStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalConsignmentStockInQualityInspection()))
            obj.setLocalConsignmentStockInQualityInspection(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalQualityInspectionBatchStock()))
            obj.setLocalQualityInspectionBatchStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalQualityInspectionConsignmentStock()))
            obj.setLocalQualityInspectionConsignmentStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalQualityInspectionSpecialStock()))
            obj.setLocalQualityInspectionSpecialStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalQualityInspectionStock()))
            obj.setLocalQualityInspectionStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalRestrictedBatchStock()))
            obj.setLocalRestrictedBatchStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalRestrictedSpecialStock()))
            obj.setLocalRestrictedSpecialStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalRestrictedStock()))
            obj.setLocalRestrictedStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalRestrictedUseConsignment()))
            obj.setLocalRestrictedUseConsignment(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalReturnsBatchStock()))
            obj.setLocalReturnsBatchStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalRestrictedConsignmentStock()))
            obj.setLocalRestrictedConsignmentStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalTotalStockAllRestrictedBatches()))
            obj.setLocalTotalStockAllRestrictedBatches(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalUnrestrictedBatchStock()))
            obj.setLocalUnrestrictedBatchStock(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if( StringUtils.isBlank(obj.getLocalUnrestrictedConsignmentStock()))
            obj.setLocalUnrestrictedConsignmentStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalUnrestrictedSpecialStock()))
            obj.setLocalUnrestrictedSpecialStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalUnrestrictedStock()))
            obj.setLocalUnrestrictedStock(IConstant.VALUE.ZERO_DOT_3ZEROS);


        if( StringUtils.isBlank(obj.getLocalUnrestrictedUseConsignment()))
            obj.setLocalUnrestrictedUseConsignment(IConstant.VALUE.ZERO_DOT_3ZEROS);



        if( StringUtils.isBlank(obj.getLocalReturnsStock()))
            obj.setLocalReturnsStock(IConstant.VALUE.ZERO_DOT_3ZEROS);
        if( StringUtils.isBlank(obj.getLocalStockInTransitStorageLocation()))
            obj.setLocalStockInTransitStorageLocation(IConstant.VALUE.ZERO_DOT_3ZEROS);
        if( StringUtils.isBlank(obj.getLocalStockInTransitBatch ()))
            obj.setLocalStockInTransitBatch(IConstant.VALUE.ZERO_DOT_3ZEROS);
        if( StringUtils.isBlank(obj.getLocalStockInTransitSpecial()))
            obj.setLocalStockInTransitSpecial(IConstant.VALUE.ZERO_DOT_3ZEROS);
        if( StringUtils.isBlank(obj.getLocalStockInTransitPlantToPlant()))
            obj.setLocalStockInTransitPlantToPlant(IConstant.VALUE.ZERO_DOT_3ZEROS);
        if( StringUtils.isBlank(obj.getLocalStockInTransitPlant()))
            obj.setLocalStockInTransitPlant(IConstant.VALUE.ZERO_DOT_3ZEROS);

        if(StringUtils.isBlank(obj.getLocalStorageLocation())){
            obj.setLocalStorageLocation(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalVendorNumber())){
            obj.setLocalVendorNumber(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalPlant())){
            obj.setLocalPlant(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalSpecialStockIndicator())){
            obj.setLocalSpecialStockIndicator(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalConsignmentSpecialStockIndicator())){
            obj.setLocalConsignmentSpecialStockIndicator(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalBatchId())){
            obj.setLocalBatchId(IConstant.VALUE.BLANK);
        }
        if(StringUtils.isBlank(obj.getLocalMaterial())){
            obj.setLocalMaterial(IConstant.VALUE.BLANK);
        }



    }

    private void addMkolAttributes(MardEntity mardEntity, EDMInventoryStockBo inventoryStockMardBo) {

        List<MkolEntity> mkolList = joinMkol(mardEntity.getMatnr(),mardEntity.getWerks(),mardEntity.getLgort());
        mkolList.forEach(mkolEntity -> {
            inventoryStockMardBo.setLocalBlkdConstStkNonBm(mardEntity.getKspem());
            inventoryStockMardBo.setLocalTotalStockAllRestrictedBatches(mardEntity.getEinme());
            inventoryStockMardBo.setLocalVendorNumber(mkolEntity.getLifnr());
            inventoryStockMardBo.setLocalConsignmentSpecialStockIndicator(mkolEntity.getSobkz());
            inventoryStockMardBo.setLocalUnrestrictedConsignmentStock(mkolEntity.getSlabs());
            inventoryStockMardBo.setLocalQualityInspectionConsignmentStock(mkolEntity.getSinsm());
            inventoryStockMardBo.setLocalRestrictedConsignmentStock(mkolEntity.getSeinm());
            inventoryStockMardBo.setLocalBlockedConsignmentStock(mkolEntity.getSspem());
            return;
        });
    }

    private boolean checkforValidStock(EDMInventoryStockBo inventoryStockMardBo) {
        if( !IsValidQuantity(inventoryStockMardBo.getLocalBlkdConstStkNonBm())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalBlockedBatchStock())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalBlockedConsignmentStock())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalBlockedStock())  &&

                !IsValidQuantity(inventoryStockMardBo.getLocalConsignmentStockInQualityInspection()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalQualityInspectionBatchStock())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalQualityInspectionConsignmentStock())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalQualityInspectionSpecialStock())  &&
                !IsValidQuantity(inventoryStockMardBo.getLocalQualityInspectionStock()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalRestrictedBatchStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalRestrictedConsignmentStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalRestrictedSpecialStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalRestrictedStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalRestrictedUseConsignment()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalReturnsBatchStock()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalTotalStockAllRestrictedBatches()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalUnrestrictedBatchStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalUnrestrictedConsignmentStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalUnrestrictedSpecialStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalUnrestrictedStock()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalReturnsStock()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalStockInTransitStorageLocation()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalStockInTransitBatch()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalStockInTransitSpecial()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalStockInTransitPlantToPlant()) &&
                !IsValidQuantity(inventoryStockMardBo.getLocalStockInTransitPlant()) &&

                !IsValidQuantity(inventoryStockMardBo.getLocalUnrestrictedUseConsignment())){


            return false;
        }


        return true;

    }

    private boolean IsValidQuantity(String stock) {
        if ( stock == null || stock.trim().equals("") ||stock.trim().equals("0") || stock.trim().equals("0.0") ||
                stock.trim().equals("0.00") || stock.trim().equals("0.000"))
            return false;
        return true;
    }

    public List<MchbEntity> joinMchb(String matnr, String werks,String lgort){
        return mchbDao.getMchbListWithMatnrAndWerksAndLgort(matnr, werks, lgort);
    }

    public List<MslbEntity> joinMslb(String matnr, String werks, String lgort){
        return mslbDao.getMslbListWithMatnrAndWerks(matnr, werks);
    }

    public List<MkolEntity> joinMkol(String matnr, String werks, String lgort){
        return mkolDao.getMkolListWithMatnrAndWerksAndLgort(matnr, werks, lgort);
    }

    public List<MarcEntity> joinMarc(String matnr, String werks){
        return marcDao.getMarcListWithMatnr(matnr, werks);
    }
}

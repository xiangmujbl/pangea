package com.jnj.pangea.edm.inventory_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMardDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMchbDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMslbDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMkolDaoImpl;
import com.jnj.pangea.edm.inventory_stock.bo.EDMInventoryStockBo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EDMInventoryStockServiceImpl {

    private static EDMInventoryStockServiceImpl instance;

    public static EDMInventoryStockServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMInventoryStockServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneMchbDaoImpl mchbDao = ProjectOneMchbDaoImpl.getInstance();
    private ProjectOneMkolDaoImpl mkolDao = ProjectOneMkolDaoImpl.getInstance();
    private ProjectOneMardDaoImpl mardDao = ProjectOneMardDaoImpl.getInstance();
    private ProjectOneMslbDaoImpl mslbDao = ProjectOneMslbDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        MarcEntity marcEntity = (MarcEntity) o;
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem("project_one");
        //N1
        List<MchbEntity> mchbList = joinMchb(marcEntity.getMatnr(), marcEntity.getWerks());
        mchbList.forEach(mchbEntity -> {
            try {
                EDMInventoryStockBo inventoryStockBo = new EDMInventoryStockBo();
                inventoryStockBo.setLocalBatchId(mchbEntity.getCharg());
                inventoryStockBo.setLocalUnrestrictedBatchStock(mchbEntity.getClabs());
                inventoryStockBo.setLocalQualityInspectionBatchStock(mchbEntity.getCinsm());
                inventoryStockBo.setLocalRestrictedBatchStock(mchbEntity.getCeinm());
                inventoryStockBo.setLocalBlockedBatchStock(mchbEntity.getCspem());
                inventoryStockBo.setLocalReturnsBatchStock(mchbEntity.getCretm());
                inventoryStockBo.setLocalStockInTransitBatch(mchbEntity.getCumlm());

                //N2
                List<MslbEntity> mslbList = joinMslb(marcEntity.getMatnr(), marcEntity.getWerks());
                mslbList.forEach(mslbEntity -> {
                    try {
                        EDMInventoryStockBo inventoryStockBo1 = (EDMInventoryStockBo) inventoryStockBo.copy();
                        inventoryStockBo1.setLocalVendorNumber(mslbEntity.getLifnr());
                        inventoryStockBo1.setLocalSpecialStockIndicator(mslbEntity.getSobkz());
                        inventoryStockBo1.setLocalUnrestrictedSpecialStock(mslbEntity.getLblab());
                        inventoryStockBo1.setLocalQualityInspectionSpecialStock(mslbEntity.getLbins());
                        inventoryStockBo1.setLocalRestrictedSpecialStock(mslbEntity.getLbein());
                        inventoryStockBo1.setLocalStockInTransitSpecial(mslbEntity.getLbuml());

                        //N3
                        List<MkolEntity> mkolList = joinMkol(marcEntity.getMatnr(), marcEntity.getWerks());
                        mkolList.forEach(mkolEntity -> {
                            try {
                                EDMInventoryStockBo inventoryStockBo2 = (EDMInventoryStockBo) inventoryStockBo1.copy();
                                inventoryStockBo2.setLocalConsignmentSpecialStockIndicator(mkolEntity.getSobkz());
                                inventoryStockBo2.setLocalUnrestrictedConsignmentStock(mkolEntity.getSlabs());
                                inventoryStockBo2.setLocalQualityInspectionConsignmentStock(mkolEntity.getSinsm());
                                inventoryStockBo2.setLocalRestrictedConsignmentStock(mkolEntity.getSeinm());
                                inventoryStockBo2.setLocalBlockedConsignmentStock(mkolEntity.getSspem());

                                //N4
                                List<MardEntity> mardList = joinMard(marcEntity.getMatnr(), marcEntity.getWerks());
                                mardList.forEach(mardEntity -> {
                                    try {
                                        EDMInventoryStockBo inventoryStockBo3 = (EDMInventoryStockBo) inventoryStockBo2.copy();
                                        inventoryStockBo3.setSourceSystem(edmSourceSystemV1Entity.getSourceSystem());
                                        inventoryStockBo3.setLocalStockInTransitStorageLocation(marcEntity.getTrame());
                                        inventoryStockBo3.setLocalPlant(mardEntity.getWerks());
                                        inventoryStockBo3.setLocalMaterial(mardEntity.getMatnr());
                                        inventoryStockBo3.setLocalStorageLocation(mardEntity.getLgort());
                                        inventoryStockBo3.setLocalUnrestrictedStock(mardEntity.getLabst());
                                        inventoryStockBo3.setLocalQualityInspectionStock(mardEntity.getInsme());
                                        inventoryStockBo3.setLocalRestrictedStock(mardEntity.getEinme());
                                        inventoryStockBo3.setLocalBlockedStock(mardEntity.getSpeme());
                                        inventoryStockBo3.setLocalReturnsStock(mardEntity.getRetme());
                                        inventoryStockBo3.setLocalStockInTransitPlantToPlant(mardEntity.getUmlmc());
                                        inventoryStockBo3.setLocalStockInTransitPlant(mardEntity.getUmlme());
                                        inventoryStockBo3.setLocalRestrictedUseConsignment(mardEntity.getKeinm());
                                        inventoryStockBo3.setLocalConsignmentStockInQualityInspection(mardEntity.getKinsm());
                                        inventoryStockBo3.setLocalUnrestrictedUseConsignment(mardEntity.getKlabs());
                                        inventoryStockBo3.setStandLocalBlockedConsignmentStock(mardEntity.getKspem());
                                        inventoryStockBo3.setLocalTotalStockAllRestrictedBatches(mardEntity.getEinme());

                                        ResultObject resultObject = new ResultObject();
                                        resultObject.setBaseBo(inventoryStockBo3);
                                        resultObjectList.add(resultObject);
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                        LogUtil.getLogger().info("");
                                    }
                                });
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        LogUtil.getLogger().info("----------------resultObjectList1-------------------->>>"+resultObjectList.size());
        return resultObjectList;
    }

    public List<MchbEntity> joinMchb(String matnr, String werks){
        List<MchbEntity> mchbList = mchbDao.getMchbListWithMatnrAndWerks(matnr, werks);
        if(mchbList.isEmpty()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("charg","");
            map.put("clabs","");
            map.put("cinsm","");
            map.put("ceinm","");
            map.put("cspem","");
            map.put("cretm","");
            map.put("cumlm","");
            MchbEntity mchbEntity = new MchbEntity(map);
            mchbList.add(mchbEntity);
        }
        return mchbList;
    }

    public List<MslbEntity> joinMslb(String matnr, String werks){
        List<MslbEntity> mslbList = mslbDao.getMslbListWithMatnrAndWerks(matnr, werks);
        if(mslbList.isEmpty()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("lifnr","");
            map.put("sobkz","");
            map.put("lblab","");
            map.put("lbins","");
            map.put("lbein","");
            map.put("lbuml","");
            MslbEntity mslbList1Entity = new MslbEntity(map);
            mslbList.add(mslbList1Entity);
        }
        return mslbList;
    }

    public List<MkolEntity> joinMkol(String matnr, String werks){
        List<MkolEntity> mkolList = mkolDao.getMkolListWithMatnrAndWerks(matnr, werks);
        LogUtil.getLogger().info("-----------mkolList-------------------->>>>"+mkolList.size());
        if(mkolList.isEmpty()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("sobkz","");
            map.put("slabs","");
            map.put("sinsm","");
            map.put("seinm","");
            map.put("sspem","");
            MkolEntity mkolEntity = new MkolEntity(map);
            mkolList.add(mkolEntity);
        }
        LogUtil.getLogger().info("-----------mkolList1111111-------------------->>>>"+mkolList.size());
        return mkolList;
    }

    public List<MardEntity> joinMard(String matnr, String werks){
        List<MardEntity> mardList = mardDao.getMardListWithMatnrAndWerks(matnr,werks);
        LogUtil.getLogger().info("-----------mardList-------------------->>>>"+mardList.size());
        if(mardList.isEmpty()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("werks","");
            map.put("matnr","");
            map.put("lgort","");
            map.put("labst","");
            map.put("insme","");
            map.put("einme","");
            map.put("speme","");
            map.put("retme","");
            map.put("umlmc","");
            map.put("keinm","");
            map.put("kinsm","");
            map.put("klabs","");
            map.put("kspem","");
            MardEntity mardEntity = new MardEntity(map);
            mardList.add(mardEntity);
        }
        LogUtil.getLogger().info("-----------mardList1111111-------------------->>>>"+mardList.size());
        return mardList;
    }
}

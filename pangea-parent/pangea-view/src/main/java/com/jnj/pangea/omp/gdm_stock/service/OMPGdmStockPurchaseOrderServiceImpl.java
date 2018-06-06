package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.bo.OMPGdmStockBo;
import com.jnj.pangea.omp.gdm_subcluster.bo.OMPGdmSubClusterBo;

import java.util.HashMap;
import java.util.Map;


public class OMPGdmStockPurchaseOrderServiceImpl implements ICommonService{

    private static OMPGdmStockPurchaseOrderServiceImpl instance;

    public static OMPGdmStockPurchaseOrderServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmStockPurchaseOrderServiceImpl();
        }
        return instance;
    }

    EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity = (EDMPurchaseOrderOAV1Entity) o;

        OMPGdmStockBo stockBo = new OMPGdmStockBo();

        //PO2
        stockBo.setActive("YES");
        stockBo.setActiveOPRERP("YES");
        //PO14
        stockBo.setActiveSOPERP("NO");
        //PO4
        stockBo.setBatchId("");
        //PO16
        stockBo.setBlockedQuantity("0.0");
        stockBo.setQualityQuantity("0.0");
        stockBo.setRestrictedQuantity("0.0");
        stockBo.setReturnsQuantity("0.0");
        stockBo.setTransferQuantity("0.0");
        stockBo.setUnrestrictedQuantity("0.0");
        //PO13
        stockBo.setStockType("movement");
        //PO20
        stockBo.setTransitDate("1980/01/01 00:00:00");

        //PO1
        String productId = "";
        String locationId = "";
        //A
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(purchaseOrderOAV1Entity.getMatlNum(), purchaseOrderOAV1Entity.getSourceSystem());
        if(edmMaterialGlobalV1Entity != null){
            if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
                productId = edmMaterialGlobalV1Entity.getMaterialNumber();
            } else{
                productId = edmMaterialGlobalV1Entity.getPrimaryPlanningCode();
            }

            //B
            locationId = purchaseOrderOAV1Entity.getSourceSystem() + "_" + purchaseOrderOAV1Entity.getPlntCd();


            //C,D
            if(purchaseOrderOAV1Entity.getDelvSchedCntNbr().isEmpty()) {
                stockBo.setStockId(productId + "/" + locationId + "/" + purchaseOrderOAV1Entity.getPoNum() + "/" + purchaseOrderOAV1Entity.getPoLineNbr());
            } else{
                stockBo.setStockId(productId + "/" + locationId + "/" + purchaseOrderOAV1Entity.getPoNum() + "/" + purchaseOrderOAV1Entity.getPoLineNbr() + "/" +purchaseOrderOAV1Entity.getDelvSchedCntNbr());
            }

            //PO5
            if(purchaseOrderOAV1Entity.getPoCatTypeCd().equals("F")){
                stockBo.setCertaintyID("BE");
            } else if (purchaseOrderOAV1Entity.getPoCatTypeCd().equals("L")) {
                stockBo.setCertaintyID("LE");
            }

            //PO6
        }

        resultObject.setBaseBo(stockBo);

        return resultObject;
    }
}

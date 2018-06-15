package com.jnj.pangea.omp.gdm_productcustomer.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_productcustomer.bo.OMPGdmProductCustomerBo;
import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class OMPGdmProductCustomerServiceImpl {

    private static OMPGdmProductCustomerServiceImpl instance;

    public static OMPGdmProductCustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductCustomerServiceImpl();
        }
        return instance;

    }

    private PlanCnsProductCustomerDaoImpl cnsProductCustomerDao = PlanCnsProductCustomerDaoImpl.getInstance();
    private EDMMaterialAuomV1DaoImpl materialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMinimumFractionDigits(IConstant.LFU.VALUE_DECIMAL_6);
        nf.setMaximumFractionDigits(IConstant.LFU.VALUE_DECIMAL_6);
        nf.setGroupingUsed(false);

        List<ResultObject> resultObjects = new LinkedList<>();
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
            //rule T1
            FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.T1, "localDpParentCode is missing in edm material");
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjects.add(resultObject);
            return resultObjects;
        }
        double minStock=0;
        double maxStock=0;
        double stockLevel=0;
        String LeadTime="";
        String RevenueRecognitionOffset="";
        String localDpParentCode=edmMaterialGlobalV1Entity.getLocalDpParentCode();
        List<EDMMaterialGlobalV1Entity> edmMaterialGlobalV1List = materialGlobalV1Dao.getCloneEntitiesWithLocalDpParentCode(edmMaterialGlobalV1Entity.getLocalDpParentCode());
        List<PlanCnsProductCustomerEntity> planCnsProductCustomerList = new ArrayList<>();
        List<PlanCnsProductCustomerEntity> planCnsProductCustomerTempList = new ArrayList<>();
        List<EDMMaterialAuomV1Entity> edmMaterialAuomV1List = new ArrayList<>();
        for (EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity1 : edmMaterialGlobalV1List) {
            List<PlanCnsProductCustomerEntity> planCnsProductCustomerTemp = cnsProductCustomerDao.getListWithProductIdAndSourceSystem(edmMaterialGlobalV1Entity1.getLocalMaterialNumber(), edmMaterialGlobalV1Entity1.getSourceSystem());
            if (planCnsProductCustomerTemp == null || planCnsProductCustomerTemp.size() < 1) {
                FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1, "ProductId do not exist in edm material");
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(failData);
                resultObjects.add(resultObject);
            } else {
                planCnsProductCustomerTempList.addAll(planCnsProductCustomerTemp);
            }
        }

        for (PlanCnsProductCustomerEntity planCnsProductCustomerEntity1 : planCnsProductCustomerTempList) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = materialAuomV1Dao.getListWithLocalMaterialNumber(planCnsProductCustomerEntity1.getProductId(), planCnsProductCustomerEntity1.getUom(), planCnsProductCustomerEntity1.getSourceSystem());
            if (edmMaterialAuomV1Entity == null) {
                FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J2, "uom do not exist in edm auom");
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(failData);
                resultObjects.add(resultObject);
            } else {
                //rule T7,T8
                LeadTime = getlargestValue(LeadTime,planCnsProductCustomerEntity1.getLeadTime());
                RevenueRecognitionOffset = getlargestValue(RevenueRecognitionOffset,planCnsProductCustomerEntity1.getRevenueRecognitionOffset());

                Double NumerlocalDeno = null;
                try {
                    Double localDenominator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalDenominator());
                    Double localNumerator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalNumerator());
                    NumerlocalDeno = localNumerator / localDenominator;
                } catch (Exception e) {

                }
                try {
                    //rule T4
                    minStock += Double.parseDouble(planCnsProductCustomerEntity1.getMinStock())*NumerlocalDeno;
                } catch (Exception e) {

                }
                try {
                    //rule T5
                    maxStock += Double.parseDouble(planCnsProductCustomerEntity1.getMaxStock())*NumerlocalDeno;
                } catch (Exception e) {

                }
                try {
                    //rule T6
                    stockLevel += Double.parseDouble(planCnsProductCustomerEntity1.getStockLevel())*NumerlocalDeno;
                } catch (Exception e) {

                }
                planCnsProductCustomerList.add(planCnsProductCustomerEntity1);
            }
        }
        for (PlanCnsProductCustomerEntity planCnsProductCustomerEntity : planCnsProductCustomerList) {
            OMPGdmProductCustomerBo gdmProductCustomerBo=new OMPGdmProductCustomerBo();
            //rule J1, T1, T2, T2A
            gdmProductCustomerBo.setProductCustomerId("LA_" + localDpParentCode + planCnsProductCustomerEntity.getDemandGroup() + planCnsProductCustomerEntity.getPreferredDC());
            if(StringUtils.isNotBlank(planCnsProductCustomerEntity.getProductStatus())&&planCnsProductCustomerEntity.getProductStatus().equalsIgnoreCase("ACTIVE")){
               gdmProductCustomerBo.setProductStatus("ACTIVE");
            }else{
               gdmProductCustomerBo.setProductStatus("INACTIVE");
            }
            gdmProductCustomerBo.setPreferredDC(planCnsProductCustomerEntity.getPreferredDC());
            //rule T4, T5, T6
           ;
            gdmProductCustomerBo.setMinStock(String.valueOf( nf.format(minStock)));
            gdmProductCustomerBo.setMaxStock(String.valueOf(nf.format(maxStock)));
            gdmProductCustomerBo.setStockLevel(String.valueOf(nf.format(stockLevel)));
            //rule T7, T8
            gdmProductCustomerBo.setLeadTime(LeadTime);
            gdmProductCustomerBo.setRevenueRecognitionOffset(RevenueRecognitionOffset);
            ResultObject resultObject=new ResultObject();
            resultObject.setBaseBo(gdmProductCustomerBo);
            resultObjects.add(resultObject);
        }
    return resultObjects;
    }

    private FailData writeFailDataToRegion(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, String ruleCode, String errorValue) {
         FailData failData = new FailData();
         failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
         failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCTCUSTOMER);
         failData.setErrorCode(ruleCode);
         failData.setSourceSystem(edmMaterialGlobalV1Entity.getSourceSystem());
         failData.setKey1(edmMaterialGlobalV1Entity.getLocalMaterialNumber());
         failData.setKey2("");
         failData.setKey3("");
         failData.setKey4("");
         failData.setKey5("");
         failData.setErrorValue(errorValue);
         return failData;
    }
    private String getlargestValue(String str1 ,String str2){
        if(StringUtils.isBlank(str1)){
            return str2;
        }
        if(StringUtils.isBlank(str2)){
            return str1;
        }
        double value1;
        double value2;
        try{
             value1 = Double.parseDouble(str1);
        } catch (Exception e) {
            return str2;
        }
        try{
            value2 = Double.parseDouble(str2);
        } catch (Exception e) {
            return str1;
        }
        if(value1>value2){
            return str1;
        }else {
            return str2;
        }
    }
}

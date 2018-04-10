package com.jnj.pangea.omp.gdm_batch.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMBatchMasterV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_batch.bo.OMPGdmBatchBo;

public class OMPGdmBatchServiceImpl implements ICommonService {

    private static OMPGdmBatchServiceImpl instance;

    public static OMPGdmBatchServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBatchServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMBatchMasterV1Entity batchMasterV1Entity = (EDMBatchMasterV1Entity) o;

        if (batchMasterV1Entity==null){
            return resultObject;
        }

        OMPGdmBatchBo gdmBatchBo = new OMPGdmBatchBo();

        gdmBatchBo.setBatchId(batchMasterV1Entity.getLocalBatchNumber());
        gdmBatchBo.setExpirationDate(batchMasterV1Entity.getLocalBatchExpDate());
        gdmBatchBo.setManufacturingDate(batchMasterV1Entity.getLocalBatchMfgDate());

        //rules N1
        gdmBatchBo.setActive(IConstant.VALUE.YES);
        gdmBatchBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmBatchBo.setActiveSOPERP(IConstant.VALUE.YES);

        //rules N2
        String productId=getFieldWithN2(batchMasterV1Entity.getLocalMaterialNumber());
        if ("".equals(productId)){
            FailData failData = new FailData();
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_BATCH);
            failData.setErrorCode(IConstant.FAILED.ERROR_CODE.N2);
            failData.setSourceSystem("");
            failData.setKey1(batchMasterV1Entity.getLocalMaterialNumber());
            failData.setKey2(batchMasterV1Entity.getLocalBatchNumber());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            failData.setErrorValue("");

            resultObject.setFailData(failData);
            return resultObject;
        }

        gdmBatchBo.setProductId(productId);

        resultObject.setBaseBo(gdmBatchBo);
        return resultObject;
    }

    /**
     * rules N2
     * @param localMaterialNumber
     * @return
     */
    private String getFieldWithN2(String localMaterialNumber) {
        if ("".equals(localMaterialNumber)){
           return "";
        }
        EDMMaterialGlobalV1Entity entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (entity==null){
            return "";
        }
       if (entity.getPrimaryPlanningCode()==null||"".equals(entity.getPrimaryPlanningCode())){
           if (!"".equals(entity.getMaterialNumber())){
              return entity.getMaterialNumber();
           }
       }else if (entity.getPrimaryPlanningCode().equals(entity.getMaterialNumber())){
            if (!"".equals(entity.getPrimaryPlanningCode())){
                return entity.getPrimaryPlanningCode();
            }
       }else if (!entity.getPrimaryPlanningCode().equals(entity.getMaterialNumber())){
            if (!("".equals(entity.getPrimaryPlanningCode())||"".equals(entity.getMaterialNumber()))){
                return entity.getPrimaryPlanningCode();
            }
       }
        return "";
    }
}

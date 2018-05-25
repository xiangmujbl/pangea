package com.jnj.pangea.omp.gdm_batch.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBatchMasterV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_batch.bo.OMPGdmBatchBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmBatchServiceImpl implements ICommonService {

    private static OMPGdmBatchServiceImpl instance;

    public static OMPGdmBatchServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBatchServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMBatchMasterV1Entity batchMasterV1Entity = (EDMBatchMasterV1Entity) o;

        OMPGdmBatchBo gdmBatchBo = new OMPGdmBatchBo();
        if(batchMasterV1Entity==null){
            return resultObject;
        }
        //rules N2
        String result=getFieldWithN2(batchMasterV1Entity);
        if(IConstant.VALUE.Y.equals(result)){
            String matlNum = batchMasterV1Entity.getMatlNum();
            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(batchMasterV1Entity.getMatlNum());
            if(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()){
                String materialNumber = materialGlobalV1Entity.getMaterialNumber();
                if(materialNumber.isEmpty()){
                    FailData failData = new FailData();
                    failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
                    failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_BATCH);
                    failData.setErrorCode(IConstant.FAILED.ERROR_CODE.N2);
                    failData.setSourceSystem("");
                    failData.setKey1(batchMasterV1Entity.getBtchNum());
                    failData.setKey2("");
                    failData.setKey3("");
                    failData.setKey4("");
                    failData.setKey5("");
                    failData.setErrorValue("");
                    resultObject.setFailData(failData);
                    return resultObject;
                }
                gdmBatchBo.setProductId(materialNumber);
            }else if(materialGlobalV1Entity.getPrimaryPlanningCode().equals(materialGlobalV1Entity.getMaterialNumber())){
                gdmBatchBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            }

            else if (materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()&&materialGlobalV1Entity.getMaterialNumber().isEmpty()){
                FailData failData = new FailData();
                failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
                failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_BATCH);
                failData.setErrorCode(IConstant.FAILED.ERROR_CODE.N2);
                failData.setSourceSystem("");
                failData.setKey1(batchMasterV1Entity.getBtchNum());
                failData.setKey2("");
                failData.setKey3("");
                failData.setKey4("");
                failData.setKey5("");
                failData.setErrorValue("");
                resultObject.setFailData(failData);
                return resultObject;
            }
            //rules N3

           // if(!batchMasterV1Entity.getLocalPlant().isEmpty()&&!batchMasterV1Entity.getLocalBatchNumber().isEmpty()
           //         &&!batchMasterV1Entity.getSrcSysCd().isEmpty()){

                if(!batchMasterV1Entity.getLocalPlant().isEmpty()&&  batchMasterV1Entity.getBtchNum() !=null && !batchMasterV1Entity.getBtchNum().isEmpty() && !batchMasterV1Entity.getMatlNum().isEmpty()
                        &&!batchMasterV1Entity.getSrcSysCd().isEmpty()){
                String locationId = batchMasterV1Entity.getSrcSysCd()+"_"+batchMasterV1Entity.getLocalPlant();
                String batchId = gdmBatchBo.getProductId()+"/"+locationId+"/"+batchMasterV1Entity.getBtchNum();
                gdmBatchBo.setBatchId(batchId);
            }
            //rules N4
            //rules N1
            gdmBatchBo.setActive(IConstant.VALUE.YES);
            gdmBatchBo.setActiveOPRERP(IConstant.VALUE.YES);
            gdmBatchBo.setActiveSOPERP(IConstant.VALUE.NO);
            gdmBatchBo.setExpirationDate(batchMasterV1Entity.getBtchExpDt());
            gdmBatchBo.setManufacturingDate(batchMasterV1Entity.getBtchMfgDt());
            resultObject.setBaseBo(gdmBatchBo);
        }

        return resultObject;
    }

    /**
     * rules N2
     * @param batchMasterV1Entity
     * @return
     */
    private String getFieldWithN2(EDMBatchMasterV1Entity batchMasterV1Entity) {

        String srcSysCd = batchMasterV1Entity.getSrcSysCd();
        String matlNum = batchMasterV1Entity.getMatlNum();
        String localPlant = batchMasterV1Entity.getLocalPlant();
        if((!StringUtils.isEmpty(srcSysCd))&&(!StringUtils.isEmpty(matlNum))&&(!StringUtils.isEmpty(localPlant))){
            PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusDaoEntity = cnsMaterialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity(srcSysCd, matlNum, localPlant);
            if(cnsMaterialPlanStatusDaoEntity==null){
                return IConstant.VALUE.N;
            }
            if(cnsMaterialPlanStatusDaoEntity.getSpRelevant().equals(IConstant.VALUE.X) || cnsMaterialPlanStatusDaoEntity.getNoPlanRelevant().equals(IConstant.VALUE.X)){
                return IConstant.VALUE.Y;
            }
        }
        return IConstant.VALUE.N;
    }
}

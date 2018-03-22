package com.jnj.pangea.edm.mat_plant_fi.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.projectone.MbewEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_fi.bo.EDMMatPlantFiBo;

public class EDMMatPlantFiServiceImpl implements ICommonService {

    private static EDMMatPlantFiServiceImpl instance;

    public static EDMMatPlantFiServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantFiServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        MbewEntity mbewEntity = (MbewEntity) o;

        EDMMatPlantFiBo matPlantFiBo = new EDMMatPlantFiBo();
        String sourceSystem = null;
        if(null != sourceSystemV1Dao.getSourceSystemWithProjectOne()){
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            matPlantFiBo.setSourceSystem(sourceSystem);

            matPlantFiBo.setLocalMaterialNumber(mbewEntity.getMatnr());

            String bwkey = mbewEntity.getBwkey();
            matPlantFiBo.setLocalPlant(bwkey);
            matPlantFiBo.setLocalDeleIndicator(mbewEntity.getLvorm());

            String plant = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,bwkey).getPlant();
            matPlantFiBo.setPlant(plant);

            matPlantFiBo.setPriceControl(mbewEntity.getVprsv());
            matPlantFiBo.setLocalStandardPrice(mbewEntity.getStprs());
            matPlantFiBo.setLocalPriceUnit(mbewEntity.getPeinh());
            matPlantFiBo.setLocalMvp(mbewEntity.getVerpr());
            resultObject.setBaseBo(matPlantFiBo);
        }else{
            FailData failData = writeFailDataToRegion(mbewEntity, "T1", "Unable to find the Source System");
            resultObject.setFailData(failData);
        }

        return resultObject;
    }

    private FailData writeFailDataToRegion(MbewEntity mbewEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMMatPlantFi");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("EMS");
        failData.setKey1(mbewEntity.getMatnr());
        failData.setKey2(mbewEntity.getBwkey());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}

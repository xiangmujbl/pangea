package com.jnj.pangea.edm.mat_plant_fi.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.MbewEntity;
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
        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
        matPlantFiBo.setSourceSystem(sourceSystem);

        matPlantFiBo.setLocalMaterialNumber(mbewEntity.getMatnr());

        String bwkey = mbewEntity.getBwkey();
        matPlantFiBo.setLocalPlant(bwkey);
        matPlantFiBo.setLocalDeleIndicator(mbewEntity.getLvorm());

        String plant = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem, bwkey).getPlant();
        matPlantFiBo.setPlant(plant);

        matPlantFiBo.setPriceControl(mbewEntity.getVprsv());
        matPlantFiBo.setLocalStandardPrice(mbewEntity.getStprs());
        matPlantFiBo.setLocalPriceUnit(mbewEntity.getPeinh());
        matPlantFiBo.setLocalMvp(mbewEntity.getVerpr());
        resultObject.setBaseBo(matPlantFiBo);

        return resultObject;
    }

}

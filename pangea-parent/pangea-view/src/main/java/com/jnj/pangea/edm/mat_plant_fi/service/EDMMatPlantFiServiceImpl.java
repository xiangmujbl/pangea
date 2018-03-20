package com.jnj.pangea.edm.mat_plant_fi.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.projectone.MbewEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_fi.bo.EDMMatPlantFiBo;

public class EDMMatPlantFiServiceImpl implements ICommonService {

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = new EDMSourceSystemV1DaoImpl();

    EDMPlantV1DaoImpl plantV1Dao = new EDMPlantV1DaoImpl();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        MbewEntity mbewEntity = (MbewEntity) o;

        EDMMatPlantFiBo matPlantFiBo = new EDMMatPlantFiBo();
        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        matPlantFiBo.setSourceSystem(sourceSystem);

        matPlantFiBo.setLocalMaterialNumber(mbewEntity.getMatnr());

        String bwkey = mbewEntity.getBwkey();
        matPlantFiBo.setLocalPlant(bwkey);
        matPlantFiBo.setLocalDeleIndicator(mbewEntity.getLvorm());

        String plant = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,bwkey);
        matPlantFiBo.setPlant(plant);

        matPlantFiBo.setPriceControl(mbewEntity.getVprsv());
        matPlantFiBo.setLocalStandardPrice(mbewEntity.getStprs());
        matPlantFiBo.setLocalPriceUnit(mbewEntity.getPeinh());
        matPlantFiBo.setLocalMvp(mbewEntity.getVerpr());

        resultObject.setBaseBo(matPlantFiBo);

        return resultObject;
    }
}

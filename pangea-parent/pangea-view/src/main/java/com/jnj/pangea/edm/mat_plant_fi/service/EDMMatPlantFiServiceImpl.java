package com.jnj.pangea.edm.mat_plant_fi.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.MbewEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mat_plant_fi.bo.EDMMatPlantFiBo;
import org.apache.commons.lang3.StringUtils;

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

        String sourceSystem = "";
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemV1Entity) {
            sourceSystem = sourceSystemV1Entity.getSourceSystem();
            matPlantFiBo.setSourceSystem(sourceSystem);
        }

        matPlantFiBo.setLocalMaterialNumber(mbewEntity.getMatnr());

        String bwkey = mbewEntity.getBwkey();
        matPlantFiBo.setLocalPlant(bwkey);
        matPlantFiBo.setLocalDeleIndicator(mbewEntity.getLvorm());

        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(bwkey)) {
            EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem, bwkey);
            if (null != plantV1Entity) {
                matPlantFiBo.setPlant(plantV1Entity.getPlant());
            }
        }

        matPlantFiBo.setPriceControl(mbewEntity.getVprsv());
        matPlantFiBo.setLocalStandardPrice(mbewEntity.getStprs());
        matPlantFiBo.setLocalPriceUnit(mbewEntity.getPeinh());
        matPlantFiBo.setLocalMvp(mbewEntity.getVerpr());
        resultObject.setBaseBo(matPlantFiBo);

        return resultObject;
    }

}

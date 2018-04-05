package com.jnj.pangea.edm.unit_of_measure.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmUnitOfMeasureDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMUnitOfMeasureServiceImpl implements ICommonService {

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EMSFMdmUnitOfMeasureDaoImpl emsfMdmUnitOfMeasureDao = EMSFMdmUnitOfMeasureDaoImpl.getInstance();
    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMUnitOfMeasureServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFMdmUnitOfMeasureEntity mainData = (EMSFMdmUnitOfMeasureEntity) o;

        EDMUnitOfMeasureBo edmUnitOfMeasureBo = new EDMUnitOfMeasureBo();
        resultObject.setBaseBo(edmUnitOfMeasureBo);

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(mainData.getzSourceSystem());
        edmUnitOfMeasureBo.setSourceSystem(sourceSystem);

        processSystem(mainData, edmUnitOfMeasureBo);
        EMSFMdmUnitOfMeasureEntity emsfMdmUnitOfMeasureEntity = emsfMdmUnitOfMeasureDao.getMdmNameWithzSourceSystemAndMdmSapCode(IConstant.VALUE.EMS, mainData.getzEnterpriseCode());
        if (emsfMdmUnitOfMeasureEntity != null) {
            edmUnitOfMeasureBo.setUomName(emsfMdmUnitOfMeasureEntity.getMdmName());
        }

        return resultObject;
    }

    private final boolean processSystem(EMSFMdmUnitOfMeasureEntity mainData, EDMUnitOfMeasureBo edmUnitOfMeasureBo) {
        edmUnitOfMeasureBo.setLocalUom(mainData.getMdmSapCode());
        edmUnitOfMeasureBo.setLocalUomName(mainData.getMdmName());
        edmUnitOfMeasureBo.setUom(mainData.getzEnterpriseCode());
        edmUnitOfMeasureBo.setIsoCode(mainData.getMdmIsoCode());
        edmUnitOfMeasureBo.setMeasure(mainData.getzUnitsDimension());
        edmUnitOfMeasureBo.setFactor("");
        edmUnitOfMeasureBo.setRoundingDecimal("");
        return true;
    }

}

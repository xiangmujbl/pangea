package com.jnj.pangea.edm.unit_of_measure.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmUnitOfMeasureDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;
import org.apache.commons.lang3.StringUtils;

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

        String sourceSystem = mainData.getzSourceSystem();
        if (StringUtils.isNotEmpty(sourceSystem)) {
            edmUnitOfMeasureBo.setSourceSystem(sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(sourceSystem));
        }

        processSystem(mainData, edmUnitOfMeasureBo);

        String zCode = mainData.getzEnterpriseCode();
        if (StringUtils.isNotEmpty(zCode)) {
            EMSFMdmUnitOfMeasureEntity emsfMdmUnitOfMeasureEntity = emsfMdmUnitOfMeasureDao.getMdmNameWithzSourceSystemAndMdmSapCode(IConstant.VALUE.EMS, zCode);
            if (emsfMdmUnitOfMeasureEntity != null) {
                edmUnitOfMeasureBo.setUomName(emsfMdmUnitOfMeasureEntity.getMdmName());
                edmUnitOfMeasureBo.setIsoCode(emsfMdmUnitOfMeasureEntity.getMdmIsoCode());
                edmUnitOfMeasureBo.setMeasure(emsfMdmUnitOfMeasureEntity.getzUnitsDimension());
            }
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

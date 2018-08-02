package com.jnj.pangea.edm.unit_of_measure.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSEdmUnitInputDaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmUnitOfMeasureDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSEdmUnitInputEntity;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMUnitOfMeasureServiceImpl implements ICommonService {


    private EMSEdmUnitInputDaoImpl emsEdmUnitInputDao = EMSEdmUnitInputDaoImpl.getInstance();
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
        LogUtil.getCoreLog().info("Start View=======");

        ResultObject resultObject = new ResultObject();

        EMSFMdmUnitOfMeasureEntity mainData = (EMSFMdmUnitOfMeasureEntity) o;

        EDMUnitOfMeasureBo edmUnitOfMeasureBo = new EDMUnitOfMeasureBo();
        resultObject.setBaseBo(edmUnitOfMeasureBo);

        String sourceSystem = mainData.getzSourceSystem();
        //T1
        if (StringUtils.isNotEmpty(sourceSystem)) {
            edmUnitOfMeasureBo.setSourceSystem(sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(sourceSystem));
        }
        processSystem(mainData, edmUnitOfMeasureBo);

        //T3

        EMSEdmUnitInputEntity emsEdmUnitInputEntity  = emsEdmUnitInputDao.queryEdmUnitInput(edmUnitOfMeasureBo.getSourceSystem(),mainData.getMdmSapCode());
        if(emsEdmUnitInputEntity!=null) {

            edmUnitOfMeasureBo.setFactor(emsEdmUnitInputEntity.getFactor());
                edmUnitOfMeasureBo.setRoundingDecimal(emsEdmUnitInputEntity.getRoundingDecimal());
        }
        String zCode = mainData.getzEnterpriseCode();
       //T2
        if (StringUtils.isNotEmpty(zCode)) {
            EMSFMdmUnitOfMeasureEntity emsfMdmUnitOfMeasureEntity = emsfMdmUnitOfMeasureDao.getMdmNameWithzSourceSystemAndMdmSapCode(IConstant.VALUE.EMS, zCode);
            if (emsfMdmUnitOfMeasureEntity != null) {
                edmUnitOfMeasureBo.setIsoCode(emsfMdmUnitOfMeasureEntity.getMdmIsoCode());
                edmUnitOfMeasureBo.setMeasure(emsfMdmUnitOfMeasureEntity.getzUnitsDimension());
                edmUnitOfMeasureBo.setUomName(emsfMdmUnitOfMeasureEntity.getMdmName());
            }
        }else {
            EMSFMdmUnitOfMeasureEntity emsfMdmUnitOfMeasureEntity = emsfMdmUnitOfMeasureDao.getMdmNameWithzSourceSystemAndMdmSapCode(IConstant.VALUE.EMS);
            if (emsfMdmUnitOfMeasureEntity != null) {
                edmUnitOfMeasureBo.setIsoCode(emsfMdmUnitOfMeasureEntity.getMdmIsoCode());
                edmUnitOfMeasureBo.setMeasure(emsfMdmUnitOfMeasureEntity.getzUnitsDimension());
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
        return true;
    }


}

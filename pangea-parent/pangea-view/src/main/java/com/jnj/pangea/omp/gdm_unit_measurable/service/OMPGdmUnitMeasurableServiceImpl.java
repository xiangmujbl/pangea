package com.jnj.pangea.omp.gdm_unit_measurable.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_measurable.bo.OMPGdmUnitMeasurableBo;

public class OMPGdmUnitMeasurableServiceImpl implements ICommonService {

    private static OMPGdmUnitMeasurableServiceImpl instance;

    public static OMPGdmUnitMeasurableServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmUnitMeasurableServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMUnitOfMeasureV1Entity unitOfMeasureV1Entity = (EDMUnitOfMeasureV1Entity) o;

        OMPGdmUnitMeasurableBo gdmUnitMeasurableBo = new OMPGdmUnitMeasurableBo();
        String localUom = unitOfMeasureV1Entity.getLocalUom();
        CnsPlanUnitEntity cnsPlanUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localUom);
        if (null != cnsPlanUnitEntity) {
            gdmUnitMeasurableBo.setUnitId(cnsPlanUnitEntity.getUnit());
            gdmUnitMeasurableBo.setActive(IConstant.VALUE.YES);
            gdmUnitMeasurableBo.setActiveFCTERP(IConstant.VALUE.YES);
            gdmUnitMeasurableBo.setActiveOPRERP(IConstant.VALUE.YES);
            gdmUnitMeasurableBo.setActiveSOPERP(IConstant.VALUE.YES);
            gdmUnitMeasurableBo.setFactor(unitOfMeasureV1Entity.getFactor());
            gdmUnitMeasurableBo.setISOCode(unitOfMeasureV1Entity.getIsoCode());
            gdmUnitMeasurableBo.setMeasure(unitOfMeasureV1Entity.getMeasure());
            gdmUnitMeasurableBo.setPrecision(unitOfMeasureV1Entity.getRoundingDecimal());
            gdmUnitMeasurableBo.setLongDescription(unitOfMeasureV1Entity.getUomName());
            gdmUnitMeasurableBo.setShortDescription(unitOfMeasureV1Entity.getUomName());
            resultObject.setBaseBo(gdmUnitMeasurableBo);

        } else {
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.GDM_UNIT_MEASURABLE, IConstant.FAILED.ERROR_CODE.T1,
                    "Enterprise UOM is missing for local UOM", "", unitOfMeasureV1Entity.getLocalUom(),
                    unitOfMeasureV1Entity.getSourceSystem()));
        }
        return resultObject;
    }
}

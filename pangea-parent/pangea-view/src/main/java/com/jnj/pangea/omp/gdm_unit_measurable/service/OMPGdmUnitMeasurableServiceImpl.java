package com.jnj.pangea.omp.gdm_unit_measurable.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_measurable.bo.OMPGdmUnitMeasurableBo;
import org.apache.commons.lang.StringUtils;

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

        // rule T1
        String localUom = unitOfMeasureV1Entity.getLocalUom();
        if (StringUtils.isNotEmpty(localUom)) {
            PlanCnsPlanUnitEntity cnsPlanUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localUom);
            if (null != cnsPlanUnitEntity) {
                if (StringUtils.isNotEmpty(cnsPlanUnitEntity.getUnit())) {
                    gdmUnitMeasurableBo.setUnitId(cnsPlanUnitEntity.getUnit());
                    // rules D1
                    gdmUnitMeasurableBo.setActive(IConstant.VALUE.YES);
                    gdmUnitMeasurableBo.setActiveFCTERP(IConstant.VALUE.YES);
                    gdmUnitMeasurableBo.setActiveOPRERP(IConstant.VALUE.YES);
                    // rules D2
                    gdmUnitMeasurableBo.setActiveSOPERP(IConstant.VALUE.NO);
                    gdmUnitMeasurableBo.setFactor(unitOfMeasureV1Entity.getFactor());
                    gdmUnitMeasurableBo.setIsoCode(unitOfMeasureV1Entity.getIsoCode());
                    gdmUnitMeasurableBo.setMeasure(unitOfMeasureV1Entity.getMeasure());
                    gdmUnitMeasurableBo.setPrecision(unitOfMeasureV1Entity.getRoundingDecimal());
                    gdmUnitMeasurableBo.setLongDescription(unitOfMeasureV1Entity.getUomName());
                    gdmUnitMeasurableBo.setShortDescription(unitOfMeasureV1Entity.getUomName());
                    resultObject.setBaseBo(gdmUnitMeasurableBo);
                }
            } else {

                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.GDM_UNIT_MEASURABLE, IConstant.FAILED.ERROR_CODE.E1,
                        "Enterprise UOM is missing for local UOM", "omp", unitOfMeasureV1Entity.getLocalUom(),
                        unitOfMeasureV1Entity.getSourceSystem()));
            }
        }
        return resultObject;
    }
}

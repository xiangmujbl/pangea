package com.jnj.pangea.omp.gdm_unit_measurable.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMUnitOfMeasureV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;
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

    private EDMUnitOfMeasureV1DaoImpl unitOfMeasureV1Dao = EDMUnitOfMeasureV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsPlanUnitEntity cnsPlanUnitEntity = (PlanCnsPlanUnitEntity) o;

        OMPGdmUnitMeasurableBo gdmUnitMeasurableBo = new OMPGdmUnitMeasurableBo();

            // rules T1
        gdmUnitMeasurableBo.setUnitId(cnsPlanUnitEntity.getUnit());
        // rules D1
        gdmUnitMeasurableBo.setActive(IConstant.VALUE.YES);
        // rules D3
        if (IConstant.VALUE.DP.equals(cnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(cnsPlanUnitEntity.getPlanFlag())) {
            gdmUnitMeasurableBo.setActiveFCTERP(IConstant.VALUE.YES);
        } else {
            gdmUnitMeasurableBo.setActiveFCTERP(IConstant.VALUE.NO);
        }
        // rules D4
        if (IConstant.VALUE.SP1.equals(cnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(cnsPlanUnitEntity.getPlanFlag())) {
            gdmUnitMeasurableBo.setActiveOPRERP(IConstant.VALUE.YES);
        } else {
            gdmUnitMeasurableBo.setActiveOPRERP(IConstant.VALUE.NO);
        }
        // rules D2
        gdmUnitMeasurableBo.setActiveSOPERP(IConstant.VALUE.NO);

        String unit = cnsPlanUnitEntity.getUnit();
        if (StringUtils.isNotEmpty(unit)) {

            EDMUnitOfMeasureV1Entity unitOfMeasureV1Entity = unitOfMeasureV1Dao.getEntityWithConditions(unit);
            if (null != unitOfMeasureV1Entity){

                // rules F1
                if (StringUtils.isNotEmpty(cnsPlanUnitEntity.getFactor())) {
                    gdmUnitMeasurableBo.setFactor(cnsPlanUnitEntity.getFactor());
                } else {
                    gdmUnitMeasurableBo.setFactor(IConstant.VALUE.ONE);
                }
            } else {

                // rules F1
                gdmUnitMeasurableBo.setFactor(IConstant.VALUE.ONE);
            }

        }

        if (StringUtils.isNotEmpty(unit) && StringUtils.isNotEmpty(cnsPlanUnitEntity.getSourceSystem())) {
            EDMUnitOfMeasureV1Entity unitOfMeasureV1Entity1 = unitOfMeasureV1Dao.getEntityWithUnitAndSourceSystem(unit,cnsPlanUnitEntity.getSourceSystem());
            if (null != unitOfMeasureV1Entity1) {

                // rules F2
                if (StringUtils.isNotEmpty(unitOfMeasureV1Entity1.getIsoCode())) {
                    gdmUnitMeasurableBo.setIsoCode(unitOfMeasureV1Entity1.getIsoCode());
                } else {
                    gdmUnitMeasurableBo.setIsoCode(IConstant.VALUE.BLANK);
                }

                // rules T2
                if (StringUtils.isNotEmpty(unitOfMeasureV1Entity1.getUomName())) {
                    gdmUnitMeasurableBo.setLongDescription(unitOfMeasureV1Entity1.getUomName());
                } else {
                    gdmUnitMeasurableBo.setLongDescription(cnsPlanUnitEntity.getLocalUomName());
                }

                // rules T5
                if (StringUtils.isNotEmpty(unitOfMeasureV1Entity1.getUomName())) {
                    gdmUnitMeasurableBo.setShortDescription(unitOfMeasureV1Entity1.getUomName());
                } else {
                    gdmUnitMeasurableBo.setShortDescription(cnsPlanUnitEntity.getLocalUomName());
                }

                // rules T3
                if (StringUtils.isNotEmpty(unitOfMeasureV1Entity1.getMeasure())) {
                    gdmUnitMeasurableBo.setMeasure(unitOfMeasureV1Entity1.getMeasure());
                } else {
                    gdmUnitMeasurableBo.setMeasure(IConstant.VALUE.AAAADL);
                }

                // rules T4
                if (StringUtils.isNotEmpty(unitOfMeasureV1Entity1.getRoundingDecimal())) {
                    gdmUnitMeasurableBo.setPrecision(unitOfMeasureV1Entity1.getRoundingDecimal());
                } else {}
            } else {
                    gdmUnitMeasurableBo.setPrecision(cnsPlanUnitEntity.getRoundingDecimal());
                

                // rules F2
                gdmUnitMeasurableBo.setIsoCode(IConstant.VALUE.BLANK);
                // rules T4
                gdmUnitMeasurableBo.setPrecision(cnsPlanUnitEntity.getRoundingDecimal());
                // rules T2
                gdmUnitMeasurableBo.setLongDescription(cnsPlanUnitEntity.getLocalUomName());
                // rules T3
                gdmUnitMeasurableBo.setMeasure(IConstant.VALUE.AAAADL);
                // rules T5
                gdmUnitMeasurableBo.setShortDescription(cnsPlanUnitEntity.getLocalUomName());
            }
        }
        resultObject.setBaseBo(gdmUnitMeasurableBo);
        return resultObject;
    }
}

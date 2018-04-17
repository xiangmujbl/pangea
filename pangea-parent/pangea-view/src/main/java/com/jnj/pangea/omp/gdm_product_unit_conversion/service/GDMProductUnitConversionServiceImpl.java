package com.jnj.pangea.omp.gdm_product_unit_conversion.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialAuomDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product_unit_conversion.bo.GDMProductUnitConversionBo;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;

public class GDMProductUnitConversionServiceImpl implements ICommonService {

    private static GDMProductUnitConversionServiceImpl instance;

    private final DecimalFormat df = new DecimalFormat("0.00");


    public static GDMProductUnitConversionServiceImpl getInstance() {
        if (instance == null) {
            instance = new GDMProductUnitConversionServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private EDMMaterialAuomDaoImpl edmMaterialAuomDao = EDMMaterialAuomDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        GDMProductUnitConversionBo gdmProductUnitConversionBo = new GDMProductUnitConversionBo();


        processSystem(edmMaterialGlobalV1Entity, gdmProductUnitConversionBo);

        String localBaseUOM = edmMaterialGlobalV1Entity.getLocalBaseUom();
        if (StringUtils.isNotEmpty(localBaseUOM)) {
            CnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUOM);
            String gdmProductUnitConversionId = "";
            if (StringUtils.isNotEmpty(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                gdmProductUnitConversionId += edmMaterialGlobalV1Entity.getPrimaryPlanningCode();
            }
            if (cnsPlanUnitEntity != null) {
                if (StringUtils.isNotEmpty(cnsPlanUnitEntity.getUnit())) {
                    gdmProductUnitConversionId += cnsPlanUnitEntity.getUnit();
                }
                if (!StringUtils.isEmpty(cnsPlanUnitEntity.getUnit())) {
                    gdmProductUnitConversionBo.setUnitId(cnsPlanUnitEntity.getUnit());
                }
            }
            gdmProductUnitConversionBo.setGdmProductUnitConversionId(gdmProductUnitConversionId);
        }

        String localMaterialNumber = edmMaterialGlobalV1Entity.getLocalMaterialNumber();
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithLocalMaterialNumAndLocalAuom(localMaterialNumber, localMaterialNumber);
            if (edmMaterialAuomV1Entity != null) {
                String factor = String.valueOf(df.format((float) Integer.valueOf(edmMaterialAuomV1Entity.getLocalNumerator()) / Integer.valueOf(edmMaterialAuomV1Entity.getLocalDenominator())));
                gdmProductUnitConversionBo.setFactor(factor);
            }
        }

        resultObject.setBaseBo(gdmProductUnitConversionBo);
        return resultObject;
    }


    private void processSystem(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, GDMProductUnitConversionBo gdmProductUnitConversionBo) {
        gdmProductUnitConversionBo.setProductId(edmMaterialGlobalV1Entity.getLocalDpParentCode());
        gdmProductUnitConversionBo.setActive(IConstant.VALUE.YES);
        gdmProductUnitConversionBo.setActiveFCTERP(IConstant.VALUE.YES);
        gdmProductUnitConversionBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmProductUnitConversionBo.setActiveSOPERP(IConstant.VALUE.NO);
    }
}

package com.jnj.pangea.omp.gdm_location_detail.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.omp.gdm_location_detail.bo.OMPGdmLocationDetailBo;

import java.util.LinkedList;
import java.util.List;

public class OMPGdmLocationDetailServiceImpl {

    private static OMPGdmLocationDetailServiceImpl instance;

    public static OMPGdmLocationDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationDetailServiceImpl();
        }
        return instance;
    }

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new LinkedList<>();
        PlanCnsPlantAttrEntity cnsPlantAttrEntity = (PlanCnsPlantAttrEntity) o;

        String[] names = new String[4];
        String[] values = new String[4];;

        names[0] = cnsPlantAttrEntity.getLocationAttribute1Desc();
        names[1] = cnsPlantAttrEntity.getLocationAttribute2Desc();
        names[2] = cnsPlantAttrEntity.getLocationAttribute3Desc();
        names[3] = cnsPlantAttrEntity.getLocationAttribute4Desc();

        values[0] = cnsPlantAttrEntity.getLocationAttribute1Value();
        values[1] = cnsPlantAttrEntity.getLocationAttribute2Value();
        values[2] = cnsPlantAttrEntity.getLocationAttribute3Value();
        values[3] = cnsPlantAttrEntity.getLocationAttribute4Value();

        // T1 - create multi records per attr
        for (int i=0;i<4;i++){

            ResultObject resultObject = new ResultObject();

            if (names[i] == null) {
                names[i] = "";
            }

            OMPGdmLocationDetailBo gdmLocationDetailBo = new OMPGdmLocationDetailBo();

            // Rule T1/T2
            gdmLocationDetailBo.setName(names[i]);
            gdmLocationDetailBo.setValue(values[i]);

            // Rule N3
            String CLASS = "";
            if (IConstant.VALUE.CONS_LATAM.equals(cnsPlantAttrEntity.getSourceSystem())) {
                CLASS = IConstant.VALUE.PGA;
            }
            gdmLocationDetailBo.setCLASS(CLASS);


            // Rule C1
            String locationid = cnsPlantAttrEntity.getSourceSystem() + IConstant.VALUE.UNDERLINE + cnsPlantAttrEntity.getLocalPlant();
            gdmLocationDetailBo.setLocationId(locationid);

            // Rule C2
            String locationDetailId = locationid + IConstant.VALUE.BACK_SLANT + CLASS + IConstant.VALUE.BACK_SLANT + names[i];// + IConstant.VALUE.BACK_SLANT + values[i];
            gdmLocationDetailBo.setLocationDetailId(locationDetailId);


            // Rule N1
            gdmLocationDetailBo.setActiveOPRERP(IConstant.VALUE.YES);

            // Rule N4
            gdmLocationDetailBo.setComments("");
            gdmLocationDetailBo.setUnit("");

            // Rule N5
            gdmLocationDetailBo.setActiveSOPERP(IConstant.VALUE.NO);

            resultObject.setBaseBo(gdmLocationDetailBo);
            resultObjectList.add(resultObject);

        }

        return resultObjectList;
    }
}

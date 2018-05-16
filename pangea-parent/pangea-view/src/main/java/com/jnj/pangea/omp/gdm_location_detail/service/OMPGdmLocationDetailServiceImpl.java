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

        String name1 = cnsPlantAttrEntity.getLocationAttribute1Desc();
        String name2 = cnsPlantAttrEntity.getLocationAttribute2Desc();
        String name3 = cnsPlantAttrEntity.getLocationAttribute3Desc();
        String name4 = cnsPlantAttrEntity.getLocationAttribute4Desc();

        String value1 = cnsPlantAttrEntity.getLocationAttribute1Value();
        String value2 = cnsPlantAttrEntity.getLocationAttribute2Value();
        String value3 = cnsPlantAttrEntity.getLocationAttribute3Value();
        String value4 = cnsPlantAttrEntity.getLocationAttribute4Value();

        String name = "";
        String value = "";




        // T1 - create multi records per attr
        for (int i=0;i<4;i++){

            ResultObject resultObject = new ResultObject();

            if (i == 0){
                name = name1;
                value = value1;
            }else if (i == 1){
                name = name2;
                value = value2;
            }else if (i==2){
                name = name3;
                value = value3;
            }else if (i==3){
                name = name4;
                value = value4;
            }

            if (name == null || "".equals(name.trim()))
                continue;

            OMPGdmLocationDetailBo gdmLocationDetailBo = new OMPGdmLocationDetailBo();

            // Rule T1/T2
                gdmLocationDetailBo.setName(name);
                gdmLocationDetailBo.setValue(value);

                // Rule N5
                gdmLocationDetailBo.setActiveSoperp(IConstant.VALUE.NO);

                // Rule N3
                String CLASS = "";
                if (IConstant.VALUE.CONS_LATAM.equals(cnsPlantAttrEntity.getSourceSystem())) {
                    CLASS = IConstant.VALUE.PGA;
                }
                gdmLocationDetailBo.setCLASS(CLASS);


               // Rule C1
               String locationid = cnsPlantAttrEntity.getSourceSystem()+ IConstant.VALUE.UNDERLINE+cnsPlantAttrEntity.getLocalPlant();
               gdmLocationDetailBo.setLocationId(locationid);

                // Rule C2
                String locationDetailId = locationid + IConstant.VALUE.BACK_SLANT + CLASS + IConstant.VALUE.BACK_SLANT + name + IConstant.VALUE.BACK_SLANT + value;
                gdmLocationDetailBo.setLocationDetailId(locationDetailId);


            // Rule N1
            gdmLocationDetailBo.setActiveOprerp(IConstant.VALUE.YES);

            resultObject.setBaseBo(gdmLocationDetailBo);
            resultObjectList.add(resultObject);

        }

        return resultObjectList;
    }
}

package com.jnj.pangea.omp.gdm_lot_size_key.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsLotSizeKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_lot_size_key.bo.OMPGdmLotSizeKeyBo;
import com.jnj.pangea.omp.lot_size_key.bo.OMPLotSizeKeyBo;

public class OMPGdmLotSizeKeyServiceImpl implements ICommonService {

    private static OMPGdmLotSizeKeyServiceImpl instance;

    public static OMPGdmLotSizeKeyServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLotSizeKeyServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsLotSizeKeyEntity cnsLotSizeKeyEntity = (PlanCnsLotSizeKeyEntity) o;

        OMPGdmLotSizeKeyBo lotSizeKeyBo = new OMPGdmLotSizeKeyBo();
        if (null!=cnsLotSizeKeyEntity){
            lotSizeKeyBo.setLotSizeKey(cnsLotSizeKeyEntity.getLotSizeKey());
            lotSizeKeyBo.setComments(cnsLotSizeKeyEntity.getComments());
            lotSizeKeyBo.setDescRiption(cnsLotSizeKeyEntity.getLotSizeKeyDescription());
            lotSizeKeyBo.setPeriod(cnsLotSizeKeyEntity.getPeriod());
            lotSizeKeyBo.setQuantity(cnsLotSizeKeyEntity.getQuantity());
            lotSizeKeyBo.setActiveOprerp(IConstant.VALUE.YES);
            lotSizeKeyBo.setActiveSoperp("");
        }
        resultObject.setBaseBo(lotSizeKeyBo);
        return resultObject;
    }
}

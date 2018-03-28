package com.jnj.pangea.omp.lot_size_key.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsLotSizeKeyEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.lot_size_key.bo.OMPLotSizeKeyBo;

public class OMPLotSizeKeyServiceImpl implements ICommonService {

    private static OMPLotSizeKeyServiceImpl instance;

    public static OMPLotSizeKeyServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPLotSizeKeyServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsLotSizeKeyEntity cnsLotSizeKeyEntity = (PlanCnsLotSizeKeyEntity) o;

        OMPLotSizeKeyBo lotSizeKeyBo = new OMPLotSizeKeyBo();
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

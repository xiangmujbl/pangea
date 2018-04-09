package com.jnj.pangea.plan.cns_prod_cty_affl.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProdCtyAfflDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_prod_cty_affl.service.PlanCnsProdCtyAfflServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanCnsProdCtyAfflController extends CommonController {

    private ICommonService service = PlanCnsProdCtyAfflServiceImpl.getInstance();
    private PlanCnsProdCtyAfflDaoImpl prodCtyAfflDao = PlanCnsProdCtyAfflDaoImpl.getInstance();

    private Map<String, Object> prodCtyAfflEntityMap = getProdCtyAfflEntityMap();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialGlobalV1Entity.class), prodCtyAfflEntityMap);
    }

    private Map<String, Object> getProdCtyAfflEntityMap(){

        List<PlanCnsProdCtyAfflEntity> prodCtyAfflEntities = prodCtyAfflDao.getEntitiesAll();

        Map<String, Object> prodCtyAfflEntityMap = new HashMap<>();
        for (PlanCnsProdCtyAfflEntity prodCtyAfflEntity:prodCtyAfflEntities) {
            prodCtyAfflEntityMap.put(prodCtyAfflEntity.getDpParentCode(),prodCtyAfflEntity);
        }
        return prodCtyAfflEntityMap;
    }
}

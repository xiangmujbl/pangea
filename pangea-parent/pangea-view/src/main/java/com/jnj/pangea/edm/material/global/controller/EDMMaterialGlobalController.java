package com.jnj.pangea.edm.material.global.controller;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entry.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entry.projectone.MaraEntity;
import com.jnj.pangea.edm.material.global.service.EDMMaterialGlobalServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class EDMMaterialGlobalController extends BaseController {

    private EDMMaterialGlobalServiceImpl materialGlobalService = (EDMMaterialGlobalServiceImpl) EDMMaterialGlobalServiceImpl.getInstance();
    private ICommonDao commonDao = CommonDaoImpl.getInstance();


    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        String sourceSystem = getFieldWithT1();

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            MaraEntity maraEntity = BeanUtil.mapToBean(rawValue.toMap(), new MaraEntity());

            ResultObject resultObject = materialGlobalService.buildView(raw.getKey(), maraEntity, sourceSystem);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                result.add(viewRaw);
            } else {
                if (resultObject.getFailData() != null) {
                    FailData failData = resultObject.getFailData();
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(CommonRegionPath.FAIL_DATA, failData.getKey(), failData.toMap());
                    result.add(viewResultItem);
                }
            }
        });
        return result;
    }

    private String getFieldWithT1() {

        String queryString = QueryHelper.buildCriteria(CommonRegionPath.LOCALSOURCESYSTEM).is(CommonRegionPath.LOCALSOURCESYSTEM_PROJECT_ONE).toQueryString();

        EDMSourceSystemV1Entity sourceSystems = commonDao.queryForObject(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystems) {
            return sourceSystems.getSourceSystem();
        }
        return "";
    }

}
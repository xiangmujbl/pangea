package com.jnj.pangea.edm.material.global.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.MaraEntity;
import com.jnj.pangea.edm.material.global.service.EDMMaterialGlobalServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class EDMMaterialGlobalController extends BaseController {

    private EDMMaterialGlobalServiceImpl materialGlobalService = (EDMMaterialGlobalServiceImpl) EDMMaterialGlobalServiceImpl.getInstance();

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);

        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(raw -> {

            RawDataValue rawValue = raw.getValue();

            MaraEntity maraEntity = BeanUtil.mapToBean(rawValue.toMap(), MaraEntity.class);

            ResultObject resultObject = materialGlobalService.buildView(raw.getKey(), maraEntity, sourceSystem);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                result.add(viewRaw);
            } else {
                if (resultObject.getFailData() != null) {
                    FailData failData = resultObject.getFailData();
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap());
                    result.add(viewResultItem);
                }
            }
        });
        return result;
    }
}
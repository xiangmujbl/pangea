package com.jnj.pangea.edm.unit_of_measure.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ems.EMSFMdmUnitsEntity;
import com.jnj.pangea.common.entry.projectone.MarmEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import com.jnj.pangea.edm.country.service.EDMCountryServiceImpl;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;
import com.jnj.pangea.edm.unit_of_measure.service.EDMUnitOfMeasureServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMUnitOfMeasureController extends BaseController implements IEventProcessor {

    private ICommonService edmUnitOfMeasureService = EDMUnitOfMeasureServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(mainRaw -> {
            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();
            Map map = mainValue.toMap();
            try {
                EMSFMdmUnitsEntity mainObject = (EMSFMdmUnitsEntity) BeanUtil.mapToObject(map, EMSFMdmUnitsEntity.class);
                LogUtil.getCoreLog().info(">>>>>>>>>>>start>>>>>>>>>mainObject:{}", mainObject);
                ResultObject resultObject = edmUnitOfMeasureService.buildView(key, mainObject, null);
                EDMUnitOfMeasureBo edmUnitOfMeasureBo = (EDMUnitOfMeasureBo) resultObject.getBaseBo();
                LogUtil.getCoreLog().info(">>>>>>>>>>>result:{}", resultObject.isSuccess());
                if (resultObject.isSuccess()) {
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(edmUnitOfMeasureBo.getKey(), edmUnitOfMeasureBo.toMap());
                    result.add(viewResultItem);
                }
            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMCountryController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMCountryController Exception:", e);
            }
        });
        return result;
    }

}

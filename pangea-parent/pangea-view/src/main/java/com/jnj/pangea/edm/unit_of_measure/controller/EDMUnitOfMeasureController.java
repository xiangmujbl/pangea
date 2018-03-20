package com.jnj.pangea.edm.unit_of_measure.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import com.jnj.pangea.common.service.ICommonService;
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
                EMSFMdmUnitOfMeasureEntity mainObject = BeanUtil.mapToBean(map, EMSFMdmUnitOfMeasureEntity.class);
//                EMSFMdmUnitOfMeasureEntity mainObject =  EMSFMdmUnitOfMeasureEntity.class.getDeclaredConstructor(Map.class).newInstance(map);
                ResultObject resultObject = edmUnitOfMeasureService.buildView(key, mainObject, null);
                EDMUnitOfMeasureBo edmUnitOfMeasureBo = (EDMUnitOfMeasureBo) resultObject.getBaseBo();
                if (resultObject.isSuccess()) {
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(edmUnitOfMeasureBo.getKey(), edmUnitOfMeasureBo.toMap());
                    result.add(viewResultItem);
                } else {
                    if (resultObject.getFailData() != null) {
                        FailData failData = resultObject.getFailData();
                        ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap());
                        result.add(viewResultItem);
                    }
                }
            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMCountryController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMCountryController Exception:", e);
            }
        });
        return result;
    }

}

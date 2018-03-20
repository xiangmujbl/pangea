package com.jnj.pangea.edm.currency.controller;

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
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.currency.bo.EDMCurrencyBo;
import com.jnj.pangea.edm.currency.service.EDMCurrencyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMCurrencyController extends BaseController implements IEventProcessor {

    private ICommonService edmCurrencyyService = EDMCurrencyServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(mainRaw -> {
            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();
            Map map = mainValue.toMap();
            LogUtil.getCoreLog().info("map:{}",map);
            try {
//                EMSFMdmCurrenciesEntity mainObject =  EMSFMdmCurrenciesEntity.class.getDeclaredConstructor(Map.class).newInstance(map);
                EMSFMdmCurrenciesEntity mainObject = BeanUtil.mapToBean(map, EMSFMdmCurrenciesEntity.class);
                ResultObject resultObject = edmCurrencyyService.buildView(key, mainObject, null);
                EDMCurrencyBo edmCountryBo = (EDMCurrencyBo) resultObject.getBaseBo();
                if (resultObject.isSuccess()) {
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(edmCountryBo.getKey(), edmCountryBo.toMap());
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

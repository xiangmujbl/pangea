package com.jnj.pangea.edm.material.auom.controler;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.entity.projectone.MarmEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.auom.service.EDMMaterialAuomServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMMaterialAuomController extends BaseController implements IEventProcessor {

    private ICommonService materialAuomService = EDMMaterialAuomServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new ArrayList<>();


        list.forEach(mainRaw -> {

            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();

            Map map = mainValue.toMap();

            try {
                MarmEntity mainObject = BeanUtil.mapToBean(map, MarmEntity.class);

                ResultObject resultObject = materialAuomService.buildView(key, mainObject, null);

                if (resultObject.isSuccess()) {
                    BaseBo baseBo = (BaseBo) resultObject.getBaseBo();
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                    result.add(viewResultItem);
                } else {
                    if (resultObject.getFailData() != null) {
                        FailData failData = resultObject.getFailData();
//                        LogUtil.getCoreLog().info(">>>>failData:{} ,>>>>key:{}",failData.getKey(),key);
                        ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(CommonRegionPath.FAIL_DATA, failData.getKey(), failData.toMap());
                        result.add(viewResultItem);
                    }
                }

            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMMaterialAuomController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMMaterialAuomController Exception:", e);
            }

        });
        return result;
    }

}

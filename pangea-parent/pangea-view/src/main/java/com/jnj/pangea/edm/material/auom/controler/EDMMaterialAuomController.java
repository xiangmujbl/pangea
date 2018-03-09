package com.jnj.pangea.edm.material.auom.controler;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.utils.PdxBeanUtils;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.BaseController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.projectone.MarmEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.auom.bo.EDMMaterialAuomBo;
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
                //MarmEntry mainObject = new MarmEntry();
                //PdxBeanUtils.convert2Bean(map, mainObject);
                MarmEntry mainObject = (MarmEntry) BeanUtil.mapToObject(map, MarmEntry.class);

                LogUtil.getCoreLog().info(">>>>>>>>>>>start>>>>>>>>>mainObject:{}", mainObject);

                ResultObject resultObject = materialAuomService.buildView(key, mainObject, null);

                EDMMaterialAuomBo materialAuomBo = (EDMMaterialAuomBo) resultObject.getBaseBo();

                LogUtil.getCoreLog().info(">>>>>>>>>>>result:{}", resultObject.isSuccess());

                if (resultObject.isSuccess()) {
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(materialAuomBo.getKey(), materialAuomBo.toMap());
                    result.add(viewResultItem);
                }

            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMMaterialAuomController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMMaterialAuomController Exception:", e);
            }

        });


        return result;
    }

}

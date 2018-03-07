package com.jnj.pangea.edm.material.type.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.BaseController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ems.EmsFMdmMaterialTypesEntry;
import com.jnj.pangea.common.entry.projectone.MarmEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.auom.bo.EDMMaterialAuomBo;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;
import com.jnj.pangea.edm.material.type.service.EDMMaterialTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMMaterialTypeController  extends BaseController implements IEventProcessor {
    private ICommonService materialTypeService = EDMMaterialTypeServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(mainRaw -> {

            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();

            Map map = mainValue.toMap();

            try {
                EmsFMdmMaterialTypesEntry mainObject = (EmsFMdmMaterialTypesEntry) BeanUtil.mapToObject(map, EmsFMdmMaterialTypesEntry.class);

                LogUtil.getCoreLog().info(">>>>>>>>>>>start>>>>>>>>>mainObject:{}", mainObject);

                ResultObject resultObject = materialTypeService.buildView(key, mainObject, null);



                LogUtil.getCoreLog().info(">>>>>>>>>>>result:{}", resultObject.isSuccess());

                if (resultObject.isSuccess()) {
                    BaseBo baseBo = (BaseBo) resultObject.getBaseBo();
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                    result.add(viewResultItem);
                }

            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMMaterialTypeController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMMaterialTypeController Exception:", e);
            }

        });
        //recycling object
        materialTypeService = null;

        return result;


    }
}

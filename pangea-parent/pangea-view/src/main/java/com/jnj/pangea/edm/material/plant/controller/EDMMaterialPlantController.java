package com.jnj.pangea.edm.material.plant.controller;

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
import com.jnj.pangea.common.entry.projectone.MarcEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.bo.EDMMaterialPlantBo;
import com.jnj.pangea.edm.material.plant.service.EDMMaterialPlantServiceImpl;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMMaterialPlantController extends BaseController implements IEventProcessor {
    private ICommonService materialPlantService = EDMMaterialPlantServiceImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {
        List<ViewResultItem> result = new ArrayList<>();
        list.forEach(mainRaw -> {
            RawDataValue mainValue = mainRaw.getValue();
            String key = mainRaw.getKey();

            Map map = mainValue.toMap();

            try {
                MarcEntry mainObject = (MarcEntry) BeanUtil.mapToObject(map, MarcEntry.class);

                ResultObject resultObject = materialPlantService.buildView(key, mainObject, null);

                if (resultObject.isSuccess()) {
                    BaseBo baseBo = (BaseBo) resultObject.getBaseBo();
                    ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
                    result.add(viewResultItem);
                }

            } catch (Exception e) {
                LogUtil.getCoreLog().info("EDMMaterialPlantController Exception occured. key = {}.", key);
                LogUtil.getCoreLog().info("EDMMaterialPlantController Exception:", e);
            }

        });

        return result;
    }
}

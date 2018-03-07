package com.jnj.pangea.common;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CommonController implements IEventProcessor {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw.getValue().toMap());
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();

                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                commonDao.saveFailData(resultObject.getFailData());
            }
        });
        return result;
    }

    public abstract ResultObject process(Map<String, Object> rawMap);
}

package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseController;

import java.util.LinkedList;
import java.util.List;

public class CopyController extends BaseController {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> result = new LinkedList<>();
        list.forEach(raw -> result.add(ViewResultBuilder.newResultItem(raw.getKey(), raw.getValue().toMap())));
        return result;
    }
}

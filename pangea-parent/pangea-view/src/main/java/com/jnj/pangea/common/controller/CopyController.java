package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;

import java.util.LinkedList;
import java.util.List;

public class CopyController extends BaseController {

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> resultItems = new LinkedList<>();
        events.forEach(raw -> resultItems.add(ViewResultBuilder.newResultItem(raw.getKey(), raw.getValue().toMap())));
        return resultItems;
    }
}

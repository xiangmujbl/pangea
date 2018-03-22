package com.jnj.pangea.plan.cns_lot_size_key.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.service.ICommonService;

public class CnsLotSizeKeyServiceImpl implements ICommonService {

    private static CnsLotSizeKeyServiceImpl instance;

    public static CnsLotSizeKeyServiceImpl getInstance() {
        if (instance == null) {
            instance = new CnsLotSizeKeyServiceImpl();
        }
        return instance;
    }
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        return null;
    }
}

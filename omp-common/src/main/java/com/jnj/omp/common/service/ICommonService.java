package com.jnj.omp.common.service;

import com.jnj.omp.common.dao.ICommonDao;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.ResultObject;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public interface ICommonService<M, V> {

    ICommonDao commonDao = CommonDaoImpl.getInstance();

    /**
     * @param key
     * @param m
     * @param v
     * @return
     */
    public ResultObject buildView(String key, M m, V v);
}

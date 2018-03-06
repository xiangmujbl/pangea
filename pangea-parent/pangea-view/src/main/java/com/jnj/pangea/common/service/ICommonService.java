package com.jnj.pangea.common.service;

import com.jnj.pangea.common.ResultObject;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public interface ICommonService<M, V> {


    /**
     * @param key
     * @param m
     * @param v
     * @return
     */
    public ResultObject buildView(String key, M m, V v);
}

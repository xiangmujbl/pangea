package com.jnj.pangea.common.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import java.util.List;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public interface ICommonListService<M, V> {

    ICommonDao commonDao = CommonDaoImpl.getInstance();

    /**
     * @param key
     * @param m
     * @param v
     * @return
     */
    public List<ResultObject> buildView(String key, M m, V v);
}

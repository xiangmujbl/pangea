package com.jnj.pangea.common.Dao;

import java.util.List;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public interface ICommonDao {

    /**
     * queryString content is key
     * @param region
     * @param queryString
     * @return
     */
    public Object fetchByKey(String region, String queryString, Class<?> resultType);

    /**
     * queryString content is not key
     * @param region
     * @param queryString
     * @return
     */
    public List<Object> queryForList(String region, String queryString, Class<?> resultType);

}

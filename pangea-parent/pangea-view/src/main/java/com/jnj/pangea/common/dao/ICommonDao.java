package com.jnj.pangea.common.dao;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.List;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public interface ICommonDao {

    /**
     * queryString content is key
     *
     * @param region
     * @param queryString
     * @return
     */
    public <T> T fetchByKey(String region, String queryString, Class<? extends CommonEntity> resultType);

    /**
     * queryString content is not key
     *
     * @param region
     * @param queryString
     * @return
     */
    public <T> List<T> queryForList(String region, String queryString, Class<? extends CommonEntity> resultType);

    public <T> T queryForObject(String region, String queryString, Class<? extends CommonEntity> resultType);

    public <T> List<T> queryForList(String region, String queryString, Class<? extends CommonEntity> clazz,String orderBys);
}

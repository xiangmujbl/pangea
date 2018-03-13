package com.jnj.pangea.common.Dao.impl;

import com.google.common.collect.Lists;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class CommonDaoImpl implements ICommonDao {

    private static ICommonDao instance;

    public static ICommonDao getInstance() {
        if (instance == null) {
            instance = new CommonDaoImpl();
        }
        return instance;
    }

    @Override
    public <T> T fetchByKey(String region, String queryString, Class<T> clazz) {

        T entry = null;
        RawDataValue rawDataValue = CurationRawDataHelper.getInstance().fetchByKey(region, queryString);
        Map map = rawDataValue.toMap();
        if (map != null) {
            try {
                entry = clazz.newInstance();
                BeanUtil.mapToBean(map, entry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    @Override
    public <T> List<T> queryForList(String region, String queryString, Class<T> clazz) {
        LogUtil.getCoreLog().info("<<<<<<<queryForList>>>>>>>>>>region:{},queryString:{},Class:{}",region,queryString,clazz);
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, -1);
        return mapsToObjects(sourceSystemList, clazz);
    }

    @Override
    public <T> T queryForObject(String region, String queryString, Class<T> clazz) {

        T entry = null;

        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(region, queryString);
        if (null != result && null != result.getValue()) {
            try {
                entry = clazz.newInstance();
                BeanUtil.mapToBean(result.getValue(), entry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    @Override
    public void saveFailData(FailData failData) {
        // TODO save failData


    }

    private static <T> List<T> mapsToObjects(List<Map.Entry<String, String>> maps, Class<T> clazz) {

        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map map = null;
            T bean = null;
            for (Map.Entry<String, String> entry : maps) {
                map = JsonUtils.jsonToObject(entry.getValue(), Map.class);
                try {
                    bean = clazz.newInstance();
                    BeanUtil.mapToBean(map, bean);
                    list.add(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}

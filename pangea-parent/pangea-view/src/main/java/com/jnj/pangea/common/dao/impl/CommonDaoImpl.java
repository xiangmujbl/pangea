package com.jnj.pangea.common.dao.impl;

import com.google.common.collect.Lists;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.CommonEntity;
import com.jnj.pangea.common.dao.ICommonDao;
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
                entry = (T) BeanUtil.mapToBean(map, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    @Override
    public <T> List<T> queryForList(String region, String queryString, Class<T> clazz) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, -1);
        return mapsToObjects(sourceSystemList, clazz);
    }

    @Override
    public <T> T queryForObject(String region, String queryString, Class<T> resultType) {
        T entry = null;

        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(region, queryString);
        if (null != result && null != result.getValue()) {
            try {
                entry = (T) BeanUtil.mapToBean(result.getValue(), resultType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    @Override
    public <T> T queryForEntity(String region, String queryString, Class<? extends CommonEntity> resultType) {
        T entry = null;

        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.queryForMap(region, queryString);
        if (null != result && null != result.getValue()) {
            try {
                entry = (T) resultType.getDeclaredConstructor(Map.class).newInstance(result.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    private static <T> List<T> mapsToObjects(List<Map.Entry<String, String>> maps, Class<T> clazz) {

        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map map = null;
            T bean = null;
            for (Map.Entry<String, String> entry : maps) {
                map = JsonUtils.jsonToObject(entry.getValue(), Map.class);
                try {
                    bean = (T) BeanUtil.mapToBean(map, clazz);
                    list.add(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}

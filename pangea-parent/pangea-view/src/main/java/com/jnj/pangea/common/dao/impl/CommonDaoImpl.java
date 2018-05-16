package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.dao.ICommonDao;
import com.jnj.pangea.common.entity.CommonEntity;
import com.jnj.pangea.util.BeanUtil;
import com.google.common.collect.Lists;

import java.util.LinkedList;
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
    public <T> T fetchByKey(String region, String queryString, Class<? extends CommonEntity> clazz) {

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
    public <T> List<T> queryForList(String region, String queryString, Class<? extends CommonEntity> clazz) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, -1);
        return mapsToObjects(sourceSystemList, clazz);
    }
    @Override
    public <T> List<T> queryForList(String region, String queryString, Class<? extends CommonEntity> clazz,String orderBys) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, null,-1,orderBys);
        return mapsToObjects(sourceSystemList, clazz);
    }
    @Override
    public <T> T queryForObject(String region, String queryString, Class<? extends CommonEntity> resultType) {
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
    public <T> List<T> queryForList(String region, String queryString, String orderBys,  Class<? extends CommonEntity>  clazz) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, null, Integer.MAX_VALUE, orderBys);
        return mapsToObjects(sourceSystemList, clazz);
    }

    @Override
    public long queryForCount(String region, String queryString) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, -1);
        return sourceSystemList.size();
    }

    @Override
    public <T> T maxRec(String region, String queryString, String maxField, Class<? extends CommonEntity> clazz) {
        T entry = null;

        Map.Entry<String, Map<String, Object>> result = AdfViewHelper.maxRec(region, queryString, maxField);
        if (null != result && null != result.getValue()) {
            try {
                entry = (T) clazz.getDeclaredConstructor(Map.class).newInstance(result.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

    private static <T> List<T> mapsToObjects(List<Map.Entry<String, String>> maps, Class<? extends CommonEntity> clazz) {

        List<T> list = new LinkedList<>();
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

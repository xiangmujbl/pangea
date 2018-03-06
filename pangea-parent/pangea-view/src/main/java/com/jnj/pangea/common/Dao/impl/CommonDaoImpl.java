package com.jnj.pangea.common.Dao.impl;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.util.BeanUtil;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public Object fetchByKey(String region, String queryString, Class<?> resultType) {

        Object entry = null;

        RawDataValue sourceSystem = CurationRawDataHelper.getInstance().fetchByKey(region, queryString);

        Map map = sourceSystem.toMap();

        if (map != null) {
            entry = BeanUtil.mapToObject(map, resultType);
        }

        return entry;
    }

    @Override
    public List<Object> queryForList(String region, String queryString, Class<?> resultType) {
        List<Map.Entry<String, String>> sourceSystemList = AdfViewHelper.queryForList(region, queryString, -1);

        List<Object> list = new ArrayList<>();
        sourceSystemList.forEach(entry->{
            String value = entry.getValue();
            Map valueMap = JsonUtils.jsonToObject(value, Map.class);
            Object obj = BeanUtil.mapToObject(valueMap, resultType);
            list.add(obj);
        });

        return list;
    }

}

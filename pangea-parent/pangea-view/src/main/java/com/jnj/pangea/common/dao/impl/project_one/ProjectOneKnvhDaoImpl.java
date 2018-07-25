package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.common.DateUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class ProjectOneKnvhDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_KNVH = "/project_one/knvh";

    public static final String HKUNNR = "hkunnr";
    public static final String KUNNR = "kunnr";
    public static final String VKORG = "vkorg";
    public static final String DATBI = "datbi";
    public static final String HITYP = "hityp";

    private static ProjectOneKnvhDaoImpl instance;

    public static ProjectOneKnvhDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneKnvhDaoImpl();
        }
        return instance;
    }

    public KnvhEntity getEntityWithConditions(String kunnr, String vkorg, String datbi) {
        if (null != kunnr && !"".equals(kunnr) && null != vkorg && !"".equals(vkorg) && null != datbi && !"".equals(datbi)) {
            String queryString = QueryHelper.buildCriteria(KUNNR).is(kunnr)
                    .and(VKORG).is(vkorg)
                    .and(DATBI).greaterThanEqual(datbi).toQueryString();
            return queryForObject(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public KnvhEntity getEntityWithKunnrAndVkorg(String kunnr, String vkorg) {
        if (null != kunnr && !"".equals(kunnr) && null != vkorg && !"".equals(vkorg)) {
            String queryString = QueryHelper.buildCriteria(KUNNR).is(kunnr)
                    .and(VKORG).is(vkorg).toQueryString();
            return queryForObject(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public List<KnvhEntity> getEntityListByKunnrAndDatbi(String kunnr) {
        if (null != kunnr && !"".equals(kunnr)) {
            String currentDate = DateUtil.format(new Date(), DateUtil.F_yyyyMMdd);
            String queryString = QueryHelper.buildCriteria(KUNNR).is(kunnr)
                    .and(DATBI).greaterThan(currentDate).toQueryString();
            return queryForList(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public List<KnvhEntity> getEntityListByHKunnrAndDatbi(String hkunnr) {
        if (null != hkunnr && !"".equals(hkunnr)) {
            String currentDate = DateUtil.format(new Date(), DateUtil.F_yyyyMMdd);
            String queryString = QueryHelper.buildCriteria(HKUNNR).is(hkunnr)
                    .and(DATBI).greaterThan(currentDate).toQueryString();
            return queryForList(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public KnvhEntity getEntityWithKunnrAndVkorgAndDatbi(String kunnr, String vkorg, String datbi) {
        if (StringUtils.isNotEmpty(kunnr) && StringUtils.isNotEmpty(vkorg) && StringUtils.isNotEmpty(datbi)) {
            String queryString = QueryHelper.buildCriteria(KUNNR).is(kunnr)
                    .and(VKORG).is(vkorg)
                    .and(DATBI).greaterThan(datbi).toQueryString();
            return queryForObject(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public KnvhEntity getEntityWithFourConditions(String kunnr, String vkorg, String hityp, String datbi) {
        if (StringUtils.isNotEmpty(kunnr) && StringUtils.isNotEmpty(vkorg) && StringUtils.isNotEmpty(vkorg) && StringUtils.isNotEmpty(datbi)) {
            String queryString = QueryHelper.buildCriteria(KUNNR).is(kunnr)
                    .and(VKORG).is(vkorg)
                    .and(HITYP).is(hityp)
                    .and(DATBI).greaterThan(datbi).toQueryString();
            return queryForObject(PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }
}

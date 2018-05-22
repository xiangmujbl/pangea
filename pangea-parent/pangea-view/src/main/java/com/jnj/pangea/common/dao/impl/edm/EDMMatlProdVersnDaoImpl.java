package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMatlProdVersnEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMatlProdVersnDaoImpl extends CommonDaoImpl {

    private static EDMMatlProdVersnDaoImpl instance;

    public static EDMMatlProdVersnDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlProdVersnDaoImpl();
        }
        return instance;
    }

    public List<EDMMatlProdVersnEntity> getEntityWithConditions(String srcSysCd, String plantCd, String matlNum, String altBomNum) {
        if (StringUtils.isNotBlank(srcSysCd)&&StringUtils.isNotBlank(plantCd)&&StringUtils.isNotBlank(matlNum)&&StringUtils.isNotBlank(altBomNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MATL_PROD_VERSN.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MATL_PROD_VERSN.PLNTCD).is(plantCd)
                    .and(IConstant.MATL_PROD_VERSN.MATLNUM).is(matlNum)
                    .and(IConstant.MATL_PROD_VERSN.ALTBOMNUM).is(altBomNum)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, queryString, EDMMatlProdVersnEntity.class);

        }
        return null;
    }
}

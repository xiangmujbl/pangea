package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMBomItemDaoImpl extends CommonDaoImpl {

    private static EDMBomItemDaoImpl instance;

    public static EDMBomItemDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomItemDaoImpl();
        }
        return instance;
    }

    public EDMBomItemEntity getEntityWithConditions(String bomNum, String srcSysCd ) {
        if (StringUtils.isNotBlank(bomNum) && StringUtils.isNotBlank(srcSysCd)) {
            String queryString = QueryHelper.buildCriteria(IConstant.BOM_ITEM.BOMNUM).is(bomNum)
                    .and(IConstant.BOM_ITEM.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.BOM_ITEM.BOMCATCD).is(IConstant.VALUE.M)
                    .toQueryString();
            return queryForObject(IConstant.REGION.BOM_ITEM, queryString, EDMBomItemEntity.class);
        }
        return null;
    }
}

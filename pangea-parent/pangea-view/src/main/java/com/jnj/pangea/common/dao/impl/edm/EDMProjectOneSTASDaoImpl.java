package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProjectOneMAPLEntity;
import com.jnj.pangea.common.entity.edm.EDMProjectOneSTASEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMProjectOneSTASDaoImpl extends CommonDaoImpl {
    private static EDMProjectOneSTASDaoImpl instance;

    public static EDMProjectOneSTASDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMProjectOneSTASDaoImpl();
        }
        return instance;
    }

  public long getEDMProjectOneSTASEntityCount(String STLTY,String STLNR,String STLKN){
      ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.BOM_ITEM.FIELD_STAS_STLTY_VALUE);
      if(StringUtils.isNotBlank(STLTY)){
          aDFCriteria.is(STLTY);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(STLNR)){
          aDFCriteria.and(IConstant.BOM_ITEM.FIELD_STAS_STLNR_VALUE).is(STLNR);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(STLKN)){
          aDFCriteria.and(IConstant.BOM_ITEM.FIELD_STAS_STLKN_VALUE).is(STLKN);
      }else{
          aDFCriteria.isNull();
      }
      return queryForCount(IConstant.REGION.PROJECT_ONE_STAS,aDFCriteria.toQueryString());
  }


    public EDMProjectOneSTASEntity getEDMProjectOneSTASEntity(String STLTY,String STLNR,String STLKN){
        ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.BOM_ITEM.FIELD_STAS_STLTY_VALUE);
        if(StringUtils.isNotBlank(STLTY)){
            aDFCriteria.is(STLTY);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(STLNR)){
            aDFCriteria.and(IConstant.BOM_ITEM.FIELD_STAS_STLNR_VALUE).is(STLNR);
        }else{
            aDFCriteria.isNull();
        }
        if(StringUtils.isNotBlank(STLKN)){
            aDFCriteria.and(IConstant.BOM_ITEM.FIELD_STAS_STLKN_VALUE).is(STLKN);
        }else{
            aDFCriteria.isNull();
        }

        LogUtil.getCoreLog().info("==================================================queryString  "+aDFCriteria.toQueryString());
        return maxRec(IConstant.REGION.PROJECT_ONE_STAS,aDFCriteria.toQueryString(),IConstant.BOM_ITEM.FIELD_STAS_STASZ_VALUE,EDMProjectOneSTASEntity.class);
    }

}

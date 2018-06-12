package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMProjectOneMAPLEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMProjectOneMaplDaoImpl extends CommonDaoImpl {
    private static EDMProjectOneMaplDaoImpl instance;

    public static EDMProjectOneMaplDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMProjectOneMaplDaoImpl();
        }
        return instance;
    }

  public List<EDMProjectOneMAPLEntity> getProjectOneMaplClone(String PLNNR,String PLNTY,String PLNAL,String ZKRIZ){
      ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.MATL_MFG_RTNG.FIELD_NAME_PLNNR);
      if(StringUtils.isNotBlank(PLNNR)){
          aDFCriteria.is(PLNNR);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(PLNTY)){
          aDFCriteria.and(IConstant.MATL_MFG_RTNG.FIELD_NAME_PLNTY).is(PLNTY);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(PLNAL)){
          aDFCriteria.and(IConstant.MATL_MFG_RTNG.FIELD_NAME_PLNAL).is(PLNAL);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(ZKRIZ)){
          aDFCriteria.and(IConstant.MATL_MFG_RTNG.FIELD_NAME_ZKRIZ).is(ZKRIZ);
      }else{
          aDFCriteria.isNull();
      }
      LogUtil.getCoreLog().info("queryString  "+aDFCriteria.toQueryString());
      return queryForList(IConstant.REGION.PROJECT_ONE_MAPL_CLONE,aDFCriteria.toQueryString(),EDMProjectOneMAPLEntity.class);
  }
}

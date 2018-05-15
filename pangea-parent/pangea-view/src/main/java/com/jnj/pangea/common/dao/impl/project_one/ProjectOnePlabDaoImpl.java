package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProjectOneMAPLEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlabEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlabDaoImpl extends CommonDaoImpl {
    private static ProjectOnePlabDaoImpl instance;

    public static ProjectOnePlabDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlabDaoImpl();
        }
        return instance;
    }

  public List<ProjectOnePlabEntity> getProjectOneMaplClone(String PLNNR, String PLNTY, String PLNAL, String PLNKN,String KNNRN){
      ADFCriteria aDFCriteria=QueryHelper.buildCriteria(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNNR);
      if(StringUtils.isNotBlank(PLNNR)){
          aDFCriteria.is(PLNNR);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(PLNTY)){
          aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNTY).is(PLNTY);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(PLNAL)){
          aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNAL).is(PLNAL);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(PLNKN)){
          aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_PLNKN).is(PLNKN);
      }else{
          aDFCriteria.isNull();
      }
      if(StringUtils.isNotBlank(KNNRN)){
          aDFCriteria.and(IConstant.MFG_RTNG_RLTNSHP.FIELD_NAME_KNNRN).is(KNNRN);
      }else{
          aDFCriteria.isNull();
      }
      return queryForList(IConstant.REGION.PROJECT_ONE_PLAB_CLONE,aDFCriteria.toQueryString(),IConstant.MFG_RTNG_RLTNSHP.SOFT_ZAEHL_VALUE,ProjectOnePlabEntity.class);
  }
}

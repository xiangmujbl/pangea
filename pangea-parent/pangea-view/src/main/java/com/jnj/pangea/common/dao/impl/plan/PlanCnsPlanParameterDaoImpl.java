package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PlanCnsPlanParameterDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanParameterDaoImpl instance;

    public static PlanCnsPlanParameterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanParameterDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsPlanParameterEntity> getEntriessWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER_VALUE).is(localPlant)
                .toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntitiesWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String inclExcl) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.INCL_EXCL).is(inclExcl).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntitiesWithSourceSystem(String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithConditions(String sourceSystem, String dataObject, String attribute, String parameter, String inclExcl) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.PARAMETER).is(parameter)
                .and(IConstant.CNS_PLAN_PARAMETER.INCL_EXCL).is(inclExcl).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithConditions(String sourceSystem, String dataObject) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithAttribute(String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(attribute).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithAttribute(String sourceSystem,String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }


    public List<PlanCnsPlanParameterEntity> getEntityWithAttributeList(String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(attribute).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithSourceSystemAndDataObject(String sourceSystem, String dataObject) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(dataObject)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(sourceSystem)
                    .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlanParameterEntity> getEntityListWithSourceSystemAndDataObject(String sourceSystem, String dataObject) {
        List<PlanCnsPlanParameterEntity> planParameterEntityList = new ArrayList<>();
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(dataObject)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
            planParameterEntityList = queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return planParameterEntityList;
    }

    public PlanCnsPlanParameterEntity getEntitiesWithConditions1(String sourceSystem, String dataObject) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }
    public PlanCnsPlanParameterEntity getEntitiesWithConditions2(String sourceSystem, String dataObject,String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }
    public PlanCnsPlanParameterEntity getEntitiesWithConditions3(String sourceSystem, String dataObject,String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public List<PlanCnsPlanParameterEntity> getEntitiesWithConditions(String sourceSystem, String dataObject, String attribute) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }
    public List<PlanCnsPlanParameterEntity> getEntityWithAttributeListForLFU(String sourceSystem) {
        if (StringUtils.isBlank(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.LFU.DATA_OBJECT).is(IConstant.VALUE.SEND_TO_OMP)
                .toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(String sourceSystem, String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotBlank(sourceSystem) && StringUtils.isNotBlank(localMaterialNumber) && StringUtils.isNotBlank(localPlant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.CNS_PLAN_PARAMETER.LOCAL_MATERIALNUMBER_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_PLAN_PARAMETER.LOCAL_PLANT).is(localPlant)
                    .toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return null;

    }
    public PlanCnsPlanParameterEntity getEntityWithSourceSystem(String sourceSystem) {
        if (StringUtils.isNotBlank(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(IConstant.VALUE.SEND_TO_OMP)
                    .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(sourceSystem)
                    .toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return null;
    }
    public PlanCnsPlanParameterEntity getEntityWithAttributeListForLFUOne(String sourceSystem) {
        if(StringUtils.isBlank(sourceSystem)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.LFU.DATA_OBJECT).is(IConstant.VALUE.SEND_TO_OMP)
                .toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public PlanCnsPlanParameterEntity getEntityWithAttributeAndDataObject(String attribute, String dataObject) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is(attribute)
                .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
    }

    public  List<PlanCnsPlanParameterEntity> getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(String srcSysCd,String dataObject){
        List<PlanCnsPlanParameterEntity> list = new ArrayList<PlanCnsPlanParameterEntity>();
        if(StringUtils.isNotBlank(srcSysCd)){
            String queryString  = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(srcSysCd)
                    .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is(dataObject)
                    .toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return list;
    }

    public PlanCnsPlanParameterEntity getPlanCnsPlanParameterEntity(String sourceSysCd){
        if(StringUtils.isNotBlank(sourceSysCd)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_PARAMETER.SOURCE_SYSTEM).is(sourceSysCd)
                    .and(IConstant.CNS_PLAN_PARAMETER.DATA_OBJECT).is("SEND_TO_OMP")
                    .and(IConstant.CNS_PLAN_PARAMETER.ATTRIBUTE).is("PPTRANSACTIONAL")
                    .toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_PARAMETER, queryString, PlanCnsPlanParameterEntity.class);
        }
        return null;
    }
}

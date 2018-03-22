package com.jnj.pangea.plan.cns_plng_strat_grp.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ProjectOneT461XDaoImpl;
import com.jnj.pangea.common.entity.projectone.T461PEntity;
import com.jnj.pangea.common.entity.projectone.T461XEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_plng_strat_grp.bo.CnsPlngStratGrpBo;

public class CnsPlngStratGrpServiceImpl implements ICommonService {

    private static CnsPlngStratGrpServiceImpl instance;

    public static CnsPlngStratGrpServiceImpl getInstance() {
        if (instance == null) {
            instance = new CnsPlngStratGrpServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    ProjectOneT461XDaoImpl t461XDao = ProjectOneT461XDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        T461PEntity t461PEntity = (T461PEntity) o;

        CnsPlngStratGrpBo cnsPlngStratGrpBo = new CnsPlngStratGrpBo();

        String sourceSystem = null;
        if(null != sourceSystemV1Dao.getSourceSystemWithProjectOne()) {
            sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
            cnsPlngStratGrpBo.setSourceSystem(sourceSystem);
        }

        String strgr = t461PEntity.getStrgr();
        cnsPlngStratGrpBo.setLocalPlanStratGrp(strgr);

        T461XEntity t461XEntity = t461XDao.getEntityWithStrgrAndSpras(strgr);
        if (null != t461XEntity){
            cnsPlngStratGrpBo.setLocalPlanStratGrpDesc(t461XEntity.getText40());
        }

        resultObject.setBaseBo(cnsPlngStratGrpBo);
        return resultObject;
    }
}

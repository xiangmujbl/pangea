package com.jnj.pangea.edm.mfg_rtng_itm.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlpoEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlpoEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlasDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm.bo.EDMMfgRtngItmBo;

public class EDMMfgRtngItmServiceImpl implements ICommonService {

    private static EDMMfgRtngItmServiceImpl instance;

    public static EDMMfgRtngItmServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmServiceImpl();
        }
        return instance;
    }

    private ProjectOnePlasDaoImpl plasDao = ProjectOnePlasDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOnePlpoEntity plpoEntity = (ProjectOnePlpoEntity) o;

        EDMMfgRtngItmBo mfgRtngItmBo = new EDMMfgRtngItmBo();
        // TODO add logic

        resultObject.setBaseBo(mfgRtngItmBo);
        return resultObject;
    }
}

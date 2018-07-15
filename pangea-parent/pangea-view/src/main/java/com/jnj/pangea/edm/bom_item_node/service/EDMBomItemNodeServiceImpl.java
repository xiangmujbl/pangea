package com.jnj.pangea.edm.bom_item_node.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.project_one.StasEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_item_node.bo.EDMBomItemNodeBo;

public class EDMBomItemNodeServiceImpl implements ICommonService {

    private static EDMBomItemNodeServiceImpl instance;

    public static EDMBomItemNodeServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomItemNodeServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        StasEntity stasEntity = (StasEntity) o;

        EDMBomItemNodeBo bomItemNodeBo = new EDMBomItemNodeBo();
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
            bomItemNodeBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        bomItemNodeBo.setBomCatCd(stasEntity.getStlty());
        bomItemNodeBo.setBomNum(stasEntity.getStlnr());
        bomItemNodeBo.setAltBomNum(stasEntity.getStlal());
        bomItemNodeBo.setBomItmNdeNum(stasEntity.getStlkn());
        bomItemNodeBo.setBomItmNdeCntrNbr(stasEntity.getStasz());
        bomItemNodeBo.setBomItmNdeVldFromDt(stasEntity.getDatuv());
        bomItemNodeBo.setChgNum(stasEntity.getAennr());
        bomItemNodeBo.setCrtDttm(stasEntity.getAndat());
        bomItemNodeBo.setDelInd(stasEntity.getLkenz());
        bomItemNodeBo.setChgDttm(stasEntity.getAedat());

        resultObject.setBaseBo(bomItemNodeBo);
        return resultObject;
    }
}

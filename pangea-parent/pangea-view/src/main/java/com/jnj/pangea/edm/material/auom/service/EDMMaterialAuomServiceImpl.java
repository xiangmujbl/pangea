package com.jnj.pangea.edm.material.auom.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectone.MarmEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.auom.bo.EDMMaterialAuomBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by XZhan290 on 2018/3/2.
 */
public class EDMMaterialAuomServiceImpl implements ICommonService {

    private static EDMMaterialAuomServiceImpl instance;

    public static EDMMaterialAuomServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomServiceImpl();
        }
        return instance;
    }
    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        MarmEntity marmEntity = (MarmEntity) o;

        EDMMaterialAuomBo materialAuomBo = new EDMMaterialAuomBo();

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
        materialAuomBo.setSourceSystem(sourceSystem);

        //localMaterialNumber
        String matnr = marmEntity.getMatnr();
        materialAuomBo.setLocalMaterialNumber(StringUtils.trim(matnr));

        //localAuom
        String localAuom = marmEntity.getMeinh();
        materialAuomBo.setLocalAuom(StringUtils.trim(localAuom));

        //localNumerator
        String localNumerator = marmEntity.getUmrez();
        materialAuomBo.setLocalNumerator(StringUtils.trim(localNumerator));

        //localDenominator
        String localDenominator = marmEntity.getUmren();
        materialAuomBo.setLocalDenominator(StringUtils.trim(localDenominator));

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(sourceSystem,matnr);
        if (null != materialGlobalV1Entity){
            String materialNumber = materialGlobalV1Entity.getMaterialNumber();
            materialAuomBo.setMaterialNumber(materialNumber);

        }

        resultObject.setBaseBo(materialAuomBo);

        return resultObject;
    }

}

package com.jnj.pangea.edm.material.auom.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
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

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMaterialAuomServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        MarmEntity mainData = (MarmEntity) o;

        EDMMaterialAuomBo materialAuomBo = new EDMMaterialAuomBo();
        resultObject.setBaseBo(materialAuomBo);

        processSystem(mainData, materialAuomBo);
        processSourceSystem(materialAuomBo);

        processMaterialNumber(materialAuomBo);

        return resultObject;
    }

    private void processMaterialNumber(EDMMaterialAuomBo materialAuomBo){
        String materialNumber= null;
        String matnr = materialAuomBo.getLocalMaterialNumber();
        if (StringUtils.isNotBlank(materialAuomBo.getSourceSystem()) && StringUtils.isNotBlank(matnr)) {
            String queryString = QueryHelper.buildCriteria(IConstant.SOURCESYSTEM).is(materialAuomBo.getSourceSystem())
                    .and(IConstant.LOCALMATERIALNUMBER).is(matnr).toQueryString();

            if (matnr.contains(">") || matnr.contains("<") || matnr.contains("=")) {
                queryString = IConstant.SOURCESYSTEM+":\""+ materialAuomBo.getSourceSystem() + "\" AND "+ IConstant.LOCALMATERIALNUMBER+":\"" + AdfLuceneHelper.keyword(matnr) + "\"";
            }
            List<EDMMaterialGlobalV1Entity> materialList = commonDao.queryForList(IConstant.REGION.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entity.class);
            for (EDMMaterialGlobalV1Entity entry : materialList) {
                materialNumber = entry.getMaterialNumber();
            }
        }
        materialAuomBo.setMaterialNumber(materialNumber);
    }

    private boolean processSourceSystem(EDMMaterialAuomBo materialAuomBo) {

        String queryString = QueryHelper.buildCriteria("localSourceSystem").is("project_one").toQueryString();
        List<EDMSourceSystemV1Entity> sourceList = commonDao.queryForList(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);

        String sourceSystem = null;
        for (EDMSourceSystemV1Entity entry : sourceList) {
            sourceSystem = entry.getSourceSystem();
        }
        materialAuomBo.setSourceSystem(sourceSystem);

        return true;
    }

    private boolean processSystem(MarmEntity mainData, EDMMaterialAuomBo materialAuomBo) {

        //localMaterialNumber
        String matnr = mainData.getMatnr();
        materialAuomBo.setLocalMaterialNumber(StringUtils.trim(matnr));

        //localAuom
        String localAuom = mainData.getMeinh();
        materialAuomBo.setLocalAuom(StringUtils.trim(localAuom));

        //localNumerator
        String localNumerator = mainData.getUmrez();
        materialAuomBo.setLocalNumerator(StringUtils.trim(localNumerator));

        //localDenominator
        String localDenominator = mainData.getUmren();
        materialAuomBo.setLocalDenominator(StringUtils.trim(localDenominator));

        return true;
    }

}

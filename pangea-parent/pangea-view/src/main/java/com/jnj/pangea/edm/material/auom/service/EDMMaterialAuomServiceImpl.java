package com.jnj.pangea.edm.material.auom.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.edm.EDMMaterialGlobalV1Entry;
import com.jnj.pangea.common.entry.edm.EDMSourceSystemV1Entry;
import com.jnj.pangea.common.entry.projectone.MarmEntry;
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

        MarmEntry mainData = (MarmEntry) o;

        EDMMaterialAuomBo materialAuomBo = new EDMMaterialAuomBo();
        resultObject.setBaseBo(materialAuomBo);

        processSystem(mainData, materialAuomBo);
        processSourceSystem(materialAuomBo);

        processMaterialNumber(materialAuomBo);

        return resultObject;
    }

    private void processMaterialNumber(EDMMaterialAuomBo materialAuomBo){
        String materialNumber= null;

        if (StringUtils.isNotBlank(materialAuomBo.getSourceSystem()) && StringUtils.isNotBlank(materialAuomBo.getLocalMaterialNumber())) {
            String queryString = QueryHelper.buildCriteria(CommonRegionPath.SOURCESYSTEM).is(materialAuomBo.getSourceSystem())
                    .and(CommonRegionPath.LOCALMATERIALNUMBER).is(materialAuomBo.getLocalMaterialNumber()).toQueryString();

            List<EDMMaterialGlobalV1Entry> materialList = commonDao.queryForList(CommonRegionPath.EDM_MATERIAL_GLOBAL_V1, queryString, EDMMaterialGlobalV1Entry.class);
            for (EDMMaterialGlobalV1Entry entry : materialList) {
                materialNumber = entry.getMaterialNumber();
            }
        }
        materialAuomBo.setMaterialNumber(materialNumber);
    }

    private boolean processSourceSystem(EDMMaterialAuomBo materialAuomBo) {

        String queryString = QueryHelper.buildCriteria("localSourceSystem").is("project_one").toQueryString();
        List<EDMSourceSystemV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entry.class);

        String sourceSystem = null;
        for (EDMSourceSystemV1Entry entry : sourceList) {
            sourceSystem = entry.getSourceSystem();
        }
        materialAuomBo.setSourceSystem(sourceSystem);

        return true;
    }

    private boolean processSystem(MarmEntry mainData, EDMMaterialAuomBo materialAuomBo) {

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

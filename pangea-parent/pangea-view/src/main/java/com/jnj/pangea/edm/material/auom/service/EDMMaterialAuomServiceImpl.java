package com.jnj.pangea.edm.material.auom.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;
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

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

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

        boolean isOk = processSourceSystem(key, materialAuomBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSourceSystem of flag:{}", key, isOk);
            resultObject.setFlag(false);
            return resultObject;
        }

        isOk = processSystem(mainData, materialAuomBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}", key, isOk);
            resultObject.setFlag(false);
            return resultObject;
        }

        isOk = processMaterialNumber(key, mainData, materialAuomBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processMaterialNumber of flag:{}", key, isOk);
            resultObject.setFlag(false);
            return resultObject;
        }

        //recycling object
        commonDao = null;

        return resultObject;
    }

    private final boolean processMaterialNumber(String key, MarmEntry mainData, EDMMaterialAuomBo materialAuomBo) {

        String matnr = mainData.getMatnr();
        String sourceSystem = materialAuomBo.getSourceSystem();
        String queryString = QueryHelper.buildCriteria("localMaterialNumber").is(matnr).and("sourceSystem").is(sourceSystem).toQueryString();

        List<Object> materialList = commonDao.queryForList(CommonRegionPath.REGION_MATERIAL_GLOBAL, queryString, EDMMaterialGlobalV1Entry.class);

        String materialNumber = null;
        for (Object entry : materialList) {
            EDMMaterialGlobalV1Entry edmMaterialGlobalV1Entry = (EDMMaterialGlobalV1Entry) entry;
            materialNumber = edmMaterialGlobalV1Entry.getMaterialNumber();
        }

        if (StringUtils.isEmpty(materialNumber)) {
            LogUtil.getCoreLog().info(">>>query {} is null, query condition.", CommonRegionPath.REGION_MATERIAL_GLOBAL);
            //@TODO write fail data to region or file, T1
            return false;
        }

        materialAuomBo.setMaterialNumber(materialNumber);

        return true;
    }

    private final boolean processSourceSystem(String key, EDMMaterialAuomBo materialAuomBo) {

        String queryString = QueryHelper.buildCriteria("localSourceSystem").is("project_one").toQueryString();

        List<Object> sourceList = commonDao.queryForList(CommonRegionPath.REGION_SOURCE_SYSTEM, queryString, EDMSourceSystemV1Entry.class);

        String sourceSystem = null;
        for (Object entry : sourceList) {
            EDMSourceSystemV1Entry sourceSystemV1Entry = (EDMSourceSystemV1Entry) entry;
            sourceSystem = sourceSystemV1Entry.getSourceSystem();
        }

        if (StringUtils.isEmpty(sourceSystem)) {
            LogUtil.getCoreLog().info(">>>query {} data is null for project_one, query condition.", CommonRegionPath.REGION_SOURCE_SYSTEM);
            //@TODO write fail data to region or file, T1
            return false;
        }

        materialAuomBo.setSourceSystem(sourceSystem);

        return true;
    }

    private final boolean processSystem(MarmEntry mainData, EDMMaterialAuomBo materialAuomBo) {

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

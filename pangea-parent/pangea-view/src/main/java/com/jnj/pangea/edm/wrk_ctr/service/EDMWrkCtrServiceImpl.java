package com.jnj.pangea.edm.wrk_ctr.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneCrtxDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.CrhdEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOneCrtxEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.wrk_ctr.bo.EDMWrkCtrBo;

import java.util.List;

public class EDMWrkCtrServiceImpl implements ICommonService {

    private static EDMWrkCtrServiceImpl instance;

    public static EDMWrkCtrServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMWrkCtrServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneCrtxDaoImpl crtxDao = ProjectOneCrtxDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        CrhdEntity crhdEntity = (CrhdEntity) o;

        EDMWrkCtrBo wrkCtrBo = new EDMWrkCtrBo();
        //T1
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (entityWithLocalSourceSystem != null) {
            wrkCtrBo.setSrcSysCd(entityWithLocalSourceSystem.getSourceSystem());
        }
        wrkCtrBo.setWrkCtrTypeCd(crhdEntity.getObjty());
        wrkCtrBo.setWrkCtrNum(crhdEntity.getObjid());
        wrkCtrBo.setVldFromDt(crhdEntity.getBegda());
        wrkCtrBo.setVldToDt(crhdEntity.getEndda());
        wrkCtrBo.setWrkCtrCd(crhdEntity.getArbpl());
        wrkCtrBo.setPlntCd(crhdEntity.getWerks());
        wrkCtrBo.setWrkCtrCatCd(crhdEntity.getVerwe());
        wrkCtrBo.setDelInd(crhdEntity.getLvorm());
        wrkCtrBo.setWrkCtrUsgCd(crhdEntity.getPlanv());
        wrkCtrBo.setWrkCtrLocCd(crhdEntity.getStand());
        wrkCtrBo.setRespPrsnNum(crhdEntity.getVeran());
        wrkCtrBo.setWrkCtrActvCd(crhdEntity.getVgwts());
        wrkCtrBo.setLockInd(crhdEntity.getXsprr());
        wrkCtrBo.setSchdlngInd(crhdEntity.getXterm());
        wrkCtrBo.setSetupTypeCd(crhdEntity.getRasch());
        wrkCtrBo.setOprCd(crhdEntity.getSteus());
        wrkCtrBo.setSetupFrmlCd(crhdEntity.getFort1());
        wrkCtrBo.setRunFrmlCd(crhdEntity.getFort2());
        wrkCtrBo.setTeardownFrmlCd(crhdEntity.getFort3());
        wrkCtrBo.setCapyNum(crhdEntity.getKapid());
        wrkCtrBo.setLocGrpCd(crhdEntity.getOrtgr());
        wrkCtrBo.setMachTypeCd(crhdEntity.getMatyp());
        wrkCtrBo.setPlnrGrpCd(crhdEntity.getCplgr());
        wrkCtrBo.setOthFrmlCd(crhdEntity.getFortn());
        wrkCtrBo.setSuplAreaCd(crhdEntity.getPrvbe());
        wrkCtrBo.setSlocCd(crhdEntity.getLgort_res());
        wrkCtrBo.setMixingInd(crhdEntity.getMixmat());
        //J1 T2
        List<ProjectOneCrtxEntity> crtxEntities = crtxDao.getEntityWithObjid(crhdEntity.getObjid(), crhdEntity.getObjty());
        LogUtil.getCoreLog().info("--------------------crtxEntities--------" + crtxEntities);
        if (crtxEntities != null && crtxEntities.size() > 0) {
            String capyDesc = "";
            String[] strArray = new String[3];
            for (ProjectOneCrtxEntity entity : crtxEntities) {
                if (entity.getSpras().equals(IConstant.VALUE.EN_CAPY)) {
                    strArray[0] = entity.getKtext() + "/";
                }
                if (entity.getSpras().equals(IConstant.VALUE.ES_CAPY)) {
                    strArray[1] = entity.getKtext() + "/";
                }
                if (entity.getSpras().equals(IConstant.VALUE.PT_CAPY)) {
                    strArray[2] = entity.getKtext() + "/";
                }
            }
            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i] == null) {
                    capyDesc = capyDesc + "/";
                } else {
                    capyDesc = capyDesc + strArray[i];
                }
            }
            capyDesc = capyDesc.substring(0, capyDesc.length() - 1);
            wrkCtrBo.setWrkCtrDesc(capyDesc);
        }
        resultObject.setBaseBo(wrkCtrBo);
        return resultObject;
    }
}

package com.jnj.pangea.edm.capy_hdr.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKaktDaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKakoEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKaktEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.capy_hdr.bo.EDMCapyHdrBo;

import java.util.List;

public class EDMCapyHdrServiceImpl implements ICommonService {

    private static EDMCapyHdrServiceImpl instance;

    public static EDMCapyHdrServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMCapyHdrServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneKaktDaoImpl kaktDao = ProjectOneKaktDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneKakoEntity kakoEntity = (ProjectOneKakoEntity) o;

        EDMCapyHdrBo capyHdrBo = new EDMCapyHdrBo();
        //T1
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (entityWithLocalSourceSystem != null) {
            capyHdrBo.setSrcSysCd(entityWithLocalSourceSystem.getSourceSystem());
        }
        capyHdrBo.setCapyNum(kakoEntity.getKapid());
        capyHdrBo.setCapyNbr(kakoEntity.getAznor());
        capyHdrBo.setStrtTm(kakoEntity.getBegzt());
        capyHdrBo.setEndTm(kakoEntity.getEndzt());
        capyHdrBo.setFctryCalCd(kakoEntity.getKalid());
        capyHdrBo.setCapyCatCd(kakoEntity.getKapar());
        capyHdrBo.setCapyBaseUomCd(kakoEntity.getMeins());
        capyHdrBo.setCapyNm(kakoEntity.getName());
        capyHdrBo.setCapyUtlzRtPct(kakoEntity.getNgrad());
        capyHdrBo.setPlnrGrpCd(kakoEntity.getPlanr());
        capyHdrBo.setPoolCapyInd(kakoEntity.getPoolk());
        capyHdrBo.setPlntCd(kakoEntity.getWerks());
        capyHdrBo.setFiniteSchdlngInd(kakoEntity.getKapter());
        capyHdrBo.setMltOpsInd(kakoEntity.getKapavo());
        capyHdrBo.setOvldPct(kakoEntity.getUeberlast());
        capyHdrBo.setLtpExclnInd(kakoEntity.getKaplpl());
        capyHdrBo.setCapyUomCd(kakoEntity.getKapeh());
        capyHdrBo.setIndivCapyInd(kakoEntity.getMehr());
        capyHdrBo.setAvailCapyUomCd(kakoEntity.getAng_unit());
        capyHdrBo.setBtneckInd(kakoEntity.getIs_bottleneck());
        //J1 T2
        List<ProjectOneKaktEntity> kaptEntityList = kaktDao.getEntityWithKapid(kakoEntity.getKapid());
        if (kaptEntityList != null && kaptEntityList.size() > 0) {
            String capyDesc = "";
            String[] strArray = new String[3];
            for (ProjectOneKaktEntity entity : kaptEntityList) {
                if (entity.getSpras().equals("EN")) {
                        strArray[0] = entity.getKtext() + "/";
                }
                if (entity.getSpras().equals("ES")) {
                        strArray[1] = entity.getKtext() + "/";
                }
                if (entity.getSpras().equals("PT")) {
                        strArray[2] = entity.getKtext() + "/";
                }
            }
            for(int i=0;i<strArray.length;i++){
                if(strArray[i]==null){
                    capyDesc=capyDesc+"/";
                }else{
                    capyDesc=capyDesc+strArray[i];
                }
            }
            capyDesc = capyDesc.substring(0, capyDesc.length() - 1);
            capyHdrBo.setCapyDesc(capyDesc);
        }
        resultObject.setBaseBo(capyHdrBo);
        return resultObject;
    }

}

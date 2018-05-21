package com.jnj.pangea.edm.bom_hdr.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneStkoDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.StkoEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_hdr.bo.EDMBomHdrV1Bo;

import java.util.*;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMBomHdrServiceImpl implements ICommonService {


    private static ICommonService instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneStkoDaoImpl projectOneStkoDao = ProjectOneStkoDaoImpl.getInstance();

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMBomHdrServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        StkoEntity mainData = (StkoEntity) o;
        EDMBomHdrV1Bo edmBomHdrV1Bo = new EDMBomHdrV1Bo();
        //T1 Get sourceSystem from source_system_v1 using below condition:
        //source_system_v1-localSourceSystem = "project_one"
        edmBomHdrV1Bo.setSrcSysCd(getSourceSystemFromSourceSystemV1());
        edmBomHdrV1Bo.setBomCatCd(mainData.getStlty());
        edmBomHdrV1Bo.setBomNum(mainData.getStlnr());
        edmBomHdrV1Bo.setAltBomNum(mainData.getStlal());
        edmBomHdrV1Bo.setBomCntrNbr(mainData.getStkoz());
        edmBomHdrV1Bo.setBomVldFromDt(mainData.getDatuv());
        edmBomHdrV1Bo.setChgNum(mainData.getAennr());
        edmBomHdrV1Bo.setDelInd(mainData.getLoekz());
        edmBomHdrV1Bo.setPrvCntrNbr(mainData.getVgkzl());
        edmBomHdrV1Bo.setCrtDttm(mainData.getAndat());
        edmBomHdrV1Bo.setChgDttm(mainData.getAedat());
        edmBomHdrV1Bo.setBomUomCd(mainData.getBmein());
        edmBomHdrV1Bo.setBomBaseQty(mainData.getBmeng());
        edmBomHdrV1Bo.setBomTxt(mainData.getStktx());
        edmBomHdrV1Bo.setBomStsCd(mainData.getStlst());
        //T2
        List<StkoEntity> stkoEntityList = projectOneStkoDao.getEntityWithStlnrAndStlal(mainData.getStlnr(), mainData.getStlal());
        String stkoz = mainData.getStkoz();
        if (stkoEntityList.size() > 1) {
            sort( stkoEntityList);
            if ((stkoEntityList.get(stkoEntityList.size() - 1).getStkoz().equals(stkoz))) {
                edmBomHdrV1Bo.setBomVld_ToDt(IConstant.VALUE.BOM_VlD_ToDt);
            }
            for (StkoEntity st : stkoEntityList) {
                if (Integer.parseInt(st.getStkoz()) > Integer.parseInt(stkoz)) {
                    edmBomHdrV1Bo.setBomVld_ToDt(st.getDatuv());
                    break;
                }
            }
        } else {
            edmBomHdrV1Bo.setBomVld_ToDt(IConstant.VALUE.BOM_VlD_ToDt);
        }
        resultObject.setBaseBo(edmBomHdrV1Bo);
        return resultObject;
    }

    public String getSourceSystemFromSourceSystemV1() {
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (entityWithLocalSourceSystem != null) {
            return entityWithLocalSourceSystem.getSourceSystem();
        }
        return null;
    }
    public List<StkoEntity> sort(List<StkoEntity> stkoEntityList){
        Collections.sort(stkoEntityList, new Comparator<StkoEntity>(){
                    public int compare(StkoEntity s1, StkoEntity s2) {
                        if(Integer.parseInt(s1.getStkoz()) > Integer.parseInt(s2.getStkoz())){
                            return 1;
                        }
                        if(Integer.parseInt(s1.getStkoz()) == Integer.parseInt(s2.getStkoz())){
                            return 0;
                        }
                        return -1;
                    }
                });
        return stkoEntityList;
    }
}

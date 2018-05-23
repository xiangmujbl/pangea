package com.jnj.pangea.edm.mfg_rtng_seq.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlflDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlflEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_seq.bo.EDMMfgRtngSeqBo;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EDMMfgRtngSeqServiceImpl implements ICommonService {
    private static EDMMfgRtngSeqServiceImpl instance;
    //private static EDMMfgRtngSeqServiceImpl instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOnePlflDaoImpl projectOnePlflDao = ProjectOnePlflDaoImpl.getInstance();

    public static EDMMfgRtngSeqServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngSeqServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        PlflEntity plflEntity = (PlflEntity) o;
        if (null != plflEntity) {
            if (StringUtils.isNotEmpty(plflEntity.getLoekz()) && IConstant.VALUE.X.equals(plflEntity.getLoekz())) {
                return resultObject;
            }
        }

        EDMMfgRtngSeqBo mfgRtngSeqBo = new EDMMfgRtngSeqBo();

        //rule F1
        mfgRtngSeqBo.setRtngTypCd(IConstant.VALUE.N);
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != edmSourceSystemV1Entity) {
            mfgRtngSeqBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        mfgRtngSeqBo.setRtngGrpCd(plflEntity.getPlnnr());
        mfgRtngSeqBo.setRtngGrpCntrNbr(plflEntity.getPlnal());
        mfgRtngSeqBo.setRtngSqncNum(plflEntity.getPlnfl());
        mfgRtngSeqBo.setRtngSqncVrsnCntrNbr(plflEntity.getZaehl());
        mfgRtngSeqBo.setValFromDt(plflEntity.getDatuv());
        mfgRtngSeqBo.setDelInd(plflEntity.getLoekz());
        mfgRtngSeqBo.setCrtDttm(plflEntity.getAndat());
        mfgRtngSeqBo.setChgDttm(plflEntity.getAedat());
        mfgRtngSeqBo.setSeqCategory(plflEntity.getFlgat());
        mfgRtngSeqBo.setSeqDesc(plflEntity.getLtxa1());
        mfgRtngSeqBo.setFromLtSzQty(plflEntity.getLosvn());
        mfgRtngSeqBo.setToLtSzQty(plflEntity.getLosbs());
        mfgRtngSeqBo.setSeqRelKeyBranch(plflEntity.getBschl1());
        mfgRtngSeqBo.setSeqRelKeyReturn(plflEntity.getBschl2());
        mfgRtngSeqBo.setSeqStartNode(plflEntity.getBknt1());
        mfgRtngSeqBo.setSeqEndNode(plflEntity.getBknt2());
        //rule T2

        List<PlflEntity> plflEntitylist = projectOnePlflDao.getEntityWithPlnnrAndPlnalAndPlnfl(plflEntity.getPlnnr(), plflEntity.getPlnal(), plflEntity.getPlnfl());
        String zaehl = plflEntity.getZaehl();
        if (plflEntitylist != null) {
            if (plflEntitylist.size() > 1) {
                Collections.sort(plflEntitylist, new Comparator<PlflEntity>(){
                    public int compare(PlflEntity s1, PlflEntity s2) {
                        if(Integer.parseInt(s1.getZaehl()) > Integer.parseInt(s2.getZaehl())){
                            return 1;
                        }
                        if(Integer.parseInt(s1.getZaehl()) == Integer.parseInt(s2.getZaehl())){
                            return 0;
                        }
                        return -1;
                    }
                });
                if ((plflEntitylist.get(plflEntitylist.size() - 1).getZaehl().equals(zaehl))) {
                    mfgRtngSeqBo.setSeqValidToDate(IConstant.VALUE.BOM_VlD_ToDt);
                    mfgRtngSeqBo.setChgNum(plflEntity.getAennr());
                }
                for (PlflEntity st : plflEntitylist) {
                    if (Integer.parseInt(st.getZaehl()) > Integer.parseInt(zaehl)) {
                        mfgRtngSeqBo.setSeqValidToDate(st.getDatuv());
                        mfgRtngSeqBo.setChgNum(st.getAennr());
                        break;
                    }
                }
            } else {
                mfgRtngSeqBo.setSeqValidToDate(IConstant.VALUE.BOM_VlD_ToDt);
                mfgRtngSeqBo.setChgNum(plflEntity.getAennr());

            }
        }
        LogUtil.getCoreLog().info("======mfgRtngSeqBo=====" + mfgRtngSeqBo);
        resultObject.setBaseBo(mfgRtngSeqBo);
        return resultObject;
    }

}


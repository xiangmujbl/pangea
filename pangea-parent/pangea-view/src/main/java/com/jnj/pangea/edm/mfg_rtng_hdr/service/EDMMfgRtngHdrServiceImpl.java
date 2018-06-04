package com.jnj.pangea.edm.mfg_rtng_hdr.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlkoDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlkoEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_hdr.bo.EDMMfgRtngHdrBo;
import com.jnj.pangea.util.DateUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EDMMfgRtngHdrServiceImpl implements ICommonService {

    private static EDMMfgRtngHdrServiceImpl instance;

    public static EDMMfgRtngHdrServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngHdrServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOnePlkoDaoImpl projectOnePlkoDao = ProjectOnePlkoDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlkoEntity plkoEntity = (PlkoEntity) o;

        EDMMfgRtngHdrBo mfgRtngHdrBo = new EDMMfgRtngHdrBo();

        if(plkoEntity.getLoekz()!=null&&plkoEntity.getLoekz().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_LOEKZ_VALUE_X)){
            return resultObject;
        }
        if(plkoEntity.getPlnty()!=null&&!plkoEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_2)&&!plkoEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_N)){
            return resultObject;
        }
        mfgRtngHdrBo.setRtngTypCd(plkoEntity.getPlnty());
        mfgRtngHdrBo.setRtngGrpCd(plkoEntity.getPlnnr());
        mfgRtngHdrBo.setRtngGrpCntrNbr(plkoEntity.getPlnal());
        mfgRtngHdrBo.setRtngVrsnCntrNbr(plkoEntity.getZaehl());
        mfgRtngHdrBo.setValFromDt(plkoEntity.getDatuv());
        mfgRtngHdrBo.setChgNum(plkoEntity.getAennr());
        mfgRtngHdrBo.setDelInd(plkoEntity.getLoekz());
        mfgRtngHdrBo.setCrtDttm(plkoEntity.getAndat());
        mfgRtngHdrBo.setChgDttm(plkoEntity.getAedat());
        mfgRtngHdrBo.setPlntCd(plkoEntity.getWerks());
        mfgRtngHdrBo.setRtngUsgCd(plkoEntity.getVerwe());
        mfgRtngHdrBo.setRtngSttsCd(plkoEntity.getStatu());
        mfgRtngHdrBo.setRtngUomCd(plkoEntity.getPlnme());
        mfgRtngHdrBo.setFromLtSzQty(plkoEntity.getLosvn());
        mfgRtngHdrBo.setToLtSzQty(plkoEntity.getLosbs());
        mfgRtngHdrBo.setRtngPlnnrGrpCd(plkoEntity.getVagrp());
        mfgRtngHdrBo.setRtngDesc(plkoEntity.getKtext());
        mfgRtngHdrBo.setRtngPrflCd(plkoEntity.getProfidnetz());
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            mfgRtngHdrBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }

        List<PlkoEntity> list= projectOnePlkoDao.getProjectOneMaplClone(plkoEntity.getPlnty(),plkoEntity.getPlnnr(),plkoEntity.getPlnal());
        if(list!=null&&list.size()>1){
            PlkoEntity projectOneplkoEntity=getNextRecord(list,plkoEntity.getZaehl());
            if(projectOneplkoEntity!=null){
                Date fromDueDate = DateUtils.stringToDate(projectOneplkoEntity.getDatuv(), DateUtils.F_yyyyMMdd);
                mfgRtngHdrBo.setRtgVld_ToDt(DateUtils.dateToString(fromDueDate, DateUtils.yyyy_MM_dd));
            }else{
                mfgRtngHdrBo.setRtgVld_ToDt(IConstant.MFG_RTNG_RLTNSHP.FIELD_MATLRTNGVALID_TO);
            }
        }else{
            mfgRtngHdrBo.setRtgVld_ToDt(IConstant.MFG_RTNG_RLTNSHP.FIELD_MATLRTNGVALID_TO);
        }

        resultObject.setBaseBo(mfgRtngHdrBo);
        return resultObject;
    }

    public PlkoEntity getNextRecord(List<PlkoEntity> list, String zaehl){
        Iterator<PlkoEntity> iterable=list.iterator();
        try {
            while (iterable.hasNext()){
                PlkoEntity ProjectOnePlkoEntity=  iterable.next();
                LogUtil.getCoreLog().info("==========="+zaehl+ProjectOnePlkoEntity.toString());
                if(ProjectOnePlkoEntity.getZaehl().equalsIgnoreCase(zaehl)){
                    if(iterable.hasNext()){
                        return iterable.next();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

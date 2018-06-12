package com.jnj.pangea.edm.matl_mfg_rtng.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMProjectOneMaplDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ngems.NgemsGoldenMaterialDaoImpl;
import com.jnj.pangea.common.dao.impl.ngems.NgemsMaterialLinkageDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMaktDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProjectOneMAPLEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_mfg_rtng.bo.MATLMFGRTNGBo;
import com.jnj.pangea.util.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation
 * AEAZ-3268
 */
public class MATLMFGRTNGServiceImpl implements ICommonService {

    private static MATLMFGRTNGServiceImpl instance;

    public static MATLMFGRTNGServiceImpl getInstance() {
        if (instance == null) {
            instance = new MATLMFGRTNGServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMProjectOneMaplDaoImpl edmProjectOneMaplDao = EDMProjectOneMaplDaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMProjectOneMAPLEntity edmProjectOneMAPLEntity=(EDMProjectOneMAPLEntity)o;
        if(edmProjectOneMAPLEntity.getLoekz().equalsIgnoreCase(IConstant.MATL_MFG_RTNG.FIELD_LOEKZ_VALUE_X)){
            return resultObject;
        }
        MATLMFGRTNGBo matlmfgrtngBo=new MATLMFGRTNGBo();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            matlmfgrtngBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        matlmfgrtngBo.setMatlNum(edmProjectOneMAPLEntity.getMatnr());
        matlmfgrtngBo.setPlntCd(edmProjectOneMAPLEntity.getWerks());
        matlmfgrtngBo.setRtngTypCd(edmProjectOneMAPLEntity.getPlnty());
        matlmfgrtngBo.setRntgGrpCd(edmProjectOneMAPLEntity.getPlnnr());
        matlmfgrtngBo.setRntgAddtnlCntrNbr(edmProjectOneMAPLEntity.getZkriz());
        matlmfgrtngBo.setRntgGrpCntrNbr(edmProjectOneMAPLEntity.getPlnal());
        matlmfgrtngBo.setMatlRtngVrsnCntrNbr(edmProjectOneMAPLEntity.getZaehl());
        matlmfgrtngBo.setDelInd(edmProjectOneMAPLEntity.getLoekz());
        matlmfgrtngBo.setCrtDttm(edmProjectOneMAPLEntity.getAndat());
        matlmfgrtngBo.setChgDttm(edmProjectOneMAPLEntity.getAedat());

        //  get data  from  clone
        List<EDMProjectOneMAPLEntity> list=edmProjectOneMaplDao.getProjectOneMaplClone(edmProjectOneMAPLEntity.getPlnnr(),edmProjectOneMAPLEntity.getPlnty(),edmProjectOneMAPLEntity.getPlnal(),edmProjectOneMAPLEntity.getZkriz());
        if(list!=null&&list.size()>1){
            EDMProjectOneMAPLEntity edmProjectOneMAPLEntity1=getLOEKZIsX(list);
            LogUtil.getCoreLog().info(edmProjectOneMAPLEntity1.toString());
            matlmfgrtngBo.setValFromDt(edmProjectOneMAPLEntity.getDatuv());
            matlmfgrtngBo.setChgNum(edmProjectOneMAPLEntity.getAennr());
            matlmfgrtngBo.setMatlRtngValid_To(IConstant.MATL_MFG_RTNG.FIELD_MATLRTNGVALID_TO_NULL);
            if(edmProjectOneMAPLEntity1!=null ){
                Date fromDueDate = DateUtils.stringToDate(edmProjectOneMAPLEntity1.getDatuv(), DateUtils.F_yyyyMMdd);
                matlmfgrtngBo.setMatlRtngValid_To(DateUtils.dateToString(fromDueDate, DateUtils.yyyy_MM_dd));
            }
        }else {
            matlmfgrtngBo.setValFromDt(edmProjectOneMAPLEntity.getDatuv());
            matlmfgrtngBo.setChgNum(edmProjectOneMAPLEntity.getAennr());
            matlmfgrtngBo.setMatlRtngValid_To(IConstant.MATL_MFG_RTNG.FIELD_MATLRTNGVALID_TO);
        }
        resultObject.setBaseBo(matlmfgrtngBo);
        return resultObject;
    }
    public EDMProjectOneMAPLEntity getLOEKZIsX(List<EDMProjectOneMAPLEntity> list){
        for(EDMProjectOneMAPLEntity edmProjectOneMAPLEntity:list){
            if(edmProjectOneMAPLEntity.getLoekz().equalsIgnoreCase(IConstant.MATL_MFG_RTNG.FIELD_LOEKZ_VALUE_X)){
                return edmProjectOneMAPLEntity;
            }
        }
        return null;
    }
}

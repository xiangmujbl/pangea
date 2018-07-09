package com.jnj.pangea.edm.mfg_rtng_rltnshp.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlabDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlabEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlabEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_rltnshp.bo.EDMMfgRtngRltnshpBo;
import com.jnj.pangea.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EDMMfgRtngRltnshpServiceImpl implements ICommonService {

    private static EDMMfgRtngRltnshpServiceImpl instance;

    public static EDMMfgRtngRltnshpServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngRltnshpServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOnePlabDaoImpl projectOnePlabDao = ProjectOnePlabDaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOnePlabEntity plabEntity = (ProjectOnePlabEntity) o;
        if (plabEntity.getLoekz() != null && plabEntity.getLoekz().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_LOEKZ_VALUE_X)) {
            return resultObject;
        }
        if (plabEntity.getPlnty() != null && !plabEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_2) && !plabEntity.getPlnty().trim().equalsIgnoreCase(IConstant.MFG_RTNG_RLTNSHP.FIELD_PLNTY_VALUE_N)) {
            return resultObject;
        }

        EDMMfgRtngRltnshpBo mfgRtngRltnshpBo = new EDMMfgRtngRltnshpBo();
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        // TODO add logic
        if (null != sourceSystemV1Entity) {
            mfgRtngRltnshpBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        mfgRtngRltnshpBo.setRtngTypCd(plabEntity.getPlnty());
        mfgRtngRltnshpBo.setRtngGrpCd(plabEntity.getPlnnr());
        mfgRtngRltnshpBo.setRtngGrpCntrNbr(plabEntity.getPlnal());
        mfgRtngRltnshpBo.setRtngNdeNum(plabEntity.getPlnkn());
        mfgRtngRltnshpBo.setRtngScndGrpCd(plabEntity.getPlnrn());
        mfgRtngRltnshpBo.setRtngScndGrpCntrNbr(plabEntity.getAlnrn());
        mfgRtngRltnshpBo.setRtngScndNdeNum(plabEntity.getKnnrn());
        mfgRtngRltnshpBo.setRltnshpTypCd(plabEntity.getAobar());
        mfgRtngRltnshpBo.setRltnshpMxIntrvlCd(plabEntity.getMimax());
        mfgRtngRltnshpBo.setRltnshpVrsnCntrNbr(plabEntity.getZaehl());
        mfgRtngRltnshpBo.setValFromDt(plabEntity.getDatuv());
        mfgRtngRltnshpBo.setChgNum(plabEntity.getAennr());
        mfgRtngRltnshpBo.setRltnshpIntrvlUomCd(plabEntity.getZeinh());
        mfgRtngRltnshpBo.setRltnshpIntrvl(plabEntity.getDauer());
        mfgRtngRltnshpBo.setRltsnhpDrtnInd(plabEntity.getDaukz());
        mfgRtngRltnshpBo.setFctryClndrCd(plabEntity.getKalid());
        mfgRtngRltnshpBo.setPlntCd(plabEntity.getWerks());
        mfgRtngRltnshpBo.setCrtDttm(plabEntity.getAndat());
        mfgRtngRltnshpBo.setChgDttm(plabEntity.getAedat());
        List<ProjectOnePlabEntity> list = projectOnePlabDao.getProjectOneMaplClone(plabEntity.getPlnnr(), plabEntity.getPlnty(), plabEntity.getPlnal(), plabEntity.getPlnkn(), plabEntity.getKnnrn());
        if (list != null && list.size() > 1) {
            ProjectOnePlabEntity projectOnePlabEntity = getNextRecord(list, plabEntity.getZaehl());
            if (projectOnePlabEntity != null) {
                Date fromDueDate = DateUtils.stringToDate(projectOnePlabEntity.getDatuv(), DateUtils.F_yyyyMMdd);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fromDueDate);
                calendar.add(Calendar.DATE, -1);
                String VldToDt = DateUtils.dateToString(calendar.getTime(), DateUtils.F_yyyyMMdd);
                mfgRtngRltnshpBo.setRltnshpValidEnd(VldToDt);
            } else {
                mfgRtngRltnshpBo.setRltnshpValidEnd(IConstant.MFG_RTNG_RLTNSHP.FIELD_MATLRTNGVALID_TO);
            }
        } else {
            mfgRtngRltnshpBo.setRltnshpValidEnd(IConstant.MFG_RTNG_RLTNSHP.FIELD_MATLRTNGVALID_TO);
        }
        resultObject.setBaseBo(mfgRtngRltnshpBo);
        return resultObject;
    }

    public ProjectOnePlabEntity getNextRecord(List<ProjectOnePlabEntity> list, String zaehl) {
        Iterator<ProjectOnePlabEntity> iterable = list.iterator();
        try {
            while (iterable.hasNext()) {
                ProjectOnePlabEntity projectOnePlabEntity = iterable.next();
                LogUtil.getCoreLog().info("===========" + zaehl + projectOnePlabEntity.toString());
                if (projectOnePlabEntity.getZaehl().equalsIgnoreCase(zaehl)) {
                    if (iterable.hasNext()) {
                        return iterable.next();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

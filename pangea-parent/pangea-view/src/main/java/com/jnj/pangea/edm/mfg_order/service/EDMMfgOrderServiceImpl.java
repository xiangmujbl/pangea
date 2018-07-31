package com.jnj.pangea.edm.mfg_order.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneAufkDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneJestDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTj02tDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.AfkoEntity;
import com.jnj.pangea.common.entity.project_one.AufkEntity;
import com.jnj.pangea.common.entity.project_one.JestEntity;
import com.jnj.pangea.common.entity.project_one.Tj02tEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_order.bo.EDMMfgOrderBo;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EDMMfgOrderServiceImpl implements ICommonService {

    private static EDMMfgOrderServiceImpl instance;

    public static EDMMfgOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneAufkDaoImpl aufkDao = ProjectOneAufkDaoImpl.getInstance();
    private ProjectOneJestDaoImpl jestDao = ProjectOneJestDaoImpl.getInstance();
    private ProjectOneTj02tDaoImpl tj02tDao = ProjectOneTj02tDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {


        ResultObject resultObject = new ResultObject();
        AfkoEntity afkoEntity = (AfkoEntity) o;

        EDMMfgOrderBo mfgOrderBo = new EDMMfgOrderBo();
        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
            mfgOrderBo.setSourceSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        //rule J1
        String aufnr = afkoEntity.getAufnr();
        AufkEntity aufkEntity = aufkDao.getEntityWithAufnr(aufnr);
        if (null!=aufkEntity){
            mfgOrderBo.setMfgOrdrNum(aufkEntity.getAufnr());

            mfgOrderBo.setMfgOrdrTypCd(aufkEntity.getAuart());
            mfgOrderBo.setPlntCd(aufkEntity.getWerks());
            mfgOrderBo.setCrtdDttm(aufkEntity.getErdat());
            mfgOrderBo.setChgDttm(aufkEntity.getAedat());
            mfgOrderBo.setDelInd(aufkEntity.getLoekz());
            mfgOrderBo.setObjectNumber(aufkEntity.getObjnr());

            //rule T2
            String objnr = aufkEntity.getObjnr();
            List<JestEntity> jestEntities = jestDao.getEntityByObjnrAndStat(objnr);
            if (null!=jestEntities && jestEntities.size()>0){
                String istat = IConstant.VALUE.BLANK;
                String txt04 = IConstant.VALUE.BLANK;
                String stat = IConstant.VALUE.BLANK;
                for (JestEntity jest:jestEntities) {
                    String stat1 = jest.getStat();
                    List<Tj02tEntity> tj02tEntities = tj02tDao.getEntityWithStat(stat1);
                    if (null!=tj02tEntities && tj02tEntities.size()>0){
                        for (Tj02tEntity tj02:tj02tEntities) {
                            istat=istat + "," +tj02.getIstat();
                            txt04=txt04 + "," +tj02.getTxt04();
                            stat=stat + "," +jest.getStat();
                        }
                    }
                }
                mfgOrderBo.setLocalSystemStatus(istat.trim().substring(1));
                mfgOrderBo.setLocalStatus1(txt04.trim().substring(1));
                mfgOrderBo.setMfgOrdrSttsCd(stat.trim().substring(1));
            }
        }
        mfgOrderBo.setStrtDt(afkoEntity.getGstrp());
        mfgOrderBo.setStrtTm(afkoEntity.getGsuzp());
        mfgOrderBo.setEndDt(afkoEntity.getGltrp());
        mfgOrderBo.setEndTm(afkoEntity.getGluzp());
        mfgOrderBo.setSchdStrtDt(afkoEntity.getGstrs());
        mfgOrderBo.setSchdStrtTm(afkoEntity.getGsuzs());
        mfgOrderBo.setSchdEndDt(afkoEntity.getGltrs());
        mfgOrderBo.setSchdEndTm(afkoEntity.getGluzs());
        mfgOrderBo.setSchRelDt(afkoEntity.getFtrms());
        mfgOrderBo.setActStrtDt(afkoEntity.getGstri());
        mfgOrderBo.setActStrtTm(afkoEntity.getGsuzi());
        mfgOrderBo.setPrdtnEndDt(afkoEntity.getGltri());
        mfgOrderBo.setCnfrmdEndDt(afkoEntity.getGetri());
        mfgOrderBo.setCnfrmdEndTm(afkoEntity.getGeuzi());
        mfgOrderBo.setPlanRlseDt(afkoEntity.getFtrmp());
        mfgOrderBo.setActRlseDt(afkoEntity.getFtrmi());
        mfgOrderBo.setRsrvtnNum(afkoEntity.getRsnum());
        mfgOrderBo.setRtngTypCd(afkoEntity.getPlnty());
        mfgOrderBo.setRtngGrpCd(afkoEntity.getPlnnr());
        mfgOrderBo.setRtngGrpCntrNum(afkoEntity.getPlnal());
        mfgOrderBo.setBomCatCd(afkoEntity.getStlty());
        mfgOrderBo.setBomNum(afkoEntity.getStlnr());
        mfgOrderBo.setBomAltNum(afkoEntity.getStlal());
        mfgOrderBo.setMrpCntrllrCd(afkoEntity.getDispo());
        mfgOrderBo.setOrdrRtngNum(afkoEntity.getAufpl());
        mfgOrderBo.setPrdSpvsrCd(afkoEntity.getFevor());
        mfgOrderBo.setCnfrmdYldQty(afkoEntity.getIgmng());
        mfgOrderBo.setCnfrmdScrpQty(afkoEntity.getIasmg());

        //rule T3
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sd.format(new Date());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        }catch (ParseException e){
            LogUtil.getCoreLog().info("==="+e.getMessage());
        }
        long unixTimestamp = date.getTime() / 1000;
        String timeStmp = String.valueOf(unixTimestamp);
        mfgOrderBo.setInternalTimeStamp(timeStmp);

        resultObject.setBaseBo(mfgOrderBo);
        return resultObject;
    }
}

package com.jnj.pangea.edm.process_order.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.*;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneAufkDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneJestDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTj02tDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneAfpoDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.process_order.bo.EDMProcessOrderBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EDMProcessOrderServiceImpl implements ICommonService {

    private static EDMProcessOrderServiceImpl instance;

    public static EDMProcessOrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMProcessOrderServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneAufkDaoImpl aufkDao = ProjectOneAufkDaoImpl.getInstance();
    private ProjectOneJestDaoImpl jestDao = ProjectOneJestDaoImpl.getInstance();
    private ProjectOneTj02tDaoImpl tj02tDao = ProjectOneTj02tDaoImpl.getInstance();
    private ProjectOneAfpoDaoImpl afpoDao = ProjectOneAfpoDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        AfkoEntity afkoEntity = (AfkoEntity) o;

        EDMProcessOrderBo processOrderBo = new EDMProcessOrderBo();

        // T1
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            processOrderBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());
        }

        String aufnr = afkoEntity.getAufnr();
        if (StringUtils.isNotEmpty(aufnr)) {

            // N1
            AufkEntity aufkEntity = aufkDao.getEntityWithAufnr(aufnr);
            if (null != aufkEntity) {
                processOrderBo.setMfgOrdrNum(aufkEntity.getAufnr());
                processOrderBo.setOrdrType(aufkEntity.getAuart());
                processOrderBo.setLocalOrderCat(aufkEntity.getAutyp());
                processOrderBo.setCrtdDttm(aufkEntity.getErdat());
                processOrderBo.setChgDttm(aufkEntity.getAedat());
                processOrderBo.setPlntCd(aufkEntity.getWerks());
                processOrderBo.setDelInd(aufkEntity.getLoekz());
                processOrderBo.setLocalTechnicalCompletion(aufkEntity.getIdat2());
                processOrderBo.setLocalProductionProcessNo(aufkEntity.getProcnr());
                processOrderBo.setLocalLogicalSystem(aufkEntity.getLogsystem());

                // N3
                String mfgOrdrNum = aufkEntity.getAufnr();
                if (StringUtils.isNotEmpty(mfgOrdrNum)) {
                    List<JestEntity> jestEntityList = jestDao.getEntityWithObjnr(mfgOrdrNum);

                    if (null != jestEntityList) {

                        String ordrStts = StringUtils.EMPTY;
                        for (JestEntity jestEntity : jestEntityList) {

                            String stat = jestEntity.getStat();
                            if (StringUtils.isNotEmpty(stat)) {
                                List<Tj02tEntity> tj02tEntityList = tj02tDao.getEntityWithStat(stat);
                                if (tj02tEntityList.size() > 0) {
                                    for (Tj02tEntity tj02tEntity : tj02tEntityList) {
                                        ordrStts = ordrStts + IConstant.VALUE.SPACE + tj02tEntity.getTxt04();
                                    }
                                }
                            }
                        }
                        processOrderBo.setOrdrStts(ordrStts.trim());
                    }
                }
            }

            // N2
            AfpoEntity afpoEntity = afpoDao.getEntityWithAufnr(aufnr);
            if (null != afpoEntity) {
                processOrderBo.setMfgOrdrItemNum(afpoEntity.getPosnr());
                processOrderBo.setPlnndOrdr(afpoEntity.getPlnum());
                processOrderBo.setScrpQty(afpoEntity.getPsamg());
                processOrderBo.setOrdrQty(afpoEntity.getPsmng());
                processOrderBo.setRcvdQty(afpoEntity.getWemng());
                processOrderBo.setPrdtnUom(afpoEntity.getAmein());
                processOrderBo.setMatlNum(afpoEntity.getMatnr());
                processOrderBo.setDlvCmpltInd(afpoEntity.getElikz());
                processOrderBo.setPrdntVrsnNum(afpoEntity.getVerid());
                processOrderBo.setBtchNum(afpoEntity.getCharg());
                processOrderBo.setFctrNmrtrMeas(afpoEntity.getUmrez());
                processOrderBo.setFctrDnmntrMeas(afpoEntity.getUmren());
                processOrderBo.setGoodRcptLdDaysQty(afpoEntity.getWebaz());
            }
        }

        processOrderBo.setPlnEndDt(afkoEntity.getGltrp());
        processOrderBo.setPlnStrtDt(afkoEntity.getGstrp());
        processOrderBo.setPrdtnSchdEndDt(afkoEntity.getGltrs());
        processOrderBo.setPrdtnSchdStrtDt(afkoEntity.getGstrs());
        processOrderBo.setPrdntStrtDt(afkoEntity.getGstri());
        processOrderBo.setPrdtnEndDt(afkoEntity.getGltri());
        processOrderBo.setPrdtnRlseDt(afkoEntity.getFtrmi());
        processOrderBo.setPrdtnPlanRlseDt(afkoEntity.getFtrmp());
        processOrderBo.setMfgPlnnr(afkoEntity.getDispo());
        processOrderBo.setMfgSprvsr(afkoEntity.getFevor());
        processOrderBo.setLocalReservation(afkoEntity.getRsnum());
        processOrderBo.setLocalTotalScrap(afkoEntity.getGasmg());
        processOrderBo.setLocalTargetQty(afkoEntity.getGamng());
        processOrderBo.setLocalBaseUOM(afkoEntity.getGmein());
        processOrderBo.setLocalMaterialAFKOPLNBEZ(afkoEntity.getPlnbez());
        processOrderBo.setLocalTaskListType(afkoEntity.getPlnty());
        processOrderBo.setLocalGroup(afkoEntity.getPlnnr());
        processOrderBo.setLocalApplication(afkoEntity.getPlnaw());
        processOrderBo.setLocalGroupCounter(afkoEntity.getPlnal());
        processOrderBo.setLocalUsage(afkoEntity.getPverw());
        processOrderBo.setLocalMaterialAFKOSTLBEZ(afkoEntity.getStlbez());
        processOrderBo.setLocalSchedulingType(afkoEntity.getTerkz());
        processOrderBo.setLocalConfirmation(afkoEntity.getRueck());
        processOrderBo.setLocalConfirmationCounter(afkoEntity.getRmzhl());
        processOrderBo.setLocalConfirmedQtyIGMNG(afkoEntity.getIgmng());

        resultObject.setBaseBo(processOrderBo);
        return resultObject;
    }
}

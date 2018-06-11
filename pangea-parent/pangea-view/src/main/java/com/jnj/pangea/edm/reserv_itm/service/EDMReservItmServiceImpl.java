package com.jnj.pangea.edm.reserv_itm.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.ProjectOneResbEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.reserv_itm.bo.EDMReservItmBo;
import com.jnj.pangea.util.DateUtils;

import java.util.Date;

public class EDMReservItmServiceImpl implements ICommonService {

    private static EDMReservItmServiceImpl instance;

    public static EDMReservItmServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMReservItmServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneResbEntity resbEntity = (ProjectOneResbEntity) o;
        LogUtil.getCoreLog().info("==============resbEntity================="+resbEntity);
        if (null == resbEntity) {
            return resultObject;
        }
        EDMReservItmBo reservItmBo = new EDMReservItmBo();

        //rule T1
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null!=edmSourceSystemV1Entity){
            reservItmBo.setSourceSysCd(edmSourceSystemV1Entity.getSourceSystem());
        }
        //rule T2
        Date dueDate = DateUtils.stringToDate(resbEntity.getBdter(), DateUtils.F_yyyyMMdd);
        reservItmBo.setRqmtDt(DateUtils.dateToString(dueDate, DateUtils.ISO_8602));

        reservItmBo.setRsrvtnNum(resbEntity.getRsnum());
        reservItmBo.setRsrvtnItmNum(resbEntity.getRspos());
        reservItmBo.setRsrvtnRcrdTypCd(resbEntity.getRsart());
        reservItmBo.setRsrvtnRqrmntTypCd(resbEntity.getBdart());
        reservItmBo.setMfgOrdrNum(resbEntity.getAufnr());
        reservItmBo.setBomItmNum(resbEntity.getPosnr());
        reservItmBo.setDelInd(resbEntity.getXloek());
        reservItmBo.setFinIssind(resbEntity.getKzear());
        reservItmBo.setMatlNum(resbEntity.getMatnr());
        reservItmBo.setPlntCd(resbEntity.getWerks());
        reservItmBo.setsLocCd(resbEntity.getLgort());
        reservItmBo.setBtchNum(resbEntity.getCharg());
        reservItmBo.setRqmtQty(resbEntity.getBdmng());
        reservItmBo.setDebCrdInd(resbEntity.getShkzg());
        reservItmBo.setQtyFxInd(resbEntity.getFmeng());
        reservItmBo.setWthdrnQty(resbEntity.getEnmng());
        reservItmBo.setEntryQty(resbEntity.getErfmg());
        reservItmBo.setEntryUomCd(resbEntity.getErfme());
        reservItmBo.setMfgPlanOrdrDocNum(resbEntity.getPlnum());
        reservItmBo.setPrNum(resbEntity.getBanfn());
        reservItmBo.setPrLineNbr(resbEntity.getBnfpo());
        reservItmBo.setSlsOrdrDocNum(resbEntity.getKdauf());
        reservItmBo.setSlsOrdrLineNbr(resbEntity.getKdpos());
        reservItmBo.setMvmtTyp(resbEntity.getBwart());
        reservItmBo.setRcvngIssngPlntCd(resbEntity.getUmwrk());
        reservItmBo.setRcvngIssngSLocCd(resbEntity.getUmlgo());
        reservItmBo.setItmCatCd(resbEntity.getPostp());
        reservItmBo.setCmpntScrapPct(resbEntity.getAusch());
        reservItmBo.setLeadTimeOffset(resbEntity.getNlfzt());
        reservItmBo.setFctrNmrtrMeas(resbEntity.getUmrez());

        LogUtil.getCoreLog().info("==============resbEntity.getUmrez()================="+resbEntity.getUmrez());
        LogUtil.getCoreLog().info("==============reservItmBo================="+reservItmBo.getFctrNmrtrMeas());

        reservItmBo.setFctrDnmntrMeas(resbEntity.getUmren());

        reservItmBo.setLnItmNbr(resbEntity.getAufps());
        reservItmBo.setPurchsOrdrNum(resbEntity.getEbeln());
        reservItmBo.setPoLineNbr(resbEntity.getEbelp());
        reservItmBo.setBckflushInd(resbEntity.getRgekz());
        reservItmBo.setCoProdInd(resbEntity.getKzkup());
        reservItmBo.setGrProcTime(resbEntity.getWebaz());
        reservItmBo.setVndNum(resbEntity.getLifnr());


        resultObject.setBaseBo(reservItmBo);
        return resultObject;
    }
}

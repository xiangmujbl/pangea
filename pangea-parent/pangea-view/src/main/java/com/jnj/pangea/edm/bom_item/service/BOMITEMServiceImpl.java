package com.jnj.pangea.edm.bom_item.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMProjectOneSTASDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProjectOneSTASEntity;
import com.jnj.pangea.common.entity.edm.EDMProjectOneSTPOEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.bom_item.bo.BOMITEMBo;
import com.jnj.pangea.util.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class BOMITEMServiceImpl implements ICommonService {
    private static BOMITEMServiceImpl instance;

    public static BOMITEMServiceImpl getInstance() {
        if (instance == null) {
            instance = new BOMITEMServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMProjectOneSTASDaoImpl edmProjectOneSTASDao = EDMProjectOneSTASDaoImpl.getInstance();



    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        BOMITEMBo bomitemBo=new BOMITEMBo();
        EDMProjectOneSTPOEntity edmProjectOneSTPOEntity=(EDMProjectOneSTPOEntity)o;
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        long count= edmProjectOneSTASDao.getEDMProjectOneSTASEntityCount(edmProjectOneSTPOEntity.getStlty(),edmProjectOneSTPOEntity.getStlnr(),edmProjectOneSTPOEntity.getStlkn());
        if(count==0){
            return resultObject;
        }else{
            bomitemBo.setBomCatCd(edmProjectOneSTPOEntity.getStlty());
        }
        if (null != sourceSystemV1Entity) {
            bomitemBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        bomitemBo.setBomNum(edmProjectOneSTPOEntity.getStlnr());
        bomitemBo.setBomItmNdeNum(edmProjectOneSTPOEntity.getStlkn());
        bomitemBo.setBomItmCntrNbr(edmProjectOneSTPOEntity.getStpoz());
        bomitemBo.setBomItmVldFromDt(edmProjectOneSTPOEntity.getDatuv());
        bomitemBo.setBomChgNum(edmProjectOneSTPOEntity.getAennr());
        bomitemBo.setBomDelInd(edmProjectOneSTPOEntity.getLkenz());
        bomitemBo.setBomPreItmNdeNum(edmProjectOneSTPOEntity.getVgknt());
        bomitemBo.setBomPreItmCntrNbr(edmProjectOneSTPOEntity.getVgpzl());
        bomitemBo.setCrtDttm(edmProjectOneSTPOEntity.getAndat());
        bomitemBo.setChgDttm(edmProjectOneSTPOEntity.getAedat());
        bomitemBo.setCmpntNum(edmProjectOneSTPOEntity.getIdnrk());
        bomitemBo.setBomItmCatCd(edmProjectOneSTPOEntity.getPostp());
        bomitemBo.setBomItmNum(edmProjectOneSTPOEntity.getPosnr());
        bomitemBo.setCmpntUomCd(edmProjectOneSTPOEntity.getMeins());
        bomitemBo.setCmpntQty(edmProjectOneSTPOEntity.getMenge());
        bomitemBo.setFxQtyInd(edmProjectOneSTPOEntity.getFmeng());
        bomitemBo.setCmpntScrapPct(edmProjectOneSTPOEntity.getAusch());
        bomitemBo.setBomItmBulkInd(edmProjectOneSTPOEntity.getSchgt());
        bomitemBo.setLeadTimeOffst(edmProjectOneSTPOEntity.getNlfzt());
        bomitemBo.setDstrbtnKeyCd(edmProjectOneSTPOEntity.getVerti());
        if(edmProjectOneSTPOEntity.getPotx1().isEmpty()&&edmProjectOneSTPOEntity.getPotx2().isEmpty()){
            bomitemBo.setBomItmTxt("");
        }else{
            bomitemBo.setBomItmTxt(edmProjectOneSTPOEntity.getPotx1()+" "+edmProjectOneSTPOEntity.getPotx2());
        }
        bomitemBo.setCoProdInd(edmProjectOneSTPOEntity.getKzkup());
        if(count==1){
            bomitemBo.setBomItmVldToDt(IConstant.BOM_ITEM.FIELD_MATLRTNGVALID_TO);
        }else{
           // maxRec
            EDMProjectOneSTASEntity edmProjectOneSTASEntity= edmProjectOneSTASDao.getEDMProjectOneSTASEntity(edmProjectOneSTPOEntity.getStlty(),edmProjectOneSTPOEntity.getStlnr(),edmProjectOneSTPOEntity.getStlkn());
            if(edmProjectOneSTASEntity==null||!edmProjectOneSTASEntity.getLkenz().equalsIgnoreCase(IConstant.BOM_ITEM.FIELD_LEKNZ_VALUE_X)){
                bomitemBo.setBomItmVldToDt(IConstant.BOM_ITEM.FIELD_MATLRTNGVALID_TO);
            }else{
                Date fromDueDate = DateUtils.stringToDate(edmProjectOneSTASEntity.getDatuv(), DateUtils.F_yyyyMMdd);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fromDueDate);
                calendar.add(Calendar.DATE, -1);
                String VldToDt = DateUtils.dateToString(calendar.getTime(), DateUtils.F_yyyyMMdd);
                bomitemBo.setBomItmVldToDt(VldToDt);
            }
        }
        bomitemBo.setAltItmGrpCd(edmProjectOneSTPOEntity.getAlpgr());
        bomitemBo.setAltItmPct(edmProjectOneSTPOEntity.getEwahr());
        bomitemBo.setAltItmInd(edmProjectOneSTPOEntity.getAlpos());
        bomitemBo.setAltItmStratCd(edmProjectOneSTPOEntity.getAlpst());
        bomitemBo.setAltItmRankNbr(edmProjectOneSTPOEntity.getAlprf());
        resultObject.setBaseBo(bomitemBo);
        return resultObject;
    }

}

package com.jnj.pangea.omp.gdm_bom_element.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMatlBomEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomItemEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMBomItemDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMBomHdrDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlProdVersnEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMatlProdVersnDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatlMfgRtngEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMatlMfgRtngDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgRtngItmNdeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgRtngItmDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_bom_element.bo.OMPGdmBomElementBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class OMPGdmBomElementServiceImpl implements ICommonService {

    private static OMPGdmBomElementServiceImpl instance;

    public static OMPGdmBomElementServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBomElementServiceImpl();
        }
        return instance;
    }

    private EDMMaterialPlantV1DaoImpl materialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
    private EDMBomItemDaoImpl bomItemDao = EDMBomItemDaoImpl.getInstance();
    private EDMBomHdrDaoImpl bomHdrDao = EDMBomHdrDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl matlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private EDMMatlMfgRtngDaoImpl matlMfgRtngDao = EDMMatlMfgRtngDaoImpl.getInstance();
    private EDMMfgRtngItmNdeDaoImpl mfgRtngItmNdeDao = EDMMfgRtngItmNdeDaoImpl.getInstance();
    private EDMMfgRtngItmDaoImpl mfgRtngItmDao = EDMMfgRtngItmDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;

        OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
        //J1
        List<EDMMatlProdVersnEntity> matlProdVersnList = matlProdVersnDao.getEntityWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());
        EDMBomItemEntity bomItemEntity=bomItemDao.getEntityWithConditions(matlBomEntity.getBomNum(),matlBomEntity.getSrcSysCd());
        EDMBomHdrEntity bomHdrEntity=bomHdrDao.getEntityWithConditions(matlBomEntity.getSrcSysCd(),matlBomEntity.getBomNum(),matlBomEntity.getAltBomNum(),bomItemEntity.getBomCatCd());
        EDMMaterialPlantV1Entity materialPlantV1Entity=materialPlantV1Dao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(matlBomEntity.getSrcSysCd(),matlBomEntity.getPlntCd(),matlBomEntity.getMatlNum());
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity_system_object=cnsPlanParameterDao.getEntityWithSourceSystemAndDataObject(matlBomEntity.getSrcSysCd(),IConstant.VALUE.SEND_TO_OMP);
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity_system_local_plant=cnsPlanParameterDao.getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(matlBomEntity.getSrcSysCd(),matlBomEntity.getMatlNum(),matlBomEntity.getPlntCd());
        if (matlProdVersnList != null && matlProdVersnList.size() > 0) {
            for (EDMMatlProdVersnEntity matlProdVersnEntity : matlProdVersnList) {
                List<EDMMatlMfgRtngEntity> mfgRtngEntityList = matlMfgRtngDao.getEntityWithFiveConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd(), matlProdVersnEntity.getRtngGrpCd(), matlProdVersnEntity.getRtngGrpCntrNum());
                if (mfgRtngEntityList != null && mfgRtngEntityList.size() > 0) {
                    for (EDMMatlMfgRtngEntity mfgRtngEntity : mfgRtngEntityList) {
                        List<EDMMfgRtngItmNdeEntity> rtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityWithConditions(mfgRtngEntity.getSrcSysCd(), mfgRtngEntity.getRtngTypCd(), mfgRtngEntity.getRntgGrpCntrNbr(), mfgRtngEntity.getRtngGrpCd());
                        if (rtngItmNdeEntityList != null && rtngItmNdeEntityList.size() > 0) {
                            for (EDMMfgRtngItmNdeEntity rtngItmNdeEntity : rtngItmNdeEntityList) {
                                List<EDMMfgRtngItmEntity> mfgRtngItmEntityList = mfgRtngItmDao.getEntityWithConditions(rtngItmNdeEntity.getSrcSysCd(), rtngItmNdeEntity.getRtngTypCd(), rtngItmNdeEntity.getRtngNdeNum(), rtngItmNdeEntity.getRtngGrpCd());
                                if (mfgRtngItmEntityList != null && mfgRtngItmEntityList.size() > 0) {
                                    for(EDMMfgRtngItmEntity mfgRtngItmEntity:mfgRtngItmEntityList){
                                        //T1
                                        String bomElementId=getBomElementId(matlProdVersnEntity,mfgRtngEntity,matlBomEntity,mfgRtngItmEntity,bomItemEntity);
                                        gdmBomElementBo.setBomElementId(bomElementId);
                                        //T2
                                        gdmBomElementBo.setActive(IConstant.VALUE.YES);
                                        gdmBomElementBo.setActiveFCTERP(IConstant.VALUE.YES);
                                        gdmBomElementBo.setActiveOPRERP(IConstant.VALUE.YES);
                                        //T3
                                        gdmBomElementBo.setActiveSOPERP(IConstant.VALUE.NO);
                                        //T4
                                        gdmBomElementBo.setBatchId(IConstant.VALUE.BLANK);
                                        gdmBomElementBo.setComment(IConstant.VALUE.BLANK);
                                        gdmBomElementBo.setErpFeedbackQuantity(IConstant.VALUE.BLANK);
                                        gdmBomElementBo.setOffsetCalendarId(IConstant.VALUE.BLANK);
                                        gdmBomElementBo.setOffsetPercentage(IConstant.VALUE.BLANK);
                                        gdmBomElementBo.setOffsetPercType(IConstant.VALUE.BLANK);
                                        //T5

                                    }
                                } else {
                                    return resultObject;
                                }
                            }
                        } else {
                            return resultObject;
                        }
                    }
                } else {
                    return resultObject;
                }
            }
        } else {
            return resultObject;
        }
        resultObject.setBaseBo(gdmBomElementBo);
        return resultObject;
    }

    /**
     * CONCATENATE  MATL_PROD_VERSN-prdntVrsnNum,
     * MATL_MFG_RTNG-matlNum,MATL_MFG_RTNG-plntCd
     * MATL_BOM-bomNum,MATL_BOM-altBomNum,MATL_BOM-bomUsgCd,
     * MFG_RTNG_ITM-operNum
     *
     * @param matlProdVersnEntity
     * @param matlBomEntity
     * @return
     */
    public String getBomElementId(EDMMatlProdVersnEntity matlProdVersnEntity,EDMMatlMfgRtngEntity mfgRtngEntity,EDMMatlBomEntity matlBomEntity,EDMMfgRtngItmEntity mfgRtngItmEntity,EDMBomItemEntity bomItemEntity){
        String bomElementId="";
        if(StringUtils.isNotBlank(matlProdVersnEntity.getPrdntVrsnNum())){
            bomElementId=bomElementId+matlProdVersnEntity.getPrdntVrsnNum()+"/";
        }
        if(StringUtils.isNotBlank(mfgRtngEntity.getMatlNum())){
            bomElementId=bomElementId+mfgRtngEntity.getMatlNum()+"/";
        }
        if(StringUtils.isNotBlank(mfgRtngEntity.getPlntCd())){
            bomElementId=bomElementId+mfgRtngEntity.getPlntCd()+"/";
        }
        if(StringUtils.isNotBlank(matlBomEntity.getBomNum())){
            bomElementId=bomElementId+matlBomEntity.getBomNum()+"/";
        }
        if(StringUtils.isNotBlank(matlBomEntity.getAltBomNum())){
            bomElementId=bomElementId+matlBomEntity.getAltBomNum()+"/";
        }
        if(StringUtils.isNotBlank(matlBomEntity.getBomUsgCd())){
            bomElementId=bomElementId+matlBomEntity.getBomUsgCd()+"/";
        }
        if(StringUtils.isNotBlank(mfgRtngItmEntity.getOperNum())){
            bomElementId=bomElementId+mfgRtngItmEntity.getOperNum()+"/";
        }
        if(StringUtils.isNotBlank(bomItemEntity.getCmpntNum())){
            bomElementId=bomElementId+bomItemEntity.getCmpntNum()+"/";
        }
        if(StringUtils.isNotBlank(bomItemEntity.getBomItmNum())){
            bomElementId=bomElementId+bomItemEntity.getBomItmNum()+"/";
        }
        if(StringUtils.isNotBlank(bomItemEntity.getBomItmVldFromDt())){
            Date fromDueDate = DateUtils.stringToDate(bomItemEntity.getBomItmVldFromDt(), DateUtils.DATE_FORMAT_1);
            String fromDate=DateUtils.dateToString(fromDueDate, DateUtils.F_yyyyMMdd);
            bomElementId=bomElementId+fromDate;
        }
        return null;
    }

}

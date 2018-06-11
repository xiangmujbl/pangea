package com.jnj.pangea.omp.gdm_bom.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMatlBomEntity;
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
import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMBomHdrDaoImpl;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_bom.bo.OMPGdmBomBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OMPGdmBomServiceImpl implements ICommonListService {

    private static OMPGdmBomServiceImpl instance;

    public static OMPGdmBomServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBomServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl matlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private EDMMatlMfgRtngDaoImpl matlMfgRtngDao = EDMMatlMfgRtngDaoImpl.getInstance();
    private EDMMfgRtngItmNdeDaoImpl mfgRtngItmNdeDao = EDMMfgRtngItmNdeDaoImpl.getInstance();
    private EDMMfgRtngItmDaoImpl mfgRtngItmDao = EDMMfgRtngItmDaoImpl.getInstance();
    private EDMBomHdrDaoImpl bomHdrDao = EDMBomHdrDaoImpl.getInstance();

    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<>();
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;

        List<OMPGdmBomBo> gdmBomBoList = checkT1(matlBomEntity);

        for (OMPGdmBomBo gdmBomBo : gdmBomBoList) {

            ResultObject resultObject = new ResultObject();

            gdmBomBo.setActive(IConstant.VALUE.YES);
            gdmBomBo.setActiveFCTERP(IConstant.VALUE.YES);
            gdmBomBo.setActiveOPRERP(IConstant.VALUE.YES);
            gdmBomBo.setActiveSOPERP(IConstant.VALUE.NO);

            String locationId = matlBomEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + matlBomEntity.getPlntCd();
            gdmBomBo.setLocationId(locationId);

            resultObject.setBaseBo(gdmBomBo);

            resultObjectList.add(resultObject);
        }

        return resultObjectList;
    }

    private List<OMPGdmBomBo> checkT1(EDMMatlBomEntity matlBomEntity) {

        List<OMPGdmBomBo> gdmBomBoList = new ArrayList<>();

        List<EDMMatlProdVersnEntity> matlProdVersnEntityList = matlProdVersnDao.getEntityListWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());

        for (EDMMatlProdVersnEntity matlProdVersnEntity : matlProdVersnEntityList) {

            List<PlanCnsPlanParameterEntity> planParameterEntityList = cnsPlanParameterDao.getEntityListWithSourceSystemAndDataObject(matlBomEntity.getSrcSysCd(), IConstant.VALUE.SEND_TO_OMP);

            for (PlanCnsPlanParameterEntity planParameterEntity : planParameterEntityList) {

                List<EDMMatlMfgRtngEntity> matlMfgRtngEntityList = matlMfgRtngDao.getEntityWithThreeConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd());

                for (EDMMatlMfgRtngEntity matlMfgRtngEntity : matlMfgRtngEntityList) {

                    List<EDMMfgRtngItmNdeEntity> mfgRtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityListWithConditions(matlMfgRtngEntity.getSrcSysCd(), matlMfgRtngEntity.getRtngTypCd(), matlMfgRtngEntity.getRntgGrpCd(), matlMfgRtngEntity.getRntgGrpCntrNbr());

                    for (EDMMfgRtngItmNdeEntity mfgRtngItmNdeEntity : mfgRtngItmNdeEntityList) {

                        List<EDMMfgRtngItmEntity> mfgRtngItmEntityList = mfgRtngItmDao.getEntityListWithConditions(mfgRtngItmNdeEntity.getSrcSysCd(), mfgRtngItmNdeEntity.getRtngTypCd(), mfgRtngItmNdeEntity.getRtngNdeNum(), mfgRtngItmNdeEntity.getRtngGrpCd());

                        for (EDMMfgRtngItmEntity mfgRtngItmEntity : mfgRtngItmEntityList) {
                            OMPGdmBomBo gdmBomBo = new OMPGdmBomBo();

                            try{
                                Integer matlNum = Integer.parseInt(StringUtils.trim(matlMfgRtngEntity.getMatlNum()));
                                Integer bomNum = Integer.parseInt(StringUtils.trim(matlBomEntity.getBomNum()));
                                Integer altBomNum = Integer.parseInt(StringUtils.trim(matlBomEntity.getAltBomNum()));
                                Integer bomUsgCd = Integer.parseInt(StringUtils.trim(matlBomEntity.getBomUsgCd()));
                                Integer operNum = Integer.parseInt(StringUtils.trim(mfgRtngItmEntity.getOperNum()));

                                String bomId = matlProdVersnEntity.getPrdntVrsnNum() + IConstant.VALUE.BACK_SLANT + planParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE +
                                        matlNum + IConstant.VALUE.BACK_SLANT + matlMfgRtngEntity.getPlntCd() + IConstant.VALUE.BACK_SLANT +
                                        bomNum + IConstant.VALUE.BACK_SLANT + altBomNum + IConstant.VALUE.BACK_SLANT +
                                        bomUsgCd + IConstant.VALUE.BACK_SLANT + operNum;
                                gdmBomBo.setBomId(bomId);
                            }catch (NumberFormatException e){
                                LogUtil.getCoreLog().info(e);
                            }

                            EDMBomHdrEntity bomHdrEntity = bomHdrDao.getEntityWithFiveConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), IConstant.VALUE.M);

                            if (null != bomHdrEntity){

                                String endEff = checkEndEff(bomHdrEntity.getBomVld_ToDt(), matlProdVersnEntity.getValToDt());
                                String startEff = checkStartEff(bomHdrEntity.getBomVldFromDt(), matlProdVersnEntity.getValToDt());

                                gdmBomBo.setEndEff(endEff);
                                gdmBomBo.setStartEff(startEff);
                            }

                            gdmBomBoList.add(gdmBomBo);

                        }

                    }
                }
            }
        }

        return gdmBomBoList;
    }

    private String checkEndEff(String bomVld_ToDt, String valToDt) {
        try{
            Date bomVld_ToDtFormat = DateUtils.stringToDate(bomVld_ToDt, DateUtils.yyyy_MM_dd);
            Date valToDtFormat = DateUtils.stringToDate(valToDt, DateUtils.F_yyyyMMdd);
            if (bomVld_ToDtFormat.getTime() >= valToDtFormat.getTime()) {
                return DateUtils.dateToString(valToDtFormat, DateUtils.J_yyyyMMdd_HHmmss);
            } else {
                return DateUtils.dateToString(bomVld_ToDtFormat, DateUtils.J_yyyyMMdd_HHmmss);
            }
        }catch (Exception e){
            LogUtil.getCoreLog().info(e);
            return null;
        }
    }

    private String checkStartEff(String bomVldFromDt, String valToDt) {
        Date bomVldFromDtFormat = DateUtils.stringToDate(bomVldFromDt, DateUtils.yyyy_MM_dd);
        Date valToDtFormat = DateUtils.stringToDate(valToDt, DateUtils.F_yyyyMMdd);
        if (bomVldFromDtFormat.getTime() >= valToDtFormat.getTime()) {
            return DateUtils.dateToString(bomVldFromDtFormat, DateUtils.J_yyyyMMdd_HHmmss);
        } else {
            return DateUtils.dateToString(valToDtFormat, DateUtils.J_yyyyMMdd_HHmmss);
        }
    }
}

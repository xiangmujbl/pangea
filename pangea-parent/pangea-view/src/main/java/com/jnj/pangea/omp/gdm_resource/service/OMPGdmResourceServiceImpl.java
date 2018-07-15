package com.jnj.pangea.omp.gdm_resource.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCapyHdrEntity;
import com.jnj.pangea.common.entity.edm.EDMWrkCtrEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMWrkCtrDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_resource.bo.OMPGdmResourceBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class OMPGdmResourceServiceImpl implements ICommonService {

    private static OMPGdmResourceServiceImpl instance;

    public static OMPGdmResourceServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmResourceServiceImpl();
        }
        return instance;
    }

    private EDMWrkCtrDaoImpl wrkCtrDao = EDMWrkCtrDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMCapyHdrEntity capyHdrEntity = (EDMCapyHdrEntity) o;

        OMPGdmResourceBo gdmResourceBo = new OMPGdmResourceBo();

        //rule J1,C1
        if (null != capyHdrEntity) {
            String src = capyHdrEntity.getSrcSysCd();
            String num = capyHdrEntity.getCapyNum();
            String capyCatCd = capyHdrEntity.getCapyCatCd();
            if (StringUtils.isNotEmpty(src) && StringUtils.isNotEmpty(num)) {
                EDMWrkCtrEntity edmWrkCtrEntity = wrkCtrDao.getEntityWithSrcSysCdAndCapyNum(src, num);
                if (null != edmWrkCtrEntity ) {
                    String usgcd = edmWrkCtrEntity.getWrkCtrUsgCd();
                    if (IConstant.VALUE.STR_TWO.equals(capyCatCd)){
                        if (IConstant.VALUE.STR_ONE.equals(usgcd) || IConstant.VALUE.STR_NINE.equals(usgcd)) {
                            String srcSysCd = edmWrkCtrEntity.getSrcSysCd();
                        if (StringUtils.isNotEmpty(srcSysCd)) {
                            PlanCnsPlanParameterEntity planCnsPlanParameterEntity = cnsPlanParameterDao.getEntityWithSourceSystem(srcSysCd);
                            if (null != planCnsPlanParameterEntity) {
                                            gdmResourceBo.setResourceId(capyHdrEntity.getSrcSysCd()+IConstant.VALUE.BACK_SLANT+capyHdrEntity.getPlntCd() + IConstant.VALUE.BACK_SLANT + edmWrkCtrEntity.getWrkCtrCd() + IConstant.VALUE.BACK_SLANT + capyHdrEntity.getCapyCatCd());
                                        }else {
                                            return resultObject;
                                        }
                            }else {
                                return resultObject;
                            }
                        }else {
                            return resultObject;
                        }
                    }else {
                        return resultObject;
                    }
                }else {
                    return resultObject;
                }
            }else {
                return resultObject;
            }
        } else {
            return resultObject;
        }

        //rule T1,T2,T3
        gdmResourceBo.setActive(IConstant.VALUE.YES);
        gdmResourceBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmResourceBo.setActiveSOPERP(IConstant.VALUE.NO);

        //rule T4
        if (null != capyHdrEntity) {
            String desc = capyHdrEntity.getCapyDesc();
            if (StringUtils.isNotEmpty(desc)) {
                String[] descT = desc.split("/");
                if (descT.length>0){
                    String plntcd = capyHdrEntity.getPlntCd();
                    if (StringUtils.isNotEmpty(plntcd)) {
                        if (StringUtils.isNotEmpty(descT[0])) {
                            gdmResourceBo.setDescription(descT[0]);
                        } else{
                            if (plntcd.startsWith(IConstant.VALUE.BR)) {
                                if (StringUtils.isNotEmpty(descT[2])) {
                                    gdmResourceBo.setDescription(descT[2]);
                                }
                            } else if (!plntcd.startsWith(IConstant.VALUE.BR)) {
                                if (StringUtils.isNotEmpty(descT[1])) {
                                    gdmResourceBo.setDescription(descT[1]);
                                }

                            }
                        }
                    }
                }
            } else {
                gdmResourceBo.setDescription(IConstant.VALUE.BLANK);
            }
        }
        //rule C2
        if (null != capyHdrEntity) {
            String plntcd = capyHdrEntity.getPlntCd();
            if (StringUtils.isNotEmpty(plntcd)) {
                gdmResourceBo.setLocationId(capyHdrEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + capyHdrEntity.getPlntCd());
            } else {
                //ResultObject resultObject = new ResultObject();
                String errorValue = "Plant Id not found";
                String errorCode = IConstant.FAILED.ERROR_CODE.C2;
                FailData failData = new FailData();
                failData.setErrorCode(errorCode);
                failData.setErrorValue(errorValue);
                failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.PP);
                failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.GDM_RESOURCE);
                failData.setSourceSystem(IConstant.VALUE.OMP);
                failData.setKey1(capyHdrEntity.getSrcSysCd());
                failData.setKey2(capyHdrEntity.getCapyNum());
                failData.setKey3("");
                failData.setKey4("");
                failData.setKey5("");
                resultObject.setFailData(failData);
                return resultObject;
               // return failData;
            }
        }
        resultObject.setBaseBo(gdmResourceBo);
        return resultObject;
    }

//    public ResultObject getFailDate(String errorCode, String errorValue, EDMCapyHdrEntity capyHdrEntity) {
//        ResultObject resultObject = new ResultObject();
//        FailData failData = new FailData();
//        failData.setErrorCode(errorCode);
//        failData.setErrorValue(errorValue);
//        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.PP);
//        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.GDM_RESOURCE);
//        failData.setSourceSystem(IConstant.VALUE.OMP);
//        failData.setKey1(capyHdrEntity.getSrcSysCd());
//        failData.setKey2(capyHdrEntity.getCapyNum());
//        failData.setKey3("");
//        failData.setKey4("");
//        failData.setKey5("");
//        resultObject.setFailData(failData);
//        return resultObject;
//    }
    public  static  void aVoid(){
               String a ="/PLANIFICADOR DE MANTENIMIETNO/";
               String[] aa = a.split("/");
               System.out.println(aa[0]+"====1======"+aa[1]+"=======2======="+aa[2]);
    }

}

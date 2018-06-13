package com.jnj.pangea.edm.mfg_rtng_itm_v2.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlasDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOnePlpoDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.PlpoEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.mfg_rtng_itm_v2.bo.EDMMfgRtngItmV2Bo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMfgRtngItmV2ServiceImpl implements ICommonService {

    private static EDMMfgRtngItmV2ServiceImpl instance;

    public static EDMMfgRtngItmV2ServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmV2ServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    private ProjectOnePlasDaoImpl plasDao = ProjectOnePlasDaoImpl.getInstance();

    private ProjectOnePlpoDaoImpl plpoDao = ProjectOnePlpoDaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlpoEntity plpoEntity = (PlpoEntity) o;

        EDMMfgRtngItmV2Bo mfgRtngItmBo = new EDMMfgRtngItmV2Bo();
        //T1
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (entityWithLocalSourceSystem != null) {
            mfgRtngItmBo.setSrcSysCd(entityWithLocalSourceSystem.getSourceSystem());
        }
        //J1 F1
        List<ProjectOnePlasEntity> plasEntityList = plasDao.getEntityWithPlntyAndPlnnrAndPlnkn(plpoEntity.getPlnty(), plpoEntity.getPlnnr(), plpoEntity.getPlnkn());
        if (plasEntityList == null || plasEntityList.size() == 0) {
            return resultObject;
        } else {
                if (plpoEntity.getPlnty().equals(IConstant.VALUE.N) || plpoEntity.getPlnty().equals(IConstant.VALUE.TWO)) {
                    mfgRtngItmBo.setRtngTypCd(plpoEntity.getPlnty());
                }else{
                    return resultObject;
                }


        }
        mfgRtngItmBo.setRtngGrpCd(plpoEntity.getPlnnr());
        mfgRtngItmBo.setRtngItmNum(plpoEntity.getPlnkn());
        mfgRtngItmBo.setRtngItmVersnCntrNbr(plpoEntity.getZaehl());
        mfgRtngItmBo.setValFromDt(plpoEntity.getDatuv());
        mfgRtngItmBo.setChgNum(plpoEntity.getAennr());
        mfgRtngItmBo.setDelInd(plpoEntity.getLoekz());
        mfgRtngItmBo.setCrtDttm(plpoEntity.getAndat());
        mfgRtngItmBo.setChgDttm(plpoEntity.getAedat());
        mfgRtngItmBo.setRtngSupNdeNum(plpoEntity.getSumnr());
        mfgRtngItmBo.setOperNum(plpoEntity.getVornr());
        mfgRtngItmBo.setOperCd(plpoEntity.getSteus());
        mfgRtngItmBo.setWrkCntrCd(plpoEntity.getArbid());
        mfgRtngItmBo.setPlntCd(plpoEntity.getWerks());
        mfgRtngItmBo.setOperDesc(plpoEntity.getLtxa1());
        mfgRtngItmBo.setOperUom(plpoEntity.getMeinh());
        mfgRtngItmBo.setBsQty(plpoEntity.getBmsch());
        mfgRtngItmBo.setAct1Cd(plpoEntity.getLar01());
        mfgRtngItmBo.setAct1UomCd(plpoEntity.getVge01());
        mfgRtngItmBo.setAct1Qty(plpoEntity.getVgw01());
        mfgRtngItmBo.setAct2Cd(plpoEntity.getLar02());
        mfgRtngItmBo.setAct2UomCd(plpoEntity.getVge02());
        mfgRtngItmBo.setAct2Qty(plpoEntity.getVgw02());
        mfgRtngItmBo.setAct3Cd(plpoEntity.getLar03());
        mfgRtngItmBo.setAct3UomCd(plpoEntity.getVge03());
        mfgRtngItmBo.setAct3Qty(plpoEntity.getVgw03());
        mfgRtngItmBo.setAct4Cd(plpoEntity.getLar04());
        mfgRtngItmBo.setAct4UomCd(plpoEntity.getVge04());
        mfgRtngItmBo.setAct4Qty(plpoEntity.getVgw04());
        mfgRtngItmBo.setAct5Cd(plpoEntity.getLar05());
        mfgRtngItmBo.setAct5UomCd(plpoEntity.getVge05());
        mfgRtngItmBo.setAct5Qty(plpoEntity.getVgw05());
        mfgRtngItmBo.setAct6Cd(plpoEntity.getLar06());
        mfgRtngItmBo.setAct6UomCd(plpoEntity.getVge06());
        mfgRtngItmBo.setAct6Qty(plpoEntity.getVgw06());
        mfgRtngItmBo.setOperDurtnQty(plpoEntity.getDauno());
        mfgRtngItmBo.setOperDurtnUomCd(plpoEntity.getDaune());
        mfgRtngItmBo.setMinOperDurtnQty(plpoEntity.getDaumi());
        mfgRtngItmBo.setMinOperDurtnUomCd(plpoEntity.getDaume());
        mfgRtngItmBo.setPhsInd(plpoEntity.getPhflg());
        mfgRtngItmBo.setAttr1Desc(plpoEntity.getUsr00());
        mfgRtngItmBo.setAttr2Desc(plpoEntity.getUsr01());
        mfgRtngItmBo.setAttr3Desc(plpoEntity.getUsr02());
        mfgRtngItmBo.setAttr4Desc(plpoEntity.getUsr03());
        mfgRtngItmBo.setAttr5Qty(plpoEntity.getUsr04());
        mfgRtngItmBo.setAttr11Ind(plpoEntity.getUsr10());
        //T2
        String zaehl = plpoEntity.getZaehl();
        List<PlpoEntity> plpoEntityList = plpoDao.getEntityWithPlntyAndPlnnrAndPlnkn(plpoEntity.getPlnty(), plpoEntity.getPlnnr(), plpoEntity.getPlnkn());
        if (plpoEntityList != null && plpoEntityList.size() > 0) {
            if (plpoEntityList.size() == 1) {
                List<ProjectOnePlasEntity> plasEntityList1 = plasDao.getEntityWithPlntyAndPlnnrAndPlnkn(plpoEntity.getPlnty(), plpoEntity.getPlnnr(), plpoEntity.getPlnkn());
                if (plasEntityList1 != null && plasEntityList1.size() > 0) {
                    if (plasEntityList1.size() == 1) {
                        mfgRtngItmBo.setRtgItemEndDate(IConstant.VALUE.BOM_VlD_ToDt);
                    } else if (plasEntityList1.size() > 1) {
                        for (ProjectOnePlasEntity plasEntity : plasEntityList1) {
                            if (plasEntity.getLoekz().equals(IConstant.VALUE.X)) {
                                mfgRtngItmBo.setRtgItemEndDate(plasEntity.getDatuv());
                                break;
                            } else if (StringUtils.isBlank(plasEntity.getLoekz())) {
                                continue;
                            }
                        }
                    }
                }
            } else if (plpoEntityList.size() > 1) {
                if ((plpoEntityList.get(plpoEntityList.size() - 1).getZaehl().equals(zaehl))) {
                    mfgRtngItmBo.setRtgItemEndDate(IConstant.VALUE.BOM_VlD_ToDt);
                }
                for (PlpoEntity pp : plpoEntityList) {
                    if (Integer.parseInt(pp.getZaehl()) > Integer.parseInt(zaehl)) {
                        mfgRtngItmBo.setRtgItemEndDate(pp.getDatuv());
                        break;
                    }
                }
            }
        }
        resultObject.setBaseBo(mfgRtngItmBo);

        return resultObject;
    }
}

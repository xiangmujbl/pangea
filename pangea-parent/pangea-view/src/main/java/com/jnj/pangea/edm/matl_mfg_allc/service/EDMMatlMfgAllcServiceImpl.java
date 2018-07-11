package com.jnj.pangea.edm.matl_mfg_allc.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.PlmzDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.PlmzEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_mfg_allc.bo.EDMMatlMfgAllcBo;

import java.util.ArrayList;
import java.util.List;

public class EDMMatlMfgAllcServiceImpl implements ICommonService {

    private static EDMMatlMfgAllcServiceImpl instance;

    public static EDMMatlMfgAllcServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlMfgAllcServiceImpl();
        }
        return instance;
    }


    List<String> combinationList = new ArrayList<>();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlmzDaoImpl plmzDao = PlmzDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Object o2) {
        PlmzEntity plmzEntity = (PlmzEntity) o;
        ResultObject resultObject = new ResultObject();
        EDMMatlMfgAllcBo matlMfgAllcBo = new EDMMatlMfgAllcBo();
        if (plmzEntity == null) {
            return resultObject;
        }
        String plnty = plmzEntity.getPlnty();
        String plnnr = plmzEntity.getPlnnr();
        String plnal = plmzEntity.getPlnal();
        String zuonr = plmzEntity.getZuonr();
        String zaehl = plmzEntity.getZaehl();
        String combination = plnty + plnnr + plnal + zuonr;
        boolean isExist = combinationList.contains(combination);
        // if exist skip
        if (isExist) {
            return resultObject;
        }
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem("project_one");
        if (sourceSystemV1Entity != null) {
            matlMfgAllcBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        List<PlmzEntity> plmzEntityList = plmzDao.getEntityByPlntyPlnnrPlnalZuonr(plnty, plnnr, plnal, zuonr);
        if (plmzEntityList == null || plmzEntityList.size() <= 0) {
            return resultObject;
        }
        matlMfgAllcBo.setRtngTypCd(plmzEntity.getPlnty());
        matlMfgAllcBo.setRtngGrpCd(plmzEntity.getPlnnr());
        matlMfgAllcBo.setAllocNum(plmzEntity.getZuonr());
        if (plmzEntityList.size() == 1) {
            matlMfgAllcBo.setAllocCount(plmzEntity.getZaehl());
            matlMfgAllcBo.setChgNum(plmzEntity.getAennr());
            matlMfgAllcBo.setAllocValidTo(IConstant.PROJECT_ONE_PLMZ.ALLOCVALIDTO);
        } else {
            if(!isMin(zaehl,plmzEntityList)){
                return resultObject;
            }
            // get lowest zaehl
            matlMfgAllcBo.setAllocCount(plmzEntityList.get(0).getZaehl());
            // get highest zaehl's aennr
            matlMfgAllcBo.setChgNum(plmzEntityList.get(plmzEntityList.size() - 1).getAennr());
            //get next zaehl's datuv
            String allocValidTo = getAllocValidTo(zaehl,plmzEntityList);
            matlMfgAllcBo.setAllocValidTo(allocValidTo);
        }
        matlMfgAllcBo.setAllocValidFrom(plmzEntity.getDatuv());
        matlMfgAllcBo.setDelInd(plmzEntity.getLoekz());
        matlMfgAllcBo.setRtngGrpCntrNbr(plmzEntity.getPlnal());
        matlMfgAllcBo.setRtngSqncNum(plmzEntity.getPlnfl());
        matlMfgAllcBo.setRtngNdeNum(plmzEntity.getPlnkn());
        matlMfgAllcBo.setBomCatCd(plmzEntity.getStlty());
        matlMfgAllcBo.setBomNum(plmzEntity.getStlnr());
        matlMfgAllcBo.setAltBomNum(plmzEntity.getStlal());
        matlMfgAllcBo.setBomItmNdeNum(plmzEntity.getStlkn());
        matlMfgAllcBo.setPlntCd(plmzEntity.getWerk_stl());
        resultObject.setBaseBo(matlMfgAllcBo);
        combinationList.add(plnty + plnnr + plnal + zuonr);
        return resultObject;
    }

    public String getAllocValidTo(String zaehl, List<PlmzEntity> plmzEntityList) {
        List<PlmzEntity> plmzEntityList_clone = new ArrayList<>(plmzEntityList);
        List<PlmzEntity> removeXList = removeX(plmzEntityList);
        String allocValidTo="";
        if(removeXList!=null && removeXList.size()>0){
            String minZaehl = removeXList.get(0).getZaehl();
            for(PlmzEntity entity:plmzEntityList_clone){
                if(Integer.parseInt(cleanZero(minZaehl))<Integer.parseInt(entity.getZaehl())){
                    allocValidTo = entity.getDatuv();
                    break;
                }else{
                    continue;
                }
            }
        }
        return allocValidTo;
    }

    public boolean isMin(String zaehl,List<PlmzEntity> plmzEntityList){
        List<PlmzEntity> plmzEntityList_clone = new ArrayList<>(plmzEntityList);
        List<PlmzEntity> removeXList = removeX(plmzEntityList_clone);
        String minZaehl = removeXList.get(0).getZaehl();
        if(zaehl.equals(minZaehl)){
            return true;
        }
        return false;
    }

    public List<PlmzEntity> removeX(List<PlmzEntity> plmzEntityList){
        for(int i=0;i<plmzEntityList.size();i++){
            if(IConstant.VALUE.X.equals(plmzEntityList.get(i).getLoekz())){
                plmzEntityList.remove(i);
            }
        }
        return plmzEntityList;
    }
    public String cleanZero(String str) {
        String newStr = str.replaceAll("^(0+)", "");
        return newStr;
    }
}
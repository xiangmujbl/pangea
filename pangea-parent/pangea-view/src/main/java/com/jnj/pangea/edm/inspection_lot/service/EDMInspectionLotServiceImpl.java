package com.jnj.pangea.edm.inspection_lot.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjcetOneQaveDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneJestDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTj02tDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.JestEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOneQalsEntity;
import com.jnj.pangea.common.entity.project_one.QaveEntity;
import com.jnj.pangea.common.entity.project_one.Tj02tEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.inspection_lot.bo.EDMInspectionLotBo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EDMInspectionLotServiceImpl implements ICommonService {

    private static EDMInspectionLotServiceImpl instance;

    public static EDMInspectionLotServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMInspectionLotServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl edmSourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    private ProjcetOneQaveDaoImpl projcetOneQaveDao = ProjcetOneQaveDaoImpl.getInstance();

    private  ProjectOneJestDaoImpl projectOneJestDao = ProjectOneJestDaoImpl.getInstance();

    private ProjectOneTj02tDaoImpl projectOneTj02tDao = ProjectOneTj02tDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ProjectOneQalsEntity projectOneQalsEntity  = (ProjectOneQalsEntity)o;
       // LogUtil.getCoreLog().info("==========================buildView start=========");
        ResultObject resultObject = new ResultObject();
        EDMInspectionLotBo edmInspectionLotV1Bo = new EDMInspectionLotBo();
        EDMSourceSystemV1Entity sourceEntity = getSourceEntity();
        edmInspectionLotV1Bo.setSrcSysCd(sourceEntity.getSourceSystem());
        edmInspectionLotV1Bo.setLotNum(projectOneQalsEntity.getPrueflos());
        edmInspectionLotV1Bo.setPlntCd(projectOneQalsEntity.getWerk());
        edmInspectionLotV1Bo.setMatlId(projectOneQalsEntity.getMatnr());
        edmInspectionLotV1Bo.setBaseUom(projectOneQalsEntity.getMengeneinh());
        edmInspectionLotV1Bo.setLotVerifTypeCd(projectOneQalsEntity.getArt());
        edmInspectionLotV1Bo.setLotOrigCd(projectOneQalsEntity.getHerkunft());
        edmInspectionLotV1Bo.setLocalObjectNumber(projectOneQalsEntity.getObjnr());
        //N1
        if(projectOneQalsEntity.getStat35().equals("")){
            edmInspectionLotV1Bo.setStsPrfl(projectOneQalsEntity.getStsma());
        }
        edmInspectionLotV1Bo.setUsgDecInd(projectOneQalsEntity.getStat35());
        //N5
        String date = getDate(projectOneQalsEntity.getEnstehdat());
        edmInspectionLotV1Bo.setLocalDateOfLotCreation(date);
        edmInspectionLotV1Bo.setLocalTimeOfLotCreation(projectOneQalsEntity.getEntstezeit());
        edmInspectionLotV1Bo.setCrtDttm(projectOneQalsEntity.getErsteldat());
        edmInspectionLotV1Bo.setChgDttm(projectOneQalsEntity.getAenderdat());
        String date1 = getDate(projectOneQalsEntity.getPastrterm());
        edmInspectionLotV1Bo.setInspStrtDt(date1);
        edmInspectionLotV1Bo.setInspStrtTm(projectOneQalsEntity.getPastrzeit());
        edmInspectionLotV1Bo.setInspEndDt(date1);
        edmInspectionLotV1Bo.setInspEndTm(projectOneQalsEntity.getPaendzeit());
        edmInspectionLotV1Bo.setCstmNum(projectOneQalsEntity.getKunnr());
        edmInspectionLotV1Bo.setVndrNum(projectOneQalsEntity.getLifnr());
        edmInspectionLotV1Bo.setBtchNum(projectOneQalsEntity.getCharg());
        edmInspectionLotV1Bo.setStgLocCd(projectOneQalsEntity.getLagortchrg());
        edmInspectionLotV1Bo.setMfgOrdrDoc(projectOneQalsEntity.getAufnr());
        edmInspectionLotV1Bo.setPoDocNum(projectOneQalsEntity.getEbeln());
        edmInspectionLotV1Bo.setPoDocLineNbr(projectOneQalsEntity.getEbelp());
        edmInspectionLotV1Bo.setMatlMvmtDocYr(projectOneQalsEntity.getMjahr());
        edmInspectionLotV1Bo.setMatlMvmtDocNum(projectOneQalsEntity.getMblnr());
        edmInspectionLotV1Bo.setStckPlntCd(projectOneQalsEntity.getWerkvorg());
        edmInspectionLotV1Bo.setStckSLocCd(projectOneQalsEntity.getLagortvorg());
        edmInspectionLotV1Bo.setInspLotQty(projectOneQalsEntity.getLosmenge());
        edmInspectionLotV1Bo.setToBePostedQty(projectOneQalsEntity.getLmengezub());
        //N2
        if(!projectOneQalsEntity.getLmengezub().equals("")){
            edmInspectionLotV1Bo.setActlLotQty(projectOneQalsEntity.getLmengeist());
        }
        //N4
        QaveEntity qaveEntity = projcetOneQaveDao.getQaveEntity(projectOneQalsEntity.getPrueflos());
        if(qaveEntity!=null){
            edmInspectionLotV1Bo.setUsgDcsnCd(qaveEntity.getVcode());
            String date3 = getDate(qaveEntity.getVaedatum());
            edmInspectionLotV1Bo.setQcDcsnDttm(date3);
        }
        String objnr = getStringNumber(projectOneQalsEntity.getPrueflos());
        String s = getqcStsCd(objnr);
        if(!s.equals("")&&s!=null){
            edmInspectionLotV1Bo.setQcStsCd(s);
        }
        resultObject.setBaseBo(edmInspectionLotV1Bo);
        return resultObject;
    }
    public EDMSourceSystemV1Entity  getSourceEntity(){
        EDMSourceSystemV1Entity entityWithLocalSourceSystem = edmSourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        return  entityWithLocalSourceSystem;
    }

    public String getStringNumber(String number) {
        String [] str = {"0","0","0","0","0","0","0","0","0","0","0","0"};
        String QL = "QL";
        StringBuffer sb = new StringBuffer();
        sb.append(QL);
        for (int i = 0; i < str.length-number.length(); i++) {
            String s = str[i];
            sb.append(s);
        }
        sb.append(number);
        return sb.toString();
    }
    public String getqcStsCd(String objnr){
        StringBuffer sb = new StringBuffer();
        List<JestEntity> entityWithObjnr = projectOneJestDao.getEntityByObjnr(objnr);
        if(null!=entityWithObjnr){
        for (JestEntity jestEntity : entityWithObjnr) {
            String stat = jestEntity.getStat();
            List<Tj02tEntity> entityWithStat = projectOneTj02tDao.getEntityWithStat(stat);
            if(null!=entityWithStat){
            for (Tj02tEntity tj02tEntity : entityWithStat) {
                if(sb.length()==0||sb.length()==entityWithStat.size()-1){
                        sb.append(tj02tEntity.getTxt04());
                    }else{
                        sb.append(" ");
                        sb.append(tj02tEntity.getTxt04());
                    }
                }
            }
        }
        }
        return sb.toString();
    }
    public  String getDate (String time)  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        try{
            Date newDate= formatter.parse(time);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            String format = formatter.format(newDate);
            return format;
        }catch (Exception e){
            return null;
        }
    }




}

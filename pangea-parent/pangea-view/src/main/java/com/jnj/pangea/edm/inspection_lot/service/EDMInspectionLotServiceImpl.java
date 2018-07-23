package com.jnj.pangea.edm.inspection_lot.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneQaveDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneJestDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneTj02tDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.JestEntity;
import com.jnj.pangea.common.entity.project_one.ProjectOneQalsEntity;
import com.jnj.pangea.common.entity.project_one.QaveEntity;
import com.jnj.pangea.common.entity.project_one.Tj02tEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.inspection_lot.bo.EDMInspectionLotBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang.StringUtils;
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

    private ProjectOneQaveDaoImpl projcetOneQaveDao = ProjectOneQaveDaoImpl.getInstance();

    private  ProjectOneJestDaoImpl projectOneJestDao = ProjectOneJestDaoImpl.getInstance();

    private ProjectOneTj02tDaoImpl projectOneTj02tDao = ProjectOneTj02tDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ProjectOneQalsEntity projectOneQalsEntity  = (ProjectOneQalsEntity)o;
        ResultObject resultObject = new ResultObject();
        EDMInspectionLotBo edmInspectionLotV1Bo = new EDMInspectionLotBo();
        //N6
        boolean empty8 = StringUtils.isEmpty(projectOneQalsEntity.getAenderdat());
        boolean empty9 = StringUtils.isEmpty(projectOneQalsEntity.getAenderzeit());
        if(!empty8 && !empty9){
            if(!projectOneQalsEntity.getAenderdat().equals("00000000")){
                String concat = projectOneQalsEntity.getAenderdat().concat(projectOneQalsEntity.getAenderzeit());
                Date dueDate1 = DateUtils.stringToDate(concat, DateUtils.F_yyyyMMddHHmmss);
                String ChgDttm = DateUtils.dateToString(dueDate1, DateUtils.ISO_8602);
                edmInspectionLotV1Bo.setChgDttm(ChgDttm);
            }
        }
        boolean empty10 = StringUtils.isEmpty(projectOneQalsEntity.getErsteldat());
        boolean empty11 = StringUtils.isEmpty(projectOneQalsEntity.getErstelzeit());
        if(!empty10 && !empty11){
            if(!projectOneQalsEntity.getErsteldat().equals("00000000")){
                String concat = projectOneQalsEntity.getErsteldat().concat(projectOneQalsEntity.getErstelzeit());
                Date dueDate = DateUtils.stringToDate(concat, DateUtils.F_yyyyMMddHHmmss);
                String CrtDttm = DateUtils.dateToString(dueDate, DateUtils.ISO_8602);
                edmInspectionLotV1Bo.setCrtDttm(CrtDttm);
            }

        }
        //N1
        boolean empty = StringUtils.isEmpty(projectOneQalsEntity.getStat35());
        boolean empty1 = StringUtils.isEmpty(projectOneQalsEntity.getLmengezub());
        if(!empty||empty1){
            return null;
        }

        EDMSourceSystemV1Entity sourceEntity = getSourceEntity();
        edmInspectionLotV1Bo.setSrcSysCd(sourceEntity.getSourceSystem());
        edmInspectionLotV1Bo.setLotNum(projectOneQalsEntity.getPrueflos());
        edmInspectionLotV1Bo.setplntCd(projectOneQalsEntity.getWerk());
        edmInspectionLotV1Bo.setMatlId(projectOneQalsEntity.getMatnr());
        edmInspectionLotV1Bo.setBaseUom(projectOneQalsEntity.getMengeneinh());
        edmInspectionLotV1Bo.setLotVerifTypeCd(projectOneQalsEntity.getArt());
        edmInspectionLotV1Bo.setLotOrigCd(projectOneQalsEntity.getHerkunft());
        edmInspectionLotV1Bo.setLocalObjectNumber(projectOneQalsEntity.getObjnr());
        //N1
        edmInspectionLotV1Bo.setStsPrfl(projectOneQalsEntity.getStsma());
        edmInspectionLotV1Bo.setUsgDecInd(projectOneQalsEntity.getStat35());
        //N5
        boolean empty5 = StringUtils.isEmpty(projectOneQalsEntity.getEnstehdat());
        if(!empty5){
            String date = getDate(projectOneQalsEntity.getEnstehdat());
            edmInspectionLotV1Bo.setLocalDateOfLotCreation(date);
        }
        edmInspectionLotV1Bo.setLocalTimeOfLotCreation(projectOneQalsEntity.getEntstezeit());

        boolean empty7 = StringUtils.isEmpty(projectOneQalsEntity.getPastrterm());
        if(!empty7){
            String date1 = getDate(projectOneQalsEntity.getPastrterm());
            edmInspectionLotV1Bo.setInspStrtDt(date1);
        }
        edmInspectionLotV1Bo.setInspStrtTm(projectOneQalsEntity.getPastrzeit());
        boolean empty6 = StringUtils.isEmpty(projectOneQalsEntity.getPaendterm());
        if(!empty6){
            String date2 = getDate(projectOneQalsEntity.getPaendterm());
            edmInspectionLotV1Bo.setInspEndDt(date2);
        }
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
        edmInspectionLotV1Bo.setActlLotQty(projectOneQalsEntity.getLmengeist());
        //N4
        QaveEntity qaveEntity = projcetOneQaveDao.getQaveEntity(projectOneQalsEntity.getPrueflos());
        if(qaveEntity!=null){
            edmInspectionLotV1Bo.setUsgDcsnCd(qaveEntity.getVcode());
            String date3 = getDate(qaveEntity.getVaedatum());
            edmInspectionLotV1Bo.setQcDcsnDttm(date3);
        }
        String objnr = getStringNumber(projectOneQalsEntity.getPrueflos());
        String s = getqcStsCd(objnr);
        boolean empty2 = StringUtils.isEmpty(s);
        if(!empty2){
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
        StringBuilder sb = new StringBuilder();
        sb.append(QL);
        if(number.length()<12){
            for (int i = 0; i < str.length-number.length(); i++) {
                String s = str[i];
                sb.append(s);
            }
        }
        sb.append(number);
        return sb.toString();
    }
    public String getqcStsCd(String objnr){
        StringBuilder sb = new StringBuilder();
        List<JestEntity> entityWithObjnr = projectOneJestDao.getEntityByObjnr(objnr);
        if(null!=entityWithObjnr){
        for (JestEntity jestEntity : entityWithObjnr) {
            String stat = jestEntity.getStat();
            List<Tj02tEntity> entityWithStat = projectOneTj02tDao.getEntityWithStat(stat);
            if(null!=entityWithStat){
            for (Tj02tEntity tj02tEntity : entityWithStat) {
                        sb.append(" ");
                        sb.append(tj02tEntity.getTxt04());
                }
            }
        }
        }
        return sb.toString().trim();
    }

    public  String getDate (String time)  {
        SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.F_yyyyMMdd);
        formatter.setLenient(false);
        try{
            Date newDate= formatter.parse(time);
            formatter = new SimpleDateFormat(DateUtils.DATE_FORMAT_1);
            String format = formatter.format(newDate);
            return format;
        }catch (Exception e){
            return null;
        }
    }



}

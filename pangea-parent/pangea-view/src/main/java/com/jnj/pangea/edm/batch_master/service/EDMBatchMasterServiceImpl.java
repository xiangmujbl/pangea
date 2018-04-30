package com.jnj.pangea.edm.batch_master.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMchaDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMchbDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.Mch1Entity;
import com.jnj.pangea.common.entity.project_one.MchaEntity;
import com.jnj.pangea.common.entity.project_one.MchbEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.batch_master.bo.EDMBatchMasterBo;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EDMBatchMasterServiceImpl implements ICommonService {

    private static EDMBatchMasterServiceImpl instance;

    public static EDMBatchMasterServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMBatchMasterServiceImpl();
        }
        return instance;
    }

    private ProjectOneMchaDaoImpl mchaDao = ProjectOneMchaDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private ProjectOneMchbDaoImpl mchbDao = ProjectOneMchbDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl materialGlobalV1Dao = EDMMaterialGlobalDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        Mch1Entity mch1Entity = (Mch1Entity) o;
        EDMBatchMasterBo eDMBatchMasterBo = new EDMBatchMasterBo();



        // N4
        String localMaterialNumber = mch1Entity.getMatnr();
        if (StringUtils.isNotEmpty(localMaterialNumber)) {

            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);
            if (null != materialGlobalV1Entity && StringUtils.isNotEmpty(materialGlobalV1Entity.getMaterialNumber())) {

                eDMBatchMasterBo.setMaterialNumber(materialGlobalV1Entity.getPrimaryPlanningCode());

                // J1
                MchaEntity mchaEntity = mchaDao.getEntityWithMatnrAndCharg(mch1Entity.getMatnr(), mch1Entity.getCharg());

                if (null != mchaEntity) {

                    String mandt = mchaEntity.getMandt();
                    if (StringUtils.isNotEmpty(mandt)) {

                        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(mandt);
                        if (null != edmSourceSystemV1Entity) {
                            eDMBatchMasterBo.setSrcSysCd(edmSourceSystemV1Entity.getSourceSystem());
                        }
                    }

                    eDMBatchMasterBo.setLocalPlant(mchaEntity.getWerks());

                    String charg = mchaEntity.getCharg();
                    if (StringUtils.isNotEmpty(charg)) {

                        MchbEntity mchbEntity = mchbDao.getEntityWithCharg(charg);
                        if (null != mchbEntity) {

                            eDMBatchMasterBo.setLocalStorLocation(mchbEntity.getLgort());
                        }
                    }

                    // N2
                    String localPlant = mchaEntity.getWerks();
                    if (StringUtils.isNotEmpty(localPlant)) {

                        EDMPlantV1Entity plantV1Entity = plantV1Dao.getEntityWithLocalPlant(localPlant);
                        if (null != plantV1Entity) {

                            eDMBatchMasterBo.setPlant(plantV1Entity.getPlant());
                        }
                    }
                }

                eDMBatchMasterBo.setMatlNum(mch1Entity.getMatnr());
                eDMBatchMasterBo.setBtchNum(mch1Entity.getCharg());
                eDMBatchMasterBo.setBtchStsCd(mch1Entity.getZustd());
                SimpleDateFormat inFormatter = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                SimpleDateFormat outFormatter = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDHHMMSS);

                try {
                    eDMBatchMasterBo.setBtchExpDt(outFormatter.format(inFormatter.parse(mch1Entity.getVfdat())));
                    eDMBatchMasterBo.setBtchMfgDt(outFormatter.format(inFormatter.parse(mch1Entity.getHsdat())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                resultObject.setBaseBo(eDMBatchMasterBo);

            } else {

                // Reject record
                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.EDM_BATCH_MASTER, IConstant.FAILED.ERROR_CODE.N4,
                        "", "edm", mch1Entity.getMatnr(), mch1Entity.getCharg()));
            }
        }

        return resultObject;
    }
}

package com.jnj.pangea.edm.material.plant.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.curation.indexer.AdfLuceneHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMMatPlantStatV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatPlantStatV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectone.MarcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.bo.EDMMaterialPlantBo;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class EDMMaterialPlantServiceImpl implements ICommonService {

    private static EDMMaterialPlantServiceImpl instance;

    public static EDMMaterialPlantServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantServiceImpl();
        }
        return instance;
    }

    EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    EDMMatPlantStatV1DaoImpl matPlantStatV1Dao = EDMMatPlantStatV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        MarcEntity marcEntity = (MarcEntity) o;

        EDMMaterialPlantBo materialPlantBo = new EDMMaterialPlantBo();

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithProjectOne().getSourceSystem();
        materialPlantBo.setSourceSystem(sourceSystem);

        String matnr = marcEntity.getMatnr();
        materialPlantBo.setLocalMaterialNumber(matnr);
        String werks = marcEntity.getWerks();
        materialPlantBo.setLocalPlant(werks);

        String materialNumber = materialGlobalDao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(sourceSystem,matnr).getMaterialNumber();
        materialPlantBo.setMaterialNumber(materialNumber);

        String plant = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,werks).getPlant();
        materialPlantBo.setPlant(plant);

        String mmsta = marcEntity.getMmsta();
        materialPlantBo.setLocalPlantStatus(mmsta);

        String plantStatus = matPlantStatV1Dao.getPlantStatusWithLocalPlantStatusAndSourceSystem(sourceSystem,mmsta).getPlantStatus();
        materialPlantBo.setPlantStatus(plantStatus);

        resultObject.setBaseBo(materialPlantBo);

        return resultObject;
    }
}

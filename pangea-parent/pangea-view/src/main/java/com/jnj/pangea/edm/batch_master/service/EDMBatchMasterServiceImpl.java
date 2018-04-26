package com.jnj.pangea.edm.batch_master.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.project_one.Mch1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.batch_master.bo.EDMBatchMasterBo;

public class EDMBatchMasterServiceImpl implements ICommonService {

    private static EDMBatchMasterServiceImpl instance;

    public static EDMBatchMasterServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMBatchMasterServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl materialGlobalV1Dao = EDMMaterialGlobalDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        Mch1Entity mch1Entity = (Mch1Entity) o;
        EDMBatchMasterBo eDMBatchMasterBo = new EDMBatchMasterBo();

        // TODO add logic
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        eDMBatchMasterBo.setSourceSystem(edmSourceSystemV1Entity.getSourceSystem());
        processSystem(mch1Entity,eDMBatchMasterBo);
        EDMMaterialGlobalV1Entity materialGlobalV1Entity =  materialGlobalV1Dao.getEntityWithSourceSystemAndLocalMaterialNumber(edmSourceSystemV1Entity.getSourceSystem(),mch1Entity.getMatnr());

        if(materialGlobalV1Entity!=null){
            eDMBatchMasterBo.setMaterialNumber(materialGlobalV1Entity.getMaterialNumber());
        }else{
            eDMBatchMasterBo.setMaterialNumber("");
        }
        resultObject.setBaseBo(eDMBatchMasterBo);
        return resultObject;
    }


    private void processSystem(Mch1Entity mainData, EDMBatchMasterBo edmBatchMasterBo) {
        edmBatchMasterBo.setLocalBatchNumber(mainData.getCharg());
        edmBatchMasterBo.setLocalDateofManufacture(mainData.getHsdat());
        edmBatchMasterBo.setLocalMaterialNumber(mainData.getMatnr());
        edmBatchMasterBo.setLocalShelfLifeExpiration(mainData.getVfdat());
    }
}

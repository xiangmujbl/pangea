package com.jnj.pangea.edm.matl_prod_versn.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneMkalAendDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.projectOne.ProjectOneMkalEntity;
import com.jnj.pangea.common.entity.projectOne.ProjectOneMkalAendEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.matl_prod_versn.bo.EDMMatlProdVersnBo;

public class EDMMatlProdVersnServiceImpl implements ICommonService {

    private static EDMMatlProdVersnServiceImpl instance;

    public static EDMMatlProdVersnServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlProdVersnServiceImpl();
        }
        return instance;
    }
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    private ProjectOneMkalAendDaoImpl mkalAendDao = ProjectOneMkalAendDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneMkalEntity mkalEntity = (ProjectOneMkalEntity) o;

        EDMMatlProdVersnBo matlProdVersnBo = new EDMMatlProdVersnBo();
        // TODO add logic

        String matnr =mkalEntity.getMatnr();
        String werks=mkalEntity.getWerks();
        String verid=mkalEntity.getVerid();

        ProjectOneMkalAendEntity projectOneMkalAendEntity=  mkalAendDao.getEntityWithConditions(matnr,werks,verid);
        if(projectOneMkalAendEntity==null){
            return resultObject;
        }

        matlProdVersnBo.setCrtDttm(projectOneMkalAendEntity.getAndat());

        matlProdVersnBo.setChgDttm(projectOneMkalAendEntity.getAedat());

        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            matlProdVersnBo.setSrcSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        matlProdVersnBo.setMatlNum(mkalEntity.getMatnr());
        matlProdVersnBo.setPlntCd(mkalEntity.getWerks());
        matlProdVersnBo.setPrdntVrsnNum(mkalEntity.getVerid());
        matlProdVersnBo.setValFromDt(mkalEntity.getAdatu());
        matlProdVersnBo.setValToDt(mkalEntity.getBdatu());
        matlProdVersnBo.setAltBomNum(mkalEntity.getStlal());
        matlProdVersnBo.setBomUsgCd(mkalEntity.getStlan());
        matlProdVersnBo.setRtngTypCd(mkalEntity.getPlnty());
        matlProdVersnBo.setRtngGrpCd(mkalEntity.getPlnnr());
        matlProdVersnBo.setRtngGrpCntrNum(mkalEntity.getAlnal());
        matlProdVersnBo.setPrcrmntTypCd(mkalEntity.getBeskz());
        matlProdVersnBo.setSpPrcrmntTypCd(mkalEntity.getSobsl());
        matlProdVersnBo.setCstLtSzQty(mkalEntity.getLosgr());
        matlProdVersnBo.setMfgLineCd(mkalEntity.getMdv01());
        matlProdVersnBo.setPrdVrsnDesc(mkalEntity.getText1());
        matlProdVersnBo.setDstrbtnKeyCd(mkalEntity.getVerto());
        matlProdVersnBo.setFrmLtSzQty(mkalEntity.getBstmi());
        matlProdVersnBo.setToLtSzQty(mkalEntity.getBstma());
        matlProdVersnBo.setSLocCd(mkalEntity.getAlort());
        matlProdVersnBo.setChckInd(mkalEntity.getPrfgF());
        matlProdVersnBo.setChckDt(mkalEntity.getPrdat());
        matlProdVersnBo.setLckInd(mkalEntity.getMksp());
        matlProdVersnBo.setOrgBtchInd(mkalEntity.getUcmat());
        resultObject.setBaseBo(matlProdVersnBo);
        return resultObject;
    }
}

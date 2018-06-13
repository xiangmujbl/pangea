package com.jnj.pangea.edm.sales_history_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneVbfaDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOneVbfaEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.sales_history_v1.bo.EDMSalesHistoryV1Bo;

/**   
 * @Name: EDMSalesHistoryV1ServiceImpl
 * @Description: service impl for sales_history_v1 of edm system
 * @author KG(Kelvin Gu)
 * @date 06-12-2018 05:05:33 
*/
public class EDMSalesHistoryV1ServiceImpl implements ICommonService {

    private ProjectOneVbfaDaoImpl projectOneVbfaDao = ProjectOneVbfaDaoImpl.getInstance();

    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMSalesHistoryV1ServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        ProjectOneVbfaEntity mainData = (ProjectOneVbfaEntity) o;

        // mapping ViewBo from MainEntity
        EDMSalesHistoryV1Bo viewBo = new EDMSalesHistoryV1Bo();
        mappingObject(mainData, viewBo);

        resultObject.setBaseBo(viewBo);

        return resultObject;
    }


    private void mappingObject(ProjectOneVbfaEntity mainData, EDMSalesHistoryV1Bo viewBo) {
        // to-do: here write your own logic of mapping
        viewBo.setLocalPrecDocNo(mainData.getVbelv());
        viewBo.setLocalSPrecDocLnNo(mainData.getPosnv());
        viewBo.setLocalSubsDocNo(mainData.getVbeln());
        viewBo.setLocalSubsDocLnNo(mainData.getPosnn());
        viewBo.setLocalSubDocCatg(mainData.getVbtypN());
        viewBo.setLocalBaseQuantity(mainData.getRfmng());
        viewBo.setLocalBaseUom(mainData.getMeins());
        viewBo.setLocalSalesQuantity(mainData.getRfmngFlt());
        viewBo.setLocalSalesUom(mainData.getVrkme());
        viewBo.setLocalPrecItemCatg(mainData.getVbtypV());
        viewBo.setLocalCrtDt(mainData.getErdat());
    }

}

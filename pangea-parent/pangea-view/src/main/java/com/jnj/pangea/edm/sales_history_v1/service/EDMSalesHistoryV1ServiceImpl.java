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

        EDMSalesHistoryV1Bo viewBo = new EDMSalesHistoryV1Bo();
        resultObject.setBaseBo(viewBo);

        // mapping ViewBo from MainEntity
        EDMSalesHistoryV1Bo destBo = new EDMSalesHistoryV1Bo();
        mappingObject(mainData, destBo);

        resultObject.setBaseBo(destBo);

        return resultObject;
    }


    private void mappingObject(ProjectOneVbfaEntity mainData, EDMSalesHistoryV1Bo viewBo) {
        // to-do: here write your own logic of mapping

    }

}

package com.jnj.pangea.edm.form_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.infa_mdm.ClKupFormV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.form_v1.bo.EDMFormV1Bo;

/**
 * EDMForm/Functional name - rework - Curation
 * AEAZ-8295
 */
public class EDMFormV1ServiceImpl implements ICommonService {

    private static EDMFormV1ServiceImpl instance;

    public static EDMFormV1ServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMFormV1ServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMFormV1Bo formBo = new EDMFormV1Bo();
        ClKupFormV1Entity clKupFormEntity=(ClKupFormV1Entity)o;
        if(clKupFormEntity!=null){
            formBo.setFormName(clKupFormEntity.getFormCd());
            formBo.setFormDescription(clKupFormEntity.getFormNm());
        }
        resultObject.setBaseBo(formBo);
        return resultObject;
    }

}

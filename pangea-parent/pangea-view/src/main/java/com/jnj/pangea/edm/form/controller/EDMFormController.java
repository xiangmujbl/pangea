package com.jnj.pangea.edm.form.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.FormEntity;
import com.jnj.pangea.edm.form.bo.EDMFormBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMFormController extends CommonController {

    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        FormEntity formEntity = BeanUtil.mapToBean(rawMap,new FormEntity());

        EDMFormBo formBo = new EDMFormBo();
        formBo.setFormName(formEntity.getFormName());
        formBo.setFormDescription(formEntity.getFormDescription());

        resultObject.setBaseBo(formBo);

        return resultObject;
    }
}

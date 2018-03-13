package com.jnj.pangea.edm.form.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.FormEntity;
import com.jnj.pangea.edm.form.bo.EDMFormBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMFormController extends CommonController {

    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        FormEntity formEntity = BeanUtil.mapToBean(raw.getValue().toMap(), FormEntity.class);

        EDMFormBo formBo = new EDMFormBo();
        formBo.setFormName(formEntity.getFormName());
        formBo.setFormDescription(formEntity.getFormDescription());

        resultObject.setBaseBo(formBo);

        return resultObject;
    }
}

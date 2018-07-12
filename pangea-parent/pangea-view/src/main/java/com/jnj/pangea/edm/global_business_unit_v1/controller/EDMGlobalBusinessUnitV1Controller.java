package com.jnj.pangea.edm.global_business_unit_v1.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.infa_mdm.CLkupGbuEntity;
import com.jnj.pangea.edm.global_business_unit_v1.service.EDMGlobalBusinessUnitV1ServiceImpl;
import com.jnj.pangea.util.BeanUtil;


public class EDMGlobalBusinessUnitV1Controller extends CommonController {

	private EDMGlobalBusinessUnitV1ServiceImpl service = EDMGlobalBusinessUnitV1ServiceImpl.getInstance();

	@Override
	public ResultObject process(RawDataEvent raw) {
		 return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), CLkupGbuEntity.class), null);
	}


}

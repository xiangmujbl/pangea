package com.jnj.pangea.edm.global_business_unit_v1.service;

import com.jnj.pangea.common.entity.infa_mdm.CLkupGbuEntity;
import org.apache.commons.lang.StringUtils;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.global_business_unit_v1.bo.EDMGlobalBusinessUnitV1Bo;


public class EDMGlobalBusinessUnitV1ServiceImpl implements ICommonService{

	private static EDMGlobalBusinessUnitV1ServiceImpl instance;

	public static EDMGlobalBusinessUnitV1ServiceImpl getInstance() {

		if (instance == null) {
            instance = new EDMGlobalBusinessUnitV1ServiceImpl();
        }
        return instance;
	}

	@Override
	public ResultObject buildView(String key, Object o, Object o2) {
		ResultObject resultObject=new ResultObject();
		CLkupGbuEntity infaMdmEntity = (CLkupGbuEntity) o;

		EDMGlobalBusinessUnitV1Bo edmGlobalEntity=new EDMGlobalBusinessUnitV1Bo();

		 if (StringUtils.isNotBlank(infaMdmEntity.getGbuCd())) {
			 edmGlobalEntity.setGbu(infaMdmEntity.getGbuCd());
	     }

		 if(StringUtils.isNotBlank(infaMdmEntity.getGbuDescnTxt())) {
			 edmGlobalEntity.setGbuName(infaMdmEntity.getGbuDescnTxt());
		 }

		 resultObject.setBaseBo(edmGlobalEntity);
		return resultObject;


	}

}

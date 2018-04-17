package com.jnj.pangea.edm.country.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmCountriesDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCountryServiceImpl implements ICommonService {


    private static ICommonService instance;
    private EMSFMdmCountriesDaoImpl emsfMdmCountriesDao = EMSFMdmCountriesDaoImpl.getInstance();

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        EMSFMdmCountriesEntity mainData = (EMSFMdmCountriesEntity) o;

        EDMCountryBo edmCountryBo = new EDMCountryBo();

        String zSourceSystem = mainData.getzSourceSystem();

        edmCountryBo.setSourceSystem(zSourceSystem);
        edmCountryBo.setLocalCountry(mainData.getMdmCode());

        String zEntCodeIso3166Alpha2 = mainData.getzEntCodeIso3166Alpha2();

        edmCountryBo.setCountryCode(zEntCodeIso3166Alpha2);
        if (StringUtils.isNotEmpty(zEntCodeIso3166Alpha2)) {
            EMSFMdmCountriesEntity emsfMdmCountriesEntity = emsfMdmCountriesDao.getMdmNameWithzSourceSystemAndMdmCode(IConstant.VALUE.EMS, zEntCodeIso3166Alpha2);
            if (emsfMdmCountriesEntity != null) {
                edmCountryBo.setCountryName(emsfMdmCountriesEntity.getMdmName());
            }
        }

        resultObject.setBaseBo(edmCountryBo);
        return resultObject;
    }

}

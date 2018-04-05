package com.jnj.pangea.edm.country.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmCountriesDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCountryServiceImpl implements ICommonService {


    private static ICommonService instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
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
        resultObject.setBaseBo(edmCountryBo);

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(mainData.getzSourceSystem());
        edmCountryBo.setSourceSystem(sourceSystem);


        processSystem(mainData, edmCountryBo);
        EMSFMdmCountriesEntity emsfMdmCountriesEntity = emsfMdmCountriesDao.getMdmNameWithzSourceSystemAndMdmCode(IConstant.VALUE.EMS, mainData.getzEntCodeIso3166Alpha2());
        if (emsfMdmCountriesEntity != null) {
            edmCountryBo.setCountryName(emsfMdmCountriesEntity.getMdmName());
        }
        return resultObject;
    }

    private boolean processSystem(EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
        edmCountryBo.setLocalCountry(mainData.getMdmCode());
        edmCountryBo.setCountryCode(mainData.getzEntCodeIso3166Alpha2());
        return true;
    }

}

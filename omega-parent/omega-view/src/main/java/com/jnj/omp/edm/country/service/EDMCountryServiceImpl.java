package com.jnj.omp.edm.country.service;

import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.ResultObject;
import com.jnj.omp.common.bo.EDMCountryBo;
import com.jnj.omp.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.omp.common.dao.impl.ems.EMSFMdmCountriesDaoImpl;
import com.jnj.omp.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.omp.common.service.ICommonService;
import org.apache.commons.lang3.StringUtils;

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

        // J1
        String zSourceSystem = mainData.getzSourceSystem();
        if (StringUtils.isNotEmpty(zSourceSystem)) {
            edmCountryBo.setSourceSystem(sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(zSourceSystem));
        }

        edmCountryBo.setLocalCountry(mainData.getMdmCode());

        String zEntCodeIso3166Alpha2 = mainData.getzEntCodeIso3166Alpha2();

        edmCountryBo.setCountryCode(zEntCodeIso3166Alpha2);

        // T2
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

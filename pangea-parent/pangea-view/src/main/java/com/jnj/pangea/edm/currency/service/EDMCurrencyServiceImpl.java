package com.jnj.pangea.edm.currency.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.ems.EMSFMdmCurrenciesDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.currency.bo.EDMCurrencyBo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCurrencyServiceImpl implements ICommonService {

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EMSFMdmCurrenciesDaoImpl emsfMdmCurrenciesDao = EMSFMdmCurrenciesDaoImpl.getInstance();
    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCurrencyServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFMdmCurrenciesEntity mainData = (EMSFMdmCurrenciesEntity) o;

        EDMCurrencyBo edmCurrencyBo = new EDMCurrencyBo();
        resultObject.setBaseBo(edmCurrencyBo);

        String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(mainData.getzSourceSystem());
        edmCurrencyBo.setSourceSystem(sourceSystem);

        processSystem(mainData, edmCurrencyBo);

        String code = mainData.getzEntCodeIso4217Alpha();
        if (StringUtils.isNotEmpty(code)) {
            EMSFMdmCurrenciesEntity emsfMdmCurrenciesEntity = emsfMdmCurrenciesDao.getZnameWithzSourceSystemAndZcode(IConstant.VALUE.EMS, code);
            if (emsfMdmCurrenciesEntity != null) {
                edmCurrencyBo.setCurrencyName(emsfMdmCurrenciesEntity.getzName());
            }
        }
        return resultObject;
    }

    private boolean processSystem(EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {
        edmCurrencyBo.setLocalCurrency(mainData.getzCode());
        edmCurrencyBo.setCurrencyCode(mainData.getzEntCodeIso4217Alpha());
        edmCurrencyBo.setIsoNumeric(mainData.getzIso4217Numeric());
        return true;
    }

}

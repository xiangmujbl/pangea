package com.jnj.pangea.edm.reserv_hdr.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.projectOne.ProjectOneRkpfEntity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.reserv_hdr.bo.EDMReservHdrBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static com.jnj.pangea.util.DateUtils.*;

public class EDMReservHdrServiceImpl implements ICommonService {

    private static EDMReservHdrServiceImpl instance;

    public static EDMReservHdrServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMReservHdrServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ProjectOneRkpfEntity rkpfEntity = (ProjectOneRkpfEntity) o;

        EDMReservHdrBo reservHdrBo = new EDMReservHdrBo();
        // TODO add logic
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getSourceSystemWithProjectOne();
        if (null != sourceSystemV1Entity) {
            reservHdrBo.setSourceSysCd(sourceSystemV1Entity.getSourceSystem());
        }
        reservHdrBo.setRsrvtnNum(rkpfEntity.getRsnum());
        reservHdrBo.setRsrvtnOrigCd(rkpfEntity.getKzver());
        reservHdrBo.setFactCalndrInd(rkpfEntity.getXcale());
        String date=rkpfEntity.getRsdat();
        Pattern pattern=Pattern.compile(IConstant.RKPF.regex);
        if(StringUtils.isNotBlank(date)&&pattern.matcher(date).matches()){
            String dateFrom=dateToString(stringToDate(date, yyyy_MM_dd), yyyy_MM_ddTHHmmss);
            reservHdrBo.setRsrvtnBsDt(dateFrom);
        }
        resultObject.setBaseBo(reservHdrBo);
        return resultObject;
    }
}

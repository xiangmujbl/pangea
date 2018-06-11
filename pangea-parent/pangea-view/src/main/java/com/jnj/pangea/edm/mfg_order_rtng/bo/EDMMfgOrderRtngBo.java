package com.jnj.pangea.edm.mfg_order_rtng.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgOrderRtngBo extends BaseBo {

    private String srcSysCd;
    private String ordrRtngNum;
    private String ordrRtngCtrNum;
    private String operNum;
    private String operCd;
    private String wrkCntrId;
    private String operDesc;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd",srcSysCd)
                .add("ordrRtngNum",ordrRtngNum)
                .add("ordrRtngCtrNum",ordrRtngCtrNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getOrdrRtngNum() {
        return this.ordrRtngNum;
    }

    public void setOrdrRtngNum(String ordrRtngNum) {
        this.ordrRtngNum = ordrRtngNum;
    }

    public String getOrdrRtngCtrNum() {
        return this.ordrRtngCtrNum;
    }

    public void setOrdrRtngCtrNum(String ordrRtngCtrNum) {
        this.ordrRtngCtrNum = ordrRtngCtrNum;
    }

    public String getOperNum() {
        return this.operNum;
    }

    public void setOperNum(String operNum) {
        this.operNum = operNum;
    }

    public String getOperCd() {
        return this.operCd;
    }

    public void setOperCd(String operCd) {
        this.operCd = operCd;
    }

    public String getWrkCntrId() {
        return this.wrkCntrId;
    }

    public void setWrkCntrId(String wrkCntrId) {
        this.wrkCntrId = wrkCntrId;
    }

    public String getOperDesc() {
        return this.operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

}

package com.jnj.pangea.edm.mfg_order_oper.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgOrderOperBo extends BaseBo {

    private String srcSysCd;
    private String ordrRtngNum;
    private String ordrRtngCtrNum;
    private String oprUomCd;
    private String factDenomMeas;
    private String factNumrtrMeas;
    private String baseQty;
    private String actv1UomCd;
    private String actv1Qty;
    private String actv2UomCd;
    private String actv2Qty;
    private String actv3UomCd;
    private String actv3Qty;
    private String actv4UomCd;
    private String actv4Qty;
    private String actv5UomCd;
    private String actv5Qty;
    private String actv6UomCd;
    private String actv6Qty;
    private String minOvrlapTimeUomCd;
    private String minOvrlapTimeInDaysQty;
    private String minSendAhdQty;
    private String minPrcsgTimeUomCd;
    private String minPrcsgTimeInDaysQty;
    private String maxWaitTimeUomCd;
    private String maxWaitTimeInDaysQty;
    private String minWaitTimeUomCd;
    private String minWaitTimeInDaysQty;
    private String stdQueTimeUomCd;
    private String stdQueTimeInDaysQty;
    private String minQueTimeUomCd;
    private String minQueTimeInDaysQty;
    private String stdMoveTimeUomCd;
    private String stdMoveTimeInDaysQty;
    private String minMoveTimeUomCd;
    private String minMoveTimeInDaysQty;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
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

    public String getOprUomCd() {
        return this.oprUomCd;
    }

    public void setOprUomCd(String oprUomCd) {
        this.oprUomCd = oprUomCd;
    }

    public String getFactDenomMeas() {
        return this.factDenomMeas;
    }

    public void setFactDenomMeas(String factDenomMeas) {
        this.factDenomMeas = factDenomMeas;
    }

    public String getFactNumrtrMeas() {
        return this.factNumrtrMeas;
    }

    public void setFactNumrtrMeas(String factNumrtrMeas) {
        this.factNumrtrMeas = factNumrtrMeas;
    }

    public String getBaseQty() {
        return this.baseQty;
    }

    public void setBaseQty(String baseQty) {
        this.baseQty = baseQty;
    }

    public String getActv1UomCd() {
        return this.actv1UomCd;
    }

    public void setActv1UomCd(String actv1UomCd) {
        this.actv1UomCd = actv1UomCd;
    }

    public String getActv1Qty() {
        return this.actv1Qty;
    }

    public void setActv1Qty(String actv1Qty) {
        this.actv1Qty = actv1Qty;
    }

    public String getActv2UomCd() {
        return this.actv2UomCd;
    }

    public void setActv2UomCd(String actv2UomCd) {
        this.actv2UomCd = actv2UomCd;
    }

    public String getActv2Qty() {
        return this.actv2Qty;
    }

    public void setActv2Qty(String actv2Qty) {
        this.actv2Qty = actv2Qty;
    }

    public String getActv3UomCd() {
        return this.actv3UomCd;
    }

    public void setActv3UomCd(String actv3UomCd) {
        this.actv3UomCd = actv3UomCd;
    }

    public String getActv3Qty() {
        return this.actv3Qty;
    }

    public void setActv3Qty(String actv3Qty) {
        this.actv3Qty = actv3Qty;
    }

    public String getActv4UomCd() {
        return this.actv4UomCd;
    }

    public void setActv4UomCd(String actv4UomCd) {
        this.actv4UomCd = actv4UomCd;
    }

    public String getActv4Qty() {
        return this.actv4Qty;
    }

    public void setActv4Qty(String actv4Qty) {
        this.actv4Qty = actv4Qty;
    }

    public String getActv5UomCd() {
        return this.actv5UomCd;
    }

    public void setActv5UomCd(String actv5UomCd) {
        this.actv5UomCd = actv5UomCd;
    }

    public String getActv5Qty() {
        return this.actv5Qty;
    }

    public void setActv5Qty(String actv5Qty) {
        this.actv5Qty = actv5Qty;
    }

    public String getActv6UomCd() {
        return this.actv6UomCd;
    }

    public void setActv6UomCd(String actv6UomCd) {
        this.actv6UomCd = actv6UomCd;
    }

    public String getActv6Qty() {
        return this.actv6Qty;
    }

    public void setActv6Qty(String actv6Qty) {
        this.actv6Qty = actv6Qty;
    }

    public String getMinOvrlapTimeUomCd() {
        return this.minOvrlapTimeUomCd;
    }

    public void setMinOvrlapTimeUomCd(String minOvrlapTimeUomCd) {
        this.minOvrlapTimeUomCd = minOvrlapTimeUomCd;
    }

    public String getMinOvrlapTimeInDaysQty() {
        return this.minOvrlapTimeInDaysQty;
    }

    public void setMinOvrlapTimeInDaysQty(String minOvrlapTimeInDaysQty) {
        this.minOvrlapTimeInDaysQty = minOvrlapTimeInDaysQty;
    }

    public String getMinSendAhdQty() {
        return this.minSendAhdQty;
    }

    public void setMinSendAhdQty(String minSendAhdQty) {
        this.minSendAhdQty = minSendAhdQty;
    }

    public String getMinPrcsgTimeUomCd() {
        return this.minPrcsgTimeUomCd;
    }

    public void setMinPrcsgTimeUomCd(String minPrcsgTimeUomCd) {
        this.minPrcsgTimeUomCd = minPrcsgTimeUomCd;
    }

    public String getMinPrcsgTimeInDaysQty() {
        return this.minPrcsgTimeInDaysQty;
    }

    public void setMinPrcsgTimeInDaysQty(String minPrcsgTimeInDaysQty) {
        this.minPrcsgTimeInDaysQty = minPrcsgTimeInDaysQty;
    }

    public String getMaxWaitTimeUomCd() {
        return this.maxWaitTimeUomCd;
    }

    public void setMaxWaitTimeUomCd(String maxWaitTimeUomCd) {
        this.maxWaitTimeUomCd = maxWaitTimeUomCd;
    }

    public String getMaxWaitTimeInDaysQty() {
        return this.maxWaitTimeInDaysQty;
    }

    public void setMaxWaitTimeInDaysQty(String maxWaitTimeInDaysQty) {
        this.maxWaitTimeInDaysQty = maxWaitTimeInDaysQty;
    }

    public String getMinWaitTimeUomCd() {
        return this.minWaitTimeUomCd;
    }

    public void setMinWaitTimeUomCd(String minWaitTimeUomCd) {
        this.minWaitTimeUomCd = minWaitTimeUomCd;
    }

    public String getMinWaitTimeInDaysQty() {
        return this.minWaitTimeInDaysQty;
    }

    public void setMinWaitTimeInDaysQty(String minWaitTimeInDaysQty) {
        this.minWaitTimeInDaysQty = minWaitTimeInDaysQty;
    }

    public String getStdQueTimeUomCd() {
        return this.stdQueTimeUomCd;
    }

    public void setStdQueTimeUomCd(String stdQueTimeUomCd) {
        this.stdQueTimeUomCd = stdQueTimeUomCd;
    }

    public String getStdQueTimeInDaysQty() {
        return this.stdQueTimeInDaysQty;
    }

    public void setStdQueTimeInDaysQty(String stdQueTimeInDaysQty) {
        this.stdQueTimeInDaysQty = stdQueTimeInDaysQty;
    }

    public String getMinQueTimeUomCd() {
        return this.minQueTimeUomCd;
    }

    public void setMinQueTimeUomCd(String minQueTimeUomCd) {
        this.minQueTimeUomCd = minQueTimeUomCd;
    }

    public String getMinQueTimeInDaysQty() {
        return this.minQueTimeInDaysQty;
    }

    public void setMinQueTimeInDaysQty(String minQueTimeInDaysQty) {
        this.minQueTimeInDaysQty = minQueTimeInDaysQty;
    }

    public String getStdMoveTimeUomCd() {
        return this.stdMoveTimeUomCd;
    }

    public void setStdMoveTimeUomCd(String stdMoveTimeUomCd) {
        this.stdMoveTimeUomCd = stdMoveTimeUomCd;
    }

    public String getStdMoveTimeInDaysQty() {
        return this.stdMoveTimeInDaysQty;
    }

    public void setStdMoveTimeInDaysQty(String stdMoveTimeInDaysQty) {
        this.stdMoveTimeInDaysQty = stdMoveTimeInDaysQty;
    }

    public String getMinMoveTimeUomCd() {
        return this.minMoveTimeUomCd;
    }

    public void setMinMoveTimeUomCd(String minMoveTimeUomCd) {
        this.minMoveTimeUomCd = minMoveTimeUomCd;
    }

    public String getMinMoveTimeInDaysQty() {
        return this.minMoveTimeInDaysQty;
    }

    public void setMinMoveTimeInDaysQty(String minMoveTimeInDaysQty) {
        this.minMoveTimeInDaysQty = minMoveTimeInDaysQty;
    }

}

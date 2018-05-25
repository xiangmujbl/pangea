package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMReservItmEntity extends CommonEntity {

    private String sourceSysCd;
    private String rsrvtnNum;
    private String delInd;
    private String matlNum;
    private String bomItmNum;
    private String btchNum;
    private String qtyFxInd;
    private String leadTimeOffset;
    private String rqmtQty;
    private String wthdrnQty;
    public EDMReservItmEntity(Map<String, Object> map) {
        super(map);

        setSourceSysCd((String) map.get("sourceSysCd"));
        setRsrvtnNum((String) map.get("rsrvtnNum"));
        setDelInd((String) map.get("delInd"));
        setMatlNum((String) map.get("matlNum"));
        setBomItmNum((String) map.get("bomItmNum"));
        setBtchNum((String) map.get("btchNum"));
        setQtyFxInd((String) map.get("qtyFxInd"));
        setLeadTimeOffset((String) map.get("leadTimeOffset"));
        setRqmtQty((String) map.get("rqmtQty"));
        setWthdrnQty((String) map.get("wthdrnQty"));


    }


    public String getRqmtQty() {
        return rqmtQty;
    }

    public void setRqmtQty(String rqmtQty) {
        this.rqmtQty = rqmtQty;
    }

    public String getWthdrnQty() {
        return wthdrnQty;
    }

    public void setWthdrnQty(String wthdrnQty) {
        this.wthdrnQty = wthdrnQty;
    }

    public String getLeadTimeOffset() {
        return leadTimeOffset;
    }

    public void setLeadTimeOffset(String leadTimeOffset) {
        this.leadTimeOffset = leadTimeOffset;
    }


    public String getQtyFxInd() {
        return qtyFxInd;
    }

    public void setQtyFxInd(String qtyFxInd) {
        this.qtyFxInd = qtyFxInd;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getBomItmNum() {
        return bomItmNum;
    }

    public void setBomItmNum(String bomItmNum) {
        this.bomItmNum = bomItmNum;
    }

    public String getBtchNum() {
        return btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getSourceSysCd() {
        return this.sourceSysCd;
    }

    public void setSourceSysCd(String sourceSysCd) {
        this.sourceSysCd = sourceSysCd;
    }

    public String getRsrvtnNum() {
        return this.rsrvtnNum;
    }

    public void setRsrvtnNum(String rsrvtnNum) {
        this.rsrvtnNum = rsrvtnNum;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    @Override
    public String toString() {
        return "EDMReservItmEntity{" +
                "sourceSysCd='" + sourceSysCd + '\'' +
                ", rsrvtnNum='" + rsrvtnNum + '\'' +
                ", delInd='" + delInd + '\'' +
                ", matlNum='" + matlNum + '\'' +
                ", bomItmNum='" + bomItmNum + '\'' +
                ", btchNum='" + btchNum + '\'' +
                ", qtyFxInd='" + qtyFxInd + '\'' +
                ", leadTimeOffset='" + leadTimeOffset + '\'' +
                ", rqmtQty='" + rqmtQty + '\'' +
                ", wthdrnQty='" + wthdrnQty + '\'' +
                '}';
    }
}

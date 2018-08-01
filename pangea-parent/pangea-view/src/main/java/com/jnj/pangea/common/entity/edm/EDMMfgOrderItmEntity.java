package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgOrderItmEntity extends CommonEntity {

    private String srcSysCd,mfgOrdrNum,matlNum,plntCd,prdntVrsnNum,bomItmNum,btchNum,rcvdQty,lnItmNbr,goodRcptLdDaysQty,itmQty;
    private String bsQty;

    public EDMMfgOrderItmEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setMfgOrdrNum((String) map.get("mfgOrdrNum"));
        setMatlNum((String) map.get("matlNum"));
        setPlntCd((String) map.get("plntCd"));
        setPrdntVrsnNum((String) map.get("prdntVrsnNum"));
        setBtchNum((String) map.get("btchNum"));
        setRcvdQty((String) map.get("rcvdQty"));
        setLnItmNbr((String) map.get("lnItmNbr"));
        setGoodRcptLdDaysQty((String) map.get("goodRcptLdDaysQty"));
        setItmQty((String) map.get("itmQty"));
        setBsQty((String) map.get("bsQty"));

    }

    public String getItmQty() {
        return itmQty;
    }

    public void setItmQty(String itmQty) {
        this.itmQty = itmQty;
    }

    public String getGoodRcptLdDaysQty() {
        return goodRcptLdDaysQty;
    }

    public void setGoodRcptLdDaysQty(String goodRcptLdDaysQty) {
        this.goodRcptLdDaysQty = goodRcptLdDaysQty;
    }

    public String getLnItmNbr() {
        return lnItmNbr;
    }

    public void setLnItmNbr(String lnItmNbr) {
        this.lnItmNbr = lnItmNbr;
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

    public String getRcvdQty() {
        return rcvdQty;
    }

    public void setRcvdQty(String rcvdQty) {
        this.rcvdQty = rcvdQty;
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMfgOrdrNum() {
        return this.mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getPrdntVrsnNum() {
        return this.prdntVrsnNum;
    }

    public void setPrdntVrsnNum(String prdntVrsnNum) {
        this.prdntVrsnNum = prdntVrsnNum;
    }

    public String getBsQty() {
        return bsQty;
    }

    public void setBsQty(String bsQty) {
        this.bsQty = bsQty;
    }

    @Override
    public String toString() {
        return "EDMMfgOrderItmEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", mfgOrdrNum='" + mfgOrdrNum + '\'' +
                ", matlNum='" + matlNum + '\'' +
                ", plntCd='" + plntCd + '\'' +
                ", prdntVrsnNum='" + prdntVrsnNum + '\'' +
                ", bomItmNum='" + bomItmNum + '\'' +
                ", btchNum='" + btchNum + '\'' +
                ", rcvdQty='" + rcvdQty + '\'' +
                ", lnItmNbr='" + lnItmNbr + '\'' +
                ", goodRcptLdDaysQty='" + goodRcptLdDaysQty + '\'' +
                ", itmQty='" + itmQty + '\'' +
                ", bsQty='" + bsQty + '\'' +
                '}';
    }
}

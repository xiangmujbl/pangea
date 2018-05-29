package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMatlMfgRtngEntity extends CommonEntity {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpcntrNum;
    private String plntCd;
    private String rntgGrpCntrNbr;
    private String matlNum;
    private String rntgGrpCd;

    public EDMMatlMfgRtngEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setRtngTypCd((String) map.get("rtngTypCd"));
        setRtngGrpcntrNum((String) map.get("rtngGrpcntrNum"));
        setPlntCd((String) map.get("plntCd"));
        setRntgGrpCntrNbr((String) map.get("rntgGrpCntrNbr"));
        setMatlNum((String) map.get("matlNum"));
        setRntgGrpCd((String) map.get("rntgGrpCd"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtngTypCd() {
        return this.rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRtngGrpcntrNum() {
        return this.rtngGrpcntrNum;
    }

    public void setRtngGrpcntrNum(String rtngGrpcntrNum) {
        this.rtngGrpcntrNum = rtngGrpcntrNum;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getRntgGrpCntrNbr() {
        return this.rntgGrpCntrNbr;
    }

    public void setRntgGrpCntrNbr(String rntgGrpCntrNbr) {
        this.rntgGrpCntrNbr = rntgGrpCntrNbr;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getRntgGrpCd() {
        return rntgGrpCd;
    }

    public void setRntgGrpCd(String rntgGrpCd) {
        this.rntgGrpCd = rntgGrpCd;
    }

    @Override
    public String toString() {
        return "EDMMatlMfgRtngEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", rtngTypCd='" + rtngTypCd + '\'' +
                ", rtngGrpcntrNum='" + rtngGrpcntrNum + '\'' +
                ", plntCd='" + plntCd + '\'' +
                ", rntgGrpCntrNbr='" + rntgGrpCntrNbr + '\'' +
                ", matlNum='" + matlNum + '\'' +
                ", rntgGrpCd='" + rntgGrpCd + '\'' +
                '}';
    }
}

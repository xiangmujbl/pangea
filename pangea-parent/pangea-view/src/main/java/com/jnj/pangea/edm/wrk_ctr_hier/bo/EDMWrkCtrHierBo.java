package com.jnj.pangea.edm.wrk_ctr_hier.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMWrkCtrHierBo extends BaseBo {

    private String srcSysCd;
    private String topWrkCtrTypeCd;
    private String topWrkCtrNum;
    private String parntWrkCtrTypeCd;
    private String parntWrkCtrNum;
    private String childWrkCtrTypeCd;
    private String childWrkCtrNum;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String stopExplsInd;
    

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("topWrkCtrTypeCd", this.topWrkCtrTypeCd)
                .add("topWrkCtrNum", this.topWrkCtrNum)
                .add("parntWrkCtrTypeCd", this.parntWrkCtrTypeCd)
                .add("parntWrkCtrNum", this.parntWrkCtrNum)
                .add("childWrkCtrTypeCd", this.childWrkCtrTypeCd)
                .add("childWrkCtrNum", this.childWrkCtrNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

	public String getTopWrkCtrTypeCd() {
		return topWrkCtrTypeCd;
	}

	public void setTopWrkCtrTypeCd(String topWrkCtrTypeCd) {
		this.topWrkCtrTypeCd = topWrkCtrTypeCd;
	}

	public String getTopWrkCtrNum() {
		return topWrkCtrNum;
	}

	public void setTopWrkCtrNum(String topWrkCtrNum) {
		this.topWrkCtrNum = topWrkCtrNum;
	}

	public String getParntWrkCtrTypeCd() {
		return parntWrkCtrTypeCd;
	}

	public void setParntWrkCtrTypeCd(String parntWrkCtrTypeCd) {
		this.parntWrkCtrTypeCd = parntWrkCtrTypeCd;
	}

	public String getParntWrkCtrNum() {
		return parntWrkCtrNum;
	}

	public void setParntWrkCtrNum(String parntWrkCtrNum) {
		this.parntWrkCtrNum = parntWrkCtrNum;
	}

	public String getChildWrkCtrTypeCd() {
		return childWrkCtrTypeCd;
	}

	public void setChildWrkCtrTypeCd(String childWrkCtrTypeCd) {
		this.childWrkCtrTypeCd = childWrkCtrTypeCd;
	}

	public String getChildWrkCtrNum() {
		return childWrkCtrNum;
	}

	public void setChildWrkCtrNum(String childWrkCtrNum) {
		this.childWrkCtrNum = childWrkCtrNum;
	}

	public String getWrkCtrTypeCd() {
		return wrkCtrTypeCd;
	}

	public void setWrkCtrTypeCd(String wrkCtrTypeCd) {
		this.wrkCtrTypeCd = wrkCtrTypeCd;
	}

	public String getWrkCtrNum() {
		return wrkCtrNum;
	}

	public void setWrkCtrNum(String wrkCtrNum) {
		this.wrkCtrNum = wrkCtrNum;
	}

	public String getStopExplsInd() {
		return stopExplsInd;
	}

	public void setStopExplsInd(String stopExplsInd) {
		this.stopExplsInd = stopExplsInd;
	}    

}

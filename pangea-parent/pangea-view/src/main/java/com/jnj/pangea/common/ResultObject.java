package com.jnj.pangea.common;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public class ResultObject {

    private boolean isSuccess = true;

    private BaseBo baseBo = null;

    private FailData failData = null;

    public boolean isSuccess() {
        return isSuccess;
    }

    public BaseBo getBaseBo() {
        return baseBo;
    }

    public void setBaseBo(BaseBo baseBo) {
        this.baseBo = baseBo;
        this.isSuccess = true;
    }

    public FailData getFailData() {
        return failData;
    }

    public void setFailData(FailData failData) {
        this.failData = failData;
        this.isSuccess = false;
    }
}

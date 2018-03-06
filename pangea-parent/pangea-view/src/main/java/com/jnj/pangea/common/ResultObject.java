package com.jnj.pangea.common;

/**
 * Created by XZhan290 on 2018/3/6.
 */
public class ResultObject {

    //true write baseBo, false write failData
    private boolean flag = true;

    private BaseBo baseBo = null;

    private FailData failData = null;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BaseBo getBaseBo() {
        return baseBo;
    }

    public void setBaseBo(BaseBo baseBo) {
        this.baseBo = baseBo;
    }

    public FailData getFailData() {
        return failData;
    }

    public void setFailData(FailData failData) {
        this.failData = failData;
    }
}

package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T439AEntity extends CommonEntity {

    private String loskz;
    private String peraz;
    private String disls;

    public T439AEntity(Map<String, Object> map) {
        super(map);

        setLoskz((String) map.get("loskz"));
        setPeraz((String) map.get("peraz"));
        setDisls((String) map.get("disls"));
    }

    public String getLoskz() {
        return this.loskz;
    }

    public void setLoskz(String loskz) {
        this.loskz = loskz;
    }

    public String getPeraz() {
        return this.peraz;
    }

    public void setPeraz(String peraz) {
        this.peraz = peraz;
    }

    public String getDisls() {
        return this.disls;
    }

    public void setDisls(String disls) {
        this.disls = disls;
    }

}

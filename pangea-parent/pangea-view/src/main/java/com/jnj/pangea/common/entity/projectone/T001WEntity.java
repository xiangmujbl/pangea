package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T001WEntity extends CommonEntity {

    private String name1;
    private String land1;
    private String nodetype;
    private String werks;
    private String bwkey;

    public T001WEntity(Map<String, Object> map) {
        super(map);
        setName1((String) map.get("name1"));
        setLand1((String) map.get("land1"));
        setNodetype((String) map.get("nodetype"));
        setWerks((String) map.get("werks"));
        setBwkey((String) map.get("bwkey"));
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getLand1() {
        return land1;
    }

    public void setLand1(String land1) {
        this.land1 = land1;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getBwkey() {
        return bwkey;
    }

    public void setBwkey(String bwkey) {
        this.bwkey = bwkey;
    }
}

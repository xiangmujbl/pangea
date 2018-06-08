package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class TvroEntity extends CommonEntity {

    private String trazt;
    private String route;
    private String traztd;

    public TvroEntity(Map<String, Object> map) {
        super(map);

        setTrazt((String) map.get("trazt"));
        setRoute((String) map.get("route"));
        setTraztd((String) map.get("traztd"));
    }

    public String getTrazt() {
        return this.trazt;
    }

    public void setTrazt(String trazt) {
        this.trazt = trazt;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTraztd() {
        return traztd;
    }

    public void setTraztd(String traztd) {
        this.traztd = traztd;
    }
}

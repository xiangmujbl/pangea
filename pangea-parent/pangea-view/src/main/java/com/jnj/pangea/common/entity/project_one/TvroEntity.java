package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class TvroEntity extends CommonEntity {

    private String trazt;
    private String route;

    public TvroEntity(Map<String, Object> map) {
        super(map);

        setTrazt((String) map.get("trazt"));
        setRoute((String) map.get("route"));
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

}

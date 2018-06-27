package com.jnj.pangea.job_dep.domain;

import java.util.ArrayList;
import java.util.List;

public class JobRegionDomain {

    private String jobName;
    private String jobScript;
    private String xmlPath;
    private String mainRegion;
    private String viewRegion;
    private List<String> dependencyList = new ArrayList<>();

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobScript() {
        return jobScript;
    }

    public void setJobScript(String jobScript) {
        this.jobScript = jobScript;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getMainRegion() {
        return mainRegion;
    }

    public void setMainRegion(String mainRegion) {
        this.mainRegion = mainRegion;
    }

    public String getViewRegion() {
        return viewRegion;
    }

    public void setViewRegion(String viewRegion) {
        this.viewRegion = viewRegion;
    }

    public List<String> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<String> dependencyList) {
        this.dependencyList = dependencyList;
    }
}

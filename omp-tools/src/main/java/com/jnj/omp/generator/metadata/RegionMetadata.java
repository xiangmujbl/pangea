package com.jnj.omp.generator.metadata;

public class RegionMetadata {

    private String path;
    private String system;
    private String name;
    private String[] columns;

    public RegionMetadata(String path) {
        this.path = path;
        String[] splitStr = path.split("/");
        this.system = StringUtils.transformToCamelCase(splitStr[1]);
        this.name = StringUtils.transformToCamelCase(splitStr[2]);
    }

    public RegionMetadata(String path, String[] columns) {
        this.path = path;
        String[] splitStr = path.split("/");
        this.system = StringUtils.transformToCamelCase(splitStr[1]);
        this.name = StringUtils.transformToCamelCase(splitStr[2]);
        this.columns = columns;
    }

    public String getFullName() {
        return StringUtils.getFullName(system, name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}
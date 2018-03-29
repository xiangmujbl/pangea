package com.jnj.pangea.generator.metadata;

import java.util.List;

public class CurationMetadata {

    private RegionMetadata mainRegion;
    private RegionMetadata viewRegion;
    private List<RegionMetadata> regions;

    public RegionMetadata getMainRegion() {
        return mainRegion;
    }

    public void setMainRegion(RegionMetadata mainRegion) {
        this.mainRegion = mainRegion;
    }

    public RegionMetadata getViewRegion() {
        return viewRegion;
    }

    public void setViewRegion(RegionMetadata viewRegion) {
        this.viewRegion = viewRegion;
    }

    public List<RegionMetadata> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionMetadata> regions) {
        this.regions = regions;
    }
}
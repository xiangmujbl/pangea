package com.jnj.pangea.omp.gdm_product_location.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductLocationBo extends BaseBo {

    private String productId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String ProductTypeId;
    private String beskz;
    private String bstfe;
    private String bstma;
    private String bstmi;
    private String bstrf;
    private String disls;
    private String dismm;
    private String dispo;
    private String dzeit;
    private String eislo;
    private String fevor;
    private String fixedHorizon;
    //private String frtme;
    private String insmk;
    private String itemPlanningCategory;
    private String kausf;
    private String kzkri;
    private String label;
    private String leadTime;
    private String locationId;
    private String maabc;
    private String minmrsl;
    private String minRemainingShelfLife;
    private String mmsta;
    private String mtvfp;
    //private String replenishmentLotSize;
    private String shflg;
    private String sobsl;
    private String strgr;
    private String supplyGroup;
    private String supplySource;
    private String totalShelfLife;
    private String vint1;
    private String vint2;
    private String vrmod;
    private String webaz;
    private String BatchManaged;
    private String MinInventoryQuantity;
    private String MaxInventoryQuantity;
    private String productValue;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productId", this.productId).add("locationId",this.locationId)
                .toJsonString();
    }

    public String getProductTypeId() {
        return ProductTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        ProductTypeId = productTypeId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return this.activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return this.activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return this.activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getBeskz() {
        return this.beskz;
    }

    public void setBeskz(String beskz) {
        this.beskz = beskz;
    }

    public String getBstfe() {
        return this.bstfe;
    }

    public void setBstfe(String bstfe) {
        this.bstfe = bstfe;
    }

    public String getBstma() {
        return this.bstma;
    }

    public void setBstma(String bstma) {
        this.bstma = bstma;
    }

    public String getBstmi() {
        return this.bstmi;
    }

    public void setBstmi(String bstmi) {
        this.bstmi = bstmi;
    }

    public String getBstrf() {
        return this.bstrf;
    }

    public void setBstrf(String bstrf) {
        this.bstrf = bstrf;
    }

    public String getDisls() {
        return this.disls;
    }

    public void setDisls(String disls) {
        this.disls = disls;
    }

    public String getDismm() {
        return this.dismm;
    }

    public void setDismm(String dismm) {
        this.dismm = dismm;
    }

    public String getDispo() {
        return this.dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getDzeit() {
        return this.dzeit;
    }

    public void setDzeit(String dzeit) {
        this.dzeit = dzeit;
    }

    public String getEislo() {
        return this.eislo;
    }

    public void setEislo(String eislo) {
        this.eislo = eislo;
    }

    public String getFevor() {
        return this.fevor;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getFixedHorizon() {
        return this.fixedHorizon;
    }

    public void setFixedHorizon(String fixedHorizon) {
        this.fixedHorizon = fixedHorizon;
    }

    /*public String getFrtme() {
        return this.frtme;
    }

    public void setFrtme(String frtme) {
        this.frtme = frtme;
    }*/

    public String getInsmk() {
        return this.insmk;
    }

    public void setInsmk(String insmk) {
        this.insmk = insmk;
    }

    public String getItemPlanningCategory() {
        return this.itemPlanningCategory;
    }

    public void setItemPlanningCategory(String itemPlanningCategory) {
        this.itemPlanningCategory = itemPlanningCategory;
    }

    public String getKausf() {
        return this.kausf;
    }

    public void setKausf(String kausf) {
        this.kausf = kausf;
    }

    public String getKzkri() {
        return this.kzkri;
    }

    public void setKzkri(String kzkri) {
        this.kzkri = kzkri;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLeadTime() {
        return this.leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMaabc() {
        return this.maabc;
    }

    public void setMaabc(String maabc) {
        this.maabc = maabc;
    }

    public String getMinmrsl() {
        return this.minmrsl;
    }

    public void setMinmrsl(String minmrsl) {
        this.minmrsl = minmrsl;
    }

    public String getMinRemainingShelfLife() {
        return this.minRemainingShelfLife;
    }

    public void setMinRemainingShelfLife(String minRemainingShelfLife) {
        this.minRemainingShelfLife = minRemainingShelfLife;
    }

    public String getMmsta() {
        return this.mmsta;
    }

    public void setMmsta(String mmsta) {
        this.mmsta = mmsta;
    }

    public String getMtvfp() {
        return this.mtvfp;
    }

    public void setMtvfp(String mtvfp) {
        this.mtvfp = mtvfp;
    }

    /*public String getReplenishmentLotSize() {
        return this.replenishmentLotSize;
    }

    public void setReplenishmentLotSize(String replenishmentLotSize) {
        this.replenishmentLotSize = replenishmentLotSize;
    }*/

    public String getShflg() {
        return this.shflg;
    }

    public void setShflg(String shflg) {
        this.shflg = shflg;
    }

    public String getSobsl() {
        return this.sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public String getStrgr() {
        return this.strgr;
    }

    public void setStrgr(String strgr) {
        this.strgr = strgr;
    }

    public String getSupplyGroup() {
        return this.supplyGroup;
    }

    public void setSupplyGroup(String supplyGroup) {
        this.supplyGroup = supplyGroup;
    }

    public String getSupplySource() {
        return this.supplySource;
    }

    public void setSupplySource(String supplySource) {
        this.supplySource = supplySource;
    }

    public String getTotalShelfLife() {
        return this.totalShelfLife;
    }

    public void setTotalShelfLife(String totalShelfLife) {
        this.totalShelfLife = totalShelfLife;
    }

    public String getVint1() {
        return this.vint1;
    }

    public void setVint1(String vint1) {
        this.vint1 = vint1;
    }

    public String getVint2() {
        return this.vint2;
    }

    public void setVint2(String vint2) {
        this.vint2 = vint2;
    }

    public String getVrmod() {
        return this.vrmod;
    }

    public void setVrmod(String vrmod) {
        this.vrmod = vrmod;
    }

    public String getWebaz() {
        return this.webaz;
    }

    public void setWebaz(String webaz) {
        this.webaz = webaz;
    }

    public String getBatchManaged() {
        return this.BatchManaged;
    }

    public void setBatchManaged(String batchManaged) {
        this.BatchManaged = batchManaged;
    }

    public String getMinInventoryQuantity() {
        return this.MinInventoryQuantity;
    }

    public void setMinInventoryQuantity(String minInventoryQuantity) {
        this.MinInventoryQuantity = minInventoryQuantity;
    }

    public String getMaxInventoryQuantity() {
        return this.MaxInventoryQuantity;
    }

    public void setMaxInventoryQuantity(String maxInventoryQuantity) {
        this.MaxInventoryQuantity = maxInventoryQuantity;
    }

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}


}

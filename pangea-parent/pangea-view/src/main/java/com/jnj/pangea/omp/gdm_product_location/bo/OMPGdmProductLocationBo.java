package com.jnj.pangea.omp.gdm_product_location.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductLocationBo extends BaseBo {

    private String productId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String basmg;
    private String beskz;
    private String bstfe;
    private String bstma;
    private String bstmi;
    private String bstrf;
    private String cost;
    private String disls;
    private String dismm;
    private String dispo;
    private String dzeit;
    private String eisbe;
    private String eislo;
    private String fevor;
    private String fixedhorizon;
    private String frtme;
    private String insmk;
    private String ItemPlanningCategory;
    private String kausf;
    private String kzkri;
    private String lABEL;
    private String leadTime;
    private String locationId;
    private String maabc;
    private String mabst;
    private String minmrsl;
    private String minremainingshelflife;
    private String mmsta;
    private String mtvfp;
    private String peinh;
    private String plifz;
    private String productTypeId;
    private String replenishmentLotsize;
    private String sbdkz;
    private String shelflifeproducttypeid;
    private String shflg;
    private String shzet;
    private String sobsl;
    private String stprs;
    private String strgr;
    private String supplyGroup;
    private String supplySource;
    private String totalshelflife;
    private String verpr;
    private String vint1;
    private String vint2;
    private String vprsv;
    private String vrmod;
    private String webaz;
    private String xchpf;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productId", this.productId).add("locationId",this.locationId)
                .toJsonString();
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

    public String getBasmg() {
        return this.basmg;
    }

    public void setBasmg(String basmg) {
        this.basmg = basmg;
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

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String getEisbe() {
        return this.eisbe;
    }

    public void setEisbe(String eisbe) {
        this.eisbe = eisbe;
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

    public String getFixedhorizon() {
        return this.fixedhorizon;
    }

    public void setFixedhorizon(String fixedhorizon) {
        this.fixedhorizon = fixedhorizon;
    }

    public String getFrtme() {
        return this.frtme;
    }

    public void setFrtme(String frtme) {
        this.frtme = frtme;
    }

    public String getInsmk() {
        return this.insmk;
    }

    public void setInsmk(String insmk) {
        this.insmk = insmk;
    }

    public String getItemPlanningCategory() {
        return this.ItemPlanningCategory;
    }

    public void setItemPlanningCategory(String itemPlanningCategory) {
        this.ItemPlanningCategory = itemPlanningCategory;
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

    public String getLABEL() {
        return this.lABEL;
    }

    public void setLABEL(String lABEL) {
        this.lABEL = lABEL;
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

    public String getMabst() {
        return this.mabst;
    }

    public void setMabst(String mabst) {
        this.mabst = mabst;
    }

    public String getMinmrsl() {
        return this.minmrsl;
    }

    public void setMinmrsl(String minmrsl) {
        this.minmrsl = minmrsl;
    }

    public String getMinremainingshelflife() {
        return this.minremainingshelflife;
    }

    public void setMinremainingshelflife(String minremainingshelflife) {
        this.minremainingshelflife = minremainingshelflife;
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

    public String getPeinh() {
        return this.peinh;
    }

    public void setPeinh(String peinh) {
        this.peinh = peinh;
    }

    public String getPlifz() {
        return this.plifz;
    }

    public void setPlifz(String plifz) {
        this.plifz = plifz;
    }

    public String getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getReplenishmentLotsize() {
        return this.replenishmentLotsize;
    }

    public void setReplenishmentLotsize(String replenishmentLotsize) {
        this.replenishmentLotsize = replenishmentLotsize;
    }

    public String getSbdkz() {
        return this.sbdkz;
    }

    public void setSbdkz(String sbdkz) {
        this.sbdkz = sbdkz;
    }

    public String getShelflifeproducttypeid() {
        return this.shelflifeproducttypeid;
    }

    public void setShelflifeproducttypeid(String shelflifeproducttypeid) {
        this.shelflifeproducttypeid = shelflifeproducttypeid;
    }

    public String getShflg() {
        return this.shflg;
    }

    public void setShflg(String shflg) {
        this.shflg = shflg;
    }

    public String getShzet() {
        return this.shzet;
    }

    public void setShzet(String shzet) {
        this.shzet = shzet;
    }

    public String getSobsl() {
        return this.sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public String getStprs() {
        return this.stprs;
    }

    public void setStprs(String stprs) {
        this.stprs = stprs;
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

    public String getTotalshelflife() {
        return this.totalshelflife;
    }

    public void setTotalshelflife(String totalshelflife) {
        this.totalshelflife = totalshelflife;
    }

    public String getVerpr() {
        return this.verpr;
    }

    public void setVerpr(String verpr) {
        this.verpr = verpr;
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

    public String getVprsv() {
        return this.vprsv;
    }

    public void setVprsv(String vprsv) {
        this.vprsv = vprsv;
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

    public String getXchpf() {
        return this.xchpf;
    }

    public void setXchpf(String xchpf) {
        this.xchpf = xchpf;
    }

}

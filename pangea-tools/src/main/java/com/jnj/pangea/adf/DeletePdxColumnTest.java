package com.jnj.pangea.adf;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.jnj.adf.grid.data.verhist.VerHistKey;
import com.jnj.adf.grid.data.version.VersionKey;
import com.jnj.adf.grid.support.system.ADFConfigHelper;

import java.util.Map;
import java.util.Set;

public class DeletePdxColumnTest {

    public static void main(String[] args) {
        // need set the locator and port where the region belong before run
        String regionPath = "/dev/plan/cns_clusters";
        String locator = "awsamenva1171";
        Integer port = 14001;
        getMetaRegion(regionPath, locator, port);
    }

    public static void getMetaRegion(String regionPath, String locator, Integer port) {

        // directly get region data through proxy.
        // but connect attributes not compatible with ADFTestCaseBase.
        ADFConfigHelper.initConfig();
        ClientCacheFactory cf = new ClientCacheFactory();
        cf.addPoolLocator(locator, port);

        ClientRegionFactory rf = cf.create().createClientRegionFactory(ClientRegionShortcut.PROXY);
        rf.setPoolName(ClientCacheFactory.getAnyInstance().getDefaultPool().getName());
        Region parentRegion = rf.create("_ADF");
        checkVersion(rf, parentRegion, regionPath, true);
        checkVersionHist(rf, parentRegion, regionPath, true);
    }

    public static void checkVersion(ClientRegionFactory rf, Region parentRegion, String regionPath, boolean delete) {
        Region r = rf.createSubregion(parentRegion, "VERSION");

        Set keys = r.keySetOnServer();
        Map values = r.getAll(keys);
        values.forEach((k, v) -> {
            VersionKey key = (VersionKey) k;
            if (regionPath.equals(key.getGridPath())) {
                System.out.println("------------------one version record-----------------------------------");
                System.out.println(k.toString());
                System.out.println(v.toString());
                if (delete)
                    r.remove(k);
            }
        });
    }

    public static void checkVersionHist(ClientRegionFactory rf, Region parentRegion, String regionPath, boolean delete) {
        Region r = rf.createSubregion(parentRegion, "VERHIST");

        Set keys = r.keySetOnServer();
        Map values = r.getAll(keys);
        values.forEach((k, v) -> {
            VerHistKey key = (VerHistKey) k;
            if (regionPath.equals(key.getGridPath())) {
                System.out.println("------------------one version history record-----------------------------------");
                System.out.println(k.toString());
                System.out.println(v.toString());
                if (delete) {
                    r.remove(k);
                }
            }
        });
    }
}

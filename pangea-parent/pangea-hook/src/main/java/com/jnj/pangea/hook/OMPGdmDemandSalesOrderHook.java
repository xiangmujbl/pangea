package com.jnj.pangea.hook;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OMPGdmDemandSalesOrderHook {

    private static class KnvhResHolder {
        private String customerId;
        private String hkunnr;
    }

    public static String determineCustomerId(String kunnr, String localSalesOrg, String localOrderCreateDt) {
        // attempt to find a match at the lowest level record in KNVH
        KnvhResHolder results = findKnvhMatch(kunnr, localSalesOrg, localOrderCreateDt);

        // if no customer is set and there is a hkunnr set - try again at the parent level
        // we stop checking when a customer is found OR when none found and no parent found
        while (StringUtils.isBlank(results.customerId) && StringUtils.isNotBlank(results.hkunnr)) {
            results = findKnvhMatch(results.hkunnr, localSalesOrg, localOrderCreateDt);
        }
        return results.customerId;
    }

    private static KnvhResHolder findKnvhMatch(String kunnr, String localSalesOrg, String localOrderCreateDt) {
        KnvhResHolder results = new KnvhResHolder();
        results.customerId = null;
        results.hkunnr = null;

        // find all matching knvh records based on the kunnr passed in
        List<Map.Entry<String, String>> knvhList = joinKnvhOnKunnr(kunnr);
        // loop over all matching knvh records
        for (Map.Entry<String, String> entry : knvhList) {
            Map<String, Object> knvhRec = JsonObject.append(entry.getValue()).toMap();
            if (knvhRec != null) {
                results.hkunnr = String.valueOf(knvhRec.get("hkunnr"));
                String vkorg = String.valueOf(knvhRec.get("vkorg"));
                String datbi = String.valueOf(knvhRec.get("datbi"));
                String hityp = String.valueOf(knvhRec.get("hityp"));

                if (vkorg.equals(localSalesOrg) && "A".equals(hityp) &&
                        Integer.parseInt(localOrderCreateDt) <= Integer.parseInt(datbi)) {
                    // if we have a match on the above criteria check the demGrpAsgn region
                    Map demGrpAsgnRec = joinCnsDemGrpAsgnCustId(results.hkunnr);
                    if (demGrpAsgnRec != null) {
                        results.customerId = String.valueOf(demGrpAsgnRec.get("demandGroup"));
                    }
                    // we found a match on the above criteria so ignore any other matching records
                    break;
                }
            }
        }
        return results;
    }

    private static List<Map.Entry<String, String>> joinKnvhOnKunnr(String localShipToParty) {
        String queryStr = QueryHelper.buildCriteria("kunnr").is(localShipToParty).toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/project_one/knvh", queryStr, -1);
        if (retList != null && !retList.isEmpty()) {
            return retList;
        }
        return new ArrayList<>();
    }

    private static Map joinCnsDemGrpAsgnCustId(String customerId) {
        String queryStr = QueryHelper.buildCriteria("customerId").is(customerId).toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/plan/cns_dem_grp_asgn", queryStr, -1);
        if (retList != null && !retList.isEmpty()) {
            Map.Entry<String, String> entry = retList.get(0);
            return JsonObject.append(entry.getValue()).toMap();
        }
        return null;
    }
}

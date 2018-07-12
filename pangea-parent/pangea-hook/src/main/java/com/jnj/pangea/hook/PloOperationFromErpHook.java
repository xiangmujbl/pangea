package com.jnj.pangea.hook;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;

import java.util.Map;

public class PloOperationFromErpHook {
   public static boolean  filterraw(RawDataValue raw){
       Boolean flag=false;
       Map<String, Object> map = (Map<String, Object>) raw.toMap();
       if(map.get("prdtnVers")==null ){
        raw.setField( "prdtnVers","");
       }
       ADFCriteria adfCriteria = QueryHelper.buildCriteria("attribute").is("SEND_TO_OMP");
       ADFCriteria adfCriteria1=   QueryHelper.buildCriteria("dataObject").is("ALL");
       ADFCriteria adfCriteria2=   QueryHelper.buildCriteria(  "sourceSystem").is( "CONS_LATAM");
       ADFCriteria adfCriteria3=   QueryHelper.buildCriteria(  "parameter").is( "SYSTEMNAME");
       ADFCriteria adfCriteria4=   QueryHelper.buildCriteria(  "inclExcl").is( "I");
       String str =adfCriteria4.and(adfCriteria3).and(adfCriteria2).and(adfCriteria1).and(adfCriteria).toQueryString();
       Map.Entry<String, Map<String, Object>> cnsmap = AdfViewHelper.queryForMap("/plan/cns_plan_parameter", str);
       if (cnsmap==null || cnsmap.getValue().isEmpty()==true ){flag=true;}
       return flag;
   }
}

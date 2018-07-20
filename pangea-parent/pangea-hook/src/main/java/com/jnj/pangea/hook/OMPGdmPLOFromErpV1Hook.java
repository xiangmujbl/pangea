package com.jnj.pangea.hook;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.hook.common.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class OMPGdmPLOFromErpV1Hook {

    public static String addDateByWorksday(String dtStr, String offset) {
        LogUtil.getCoreLog().info("addDateByWorksday>>");
        String ret = "";
        try{
            Date outpDt = DateUtils.stringToDate(dtStr,DateUtils.F_yyyyMMdd);
            Integer offsetDay = Integer.parseInt(offset);

            for(int i=0;i<Math.abs(offsetDay);i++){
                if(offsetDay>0){
                    outpDt = DateUtils.offsetDay(outpDt,1);
                }else{
                    outpDt = DateUtils.offsetDay(outpDt,-1);
                }
                if(!checkWorkday(outpDt)){
                    i--;
                }
            }

            ret = DateUtils.dateToString(outpDt,DateUtils.yyyy_MM_dd_HHmmss_2);
        }catch (NumberFormatException ex){
            LogUtil.getCoreLog().error(ex.getMessage());
        }
        return ret;
    }

    private static boolean checkWorkday(Date dt){
        Boolean ret = true;
        Calendar cld = Calendar.getInstance();
        cld.setTime(dt);

        int index = cld.get(Calendar.DAY_OF_WEEK);
        if(index == Calendar.SUNDAY ||index == Calendar.SATURDAY){
            ret = false;
        }
        return ret;
    }

    public static void main(String[] args){
        long l0 = System.currentTimeMillis();
        System.out.println(addDateByWorksday("20180531","-10000"));
        System.out.println("cost:"+(System.currentTimeMillis()-l0));
        System.out.println(addDateByWorksday("20180531","10"));
        System.out.println(addDateByWorksday("20180531","0"));
    }
}

package com.jnj.pangea.ce;

import com.jnj.adf.TestLocal;
import com.jnj.adf.client.api.ADFService;
import com.jnj.adf.config.annotations.EnableADF;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.utils.SpringBeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * Created by KDing5 on 2018/6/27 13:15(GMT+8).
 */
public class LocalDebug {

    @EnableADF
    protected static class ADFConfig {
        protected ADFConfig() {
        }
    }

    static {
        ApplicationContext applicationContext = SpringBeanUtils.initContext(LocalDebug.ADFConfig.class);
        ADFService adfService = (ADFService) applicationContext.getBean(ADFService.class);
        String userName = ADFConfigHelper.getProperty("login.name");
        String password = ADFConfigHelper.getProperty("login.password");
        adfService.login(userName, password);
    }

    public static void main(String[] args) {

        new TestLocal().testLocal("OMPProductCountry.xml");
    }
}

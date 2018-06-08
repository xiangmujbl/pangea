package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public abstract class BaseController implements IEventProcessor {

    public BaseController() {
        initEnv();
    }

    private void initEnv() {
        try {
            Properties properties = new Properties();
            URL url = getClass().getClassLoader().getResource("view.properties");
            if (null != url) {
                properties.load(url.openStream());
                String env = properties.getProperty("env");
                FailData.ENV = env;
                LogUtil.getCoreLog().info("################################ set env = " + env + " ####################################");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

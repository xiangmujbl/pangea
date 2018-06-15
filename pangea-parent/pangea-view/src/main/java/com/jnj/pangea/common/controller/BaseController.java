package com.jnj.pangea.common.controller;

import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseController implements IEventProcessor {

    public BaseController() {
        initEnv();
    }

    private void initEnv() {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = this.getClass().getClassLoader();
            if (null != classLoader) {
                InputStream inputStream = classLoader.getResourceAsStream("view.properties");
                if (null != inputStream) {
                    properties.load(inputStream);
                    String env = properties.getProperty("env");
                    FailData.ENV = env;
                    LogUtil.getCoreLog().info("################################ set env = " + env + " ####################################");
                } else {
                    LogUtil.getCoreLog().info("################################ inputStream is null ####################################");
                }
            } else {
                LogUtil.getCoreLog().info("################################  classLoader is null  ####################################");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

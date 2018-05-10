package com.jnj.pangea.sentence.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class XdJobLaunch {

    private static final Logger logger = LoggerFactory.getLogger(XdJobLaunch.class);

    private static ClassPathXmlApplicationContext context = null;

    public static boolean run(String propName, Map<String, String> param, String xdXml) {

        try {

            Properties props = new Properties();
            props.load(new FileInputStream(new File(XdJobLaunch.class.getResource("/").getPath() + propName)));

            props.putAll(param);

            context = new ClassPathXmlApplicationContext();
            context.refresh();

            registerDataBase();
            registerJobRepo();
            registerJobLauncher();
            initDb();

            context.getBeanFactory().preInstantiateSingletons();

            ClassPathXmlApplicationContext childContext = new ClassPathXmlApplicationContext(
                    new String[]{"file:///" + XdJobLaunch.class.getResource("/").getPath() + xdXml}, false, context);


            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setIgnoreResourceNotFound(true);
            pspc.setProperties(props);

            childContext.addBeanFactoryPostProcessor(pspc);
            childContext.refresh();

            JobLauncher jobLauncher = childContext.getBean(JobLauncher.class);

            String jobName = "test" + System.currentTimeMillis();
            Job job = childContext.getBean(Job.class);
            JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
            jobParametersBuilder.addString("name", jobName);
            JobParameters jobParam = jobParametersBuilder.toJobParameters();
            JobExecution exec = jobLauncher.run(job, jobParam);
            if (BatchStatus.COMPLETED.equals(exec.getStatus())) {
                return true;
            }

            childContext.close();
            context.close();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return false;
    }

    public static void registerDataBase() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder dbDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BasicDataSource.class);
        dbDefinitionBuilder.addPropertyValue("driverClassName", "org.hsqldb.jdbc.JDBCDriver");
        dbDefinitionBuilder.addPropertyValue("url", "jdbc:hsqldb:file:batch/batch.repo;sql.enforce_strict_size=true");
        dbDefinitionBuilder.addPropertyValue("username", "sa");
        dbDefinitionBuilder.addPropertyValue("password", "");
        beanFactory.registerBeanDefinition("dataSource", dbDefinitionBuilder.getBeanDefinition());

        dbDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceTransactionManager.class);
        dbDefinitionBuilder.addPropertyReference("dataSource", "dataSource");
        beanFactory.registerBeanDefinition("transactionManager", dbDefinitionBuilder.getBeanDefinition());
    }

    public static void registerJobRepo() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(JobRepositoryFactoryBean.class);
        beanDefinitionBuilder.addPropertyReference("dataSource", "dataSource");
        beanDefinitionBuilder.addPropertyReference("transactionManager", "transactionManager");
        beanFactory.registerBeanDefinition("jobRepository", beanDefinitionBuilder.getBeanDefinition());
    }

    public static void registerJobLauncher() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(SimpleJobLauncher.class);
        beanDefinitionBuilder.addPropertyReference("jobRepository", "jobRepository");
        beanFactory.registerBeanDefinition("jobLauncher", beanDefinitionBuilder.getBeanDefinition());
    }

    public static void initDb() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(DataSourceInitializer.class);
        beanDefinitionBuilder.addPropertyReference("dataSource", "dataSource");

        ResourceDatabasePopulator dp = new ResourceDatabasePopulator(
                new ClassPathResource("/org/springframework/batch/core/schema-drop-hsqldb.sql"),
                new ClassPathResource("/org/springframework/batch/core/schema-hsqldb.sql"));
        beanDefinitionBuilder.addPropertyValue("databasePopulator", dp);
        beanFactory.registerBeanDefinition("dbInit", beanDefinitionBuilder.getBeanDefinition());
    }
}

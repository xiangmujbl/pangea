package com.jnj.pangea.xdtool;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.xd.rest.client.SpringXDOperations;
import org.springframework.xd.rest.client.impl.SpringXDTemplate;
import org.springframework.xd.rest.domain.JobDefinitionResource;
import org.springframework.xd.rest.domain.JobExecutionInfoResource;

import java.net.*;

public class XDHelper {
    private static final String USERNAME = "jguo57";
    private static final String PASSWD = "123456";
    private static final String XD_URL = "http://awsamenva1175.jnj.com:9393";
    public static void main(String[] args){
        URI uri = URI.create(XD_URL);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(null, -1),
                new UsernamePasswordCredentials(USERNAME, PASSWD));
        HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        SpringXDOperations springXDOperations = new SpringXDTemplate(httpComponentsClientHttpRequestFactory,uri);

//        createJob(springXDOperations,"testDeploy","triggerCeModule --dateFormat=yyyy-MM-dd --makeUnique=true --xml=pangea/xml/edm/country.xml --executeFile=pangea/pangea-view.jar --computingNode=awsamenva1176:2551,awsamenva1177:2551 --launcherCommand=\"\" --partition=1 --waitJobDone=true --projectName=pangea",true);
//        launchJob(springXDOperations,"Pangea_Dev_exportCsv_plan_cns_prod_country_aff","");
        checkJobStatus(springXDOperations,"Pangea_Dev_exportCsv_plan_cns_prod_country_aff");

    }

    private static void launchJob(SpringXDOperations springXDOperations,String jobName,String json){
        springXDOperations.jobOperations().launchJob(jobName,json);
    }

    private static void createJob(SpringXDOperations springXDOperations,String jobName,String defination,boolean deploy){
        JobDefinitionResource jobDefinitionResource = springXDOperations.jobOperations().createJob(jobName,defination,deploy);
        System.out.println(jobDefinitionResource.getName());
    }

    private static void  checkJobStatus(SpringXDOperations springXDOperations,String jobName){
        PagedResources<JobExecutionInfoResource>  jobExecutionInfoResources =springXDOperations.jobOperations().listJobExecutions();
        for(JobExecutionInfoResource jobExecutionInfoResource:jobExecutionInfoResources){
            if(jobExecutionInfoResource.getName().equalsIgnoreCase(jobName)){

                System.out.println("jobname:"+jobExecutionInfoResource.getName());
                System.out.println("startdate:"+jobExecutionInfoResource.getStartDate()+" "+jobExecutionInfoResource.getStartTime());
                System.out.println("status:"+jobExecutionInfoResource.getJobExecution().getStatus());
                System.out.println("duration:"+jobExecutionInfoResource.getDuration());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }


}

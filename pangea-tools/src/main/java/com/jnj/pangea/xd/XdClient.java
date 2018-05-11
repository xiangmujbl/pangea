package com.jnj.pangea.xd;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.batch.core.JobExecution;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.xd.rest.client.impl.SpringXDTemplate;
import org.springframework.xd.rest.domain.JobExecutionInfoResource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by KDing5 on 2018/3/29 16:24(GMT+8).
 */
public class XdClient {

    private static final long CHECK_INTERVAL = 2000;

    private String host;
    private String port;
    private String userName;
    private String password;

    private SpringXDTemplate template;

    public XdClient(String host, String userName, String password) {
        this.host = host;
        this.port = "9393";
        this.userName = userName;
        this.password = password;
        login();
    }

    private void login() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.userName, this.password));
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build());
        try {
            template = new SpringXDTemplate(requestFactory, new URI("http://" + this.host + ":" + this.port));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void deploy(String name, String definition){
        System.out.println("deploy job: " + name);
        deleteIfExist(name);
        template.jobOperations().createJob(name, definition, true);
    }

    public void runSny(String name, String definition) {
        System.out.println("launch job: " + name);
        deleteIfExist(name);
        template.jobOperations().createJob(name, definition, true);
        template.jobOperations().launchJob(name, null);
    }

    public boolean runAsny(String name, String definition) {
        System.out.println("launch job: " + name);
        deleteIfExist(name);
        template.jobOperations().createJob(name, definition, true);
        template.jobOperations().launchJob(name, null);
        // it seems that the XD does not provided the method to get the job's executionId, so we get it by filtered the list.
        List<JobExecutionInfoResource> jobExecutionInfoResourceList = template.jobOperations().listJobExecutions().getContent()
                .stream()
                .filter(jobExecutionInfoResource -> name.equals(jobExecutionInfoResource.getName()))
                .collect(Collectors.toList());
        // just get the latest execution
        if (null != jobExecutionInfoResourceList && !jobExecutionInfoResourceList.isEmpty()) {
            long jobExecutionId = jobExecutionInfoResourceList.iterator().next().getExecutionId();
            return checkStatus(jobExecutionId);
        }
        return false;
    }

    private void deleteIfExist(String name) {
        try {
            template.jobOperations().destroy(name);
        } catch (Exception e) {

        }
    }

    private boolean checkStatus(long executionId) {
        boolean isFinished = false;
        do {
            try {
                Thread.sleep(CHECK_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JobExecution jobExecution = template.jobOperations().displayJobExecution(executionId).getJobExecution();
            if (jobExecution.getStatus().isUnsuccessful()) {
                jobExecution.getAllFailureExceptions().forEach(Throwable::printStackTrace);
                return false;
            }
            if (!jobExecution.getStatus().isRunning()) {
                isFinished = true;
            }
        } while (!isFinished);
        return true;
    }
}

package com.jnj.pangea;

import com.jnj.adf.curation.ComputeClient;
import com.jnj.adf.grid.utils.LogUtil;

import java.util.Scanner;

public class DropJob {
    public static void main(String[] args) {
        ComputeClient client = new ComputeClient();
        client.connect("awsamenva3010:2551,awsamenva3011:2551");
        String jobId = "Cucumber-test-EDMAdvancedShipNotification-1527782094454";
        Scanner scanner=new Scanner(System.in);
        do {
            String result=client.dropJob(jobId);
            LogUtil.getCoreLog().info(result);
            jobId=scanner.nextLine();
        } while (true);
    }
}
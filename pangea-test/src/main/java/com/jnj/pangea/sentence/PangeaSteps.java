package com.jnj.pangea.sentence;

import com.jnj.adf.cucumber.sentence.CommonSteps;
import com.jnj.adf.curation.ComputeClient;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.utils.Util;
import org.apache.commons.lang.StringUtils;

public class PangeaSteps extends CommonSteps {

    private static String computingNode = "";
    private static Integer computingPartition = 1;

    static {
        computingNode = ADFConfigHelper.getProperty("computingNode");
        String partition = ADFConfigHelper.getProperty("computingPartition");
        if (StringUtils.isNotEmpty(partition))
            computingPartition = Integer.parseInt(partition);
    }

    public PangeaSteps() {

        When("^I submit task with xml file \"([^\"]*)\" and execute file \"([^\"]*)\"$", (String xml, String executeFile) -> {

            submitComputeTask(xml, executeFile);
        });

        Given("^I compare the number of records between \"([^\"]*)\" and \"([^\"]*)\"$", (String compare1, String compare2) -> {

            System.out.println(compare1 + " : " + compare2);
        });
    }

    private void submitComputeTask(String xml, String drl) {
        ComputeClient client = null;
        try {
            String name = "";
            if (xml.contains("/")) {
                String[] arr = xml.split("/");
                name = arr[arr.length - 1].substring(0, arr[arr.length - 1].length() - 4);
            } else {
                name = xml.substring(0, xml.length() - 4);
            }

            String taskId = "Cucumber-test-" + name + "-" + System.currentTimeMillis();
            String commandString = "-type F";
            if (computingPartition == null || computingPartition == 0) {
                computingPartition = 1;
            }
            client = new ComputeClient();
            client.connect(computingNode);
            client.submitTask(taskId, xml, drl, commandString, computingPartition);

            boolean done = false;
            String status = null;
            int sleep = 5000;
            do {
                Util.sleep(sleep);
                done = client.isJobDone(taskId);
                status = client.checkJobStatus(taskId);
                System.out.println("Job:{" + taskId + "} done:{" + done + "} status:{" + status + "} ");
            } while (!done);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}

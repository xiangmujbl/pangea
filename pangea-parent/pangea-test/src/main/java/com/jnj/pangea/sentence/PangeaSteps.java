package com.jnj.pangea.sentence;

import com.jnj.adf.curation.ComputeClient;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.utils.Util;
import com.jnj.pangea.common.CommonSteps;
import com.jnj.pangea.common.utils.XdJobLaunch;
import cucumber.api.DataTable;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PangeaSteps extends CommonSteps {

    private static String computingNode = "";
    private static Integer computingPartition = 1;
    private static String kafkaSink = "";
    private static String ceRegionAlias = "";

    private static String mergeJobDefinition = "xd/fileMerger.xml";
    private static String mergeJobParameter = "xd/context.properties";

    static {
        computingNode = ADFConfigHelper.getProperty("computingNode");
        String partition = ADFConfigHelper.getProperty("computingPartition");
        if (StringUtils.isNotEmpty(partition))
            computingPartition = Integer.parseInt(partition);
        kafkaSink = ADFConfigHelper.getProperty("kafkaSink");
        ceRegionAlias = ADFConfigHelper.getProperty("ceRegionAlias");
    }

    public PangeaSteps() {

        When("^I submit task with xml file \"([^\"]*)\" and execute file \"([^\"]*)\"$", (String xml, String executeFile) -> {

            submitComputeTask(xml, executeFile);
        });

        When("^I submit aggregation task with xml file \"([^\"]*)\"", (String xml) -> {

            submitComputeTask(xml, null);
        });

        Given("^I compare the number of records between \"([^\"]*)\" and \"([^\"]*)\"$", (String compare1, String compare2) -> {

            long count1 = 0;
            String[] region1 = compare1.split(",");
            for (String region : region1) {
                count1 += adfService.onPath(getRealRegionPath(region)).queryOql("select * from " + getRealRegionPath(region)).size();
            }

            long count2 = 0;
            String[] region2 = compare2.split(",");
            for (String region : region2) {
                count2 += adfService.onPath(getRealRegionPath(region)).queryOql("select * from " + getRealRegionPath(region)).size();
            }
            Assert.assertEquals(count1, count2);
        });

        And("^I will remove all data with region \"([^\"]*)\"$", (String region) -> {
            adfService.onPath(getRealRegionPath(region)).removeAll();
        });


        Then("^A file is found on sink application with name \"([^\"]*)\"$", (String fileName) -> {
            retrieveFile(fileName);
        });

        And("^I check file data for filename \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String fileName, String keyFields, DataTable table) -> {

            File file = new File(fileName);
            if (!file.exists()) {
                file = retrieveFile(fileName);
            }
            this.checkFileData(table.raw(), keyFields.split(","), file);
        });

        And("^I will remove the test file on sink application \"([^\"]*)\"$", (fileName) -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://" + kafkaSink + "/api/file/" + fileName);
        });

        And("^I execute xd job to merge file \"([^\"]*)\" to \"([^\"]*)\" by keyFields \"([^\"]*)\"$",
                (String filePattern, String targetFileName, String keys) -> {

                    Map<String, String> param = new HashMap<>();

                    String path = System.getProperty("user.dir") + "/";
                    param.put("tSourceLocation", path);
                    param.put("mLocation", path);
                    param.put("outputDirectory", path);
                    param.put("sPattern", filePattern);
                    param.put("tFileName", targetFileName);
                    param.put("outputFileName", targetFileName);
                    param.put("keyString", keys);

                    XdJobLaunch.run(mergeJobParameter, param, mergeJobDefinition);
                });
    }

    private File retrieveFile(String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange("http://" + kafkaSink + "/api/file/" + fileName, HttpMethod.GET, entity, byte[].class, "1");

        File file = new File(fileName);
        FileOutputStream output = null;
        if (response.getStatusCode().equals(HttpStatus.OK)) {

            try (FileOutputStream fileOutputStream = output = new FileOutputStream(file)) {
                IOUtils.write(response.getBody(), output);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }

        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.length() > 0);

        return file;
    }

    private void checkFileData(List<List<String>> testDataList, String[] keyFields, File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            //updated to remove call to readline() before entering while loop - we were skipping the first line
            String line = null;
            int[] headerMap = null;
            boolean headersFound = false;

            // read first line from file to create header map.
            while ((line = bufferedReader.readLine()) != null) {

                List<String> targetLine = testDataList.get(0);
                List<String> fileLine = Arrays.asList(line.split("\t", -1));
                if (fileLine.size() != targetLine.size()) {
                    System.err.println("Column Count Doesn't Match.\nExpected:" + targetLine.size() + "\nActual:" + fileLine.size());
                }
                Assert.assertEquals(targetLine.size(), fileLine.size()); //check we have all columns

                // all headers present
                headersFound = targetLine.containsAll(fileLine);

                if (headersFound) {

                    headerMap = new int[fileLine.size()];

                    for (int targetIndex = 0; targetIndex < targetLine.size(); targetIndex++) {    // for each header in test data
                        for (int fileIndex = 0; fileIndex < fileLine.size(); fileIndex++) {     // for each header in file data
                            if (targetLine.get(targetIndex).equals(fileLine.get(fileIndex))) {   // find the target header in the file headers
                                headerMap[targetIndex] = fileIndex;
                            }
                        }
                    }
                }

                if (!headersFound) {
                    System.err.println("Headers Don't Match.\nExpected:" + Arrays.toString(targetLine.toArray()) + "\nActual:" + Arrays.toString(fileLine.toArray()));
                }

                Assert.assertTrue(headersFound);
                break;
            }

            if (headersFound) {

                // for each line in the output file
                while ((line = bufferedReader.readLine()) != null) {

                    List<String> fileLine = Arrays.asList(line.split("\t", -1));

                    // compare record
                    boolean recordFound = false;

                    // for each line of test data
                    for (int i = 0; i < testDataList.size(); i++) {

                        // for each column of test data line try to match the values of the record
                        for (int x = 0; x < testDataList.get(i).size(); x++) {

                            String target = testDataList.get(i).get(x);
                            String fileValue = fileLine.get(headerMap[x]);  // get mapped column from file

                            recordFound = target.equals(fileValue);

                            if (!recordFound) {    //if this line doesnt match file's line go to next line
                                break;
                            }
                        }

                        if (recordFound) {  // if file line matched continue to next file line
                            break;
                        }
                    }

                    if (!recordFound) {
                        System.err.println("Record Not Found:\n" + Arrays.toString(fileLine.toArray()));
                    }

                    Assert.assertTrue(recordFound);
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void submitComputeTask(String xml, String jar) {

        ComputeClient client = null;
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
        try {

            client = new ComputeClient();
            client.connect(computingNode);

            if (StringUtils.isNotEmpty(ENV) && StringUtils.isNotEmpty(ceRegionAlias)) {
                client.submitTask(taskId, xml, jar, commandString, ceRegionAlias, computingPartition);
            } else {
                if (StringUtils.isEmpty(jar)) {
                    commandString = "-type Aggr";
                }
                client.submitTask(taskId, xml, jar, commandString, computingPartition);
            }

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
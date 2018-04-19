package com.jnj.pangea.sentence;

import com.jnj.adf.cucumber.sentence.CommonSteps;
import com.jnj.adf.curation.ComputeClient;
import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.grid.utils.Util;
import cucumber.api.DataTable;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PangeaSteps extends CommonSteps {

    private static String computingNode = "";
    private static Integer computingPartition = 1;
    private static String mboxSink = "";
    private static String bootstrapServers = "";
    private static String schemaRegistryUrl = "";
    private static String topic = "";

    static {
        computingNode = ADFConfigHelper.getProperty("computingNode");
        String partition = ADFConfigHelper.getProperty("computingPartition");
        if (StringUtils.isNotEmpty(partition))
            computingPartition = Integer.parseInt(partition);
        mboxSink = ADFConfigHelper.getProperty("mboxSink");
        bootstrapServers = ADFConfigHelper.getProperty("bootstrap.servers");
        schemaRegistryUrl = ADFConfigHelper.getProperty("schema.registry.url");
        topic = ADFConfigHelper.getProperty("kafka.topic");
    }

    public PangeaSteps() {

        When("^I submit task with xml file \"([^\"]*)\" and execute file \"([^\"]*)\"$", (String xml, String executeFile) -> {

            submitComputeTask(xml, executeFile);
        });

        Given("^I compare the number of records between \"([^\"]*)\" and \"([^\"]*)\"$", (String compare1, String compare2) -> {

            long count1 = 0;
            String[] region1 = compare1.split(",");
            for (String region : region1) {
                count1 += adfService.onPath(region).count();
            }

            long count2 = 0;
            String[] region2 = compare2.split(",");
            for (String region : region2) {
                count2 += adfService.onPath(region).count();
            }
            Assert.assertEquals(count1, count2);
        });

        And("^I will remove all data with region \"([^\"]*)\"$", (String region) -> {
            adfService.onPath(region).removeAll();
        });


        Then("^A file is found on sink application with name \"([^\"]*)\"$", (String fileName) -> {
            retrieveFile(fileName);
        });

        And("^I check file data for filename \"([^\"]*)\" by keyFields \"([^\"]*)\"$", (String fileName, String keyFields, DataTable table) -> {
            File file = retrieveFile(fileName);
            this.checkFileData(table.raw(), keyFields.split(","), file);
        });

        And("^I will remove the test file on sink application \"([^\"]*)\"$", (fileName) -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://" + mboxSink + "/api/file/" + fileName);
        });
    }

    private File retrieveFile(String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange("http://" + mboxSink + "/api/file/" + fileName, HttpMethod.GET, entity, byte[].class, "1");

        File file = new File(fileName);
        FileOutputStream output = null;
        if(response.getStatusCode().equals(HttpStatus.OK))
        {

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

    private void checkFileData(List<List<String>> list, String[] keyFields, File file) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                //updated to remove call to readline() before entering while loop - we were skipping the first line
                String line = null;
                int count = 0;
                // check headers

                while ((line = bufferedReader.readLine()) != null) {
                    // check record
                    List<String> fileList = Arrays.asList(line.split("\t"));
                    Assert.assertEquals(fileList.size(), list.get(count).size());
                    Assert.assertTrue(list.get(count).containsAll(fileList));

                    count++;
                }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void submitComputeTask(String xml, String drl) {
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
            sendRecord(name+".tsv", "START_FILE");

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
            sendRecord(name+".tsv", "END_FILE");
            if (client != null) {
                client.close();
            }
        }
    }

    private void sendRecord(String fileName, String type) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put("schema.registry.url", schemaRegistryUrl);
        try (KafkaProducer producer = new KafkaProducer(props)){
            String startKeySchema = "{\"type\":\"record\",\"name\":\"keySchema\",\"fields\":[{\"name\":\"_OP_TYPE_\",\"type\":\"string\"}]}";
            String gdmFileSchema = "{\"type\":\"record\",\"name\":\"fileSchema\",\"fields\":[{\"name\":\"fileName\",\"type\":\"string\"}]}";

            Schema.Parser parser = new Schema.Parser();
            Schema schema = parser.parse(startKeySchema);
            GenericRecord keyRecord = new GenericData.Record(schema);
            keyRecord.put("_OP_TYPE_", type);

            parser = new Schema.Parser();
            schema = parser.parse(gdmFileSchema);
            GenericRecord avroRecord = new GenericData.Record(schema);
            avroRecord.put("fileName", fileName);
            ProducerRecord<Object, Object> record = new ProducerRecord<Object, Object>(topic, keyRecord, avroRecord);

            try {
                producer.send(record);
            } catch (SerializationException e) {
                // may need to do something with it
            }
        }
    }

}
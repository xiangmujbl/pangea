package com.jnj.pangea.generator;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by KDing5 on 2018/7/10 14:59(GMT+8).
 */
public class TopicsPropertiesGenerator {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("pangea-parent\\pangea-test\\src\\main\\installation\\topic_maps.properties"));

        List<String> topics = new ArrayList<>();
        List<String> topicsMaps = new ArrayList<>();
        List<String> topicsLocation = new ArrayList<>();

        properties.forEach((key, value) -> {

            String topicName = (String) key;
            String fileName = (String) value;
            topics.add(topicName);
            topicsMaps.add(topicName + ":'" + fileName + "'");
            topicsLocation.add(topicName + ":'${static.file.location}'");
        });

        System.out.println("\ntopics=" + String.join(",", topics));
        System.out.println("\nfilenamePrefix.map={" + String.join(",", topicsMaps) + "}\n");
        System.out.println("filePath.map={" + String.join(",", topicsLocation) + "}\n\n");
    }
}

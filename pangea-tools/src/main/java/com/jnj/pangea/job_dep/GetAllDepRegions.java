package com.jnj.pangea.job_dep;

import com.jnj.pangea.generator.metadata.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by KDing5 on 2018/7/9 14:27(GMT+8).
 */
public class GetAllDepRegions {

    public static void main(String[] args) {

        String xmlDir = "pangea-parent\\pangea-view\\src\\main\\resources\\curation\\";
        List<String> xmlPath = new ArrayList<>();
        getAllXmlPath(xmlDir, xmlPath);

        printAllTopics(xmlPath);
    }

    private static void getAllXmlPath(String parent, List<String> xmlPath) {

        File file = new File(parent);
        List<File> fileList = new ArrayList<File>();

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                getAllXmlPath(f.getAbsolutePath(), xmlPath);
            }
        }
        for (File f1 : fileList) {
            xmlPath.add(f1.getAbsolutePath());
        }
    }

    private static void getRegions(String xmlPath, List<String> regionList) {

        try {
            Document doc = new SAXReader().read(new FileInputStream(new File(xmlPath)));
            Element curation = doc.getRootElement().element("curation");
            Element regions = curation.element("regions");

            regions.elements().forEach(e -> {
                Element region = (Element) e;
                String path = region.attributeValue("path");
                if (!regionList.contains(path)) {
                    regionList.add(path);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getTopics(String xmlPath, List<String> topicList) {

        try {
            Document doc = new SAXReader().read(new FileInputStream(new File(xmlPath)));
            Element curation = doc.getRootElement().element("curation");
            String topic = curation.attributeValue("topic");
            if (!StringUtils.isEmpty(topic) && !topicList.contains(topic)) {
                topicList.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAllRegions(List<String> xmlPath) {

        List<String> allRegions = new ArrayList<>();
        xmlPath.forEach(path -> getRegions(path, allRegions));

        Collections.sort(allRegions);
        System.out.println("TOTAL REGIONS: " + allRegions.size());
        allRegions.forEach(System.out::println);
    }

    private static void printAllTopics(List<String> xmlPath) {

        List<String> allTopics = new ArrayList<>();
        xmlPath.forEach(path -> getTopics(path, allTopics));

        Collections.sort(allTopics);
        System.out.println("TOTAL TOPICS: " + allTopics.size());
        allTopics.forEach(System.out::println);
    }
}

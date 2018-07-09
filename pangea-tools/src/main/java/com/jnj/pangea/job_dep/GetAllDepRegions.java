package com.jnj.pangea.job_dep;

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

        List<String> allRegions = new ArrayList<>();
        xmlPath.forEach(path -> getRegions(path, allRegions));

        Collections.sort(allRegions);
        System.out.println("TOTAL REGIONS: " + allRegions.size());
        allRegions.forEach(System.out::println);
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
}

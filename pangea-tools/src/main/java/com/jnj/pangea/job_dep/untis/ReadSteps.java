package com.jnj.pangea.job_dep.untis;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadSteps {

    public static Map<String, String> readDirectory(String directoryPath) {
        File file = new File(directoryPath);
        Map<String, String> result = new HashMap<>();
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                Map<String, String> map = readProp(f.getPath());
                result.putAll(map);
            }
            return result;
        } else {
            return readProp(directoryPath);
        }
    }

    public static Map<String, String> readProp(String stepPath) {
        Map<String, String> map = new HashMap<>();
        try {
            Path path = Paths.get(stepPath);
            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                if (!StringUtils.isEmpty(line)) {

                    String name = "";
                    String definition = "";
                    String xmlPath = "";

                    Matcher nameMatcher = Pattern.compile("--name.+--definition").matcher(line);
                    if (nameMatcher.find()) {
                        name = nameMatcher.group(0);
                        name = name.replace("--name", "").replace("--definition", "").trim();
                    }

                    Matcher xmlMatcher = Pattern.compile("--xml.+xml").matcher(line);
                    if (xmlMatcher.find()) {
                        xmlPath = xmlMatcher.group(0);
                        xmlPath = xmlPath.replace("--xml=", "").trim();
                        xmlPath = xmlPath.replaceAll("pangea/xml", "");
                    }
                    if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(xmlPath)) {
                        map.put(name, xmlPath);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        String pathRoot = System.getProperty("user.dir");
        String stepPath = pathRoot + "/job-manager/src/main/resources/steps/20180521QA_job_steps.txt";
        readProp(stepPath);


    }
}

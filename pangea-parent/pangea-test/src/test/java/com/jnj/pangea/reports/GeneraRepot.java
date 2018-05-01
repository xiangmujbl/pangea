package com.jnj.pangea.reports;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneraRepot {

    public static void main(String[] arg) {

        String pathRoot = System.getProperty("user.dir");
        String path = pathRoot + "/pangea-parent/pangea-test/target/reports/json/";
        File fileParent = new File(path);
        File[] fileList = fileParent.listFiles();
        for (File file : fileList) {
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            createReport(pathRoot, fileName);
        }
    }

    private static void createReport(String pathRoot, String fileName) {

        String filePath = pathRoot + "/pangea-parent/pangea-test/target/reports/html/";
        File reportOutputDirectory = new File(filePath);
        List<String> jsonFiles = new ArrayList<>();

        String jsonPath = pathRoot + "/pangea-parent/pangea-test/target/reports/json/" + fileName + ".json";
        jsonFiles.add(jsonPath);

        String projectName = fileName;
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        configuration.setParallelTesting(parallelTesting);
        configuration.setRunWithJenkins(runWithJenkins);

        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "chrome");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

        File oldFile = new File(filePath + "cucumber-html-reports");

        String newFilePath = filePath + fileName;
        File newFile = new File(newFilePath);

        boolean flag = oldFile.renameTo(newFile);

        if (flag) {
            FileToZip.zipDirectory(newFilePath);
        }

    }

}

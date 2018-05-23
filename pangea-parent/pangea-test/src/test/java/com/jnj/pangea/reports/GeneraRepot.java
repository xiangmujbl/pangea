package com.jnj.pangea.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class GeneraRepot {

    public static void main(String[] arg){
        String path = "C:\\bigdata\\source\\pangea\\pangea-parent\\pangea-test\\target\\reports\\json";
        File fileParent = new File(path);
        File[] fileList = fileParent.listFiles();
        for (File file:fileList) {
            String fileName = file.getName().substring(0,file.getName().lastIndexOf("."));
            createReport(fileName);
        }
    }

    private static void createReport(String fileName){

        String filePath = "C:/bigdata/source/pangea/pangea-parent/pangea-test/target/reports/html/";
        File reportOutputDirectory = new File(filePath);
        List<String> jsonFiles = new ArrayList<>();

        String jsonPath = "C:/bigdata/source/pangea/pangea-parent/pangea-test/target/reports/json/"+fileName+".json";
        jsonFiles.add(jsonPath);

        String buildNumber = "1";
        String projectName = fileName;
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration
        configuration.setParallelTesting(parallelTesting);
        configuration.setRunWithJenkins(runWithJenkins);
        //configuration.setBuildNumber(buildNumber);
        // addidtional metadata presented on main page
        configuration.addClassifications("Platform","Windows");
        configuration.addClassifications("Browser","chrome");
        //configuration.addClassifications("Branch","release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
        // and here validate 'result' to decide what to do
        // if report has failed features, undefined steps etc}

        File oldFile = new File(filePath+"cucumber-html-reports");

        String newFilePath = filePath+fileName;
        File newFile = new File(newFilePath);

        boolean flag = oldFile.renameTo(newFile);

        if (flag){
            FileToZip.zipDirectory(newFilePath);
        }

    }

}
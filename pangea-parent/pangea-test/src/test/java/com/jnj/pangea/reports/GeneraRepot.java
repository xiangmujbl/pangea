package com.jnj.pangea.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jnj.pangea.reports.FileToZip;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class GeneraRepot {
	
	public static void main(String[] arg){
		createReport("EDMBrand","AEAZ-1273");
	}
    //AEAZ-1272 EDMSubBrand
    //AEAZ-1273 EDMBrand
    //AEAZ-1271 EDMCategory
    //AEAZ-1276 EDMForm
    //AEAZ-1274 EDMFranchise
    //AEAZ-1277 EDMGlobalBaseUnit
    //AEAZ-1279 EDMMatPlantStat
    //AEAZ-1275 EDMProductFamily
	private static void createReport(String fileName,String fileNumber){

        String filePath = "pangea/pangea-parent/pangea-test/target/reports/html/";
        File reportOutputDirectory = new File(filePath);
        List<String> jsonFiles = new ArrayList<>();

        String jsonPath = "pangea/pangea-parent/pangea-test/target/reports/json/"+fileName+".json";
        jsonFiles.add(jsonPath);

        String buildNumber = "1";
        String projectName = fileNumber+" "+fileName;
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

        String newFilePath = filePath+fileNumber+" "+fileName;
        File newFile = new File(newFilePath);

        boolean flag = oldFile.renameTo(newFile);

        if (flag){
            FileToZip.zipDirectory(newFilePath);
        }

	}

}

package com.jnj.pangea.job_dep;

import com.jnj.pangea.job_dep.domain.JobRegionDomain;
import com.jnj.pangea.job_dep.untis.ReadSteps;

import java.util.*;

import static com.jnj.pangea.job_dep.untis.XmlTransfer.getRegionList;

public class DependencyCompute {

    private static String pathRoot = System.getProperty("user.dir");


    public static void computeDependencyRegionList(String stepPath, String regionPath, String xmlRootPath) {

        Map<String, String> map = ReadSteps.readDirectory(stepPath);

        //1. get all the view region and dependency list
        Map<String, String> jobAndViewRegion = new HashMap<>();

        List<String> viewRegionList = new ArrayList<>();
        Map<String, JobRegionDomain> allRegionList = new HashMap<>();
        map.forEach((jobName, xmlPath) -> {

            JobRegionDomain jobRegionDomain = getRegionList(pathRoot + xmlRootPath + xmlPath);
            if (null != jobRegionDomain) {
                String viewRegionPath = jobRegionDomain.getViewRegion();
                viewRegionList.add(viewRegionPath);

                allRegionList.put(jobName, jobRegionDomain);
                jobAndViewRegion.put(jobName, viewRegionPath);

            }
        });

        //2. remove the raw region
        Iterator<String> iterator = allRegionList.keySet().iterator();
        Map<String, JobRegionDomain> newRegionList = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            JobRegionDomain jobRegionDomain = allRegionList.get(key);
            List<String> dependencyList = jobRegionDomain.getDependencyList();
            List<String> newDependencyList = new ArrayList<>();
            for (String region : dependencyList) {
                if (viewRegionList.contains(region) && !region.equalsIgnoreCase(jobRegionDomain.getViewRegion())) {
                    newDependencyList.add(region);
                }
            }
            if (newDependencyList.size() > 0) {
                jobRegionDomain.setDependencyList(newDependencyList);
                newRegionList.put(key, jobRegionDomain);
            }

        }

        //3. compute
        Map<String, JobRegionDomain> resultMap = new HashMap<>();
        findDependencyRegionList(regionPath, resultMap, newRegionList);

    }

    public static void findDependencyRegionList(String regionPath, Map<String, JobRegionDomain> resultList, Map<String, JobRegionDomain> allRegionList) {
        Iterator<String> iterator = allRegionList.keySet().iterator();
        Map<String, JobRegionDomain> continueCompute = new HashMap<>();
        Map<String, JobRegionDomain> currentResult = new HashMap<>();

        while (iterator.hasNext()) {
            String jobName = iterator.next();
            JobRegionDomain jobRegionDomain = allRegionList.get(jobName);

            List<String> dependencyList = jobRegionDomain.getDependencyList();

            if (dependencyList.contains(regionPath)) {
                if (!resultList.containsKey(jobName)) {
                    currentResult.put(jobName, jobRegionDomain);
                    continueCompute.put(jobName, jobRegionDomain);
                }
            }
        }

        if (currentResult.size() > 0) {
            Iterator iterator1 = currentResult.keySet().iterator();
            System.out.println("============= step============= ");
            while (iterator1.hasNext()) {
                System.out.println(iterator1.next());
            }
        }

        if (continueCompute.size() > 0) {
            resultList.putAll(currentResult);
            Iterator<String> continueComputeIterator = continueCompute.keySet().iterator();
            while (continueComputeIterator.hasNext()) {
                String jobName = continueComputeIterator.next();
                JobRegionDomain jobRegionDomain = continueCompute.get(jobName);
                findDependencyRegionList(jobRegionDomain.getViewRegion(), resultList, allRegionList);
            }
        }
    }

    private static void callCompute(String region) {
        String path = "/pangea-tools/src/main/resources/job_script/";
        String xmlRootPath = "/pangea-parent/pangea-test/src/test/resources/xml/";
        String stepPath = pathRoot + path;
        computeDependencyRegionList(stepPath, region, xmlRootPath);
    }

    public static void main(String[] args) {
        String region = "/edm/plant_v1";
        callCompute(region);

    }
}

package com.jnj.pangea.job_dep;

import com.jnj.pangea.job_dep.domain.JobRegionDomain;
import com.jnj.pangea.job_dep.untis.ReadSteps;

import java.util.*;

import static com.jnj.pangea.job_dep.untis.XmlTransfer.getRegionList;

public class HierarchyCompute {

    private static String pathRoot = System.getProperty("user.dir");

    public static void compute(String stepPath, String xmlRootPath) {

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

        System.out.println("allRegionList = " + allRegionList.size());

        //2. remove the raw region
        int minCount = -1;
        Iterator<String> iterator = allRegionList.keySet().iterator();
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
            if (minCount == -1) {
                minCount = newDependencyList.size();
            } else if (newDependencyList.size() < minCount) {
                minCount = newDependencyList.size();
            }

            jobRegionDomain.setDependencyList(newDependencyList);
            allRegionList.put(key, jobRegionDomain);
        }

        //3. compute
        Map<String, String> resultMap = new HashMap<>();
        computeView(minCount, jobAndViewRegion, viewRegionList, resultMap, allRegionList);
    }

    public static void computeView(int minCount, Map<String, String> jobAndViewRegion, List<String> viewRegionList, Map<String, String> resultMap, Map<String, JobRegionDomain> regionList) {

        Iterator<String> regionIterator = regionList.keySet().iterator();
        Map<String, JobRegionDomain> remain = new HashMap<>();
        List<String> stepResult = new ArrayList<>();
        Map<String, String> currentResultMap = new HashMap<>();

        while (regionIterator.hasNext()) {
            String jobName = regionIterator.next();
            JobRegionDomain jobRegionDomain = regionList.get(jobName);
            List<String> dependencyList = jobRegionDomain.getDependencyList();

            List<String> newDependencyList = new ArrayList<>();
            for (int i = 0; i < dependencyList.size(); i++) {
                String dependency = dependencyList.get(i);
                if (viewRegionList.contains(dependency)) {
                    // 是否已经在上一个step
                    if (!isContainViewRegion(dependency, resultMap)) {
                        newDependencyList.add(dependency);
                    }
                } else {
                    remain.put(jobName, jobRegionDomain);
                    continue;
                }
            }

            if (newDependencyList.size() == 0) {
                // add to queue

                currentResultMap.put(jobName, jobRegionDomain.getViewRegion());
                stepResult.add(jobName);

            } else if (newDependencyList.size() == minCount) {

                currentResultMap.put(jobName, jobRegionDomain.getViewRegion());
                stepResult.add(jobName);
            } else {
                jobRegionDomain.setDependencyList(newDependencyList);
                remain.put(jobName, jobRegionDomain);
            }
        }

        resultMap.putAll(currentResultMap);

        System.out.println("============== new step");
        stepResult.forEach(jobName -> {
            System.out.println(jobName);
        });

        if (remain.size() > 0) {
            computeView(minCount, jobAndViewRegion, viewRegionList, resultMap, remain);
        }
    }

    private static boolean isContainViewRegion(String region, Map<String, String> map) {
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (region.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        String path = "/pangea-tools/src/main/resources/job_script/";
        String stepPath = pathRoot + path;
        String xmlRootPath = "/pangea-parent/pangea-test/src/test/resources/xml/";
        compute(stepPath,xmlRootPath);
    }
}

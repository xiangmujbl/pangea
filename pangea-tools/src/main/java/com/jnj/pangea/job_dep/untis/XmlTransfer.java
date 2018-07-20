package com.jnj.pangea.job_dep.untis;

import com.jnj.pangea.job_dep.domain.JobRegionDomain;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class XmlTransfer {


    public static JobRegionDomain getRegionList(String xmlPath) {
        try {

            Document document = Dom4jUtils.readXml(xmlPath);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            Element curation = rootElement.element("curation");
            String viewRegion = (String) curation.attribute("viewRegion").getData();
            String mainRegion = (String) curation.attribute("mainRegion").getData();
            Element regions = curation.element("regions");
            List<Element> regionList = regions.elements();

            List<String> list = new ArrayList<>();
            for (Element element : regionList) {
                String regionPath = element.attributeValue("path");
                if (!list.contains(regionPath)) {
                    list.add(regionPath);
                }
            }

            JobRegionDomain jobRegionDomain = new JobRegionDomain();
            jobRegionDomain.setDependencyList(list);
            jobRegionDomain.setViewRegion(viewRegion);
            jobRegionDomain.setMainRegion(mainRegion);
            jobRegionDomain.setXmlPath(xmlPath);
            return jobRegionDomain;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String pathRoot = System.getProperty("user.dir");
        String path = pathRoot + "/job-manager/src/main/resources/curation/plan/PlanCnsTlaneItem.xml";
        getRegionList(path);
    }
}

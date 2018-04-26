package com.jnj.pangea.generator;

import java.util.Arrays;
import java.util.List;

public class GenerateTool {

    public static List<String> ABBR = Arrays.asList("EDM", "GDM", "OMP");

    public static void main(String[] args) {

      ViewCreator viewCreator = new ViewCreator("edm", "purchase_requisition_v1", "", false);

        // first step
        viewCreator.generateTestJavaFile();
        viewCreator.generateCurationXMLFile();
        // fill the xml, and run below
        viewCreator.generateFeature();
        viewCreator.generateBo();
        viewCreator.generateEntityAndDao();
        viewCreator.generateController();
        viewCreator.generateService();

        viewCreator.appendNewRegionPath();
        viewCreator.generateRegionGridXML();
    }
}

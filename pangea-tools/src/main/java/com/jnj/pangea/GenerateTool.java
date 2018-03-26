package com.jnj.pangea;

public class GenerateTool {

    public static void main(String[] args) {

        ViewCreator viewCreator = new ViewCreator("system", "test_view", "AEAZ-123", true);
        // if the name contains an abbreviation, use following
//        ViewCreator viewCreator = new ViewCreator("system", "TestView", "test_view", "SystemTestView", "AEAZ-123", true);

        // first step
        viewCreator.generateTestJavaFile();
        viewCreator.generateTestFeatureFile();
        viewCreator.generateCurationXMLFile();
        // fill the xml, and run below
        viewCreator.generateCurationCodeFile();
    }
}

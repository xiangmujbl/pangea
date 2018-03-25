package com.jnj.pangea;

public class GenerateTool {

    public static void main(String[] args) {

        ViewCreator viewCreator = new ViewCreator("system", "test_view", "AEAZ-123", true);
        viewCreator.generateTestJavaFile();
        viewCreator.generateTestFeatureFile();
        viewCreator.generateCurationXMLFile();
        viewCreator.generateCurationCodeFile();
    }
}

package com.jnj.pangea;

import com.jnj.pangea.metadata.CurationMetadata;
import com.jnj.pangea.metadata.RegionMetadata;
import com.jnj.pangea.metadata.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jnj.pangea.metadata.StringUtils.capFirst;
import static com.jnj.pangea.metadata.StringUtils.transformToCamelCase;

public class ViewCreator {

    private static final String TEST_DIR = "pangea-parent/pangea-test/src/test/java/com/jnj/pangea/";
    private static final String FEATURE_DIR = "pangea-parent/pangea-test/src/test/resources/features/";
    private static final String XML_DIR = "pangea-parent/pangea-view/src/main/resources/curation/";
    private static final String PROCESSOR_DIR = "pangea-parent/pangea-view/src/main/java/com/jnj/pangea/";

    private static final String REGIONS_TXT = "pangea-parent/pangea-test/src/main/installation/listRegion/regions.txt";

    private String system; // edm
    private String name; // TestView
    private String _name; // test_view
    private String fullName;// EDMTestView
    private String jira; // AEAZ-123

    private boolean forceOverride = false;
    private String xmlPath;
    private Map<String, Object> param;

    private CurationMetadata curationMetadata;

    private Configuration configuration;

    public ViewCreator(String system, String _name, String jira, boolean forceOverride) {

        this.system = system;
        this.name = transformToCamelCase(_name);
        this._name = _name;
        this.fullName = capFirst(system) + name;
        this.jira = jira;
        this.forceOverride = forceOverride;
        init();
    }

    public ViewCreator(String system, String name, String _name, String fullName, String jira, boolean forceOverride) {

        this.system = system;
        this.name = name;
        this._name = _name;
        this.fullName = fullName;
        this.jira = jira;
        this.forceOverride = forceOverride;
        init();
    }

    private void init() {

        this.param = new HashMap<String, Object>() {{
            put("system", system);
            put("name", name);
            put("_name", _name);
            put("fullName", fullName);
            put("jira", jira);
        }};

        try {
            configuration = new Configuration(Configuration.VERSION_2_3_22);
            configuration.setDirectoryForTemplateLoading(new File("pangea-tools/src/main/resources/template"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTestJavaFile() {
        String path = TEST_DIR + this.system + "/";
        String fileName = this.fullName + ".java";
        generateFileWithFtl(path, fileName, "test_java.ftl", null);
    }

    public void generateTestFeatureFile() {
        String path = FEATURE_DIR + this.system + "/";
        String fileName = this.fullName + ".feature";
        generateFileWithFtl(path, fileName, "test_feature.ftl", null);
    }

    public void generateCurationXMLFile() {
        String path = XML_DIR + this.system + "/";
        String fileName = this.fullName + ".xml";
        generateFileWithFtl(path, fileName, "curation_xml.ftl", null);
        xmlPath = path + fileName;
        appendNewRegionPath();
    }

    public void generateCurationCodeFile() {

        curationMetadata = getCurationMetadata(xmlPath);
        if (null == curationMetadata) {
            return;
        }
        String basePath = PROCESSOR_DIR + this.system + "/" + this._name + "/";

        // bo
        String boPath = basePath + "bo";
        new File(boPath).mkdirs();
        String boFileName = this.fullName + "Bo.java";
        Map<String, Object> extraParam = new HashMap<>();
        extraParam.put("boFields", curationMetadata.getViewRegion().getColumns());
        generateFileWithFtl(boPath, boFileName, "curation_bo.ftl", extraParam);
        // entity & dao
        List<Map<String, String>> entities = new ArrayList<>();
        curationMetadata.getRegions().forEach(region -> {

            String _entityPath = PROCESSOR_DIR + "common" + "/entity/" + region.getSystem() + "/";
            new File(_entityPath).mkdirs();
            Map<String, Object> extra = new HashMap<>();
            extra.put("entitySystem", region.getSystem());
            extra.put("entityName", region.getName());
            extra.put("entityFields", region.getColumns());
            generateFileWithFtl(_entityPath, capFirst(region.getName()) + "Entity.java", "curation_entity.ftl", extra);

            String _daoPath = PROCESSOR_DIR + "common" + "/dao/impl/" + region.getSystem() + "/";
            new File(_daoPath).mkdirs();
            generateFileWithFtl(_daoPath, capFirst(region.getName()) + "DaoImpl.java", "curation_dao.ftl", extra);

            entities.add(new HashMap<String, String>() {{
                put("system", region.getSystem());
                put("name", region.getName());
            }});
        });
        // controller
        String controllerPath = basePath + "controller";
        new File(controllerPath).mkdirs();
        String controllerFileName = this.fullName + "Controller.java";
        extraParam = new HashMap<>();
        extraParam.put("mainEntitySystem", curationMetadata.getViewRegion().getSystem());
        extraParam.put("mainEntityName", curationMetadata.getViewRegion().getName());
        generateFileWithFtl(controllerPath, controllerFileName, "curation_controller.ftl", extraParam);
        // service
        String servicePath = basePath + "service";
        new File(servicePath).mkdirs();
        String serviceFileName = this.fullName + "ServiceImpl.java";
        extraParam.put("entities", entities);
        generateFileWithFtl(servicePath, serviceFileName, "curation_service.ftl", extraParam);
    }

    public void appendNewRegionPath() {

        curationMetadata = getCurationMetadata(xmlPath);
        if (null == curationMetadata) {
            return;
        }

        Path path = Paths.get(REGIONS_TXT);
        try {
            List<String> lines = Files.readAllLines(path);
            Files.write(path, "\r\n\n".getBytes(), StandardOpenOption.APPEND);
            curationMetadata.getRegions().forEach(region -> {
                try {
                    if (!lines.contains(region.getPath())) {
                        Files.write(path, (region.getPath() + "\r\n").getBytes(), StandardOpenOption.APPEND);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * generate file with ftl template
     * @param path
     * @param fileName
     * @param ftlFile
     * @param extraParam
     */
    private void generateFileWithFtl(String path, String fileName, String ftlFile, Map<String, Object> extraParam) {

        // concat params
        Map<String, Object> _param = new HashMap<String, Object>();
        _param.putAll(this.param);
        if (null != extraParam) {
            _param.putAll(extraParam);
        }

        String file = path + "/" + fileName;
        try {
            new File(path).mkdirs();
            if (!new File(file).exists() || forceOverride && !fileName.endsWith("xml")) {
                Template template = configuration.getTemplate(ftlFile);
                FileWriter fileWriter = new FileWriter(file);
                template.process(_param, fileWriter);
                System.out.println("generated... " + file);
            } else {
                System.err.println("exist...     " + file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CurationMetadata getCurationMetadata(String xml) {

        CurationMetadata curationMetadata = new CurationMetadata();
        try {
            Document doc = new SAXReader().read(new FileInputStream(new File(xml)));
            Element curation = doc.getRootElement().element("curation");

            String mainRegion = curation.attributeValue("mainRegion");
            String viewRegion = curation.attributeValue("viewRegion");
            Element regions = curation.element("regions");
            if (StringUtils.isEmpty(mainRegion) || StringUtils.isEmpty(viewRegion) || regions.elements().size() == 0) {
                System.err.println("please fill the xml first...");
                return null;
            }

            List<RegionMetadata> regionList = new ArrayList<>();
            regions.elements().forEach(e -> {
                Element region = (Element) e;
                String path = region.attributeValue("path");
                String[] columns = region.element("columns").getTextTrim().split(",");
                RegionMetadata regionMetadata = new RegionMetadata(path, columns);
                regionList.add(regionMetadata);
                if (path.equals(mainRegion)) {
                    curationMetadata.setMainRegion(regionMetadata);
                }
                if (path.equals(viewRegion)) {
                    curationMetadata.setViewRegion(regionMetadata);
                }
            });
            curationMetadata.setRegions(regionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curationMetadata;
    }
}

package com.jnj.pangea.generator;

import com.jnj.pangea.generator.metadata.CurationMetadata;
import com.jnj.pangea.generator.metadata.RegionMetadata;
import com.jnj.pangea.generator.metadata.StringUtils;
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

import static com.jnj.pangea.generator.metadata.StringUtils.capFirst;
import static com.jnj.pangea.generator.metadata.StringUtils.transformToCamelCase;

public class ViewCreator {

    private static final String TEST_DIR = "pangea-parent/pangea-test/src/test/java/com/jnj/pangea/";
    private static final String FEATURE_DIR = "pangea-parent/pangea-test/src/test/resources/features/";
    private static final String XML_DIR = "pangea-parent/pangea-view/src/main/resources/curation/";
    private static final String PROCESSOR_DIR = "pangea-parent/pangea-view/src/main/java/com/jnj/pangea/";

    private static final String REGIONS_TXT = "pangea-parent/pangea-test/src/main/installation/listRegion/regions.txt";
    private static final String REGIONS_XML = "pangea-parent/pangea-test/src/test/resources/grid-server.xml";

    private String system; // edm
    private String name; // TestView
    private String _name; // test_view
    private String fullName;// EDMTestView
    private String jira; // AEAZ-123

    private boolean forceOverride = false;
    private Map<String, Object> param;

    private CurationMetadata curationMetadata;

    private Configuration configuration;

    public ViewCreator(String system, String _name, String jira, boolean forceOverride) {

        this.system = system;
        this.name = capFirst(transformToCamelCase(_name));
        this._name = _name;
        this.fullName = StringUtils.getFullName(system, name);
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

    public void generateCurationXMLFile() {
        String path = XML_DIR + this.system + "/";
        String fileName = this.fullName + ".xml";
        generateFileWithFtl(path, fileName, "curation_xml.ftl", null);

        curationMetadata = getCurationMetadata(path + fileName);
        if (null == curationMetadata) {
            return;
        }
        appendNewRegionPath();
    }

    public void generateBo() {

        String boPath = PROCESSOR_DIR + this.system + "/" + this._name + "/" + "bo";
        String boFileName = this.fullName + "Bo.java";

        Map<String, Object> extraParam = new HashMap<>();
        extraParam.put("boFields", curationMetadata.getViewRegion().getColumns());
        generateFileWithFtl(boPath, boFileName, "curation_bo.ftl", extraParam);
    }

    public void generateEntityAndDao() {

        String mainPath = curationMetadata.getMainRegion().getPath();
        String viewPath = curationMetadata.getViewRegion().getPath();
        curationMetadata.getRegions().forEach(region -> {
            if (!region.getPath().equals(viewPath)) {

                String _entityPath = PROCESSOR_DIR + "common" + "/entity/" + region.getSystem() + "/";
                String _daoPath = PROCESSOR_DIR + "common" + "/dao/impl/" + region.getSystem() + "/";

                Map<String, Object> extraParam = new HashMap<>();
                extraParam.put("entity", new HashMap<String, Object>() {{
                    put("system", region.getSystem());
                    put("fields", region.getColumns());
                    put("fullName", region.getFullName());
                }});

                generateFileWithFtl(_entityPath, region.getFullName() + "Entity.java", "curation_entity.ftl", extraParam);
                if (!region.getPath().equals(mainPath)) {
                    generateFileWithFtl(_daoPath, region.getFullName() + "DaoImpl.java", "curation_dao.ftl", extraParam);
                }
            }
        });
    }

    public void generateController() {

        String controllerPath = PROCESSOR_DIR + this.system + "/" + this._name + "/" + "controller";
        String controllerFileName = this.fullName + "Controller.java";

        Map<String, Object> extraParam = new HashMap<>();
        extraParam.put("main", new HashMap<String, Object>() {{
            put("system", curationMetadata.getMainRegion().getSystem());
            put("name", curationMetadata.getMainRegion().getName());
            put("fullName", curationMetadata.getMainRegion().getFullName());
        }});

        generateFileWithFtl(controllerPath, controllerFileName, "curation_controller.ftl", extraParam);
    }

    public void generateService() {

        String servicePath = PROCESSOR_DIR + this.system + "/" + this._name + "/" + "service";
        String serviceFileName = this.fullName + "ServiceImpl.java";

        Map<String, Object> extraParam = new HashMap<>();
        List<Map<String, String>> entities = new ArrayList<>();
        curationMetadata.getRegions().forEach(region -> {
            if (!region.getPath().equals(curationMetadata.getViewRegion().getPath())) {
                entities.add(new HashMap<String, String>() {{
                    put("system", region.getSystem());
                    put("name", region.getName());
                    put("fullName", region.getFullName());
                }});
            }
        });
        extraParam.put("entities", entities);
        extraParam.put("main", new HashMap<String, Object>() {{
            put("name", curationMetadata.getMainRegion().getName());
            put("fullName", curationMetadata.getMainRegion().getFullName());
        }});

        generateFileWithFtl(servicePath, serviceFileName, "curation_service.ftl", extraParam);
    }

    public void generateFeature() {

        String path = FEATURE_DIR + this.system + "/";
        String fileName = this.fullName + ".feature";

        Map<String, Object> extraParam = new HashMap<>();
        List<Map<String, Object>> entities = new ArrayList<>();
        curationMetadata.getRegions().forEach(region -> {
            if (!region.getPath().equals(curationMetadata.getViewRegion().getPath())) {
                entities.add(new HashMap<String, Object>() {{
                    put("path", region.getPath());
                    put("fields", region.getColumns());
                }});
            }
        });
        extraParam.put("entities", entities);
        extraParam.put("main", new HashMap<String, Object>() {{
            put("path", curationMetadata.getMainRegion().getPath());
        }});
        extraParam.put("view", new HashMap<String, Object>() {{
            put("path", curationMetadata.getViewRegion().getPath());
            put("fields", curationMetadata.getViewRegion().getColumns());
        }});

        generateFileWithFtl(path, fileName, "test_feature.ftl", extraParam);
    }

    public void appendNewRegionPath() {

        Path path = Paths.get(REGIONS_TXT);
        try {
            List<String> lines = Files.readAllLines(path);
            Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            curationMetadata.getRegions().forEach(region -> {
                try {
                    if (!lines.contains(region.getPath())) {
                        Files.write(path, (region.getPath() + "\n").getBytes(), StandardOpenOption.APPEND);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateRegionGridXML() {

        try {
            BuildGridServerXml.build(REGIONS_TXT, REGIONS_XML);
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

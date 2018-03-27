package com.jnj.pangea;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BuildGridServerXml {

    public static void main(String[] args) throws Exception {
        build("C:\\work\\workspace\\pangea\\pangea-parent\\pangea-test\\src\\main\\installation\\listRegion\\regions.txt", "C:\\work\\workspace\\pangea\\pangea-parent\\pangea-test\\src\\test\\resources\\grid-server.xml");
    }

    public static void build(String sourceFile, String targetFile) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File(sourceFile)));
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        Set<String> set;
        String line = null;
        String[] split = new String[1024];
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            if ("".equals(line)) {
                continue;
            }
            split = line.split("/");
            if (map.size() == 0) {
                set = new HashSet();
                set.add(split[2]);
                map.put(split[1], set);
                continue;
            }
            if (map.containsKey(split[1])) {
                Set<String> set1 = map.get(split[1]);
                set1.add(split[2]);
            } else {
                set = new HashSet();
                set.add(split[2]);
                map.put(split[1], set);
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(targetFile)));
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "\t   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "\t   xmlns:util=\"http://www.springframework.org/schema/util\" xmlns:adf=\"http://www.springframework.org/schema/jnj/adf\"\n" +
                "\t   xmlns:adf-grid=\"http://www.springframework.org/schema/jnj/adf-grid\"\n" +
                "\t   xmlns:gfe=\"http://www.springframework.org/schema/gemfire\"\n" +
                "\t   xsi:schemaLocation=\"http://www.springframework.org/schema/jnj/adf http://www.springframework.org/schema/jnj/adf/spring-adf.xsd\n" +
                "\t\thttp://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "\t\thttp://www.springframework.org/schema/jnj/adf-grid http://www.springframework.org/schema/jnj/adf/spring-adf-grid.xsd\n" +
                "\t\thttp://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "\t\thttp://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\n" +
                "\t\thttp://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd\n" +
                "\t\thttp://www.springframework.org/schema/gemfire http://www.springframework.org/schema/gemfire/spring-gemfire.xsd\">\n" +
                "\n" +
                "\t<adf-grid:disk-store id=\"samplePerist\" max-oplog-size=\"100\">\n" +
                "\t\t<adf-grid:disk-dir location=\"#{WorkPath.diskStore('samplePerist')}\" />\n" +
                "\t</adf-grid:disk-store>\n" +
                "\n" +
                "\t<gfe:partitioned-region id=\"_compute_routing\" total-buckets=\"113\" copies=\"0\" persistent=\"false\"/>\n" +
                "\n" +
                "\t<gfe:replicated-region id=\"hotdeploy\">\n" +
                "\t\t<gfe:replicated-region name=\"rawDataFilterTestResult\" statistics=\"true\" />\n" +
                "\t</gfe:replicated-region>");
        Set<Map.Entry<String, Set<String>>> entrySet = map.entrySet();
        for (Map.Entry<String, Set<String>> entry : entrySet) {
            sb.append("\n\r");
            sb.append("\t");
            sb.append("<adf-grid:replicated-region id=\"" + entry.getKey() + "\">");
            Set<String> valueSet = entry.getValue();
            sb.append("\n\r");
            for (String value : valueSet) {
                sb.append("\t\t");
                sb.append("<adf-grid:partitioned-region name=\"" + value + "\" enable-temporal=\"false\" enable-lucene=\"true\" persistent=\"${persistent.enable}\" disk-store-ref=\"samplePerist\" />");
                sb.append("\n\r");
            }
            sb.append("\t");
            sb.append("</adf-grid:replicated-region>");
            sb.append("\n\r");
        }
        sb.append("\n\r");
        sb.append("</beans>");
        writer.write(sb.toString());
        writer.close();
    }
}

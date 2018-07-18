package com.jnj.pangea.job_dep.untis;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Dom4jUtils {

    public static Document readXml(String xmlPath) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(new File(xmlPath));
    }

    public static Document readXml(File file) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(file);
    }

    public static void getNodeValue(Element ParentNode, String nodeName) {
        List<Element> elements = ParentNode.elements(nodeName);
        elements.forEach(e -> {
            String text = e.getText();
            System.out.println(text);
        });
    }

    public static void listNodes(Element node) {
        System.out.println("current node：" + node.getName());
        List<Attribute> list = node.attributes();
        for (Attribute attribute : list) {
            System.out.println("attribute->" + attribute.getName() + ":" + attribute.getValue());
        }
        if (!(node.getTextTrim().equals(""))) {
            System.out.println(node.getName() + "：" + node.getText());
        }
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e);
        }
    }
}

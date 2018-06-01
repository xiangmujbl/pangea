package com.jnj.pangea.xd;

import com.jnj.pangea.generator.metadata.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KDing5 on 2018/3/29 16:41(GMT+8).
 */
public class XDTool {

    private static String USER_NAME = null;
    private static String PASSWORD = null;
    private static String HOST = "awsamenva3025.jnj.com";

    public static void main(String[] args) {

        // HOW TO:
        // 1 - if USER_NAME or PASSWORD are left null, the config file will be checked for them.
        // 2 - if you edit USER_NAME or PASSWORD above, they will be used instead.

        // * see how to set up config file in the PasswordEncoder class

        Map<String, String> job2Run = readScripts();

        if (USER_NAME == null) {
            USER_NAME = PasswordEncoder.getPropertiesFile().getProperty("user");
        }

        if (PASSWORD == null) {
            PASSWORD = PasswordEncoder.getPropertiesFile().getProperty("pass");
        }

        XdClient xdClient = new XdClient(HOST, USER_NAME, PASSWORD);

        job2Run.forEach((name, definition) -> {
            System.out.println("launch job: " + name);
            if (xdClient.runAsny(name, definition)) {
                System.out.println("execute success: " + name);
            } else {
                System.err.println("execute error: " + name);
            }
        });
    }

    private static Map<String, String> readScripts() {

        Map<String, String> map = new HashMap<>();
        Path path = Paths.get("pangea-tools/src/main/resources/xd_script/job2run");
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                if (!StringUtils.isEmpty(line)) {

                    String name = "";
                    String definition = "";

                    Matcher nameMatcher = Pattern.compile("--name.+--definition").matcher(line);
                    if (nameMatcher.find()) {
                        name = nameMatcher.group(0);
                    }
                    name = name.replace("--name", "").replace("--definition", "").trim();

                    Matcher definitionMatcher = Pattern.compile("'.+'").matcher(line);
                    if (definitionMatcher.find()) {
                        definition = definitionMatcher.group(0);
                    }
                    definition = definition.substring(1, definition.length() - 1).trim();

                    map.put(name, definition);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}



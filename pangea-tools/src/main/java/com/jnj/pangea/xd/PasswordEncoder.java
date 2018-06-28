package com.jnj.pangea.xd;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class PasswordEncoder {

	private static String filePath = "pangea-tools/src/main/resources/xd_script/config.properties";
	private static Properties props = new Properties();

	public static void main (String[] args) {

		// HOW TO:

		// 1 - update the file "pangea-tools/src/main/resources/xd_script/config.properties"
		// with your username and password in plain text (if not there, create it)

		// 2 - run this program to encrypt the password

		replacePassword();
	}

	public static String encode (String plain) {
		String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());
		return new StringBuffer(b64encoded).reverse().toString();
	}

	private static void replacePassword () {
		String pass = readPassword();
		if (pass != null && !pass.isEmpty()) {
			writePassword(encode(pass));
		}
	}

	public static String readPassword () {
		Properties props = getPropertiesFile();
		return props.getProperty("pass");
	}

	public static String readUserName () {
		Properties props = getPropertiesFile();
		return props.getProperty("user");
	}

	public static Properties getPropertiesFile () {
		File file = new File(filePath);

		if(file.exists() && !file.isDirectory()) {
			try (FileReader fr = new FileReader(file)) {
				props.load(fr);
				return props;
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		} else {
			System.err.println("Missing config.properties file.");
		}
		return null;
	}

	public static void writePassword (String pass) {
		File file = new File(filePath);
		if(file.exists() && !file.isDirectory()) {
			try (FileWriter fw = new FileWriter(file)) {
				props.setProperty("pass",pass);
				props.store(fw,null);
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		} else {
			System.err.println("Missing config.properties file.");
		}
	}

	public static String decode(String secret) {
		String reversed = new StringBuffer(secret).reverse().toString();
		String decoded = "";
		try {
			decoded = new String(Base64.getDecoder().decode(reversed));
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return decoded;
	}

}

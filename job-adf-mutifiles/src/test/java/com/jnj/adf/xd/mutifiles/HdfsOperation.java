package com.jnj.adf.xd.mutifiles;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Simple Driver to read/write to hdfs. // It uses Kerberos, no SSL. Fully
 * tested.
 * 
 * Notes: Remember to set VM argument: -Djava.security.krb5.conf=krb5.conf To
 * debug: -Dsun.security.krb5.debug=true
 * 
 * https://gist.github.com/ashrithr/f7899fdfd36ee800f151
 *
 * Tested: add test.csv hdfs:/dev/edl/sc/sce2e/str/sce2e_stg/t3 read
 * /dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv This line is the same as the next
 * 2 lines!!! read hdfs:/dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv read
 * hdfs://itsusraedld01.jnj.com/dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv
 */
public class HdfsOperation {
//	private static final String kPrincipal = "sae2etld@JNJ.COM";
	private static final String kPrincipal = "sapproed@NA.JNJ.COM";
//	private static final String kPrincipal = "sbhatt42@NA.JNJ.COM";
//	private static final String kPrincipal = "sasce2ed@JNJ.COM";
//	private static final String kPrincipal = "saclsaed@AP.JNJ.COM";
//	private static final String kPrincipal = "saaddyed@NA.JNJ.COM";
	private static final String keyPath = "C:/deploy/hdfsConfig/sapproed.keytab";
	
	public static final String KEYTAB_FILE_KEY = "hdfs.keytab.file";
	public static final String USER_NAME_KEY = "hdfs.kerberos.principal";
	
	public static final String logPath = "c:/out/hdfsFiles.txt";
	
	private static String pathFile = "C:\\tmp\\in.txt";

	public static void main(String[] args) throws Exception {
		Configuration conf = KerberosLogin("hdfs://nameservice1",kPrincipal,keyPath,"C:/env");
//		String dir = "/dev/edl/sc/sce2e/str/sce2e_stg/AAYE-3343";
//		mkdir(dir, conf);
//		deleteFile(conf);
//		addFile("C:/out/files/btb_latam/ankb/btb_latam_ankb_1.parquet","/prod/edl/infra/servicenow/str/servicenow_stg/cmdb_ci_service/",conf);
		listFiles();
//		addCompleteFile();
	}

	public static void testConnection() throws Exception {
		Configuration conf = setConf();
		FileSystem fileSystem = FileSystem.get(conf);
		FileSystem.printStatistics();
		System.out.println(fileSystem.getHomeDirectory());
		System.out.println(fileSystem.getScheme());
		System.out.println(fileSystem.getUri());
	}

	public static void addCompleteFile() throws IOException{
		Configuration conf = setConf();
		List<String> filest =  getPath();
		for(String file : filest){
			String endFile = file.split(",")[0];
			String filePath = file.split(",")[1];
			File ef  = new File(endFile);
			ef.deleteOnExit();
			ef.createNewFile();
			addFile(endFile,filePath,conf);
		}
	}
	
	public static void listFiles() throws Exception {
		Configuration conf = setConf();
		FileSystem fileSystem = FileSystem.newInstance(conf);
		FileSystem.printStatistics();
		System.out.println(fileSystem.getHomeDirectory());
		System.out.println(fileSystem.getScheme());
		System.out.println(fileSystem.getUri());
		int sum = 0;
		List<String> filest =  getPath();
		for(String file : filest){
//		for(String file : filePahts.split(",")){
			Path path = new Path(file);
			if(fileSystem.exists(path)){
				int i = 0; 
				RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(path, true);
				while(files.hasNext()){
					LocatedFileStatus fileT = files.next();
					String log = (++sum)+"\t"+(++i)+"\t"+fileT.getPath().toString()+"\t"+fileT.getLen()+"\t"+fileT.getModificationTime();
//					writeLog(log);
					System.out.println(log);
				}
//				writeLog(file+"\t"+i+"\t"+size);
			}
		}
//		}
		fileSystem.close();
//		closeWrite();
	}

	public static Configuration setConf() throws IOException {
		System.setProperty("java.security.krb5.conf", "C:/out/parquet/hdfsConfig/krb5.conf");

		Configuration conf = new Configuration();
		conf.set("hadoop.security.authentication", "Kerberos");
		conf.set("hdfs.kerberos.principal", kPrincipal);
		addConf(conf, "C:/env");

		UserGroupInformation.setConfiguration(conf);
		UserGroupInformation.loginUserFromSubject(null);
		UserGroupInformation.loginUserFromKeytab(kPrincipal, keyPath);
		return conf;
	}

	public void testInsert() {
//		String[] files = new String[] { "C:/out/parquet/p01_marc_1_2.parquet", "C:/out/parquet/p01_marc_1.parquet",
//				"C:/out/parquet/p01_marc_1_3.parquet", "C:/out/parquet/p01_marc_1_4.parquet",
//				"C:/out/parquet/p01_marc_1_5.parquet", "C:/out/parquet/p01_marc_1_6.parquet" };

		ExecutorService esr = Executors.newFixedThreadPool(2);
		for (int i = 1; i < 6; i++) {
//			final int s = i;
			esr.execute(new Runnable() {
				@Override
				public void run() {
					try {
//						HdfsOperation client = new HdfsOperation();
//						Configuration conf = KerberosLogin(defaultFS, kPrincipal,
//								"C:/out/parquet/hdfsConfig/sasce2ed.keytab");
//						System.out.println(conf);
//						client.addFile(files[s], "/dev/edl/sc/sce2e/str/sce2e_stg/t3/test", conf);
					} catch (Exception e) {
					}
				}
			});
		}
		//
	}

	/**
	 * read a file from hdfs
	 * 
	 */
	public static void readFile(String file, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);
		Path path = new Path(file);
		if (!fileSystem.exists(path)) {
			System.out.println("File " + file + " does not exists");
			return;
		}

		FSDataInputStream in = fileSystem.open(path);

		String filename = file.substring(file.lastIndexOf('/') + 1, file.length());

		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filename)));

		byte[] b = new byte[1024];
		int numBytes = 0;
		while ((numBytes = in.read(b)) > 0) {
			out.write(b, 0, numBytes);
		}

		System.out.printf("Output file %s is created in your local drive.\n", filename);
		in.close();
		out.close();

		fileSystem.close();
	}

	/**
	 * delete a directory in hdfs // need testing
	 * 
	 */
	public static void deleteFile(Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);
		List<String> files =  getPath();
		for(String file : files){
			Path path = new Path(file);
			if (!fileSystem.exists(path)) {
				System.out.println("File " + file + " does not exists");
				return;
			}
			fileSystem.delete(new Path(file), true);
			System.out.println("File " + file + " delete");
		}
		fileSystem.close();
	}

	/**
	 * create directory in hdfs // need testing
	 * 
	 */
	public static void mkdir(String dir, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);

		Path path = new Path(dir);
		if (fileSystem.exists(path)) {
			System.out.println("Dir " + dir + " already exists");
			return;
		}

		fileSystem.mkdirs(path);

		fileSystem.close();
	}

	/**
	 * copy a file from local filesystem to hdfs // need testing
	 * 
	 */
	public static void addFile(String source, String dest, Configuration conf) throws IOException {

		FileSystem fileSystem = FileSystem.newInstance(conf);

		// Get the filename out of the file path
		String filename = source.substring(source.lastIndexOf('/') + 1, source.length());

		// Create the destination path including the filename.
		if (dest.charAt(dest.length() - 1) != '/') {
			dest = dest + "/" + filename;
		} else {
			dest = dest + filename;
		}

		// Check if the file already exists
		Path path = new Path(dest);
		if (fileSystem.exists(path)) {
			System.out.println("File " + dest + " already exists");
			return;
		}
		System.out.println("path :"+path);
		// Create a new file and write data to it.
		FSDataOutputStream out = fileSystem.create(path);
		InputStream in = new BufferedInputStream(new FileInputStream(new File(source)));

		byte[] b = new byte[1024];
		int numBytes = 0;
		while ((numBytes = in.read(b)) > 0) {
			out.write(b, 0, numBytes);
		}

		// Close all the file descriptors
		in.close();
		out.close();
		fileSystem.close();
		System.out.printf("File %s added to HDFS: %s.\n ", filename, dest);
	}

	public static Configuration login() throws IOException {
		Configuration conf = new Configuration();
		// if (UserGroupInformation.isSecurityEnabled()) {
		conf.set(KEYTAB_FILE_KEY, keyPath);
		conf.set(USER_NAME_KEY, kPrincipal);
		addConf(conf, "C:/env");
//		conf.set("fs.defaultFS", defaultFS);
		SecurityUtil.login(conf, KEYTAB_FILE_KEY, USER_NAME_KEY);
		// }
		return conf;
	}

	public static Configuration KerberosLogin(String defaultFS, String kPrincipal, String keyPath) throws Exception {
		Configuration conf = new Configuration();
		// Configuration.addDefaultResource("core-add.xml");
		// Configuration.addDefaultResource("hdfs-add.xml");
		addConf(conf, "C:/env");
		conf.set("fs.defaultFS", defaultFS);
		conf.set("hadoop.security.authentication", "Kerberos");

		UserGroupInformation.setConfiguration(conf);
		UserGroupInformation.loginUserFromKeytab(kPrincipal, keyPath);
		return conf;
	}
	
	public static Configuration KerberosLogin(String defaultFS, String kPrincipal, String keyPath, String hdfsConfigPath) throws Exception {
		String krbPath = getKrbPath(hdfsConfigPath);
		System.setProperty("java.security.krb5.conf", krbPath);
		
		Configuration conf = new Configuration();
		addConf(conf,hdfsConfigPath);
		conf.set("fs.defaultFS", defaultFS);	
		conf.set("hadoop.security.authentication", "Kerberos");
		
		UserGroupInformation.setConfiguration(conf);
		UserGroupInformation.loginUserFromKeytab(kPrincipal, keyPath);
		return conf;
	}
	
	private static String getKrbPath(String hdfsConfigPath) {
		File configDir = new File(hdfsConfigPath);
		if(configDir.isDirectory()){
			File[] files = configDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					return file.getName().toLowerCase().endsWith(".conf");
				}
			});
			if(files.length > 0 ){
				for(File file : files){
					if(file.getName().equalsIgnoreCase("krb5.conf")){
						return file.getAbsolutePath();
					}
				}
				return files[0].getAbsolutePath();
			}
		}
		return null;
	}

	private static void addConf(Configuration conf, String path) throws FileNotFoundException {
		File file = new File(path);
		if (file.isDirectory()) {
			for (File tmp : file.listFiles()) {
				if (tmp.getName().toLowerCase().endsWith(".xml")) {
					conf.addResource(new FileInputStream(tmp));
				}
			}
		} else {
			conf.addResource(new FileInputStream(file));
		}
	}
	
	private static List<String> getPath() {
		List<String> paths = null;
		try {
			paths = FileUtils.readLines(new File(pathFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paths;
	}

}

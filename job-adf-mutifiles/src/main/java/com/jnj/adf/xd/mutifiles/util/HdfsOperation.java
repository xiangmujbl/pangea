package com.jnj.adf.xd.mutifiles.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * Simple Driver to read/write to hdfs.  		// It uses Kerberos, no SSL.		Fully tested.
 * 
 * Notes:  Remember to set VM argument:  -Djava.security.krb5.conf=krb5.conf
 * 		   To debug:  -Dsun.security.krb5.debug=true
 * 
 * https://gist.github.com/ashrithr/f7899fdfd36ee800f151
 *
 * Tested:
 * 		add  test.csv hdfs:/dev/edl/sc/sce2e/str/sce2e_stg/t3
 * 		read /dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv								This line is the same as the next 2 lines!!!
 * 		read hdfs:/dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv							
 *		read hdfs://itsusraedld01.jnj.com/dev/edl/sc/sce2e/str/sce2e_stg/t3/test.csv
 */
public class HdfsOperation {
	private static final Logger logger = LoggerFactory.getLogger(HdfsOperation.class);
	
	/**
	 * read a file from hdfs
	 * @throws Exception 
	 * 
	 */
	public static void readFile(String file, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);

		Path path = new Path(file);
		if (!fileSystem.exists(path)) {
			logger.error("File " + file + " does not exists");
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

		logger.info("Output file %s is created in your local drive.\n", filename);
		in.close();
		out.close();
		
		fileSystem.close();
	}

	/**
	 * delete a directory in hdfs		// need testing
	 * 
	 */
	public static void deleteFile(String file, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);

		Path path = new Path(file);
		if (!fileSystem.exists(path)) {
			logger.error("File " + file + " does not exists");
			return;
		}

		fileSystem.delete(new Path(file), true);

		fileSystem.close();
	}

	/**
	 * create directory in hdfs			// need testing
	 * 
	 */
	public static void mkdir(String dir, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.newInstance(conf);

		Path path = new Path(dir);
		if (fileSystem.exists(path)) {
			logger.error("Dir " + dir + " already exists");
			return;
		}

		fileSystem.mkdirs(path);

		fileSystem.close();
	}
	
	/**
	 * copy a file from local filesystem to hdfs	// need testing
	 * @param ru 
	 * 
	 */
	public static void addFile(String source, String dest, Configuration conf) throws IOException {

		source = source.replaceAll("\\\\","/");
		dest = dest.replaceAll("\\\\","/");
		
		FileSystem fileSystem = FileSystem.newInstance(conf);

		// Get the filename out of the file path
		String filename = source.substring(source.lastIndexOf('/') + 1, source.length());

		// Create the destination path including the filename.
		if (dest.charAt(dest.length() - 1) != '/') {
			dest = dest + '/' + filename;
		} else {
			dest = dest + filename;
		}

		// Check if the file already exists
		Path path = new Path(dest);
		if (fileSystem.exists(path)) {
			fileSystem.delete(path, true);
			System.out.println("File " + dest + " already exists and deleted");
		}

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
		
		logger.info("File "+filename+" added to HDFS:"+dest+".");
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
		if(file.isDirectory()){
			for(File tmp : file.listFiles()){
				if(tmp.getName().toLowerCase().endsWith(".xml")){
					conf.addResource(new FileInputStream(tmp));
				}
			}
		}else{
			conf.addResource(new FileInputStream(file));
		}
	}
}

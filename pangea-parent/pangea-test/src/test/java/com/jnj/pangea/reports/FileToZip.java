package com.jnj.pangea.reports;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileToZip {
    public static void zipDirectory(String path)  {
        File file = new File(path);
        String parent = file.getParent();
        File zipFile = new File(parent, file.getName() + ".zip");
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            zip(zos, file, file.getName());
            zos.flush();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void zip(ZipOutputStream zos, File file, String path) throws IOException {

        if (file.isDirectory()) {
            ZipEntry entry = new ZipEntry(path + File.separator);
            zos.putNextEntry(entry);
            File[] files = file.listFiles();
            for (File x : files) {
                zip(zos, x, path + File.separator + x.getName());
            }
        } else {
            FileInputStream fis = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(path);
            zos.putNextEntry(entry);

            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.flush();
            fis.close();
            zos.closeEntry();
        }
    }


}

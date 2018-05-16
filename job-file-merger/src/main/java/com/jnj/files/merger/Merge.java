package com.jnj.files.merger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;


public class Merge implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(Merge.class);

    private String mLocation;
    private String outputFileName;
    private boolean toCompress;
    private String zipFileName;
    private String outputDirectory;
    private String keyString;
    private boolean duplicationFlag = false;
    private Resource[] sPattern = new Resource[0];

    private final static String newline = System.getProperty("line.separator", "\n");

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("Start merging the files..");
        StepContext stepContext = chunkContext.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();

        long startTime = System.currentTimeMillis();
        Path outFile = Paths.get(this.getOutputDirectory() + this.getOutputFileName());

        List<Object> fileList = new ArrayList<>();
        for (Resource resource : sPattern) {
            fileList.add(resource.getFilename());
        }

        System.out.println("TO " + outFile);
        System.out.println("fileList:" + fileList);

        if (fileList.isEmpty()) {
            return RepeatStatus.FINISHED;
        }

        String[] keyStringArray = keyString.split(",");

        Set<String> mergeKey = new HashSet<String>();
        try (FileChannel out = FileChannel.open(outFile, CREATE, WRITE)) {
            for (int ix = 0, n = fileList.size(); ix < n; ix++) {
                Path inFile = Paths.get(this.getmLocation() + fileList.get(ix).toString());
                BufferedReader br = new BufferedReader(new FileReader(inFile.toString()));
                Integer numFlag = 0;
                String strLine;
                List<Integer> keyNum = new ArrayList<Integer>();
                while ((strLine = br.readLine()) != null) {
                    strLine = strLine + newline;
                    numFlag++;
                    if (numFlag == 1) {
                        String origHeaderRow = strLine;
                        // List<Integer> keyNum = new ArrayList<Integer>();
                        if (duplicationFlag) {
                            if (StringUtils.isNotBlank(origHeaderRow)) {
                                String[] origColumns = origHeaderRow.split("\t");
                                for (String keyString : keyStringArray) {
                                    for (int i = 0; i < origColumns.length; i++) {
                                        if (origColumns[i].trim().equals(keyString.trim())) {
                                            keyNum.add(i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (ix == 0) {
                            out.write(ByteBuffer.wrap(strLine.getBytes()));
                        }
                    } else {
                        if (duplicationFlag) {
                            StringBuffer keystr = new StringBuffer();
                            String[] values = strLine.split("\t");
                            for (Integer num : keyNum) {
                                keystr.append(values[num].trim());
                            }
                            if (mergeKey.contains(keystr.toString()))
                                continue;
                            mergeKey.add(keystr.toString());
                        }
                        out.write(ByteBuffer.wrap(strLine.getBytes()));
                    }
                }
                br.close();
            }
        }

        if (this.toCompress == true) {
            zipper(this.getOutputDirectory() + this.getOutputFileName(),
                    this.getOutputDirectory() + this.getZipFileName());
        }
        System.out.println(mergeKey);
        System.out.println("Elapsed time since start: " + (System.currentTimeMillis() - startTime) + "milliseconds");
        System.out.println("DONE.");
        return RepeatStatus.FINISHED;
    }

    public void zipper(String source, String zipFileName) {

        try {
            FileInputStream in = new FileInputStream(source);
            OutputStream out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
            byte[] bytes = new byte[2 * 1024];
            int len;
            while ((len = in.read(bytes)) > 0)
                out.write(bytes, 0, len);
            in.close();
            out.close();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public boolean isToCompress() {
        return toCompress;
    }

    public void setToCompress(boolean toCompress) {
        this.toCompress = toCompress;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public boolean isDuplicationFlag() {
        return duplicationFlag;
    }

    public void setDuplicationFlag(boolean duplicationFlag) {
        this.duplicationFlag = duplicationFlag;
    }

    public Resource[] getsPattern() {
        return sPattern;
    }

    public void setsPattern(Resource[] sPattern) {
        this.sPattern = sPattern;
    }

    public static String getNewline() {
        return newline;
    }
}
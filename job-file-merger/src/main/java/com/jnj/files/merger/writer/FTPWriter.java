package com.jnj.files.merger.writer;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import nyla.solutions.global.util.Cryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Read XML file one by one
 *
 * @author lsun51
 */
public class FTPWriter implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(FTPWriter.class);

    private String tSourceLocation = null;
    private String tLocation = null;
    private String tHost;
    private String tUsername;
    private String tPassword;
    private String tFileName;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("Start sending the files..");

        Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
        JSch jsch = new JSch();

        try {
            session = jsch.getSession(this.gettUsername(), this.gettHost(), 22);

            if (session == null) {
                throw new Exception("session is null");
            }

            session.setPassword(Cryption.interpret(this.gettPassword()));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("PreferredAuthentications", "password");

            session.connect();

            channel = (Channel) session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;

            sftp.cd(this.gettLocation());

            InputStream instream = new FileInputStream(this.gettSourceLocation() + this.gettFileName());
            sftp.put(instream, this.gettFileName());
            instream.close();
//				archiveFile(file);
            System.out.println("File sent to: " + this.gettLocation() + this.gettFileName());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                    sftp = null;
                    logger.info("sftp is cosed already");
                }
            }
            if (channel != null) {
                channel.disconnect();
                channel = null;
            }
            if (session != null) {
                session.disconnect();
                session = null;
            }
        }

        return RepeatStatus.FINISHED;
    }

    public String gettSourceLocation() {
        return tSourceLocation;
    }

    public void settSourceLocation(String tSourceLocation) {
        this.tSourceLocation = tSourceLocation;
    }

    public String gettLocation() {
        return tLocation;
    }

    public void settLocation(String tLocation) {
        this.tLocation = tLocation;
    }

    public String gettHost() {
        return tHost;
    }

    public void settHost(String tHost) {
        this.tHost = tHost;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String gettFileName() {
        return tFileName;
    }

    public void settFileName(String tFileName) {
        this.tFileName = tFileName;
    }

}

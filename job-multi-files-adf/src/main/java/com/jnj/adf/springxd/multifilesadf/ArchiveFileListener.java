package com.jnj.adf.springxd.multifilesadf;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.net.URI;

public class ArchiveFileListener implements StepExecutionListener {
	private static final Logger logger = LoggerFactory.getLogger(ArchiveFileListener.class);
	private String archiveFolder;

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (ExitStatus.COMPLETED.equals(stepExecution.getExitStatus()) && StringUtils.isNotBlank(archiveFolder)) {
			ExecutionContext executionContext = stepExecution.getExecutionContext();
			String fileName = executionContext.getString("fileName");
			File src = null;
			File dest = null;
			try {
				src = new File(new URI(fileName));
				dest = new File(new URI(archiveFolder));
				// if the dest folder doesn't exist, create it
				if (!dest.exists()) {
					dest.mkdirs();
				}
				FileSystemUtils.copyRecursively(src, new File(dest, src.getName()));
			} catch (Exception e) {
				logger.error("Copy file error, src is '{}', dest is '{}'", src, dest);
				logger.error("Exception is '{}'", e);
				return ExitStatus.FAILED;
			}
			FileSystemUtils.deleteRecursively(src);
		}
		return ExitStatus.COMPLETED;
	}

	/**
	 * @return the archiveFolder
	 */
	public String getArchiveFolder() {
		return archiveFolder;
	}

	/**
	 * @param archiveFolder
	 *            the archiveFolder to set
	 */
	public void setArchiveFolder(String archiveFolder) {
		this.archiveFolder = archiveFolder;
	}

}

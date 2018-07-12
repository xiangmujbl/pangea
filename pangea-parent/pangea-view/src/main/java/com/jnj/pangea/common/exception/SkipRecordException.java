package com.jnj.pangea.common.exception;

/**
 * This is a marker exception - to be used to handle records that should be skipped from processing
 */
public class SkipRecordException extends Exception {

    public SkipRecordException() {
        super();
    }

    public SkipRecordException(String message) {
        super(message);
    }
}

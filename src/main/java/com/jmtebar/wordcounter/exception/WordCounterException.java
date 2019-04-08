package com.jmtebar.wordcounter.exception;

/**
 * Custom general application exception.
 */
public class WordCounterException extends Exception {

    private ErrorCode errorCode;

    public WordCounterException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public WordCounterException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

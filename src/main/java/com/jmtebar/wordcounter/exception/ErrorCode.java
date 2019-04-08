package com.jmtebar.wordcounter.exception;

/**
 * Application Error codes and messages.
 */
public enum ErrorCode {

    UNEXPECTED_ERROR("An unexpected error has occurred"),
    PARAMETER_NUMBER_INCORRECT ("There must be an input parameter"),
    FILE_NOT_EXISTS("The specified file does not exist"),
    FILE_SIZE_ERROR("The file size is very big");

    private String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


}

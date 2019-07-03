package com.jmtebar.wordcounter.validator;

import com.jmtebar.wordcounter.exception.ErrorCode;
import com.jmtebar.wordcounter.exception.WordCounterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Validator class for application.
 */
@Component
public class InputParameterValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputParameterValidator.class);

    @Value("${maximum-filesize}")
    private Long maxFileSize;

    /**
     * Validate input parameters is at least and only one
     *
     * @param args Program input arguments.
     */
    public void validateInputParameters(String[] args) throws WordCounterException {
        if (args.length != 1) {
            LOGGER.error("Incorrect input params. Expected 1. Received {}.", args.length);
            throw new WordCounterException(ErrorCode.PARAMETER_NUMBER_INCORRECT);
        }
    }

    /**
     * Validate input field exits and has configured size.
     *
     * @param pathFile Input file.
     * @throws WordCounterException Possible validation errors.
     */
    public void validateInputFile(Path pathFile) throws WordCounterException {
        try {

            if (Files.notExists(pathFile)) {
                LOGGER.error("File dont exits on path; {}.", pathFile);
                throw new WordCounterException(ErrorCode.FILE_NOT_EXISTS);
            }
            if (FileChannel.open(pathFile).size() > maxFileSize) {
                LOGGER.error("File is bigger than {} bytes", maxFileSize);
                throw new WordCounterException(ErrorCode.FILE_SIZE_ERROR);
            }
        } catch (RuntimeException | IOException e) {
            LOGGER.error("Unexpected error during parameter validation");
            throw new WordCounterException(ErrorCode.UNEXPECTED_ERROR, e.getMessage(), e);
        }
    }
}

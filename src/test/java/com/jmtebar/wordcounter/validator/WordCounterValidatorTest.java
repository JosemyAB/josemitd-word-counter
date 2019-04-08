package com.jmtebar.wordcounter.validator;

import com.jmtebar.wordcounter.exception.ErrorCode;
import com.jmtebar.wordcounter.exception.WordCounterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterValidatorTest {

    @Autowired
    InputParameterValidator inputParameterValidator;

    @Test
    public void contextLoads() {
        assertThat(inputParameterValidator).isNotNull();
    }

    @Test
    public void inputParametersOK() throws IOException {

        String[] args = new String[]{new ClassPathResource("test-files/documentA").getFile().getAbsolutePath()};
        try {
            inputParameterValidator.validateInputParameters(args);
            inputParameterValidator.validateInputFile(Paths.get(args[0]));
        } catch (WordCounterException e) {
            throw new Error("Test fail!", e);
        }
    }

    @Test
    public void twoInputParametersFails() {

        String[] args = new String[]{"Param1", "Param2"};
        try {
            inputParameterValidator.validateInputParameters(args);
        } catch (WordCounterException e) {
            assertThat(e.getErrorCode().equals(ErrorCode.PARAMETER_NUMBER_INCORRECT));
        }
    }

    @Test
    public void fileNotExits() {

        String[] args = new String[]{"/incorrect-path"};
        try {
            inputParameterValidator.validateInputFile(Paths.get(args[0]));
        } catch (WordCounterException e) {
            assertThat(e.getErrorCode().equals(ErrorCode.FILE_NOT_EXISTS));
        }
    }

    @Test
    public void fileSizeIncorrect() throws IOException {

        String[] args = new String[]{new ClassPathResource("test-files/10kbfile").getFile().getAbsolutePath()};
        try {
            inputParameterValidator.validateInputFile(Paths.get(args[0]));
        } catch (WordCounterException e) {
            assertThat(e.getErrorCode().equals(ErrorCode.FILE_SIZE_ERROR));
        }
    }

    @Test(expected = WordCounterException.class)
    public void unexpectedExceptionControlled() throws WordCounterException {
        try {
            inputParameterValidator.validateInputFile(null);
        } catch (WordCounterException e) {
            assertThat(e.getErrorCode().equals(ErrorCode.UNEXPECTED_ERROR));
            throw e;
        }
    }


}

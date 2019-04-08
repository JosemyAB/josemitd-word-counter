package com.jmtebar.wordcounter;

import com.jmtebar.wordcounter.exception.WordCounterException;
import com.jmtebar.wordcounter.service.WordCounterService;
import com.jmtebar.wordcounter.validator.InputParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@SpringBootApplication
public class WordCounterApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterApplication.class);

    @Autowired
    private WordCounterService wordCounterService;

    @Autowired
    private InputParameterValidator inputParameterValidator;

    @Value("${test.enviroment}")
    private Boolean testEnviroment;


    public static void main(final String[] args) {
        SpringApplication app = new SpringApplication(WordCounterApplication.class);
        app.run(args);
    }

    @Override
    public void run(final String... args) {

        if (testEnviroment) {
            LOGGER.info(":::: STARTING WORDS COUNTER APPLICATION FOR TESTING :::: ");
        } else {

            LOGGER.info(":::: STARTING WORDS COUNTER APPLICATION :::: ");

            try {
                inputParameterValidator.validateInputParameters(args);

                Path pathFile = Paths.get(args[0]);
                inputParameterValidator.validateInputFile(pathFile);

                Map<String, Long> wordsCounted = wordCounterService.countWordsFrecuency(pathFile);
                LOGGER.debug("Found {} words", wordsCounted.size());

                Map<String, Long> wordsOrdered = wordCounterService.sortWordsDescending(wordsCounted);

                LOGGER.info(":::: PRINTING WORD COUNTING :::: ");
                wordsOrdered.entrySet().stream().forEach(wordCount -> LOGGER.info("{}: {}", wordCount.getKey(),
                        wordCount.getValue()));

            } catch (WordCounterException wce) {
                LOGGER.error(":::: AN ERROR HAS OCCURRED :::: ");
                LOGGER.error("{} - {}", wce.getErrorCode(), wce.getErrorCode().getErrorMessage());
            } finally {
                LOGGER.info("Closing application.");
                System.exit(0);
            }
        }
    }
}

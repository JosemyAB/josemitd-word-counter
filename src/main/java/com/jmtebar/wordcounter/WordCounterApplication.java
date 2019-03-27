package com.jmtebar.wordcounter;

import com.jmtebar.wordcounter.service.WordCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@SpringBootApplication
public class WordCounterApplication implements CommandLineRunner {

    @Autowired
    private WordCounterService wordCounterService;

    /* Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterApplication.class);

    @Value("${test.enviroment}")
    private Boolean testEnviroment;

    /* Spring boot starting method */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WordCounterApplication.class);
        app.run(args);
    }

    /* Application main method */
    @Override
    public void run(String... args) throws Exception {

        if (testEnviroment) {
            LOGGER.info(":::: STARTING WORDS COUNTER APPLICATION FOR TESTING :::: ");
        } else {
            LOGGER.info(":::: STARTING WORDS COUNTER APPLICATION :::: ");
            Path inputFile = validateInputParameters(args);
            Map<String, Long> result = wordCounterService.countWords(inputFile);

            LOGGER.info(":::: PRINTING WORD COUNTING :::: ");
            result.entrySet().stream().forEach(w -> LOGGER.info("{}: {}", w.getKey(), w.getValue()));

        }
    }

    /**
     * Check if file exits and return it.
     * @param path
     * @return
     */
    private static Path validateFile(String path) {
        LOGGER.debug("Validating if file exits in: {}", path);

        Path pathFile = Paths.get(path);

        if (Files.notExists(pathFile)) {
            LOGGER.error("File dont exits on path; {}.", path);
            exitWithFailure();
        }

        return pathFile;
    }

    /**
     * Validate input parameters
     *
     * @param args
     */
    private static Path validateInputParameters(String[] args) {

        LOGGER.debug("Validating input params");

        // Validating args
        if (args.length != 1) {
            LOGGER.error("Incorrect input params. Expected 1. Received {}.", args.length);
            exitWithFailure();
        }
        return validateFile(args[0]);
    }

    /** Terminate program by errors. */
    private static void exitWithFailure() {
        LOGGER.info("System cant work correctly");
        System.exit(0);
    }

}

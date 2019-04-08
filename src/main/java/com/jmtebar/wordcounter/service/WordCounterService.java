package com.jmtebar.wordcounter.service;

import com.jmtebar.wordcounter.exception.ErrorCode;
import com.jmtebar.wordcounter.exception.WordCounterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service that implements the business logic.
 */
@Service
public class WordCounterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterService.class);

    @Value("${split-regex}")
    private String regexForSplit;

    /**
     * Counts words of the file and returns a map ordered descending.
     *
     * @param file Input file.
     * @return Map with word as a key and number of repetitions as value.
     * @throws IOException Possible read file problems.
     */
    public Map<String, Long> countWordsFrecuency(Path file) throws WordCounterException {

        LOGGER.debug("Counting words in file: {}", file.toString());

        try {
            Map<String, Long> fileWords = Files.lines(file).flatMap(line -> Stream.of(line.split(regexForSplit)))
                    .filter(w -> w.length() > 0)
                    .map(w -> w.toLowerCase())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            return fileWords;

        } catch (Exception e) {
            LOGGER.error("Unexpected error during parameter validation");
            throw new WordCounterException(ErrorCode.UNEXPECTED_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Sort words by repetitions descending.
     * @param fileWords Map with word as a key and number of repetitions as value.
     * @return Map with word as a key and number of repetitions as value ordered by repetitions descending.
     */
    public Map<String, Long> sortWordsDescending(Map<String, Long> fileWords) {

        Map<String, Long> fileWordsOrdered =
                fileWords.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue,
                                (k, v) -> {throw new RuntimeException();},
                                LinkedHashMap::new));

        return fileWordsOrdered;
    }
}

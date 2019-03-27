package com.jmtebar.wordcounter.service;

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
     * @param file
     * @return
     * @throws IOException
     */
    public Map<String, Long> countWords(Path file) throws IOException {

        LOGGER.debug("Counting words in file: {}", file.toString());

        Map<String, Long> fileWords = Files.lines(file).flatMap(line -> Stream.of(line.split(regexForSplit)))
                .filter(w -> w.length() > 0)
                .map(w -> w.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOGGER.debug("Found {} words", fileWords.size());

        Map<String, Long> fileWordsOrdered = fileWords.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> {throw new RuntimeException();},
                        LinkedHashMap::new));

        return fileWordsOrdered;
    }
}

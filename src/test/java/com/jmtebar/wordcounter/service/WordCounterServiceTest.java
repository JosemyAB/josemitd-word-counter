package com.jmtebar.wordcounter.service;

import com.jmtebar.wordcounter.exception.WordCounterException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterServiceTest {

    @Autowired
    private WordCounterService wordCounterService;

    private Path threeSingleWords;
    private Path fiveWordsFile;
    private Path testFileA;
    private Path testFileB;
    private Path invalidFile;


    @Before
    public void setUp() throws IOException {
        threeSingleWords = Paths.get(new ClassPathResource("test-files/three-single-words").getURI());
        assertThat(threeSingleWords).exists();
        fiveWordsFile = Paths.get(new ClassPathResource("test-files/five-words").getURI());
        assertThat(fiveWordsFile).exists();
        testFileA = Paths.get(new ClassPathResource("test-files/documentA").getURI());
        assertThat(testFileA).exists();
        testFileB = Paths.get(new ClassPathResource("test-files/documentB").getURI());
        assertThat(testFileB).exists();
        invalidFile = Paths.get(new ClassPathResource("test-files/adaptavist-icon.png").getURI());
        assertThat(invalidFile).exists();

    }

    @Test
    public void contextLoads() {
        assertThat(wordCounterService).isNotNull();
    }

    @Test
    public void countThreeSinglesWords() {
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("this", 1L);
        expected.put("is", 1L);
        expected.put("test", 1L);

        try {
            Map<String, Long> count = wordCounterService.countWordsFrecuency(threeSingleWords);
            assertThat(expected).isEqualTo(count);

        } catch (WordCounterException e) {
            throw new Error("Test fail!", e);
        }
    }

    @Test
    public void countFiveMultipleWords() {
        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("aaa", 1L);
        expected.put("bbb", 2L);
        expected.put("ccc", 3L);
        expected.put("ddd", 4L);
        expected.put("eee", 5L);

        try {
            Map<String, Long> count = wordCounterService.countWordsFrecuency(fiveWordsFile);
            assertThat(expected).isEqualTo(count);

        } catch (WordCounterException e) {
            throw new Error("Test fail!", e);
        }
    }

    @Test
    public void sortWordsDescendingByFrequency() {

        Map<String, Long> unsortedWordsCount = new LinkedHashMap<>();
        unsortedWordsCount.put("aaa", 1L);
        unsortedWordsCount.put("ddd", 4L);
        unsortedWordsCount.put("ccc", 3L);
        unsortedWordsCount.put("bbb", 2L);
        unsortedWordsCount.put("eee", 5L);

        Map<String, Long> wordsSortered = wordCounterService.sortWordsDescending(unsortedWordsCount);

        long position = 5;
        for (Map.Entry word : wordsSortered.entrySet()) {
            assertThat(word.getValue()).isEqualTo(position);
            position--;
        }

        String firstWord = wordsSortered.entrySet().iterator().next().getKey();
        String lastWord = (String) ((Map.Entry) wordsSortered.entrySet().toArray()[wordsSortered.size() - 1]).getKey();

        assertThat(firstWord).isEqualTo("eee");
        assertThat(lastWord).isEqualTo("aaa");
    }

    @Test
    public void testWordsAreOrdered() {

        try {
            Map<String, Long> testFileAWords = wordCounterService.countWordsFrecuency(testFileA);

            Long firstWordReps = testFileAWords.entrySet().stream().findFirst().get().getValue();
            Long lastWordReps =
                    (Long) ((Map.Entry) testFileAWords.entrySet().toArray()[testFileAWords.size() - 1]).getValue();

            assertThat(firstWordReps).isGreaterThanOrEqualTo(lastWordReps);

        } catch (WordCounterException e) {
            throw new Error("Test fail!", e);
        }
    }

    @Test
    public void differentFilesSameWords() {

        try {

            Map<String, Long> testFileAWords = wordCounterService.countWordsFrecuency(testFileA);
            Map<String, Long> testFileBWords = wordCounterService.countWordsFrecuency(testFileB);

            //Check sizes
            assertThat(testFileAWords.size()).isEqualTo(testFileBWords.size());

            testFileAWords.entrySet().stream().forEach(w -> {
                //Word in A should exits in B list.
                assertThat(testFileBWords.get(w.getKey())).isNotNull();

                //Number of word repetitions has to be the same.
                Long wordBList = testFileBWords.get(w.getKey());
                assertThat(wordBList).isEqualTo(w.getValue());
            });

        } catch (WordCounterException e) {
            throw new Error("Test fail!", e);
        }
    }

    @Test(expected = WordCounterException.class)
    public void invalidFile() throws WordCounterException {
        wordCounterService.countWordsFrecuency(invalidFile);
    }
}

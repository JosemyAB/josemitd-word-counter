package com.jmtebar.wordcounter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterServiceTest {

	@Autowired
	WordCounterService wordCounterService;

	@Test
	public void contextLoads() {
		assertThat(wordCounterService).isNotNull();
	}

	/**
	 * Checks document A and B has the same words and sorted
	 */
	@Test
	public void mainTest() {

		try {
			Path testFileA = Paths.get(new ClassPathResource("documentA").getURI());
			Path testFileB = Paths.get(new ClassPathResource("documentB").getURI());

			assertThat(testFileA).exists();
			assertThat(testFileB).exists();

			Map<String, Long> testFileAWords = wordCounterService.countWords(testFileA);
			Map<String, Long> testFileBWords = wordCounterService.countWords(testFileB);

			//Check sizes
			assertThat(testFileAWords.size()).isEqualTo(testFileBWords.size());

			testFileAWords.entrySet().stream().forEach(w -> {
				//Word in A should exits in B list.
				assertThat(testFileBWords.get(w.getKey())).isNotNull();

				//Number of word repetitions has to be the same.
				Long wordBList = testFileBWords.get(w.getKey());
				assertThat(wordBList).isEqualTo(w.getValue());
			});

			Long firstWordReps = testFileAWords.entrySet().stream().findFirst().get().getValue();
			Long lastWordReps = (Long) ((Map.Entry)testFileBWords.entrySet().toArray()[testFileBWords.size()-1]).getValue();

			assertThat(firstWordReps).isGreaterThanOrEqualTo(lastWordReps);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

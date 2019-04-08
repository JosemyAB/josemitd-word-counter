package com.jmtebar.wordcounter;

import com.jmtebar.wordcounter.service.WordCounterServiceTest;
import com.jmtebar.wordcounter.validator.WordCounterValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WordCounterValidatorTest.class,
        WordCounterServiceTest.class
})
public class WordCounterApplicationTestSuite {
}

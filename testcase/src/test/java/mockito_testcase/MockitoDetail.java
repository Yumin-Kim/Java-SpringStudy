package mockito_testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import spring.StudyService;

import java.time.LocalDate;
import java.util.List;

public class MockitoDetail {

    @Test
    @DisplayName("Mockito")
    void mockito_1() throws Exception{
        //given
        //when

        //then
    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<StudyService> {

        private boolean equals(Object actual, Object expected) {
            return expected.equals(actual);
        }

        @Override
        public boolean matches(StudyService argument) {

            return false;
        }
    }

}

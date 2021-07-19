package testcodelecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionLecture {

    @Test
    void create_test() throws Exception {
        Study study = new Study();
        assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "Study 객체를 인스터스화 시키실 DRAFT로 변경해주어야한다.");
        study.setStudyStatus(StudyStatus.DRAFT);
        assertEquals(StudyStatus.DRAFT, study.getStudyStatus());
    }

    @Test
    void start_1() {
        Study study = new Study();
        assertTrue(study.getStudyStatus() == null, () -> "null인지 확인해주세요");
        study.setStudyStatus(StudyStatus.DOING);
        assertTrue(study.getStudyStatus() == null, () -> "null인지 확인해주세요");
    }


    @Test
    void start_2() throws Exception {
        Study study = new Study();
        assertAll(
                () -> assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "Study 객체를 인스터스화 시키실 DRAFT로 변경해주어야한다."),
                () -> assertNull(study)
                );
    }

    @Test
    void start_3() throws Exception {
        Study study = new Study();
        assertAll(
                () -> assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "Study 객체를 인스터스화 시키실 DRAFT로 변경해주어야한다."),
                () -> assertNull(study)
                );
    }

    @Test
    void start_4(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        String message = exception.getMessage();
        assertEquals(message,"limit수가 음수 입니다");

    }

    @Test
    void start_5(){
        assertTimeout(Duration.ofSeconds(10), ()->new Study(10));
        assertTimeout(Duration.ofMillis(10),
                ()->{
                    new Study(10);
                    Thread.sleep(1000);
                });

        assertTimeoutPreemptively(Duration.ofMillis(10),
                ()->{
                    new Study(10);
                    Thread.sleep(1000);
                });
    }
    @Test
    void start_6(){
        assertTimeoutPreemptively(Duration.ofMillis(10),
                ()->{
                    new Study(10);
                    Thread.sleep(1000);
                });
    }

}

package junit_testcase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class ConditionTestCase {
    @Test
    void start_1() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);
//        assumeTrue("null".equalsIgnoreCase(test_env));
        Study study = new Study();
        assumingThat(study.getStudyStatus() == null,()->{
            System.out.println("Hello");
        });
        assumingThat("null".equalsIgnoreCase(test_env),()->{
            System.out.println("Loacl TEST_ENV");
        });
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void start_2(){
        System.out.println("Hello Windows");
    }
}

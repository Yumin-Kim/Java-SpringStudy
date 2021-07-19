package testcodelecture;


import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {

    @Test
    @DisplayName("테스트 케이스 입니다")
    void startTest(){
        Study study = new Study();
        Assertions.assertNotNull(study);
        System.out.println("테스트 시작");
    }

    @Test
    void start_under_score_case() {
        Study study = new Study();
        Assertions.assertNotNull(study);
        System.out.println("테스트 시작");
    }

    @Test
    @Disabled // 테스트 코드 비활성화
    void startTest1(){
        System.out.println("테스트 한번더 !");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach 애노테이션");
    }
    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll 애노테이션");
    }
    @AfterAll
    static void AfterAll(){
        System.out.println("AfterAll 애노테이션");
    }
    @AfterEach
    void AfterEach(){
        System.out.println("AfterEach 애노테이션");
    }
}

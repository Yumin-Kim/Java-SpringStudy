package junit_testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("매우 중요하며 JPA할때 유용하게 사용될것 같으니 집중해서 보도록하기")
public class RepeatedTestCase {


    @RepeatedTest(value = 10, name = "반복 테스트 진행중 ... ")
    void start_1(RepetitionInfo repetitionInfo) {
        System.out.println("Start repeated TestCase");
        System.out.println("repetitionInfo = " + repetitionInfo);
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("한번 더 진행 중")
    @RepeatedTest(value = 10, name = "displayName = {displayName} , {currentRepetition} , {totalRepetitions}")
    void start_2(RepetitionInfo repetitionInfo) {
        System.out.println("Start repeated TestCase");
        System.out.println("repetitionInfo = " + repetitionInfo);
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("파라미터를 받아 출력!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @ValueSource(strings = {"spring", "spring-boot", "spring-data-jpa"})
    void start_3(String message){
        System.out.println("message = " + message);
    }

    @DisplayName("파라미터를 받아 출력(클래스)!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @ValueSource(classes = {Study.class})
    void start_4(Class message){
        System.out.println("message = " + message);
    }

    @DisplayName("하나의 파라미터를 받아 출력(Convert 과정을 통한 객체 생성)!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @ValueSource(ints = {10,20,30})
    void start_5(@ConvertWith(StudyConverter.class) Study study){
        System.out.println("message = " + study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study.class");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("여러개 파라미터를 받아 출력!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @CsvSource({"10 , spring" , "1000 , 'jpa'" , "123 , test_code"})
    void start_6( int limit , String name){
        Study study = new Study(limit, name);
        System.out.println(study.toString());
    }

    @DisplayName("여러개 파라미터를 타입 정의 하지 않고 받아 출력!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @CsvSource({"10 , spring" , "1000 , 'jpa'" , "123 , test_code"})
    void start_7(ArgumentsAccessor argumentsAccessor){
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study.toString());
    }

    @DisplayName("여러개 파라미터를 타입 정의 하지 않고 편리하게 받아 출력!!")
    @ParameterizedTest(name = "{index} {displayName}")
    @CsvSource({"10 , spring" , "1000 , 'jpa'" , "123 , test_code"})
    void start_8(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println(study.toString());
    }

    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

}

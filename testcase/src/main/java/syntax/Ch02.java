    /**
     * 스트립 API의 특징
     * 스트림 API는 데이터를 추샇화하여 다루므로 다양한 방식으로 저장된 데이터를 읽고 쓰기 위한 동통된 방법을 제공
     * 스트림 API를 이용하면 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방법
     * 1. 스트림은 외부 반복을 통해 작업하는 컬렉션과는 달리 반복을 통해 작업을 수행ㅇ
     * 2. 스트립은 재사용이 가능한 컬렉션관느 달리 단 한번만 사용
     * 3. 스트림은 원본 데이터 변경하지 않는다.
     * 4. 스트림의 연산은 필터 맵 기반의 API를 사용하여 지연 연산을 통해 성능을 최적화한다.
     * 5. 스트립은 parallelStream()메소드를 통한 손쉬운 병렬 처리 지원
     *
     * 스트림API의 동작 흐름
     * 1. 스트림의 생성
     * 2. 스트림의 중개 연산(스트림 변환)
     * 3. 스트림의 최종 연산(스트림의 사용)
     * 데이터 소스 >> 중개 연산[필터] >> 중개 연산[맵] >> 최종 연산
     *
     * 스트림의 생성
     * 스트림API는 다음과 같은 다양한 데이터 소스에서 생성
     * 1. 컬력션
     * 2. 배열
     * 3. 가변 매개변수
     * 4. 지정된 범위의 연속된 정수
     * 5. 특정 타입의 난수들
     * 6. 람다 표현식
     * 7. 파일
     * 8. 빈 스트림림     */
package syntax;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Stream;

    public class Ch02 {

        ArrayList<Integer> list = new ArrayList<Integer>();
        public void AddListFunc(){
            list.add(4);
            list.add(5);

            Stream<Integer> stream = list.stream();
            Stream<Integer> stream1 = stream.map(e -> e);
            System.out.println(stream1);
        }

        public void StreamFitler(){
            System.out.println(")))))))))))))))))))((((((((((");
            List<String> list =
                    Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
            Stream<String> stream1 = list.stream();
            Stream<String> filterd = stream1.filter(s -> s.startsWith("c"));
            filterd.forEach(System.out::println);
        }

    }

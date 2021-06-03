# 스프링 입문 
    * 자바 정리 또는 스피링 친숙해지기
## 기록 - 3일 이나 중요한 내용 배울시 정리
    * 2021.05.16
        * [https://start.spring.io/](스프링부트 개발킷)제공
        * gradle , maven 빌드 또는 라이브러리 땡겨오는 도구
        * 파일 구조 src /main  /test
        * build.gradle 빌드 기록 남기는 용
        * tomcat서버를 다운 받아서 자바코드를 밀어 넣는 형식은 고대 개발자 // 현재는 라이브러리에 tomcat이 있어 라이브러리를 실행과 동시에 서버가 구동된다. 
        * spring 어플리케이션 빌드 하는법 cmd를 통해서 gradlew 통해서 build 한후에 build/libs 안에 jar파일 생성되는데 cmd에 java -jar 빌드 jar파일을 통해 서버를 실행할 수 있다.
            * node에서 프로그램 작성후 npm run build를 통해 만들어진 js 파일 node 파일명.js 와 매우 유사하게 한다.
            * node에서는 npm yarm spring에서는 maven ,gradle과 동일한 빌드 도구 의존성 주입 도구로 활용된다.
## Todo List - 찾아보기
* [스프링 , 스프링 부트 차이](https://sas-study.tistory.com/274)
* [어노 테이션](https://honeyinfo7.tistory.com/56)
* [로깅 , slf4j 관련 포스팅](https://gmlwjd9405.github.io/2019/01/04/logging-with-slf4j.html)
* [Spring AOP 관련 포스팅](https://velog.io/@gwontaeyong/Spring-AOP%EC%97%90%EC%84%9C-Proxy%EB%9E%80)
    * [프록시 패턴 강의](https://limkydev.tistory.com/79)
* [멀티스레드 query요청 처리하는 방법](https://nesoy.github.io/articles/2019-05/Database-Transaction-isolation)
* [자바 스택 프레임과 JVM](https://blog.daum.net/creazier/15309107)
* 필요한 자바 문법 정리하면서 진행하기 람다식(Arrow function이랑 비슷하여 거부감 X 단지 익명 객체선언이 필요한거만 생각하면 될듯)
* 빌드 도구 gradle , Maven
* 아파치 ? 아파치 톰켓 ?차이
    * 아파치 - 아파치 소프트웨어 단체
        * 정적인 데이터를 처리하는 웹서버
    * 아파치 서버 - 정적인 파일 처리해주는 웹 서버 (80 포트)
        * 동적인 데이터를 처리한느 웹 서버
        * WAS(Web Application Server)라고 불리며 웹 컨테이너의 결합으로 다양한 기능을 컨테이너에 구현하여 다양한 역할을 수행
            * WAS의 구성 
                * client <<<>>>> Web Server(요청을 받아 Container로 전송 / 결과값 받아 client로 전송) <<<>>> Web Container(JSP , Servlet 구동 환경 제공 / 동적 데이터 처리)
        * 웹서버 VS WAS
            * 웹서버 : 클라이언트의 요청에 따른 정적인 정보 전달 , 웹서버를 통해서 정적 컨텐츠만 처리하도록 기능을 분배하여 서버의 부담을 줄일수 있다.
            * WAS : 동적인 컨텐츠 제공 (DB조회 다양한 로직처리) 요청에 맞는 데이터를 DB에서 가져와서 비즈니스 로직에 맞게 결과를 만들어서 제공
                * WAS가 웹서버의 기능도 모두 수행하면 되지 않을까
                    * 기능을 분리하여 서버의 부하 방지 
                      * WAS는 DB조회 , 다양한 로직 처리로 인해 바쁘며 단순한 정적 컨텐츠는 웹서버로 전달
                      * 로드 밸런싱을 위해서 웹서버 사용
                      * 여러 웹 어플리케이션 동작 >> 하나의 서버에서 PHP , JSP 어플리케이션 동작하게끔 구성 
                      * Web Server를 WAS 앞에 두고 필요한 WAS들을 Web Server에 플러그인 형태로 설정하면 더욱 효율적인 분산 처리가 가능하다.
    * 톰캣 - DB처리와 같은 동적인 기능들을 가공하여 HTML파일로 만들어 클라이언트에게 제공(8080 포트)
  * Java 자료구조 ArraryList , LinkedList , Vector등등 다시확인하기
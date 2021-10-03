# Filter Interceptor 예외 처리 배움

어떤 내용을 배웠는지 기록을 하기 위해서 작성하게 되었다.

* Filter    
  Filter는 클라이언트에서 WAS로 요청을 보내게 되면 Servlet보다 먼저 동작을 하는것이며 로그 및 로그인 여부 확인을 처리할 수 있다.

  추가적으로 여러 Filter간 chain으로 이루어져 있어 연결해서 부가적인 로직을 처리할 수 있다.

  java.serlet에 존재하는 Filter 인터페이스를 사용하여 Filter를 구현할 수 있고 Filter를 사용함에 있어 등록(빈 생성)을 해주어야 사용이 가능하다. 등록하는 과정은 web.xml 이나
  Filter interface로 구현된 클래스에 애노테이션을 통해서 spring containter에 등록하는 방법도 가능하지만 `@Configuraion @Bean`을 활용한 수동 빈 등록 방법을 지향해야한다.
  애노테이션으로 등록시 순서가 보장되지 않기 떄문이다.

  등록하는 방법은 코드를 참고하면 될것이다.

* Interceptor    
  Interceptor는 Filter를 지나 Dispatch Servlet을 지나 사용이 가능하며, Sprint Context에서만 존재하는것으로 Filter와 유사한 기능으로 클라이언트에서 요청을 보내실
  Fiter를 거쳐 Interceptor를 사용할 수 있게 된다.

  수동 빈등록시 WebMvcConfigurer 상속받아 addInterceptor를 오버라이드 하여 등록해주어야한다.

    하지만 Filter는 Interface를 통해 사용하지 않을 메소드도 오버라이드를 해야 하지만 Interceptor는 java.springframwor에 존재하는 HandlerInterceptor 인터페이스를 통해 구현하며 인터페이스의 메소드가 default로 선언되어 있어 사용하지 않을시 오버라이드를 하지 않아도 되며 Filter보다 부가적인 기능이 있어 Filter보다 Interceptor를 지향해야한다.    

    HandlerIntercepter의 메소드는 preHandle , postHadle , afterCompletion이 있다. 기능은 인자를 통해 유추가 가능하며 간략하게는 preHandle은 spring Controller가 실행되기 전 postHandle는 Controller 메소드가 실행 직후 view 페이지를 렌더링 전 afterCompletion view 페이지가 렌더링 되고 난후에 실행된다.(추가적으로 controller에서 발생한 Exception 확인 가능)

* 예외 처리    
  ExceptionResolver 인터페이스를 통해 예외 처리를 할 수 있게 도와주며 인터페이스 사용한 클래스는 빈등록을 해주어야하며 extendsHandlerExceptionResolvers 메소드를 오버라이드
  하여 등록해주어야한다.

  예외 발생후 페이지 나 API로 처리하는 방식이 존재하는데 페이지 처리 방식은 `WebSeverFactoryCustomizer<ConfigurableWebServerFactory>`를 상속 받아 customize
  메소드를 통해 에러 페이지로 요청을 한번더 보내 처리하는 방법이 있다.

  하지만 두번째 방법은 예외를 확인하고 다시 한번 더 요청을 보내 Filter나 Interceptor가 다시 동작하게 된다.(추가적인 설정을 통해 실행이 안되게 할 수 있다 - 또한 예외 처리를 위한 Filter와
  Interceptor가 요청을 구분이 가능하다.DispatcherType을 통해 구분할 수 있다. ) 두번 요청 보내는 방법은 개발자 원하는 방식이 아니기 때문에 다른 방법을 사용하는 것이 좋다.

  하지만 spring 자체적으로 예외 처리를 통해 페이지를 기본으로 제공해주는 Controller가 있기 때문에 위와 같은 수고를 덜 수 있다.`BasicErrorController`클래스로 인해 페이지나 API가
  구현되어 있기 때문에 큰 신경을 안쓰고 개발 했던것 같다.

  또한 API위주의 예외 처리를 살펴 보게 되면 HandelrExceptionResolver를 상속받은 클래스가 처리하게 되며 ModelView를 return하는 메소드를 사용하여 처리하는 하여 에러를 발생하지
  않는 처리가 가능하지만 내부적으로 구현해야할 것들이 많다.



  이런 문제를 해결하기 위해 ExceptionHadler 애노테이션을 제공해주면 HandlerExceptionResolver를 상속받은 여러 클래스들이 동작하여 ExceptionHandler애노테이션을 통해 API
  예외 처리가 가능한 하다.

  이 ExceptionHandler를 하나의 controller에 모으기 위해 controllerAdvice를 사용하여 처리가 가능하다.

Filter , Interceptor , 예외 처리를 알아 보았는데 부가적으로 많은 기능을 제공하며 Filter와 Interceptor를 통해 로깅 , 로그인처리(security)등을 할 수 있다는 것을 알게 되었고
예외처리에 대한 고민이 많았지만 올바르게 하는 중이지만 Exception을 상속받아 Controller나 Domain 클래스 명을 덧붙여 명시적인 Exception을 구현하여 진행하는 방법이 올바른지는 아직
모르겠다.

하지만 이번 강의 통해 Security를 바라보는 관점이 달라졌다.



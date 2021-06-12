package startspring.hello_spring.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //어디서 사용될것인지 스코프 지정해주는 정도하고 생각
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}

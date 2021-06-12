package startspring.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
        basePackages = {"startspring.hello_spring.member","startspring.hello_spring.discount","startspring.hello_spring.order"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}

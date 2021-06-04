package startspring.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import startspring.hello_spring.aop.TimeTraceAop;
import startspring.hello_spring.repository.JpaMemberRepository;
import startspring.hello_spring.repository.MemberRepository;
import startspring.hello_spring.service.UserService;

@Configuration
public class ConfigSpring {

//    private DataSource dataSource;
//
//
//    @Autowired
//    public ConfigSpring(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
/*
기존 JPA 정식 스펙에서는 아래와 같이 PersistenceContext 어노테이션이 필요!
하지만 Spring 자체적으로 DI하기 때문에 굳이 해줄 필요 없음!
    @PersistenceContext
    private EntityManager em;*/

//    private EntityManager em;
//
//    @Autowired
//    public ConfigSpring(EntityManager entityManager){
//        this.em = entityManager;
//    }

    private final MemberRepository memberRepository;

    public ConfigSpring(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public UserService userService(){
        return new UserService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memmoryMemberRepository(){
//        //return new MemmoryMemberRepository();
//       // return new JdbcMemberReopsitory(dataSource);
////        return new JdbcMemberReopsitory(dataSource);
//        return new JpaMemberRepository(em);
//    }
}

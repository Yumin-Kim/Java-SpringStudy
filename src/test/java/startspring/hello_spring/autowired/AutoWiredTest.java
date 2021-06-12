package startspring.hello_spring.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import startspring.hello_spring.member.Member;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutiWiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean2(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setBean1(@Nullable Member noBean1) {
            System.out.println("noBean2 = " + noBean1);
        }

        @Autowired
        public void setBean3(Optional<Member>  noBean1) {
            if(noBean1.equals(Optional.empty()) ){
                System.out.println("noBean3 = " + noBean1);
            }
        }




    }

}

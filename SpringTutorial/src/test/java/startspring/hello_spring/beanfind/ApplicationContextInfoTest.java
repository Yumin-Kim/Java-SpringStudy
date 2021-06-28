package startspring.hello_spring.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.AppConfig;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든빈 출력하기")
    void beanInfosFind(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = Object >>" + bean +"bean=Name"+beanDefinitionName);
        }
    }

    @Test
    @DisplayName("사용되고 있는 빈 출력하기")
    void applicationBeanInfosFind(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){

                //Rolo_APPLICATION = 직접사용중 등록된 BEAN만 볼 수 있다.
                //Rolo_INFRASTURCTURE = 스프링 내부에 사용되는 bean만 볼 수 있다.

                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = Object >>" + bean +"/////////Name ==>  "+beanDefinitionName);
            }
        }
    }


}


package jpacore.jpashop.javatest;

import jpacore.jpashop.test.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootTest
public class JavaTestIml {

    //실제 Proxy 패턴을 구현하여 진행하는 방식
    BookService bookService = new BookServiceProxy(new DefaultBookService());
    //내부적으로 구현 되어 있는 Proxy 인터페이스를 활용하여 진행하는 방식
    // 자바가 제공하는 다이나믹 프록시
    // 제약사항 interface에서 제공하는 모든 메소드에 프록시에 적용한 부수적인 것들이 모두 적용되게 되며
    //interface가 없이는 다이나믹 프록시를 구현하지 못한다.
    BookService bookServiceImpl= (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {
        BookService bookService = new DefaultBookService();
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy interface Method Start Point");
            Object invoke = method.invoke(bookService, args);
            System.out.println("Proxy interface Method End Point ");
            return invoke;
        }
    });

    @Test
    @DisplayName("Predicate")
    public void testJava01() throws Exception {
        //given
        bookServiceImpl.rent();
        bookServiceImpl.rentToUser();
    }


    @Test
    @DisplayName("클래스의 프록시가 필요하다면")
    public void proxy_classInfo() throws Exception{
        //given

        MethodInterceptor handler = new MethodInterceptor() {
            BookServiceNotInter bookServiceNotInter = new BookServiceNotInter();
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("TestCase BookServiceMotInter startPoint Implements");
                Object invoke = method.invoke(bookServiceNotInter, args);
                System.out.println("TestCase BookServiceMotInter endPoint Implements");
                return invoke;
            }
        };
        BookServiceNotInter bookServiceImple = (BookServiceNotInter) Enhancer.create(BookServiceNotInter.class, handler);
        //when
        bookServiceImple.rent();
        bookServiceImple.rentToUser();
        //then
    }
}

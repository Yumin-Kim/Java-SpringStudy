package com.algo;


import com.algo.data.*;
import com.algo.di.BookService;
import com.algo.di.ContainerService;
import com.algo.java8sytax.FunctioInInterface;
import com.algo.syntax.*;
import com.algo.whiteship.Book;
import com.algo.whiteship.MyBook;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
System.out.println("==================리플랙션을 활용한 DI 개발하보기 ==================");
        try {
            System.out.println("DI Start point");
            BookService bookService = ContainerService.getObject(BookService.class);
            bookService.login();
            System.out.println("DI End point");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("================== 프록시패턴 ==================");

        System.out.println("==================제네릭========================");

        System.out.println("==================클래스와 인터페이스 상속========================");
//        System.out.println("간단한 상속 관계");
//        yu yu = new yu();
//        yu.helloPerson();

    }

    static class Person {
        private String name;

        public Person(String name) {
            System.out.println("Perent constructor");
            this.name = name;
        }

        public void helloPerson() {
            System.out.println("person methhod ");
        }

    }

    static class yu extends Person {
        public yu() {
            super("Test");
            System.out.println("Child constructor");

        }

        @Override
        public void helloPerson() {
            super.helloPerson();
            System.out.println("child method");
        }
    }

}

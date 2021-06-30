package com.algo.di.refiection;

import com.algo.whiteship.MyBook;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FindClassInfo {

    public void findClasInfo() throws Exception {

        /**
         * reflection
         * 객체를 통해 클래스의 정보를 분석해 내는 프로그램기법
         * 투영 , 반사 라는 사전적인 의미 지니고 있음
         * 리플랙션이 존재로 인한 이점
         * 객체의 메모리만을 알고 있고 그릐고 객체의 형에 애해서는 모른다고 가정했을때
         * 리풀랙션으로 형은 알고 있지만 형변황을 할 수 없는 상태에서 메서드들 호출할 수 있다.
         * 리플랙션은 자바의 특징이며 실행중인 자바 프로그램 내부를 검사혹 내부의 속성을 수정할 수 있도록 한다.
         */
        System.out.println("==================reflection========================");
        Class<MyBook> bookClass = MyBook.class;
        System.out.println("bookClass = " + bookClass);
        MyBook myBook = bookClass.getConstructor().newInstance(null);
        myBook.playClass();
        //public static 접근 가능
        Constructor<MyBook> constructor = bookClass.getConstructor();
//        MyBook myBook = constructor.newInstance();
        Method b = bookClass.getDeclaredMethod("b", String.class);
        b.invoke(b, "getDeclaredMethod == > public static");
        //private 접근 가능
        MyBook newMyBookPrivateMethod = MyBook.class.newInstance();
        Method e = newMyBookPrivateMethod.getClass().getDeclaredMethod("e");
        e.setAccessible(true);
        e.invoke(newMyBookPrivateMethod);
    }

}

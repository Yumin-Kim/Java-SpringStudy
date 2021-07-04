package com.algo.whiteship;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InnerClassFactory {
    public void create() throws NoSuchMethodException {
        List<Method> printHelloList = new ArrayList<>();
        Other other = new Other();
        Other.Another another2 = new Other.Another();

        Other.AnotherTo anotherTo1 = new Other().new AnotherTo();
        Other.AnotherTo anotherTo2 = new Other().new AnotherTo();
        System.out.println(false);

    }
}

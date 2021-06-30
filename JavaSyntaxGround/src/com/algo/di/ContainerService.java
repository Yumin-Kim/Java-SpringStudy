package com.algo.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
    public static<T> T getObject(Class<T> typeclass) throws Exception {
        T instance = createInstance(typeclass);
        Arrays.stream(typeclass.getDeclaredFields()).forEach((f)->{
            if (f.getAnnotation(Inject.class) != null) {
                try {
                    Object getTypeInstance = createInstance(f.getType());
                    f.setAccessible(true);
                    f.set(instance,getTypeInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return instance;
    }

    private static <T> T createInstance(Class<T> typeClass) throws Exception {
        return typeClass.getConstructor().newInstance( null);
    }
}

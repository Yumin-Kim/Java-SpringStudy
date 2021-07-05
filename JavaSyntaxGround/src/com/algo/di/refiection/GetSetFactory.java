package com.algo.di.refiection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class GetSetFactory {
    public void createGetSetFatory(){
        DtoClass dtoClass = new DtoClass();
        MemberEntitiy memberEntitiy = new MemberEntitiy();
        dtoClass.setName("hello");
        dtoClass.setCity("hello123123");
        Arrays.stream(dtoClass.getClass().getDeclaredMethods())
                .forEach(declare->{
                    if(declare.getName().contains("get")){
                        String getMethodName = declare.getName();
                        try {
                            if(dtoClass.getClass().getDeclaredMethod(getMethodName).invoke(dtoClass) != null){
                                String[] gets = getMethodName.split("get");
                                Object data = dtoClass.getClass().getDeclaredMethod(getMethodName).invoke(dtoClass);
                                System.out.println("find getter = " + data);
                                System.out.println("getMethodName = " + getMethodName);
                                Method[] declaredMethods = memberEntitiy.getClass().getDeclaredMethods();
                                Arrays.stream(declaredMethods)
                                        .forEach(d->{
                                            if(d.getName().contains(gets[1]) && d.getName().contains("update") ){
                                                try {
                                                    System.out.println(d.getName());
                                                    System.out.println("data = " + data);
                                                    memberEntitiy.getClass().getDeclaredMethod(d.getName(), String.class).invoke(memberEntitiy, data);
                                                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });

                            }
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
        System.out.println(memberEntitiy.getName());
        System.out.println(memberEntitiy.getCity());
    }
}

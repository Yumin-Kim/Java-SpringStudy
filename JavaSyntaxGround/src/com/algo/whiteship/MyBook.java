package com.algo.whiteship;

public class MyBook implements Book {

    @Override
    public void playClass() {
        System.out.println("playClass ");
    }

    public static void b(String printName){
        System.out.println(printName);
    }

    private static void d(){
        System.out.println("private static void book method");
    }

    private void e(){
        System.out.println("private void book method");
    }

    public void c(){
        System.out.println("c method");
    }

}

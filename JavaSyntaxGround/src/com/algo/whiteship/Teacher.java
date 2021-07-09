package com.algo.whiteship;


public class Teacher {
    private String name;
    private int birty;

    public Teacher() {
    }

    public Teacher(String name, int birty) {
        this.name = name;
        this.birty = birty;
    }

    public static String hello(){
        System.out.println("Hello static mehtod");
        return "hello";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirty() {
        return birty;
    }

    public void setBirty(int birty) {
        this.birty = birty;
    }
}


package com.algo.enumtest;

import lombok.Getter;

@Getter

public enum OrderType {
    ORDERTYPE(1,"asd","description"),
    CANCELTYPE();
    private int id;
    private String title;
    private String description;

    OrderType(){

    }

    OrderType(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}

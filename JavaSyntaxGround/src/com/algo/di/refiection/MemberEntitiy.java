package com.algo.di.refiection;

import lombok.Getter;

@Getter
public class MemberEntitiy {
    private String name;
    private String city;

    public void updateName(String name){
        this.name = name;
    }
    public void updateCity(String city){
        this.city = city;
    }

}

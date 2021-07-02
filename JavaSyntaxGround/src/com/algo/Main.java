package com.algo;


import com.algo.annotaionImpl.Git;
import com.algo.annotaionImpl.GitBash;
import com.algo.whiteship.Other;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws NoSuchMethodException {
        Git gitTool = new GitBash();
        System.out.println("git Bash push connection : " + gitTool.push() );

        System.out.println("==================제네릭========================");

        System.out.println("==================클래스와 인터페이스 상속========================");




    }

}

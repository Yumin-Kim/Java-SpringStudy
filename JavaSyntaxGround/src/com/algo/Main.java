package com.algo;


import com.algo.annotaionImpl.Git;
import com.algo.annotaionImpl.GitBash;
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
    public static void main(String[] args){
        Git gitTool = new GitBash();
        System.out.println("git Bash push connection : " + gitTool.push() );

        System.out.println("==================제네릭========================");

        System.out.println("==================클래스와 인터페이스 상속========================");



    }

}

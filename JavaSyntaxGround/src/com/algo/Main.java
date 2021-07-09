package com.algo;

import com.algo.data.InnerClass;
import com.algo.data.abstractdata.ImplClass;
import com.algo.data.dto.MemberAddressDto;
import com.algo.data.dto.MemberFlatDto;
import com.algo.data.dto.MemberQueryDto;
import com.algo.data.dummydata.Member;
import com.algo.data.dummydata.MemberAddress;
import com.algo.data.dummydata.MemberDto;
import com.algo.di.refiection.DtoClass;
import com.algo.di.refiection.MemberEntitiy;
import lombok.EqualsAndHashCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {


    public static void main(String[] args) throws NoSuchMethodException {
        LangCopy langCopy = new LangCopy("a");
        Lang lang = new Lang("a");
        System.out.println("lang.hashCode() = " + lang.hashCode());
        System.out.println("langCopy.hashCode() = " + langCopy.hashCode());
        Lang a = new Lang("1");
        Lang b = new Lang("1");
        System.out.println("a.equals(b) = " + a.equals(b));
        System.out.println("a.hashCode() = " + a.hashCode());
        System.out.println("b.hashCode() = " + b.hashCode());
        System.out.println("a = " + a);
        System.out.println("System.identityHashCode(a) = " + System.identityHashCode(a));
        System.out.println("b = " + b);
        System.out.println("System.identityHashCode(b) = " +  System.identityHashCode(b));
        System.out.println("(a === b ) = " + (b == a));
        ImplClass implClass = new ImplClass();
        implClass.drift("asdasd");
        implClass.racing(200);
        System.out.println(implClass instanceof Object);
    }
    static class LangCopy{
        public String name;
        @Override
        public int hashCode() {
            System.out.println(Objects.hash(name));
            return Objects.hash(name);
        }

        public LangCopy(String name) {
            this.name = name;
        }
    }
    static class Lang{
        private String name;

        public Lang(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lang lang = (Lang) o;
            return Objects.equals(name, lang.name);
        }

        @Override
        public int hashCode() {
            System.out.println(Objects.hash(name));
            return Objects.hash(name);
        }
    }
    
}

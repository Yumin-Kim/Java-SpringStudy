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

        ImplClass implClass = new ImplClass();
        implClass.drift("asdasd");
        implClass.racing(200);
        System.out.println(implClass instanceof Object);
    }

}

package com.algo;

import com.algo.data.InnerClass;
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
        System.out.println("==================reflection ========================");
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
//        System.out.println("==================클래스와 인터페이스 상속========================");
//
//
//        System.out.println("==================Collector.groupById ==================");
//
//        Member member1 = new Member(1, "이름1", "01010101010", "시티코드1", "시티1", 1);
//        Member member2 = new Member(2, "이름", "01010101010", "시티코드2", "시티2", 2);
//        Member member3 = new Member(3, "이름3", "01010101010", "시티코드3", "시티3", 3);
//        Member member4 = new Member(4, "이름", "01010101010", "시티코드4", "시티4", 4);
//        Member member5 = new Member(5, "이름5", "01010101010", "시티코드5", "시티5", 5);
//
//        ArrayList<Member> members = new ArrayList<>();
//        members.add(member1);
//        members.add(member2);
//        members.add(member3);
//        members.add(member4);
//        members.add(member5);
//
////        Map<Integer, MemberDto> mapMemberDtos = members.stream()
////                .collect(Collectors.toMap(Member::getCount, o -> new MemberDto(o.getCity(), o.getCityCode())));
////        mapMemberDtos.forEach((integer, memberDto) ->{
////            System.out.println("integer = " + integer);
////            System.out.println("integer = " + memberDto.getCity());
////            System.out.println("integer = " + memberDto.getCityCode());
////        } );
//        Map<Integer, List<Member>> collect = members.stream()
//                .collect(groupingBy(Member::getCount));
//
//        Map<MemberDto, List<MemberAddress>> memberDtoListMap = members.stream()
//                .collect(groupingBy(member -> new MemberDto(member.getId(), member.getCityCode(), member.getCity())
//                        , mapping(memberDto -> new MemberAddress(memberDto.getCityCode(), memberDto.getCity()), toList())
//                        )
//                );
//
//        Set<Map.Entry<MemberDto, List<MemberAddress>>> entrySet = memberDtoListMap.entrySet();
//        entrySet.forEach((memberDtoListEntry) -> {
//            MemberDto key = memberDtoListEntry.getKey();
//            List<MemberAddress> value = memberDtoListEntry.getValue();
//            System.out.println("value = " + value);
//            System.out.println("key = " + key);
//        });
//
//        System.out.println("=========== Basic Map ,Set ===========");
//
//        Map<Integer, String> integerStringHashMap = new HashMap<>();
//        integerStringHashMap.put(1, "Hello");
//        integerStringHashMap.put(2, "Hello");
//        integerStringHashMap.put(3, "Hello");
//
//
//        Set<Map.Entry<Integer, String>> entries = integerStringHashMap.entrySet();
//        entries.stream().forEach(integerStringEntry -> {
//            Integer key = integerStringEntry.getKey();
//            System.out.println("key = " + key);
//            String value = integerStringEntry.getValue();
//            System.out.println("value = " + value);
//        });
//        HashSet<Object> objects = new HashSet<>();
//        objects.add("Hello");
//        objects.add("Hello");
//        objects.add("Hello");
//        objects.add("Hello1");
//        objects.stream().forEach(System.out::println);
//
//        System.out.println("=========== Member Dto Flat >> Dto ===========");
////        MemberFlatDto memberFlatDto1 = new MemberFlatDto("test", "010", 12, "city1");
////        MemberFlatDto memberFlatDto2 = new MemberFlatDto("test", "010", 12, "city1");
////        MemberFlatDto memberFlatDto3 = new MemberFlatDto("test", "010", 12, "city1");
////        MemberFlatDto memberFlatDto4 = new MemberFlatDto("test", "010", 12, "city1");
//        MemberFlatDto memberFlatDto1 = new MemberFlatDto("test", "010", 12, "city1");
//        MemberFlatDto memberFlatDto2 = new MemberFlatDto("test", "010", 123, "city2");
//        MemberFlatDto memberFlatDto3 = new MemberFlatDto("test1", "0101", 12, "city1");
//        MemberFlatDto memberFlatDto4 = new MemberFlatDto("test1", "0101", 123, "city2");
//
//        List<MemberFlatDto> result = new ArrayList<MemberFlatDto>();
//        result.add(memberFlatDto1);
//        result.add(memberFlatDto2);
//        result.add(memberFlatDto3);
//        result.add(memberFlatDto4);
//
//        Map<MemberQueryDto, List<MemberAddressDto>> collect1 = result.stream()
//                .collect(
//                        groupingBy(memberFlatDto -> new MemberQueryDto(memberFlatDto.getName(), memberFlatDto.getPhoneNumber()),
//                                mapping(memberFlatDto -> new MemberAddressDto(memberFlatDto.getCityCode(), memberFlatDto.getCity()), toList())
//                        )
//                );
//        collect1.entrySet().stream()
//                .map(data -> new MemberQueryDto(data.getKey().getName(), data.getKey().getPhoneNumber(), data.getValue()))
//                .collect(toList());
//
//        int size = collect1.size();
//        System.out.println("size = " + size);
//
//        System.out.println("=======================Basic hasCode equal======================= ");
//        MemberAddressDto testAddress1 = new MemberAddressDto(123, "test");
//        MemberAddressDto testAddress2 = new MemberAddressDto(123, "test1");
//        MemberAddressDto testAddress3 = new MemberAddressDto(123, "test1");
//        System.out.println(testAddress1);
//        System.out.println(testAddress2);
//        System.out.println(testAddress1.getCity().equals(testAddress2.getCity()));
//
//        HashMap<MemberAddressDto, InnerClass> memberAddressDtoHashMap = new HashMap<>();
//        memberAddressDtoHashMap.put(testAddress1, new InnerClass("title", 1L));
//        memberAddressDtoHashMap.put(testAddress2, new InnerClass("title", 1L));
//        memberAddressDtoHashMap.put(testAddress3, new InnerClass("title", 1L));
//        int size1 = memberAddressDtoHashMap.size();
//        System.out.println("size1 = " + size1);
//        Map<String, Integer> collect2 = Arrays.asList(testAddress1).stream()
//                .collect(toMap(MemberAddressDto::getCity, MemberAddressDto::getCityCode));
//        collect2.entrySet().forEach(data->{
//            System.out.println(data.getKey());
//            System.out.println(data.getValue());
//        });
//
//        Consumer hello = (a) -> System.out.println("Hello");
//        Function<InnerClass, Long> getId = InnerClass::getId;
//        Supplier<Integer> integerCallable = () -> 5;
//

    }

}

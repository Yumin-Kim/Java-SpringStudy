package data;


import data.dto.MemberAddressDto;
import data.dto.MemberFlatDto;
import data.dto.MemberQueryDto;
import data.dummydata.Member;
import data.dummydata.MemberAddress;
import data.dummydata.MemberDto;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

public class MapSetStreamTutorial {
    public void startMain(){


        System.out.println("==================Collector.groupById ==================");

        Member member1 = new Member(1, "asd1", "01010101010", "asdasd", "asd", 1);
        Member member2 = new Member(2, "asd", "01010101010", "asdasd2", "asd2", 2);
        Member member3 = new Member(3, "asd3", "01010101010", "asdasd3", "asd3", 3);
        Member member4 = new Member(4, "ASD", "01010101010", "asdasd4", "asd4", 4);
        Member member5 = new Member(5, "asd5", "01010101010", "asdasd", "asd5", 5);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);
        members.add(member5);

//        Map<Integer, MemberDto> mapMemberDtos = members.stream()
//                .collect(Collectors.toMap(Member::getCount, o -> new MemberDto(o.getCity(), o.getCityCode())));
//        mapMemberDtos.forEach((integer, memberDto) ->{
//            System.out.println("integer = " + integer);
//            System.out.println("integer = " + memberDto.getCity());
//            System.out.println("integer = " + memberDto.getCityCode());
//        } );
        Map<Integer, List<Member>> collect = members.stream()
                .collect(groupingBy(Member::getCount));

        Map<MemberDto, List<MemberAddress>> memberDtoListMap = members.stream()
                .collect(groupingBy(member -> new MemberDto(member.getId(), member.getCityCode(), member.getCity())
                        , mapping(memberDto -> new MemberAddress(memberDto.getCityCode(), memberDto.getCity()),
                                toList())
                        )
                );

        Set<Map.Entry<MemberDto, List<MemberAddress>>> entrySet = memberDtoListMap.entrySet();
        entrySet.forEach((memberDtoListEntry) -> {
            MemberDto key = memberDtoListEntry.getKey();
            List<MemberAddress> value = memberDtoListEntry.getValue();
            System.out.println("value = " + value);
            System.out.println("key = " + key);
        });

        System.out.println("=========== Basic Map ,Set ===========");

        Map<Integer, String> integerStringHashMap = new HashMap<>();
        integerStringHashMap.put(1, "Hello");
        integerStringHashMap.put(2, "Hello");
        integerStringHashMap.put(3, "Hello");


        Set<Map.Entry<Integer, String>> entries = integerStringHashMap.entrySet();
        entries.stream().forEach(integerStringEntry -> {
            Integer key = integerStringEntry.getKey();
            System.out.println("key = " + key);
            String value = integerStringEntry.getValue();
            System.out.println("value = " + value);
        });
        HashSet<Object> objects = new HashSet<>();
        objects.add("Hello");
        objects.add("Hello");
        objects.add("Hello");
        objects.add("Hello1");
        objects.stream().forEach(System.out::println);

        System.out.println("=========== Member Dto Flat >> Dto ===========");
//        MemberFlatDto memberFlatDto1 = new MemberFlatDto("test", "010", 12, "city1");
//        MemberFlatDto memberFlatDto2 = new MemberFlatDto("test", "010", 12, "city1");
//        MemberFlatDto memberFlatDto3 = new MemberFlatDto("test", "010", 12, "city1");
//        MemberFlatDto memberFlatDto4 = new MemberFlatDto("test", "010", 12, "city1");
        MemberFlatDto memberFlatDto1 = new MemberFlatDto("test", "010", 12, "city1");
        MemberFlatDto memberFlatDto2 = new MemberFlatDto("test", "010", 123, "city2");
        MemberFlatDto memberFlatDto3 = new MemberFlatDto("test1", "0101", 12, "city1");
        MemberFlatDto memberFlatDto4 = new MemberFlatDto("test1", "0101", 123, "city2");

        List<MemberFlatDto> result = new ArrayList<MemberFlatDto>();
        result.add(memberFlatDto1);
        result.add(memberFlatDto2);
        result.add(memberFlatDto3);
        result.add(memberFlatDto4);

        Map<MemberQueryDto, List<MemberAddressDto>> collect1 = result.stream()
                .collect(
                        groupingBy(memberFlatDto -> new MemberQueryDto(memberFlatDto.getName(), memberFlatDto.getPhoneNumber()),
                                mapping(memberFlatDto -> new MemberAddressDto(memberFlatDto.getCityCode(), memberFlatDto.getCity()), toList())
                        )
                );
        collect1.entrySet().stream()
                .map(data -> new MemberQueryDto(data.getKey().getName(), data.getKey().getPhoneNumber(), data.getValue()))
                .collect(toList());

        int size = collect1.size();
        System.out.println("size = " + size);

        System.out.println("=======================Basic hasCode equal======================= ");
        MemberAddressDto testAddress1 = new MemberAddressDto(123, "test");
        MemberAddressDto testAddress2 = new MemberAddressDto(123, "test1");
        MemberAddressDto testAddress3 = new MemberAddressDto(123, "test1");
        System.out.println(testAddress1);
        System.out.println(testAddress2);
        System.out.println(testAddress1.getCity().equals(testAddress2.getCity()));

        HashMap<MemberAddressDto, InnerClass> memberAddressDtoHashMap = new HashMap<>();
        memberAddressDtoHashMap.put(testAddress1, new InnerClass("title", 1L));
        memberAddressDtoHashMap.put(testAddress2, new InnerClass("title", 1L));
        memberAddressDtoHashMap.put(testAddress3, new InnerClass("title", 1L));
        int size1 = memberAddressDtoHashMap.size();
        System.out.println("size1 = " + size1);
        Map<String, Integer> collect2 = Arrays.asList(testAddress1).stream()
                .collect(toMap(MemberAddressDto::getCity, MemberAddressDto::getCityCode));
        collect2.entrySet().forEach(data->{
            System.out.println(data.getKey());
            System.out.println(data.getValue());
        });

        Consumer hello = (a) -> System.out.println("Hello");
        Function<InnerClass, Long> getId = InnerClass::getId;
        Supplier<Integer> integerCallable = () -> 5;


    }
}

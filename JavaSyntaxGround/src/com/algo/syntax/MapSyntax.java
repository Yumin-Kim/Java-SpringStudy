package com.algo.syntax;

import java.util.HashMap;
import java.util.Iterator;

public class MapSyntax {
    static HashMap<String, Integer> hashMap = new HashMap<>();

    public static void HashMapFunc(){
        hashMap.put("str", 30);
        hashMap.put("str1", 12);
        hashMap.put("str2", 23);
        hashMap.put("str3", 142);
        hashMap.put("str3", 123);
        int hashcodeInt = hashMap.hashCode();
        System.out.println("--------------HashMap--------------------");
        System.out.println("맵에 저장된 키들의 집합" + hashMap.keySet());
        System.out.println("HashCode +=="+hashcodeInt);
        hashMap.values().stream().forEach(e-> System.out.println(e));
        hashMap.remove("str1");
        Iterator<String> keys = hashMap.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            System.out.println(String.format("Key : %s ,value : %s",key,hashMap.get(key)));
        }
    }

}

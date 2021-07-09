package algoTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryHash {

    @Test
    @DisplayName("해시 문제 1번")
    void programmersHash_1() throws Exception {
        //given
        //when
        HashMap<String, Integer> totalHash = new HashMap<>();
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};


        Arrays.sort(participant);
        Arrays.sort(completion);

        int index = 0;

        for (String name : participant) {
            totalHash.put(name, totalHash.getOrDefault(name, 0
            ) + 1);
        }
        System.out.println();
        for (String name : completion) {
            totalHash.put(name, totalHash.get(name) - 1);
        }
        String answer = "";
        for (String name : totalHash.keySet()) {
            if (totalHash.get(name) != 0) {
                answer = name;
            }
        }
        //then
        System.out.println("answer = " + answer);
    }

    @Test
    @DisplayName("프로그래머스 해시 2번_스스로 풀기")
    void programmersHash_2() throws Exception {
        //given
        String[] phoneNumbers = {"119", "97674223", "1195524421"};
        boolean answer = true;
        for (String phoneNumber : phoneNumbers) {
            for (String data : phoneNumbers) {
                if (!phoneNumber.equals(data)) {
                    if (data.startsWith(phoneNumber)) {
                        System.out.println(data);
                        answer = false;
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("프로그래머스 2번 블로그 참고")
    void programmersHash_2_Test() throws Exception {
        //given
        String[] phoneNumbers = {"119", "97674223", "1195524421"};
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phoneNumbers.length; i++) {
            map.put(phoneNumbers[i], i);
        }
        for (int i = 0; i < phoneNumbers.length; i++) {
            for (int j = 1; j < phoneNumbers[i].length(); j++) {
                if (map.containsKey(phoneNumbers[i].substring(0, j))) {
                }
            }
        }
        //when

        //then
    }

    @Test
    @DisplayName("프로그래머스 3번 스스로 풀기")
    void programmersHash_3() throws Exception {
        //given
        String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
        HashMap<String, Integer> clothCol = new HashMap<>();
        for (String[] cloths : clothes) {
            clothCol.put(cloths[0], clothCol.getOrDefault(cloths[0],0)+1);
        }
        clothCol.entrySet().stream().forEach(data->{
            System.out.println(data.getValue());
        });

        HashMap<String,Integer> map = new HashMap<>();
        int answer = 0;
        for(int i = 0 ; i < clothes.length;i++)  map.put(clothes[i][1],map.getOrDefault(clothes[i][1],0)+1);
        for (int i:  map.values())  answer*=(i+1);
        System.out.println("answer = " + answer);
        //when
        //then
    }

    @Test
    @DisplayName("프로그래머스 해시 4")
    void programmersHash_4() throws Exception{
        //given

        //when

        //then
    }

}

package CodingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFSCoding {

    public static boolean[] visited = new boolean[9];
    public static ArrayList<Integer> graph = new ArrayList<>();

    public static void main(String[] args) {
        solution("1934",2);

    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length();
        int index = 0;
        int n = len - k;

        for(int i = 0 ; i < n ; i++){
            char max = '0';

            for(int j = index ; j <= k + i ; j++){
                char tmp = number.charAt(j);
                System.out.println("index = " + index +"_" + i);
                
                if(max < tmp){
                    max = tmp;
                    index = j +1;
                    System.out.println(" max < tmp index = " + index);
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
    
}

package CodingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFSCoding {

    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) {
        solution("hit", "cog",["hot", "dot", "dog", "lot", "log", "cog"])


    }


    public static int solution(String begin, String target, String[] words) {
        dfs(begin , target , words,0);
        return answer;
    }


    private static void dfs(String begin, String target, String[] words, int cnt){
        if(begin.equals(words)){
            answer = cnt;
            return;
        }else{
            for(int i = 0 ; i < words.length ; i++){
                if(visited[i]){
                    continue;
                }
                int k = 0;

                for(int j = 0 ; j < words[i].length() ; j ++){
                    if(begin.charAt(j) == words[i].charAt(j)){
                        k++;
                    }
                }
                if(k == begin.length() - 1){
                    visited[i] = true;
                    dfs(words[i],target,words,cnt+1);
                    visited[i] = false;
                }
            }
        }

}

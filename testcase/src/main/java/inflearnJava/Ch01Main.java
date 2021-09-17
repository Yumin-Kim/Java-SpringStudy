package inflearnJava;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Ch01Main {

    /**
     * 문자열 대소문자 변경
     * 문자열을 character 배열로 변경하는 메소드
     * char.toCharArray()
     *
     * @param str
     * @param t
     * @return
     */
    public int solution_1(String str, char t) {
        int answer = 0;

        String upperCaseStr = str.toUpperCase();
        char c = Character.toUpperCase(t);
        for (int i = 0; i < upperCaseStr.length(); i++) {
            if (upperCaseStr.charAt(i) == c) {
                answer++;
            }
        }
        return answer;
    }

    /**
     * 대소문자 변환
     * Character.isLowerCase() 소문자 체크 return boolean
     * 아스키 코드로 변환 가능
     * 65~90 대문자
     * 97~122 소문자
     * 32 정도 차이가 있기 때문에 +=32후 char로 타입 캐스팅 필요
     *
     * @param str
     * @return
     */
    public String solution_2(String str) {
        String answer = "";
        for (char x : str.toCharArray()) {
//            if(Character.isLowerCase(x))  answer += Character.toUpperCase(x);
//            else answer += Character.toLowerCase(x);
            if (x >= 65 && x <= 90) {
                answer += (char) (x + 32);
            } else {
                answer += (char) (x - 32);
            }
        }
        return answer;
    }

    /**
     * StringBuilder를 활용한 구현
     * String.valueof(char) >> Character 를 String으로 변형된다.
     *
     * @param n
     * @param a
     * @return
     */
    public ArrayList<String> solution_3(int n, String[] a) {
        ArrayList<String> answer = new ArrayList<>();
        for (String x : a) {
            char[] chars = x.toCharArray();
            int lt = 0, rt = chars.length - 1;
            while (lt < rt) {
                char tmp = chars[lt];
                chars[lt] = chars[rt];
                chars[rt] = tmp;
                lt++;
                rt--;
            }
            String s = String.valueOf(chars);
//            String s = new StringBuilder(x).reverse().toString();
            answer.add(s);
        }
        return answer;
    }

    /**
     * Character.isAlphabetic(chars[lt]) >> Character.isAlphabetic 알파벳유무 판단
     * 아스키코드로도 가능 대문자 65~90 소문자 97-122
     *
     * @param str
     * @return
     */
    public String solution_4(String str) {
        String answer = "";
        char[] chars = str.toCharArray();
        int lt = 0, rt = chars.length - 1;
//            if (!Character.isAlphabetic(chars[lt])) lt++;
//            else if (!Character.isAlphabetic(chars[rt])){ rt--;}
//            else{
//             }
        while (lt < rt) {
            char tmp = chars[lt];
            if ((tmp >= 65 && tmp <= 90)) {
                chars[lt] = chars[rt];
                chars[rt] = tmp;
            }
            if ((tmp >= 97 && tmp <= 122)) {
                chars[lt] = chars[rt];
                chars[rt] = tmp;
            }
            lt++;
            rt--;
        }
        answer = String.valueOf(chars);
        return answer;
    }

    /**
     * 문자열간 중복 제거
     * string.charAt(index) >> 해당 문자열의 인덱스에 해당하는 character 문자 확인 가능
     *
     * @param str
     * @return
     */
    public String soulution_5(String str) {
        String answer = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == str.indexOf(str.charAt(i))) {
                System.out.println("x = " + str.charAt(i));
                answer += str.charAt(i);
            }
        }
        return answer;
    }

    /**
     * 희문
     * StringBuilder()를 활용하여 reverse.toString()진행후 equal() 활용
     *
     * @return
     */
    public String soulution_6(String str) {
        String answer = "YES";
        String upperCase = str.toUpperCase();
        for (int i = 0; i < str.length() / 2; i++) {
            System.out.println("i = " + i);
            if (upperCase.charAt(i) != upperCase.charAt(str.length() - 1 - i)) {
                System.out.println("i = " + upperCase.charAt(i));
                answer = "No";
            }
        }
        String upperCase1 = str.toUpperCase();
        StringBuilder stringBuilder = new StringBuilder(upperCase1);
        String reverseStr = stringBuilder.reverse().toString();
        if (reverseStr.equals(upperCase1)) {
            System.out.println("희문 가능 동일합니다.");
        } else {
            System.out.println("희문 가능 불가능 합니다.");
        }
        return answer;
    }

    /**
     * replaceAll(정규 표현식 , 변경될 문자)
     *
     * @param str
     * @return
     */
    public String solution_7(String str) {
        String answer = "NO";
        String uppverCase = str.toUpperCase().replaceAll("[^A-Z]", "");
        String s = new StringBuilder(uppverCase).reverse().toString();
        if (s.equals(uppverCase)) {
            answer = "YES";
        }
        return answer;
    }

    /**
     * Character.isDigit >> 문자가 숫자인지 판별
     * 아스키코드의 숫자는 0~9 >> 48 ~ 57 까지 이다.
     *
     * @param str
     * @return
     */
    public Integer solution_8(String str) {
        int answer = 0;
        for (char x : str.toCharArray()) {
            if (x >= 48 && x <= 57) {
                answer = (answer * 10) + (x - 48);
            }
        }
        String answer_1 = "";
        for (char x : str.toCharArray()) {
            if (Character.isDigit(x)) {
                answer_1 += x;
            }
        }
        System.out.println("answer_1 = " + answer_1);
        System.out.println("Integer.parseInt(answer_1) = " + Integer.parseInt(answer_1));
        return answer;
    }

    public int[] solution_9(String str, char n) {
        int[] ints = new int[str.length()];
        int p = 1000;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == n) {
                p = 0;
                ints[i] = p;
            } else {
                p++;
                ints[i] = p;
            }
        }
        p = 1000;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == n) p = 0;
            else {
                p++;
                ints[i] = Math.min(p, ints[i]);
            }
        }
        return ints;
    }

    public String solution_10(String str) {
        int count = 1;
        String tmp = "";
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                tmp += str.charAt(i);
                if (count > 1) {
                    tmp += String.valueOf(count);
                }
                count = 1;
            }
        } 
        return tmp;
    }

    public String solution_11(int n , String str){
        String answer = "";

        for (int i = 0; i < n; i++) {
            String tmp = str.substring(0, 7);
            String replace = tmp.replace('#', '1').replace('*','0');
            int i1 = Integer.parseInt(replace, 2);
            System.out.println("tmp = " + tmp + "   " +(char)i1);
            str = str.substring(7);
        }
        return null;
    }


    /**
     * new Scanner 받아옴
     * sc.next() >> 단어 하나만
     * sc.nextLine() >> 문장을 받앙옴 , 뛰어쓰기존재한다면 nextLine()을 사용
     *
     * @param args
     */
    public static void main(String[] args) {
        Ch01Main T = new Ch01Main();
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        String[] str = new String[n];
//        for (int i = 0; i < n; i++) {
//            String s = sc.next();
//            str[i] = s;
//        }
        String s = sc.next();
        String str = sc.next();
//        char c = sc.next().charAt(0);
//                String str = sc.nextLine();
        System.out.println("T.solution_9(str) = " + T.solution_11(Integer.parseInt(s),str));
//        char c = sc.next().charAt(0);
//        T.solution_3(n, str).stream()
//                .forEach(System.out::println);
//        return;
    }


}
